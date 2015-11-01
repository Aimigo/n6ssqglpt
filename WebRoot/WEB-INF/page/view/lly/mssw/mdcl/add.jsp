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
<script type="text/javascript">
	$(function(){
		$("#save").click(function(){
			var form = document.saveform;
			Validator.Validate(form,3);
		});
		
		$("input[name='isjj']").click(function(){
			var clyj = '<td valign="top">处理意见（<font class="fred">*</font>）</td>'
					  +'<td colspan="3">'
					  +'	<textarea name="clyj" id="clyj" type="text" style="width:98%;" rows="15"'
					  +'		dataType="Require" msg="处理意见不能为空！" require="true">${mdcl.clyj }</textarea>'
					  +'</td>';
	   		if("0"==this.value){
	   			$("#zt").val(0);
	   			$("#showsome").html('');
	   		}else if("1"==this.value){
	   			$("#zt").val(4);
	   			$("#showsome").html(clyj);
	   		}else if("-1"==this.value){
	   			$("#zt").val(-1);
	   			$("#showsome").html('');
	   		}
	   	});
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;民生事务&nbsp;&gt;&gt;&nbsp;矛盾处理&nbsp;&gt;&gt;&nbsp;添加</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="mdcl_save.action" method="post" name="saveform">
				<div class="biaodan-all">
					<div class="biaodan-title">矛盾处理信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">矛盾信息</td>
						</tr>
						<tr>
							<td>标题（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="bt" type="text" class="qbg" maxlength="50" 
									dataType="Require" msg="标题不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>所属分类（<font class="fred">*</font>）</td>
							<td>
								<select name="flid" class="bd-r" dataType="Require" msg="请选择所属分类！" require="true">
									<option value="">请选择</option>
									<c:forEach items="${mdfllist }" var="mdfl">
									<option value="${mdfl.id }">${mdfl.name }</option>
									</c:forEach>
								</select>
							</td>
							<td>解决方式（<font class="fred">*</font>）</td>
							<td>
								<select name="jcfs" class="bd-r" dataType="Require" msg="请选择解决方式！" require="true">
									<option value="">请选择</option>
									<option value="民事手段">民事手段</option>
									<option value="司法手段">司法手段</option>
									<option value="行政手段">行政手段</option>
								</select>
							</td>
						</tr>
						<tr>
							<td valign="top">内容（<font class="fred">*</font>）</td>
							<td colspan="3">
								<textarea name="nr" id="nr" type="text" style="width:98%;" rows="15"
									dataType="Require" msg="内容不能为空！" require="true"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">问题处理</td>
						</tr>
						<tr>
							<td>是否解决（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input type="radio" name="isjj" value="-1" id="isjj_zb" checked="checked"/>&nbsp;<label for="isjj_zb">暂不解决</label>
								<input type="radio" name="isjj" value="1" id="isjj_yes"/>&nbsp;<label for="isjj_yes">解决问题</label>
								<input type="radio" name="isjj" value="0" id="isjj_no"/>&nbsp;<label for="isjj_no">上报问题</label>
								<input type="hidden" name="zt" id="zt" value="-1"/>
							</td>
						</tr>
						<tr id="showsome">
							
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