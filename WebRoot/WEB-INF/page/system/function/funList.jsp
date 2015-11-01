<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table class="zhwxx-tab">
	<colgroup>
		<col style="width: 5%;" />
		<col style="" />
		<col style="width: 20%;" />
		<col style="width: 25%;" />
		<col style="width: 15%;" />
	</colgroup>
	<thead>
		<tr>
			<th>
				<input type="checkbox" name="checkAll" id="checkAll"
					onclick="checkAll(this)" />
			</th>
			<th>
				项目名称
			</th>
			<th>
				项目URL
			</th>
			<th>
				项目操作
			</th>
			<th>
				操作
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${_page.data }" var="function" varStatus="i">
			<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
				<td>
					<input type="checkbox" name="check" value="${function.id }" />
				</td>
				<td>
					${function.name }
				</td>
				<td>
					${function.url }
				</td>
				<td>
					<c:forEach items="${operationMap[function.code] }" var="operation">
											        	${operation.operationname }
											        	</c:forEach>
				</td>
				<td>
					<%--<c:if test='${fn:contains(operationlist,"查看") }'>--%>
					<a href="javascript:showDetail(${function.id});">查看详情</a>&nbsp;
					<%--</c:if>--%>
					<%--<c:if test='${fn:contains(operationlist,"删除") }'>--%>
					<a href="javascript:deleteIt(${function.id});">删除</a>&nbsp;
					<%--</c:if>--%>
				</td>
			</tr>
		</c:forEach>
		<c:forEach begin="${fn:length(_page.data)}" end="${_page.pageRows-1}"
			var="i">
			<tr <c:if test="${i%2 != 0 }">class="ancolorbg"</c:if>>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!--表格内容  结束-->
<jsp:include page="/WEB-INF/page/public/pager.jsp"></jsp:include>