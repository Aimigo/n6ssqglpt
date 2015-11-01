<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	/*
	 * 功能树
	 */
	var setting = null;//zTree配置
	var zNodes = null;//zTree数据源
	var zTreeObj = null;
	
	/**
	 * 配置zTree
	 */
	setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : 0
			}
		},
		check: {
			enable: true,
			chkStyle: "checkbox"
		}
	};
	
	//获取角色操作
	$.ajax({
		type : "post",
		url : "role_getTree.action",
		data : {
			"rolecode" : $("#rolecode").val()
		},
		dataType : "json",
		success : function(data) {
			/*
				1.mode 模式
					a. confirm [确认模式]
					b. info [显示信息]
					b. window [AJAX获取网页内容]
					c. alert [警告模式]
				2. msg 内容
					显示弹出宽口的内容
				3. t 标题
					显示弹出窗口的标题
				4. sd_width 宽度
					显示弹出窗口的宽度
			*/
			zNodes = data[0] ;
			zTreeObj = $.fn.zTree.init($("#showTree"), setting, zNodes);
			
			$.each(data[1],function(index,d){
				zTreeObj.getNodeByParam("id",d.funcode+d.funxxcode,null).checked = true;
				zTreeObj.refresh();
			});
		}
	});
	
	$("#showbutton").click(function(){zTreeObj.expandAll(true);});
	$("#hidebutton").click(function(){zTreeObj.expandAll(false);});
	
	/**
	 * 保存角色功能操作
	 */
	$("#setfunctionbutton").click(function(){
		if(!zTreeObj){
			alert("数据正在加载中，请稍后…");
			return;
		}
		var functionandoperationcodes = null;
		
		var nodes = zTreeObj.getCheckedNodes(true);
		for (var i = 0; i < nodes.length; i++) {
			if(!nodes[i].isParent){//只需要功能操作CODE
				if(null==functionandoperationcodes){
					functionandoperationcodes = nodes[i].id;
				}else{
					functionandoperationcodes += "," + nodes[i].id;
				}
			}
		}
		
		if(null == functionandoperationcodes){
			alert("您未选择任何项目功能操作！");
		}else{
			$.ajax({
				type : "post",
				url : "role_selectFunction.action",
				data : {
					"rolecode" : $("#rolecode").val(),
					"opercode" : functionandoperationcodes
				},
				dataType : "html",
				cache : false,
				success:function(data){
					sd_remove();
				},
				error : function() {
					return;
				}
			});
		}
	});
</script>
<!--  弹出中心内容  -->
<p style="margin:2px 10px;"><a  id="showbutton" style="cursor:pointer">展开所有</a> | <a  id="hidebutton" style="cursor:pointer">收起所有</a></p>
<div id="showTree" style="height:300px;margin:5px 10px;overflow-y:auto;overflow-x:none;" class="ztree">
	<span>
		<img src="fun/ztree/css/zTreeStyle/img/loading.gif"/>&nbsp;数据加载中，请稍后…
	</span>
</div>
<div class="bcan">
	<a href="#" id="setfunctionbutton">
		<img src="fun/system/images/bc.gif">
	</a>
</div>
