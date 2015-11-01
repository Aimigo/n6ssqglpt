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
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	function detail(id) {
		window.location.href = "grjl_detail.action?id=" + id;
	}

	/*复选框的全选和取消全选*/
	function checkAll(o) {
		var list = document.getElementsByName("check");
		for ( var i = 0; i < list.length; i++) {
			list[i].checked = o.checked;
		}
	}

	/*删除功能实现*/
	function deleteIt(id) {
		if (confirm("您确定要删除吗？")) {
			$.ajax({
				type : "post",
				url : "grjl_delete.action",
				data : {
					"id" : id
				},
				dataType : "html",
				success : function(data) {
					window.location.href = "grjl_list.action";
				}
			});
		}

	}
	/*批量删除功能实现*/
	function deleteAll() {
		var str = "";
		$("[name='check']").each(function() {
			if (this.checked) {
				if (str == "")
					str = $(this).val();
				else
					str += "," + $(this).val();
			}
		});
		if (str == "")
			alert("请选择您要删除的信息！");
		else {
			if (confirm("您确定要删除吗？")) {
				$.ajax({
					type : "post",
					url : "grjl_delete.action",
					data : {
						"id" : str
					},
					dataType : "html",
					success : function(data) {
						window.location.href = "grjl_list.action";
					}
				});
			}
		}
	}
	
	/*展示新增修改窗体*/
	function show(id,type) {
		var url = "grjl_add.action";
		if (id)
			url = "grjl_edit.action?id=" + id;
		if(type)
			url = "grjl_detail.action?id=" + id;
		
		location.href=url;
	}

	$(function() {
		$("tr").click(function() {
			var checked = $(this).find("td").first().find("input");
			checked.attr("checked", !checked.attr("checked"));
		});

		$(":checkbox").click(function(event) {
			event.stopPropagation(); // do something   
		});
		
		$("td a").click(function(event){
			event.stopPropagation(); // do something
		});
	});
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;个人就业信息管理</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<form action="grjl_list.action" method="post">
					<div class="chaxun-form">
						<b>查询:</b> 
						<span>
							<label>登记表编号</label>
							<input name="_page.params.djbbh" type="text" value="${_page.params.djbbh }" class="bd-r" />
						</span>
						<span>
							<label>姓名</label>
							<input name="_page.params.xm" type="text" value="${_page.params.xm }" class="bd-r" />
						</span>  
						<span>
							<label>身份证号</label>
							<input name="_page.params.sfzh" type="text" value="${_page.params.sfzh }" class="bd-r" />
						</span> 
						<span>
							<button class="button02" type="submit">
								<img src="fun/system/images/icon-search.png" />查询
							</button>
						</span>
						<c:if test='${fn:contains(operationlist,"增加") }'>
						<span>
							<button class="button02" onclick="show()" type="button">
								<img src="fun/system/images/icon-add.png" />添加
							</button>
						</span>
						</c:if>
						<c:if test='${fn:contains(operationlist,"删除") }'>
        				<span>
        					<button class="button02" onClick="deleteAll()" type="button">
        						<img src="fun/system/images/icon-del.png" />删除
        					</button>
        				</span>
        				</c:if>
					</div>
					</form>
					<!--查询  结束-->
					
					<!--表格内容  开始-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:5%;"/>
								<col style="width:15%;"/>
								<col style=""/>
								<col style="width:15%;"/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
							</colgroup>
							<thead>
								<tr>
									<th>
										<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll(this)" />
									</th>
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
								<c:set var="currentday">
									<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd" type="date"/>
								</c:set>
								<c:forEach items="${_page.data }" var="obj" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if> style="<c:if test='${obj.yxq < currentday }'>color:gray;</c:if>">
										<td>
											<input name="check" type="checkbox" value="${obj.id }" />
										</td>
										<td>${obj.djbbh}</td>
										<td>${obj.xm}</td>
										<td>${obj.sfzh}</td>
										<td>${obj.zydqname}</td>
										<td>
											<c:choose>
												<c:when test="${obj.whcd eq '00'}">文盲</c:when>
												<c:when test="${obj.whcd eq '01'}">半文盲</c:when>
												<c:when test="${obj.whcd eq '02'}">小学</c:when>
												<c:when test="${obj.whcd eq '03'}">初中</c:when>
												<c:when test="${obj.whcd eq '04'}">高中</c:when>
												<c:when test="${obj.whcd eq '05'}">技工学校</c:when>
												<c:when test="${obj.whcd eq '06'}">中技</c:when>
												<c:when test="${obj.whcd eq '07'}">中专</c:when>
												<c:when test="${obj.whcd eq '08'}">大专</c:when>
												<c:when test="${obj.whcd eq '09'}">本科</c:when>
												<c:when test="${obj.whcd eq '10'}">硕士</c:when>
												<c:when test="${obj.whcd eq '11'}">博士</c:when>
											</c:choose>
										</td>
										<td>${obj.lldh}</td>
										<td><fmt:formatDate value="${obj.yxq }" pattern="yyyy-MM-dd" /> </td>
										<td>
											<c:if test='${fn:contains(operationlist,"查看") }'>
												<a href="javascript:show(${obj.id},true);">查看详情</a>&nbsp;
											</c:if>
											<c:if test='${fn:contains(operationlist,"修改") }'>
												<a href="javascript:show(${obj.id});">编辑</a>&nbsp;
											</c:if>
											<c:if test='${fn:contains(operationlist,"删除") }'>
												<a href="javascript:deleteIt(${obj.id});">删除</a>&nbsp;
											</c:if>
										</td>
									</tr>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)}" end="${_page.pageRows-1}" var="i">
									<tr <c:if test="${i%2 != 0 }">class="ancolorbg"</c:if>>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
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