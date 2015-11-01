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
<script type="text/javascript"
	src="fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function detail(id) {
		window.location.href = "mdcl_detail.action?id=" + id;
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
				url : "mdcl_delete.action",
				data : {
					"id" : id
				},
				dataType : "html",
				success : function(data) {
					window.location.href = "mdcl_list.action";
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
					url : "mdcl_delete.action",
					data : {
						"id" : str
					},
					dataType : "html",
					success : function(data) {
						window.location.href = "mdcl_list.action";
					}
				});
			}
		}
	}

	/*展示新增修改窗体*/
	function show(id, type) {
		var url = "mdcl_add.action";
		if (id)
			url = "mdcl_edit.action?id=" + id;
		if (type)
			url = "mdcl_detail.action?id=" + id;

		location.href = url;
	}

	$(function() {
		$("tr").click(function() {
			var checked = $(this).find("td").first().find("input");
			checked.attr("checked", !checked.attr("checked"));
		});

		$(":checkbox").click(function(event) {
			event.stopPropagation(); // do something   
		});

		$("td a").click(function(event) {
			event.stopPropagation(); // do something
		});
	});

	function exportExcel() {
		$("#searchForm").attr("action", "mdcl_exportExcel.action").submit()
				.attr("action", "mdcl_list.action");
	}
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;民生事务&nbsp;&gt;&gt;&nbsp;矛盾处理</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<script type="text/javascript">
						$(document).ready(function() {
							var $demo = 0;
							var timer = setInterval(play, 4000);
							function play() {
								$demo++;
								if ($demo > 1<c:if test="${_page.params.type ne '6'}">+1</c:if><c:if test="${headlist[0] eq null }">-1</c:if>) {
									$demo = 0;
									$(".s_ul").css("top", 0);
								}
								$(".s_ul").stop().animate({
									"top" : -$demo * 35
								}, 500);
							}
							$(".s_ul li").hover(function() {
								clearInterval(timer);
							}, function() {
								clearInterval(timer);
								timer = setInterval(play, 4000);
							});
						});

						//文字闪烁
						function changeColor() {
							var color = "#f00|#0f0|#00f|#880|#808|#088|yellow|green|blue|gray";
							color = color.split("|");
							$(".blink")
									.css(
											"color",
											color[parseInt(Math.random()
													* color.length)]);
						}
						setInterval("changeColor()", 500);
					</script>
					<LINK href="fun/system/css/link.css" type="text/css"
						rel="stylesheet" />
					<div class="scroll">
						<ul class="s_ul">
							<li>系统中存在矛盾：<font class="blink" size=4>${headlist[1]}条</font>，其中已处理矛盾<font
								class="blink" size=4>${headlist[2]}条</font>，还有未处理<font class="blink" size=4>${headlist[3]}条</font>。</li>
							<c:if test="${_page.params.type ne '6'}">
							<li><font class="blink" size=4>智能提醒：</font>您还有<font
								class="blink" size=4>${headlist[4]}条</font>矛盾未处理。</li>
							</c:if>
							<c:if test="${headlist[0] ne null }">
							<li><font class="blink" size=4>最新提醒：</font>${headlist[0].wg}出现${headlist[0].flname}事件<font
								class="blink" size=4>1</font>起。</li>
							</c:if>
						</ul>
					</div>
					<form action="mdcl_list.action" method="post" id="searchForm">
						<div class="chaxun-form">
							<b>查询:</b> <span> <label>标题</label> <input
								name="_page.params.bt" type="text" value="${_page.params.bt }"
								class="bd-r" /> </span> 
								<span> <label>所属分类</label> 
								<select name="_page.params.flid" class="bd-r">
									<option value="">请选择</option>
									<c:forEach items="${mdfllist }" var="mdfl">
										<option value="${mdfl.id }"
											<c:if test="${_page.params.flid eq mdfl.id }">selected="selected"</c:if>>${mdfl.name
											}</option>
									</c:forEach>
								</select> 
								</span> 
								<span> <label>填写时间</label> 
									<input name="_page.params.begintime" type="text"
										value='${_page.params.begintime}' class="bd-r"
										readonly="readonly"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> - 
									<input name="_page.params.endtime" type="text"
										value='${_page.params.endtime}' class="bd-r" readonly="readonly"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> 
								</span>
									<span><label>处理状态</label> 
									<select name="_page.params.flag" class="bd-r">
										<option value="">请选择</option>
										<option value="no"
											<c:if test="${_page.params.flag eq 'no' }">selected="selected"</c:if>>未解决</option>
										<option value="yes"
											<c:if test="${_page.params.flag eq 'yes' }">selected="selected"</c:if>>已解决</option>
									</select> 
								</span> 
								<span>
									<button class="button02" type="submit">
										<img src="fun/system/images/icon-search.png" />查询
									</button> 
								</span>
							<c:if test='${fn:contains(operationlist,"增加")}'>
								<span>
									<button class="button02" onclick="show()" type="button">
										<img src="fun/system/images/icon-add.png" />添加
									</button> </span>
							</c:if>
							<c:if test='${fn:contains(operationlist,"删除") }'>
								<span>
									<button class="button02" onClick="deleteAll()" type="button">
										<img src="fun/system/images/icon-del.png" />删除
									</button> </span>
							</c:if>
							<c:if test='${fn:contains(operationlist,"导出") }'>
								<span title="可根据查询条件导出">
									<button class="button02" onClick="exportExcel()" type="button">
										<img src="fun/system/images/icon-add.png" />导出
									</button> </span>
							</c:if>
						</div>
					</form>
					<!--查询  结束-->

					<!--表格内容  开始-->
					<div class="tab-border" style="height: auto">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:5%;" />
								<col style="" />
								<col style="width:8%;" />
								<col style="width:8%;" />
								<col style="width:8%;" />
								<col style="width:8%;" />
								<col style="width:15%;" />
								<col style="width:10%;" />
								<col style="width:15%;" />
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="checkAll" id="checkAll"
										onclick="checkAll(this)" />
									</th>
									<th>标题</th>
									<th>所属分类</th>
									<th>解决方式</th>
									<th>信息采集员</th>
									<th>所属网格</th>
									<th>填写时间</th>
									<th>处理状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${_page.data }" var="obj" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
										<td><input name="check" type="checkbox"
											value="${obj.id }" />
										</td>
										<td>${obj.bt}</td>
										<td>${obj.flname}</td>
										<td>${obj.jcfs}</td>
										<td>${obj.cjrname}</td>
										<td>${obj.wg}</td>
										<td><fmt:formatDate value="${obj.txsj }"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td><c:choose>
												<c:when test="${obj.zt eq -1}">未解决</c:when>
												<c:when test="${obj.zt eq 0}">信息采集员上报未解决</c:when>
												<c:when test="${obj.zt eq 1}">管理员上报未解决</c:when>
												<c:when test="${obj.zt eq 2}">职能部门上报未解决</c:when>
												<c:when test="${obj.zt eq 3}">分管领导上报未解决</c:when>
												<c:when test="${obj.zt eq 4}">已解决</c:when>
											</c:choose>
										</td>
										<td><c:if test='${fn:contains(operationlist,"查看") }'>
												<a href="javascript:show(${obj.id},true);">查看详情</a>&nbsp;
											</c:if> <c:if test='${fn:contains(operationlist,"修改") }'>
												<c:if test="${_page.params.type eq '1' and obj.zt eq -1}">
													<a href="javascript:show(${obj.id});">解决问题</a>&nbsp;
												</c:if>
												<c:if test="${_page.params.type eq '1' and obj.zt eq 0}">
													<a href="javascript:show(${obj.id});">编辑</a>&nbsp;
												</c:if>
												<c:if test="${_page.params.type eq '2' and obj.zt eq 0 }">
													<a href="javascript:show(${obj.id});">解决问题</a>&nbsp;
												</c:if>
												<c:if test="${_page.params.type eq '3' and obj.zt eq 1 }">
													<a href="javascript:show(${obj.id});">解决问题</a>&nbsp;
												</c:if>
												<c:if test="${_page.params.type eq '4' and obj.zt eq 2 }">
													<a href="javascript:show(${obj.id});">解决问题</a>&nbsp;
												</c:if>
												<c:if test="${_page.params.type eq '5' and obj.zt eq 3 }">
													<a href="javascript:show(${obj.id});">解决问题</a>&nbsp;
												</c:if>
											</c:if> <c:if test='${fn:contains(operationlist,"删除") }'>
												<a href="javascript:deleteIt(${obj.id});">删除</a>&nbsp;
											</c:if>
										</td>
									</tr>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)}"
									end="${_page.pageRows-1}" var="i">
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