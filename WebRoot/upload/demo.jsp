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
<title>uploaddemo</title>
<script type="text/javascript">
	isload=true;
	function uploadfilebegin(){
		isload=false;
		alert(isload);
	}
	function uploadfileend(file0,file1,file2){
		isload=true;
		alert(file0+";;;;;;;;;"+file1+";;;;;;;;;"+file2);
	}
	function aler(){
		alert(document.getElementById("upload_url1").value);
	}
</script>
</head>
<body>
	<input type="hidden" id="upload_url0" value="" />
	<input type="hidden" id="upload_url1" value="" />
	<input type="hidden" id="upload_url2" value="" />
	<input type="button" onclick="aler()"value="获取返回值"/>
	<br />
	
	<iframe src="uploadfile_uploadfile.action?lx=imgs"
		style="width:630px; height: 400px" id="_img_iframe" />
	<br />


</body>

</html>