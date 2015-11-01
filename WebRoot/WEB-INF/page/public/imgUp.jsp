<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	request.setAttribute("url", request.getContextPath() + "/admin");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<style>
<!--
body {
	margin: 0px;
	padding: 0px;
	overflow: auto;
	scrollbar-face-color: #efefef;
	scrollbar-highlight-color: #ffffff;
	scrollbar-shadow-color: #e0e0e0;
	scrollbar-3dlight-color: #e0e0e0;
	scrollbar-arrow-color: #000;
	scrollbar-track-color: #f6f6f6;
	scrollbar-darkshadow-color: #ffffff;
}

.icon_upload {
	background: url(${url}/images1/btn_upload.gif) no-repeat 0px 8px;
	padding-top: 2px;
	margin-top: 0px;
	margin-bottom: 0px;
	border: none;
	height: 25px;
	width: 49px;
	cursor: pointer;
}
-->
</style>
<script type="text/javascript">				
	//获取action存入的信息
	var message="${message}";
	if(message!="")//判断并打印信息
		alert(message)
	var imgname="${imgname}"
	if(imgname!="")
		window.parent.setImage(imgname,'${id}','${tempImgContentType}')
		
	<c:if test="${fn:length(actionErrors[0])>0}">
		alert("${actionErrors[0]}")
	</c:if>	
	function imgClick(){
		document.getElementById('_upFile').click();
	}
	
	function $submit(){
		try {
			if(document.getElementById('_upFile').value==""){
				alert('请选择要上传的文件')
			}else{
				window.parent.document.getElementById("imgpath").value=document.getElementById('_upFile').value;
				document.getElementById('imgForm').submit();
			}
				
		} catch (e) {
		
		}
	}
</script>
</head>
<body>
	<form id="imgForm" action="upLoadAction!fileUp.action" method="post"
		enctype="multipart/form-data" name="imgForm">
		<input type="file" value="浏览文件" name="tempImg" border="0" id="_upFile" onchange="$submit()" />
		<input type="hidden" name="upAddr" id="upAddr" value="" /> 
		<input type="button" onclick="$submit()" class="icon_upload" />
	</form>
</body>
<script type="text/javascript">
	window.parent.fileUpload = document.getElementById('_upFile');
</script>
</html>
