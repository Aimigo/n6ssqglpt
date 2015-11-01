<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="fun/javascript/treejs.js"></script>
<p style="margin:2px 10px;"><span id="showbutton" style="cursor:pointer;">展开所有</span> | <span id="hidebutton" style="cursor:pointer;">收起所有</span></p>
	<div id="gridpop" style="height:300px;margin:5px 10px;overflow-y:auto;overflow-x:none;" class="ztree">
	</div>
	<script type="text/javascript">
		//<!--
		d2 = new dTree('d2');
		d2.add(0, -1, '选择连队');
		<c:forEach items="${grides}" var="grid" varStatus="i">
			d2.add('${i.index+100}',0,'${grid}',"javascript:getGrid2('${fn:trim(grid)}');");
		</c:forEach>
		$("#gridpop").html(d2.toString());
		function getGrid2(name){
			sd_remove();
			$("#owner").val(name);
		}
		//-->
	</script>


						
