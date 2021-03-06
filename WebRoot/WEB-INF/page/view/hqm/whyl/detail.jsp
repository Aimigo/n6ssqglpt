<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="fun/showDialog/showDialog.css"
	type="text/css" />
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/javascript/treejs.js"></script>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fun/njb/Validator.js"></script>
<script type="text/javascript">
$(function() {
	showCont();
	});
function showCont() {
	var selectedvalue = '${whyl.ly}';
	switch (selectedvalue) {
	case "来自网络":
		$("#lzwl").show();
		$("#spbf").hide();
		$("#ms").show();
		break;
	case "拷贝文件":
		$("#lzwl").hide();
		$("#spbf").show();
		$("#ms").hide();
		break;
	case "系统上传":
		$("#lzwl").hide();
		$("#spbf").show();
		$("#ms").hide();
		break;
	default:
		break;
	}
}
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;数码文化娱乐信息系统&nbsp;&gt;&gt;&nbsp;数码文化娱乐资源&nbsp;&gt;&gt;&nbsp;数码文化娱乐管理&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--表单内容  开始-->
				<form action="whyl_update.action" method="post" name="saveform">
					<div class="biaodan-all" style="height:100%;">
						<div class="biaodan-title">数码文化娱乐资源</div>
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<colgroup>
								<col style="width:15%;" />
								<col style="width:35%;" />
								<col style="width:10%;" />
								<col style="width:40%;" />
							</colgroup>
							<tr>
								<td colspan="4" class="title2">文化娱乐资源管理</td>
							</tr>
							<tr>
								<td>标题</td>
								<td colspan="3">${whyl.bt}</td>
							</tr>
							<tr>
								<td>文化娱乐分类</td>
								<td colspan="3">${whyl.flname}</td>
							</tr>
							<tr id="spbf">
								<td>视频预览</td>
								<td colspan="3">
									<iframe src="uploadfile_videoplayer.action?w=600&h=400&vs=<%=basePath %>${whyl.dz}&is=<%=basePath %>${whyl.tp}&ms=${whyl.ms}&p=0&wh=16:9"
		style="width:600px; height: 400px;border: 0px" id="_img_iframe" scrolling="no" ></iframe>
								</td>

							</tr>
							<tr id="lzwl">
								<td>点击跳转</td>
								<td colspan="3">
								<a href="${whyl.dz}" target="_black">${whyl.dz}</a>
									</td>

							</tr>
							<tr id="ms">
								<td>描述</td>
								<td colspan="3">${whyl.ms}</td>
							</tr>
							<tr>
								<td>上传用户</td>
								<td>${whyl.username}</td>
								<td>上传时间</td>
								<td><fmt:formatDate value="${whyl.scsj}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
						</table>
						<div class="bcan">
							<a href="javascript:history.go(-1);"> <img
								src="fun/system/images/fh.gif" /> </a>
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