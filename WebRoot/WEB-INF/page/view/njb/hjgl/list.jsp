<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
var baseUrl="hjgl_";
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;户籍管理</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<form action="" method="post" id="seach">
						<div class="chaxun-form">
							<b>查询:</b> <span> <label>户口薄号</label> <input name="_page.params.num"
								type="text" value="${_page.params.num }" class="bd-r" /> </span>
								<span> <label>户主姓名</label> <input name="_page.params.name"
								type="text" value="${_page.params.name }" class="bd-r" /> </span> <span>
								<button class="button02" type="submit">
									<img src="fun/system/images/icon-search.png" />查询
								</button> </span>
							<c:if test='${fn:contains(operationlist,"增加") }'>
							<span>
								<button class="button02" onclick="operator('a')" type="button">
									<img src="fun/system/images/icon-add.png" />添加
								</button> </span>
							</c:if>
							<c:if test='${fn:contains(operationlist,"删除") }'>
							<span>
								<button class="button02" onClick="operator('d')" type="button">
									<img src="fun/system/images/icon-del.png" />删除
								</button> </span>
							</c:if>
						</div>
					</form>
					<!--查询  结束-->

					<!--表格内容  开始-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:5%;" />
								<col style="" />
								<col style="width:15%;" />
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="checkAll" id="checkAll"
										onclick="checkAll(this)" /></th>
									<th>户口薄号</th>
									<th>户主姓名</th>
									<th>电话</th>
									<th>手机</th>
									<th>身份证</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${_page.data }" var="model" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
										<td><input name="check" type="checkbox"
											value="${model.id }" /></td>
										<td>${model.hkbh}</td>
										<td>${model.hzname}</td>
										<td>${model.dh}</td>
										<td>${model.sj}</td>
										<td>${model.sfz}</td>
										<td>
											<c:if test='${fn:contains(operationlist,"查看") }'> <a
											href="javascript:operator('x','${model.id}');">查看详情</a>&nbsp; </c:if>
											<c:if test='${fn:contains(operationlist,"修改") }'> <a
											href="javascript:operator('e','${model.id}');">编辑</a>&nbsp; </c:if>
											<c:if test='${fn:contains(operationlist,"删除") }'><a
											href="javascript:operator('d','${model.id}');">删除</a>&nbsp; </c:if>
										</td>
									</tr>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)}"
									end="${_page.pageRows-1}" var="i">
									<tr <c:if test="${i%2 != 0 }">class="ancolorbg"</c:if>>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td><td>&nbsp;</td>
										<td>&nbsp;</td><td>&nbsp;</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!--表格内容  结束-->

					<jsp:include page="/WEB-INF/page/public/pager.jsp"></jsp:include>
				</div>
				<!--选项卡下方边框  结束-->
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