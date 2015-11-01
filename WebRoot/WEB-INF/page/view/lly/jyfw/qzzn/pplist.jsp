<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<c:if test="${type eq '1' }">
	<c:if test="${fn:length(grjls) <= 0 }">没有匹配的人员</c:if>
	<c:if test="${fn:length(grjls) > 0 }">
	<!-- MAP -->
	<table style="width:98%;" class="zhwxx-tab">
		<colgroup>
			<col style="width:10%;"/>
			<col style=""/>
			<col style="width:10%;"/>
			<col style="width:10%;"/>
			<col style="width:10%;"/>
			<col style="width:10%;"/>
			<col style="width:10%;"/>
			<col style="width:10%;"/>
		</colgroup>
		<thead>
			<tr>
				<th>登记表编号</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>择业地区</th>
				<th>学历</th>
				<th>联系电话</th>
				<th>有效期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${grjls}" var="grjl" varStatus="j">
				<tr <c:if test="${j.count%2 == 0 }">class="ancolorbg"</c:if>>
					<td>${grjl.djbbh}</td>
					<td>${grjl.xm}</td>
					<td>${grjl.sfzh}</td>
					<td>${grjl.zydqname}</td>
					<td>
						<c:choose>
							<c:when test="${grjl.whcd eq '00'}">文盲</c:when>
							<c:when test="${grjl.whcd eq '01'}">半文盲</c:when>
							<c:when test="${grjl.whcd eq '02'}">小学</c:when>
							<c:when test="${grjl.whcd eq '03'}">初中</c:when>
							<c:when test="${grjl.whcd eq '04'}">高中</c:when>
							<c:when test="${grjl.whcd eq '05'}">技工学校</c:when>
							<c:when test="${grjl.whcd eq '06'}">中技</c:when>
							<c:when test="${grjl.whcd eq '07'}">中专</c:when>
							<c:when test="${grjl.whcd eq '08'}">大专</c:when>
							<c:when test="${grjl.whcd eq '09'}">本科</c:when>
							<c:when test="${grjl.whcd eq '10'}">硕士</c:when>
							<c:when test="${grjl.whcd eq '11'}">博士</c:when>
						</c:choose>
					</td>
					<td>${grjl.lldh}</td>
					<td><fmt:formatDate value="${grjl.yxq }" pattern="yyyy-MM-dd" /> </td>
					<td>
						<a href="javascript:location.href='grjl_detail.action?id=${grjl.id}';">查看详情</a>&nbsp;
						<a href="javascript:sendMessage('${grjl.id }','${id }',2);">发送短信</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- MAP -->
	</c:if>
</c:if>

<c:if test="${type eq '2' }">
	<c:if test="${fn:length(qyzps) <= 0 }">没有匹配的企业</c:if>
	<c:if test="${fn:length(qyzps) > 0 }">
	<!-- MAP -->
	<table style="width:98%;" class="zhwxx-tab">
		<colgroup>
			<col style=""/>
			<col style="width:15%;"/>
			<col style="width:15%;"/>
			<col style="width:10%;"/>
			<col style="width:10%;"/>
			<col style="width:10%;"/>
			<col style="width:10%;"/>
		</colgroup>
		<thead>
			<tr>
				<th>企业名称</th>
				<th>招聘职位</th>
				<th>职位类别</th>
				<th>工作地点</th>
				<th>最低学历</th>
				<th>发布日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${qyzps}" var="qyzp" varStatus="j">
				<tr <c:if test="${j.count%2 == 0 }">class="ancolorbg"</c:if>>
					<td>${qyzp.qymc}</td>
					<td>${qyzp.zpzw}</td>
					<td>${qyzp.zwlbname}</td>
					<td>${qyzp.gzddname}</td>
					<td>
						<c:choose>
							<c:when test="${qyzp.zdxl eq '00'}">文盲</c:when>
							<c:when test="${qyzp.zdxl eq '01'}">半文盲</c:when>
							<c:when test="${qyzp.zdxl eq '02'}">小学</c:when>
							<c:when test="${qyzp.zdxl eq '03'}">初中</c:when>
							<c:when test="${qyzp.zdxl eq '04'}">高中</c:when>
							<c:when test="${qyzp.zdxl eq '05'}">技工学校</c:when>
							<c:when test="${qyzp.zdxl eq '06'}">中技</c:when>
							<c:when test="${qyzp.zdxl eq '07'}">中专</c:when>
							<c:when test="${qyzp.zdxl eq '08'}">大专</c:when>
							<c:when test="${qyzp.zdxl eq '09'}">本科</c:when>
							<c:when test="${qyzp.zdxl eq '10'}">硕士</c:when>
							<c:when test="${qyzp.zdxl eq '11'}">博士</c:when>
						</c:choose>
					</td>
					<td><fmt:formatDate value="${qyzp.fbrq}" pattern="yyyy-MM-dd"/></td>
					<td>
						<a href="javascript:location.href='qyzp_detail.action?id=${qyzp.id}';">查看详情</a>&nbsp;
						<a href="javascript:sendMessage('${id }','${qyzp.id }',2);">发送短信</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- MAP -->
	</c:if>
</c:if>
