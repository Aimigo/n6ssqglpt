<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet"/>
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="fun/showDialog/showDialog.css" type="text/css"/>
<link rel="stylesheet" href="fun/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript" src="fun/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript">
	/*复选框的全选和取消全选*/
	function checkAll(o){
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
				url : "role_delete.action",
				data : {
					"id" : id
				},
				dataType : "html",
				success : function(data) {
					window.location.href = "role_list.action";
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
					url : "role_delete.action",
					data : {
						"id" : str
					},
					dataType : "html",
					success : function(data) {
						window.location.href = "role_list.action";
					}
				});
			}
		}
	}
	
	/*展示新增修改窗体*/
	function show(id,type){
		var  url = "role_add.action";
		if(id)
			url = "role_edit.action?id="+id;
		if(type)
			url = "role_detail.action?id="+id;
		
		location.href = url;
	}
	
	$(function(){
		$("tr").click(function(){
			var checked = $(this).find("td").first().find("input");
			checked.attr("checked",!checked.attr("checked"));
		});
		
		$(":checkbox").click(function(event){   
		    event.stopPropagation(); // do something   
		});
		
		$("td a").click(function(event){
			event.stopPropagation(); // do something
		});
		
		/*显示角色树形结构*/
		window.showTree=function(rolecode){
			$("#rolecode").val(rolecode);
			/**
			 * 获取zTree数据源
			 */
			$.ajax({
				type : 'post',
				url : 'role_getTreeFromOperation.action',
				cache : false,
				dataType : 'html',
				success : function(da) {
					showDialog("window",da, "选择功能操作");
				},
				error : function() {
					return false;
				}
			});
		}
	});
</script>
</head>

<body onload="MM_preloadImages('fun/system/images/nchjbxx-nav01-.png')">
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>
	<input type="hidden" value="" name="rolecode" id="rolecode"/>
	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;系统管理&nbsp;&gt;&gt;&nbsp;角色管理</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<form action="role_list.action" method="post">
					<div class="chaxun-form">
						<b>查询:</b> 
						<span>
							<label>角色名称</label>
							<input name="name" type="text" value="${_page.params.name }" class="bd-r" />
						</span> 
						<span>
							<button class="button02" type="submit">
								<img src="fun/system/images/icon-search.png" />查询
							</button>
						</span>
						<%--<c:if test='${fn:contains(operationlist,"增加") }'>--%>
						<span>
							<button class="button02" onclick="show()" type="button">
								<img src="fun/system/images/icon-add.png" />添加
							</button>
						</span>
						<%--</c:if>--%>
						<%--<c:if test='${fn:contains(operationlist,"删除") }'>--%>
        				<span>
        					<button class="button02" onClick="deleteAll()" type="button">
        						<img src="fun/system/images/icon-del.png" />删除
        					</button>
        				</span>
        				<%--</c:if>--%>
					</div>
					</form>
					<!--查询  结束-->
					
					<!--表格内容  开始-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:5%;"/>
								<col style=""/>
								<col style="width:50%;"/>
								<col style="width:15%;"/>
							</colgroup>
							<thead>
								<tr>
									<th>
										<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll(this)" />
									</th>
									<th>角色名称</th>
									<th>角色描述</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${_page.data }" var="role" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
										<td>
											<input name="check" type="checkbox" value="${role.id }" />
										</td>
										<td>${role.name}</td>
										<td title="${role.ms }">${fn:substring(role.ms,0,25)}<c:if test="${fn:length(role.ms) > 25}">…</c:if></td>
										<td>
											<%--<c:if test='${fn:contains(operationlist,"修改") }'>--%>
												<a href="javascript:showTree('${role.code }')">分配功能</a>
											<%--</c:if>--%>
											<%--<c:if test='${fn:contains(operationlist,"查看") }'>--%>
												<a href="javascript:show(${role.id},true);">查看详情</a>&nbsp;
											<%--</c:if>--%>
											<%--<c:if test='${fn:contains(operationlist,"修改") }'>--%>
												<a href="javascript:show(${role.id});">编辑</a>&nbsp;
											<%--</c:if>
											<%--<c:if test='${fn:contains(operationlist,"删除") }'>--%>
												<a href="javascript:deleteIt(${role.id});">删除</a>&nbsp;
											<%--</c:if>--%>
										</td>
									</tr>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)}" end="${_page.pageRows-1}" var="i">
									<tr <c:if test="${i%2 != 0 }">class="ancolorbg"</c:if>>
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
