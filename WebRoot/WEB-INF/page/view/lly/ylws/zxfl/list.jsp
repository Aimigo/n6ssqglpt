<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script src="fun/highcharts/highcharts.js" type="text/javascript"></script>
<script src="fun/highcharts/myChart.js" type="text/javascript"></script>
<script type="text/javascript">
	function detail(id) {
		window.location.href = "zxfl_detail.action?id=" + id;
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
				url : "zxfl_delete.action",
				data : {
					"id" : id
				},
				dataType : "html",
				success : function(data) {
					window.location.href = "zxfl_list.action";
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
					url : "zxfl_delete.action",
					data : {
						"id" : str
					},
					dataType : "html",
					success : function(data) {
						window.location.href = "zxfl_list.action";
					}
				});
			}
		}
	}
	
	/*展示新增修改窗体*/
	function show(id,type) {
		var url = "zxfl_add.action";
		if (id)
			url = "zxfl_edit.action?id=" + id;
		if(type)
			url = "zxfl_detail.action?id=" + id;
		
		location.href=url;
	}

	/* $(function() {
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
	}); */
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
		
		var json1 = '${topmap}';
		var json2 = json1.substring(1, json1.length - 1);
		var json3 = json2.split(",");
		var m = "";
		for ( var i = 1; i < json3.length; i++) {
			json4 = json3[i].split("=");
			m += "['" + json4[0] + "'," + json4[1] + "],";
		}
		mdata = "[" + m.substring(0, m.length - 1) + "]";
		//饼图chart
		$("#one").myChart({
			title : '比例图',
			subtitle : '关于系统中健康咨询的数据分布情况',
			width : '448',
			type : 'pie',
			series : [ {
				name : '分类下数据量',
				data : eval("(" + mdata + ")")
			} ]
		});
		var color = "#f97930|#199b77|#0e4677|#ecaf44|#4f2049|green|bule|red|#fe2193";
		color = color.split("|");
		for ( var i = 0; i < document.getElementById("niu")
				.getElementsByTagName("li").length; i++) {
			var col = color[parseInt(Math.random() * color.length)];
			var niuh3 = document.getElementById("niu")
					.getElementsByTagName("li")[i]
					.getElementsByTagName("h3")[0];
			niuh3.style.color = col;
		}
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;医疗卫生&nbsp;&gt;&gt;&nbsp;健康咨询&nbsp;&gt;&gt;&nbsp;咨询分类</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<form action="zxfl_list.action" method="post">
					<div class="chaxun-form">
						<b>查询:</b> 
						<span>
							<label>分类名称</label>
							<input name="_page.params.name" type="text" value="${_page.params.name }" class="bd-r" />
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
						<%-- <c:if test='${fn:contains(operationlist,"删除") }'>
        				<span>
        					<button class="button02" onClick="deleteAll()" type="button">
        						<img src="fun/system/images/icon-del.png" />删除
        					</button>
        				</span>
        				</c:if> --%>
					</div>
					</form>
					<!--查询  结束-->
					
					<!--表格内容  开始-->
					<link href="fun/system/css/link.css" type="text/css"
						rel="stylesheet" />
					<div class="tab-border">
						<div class="tb_left">
							<div class="tb_leftin">
								<ul id="niu">
									<c:forEach items="${_page.data }" var="obj" varStatus="i">
										<li>
											<h3>${obj.name}</h3>
											<div class="tb_a">

												<c:if test='${fn:contains(operationlist,"查看") }'>
													<a href="javascript:show(${obj.id},true);">详情</a>
												</c:if>
												<c:if test='${fn:contains(operationlist,"修改") }'>
													<a href="javascript:show(${obj.id});">编辑</a>
												</c:if>
												<c:if test='${fn:contains(operationlist,"删除") }'>
													<a href="javascript:deleteIt(${obj.id});">删除</a>
												</c:if>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="tb_right">
							<div id="one" class="right_foot"></div>
							<div class="right_top">
								<c:forEach items="${topmap}" var="item" varStatus="t">
									<c:if test="${t.count != 1&&t.count != 6 }">
										<span class="rt_${t.count-1}">${item.key}</span>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
					<!--表格内容  结束-->
					<!--表格内容  开始
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:5%;"/>
								<col style=""/>
								<col style="width:10%;"/>
								<col />
								<col style="width:5%;"/>
								<col style=""/>
								<col style="width:10%;"/>
								<col />
								<col style="width:5%;"/>
								<col style=""/>
								<col style="width:10%;"/>
							</colgroup>
							<thead>
								<tr>
									<th>
										<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll(this)" />
									</th>
									<th>分类名称</th>
									<th>操作</th>
									<th></th>
									<th>
										
									</th>
									<th>分类名称</th>
									<th>操作</th>
									<th></th>
									<th>
										
									</th>
									<th>分类名称</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${_page.data }" var="obj" varStatus="i">
									<c:if test="${i.count%3 == 1 }">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
									</c:if>
										<td>
											<input name="check" type="checkbox" value="${obj.id }" />
										</td>
										<td>${obj.name}</td>
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
										<c:if test="${i.count == 1 }"><td rowspan="20"></td></c:if>
										<c:if test="${i.count == 2 }"><td rowspan="20"></td></c:if>
									<c:if test="${i.count%3 == 0 }">
									</tr>
									</c:if>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)+1}" end="${_page.pageRows}" var="i">
									<c:if test="${i%3 == 1 }">
									<tr <c:if test="${i%2 == 0 }">class="ancolorbg"</c:if>>
									</c:if>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<c:if test="${i == 1 }"><td rowspan="20"></td></c:if>
										<c:if test="${i == 2 }"><td rowspan="20"></td></c:if>
									<c:if test="${i%3 == 0 }">
									</tr>
									</c:if>
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