package com.lwphk.pull_medium.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.lwphk.pull_medium.vo.Article;
import com.lwphk.pull_medium.vo.Items;
import com.lwphk.pull_medium.vo.Result;
import com.lwphk.pull_medium.vo.req.GraphqlRequestVo;
import com.lwphk.pull_medium.vo.req.Paging;
import com.lwphk.pull_medium.vo.req.VariablesVo;

/**
 * @author: lwphk
 * @date: 2024年1月6日 上午12:28:45
 */
public class PullDatUtil {

	private static final String url = "https://medium.com";
	
	private static final String uri = "/_/graphql";

	

	/**
	 * top文章数据抓取
	 * 
	 * @param proxyType 1:socket代理 2:http代理
	 * @param proxyHost 代理服务器
	 * @param proxyPort 代理端口
	 * @param ids       文章id集合
	 * @return
	 */
	public static List<Article> pullData(int proxyType, String proxyHost, int proxyPort, int size) {
		
		List<Article> list = new ArrayList<Article>();
		
		 List<GraphqlRequestVo> reqs = new ArrayList<GraphqlRequestVo>();
	      GraphqlRequestVo req = new GraphqlRequestVo();
	       req.setOperationName(GraphqlRequestVo.operationNameTagRecommendedFeedQuery);
	        req.setQuery(GraphqlRequestVo.queryTagRecommendedFeedQuery);
	        
	        VariablesVo variables = new VariablesVo();
	        variables.setTagSlug(VariablesVo.tagslugval);
	        variables.setPaging(new Paging(size+"",size+25+"","25",""));
			req.setVariables(variables );
			
			reqs.add(req);
			System.out.println(JSON.toJSONString(reqs));
			Connection connect = Jsoup.connect(url+uri).timeout(5000).header("Content-Type", "application/json")
					.requestBody(JSON.toJSONString(reqs)).ignoreContentType(true).method(Connection.Method.POST);

			if (proxyType == 1) {
				connect.proxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyHost, proxyPort)));
			} else if (proxyType == 2) {
				connect.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
			}

			Response response;
			try {
				response = connect.execute();
				int statusCode = response.statusCode();
				
				if (statusCode == 200) {
					 String responseBody = response.body();
					 List<Result> res = JSON.parseObject(responseBody, new TypeReference<List<Result>>() {});
					 
					 
					 
					 for (Result r : res) {
						List<Items> itemList = r.getData().getTagFromSlug().getViewerEdge().getRecommendedPostsFeed().getItems(); 
						
						itemList.forEach(i->{
							Article ar = new Article();
							ar.setId(i.getPost().getId());
							ar.setTitle(i.getPost().getTitle());
							ar.setClap(i.getPost().getClapCount());
							
							list.add(ar);
						});
						
					}
					 
					
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			
			
			
			return list;
			
			
	}
	
	
	
	
	
	
	
	/**
	 * top文章数据抓取
	 * 
	 * @param proxyType 1:socket代理 2:http代理
	 * @param proxyHost 代理服务器
	 * @param proxyPort 代理端口
	 * @param ids       文章id集合
	 * @return
	 */
	public static List<Result> pullDataByIds(int proxyType, String proxyHost, int proxyPort, List<String> ids) {
		List<GraphqlRequestVo> reqs = new ArrayList<GraphqlRequestVo>();

		ids.forEach(e -> {
			GraphqlRequestVo req = new GraphqlRequestVo(new VariablesVo(e));
			req.setQuery(GraphqlRequestVo.queryIds);
			req.setOperationName(GraphqlRequestVo.operationNameIds);
			reqs.add(req);
		});
		
		System.out.println( "req"+JSON.toJSONString(reqs));
		Connection connect = Jsoup.connect(url+uri).timeout(5000).header("Content-Type", "application/json")
				.requestBody(JSON.toJSONString(reqs)).ignoreContentType(true).method(Connection.Method.POST);

		if (proxyType == 1) {
			connect.proxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyHost, proxyPort)));
		} else if (proxyType == 2) {
			connect.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
		}

		Response response;
		try {
			response = connect.execute();
			int statusCode = response.statusCode();
			
			if (statusCode == 200) {
				String responseBody = response.body();
				System.out.println(responseBody);
				return JSON.parseObject(responseBody, new TypeReference<List<Result>>() {
				});
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * 魔法测试
	 * 
	 * @return
	 */
	public static boolean testPing(String host,int port,int type) {
		Connection connect = Jsoup.connect(url).timeout(3000).ignoreContentType(true).method(Connection.Method.GET);
		
		if (type == 1) {
			connect.proxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, port)));
		} else if (type == 2) {
			connect.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)));
		}
		try {
			Response response = connect.execute();
			int statusCode = response.statusCode();
			return statusCode > 0;
		} catch (IOException e) {
			if (e instanceof SocketTimeoutException ) {
				return false;

			}
			e.printStackTrace();
		}
		return false;
	}

}
