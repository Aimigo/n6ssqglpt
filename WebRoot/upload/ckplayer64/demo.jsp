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
<title>ckplayer</title>

</head>
<body>
	
	<br />
	
	<iframe src="uploadfile_videoplayer.action?w=600&h=400&vs=<%=basePath %>videos/transcod/1414133406992.flv&is=<%=basePath %>videos/transcod/1414133406992.jpg&ms=qqqqqqqq"
		style="width:800px; height: 600px" id="_img_iframe" />
	<br />


</body>

</html>