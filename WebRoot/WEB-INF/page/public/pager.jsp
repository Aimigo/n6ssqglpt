<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- 数据分页开始 -->
<script type="text/javascript">
function gotopage(){
	var gop=_$pform['gotoPage'].value;
	if(isNaN(gop)||gop.indexOf("\.")!=-1){
		alert("请输入数字");
		return;
	}
	_$pform['_page.curPage'].value=gop;
	_$pform.submit();
}
</script>

<c:set var="page" value="${_page}" scope="request"></c:set>

<form action="${pageUrl}" method="post" name="_$pform">
	
	<c:forEach items="${page.params}" var="d">
		<input name="${d.key}" value="${d.value}" type="hidden" />
		<input name="_page.params.${d.key}" value="${d.value}" type="hidden"/>
	</c:forEach>

	<!-- 每页数据条数 -->
	<input value="${page.pageRows}" name="_page.pageRows" type="hidden" />

	<!-- 当前为第几页 -->
	<input value="${page.curPage}" name="_page.curPage" type="hidden" />
	
	<!--分页  开始-->
	<div class="page">
		<div class="page-left">
			共有&nbsp;<b>${page.totalRows }</b>&nbsp;条数据&nbsp;<b>${page.totalPages}</b>&nbsp;页&nbsp;当前第&nbsp;<b>${page.curPage }</b>&nbsp;页
		</div>
		<div class="page-right" style="width:530px">
			<a href="javascript:_$pform['_page.curPage'].value=${page.firstPage},_$pform.submit();">首页</a>
			<a href="javascript:_$pform['_page.curPage'].value=${page.previousPage},_$pform.submit();">上一页</a>
			<!-- 页面按钮  -->
			<c:forEach var="i" begin="${page.beginPageIndex }" end="${page.endPageIndex }" step="1">
				<a class='<c:if test="${i eq page.curPage}">nowpage</c:if><c:if test="${i ne page.curPage}">numpage</c:if>' href="javascript:_$pform['_page.curPage'].value=${i},_$pform.submit();">${i}</a>
			</c:forEach>
			<a href="javascript:_$pform['_page.curPage'].value=${page.nextPage},_$pform.submit();">下一页</a>
			<a href="javascript:_$pform['_page.curPage'].value=${page.lastPage},_$pform.submit();">尾页</a>
			&nbsp;&nbsp;跳转到第<input type="text" style="width:25px;" value="${page.curPage }" name="gotoPage"/>页
			<a href="javascript:gotopage();">GO>></a>
		</div>
		<div class="clearit"></div>
	</div>
	<!--分页  结束-->
	
</form>
<!-- 数据分页结束-->