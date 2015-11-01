<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登入超时</title>
<SCRIPT type=text/javascript>
var i = 4;
var intervalid;
if(window.parent != window){
	window.parent.location.reload(true);
}else{
	intervalid = setInterval("fun()", 1000);
	function fun() {
		var msg = document.getElementById("msg");
		if (i == 0){
			window.location.href = "login.jsp";
			clearInterval(intervalid);
		}
		msg.innerHTML = i;
		i--;
	}
}
</SCRIPT>
<style type="text/css">
	*{margin:0;padding:0;}
	body{background:url(fun/system/images/bg.jpg)repeat-x;}
	.main{width:1200px;height:768px;margin:0 auto;position: relative;background: url(fun/system/images/dlcs.jpg) no-repeat;}
	.main a{font-size: 16px;}
	.main a:hover{color:#ea4d02;}
	.main img{position: absolute;top:195px;left:515px;}
	.main .login{position: absolute;top:503px;left:570px;}
	.main .tiao{position: absolute;top:550px;left:486px;font-size: 14px;color: #000;}
	.main .tiao em{font-size: 16px;color:#ea4d02;font-weight: bold;font-style: normal; }

	</style>
</head>
<body>
	<div class="main">
		<img src="fun/system/images/404s.gif" alt="" />
		<a href="login.jsp" class="login">重新登录</a>
		<span class="tiao">友情提示：<em id="msg">5 </em> 秒后将跳转到登录页面</span>
	</div>
</body>
</html>