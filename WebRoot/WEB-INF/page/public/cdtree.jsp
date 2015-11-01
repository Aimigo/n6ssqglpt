<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>'cdtree.jsp' </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/script/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/script/ztree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/script/ztree/js/jquery.ztree.core-3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/script/ztree/js/jquery.ztree.excheck-3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/script/ztree/js/jquery.ztree.exedit-3.2.js"></script>
	<SCRIPT type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url: getUrl
			},
			
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				expandSpeed: ""
			},
			callback: {
				beforeExpand: beforeExpand,
				onAsyncSuccess: onAsyncSuccess,
				onAsyncError: onAsyncError
			}
		};

		var zNodes =[
			{name:"中华人民共和国",id:'1', city:"101000", times:1, isParent:true}
		];

		var log, className = "dark",
		startTime = 0, endTime = 0, perCount = 100, perTime = 100;
		function getUrl(treeId, treeNode) {
			var curCount = (treeNode.children) ? treeNode.children.length : 0;
			var getCount = (curCount + perCount) > treeNode.count ? (treeNode.count - curCount) : perCount;
			var param = "city="+treeNode.city+"_"+(treeNode.times++) +"&count="+getCount;
			return "region_getTree.action?city=" + treeNode.city;
		}
		function beforeExpand(treeId, treeNode) {
			if (!treeNode.isAjaxing) {
				startTime = new Date();
				treeNode.times = 1;
				ajaxGetNodes(treeNode, "refresh");
				return true;
			} else {
				alert("正在下载数据中，请稍后展开节点。。。");
				return false;
			}
		}
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			if (!msg || msg.length == 0) {
				return;
			}
			var zTree = $.fn.zTree.getZTreeObj("treeCd"),
			totalCount = treeNode.count;
			if (treeNode.children.length < totalCount) {
				setTimeout(function() {ajaxGetNodes(treeNode);}, perTime);
			} else {
				treeNode.icon = "";
				zTree.updateNode(treeNode);
				zTree.selectNode(treeNode.children[0]);
				endTime = new Date();
				var usedTime = (endTime.getTime() - startTime.getTime())/1000;
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" ]&nbsp;&nbsp;treeNode:" + treeNode.name );
				showLog("加载完毕，共进行 "+ (treeNode.times-1) +" 次异步加载, 耗时："+ usedTime + " 秒");
			}
		}
		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			var zTree = $.fn.zTree.getZTreeObj("treeCd");
			alert("异步获取数据出现异常。");
			treeNode.icon = "";
			zTree.updateNode(treeNode);
		}
		function ajaxGetNodes(treeNode, reloadType) {
			var zTree = $.fn.zTree.getZTreeObj("treeCd");
			if (reloadType == "refresh") {
				treeNode.icon = "ui/script/ztree/css/zTreeStyle/img/loading.gif";
				zTree.updateNode(treeNode);
			}
			zTree.reAsyncChildNodes(treeNode, reloadType, true);
		}
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 4) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

		$(document).ready(function(){
			//alert(zNodes);
			$.fn.zTree.init($("#treeCd"), setting, zNodes);
		});
		var _id = "";
		var _fl ="";
		var _name = "";
		var _city = "";
		function toinput(id,name,code,fid){
			_id = id;
			_fl = fid;
			_name = name;
			_city = code;
			showzbqy();
			//alert(_id+_fatherid+_name+_code);
		}
</SCRIPT>
</head>
</html>
