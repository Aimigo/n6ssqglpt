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
<script type="text/javascript">
	var code = "";
	/*显示角色树形结构*/
	function showTree(usercode) {
		code = usercode;
		var now = new Date().getTime();
		$.getJSON("user_getTreeFromRole.action?now=" + now,{"usercode" : usercode},function(data) {
			d = new dTree('d'); //参数tree，表示生成的html代码中id的标记，不影响功能
			d.add(0, -1, '角色信息'); //树根
			$.each(data,function(i, role) {
				if (role.isHave == "true") {
					d.add(role.id,0,'<input type="checkbox" name="rolebox" value="'+role.code+'" checked="checked">'
											+ role.name);
				} else {
					d.add(role.id,0,'<input type="checkbox" name="rolebox" value="'+role.code+'">'
											+ role.name);
				}
			})
			$("#showTree").html(d.toString());
		});
	}
	
	/*选择角色*/
	function selectRole() {
		//alert(code);
		var rolecode = "";
		$("[name='rolebox']").each(function() {
			if (this.checked) {
				if (rolecode == "")
					rolecode = $(this).val();
				else
					rolecode += "," + $(this).val();
			}
		});
		//alert(rolecode)
		if (rolecode == "")
			alert("请选择您要添加的角色信息！");
		else {
			$.ajax({
				type : "post",
				url : "user_selectRole.action",
				data : {
					"usercode" : code,
					"rolecode" : rolecode
				},
				cache : false,
				success : function(data) {
					
				},
				error : function() {
					return;
				}

			})
		}
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
				url : "user_delete.action",
				data : {
					"id" : id
				},
				dataType : "html",
				success : function(data) {
					window.location.href = "user_list.action";
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
					url : "user_delete.action",
					data : {
						"id" : str
					},
					dataType : "html",
					success : function(data) {
						window.location.href = "user_list.action";
					}
				});
			}
		}
	}

	/*展示新增修改窗体*/
	function show(id,type){
		var  url = "user_add.action";
		if(id)
			url = "user_edit.action?id="+id;
		if(type)
			url = "user_detail.action?id="+id;
		
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;系统管理&nbsp;&gt;&gt;&nbsp;用户管理</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<form action="user_list.action" method="post">
					<div class="chaxun-form">
						<b>查询:</b> 
						<span>
							<label>真实姓名</label>
							<input name="realname" type="text" value="${_page.params.realname }" class="bd-r" />
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
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:5%;"/>
								<col style="width:5%;"/>
								<col style="width:15%;"/>
							</colgroup>
							<thead>
								<tr>
									<th>
										<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll(this)" />
									</th>
									<th>用户名</th>
									<th>真实姓名</th>
									<th>出生日期</th>
									<th>性别</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${_page.data }" var="user" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
										<td>
											<input name="check" type="checkbox" value="${user.id }" />
										</td>
										<td>${user.username }</td>
										<td>${user.realname }</td>
										<td>
											<fmt:formatDate value="${user.age }" pattern="yyyy-MM-dd" />
										</td>
										<td>${user.sex }</td>
										<td>${user.state }</td>
										<td>
											<%--<c:if test='${fn:contains(operationlist,"查看") }'>--%>
												<a href="javascript:show(${user.id},true);">查看详情</a>&nbsp;
											<%--</c:if>--%>
											<%--<c:if test='${fn:contains(operationlist,"修改") }'>--%>
												<a href="javascript:show(${user.id});">编辑</a>&nbsp;
											<%--</c:if>
											<%--<c:if test='${fn:contains(operationlist,"删除") }'>--%>
												<a href="javascript:deleteIt(${user.id});">删除</a>&nbsp;
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
	
	<!-- 废弃 -->
	<div id="show" style="display:none;">
		<p style="margin:2px 10px;"><a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">收起所有</a></p>
		<div id="showTree" style="height:300px;margin:5px 10px;overflow-y:auto;overflow-x:none;"></div>
		<div class="bcan">
			<a href="#" id="update" onclick="selectRole()">
				<img src="fun/system/images/bc.gif">
			</a>
		</div>
	</div>
</body>
</html>