<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">
	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}
	function MM_preloadImages() { //v3.0
		var d = document;
		if (d.images) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
</script>
<!--title 开始-->
<div class="nchjbxx-titlebg">
	<div class="nchjbxx-titlebgleft">
		<!--导航菜单 开始-->
		<div class="nchjbxx-nav" style="width:${(fn:length(sessionScope.functionMap["0"])+1)*96.42}px;">
			<div class="head-navbg">
				<ul class="head-nav">
					<li class="selectedfirst">
						<a href="login_enter.action" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image0','','fun/system/images/nchjbxx-nav01<c:if test="${sessionScope.function_one ne null}">-</c:if>.png',1)">
							<img src="fun/system/images/nchjbxx-nav01<c:if test="${sessionScope.function_one eq null}">-</c:if>.png" id="Image0">
						</a>
					</li>
					<c:forEach items='${sessionScope.functionMap["0"] }' var="function" varStatus="i">
						<c:choose>
							<c:when test="${sessionScope.functionMap[function.code][0].url eq null}">
								<!-- 如果二级没有地址，找其下三级地址 -->
								<c:set var="url" value="${sessionScope.functionMap[sessionScope.functionMap[function.code][0].code][0].url}?functioncode=${function.code}&functioncode2=${sessionScope.functionMap[function.code][0].code}&functioncode3=${sessionScope.functionMap[sessionScope.functionMap[function.code][0].code][0].code}"/>
							</c:when>
							<c:otherwise>
								<c:set var="url" value="${sessionScope.functionMap[function.code][0].url}?functioncode=${function.code}&functioncode2=${sessionScope.functionMap[function.code][0].code}"/>
							</c:otherwise>
						</c:choose>
						<li class="noselected">
							<a href="${url }" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image${i.count }','','picture/<c:choose><c:when test="${sessionScope.function_one.code ne function.code }">${function.funicon2 }</c:when><c:otherwise>${function.funicon }</c:otherwise></c:choose>',1)">
								<img src="picture/<c:choose><c:when test="${sessionScope.function_one.code eq function.code }">${function.funicon2 }</c:when><c:otherwise>${function.funicon }</c:otherwise></c:choose>" id="Image${i.count }" />
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!--导航菜单 结束-->
	</div>
</div>
