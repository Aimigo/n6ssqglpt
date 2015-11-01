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
<script type="text/javascript" src="fun/njb/Validator.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="fun/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="fun/ueditor/ueditor.all.js"></script>
<script type="text/javascript">
	$(function(){
		$("#update").click(function(){
			var form = document.updateform;
			Validator.Validate(form,3);
		});
		
		/* 实例化编辑器  */
    	var ue = UE.getEditor('nr');
	});
</script>
<style type="text/css">
.edui-default .edui-editor-bottomContainer td{border: 1px solid #ccc;}
</style>
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;医疗卫生&nbsp;&gt;&gt;&nbsp;医疗知识&nbsp;&gt;&gt;&nbsp;医疗知识&nbsp;&gt;&gt;&nbsp;修改</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="ylzs_update.action" method="post" name="updateform">
				<div class="biaodan-all">
					<div class="biaodan-title">医疗知识信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">医疗知识信息</td>
						</tr>
						<tr>
							<td>知识标题（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="bt" type="text" class="qbg" maxlength="25" value="${ylzs.bt }"
									dataType="Require" msg="知识标题不能为空！" require="true"/>
								<input name="id" type="hidden" class="qbg" maxlength="9" value="${ylzs.id }"/>
							</td>
						</tr>
						<tr>
							<td>知识分类（<font class="fred">*</font>）</td>
							<td colspan="3">
								<select name="flid">
									<c:forEach items="${ylzsfl}" var="zsfl">
										<option value="${zsfl.id }" <c:if test="${zsfl.id eq ylzs.flid }">selected="selected"</c:if>>${zsfl.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td valign="top">知识内容（<font class="fred">*</font>）</td>
							<td colspan="3">
								<textarea name="nr" id="nr" type="text" style="width:98%;"
									dataType="Require" msg="知识内容不能为空！" require="true">${ylzs.nr }</textarea>
							</td>
						</tr>
					</table>
					<div class="bcan">
						<a href="#" id="update">
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