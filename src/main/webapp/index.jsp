<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<div id="vpnDiv">
		<p>系统检测到访问medium.com需要魔法</p>
		代理服务器:<input id="vpnHost"/>
		代理端口:<input id="vpnport"/>
		代理方式:<select id="vpnType">
			<option value="1">socket</option>
			<option value="2">http</option>
		</select>
		<button id="testPing">测试</button>
	</div>
	<div id="pullDiv" style="display:none;">
		<p>代理成功</p>
		<button id="pull">Create pdf files</button>
		<button id="download">download</button>
		<div id="info"> 
				<p ><span id="ingmsg" style="display: none">数据抓取中...</span> &nbsp;&nbsp;&nbsp;  当前已经抓取:<span id="pullSize"></span>章文章</p>
				排名前10数据:
				<div id="topList">
						
				</div>
		</div>
	</div>
</body>
<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"
  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
  crossorigin="anonymous"></script>
  
<script type="text/javascript">
$(document).ready(function(){
	$("#vpnDiv").hide();
	 $.get('/vpnCheck',function(res){
        if(res == true){
        	$("#vpnDiv").show();
        }
     })
     
     $("#testPing").click(function(){
    	 var host = $("#vpnHost").val();
    	 var port = $("#vpnport").val();
    	 var type = $("#vpnType").val();
    	 $.post('/setVpn',{host:host,port:port,type:type},function(res){
            if(res == true){
            	$("#vpnDiv").hide();
            	$("#pullDiv").show();
            }else{
            	alert("当前vpn配置不通")	
            }
         })
    	
     });
	 
	 setInterval(function(){
		 
		 $.post('/info',function(res){
	            
			 $("#pullSize").text(res.pullSize);
			 var html = "";
			 $.each(res.articles, function(index, article) {
				 
				 	html +="<p ><span style=\"width:300px;\">文章标题:"+article.title+"</span> &nbsp;&nbsp;&nbsp; <span style=\"width:100px;\">点赞数:"+article.clap+"</span></p><hr/>"
				 
				  	
				});
			 
			 $("#topList").html(html);
			 
	     })
		 
	 }, 2000);
	 
	 
	 $("#pull").click(function(){
		 
		 $.post('/pullData',function(res){
	            if(res == true){
	            	$("#ingmsg").show();
	            }
	         })
		 
	 });
	 
	 
	$("#download").click(function(){
		 
		window.open("/download", "_blank");
		 
	 });
	 
	 

});
</script>
</html>