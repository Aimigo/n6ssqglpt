 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%request.setAttribute("url",request.getContextPath()+"/admin");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<script type="text/javascript">
<!--
		//去空格函数
		String.prototype.trim = function () {
		    return this.replace(/(^\s*)|(\s*$)/g, "");
		};
		
		//获取action存入的信息
		var message="${message}";
		if(message!=""){//判断并打印信息
			alert(message);
			parent.location.reload(true);
		}
		
		var imgname="${imgname}";
		if(imgname!="")
			window.parent.showZd(imgname);
			
		function $submit(){
			try {
				
				var fileName  = document.getElementById("_upFile").value;
	        	var fileName1 = fileName.substring(fileName.lastIndexOf(".")+1);
	        	if(fileName1=="xls"||fileName1=="xlsx"){
	        		document.getElementById('imgForm').submit();
		        }else{
	        		alert("请选择正确的Excel表格");
	        	}
			} catch (e) {
				alert(e);
			}
		}
//-->
</script>
	</head>
	<body style="display:none;">
		<form id="imgForm" action="jmxx_excelUp.action" method="post"
			enctype="multipart/form-data" name="imgForm" style="display:none;">
			<input type="file" value="浏览文件 " name="upload" onchange="$submit()" border= "0" id="_upFile" style="width: 250px"/>
			<input type="hidden" name="filename" id="filename" />
		</form>

	</body>
</html>
<script>
	window.parent.fileUpload = document.getElementById('_upFile');
</script>
