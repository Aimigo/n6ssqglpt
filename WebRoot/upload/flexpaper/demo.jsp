<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>readonline</title>

</head>
<body>
	
	<br />
	
	<iframe src="uploadfile_readonline.action?w=745&h=580&s=<%=basePath %>upload/flexpaper/doc/noread.swf"
		style="width:800px; height: 600px" id="_img_iframe" />
	<br />


</body>

</html>