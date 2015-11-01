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
<script type="text/javascript" src="fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function detail(id) {
		window.location.href = "pxjn_detail.action?id=" + id;
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
				url : "pxjn_delete.action",
				data : {
					"id" : id
				},
				dataType : "html",
				success : function(data) {
					window.location.href = "pxjn_list.action?dtype=${_page.params.type}";
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
					url : "pxjn_delete.action",
					data : {
						"id" : str
					},
					dataType : "html",
					success : function(data) {
						window.location.href = "pxjn_list.action?dtype=${_page.params.type}";
					}
				});
			}
		}
	}
	
	/*展示新增修改窗体*/
	function show(id,type) {
		var url = "pxjn_add.action?_page.params.type=${_page.params.type}";
		if (id)
			url = "pxjn_edit.action?_page.params.type=${_page.params.type}&id=" + id;
		if(type)
			url = "pxjn_detail.action?_page.params.type=${_page.params.type}&id=" + id;
		
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
	
	
	var mouseX;
	var mouseY;
	function tpyl(e,src){
		document.getElementById('tpylimg').src = src;
		var obj=document.getElementById('tpyl');
		mouseOver(e);
		obj.style.left = mouseX + 230 + "px";
		obj.style.top = mouseY + 0 + "px";
		obj.style.display = 'block';
	}
	function mouseOver(obj) {
		e = obj || window.event;
		// 此处记录鼠标停留在组建上的时候的位置, 可以自己通过加减常量来控制离鼠标的距离.
		mouseX = e.layerX|| e.offsetX;
		mouseY = e.layerY|| e.offsetY;
	} 
</script>
</head>

<body onload="MM_preloadImages('fun/system/images/nchjbxx-nav01-.png')">
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>
	
	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
			<div style="display: none; left: 772px; top: 437px;" id="tpyl" class="yiyuan-weizhi">
				<div style="width: 350px; height: 350px" class="border-top1">
					<div class="border-top-title-bottom">
			    		<div class="border-top-title">
			    			<div class="iw_poi_title">图片预览</div>
			                <div style="float:right;position:absolute;top:0px;right:0px;height:30px;background-color:#f9f9f9"><div style="cursor:pointer;width:32px;height:30px"><img src="fun/system/images/iw_close.gif" onclick="javascript:document.getElementById('tpyl').style.display = 'none'; " style="position:absolute;top:9px;right:12px;width:10px;height:10px;-moz-user-select:none;cursor:pointer;z-index:10000;"></div></div>
			            </div>
			        </div>
			        <div class="biaoshi-img">
			        	<img src="" style="width: 300px; height: 290px" id="tpylimg">
			        </div>
			    </div>
			</div>
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;技能培训多媒体&nbsp;&gt;&gt;&nbsp;多媒体管理</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--选项卡  开始-->
			    <div class="tdzy-tab">
			        <ul>
			          <li class="tab-<c:if test='${_page.params.type ne 1}'>no</c:if>selected"  style="cursor:pointer;" onclick="javascript:location.href='pxjn_list.action?dtype=1';"><a href="#">文档</a></li>
			          <li class="tab-<c:if test='${_page.params.type ne 2}'>no</c:if>selected"  style="cursor:pointer;" onclick="javascript:location.href='pxjn_list.action?dtype=2';"><a href="#">视频</a></li>
			        </ul>
			    </div>
				<!--选项卡  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<form action="pxjn_list.action?dtype=${_page.params.type }" method="post">
					<div class="chaxun-form">
						<b>查询:</b> 
						<span>
							<label>标题</label>
							<input name="_page.params.bt" type="text" value="${_page.params.bt }" class="bd-r" />
						</span>
						<span>
							<label>多媒体分类</label>
							<select name="_page.params.flid" class="bd-r" >
								<option value="">请选择</option>
								<c:forEach items="${jnfllist }" var="jnfl">
									<option value="${jnfl.id }" <c:if test="${_page.params.flid eq jnfl.id }">selected="selected"</c:if>>${jnfl.name }</option>
								</c:forEach>
							</select>
						</span>
						<span>
							<label>上传时间</label>
							<input name="_page.params.begintime" type="text" value='${_page.params.begintime}' class="bd-r" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							 - <input name="_page.params.endtime" type="text" value='${_page.params.endtime}' class="bd-r" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
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
        				<button class="button02" type="button" style="float:right;" onclick="javascript:location.href='pxjn_chart.action';">
							<img src="fun/system/images/icon-search.png" />查看比例图
						</button>
					</div>
					</form>
					<!--查询  结束-->
					
					<!--表格内容  开始-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:5%;"/>
								<col style=""/>
								<c:if test='${_page.params.type eq 2}'>
								<col style="width:15%;"/>
								</c:if>
								<col style="width:15%;"/>
								<col style="width:15%;"/>
								<col style="width:15%;"/>
							</colgroup>
							<thead>
								<tr>
									<th>
										<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll(this)" />
									</th>
									<th>标题</th>
									<c:if test='${_page.params.type eq 2}'>
									<th>缩略图</th>
									</c:if>
									<th>多媒体分类</th>
									<th>上传时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${_page.data }" var="obj" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
										<td>
											<input name="check" type="checkbox" value="${obj.id }" />
										</td>
										<td>${obj.bt}</td>
										<c:if test='${_page.params.type eq 2}'>
										<td><img width="100px" height="75px" onmouseout="javascript:document.getElementById('tpyl').style.display = 'none';" onmouseover="tpyl(event,'${obj.tp }')" src="${obj.tp }"></td>
										</c:if>
										<td>${obj.flname}</td>
										<td><fmt:formatDate value="${obj.scsj }" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
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
										<c:if test='${_page.params.type eq 2}'>
										<td></td>
										</c:if>
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