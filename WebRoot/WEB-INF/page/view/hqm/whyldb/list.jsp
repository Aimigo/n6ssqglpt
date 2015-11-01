<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/njb/operator.js"></script>
<script type="text/javascript">
	var baseUrl = "whyldb_";
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;数码文化娱乐信息系统&nbsp;&gt;&gt;&nbsp;数码文化娱乐点播</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--查询  开始-->
				<form action="" method="post" id="seach">
					<div class="chaxun-form">
						<b>查询:</b> <span><label>标题:</label> <input
							name="_page.params.bt" type="text" value="${_page.params.bt}"
							class="bd-r" /> </span> <span><label>分类信息:</label><input
							name="_page.params.flname" type="text"
							value="${_page.params.flname}" class="bd-r" /> </span> <span><label>来源:</label><input
							name="_page.params.ly" type="text" value="${_page.params.ly}"
							class="bd-r" /> </span> <span><label>发布人:</label><input
							name="_page.params.username" type="text"
							value="${_page.params.username}" class="bd-r" /> </span> <span><button
								class="button02" type="submit">
								<img src="fun/system/images/icon-search.png" />查询
							</button> </span>
					</div>
				</form>
				<!--查询  结束-->

				<!--视频内容  开始-->
				<div class="pxkchvideo-border">
					<ul class="box">
						<c:forEach items="${_page.data}" var="model" varStatus="i">
							<li class="TRol-left">
								<div class="TRoll-pic">
									<a
										href="<c:if test="${model.ly=='来自网络'}">${model.dz}</c:if><c:if test="${model.ly!='来自网络'}">whyldb_detail.action?id=${model.id}</c:if>"
										target="_blank"> <img src="${model.tp}" /> </a>
								</div>
								<div class="half-opw3"></div>
								<div class="TRoll-icon"></div>
								<div class="TRoll-time"></div>
								<div class="TRoll-word">
									<a
										href="<c:if test="${model.ly=='来自网络'}">${model.dz}</c:if><c:if test="${model.ly!='来自网络'}">whyldb_detail.action?id=${model.id}</c:if>"
										target="_blank">${model.bt}</a>
								</div>
								<div class="TRoll-source">
									<a
										href="<c:if test="${model.ly=='来自网络'}">${model.dz}</c:if><c:if test="${model.ly!='来自网络'}">whyldb_detail.action?id=${model.id}</c:if>"
										target="_blank"> <c:if test="${model.ly!='来自网络'}">
											<c:set var='tem' value='<%=basePath%>' />
											<c:set var='temp' value='${tem}${model.dz}' />
										</c:if> <c:if test="${model.ly=='来自网络'}">
											<c:set var='temp' value='${model.dz}' />
										</c:if> ${fn:length(temp) > 34 ? fn:substring(temp,0,34) :
										temp}${fn:length(temp) > 34 ? '...' : ''} </a>
								</div>
								<div class="TRoll-source">
									<span>分类信息：[${model.flname}]</span>
								</div>
								<div class="TRoll-source">
									<span>来源：${model.ly}</span>
								</div>
								<div class="TRoll-date">
									<span>发布人:${model.username}</span>
								</div>
								<div class="TRoll-date">
									<font class="today"><fmt:formatDate
											value="${model.scsj}" pattern="yy/MM/dd HH:mm:ss" />
									</font>
								</div></li>
						</c:forEach>
					</ul>
				</div>
				<!--视频内容  结束-->

				<jsp:include page="/WEB-INF/page/public/pager.jsp"></jsp:include>
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