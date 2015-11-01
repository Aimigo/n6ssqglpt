<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<p style="margin:2px 10px;"><span id="showbutton" style="cursor:pointer;">展开所有</span> | <span id="hidebutton" style="cursor:pointer;">收起所有</span></p>
户主姓名：<input type="text" class="w100" name="hzname"  id="hz_name" value="${_page.params.name }"/><button class="button02"  onclick="chaxun()"><img src="fun/system/images/icon-search.png" />查询</button>
<div id="ztree" style="height:300px;margin:5px 10px;overflow-y:auto;overflow-x:none;" class="ztree">
	<span>
		<img src="fun/ztree/css/zTreeStyle/img/loading.gif"/>&nbsp;数据加载中，请稍后…
	</span>
</div>
<script type="text/javascript">
	function chaxun(){
		var hz_name=$("#hz_name").val();
		//if(hz_name==null||""==hz_name){
		//	return;
		//}
		$.ajax({
			type : 'post',
			url : 'jmxx_getTree.action',
			data:{"hzname":hz_name},
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
	}
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
				rootPId : 0,
				level:"level"
			}
		},
		async: {
			enable: true,
			url:"jmxx_getTree.action",
			autoParam:["id=code","level=level"],
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
		url : 'jmxx_getTree.action',
		data:{"level":-1},
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
		
		getHkxx(treeNode.id,treeNode.name);
	}
	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		if (!check) alert("只能选择子节点...");
		return check;
	}
	

	$("#showbutton").click(function(){zTreeObj.expandAll(true);});
	$("#hidebutton").click(function(){zTreeObj.expandAll(false);});
</script>
						
