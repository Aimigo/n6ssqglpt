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
<link rel="stylesheet" href="fun/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
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
		if (confirm("您确定要删除吗？此操作将删除与之关联的所有信息！")) {
			$.ajax({
				type : "post",
				url : "function_delete.action",
				data : {
					"id" : id
				},
				dataType : "html",
				success : function(data) {
					window.location.href = "function_list.action";
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
			if (confirm("您确定要删除吗？此操作将删除与之关联的所有信息！")) {
				$.ajax({
					type : "post",
					url : "function_delete.action",
					data : {
						"id" : str
					},
					dataType : "html",
					success : function(data) {
						window.location.href = "function_list.action";
					}
				});
			}
		}
	}
	
	var functionCode = "";
	var projectCode = "";
	var isLeaf=true;
	/*选中节点事件*/
	function getId(id,code){
		if($("#showWindow").is(":visible")){//判断增加窗体是否已经打开 有则提示
			alert("请先进行当前操作或关闭当前操作再进行选取！");
			return;
		}
		functionCode = id;
		projectCode = code;
	}
	
	/*显示添加功能窗口*/
	function showInsert(){
		if(!isLeaf){
			alert("该节点下不能添加功能");
			return;
		}
		if(""==functionCode&&""==projectCode){
			alert("请选择一个节点后再进行添加操作");
		}else{
			//ajax判断所选节点是否是子节点 如果是子节点则提示不能进行增加操作
			location.href="function_add.action?code="+functionCode+"&projectCode="+projectCode;
		}
	}
	
	/*显示修改功能窗口*/
	function showEdit(){
		if(!isLeaf){
			alert("该节点不能修改");
			return;
		}
		if(""==functionCode){
			alert("请选择一个节点后再进行修改操作");
		}else if("0"==functionCode){
			alert("此功能不能被修改！")
		}else{
			//ajax判断所选节点是否是子节点 如果是子节点则提示不能进行增加操作
			location.href="function_edit.action?code="+functionCode;
		}
	}
	
	/*显示修改功能窗口*/
	function showDetail(functionId){
		if(""==functionId){
			alert("查看功能管理失败！");
		}else{
			//ajax判断所选节点是否是子节点 如果是子节点则提示不能进行增加操作
			location.href="function_detail.action?id="+functionId;
		}
	}

	//zTree树形结构
	var ztreeObj = null;
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : 0
			}
		},
		callback:{
			onClick:menuClick
		}
	};
	//菜单的点击事件
	function menuClick(event, treeId, treeNode){
		isLeaf=treeNode.isParent;
		if(!treeNode.isParent){
			return;
		}
		//获取树节点的id和功能本身的代码
		if(treeNode.pId==0){//如果treeNode.pId=0则证明这个节点是项目节点
			projectCode=treeNode.id;
			
			$.ajax({
				type : 'post',
				url : 'function_list.action',
				data:{isBtn:"yes"},
				dataType : 'html',
				success : function(data) {
					$("#funList").html(data);
				},
				error : function() {
					return false;
				}
			});
		}else{
			functionCode=treeNode.id;
			projectCode=treeNode.pId;
			
			$.ajax({
				type : 'post',
				url : 'function_list.action',
				data:{code:treeNode.id,isBtn:"no"},
				dataType : 'html',
				success : function(data) {
					$("#funList").html(data);
				},
				error : function() {
					return false;
				}
			});
		}
	}
	
	function getChildrens(treeNode){
		var childrens = new Array();
		var childrensTemp = new Array();
		childrens.push(treeNode);
		childrensTemp.push(treeNode);
		
		while(childrensTemp.length > 0){
			var childsTemp = new Array();
			for(var i in childrensTemp){
				if(childrensTemp[i].isParent)
					childsTemp = childsTemp.concat(childrensTemp[i].children);
			}
			childrensTemp = childsTemp;
			childrens = childrens.concat(childsTemp);
		}

		var str = ""
		for(var i=0;i<childrens.length;i++){
			if(str==""){
				str = childrens[i].nodeId;
			}else{
				str = str + "," + childrens[i].nodeId;
			}
		}
		alert(str);
	}
	
	$(function(){
		/**
		 * 获取zTree数据源
		 */
		$.ajax({
			type : 'post',
			url : 'function_getTree.action',
			cache : false,
			dataType : 'json',
			success : function(data) {
				var zNodes = data ;
				ztreeObj=$.fn.zTree.init($("#showTree"), setting, zNodes);
			},
			error : function() {
				return false;
			}
		});
		
		
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

		//展开/收缩节点
		$("#showbutton").click(function(){ztreeObj.expandAll(true);});
		$("#hidebutton").click(function(){ztreeObj.expandAll(false);});
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;系统管理&nbsp;&gt;&gt;&nbsp;功能管理</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<table style="width:100%;">
					<colgroup>
						<col style="width:20%;"/>
						<col style=""/>
					</colgroup>
					<tr>
						<td>
							<div class="tdzy-tab-down">
								<!--按钮  开始-->
								<div class="chaxun-form">
									<%--<c:if test='${fn:contains(operationlist,"增加") }'>--%>
									<span>
										<button class="button02" onclick="showInsert();" type="button">
											<img src="fun/system/images/icon-add.png" />添加
										</button>
									</span>
									<%--</c:if>--%>
									<%--<c:if test='${fn:contains(operationlist,"修改") }'>--%>
			        				<span>
			        					<button class="button02" onClick="showEdit();" type="button">
			        						<img src="fun/system/images/icon-del.png" />修改
			        					</button>
			        				</span>
			        				<%--</c:if>--%>
								</div>
								<!--查询  结束-->
								
								<!--表格内容  开始-->
								<div class="tab-border">
									<table class="zhwxx-tab">
										<thead>
											<tr>
												<th align="left">
													<a id="showbutton" style="cursor:pointer">展开所有</a> | <a id="hidebutton" style="cursor:pointer">收起所有</a>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<!--  弹出中心内容  -->
													<div id="showTree" style="height:777px;overflow-y:auto;overflow-x:none;" class="ztree">
														<span style="float:left;">
															<img src="fun/ztree/css/zTreeStyle/img/loading.gif"/>&nbsp;数据加载中，请稍后…
														</span>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--表格内容  结束-->
							</div>
						</td>
						<td>
							<div class="tdzy-tab-down">
								<!--查询  开始-->
								<form action="function_list.action" method="post">
								<div class="chaxun-form">
									<b>查询:</b> 
									<span>
										<label>功能名称</label>
										<input name="name" type="text" value="${_page.params.name }" class="bd-r" />
									</span> 
									<span>
										<button class="button02" type="submit">
											<img src="fun/system/images/icon-search.png" />查询
										</button>
									</span>
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
								<div class="tab-border" id="funList">
									<jsp:include page="funList.jsp"></jsp:include>
								</div>
							</div>
						</td>
					</tr>
				</table>
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
