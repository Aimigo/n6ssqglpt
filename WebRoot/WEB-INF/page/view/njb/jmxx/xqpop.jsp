<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="fun/javascript/treejs.js"></script>
<p style="margin:2px 10px;"><span id="showbutton" style="cursor:pointer;">展开所有</span> | <span id="hidebutton" style="cursor:pointer;">收起所有</span></p>
	<div id="gridpop6" style="height:300px;margin:5px 10px;overflow-y:auto;overflow-x:none;" class="ztree">
	</div>
	<script type="text/javascript">
		//<!--
		d6 = new dTree('d6');
		d6.add(0, -1, '选择');
		<c:forEach items="${data}" var="val" varStatus="i">
			d6.add('${i.index+100}',0,'${val}',"javascript:getGrid6('${fn:trim(val)}');");
		</c:forEach>
		$("#gridpop6").html(d6.toString());
		function getGrid6(name){
			if("${ints}"=="0"){
				$("input[name=road]").val(name);
			}else if("${ints}"=="1"){
				$("input[name=ridgepole]").val(name);
			}else if("${ints}"=="2"){
				$("input[name=element]").val(name);
			}else if("${ints}"=="3"){
				$("input[name=roomnumber]").val(name);
			}
			sd_remove();
			
		}
		//-->
	</script>


						
