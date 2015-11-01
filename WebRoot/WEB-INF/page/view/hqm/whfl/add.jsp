<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
			src="${pageContext.request.contextPath}/fun/njb/Validator.js"></script>
<script type="text/javascript">
	$(function(){
		$("#save").click(function(){
			/*var form = document.saveform;
			var name = form.name.value;
			
			if($.trim(name)==""){
				alert("分类名称不能为空！");
				form.name.focus();
				return;
			}
			
			var url = "whfl_isRepeat.action";
			var data = {"name":name};
			$.post(url,data,function(data){
				if(data=="1"){
					alert("分类名称重复，请重新输入分类名称！");
					form.name.focus();
					name = "";
				}else{
					form.submit();
				}
			},"html");*/
			Validator.Validate(document.forms[0], 3);
		});
	});
</script>
</head>

<body onload="MM_preloadImages('fun/system/images/nchjbxx-nav01-.png')">
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>
	
	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;数码文化娱乐信息系统&nbsp;&gt;&gt;&nbsp;数码文化娱乐资源&nbsp;&gt;&gt;&nbsp;文化娱乐资源分类&nbsp;&gt;&gt;&nbsp;添加</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="whfl_save.action" method="post" name="saveform">
				<div class="biaodan-all" style="height:800px;">
					<div class="biaodan-title">数码文化娱乐分类信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">文化分类信息</td>
						</tr>
						<tr>
							<td>分类名称（<font class="fred">*</font>）</td>
							<td colspan="3"><input name="name" type="text" class="qbg" maxlength="25" 
									dataType="Require" msg="文化娱乐分类名称不能为空！" require="true" noRepeat="true" repeatTab="TblWhfl" repeatMsg="文化娱乐分类名称已经存在！"/></td>
						</tr>
						
					</table>
					<div class="bcan">
						<a href="#" id="save">
							<img src="fun/system/images/bc.gif">
						</a>
						&nbsp;
						<a href="javascript:history.go(-1);">
							<img src="fun/system/images/fh.gif">
						</a>
					</div>
				</div>
				</form>
				<!--表单内容 结束 -->
			</div>
		</div>
		<!--right主体内容  结束-->

		<jsp:include page="/WEB-INF/page/system/index/left.jsp"></jsp:include>
		<div class="clearit"></div>
	</div>
	<!--主体内容 结束-->
	
	<jsp:include page="/WEB-INF/page/system/index/foot.jsp"></jsp:include>
	
</body>
</html>