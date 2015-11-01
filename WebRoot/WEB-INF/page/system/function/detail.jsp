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
<script type="text/javascript" src="fun/javascript/treejs.js"></script>
<script type="text/javascript">
	
</script>
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;系统管理&nbsp;&gt;&gt;&nbsp;功能管理&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<input type="hidden" name="operationList" value='<c:forEach items="${operationList }" var ="oper" varStatus="i"><c:choose><c:when test="${i.count eq 1}">${oper.name }</c:when><c:otherwise>,${oper.name }</c:otherwise></c:choose></c:forEach>' />
			   	<input type="hidden" name="id" value="${tblfunction.id}"/>
			   	<input type="hidden" name="code" value="${tblfunction.code}"/>
			   	<input type="hidden" name="oldname" class="input" maxlength="10" value="${tblfunction.name }"/>
				<div class="biaodan-all" style="height:800px;">
					<div class="biaodan-title">功能管理</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">功能信息</td>
						</tr>
						<tr>
							<td>功能名称（<font class="fred">*</font>）</td>
							<td colspan="3">
								${tblfunction.name }
							</td>
						</tr>
						<tr>
							<td>功能图标</td>
							<td>
								<img src='picture/${tblfunction.funicon }' id="_img0" width="100px" height="100px" onerror="javascript:this.src='fun/images/none.jpg';"/>
								<!-- <button class="button02" onclick="openfile(0)" type="button">
									<img src="fun/system/images/icon-add.png" />选择图片
								</button> -->
					 			<input type="hidden" name="imgpath" id="imgpath" class="input" />
								<input type="hidden" id="funicon0" name="funicon" value="${tblfunction.funicon }" />
							</td>
							<td>功能图标</td>
							<td>
								<img src='picture/${tblfunction.funicon2 }' id="_img1" width="100px" height="100px" onerror="javascript:this.src='fun/images/none.jpg';"/>
								<!-- <button class="button02" onclick="openfile(1)" type="button">
									<img src="fun/system/images/icon-add.png" />选择图片
								</button> -->
								<input type="hidden" id="funicon1" name="funicon2" value="${tblfunction.funicon2 }" />
								
								<!-- <iframe src="upLoadAction.action" style="display:none" id="_img_iframe" scrolling="no" frameborder="0"></iframe> -->
							</td>
						</tr>
						<tr>
							<td>父功能名称</td>
							<td>
								${ffunction.name }
								<input type="hidden" name="fcode" id="fcode" readonly="readonly" value="${ffunction.code }"/>
							</td>
							<td>所属项目</td>
							<td>
								${tblproject.name }
								<input type="hidden" name="projectcode" id="projectcode" readonly="readonly" value="${tblproject.code }"/>
							</td>
						</tr>
						<c:if test="${fn:length(tblfunction.url)> 0}">
						<tr id="isNoParent">
							<td>功能URL（<font class="fred">*</font>）</td>
							<td>
								${tblfunction.url }
							</td>
							<td>功能操作（<font class="fred">*</font>）</td>
							<td>
								<c:forEach items="${operationList }" var ="oper" varStatus="i">
									<c:choose>
										<c:when test="${i.count eq 1}">${oper.operationname }</c:when>
										<c:otherwise> ${oper.operationname }</c:otherwise>
									</c:choose>
								</c:forEach>
							</td>
						</tr>
						</c:if>
						<tr>
							<td>功能描述：</td>
							<td colspan="3">
								${tblfunction.ms }
							</td>
						</tr>
					</table>
					<div class="bcan">
						<a href="javascript:history.go(-1);">
							<img src="fun/system/images/fh.gif">
						</a>
					</div>
				</div>
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