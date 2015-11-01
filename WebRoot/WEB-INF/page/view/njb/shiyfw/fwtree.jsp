<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<p style="margin:2px 10px;"><span id="showbutton" style="cursor:pointer;">展开所有</span> | <span id="hidebutton" style="cursor:pointer;">收起所有</span></p>
<div id="ztree" style="height:300px;margin:5px 10px;overflow-y:auto;overflow-x:none;" class="ztree">
	<span>
		<img src="fun/ztree/css/zTreeStyle/img/loading.gif"/>&nbsp;数据加载中，请稍后…
	</span>
</div>
<script type="text/javascript">
	/*
	 * 行政区划
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
		async: {
			enable: true,
			url:"syfw_getTree.action",
			autoParam:["id=code"],
			type:"post"
		},
		callback : {
			beforeClick: beforeClick,
			onClick : zTreeOnClick
		}
	};
	
	/**
	 * 获取zTree数据源
	 */
	$.ajax({
		type : 'post',
		url : 'syfw_getTree.action',
		data:{"level":1},
		cache : false,
		dataType : 'json',
		success : function(data) {
			zNodes = data ;
			zTreeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);
		},
		error : function() {
			return false;
		}
	});
	
	/**
	 * zTree单击事件
	 */
	function zTreeOnClick(event, treeId, treeNode) {
		getRegion(treeNode.id,treeNode.name);
	}
	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		if (!check) alert("只能选择子节点...");
		return check;
	}
	

	$("#showbutton").click(function(){zTreeObj.expandAll(true);});
	$("#hidebutton").click(function(){zTreeObj.expandAll(false);});
</script>
						
