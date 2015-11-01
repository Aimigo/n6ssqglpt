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
	
	<iframe src="uploadfile_videoplayer.action?w=60%&h=40%&vs=rtmp://192.168.3.138:1935/myapp/test1&is=<%=basePath %>videos/transcod/1414133406992.jpg&ms=qqqqqqqq&p=0&wh=16:9"
		style="width:800px; height: 600px" id="_img_iframe" scrolling="no"/>
	<br />


</body>

</html>