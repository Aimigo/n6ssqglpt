<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>您访问的页面出错啦</title>
	<style type="text/css">
	*{margin:0;padding:0;}
	body{background:url(fun/system/images/bg.jpg)repeat-x;}
	.main{width:1200px;margin:0 auto;position: relative;}
	.fanhui{background: url(fun/system/images/fanhui.png) left center no-repeat;position: absolute;left:500px;top:503px;padding-left: 20px;}
	.main a{font-size: 16px;color:#000;text-decoration: none;font-family: "微软雅黑";}
	.main a:hover{color:#ea4d02;}
	.home{background: url(fun/system/images/home.png)left center no-repeat;position: absolute;left:650px;top:503px;padding-left: 20px;}

	</style>
</head>
<body>
	<div class="main">
	<img src="fun/system/images/500tupian.jpg"/>
	<a href="javascript:history.go(-1);" class="fanhui">返回</a>
	<a href="login.jsp" class="home">网站首页</a>
</div>
</body>
</html>