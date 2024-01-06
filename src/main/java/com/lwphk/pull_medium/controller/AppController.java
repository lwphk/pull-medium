package com.lwphk.pull_medium.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lwphk.pull_medium.config.SystemConfig;
import com.lwphk.pull_medium.util.HTMLTemplateUtils;
import com.lwphk.pull_medium.util.PDFUtils;
import com.lwphk.pull_medium.util.PullDatUtil;
import com.lwphk.pull_medium.vo.Article;
import com.lwphk.pull_medium.vo.Fy;
import com.lwphk.pull_medium.vo.Paragraphs;
import com.lwphk.pull_medium.vo.Result;
import com.lwphk.pull_medium.vo.Show;

@Controller
public class AppController {

	
	@Autowired
	SystemConfig config;
	
	Connection fyConnect = Jsoup.connect("https://aip.baidubce.com/rpc/2.0/mt/texttrans/v1?access_token=24.29064cf154534e8042e0a364c6a30142.2592000.1707105926.282335-46466535").ignoreContentType(true).method(Connection.Method.POST).timeout(3000).header("Content-Type", "application/json");
			
	
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("needVpn", config.getNeedVpn());
		return "index";
	}
	
	@RequestMapping("/vpnCheck")
	@ResponseBody
	public boolean vpnCheck(Model model) {
		return  config.getNeedVpn();
	}
	
	
	
	@RequestMapping("/setVpn")
	@ResponseBody
	public boolean vpnCheck(String host,Integer port,int type) {
		
		boolean res = PullDatUtil.testPing(host, port, type);
		if(res) {
			config.setVpnHost(host);
			config.setVpnPort(port);
			config.setVpnType(type);
		}
		return  res;
	}
	
	
	@RequestMapping("/pullData")
	@ResponseBody
	public boolean pullData() {
		
		 new Thread() {
	            @Override
	            public void run() {
	            	int size = 0;
	            	while(true) {
	            		List<Article> list = PullDatUtil.pullData(config.getVpnType(),config.getVpnHost(), config.getVpnPort(), size);
	            		
	            		
	            		config.getArticles().addAll(list);
	            		
	            		
	                    Collections.sort(config.getArticles(), Comparator.comparingInt(Article::getClap).reversed());

	                    // 仅保留前10条数据
	                    List<Article> top10Articles = config.getArticles().subList(0, Math.min(10, config.getArticles().size()));
	                    
	                    config.setArticles(top10Articles);
	            		
	            		size +=25;
	            		
	            		config.setPullSize(size);
	            		
	            		if(size >= 87000 || list ==null || list.isEmpty()) break;
	            		
	            		try {
	        				Thread.sleep(3000);
	        			} catch (InterruptedException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        			}
	            	}
	            }
	        }.start();
		
		return  true;
	}
	
	@RequestMapping("/info")
	@ResponseBody
	public SystemConfig info() {
		
		return  config;
	}
	
	
	
	@RequestMapping("/download")
	@ResponseBody
	public String down(HttpServletResponse response) {
		
		// 设置响应头
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=pdfs.zip");
		
		try {
			byte[] bytes = getZipBytes();
			response.getOutputStream().write(bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "下载中";
	}
	
	
	
	private byte[] getZipBytes() throws IOException {
		
		List<Article> top = config.getArticles();
		
		List<String> ids = top.stream().map(Article::getId).collect(Collectors.toList());
		
		List<Result> list = PullDatUtil.pullDataByIds(config.getVpnType(), config.getVpnHost(), config.getVpnPort(), ids);
		
		
		Map<String, String> req = new HashMap<String, String>();
		
		req.put("from", "auto");
		req.put("to", "zh");
		//pdf的文件流
		List<byte[]> pdfBytesList = new ArrayList<byte[]>();
		//每个pdf的文件名
		List<String> fileNames = new ArrayList<String>();
		
		list.forEach(e->{
			List<Show> textList = new ArrayList<Show>();
			List<Paragraphs> paragraphsList = e.getData().getPostResult().getContent().getBodyModel().getParagraphs();
			paragraphsList.forEach(p->{
				if(!"IMG".equalsIgnoreCase(p.getType())) {
					String org = p.getText();
					String zh = "这里是中文翻译";
					
					req.put("q", org);
					try {
						Connection conn = fyConnect.requestBody(JSON.toJSONString(req));
						Fy fy = JSON.parseObject(conn.execute().body(),Fy.class);
						zh = fy.getResult().getTrans_result().stream().findFirst().get().getDst();
						System.out.println("fanyi:"+zh);
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					
					Show show = new Show(org,zh);
					
					textList.add(show);
				}
					
			});
			
			
			String html;
			try {
				html = HTMLTemplateUtils
						.getText(new ClassPathResource("template/List.html").getInputStream());
				HashMap<String, Object> map = new HashMap<>();
				map.put("list", textList);
				
				String render = HTMLTemplateUtils.render(html, map);
				
				
				PDFUtils pd = new PDFUtils();
				
				
				
				fileNames.add(e.getData().getPostResult().getTitle()+".pdf");
				
				pdfBytesList.add(pd.html2PDF(render));
				
			
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
			
			
		});
	

        byte[] zipBytes = createZip(pdfBytesList, fileNames);
            
        return zipBytes; 

		
	}
	
	public static byte[] createZip(List<byte[]> fileStreams, List<String> fileNames) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (int i = 0; i < fileStreams.size(); i++) {
                byte[] fileStream = fileStreams.get(i);
                String fileName = fileNames.get(i);

                ZipEntry zipEntry = new ZipEntry(fileName);
                zos.putNextEntry(zipEntry);

                ByteArrayInputStream bis = new ByteArrayInputStream(fileStream);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    zos.write(buffer, 0, bytesRead);
                }
                bis.close();

                zos.closeEntry();
            }
        }

        return baos.toByteArray();
    }
	
	
	
}
