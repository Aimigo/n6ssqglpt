<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!--  弹出中心内容  -->
<p style="margin:2px 10px;"><a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">收起所有</a></p>
<div id="showTree" style="height:300px;margin:5px 10px;overflow-y:auto;overflow-x:none;"></div>
<script type="text/javascript">
//<!--
	var isChecked = false;
	d = new dTree('d');		//参数tree，表示生成的html代码中id的标记，不影响功能
	d.add(0,-1,'文化娱乐信息分类');		//树根
	<c:forEach items="${fllist}" var="fun">
		d.add('${fun.id }','${fun.fid}','${fun.name }',"javascript:getfl('${fun.id }','${fun.name }')");
	</c:forEach>
	
	$("#showTree").html(d.toString());
//-->
</script>

