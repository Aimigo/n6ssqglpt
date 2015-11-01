<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<script type="text/javascript">

</script>
</head>

<body onload="MM_preloadImages('fun/system/images/nchjbxx-nav01-.png')">
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>

	<!--主体内容  开始-->
	<div class="nchjbxx-index-mainbg">
		<div class="nchjbxx-index-main box">
			<c:forEach items='${sessionScope.functionMap["0"] }' varStatus="i" var="function">
			<div class='<c:if test="${i.count%3 eq 1}">nchjbxx-index-right</c:if><c:if test="${i.count%3 eq 2}">nchjbxx-index-middle</c:if><c:if test="${i.count%3 eq 0}">nchjbxx-index-left</c:if>'>
				<div class="banner${6-i.index}"></div>
				<dl class="nchjbxx-index-dl">
					<c:choose>
						<c:when test="${sessionScope.functionMap[function.code][0].url eq null}">
							<!-- 如果二级没有地址，找其下三级地址 -->
							<c:set var="url" value="${sessionScope.functionMap[sessionScope.functionMap[function.code][0].code][0].url}?functioncode=${function.code}&functioncode2=${sessionScope.functionMap[function.code][0].code}&functioncode3=${sessionScope.functionMap[sessionScope.functionMap[function.code][0].code][0].code}"/>
						</c:when>
						<c:otherwise>
							<c:set var="url" value="${sessionScope.functionMap[function.code][0].url}?functioncode=${function.code}&functioncode2=${sessionScope.functionMap[function.code][0].code}"/>
						</c:otherwise>
					</c:choose>
					<dt><a href="${url }">${function.name}</a></dt>
					<c:forEach items="${sessionScope.functionMap[function.code] }" var="fun">
						<c:choose>
							<c:when test="${fun.url eq null}">
								<!-- 如果二级没有地址，找其下三级地址 -->
								<c:set var="funurl" value="${sessionScope.functionMap[fun.code][0].url}?functioncode=${fun.fcode}&functioncode2=${fun.code}&functioncode3=${sessionScope.functionMap[fun.code][0].code}"/>
							</c:when>
							<c:otherwise>
								<c:set var="funurl" value="${fun.url}?functioncode=${fun.fcode}&functioncode2=${fun.code }"/>
							</c:otherwise>
						</c:choose>
						<dd>
							<span><a href="${funurl }">${fun.name }</a></span>
						</dd>
					</c:forEach>
				</dl>
			</div>
			</c:forEach>
		</div>
	</div>
	<!--主体内容 结束-->

	<jsp:include page="/WEB-INF/page/system/index/foot.jsp"></jsp:include>
</body>
</html>
