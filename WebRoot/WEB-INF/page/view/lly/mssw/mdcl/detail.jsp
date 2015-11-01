<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link href="fun/system/css/nchjbxx.css" type="text/css"
	rel="stylesheet">
<link rel="stylesheet"
	href="fun/step-jquery-dc/css/step-dc-style1.css" />
<script type="text/javascript"
	src="fun/step-jquery-dc/step-jquery-dc.js"></script>
<script type="text/javascript"
	src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript">

</script>

		<body
			onload="MM_preloadImages('fun/system/images/nchjbxx-nav01-.png')">
			<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>

			<!--主体内容  开始-->
			<div class="nchjbxx-mainbg box">

				<!--right主体内容  开始-->
				<div class="nchjbxx-mainbgright">
					<div class="nchjbxx-main">

						<!--当前位置  开始-->
						<div class="sitebg">
							<div class="site">
								<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;民生事务&nbsp;&gt;&gt;&nbsp;矛盾处理&nbsp;&gt;&gt;&nbsp;详细</b>
							</div>
						</div>
						<!--当前位置  结束-->

						<!--表单内容  开始-->
						<div class="biaodan-all">
							<div class="biaodan-title">矛盾处理信息</div>
							<div class="step_context test"></div>
							<script>
							//所有步骤的数据
							var stepListJson;
							//当前进行到第几步
							var currentStep;
						    <c:choose>
								<c:when test="${mdcl.zt eq '4'}">
									<c:choose>
										<c:when test="${mdcl.ldname ne null and mdcl.ldname ne '' }">
											stepListJson=[{StepNum:1,StepText:"矛盾采集"},
												    {StepNum:2,StepText:"网格管理员处理"},
												    {StepNum:3,StepText:"职能部门处理"},
												    {StepNum:4,StepText:"分管领导处理"},
												    {StepNum:5,StepText:"团场领导处理"},
												    {StepNum:6,StepText:"矛盾已解决"}];
											currentStep = 6;
										</c:when>
										<c:when test="${mdcl.bmldname ne null and mdcl.bmldname ne '' }">
											stepListJson=[{StepNum:1,StepText:"矛盾采集"},
												    {StepNum:2,StepText:"网格管理员处理"},
												    {StepNum:3,StepText:"职能部门处理"},
												    {StepNum:4,StepText:"分管领导处理"},
												    {StepNum:5,StepText:"矛盾已解决"}];
											currentStep = 5;
										</c:when>
										<c:when test="${mdcl.dkbmryname ne null and mdcl.dkbmryname ne '' }">
											stepListJson=[{StepNum:1,StepText:"矛盾采集"},
												    {StepNum:2,StepText:"网格管理员处理"},
												    {StepNum:3,StepText:"职能部门处理"},
												    {StepNum:4,StepText:"矛盾已解决"}];
											currentStep = 4;
										</c:when>
										<c:when test="${mdcl.glyname ne null and mdcl.glyname ne '' }">
											stepListJson=[{StepNum:1,StepText:"矛盾采集"},
												    {StepNum:2,StepText:"网格管理员处理"},
												    {StepNum:3,StepText:"矛盾已解决"}];
											currentStep = 3;
										</c:when>
										<c:otherwise>
											stepListJson=[{StepNum:1,StepText:"矛盾采集"},
												    {StepNum:2,StepText:"矛盾已解决"}];
											currentStep = 2;
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									stepListJson = [{StepNum:1,StepText:"矛盾采集"},
										    {StepNum:2,StepText:"网格管理员处理"},
										    {StepNum:3,StepText:"职能部门处理"},
										    {StepNum:4,StepText:"分管领导处理"},
										    {StepNum:5,StepText:"团场领导处理"},
										    {StepNum:6,StepText:"矛盾已解决"}];
									currentStep = ${mdcl.zt + 2};
								</c:otherwise>
							</c:choose>
							
							//new一个工具类
							var StepTool = new Step_Tool_dc("test","mycall");
							//使用工具对页面绘制相关流程步骤图形显示
							StepTool.drawStep(currentStep,stepListJson);
							//回调函数
							function mycall(restult){
								//	alert("mycall"+result.value+":"+result.text);
								StepTool.drawStep(result.value,stepListJson);
								//TODO...这里可以填充点击步骤的后加载相对应数据的代码
							}
							</script>
							
							<table class="biaodan" style="" cellpadding="0" cellspacing="0">
								<colgroup>
									<col style="width:10%;" />
									<col style="width:40%;" />
									<col style="width:10%;" />
									<col style="width:40%;" />
								</colgroup>
								<tr>
									<td colspan="4" class="title2">矛盾信息</td>
								</tr>
								<tr>
									<td>标题（<font class="fred">*</font>）</td>
									<td colspan="3">${mdcl.bt }</td>
								</tr>
								<tr>
									<td>所属分类（<font class="fred">*</font>）</td>
									<td>${mdcl.flname }</td>
									<td>解决方式（<font class="fred">*</font>）</td>
									<td>${mdcl.jcfs }</td>
								</tr>
								<tr>
									<td valign="top">内容（<font class="fred">*</font>）</td>
									<td colspan="3">${mdcl.nr }</td>
								</tr>
								<tr>
									<td>信息采集员（<font class="fred">*</font>）</td>
									<td>${mdcl.cjrname }</td>
									<td>填写时间（<font class="fred">*</font>）</td>
									<td><fmt:formatDate value="${mdcl.txsj }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
								<c:if test="${mdcl.glyname eq null or mdcl.glyname eq ''}">
									<tr>
										<td valign="top">所属区域（<font class="fred">*</font>）</td>
										<td colspan="3">${mdcl.wg }</td>
									</tr>
								</c:if>
								<c:if test="${mdcl.glyname ne null and mdcl.glyname ne ''}">
									<tr>
										<td valign="top">网格管理员（<font class="fred">*</font>）</td>
										<td>${mdcl.glyname }</td>
										<td valign="top">所属区域（<font class="fred">*</font>）</td>
										<td>${mdcl.wg }</td>
									</tr>
								</c:if>
								<c:if test="${mdcl.dkbmryname eq null or mdcl.dkbmryname eq ''}">
									<c:if test="${mdcl.dkbmname ne null and mdcl.dkbmname ne ''}">
										<tr>
											<td valign="top">部门名称（<font class="fred">*</font>）</td>
											<td colspan="3">${mdcl.dkbmname }</td>
										</tr>
									</c:if>
								</c:if>
								<c:if
									test="${mdcl.dkbmryname ne null and mdcl.dkbmryname ne ''}">
									<tr>
										<td valign="top">职能部门（<font class="fred">*</font>）</td>
										<td>${mdcl.dkbmryname }</td>
										<td valign="top">部门名称（<font class="fred">*</font>）</td>
										<td>${mdcl.dkbmname }</td>
									</tr>
								</c:if>
								<c:if test="${mdcl.bmldname ne null and mdcl.bmldname ne ''}">
									<tr>
										<td valign="top">分管领导（<font class="fred">*</font>）</td>
										<td colspan="3">${mdcl.bmldname }</td>
									</tr>
								</c:if>
								<c:if test="${mdcl.ldname ne null and mdcl.ldname ne ''}">
									<tr>
										<td valign="top">领导（<font class="fred">*</font>）</td>
										<td colspan="3">${mdcl.ldname }</td>
									</tr>
								</c:if>
								<tr>
									<td valign="top">处理状态（<font class="fred">*</font>）</td>
									<td colspan="3"><c:choose>
											<c:when test="${mdcl.zt eq -1}">未解决</c:when>
											<c:when test="${mdcl.zt eq 0}">信息采集员上报未解决</c:when>
											<c:when test="${mdcl.zt eq 1}">管理员上报未解决</c:when>
											<c:when test="${mdcl.zt eq 2}">职能部门上报未解决</c:when>
											<c:when test="${mdcl.zt eq 3}">分管领导上报未解决</c:when>
											<c:when test="${mdcl.zt eq 4}">已解决</c:when>
										</c:choose></td>
								</tr>
								<c:if test="${mdcl.zt eq 4 }">
									<tr>
										<td colspan="4" class="title2">问题处理</td>
									</tr>
									<tr>
										<td valign="top">处理意见（<font class="fred">*</font>）</td>
										<td colspan="3">${mdcl.clyj }</td>
									</tr>
									<tr>
										<td valign="top">处理时间（<font class="fred">*</font>）</td>
										<td colspan="3"><fmt:formatDate value="${mdcl.clsj }"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
								</c:if>
							</table>
							<div class="bcan">
								<a href="javascript:history.go(-1);"> <img
									src="fun/system/images/fh.gif">
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