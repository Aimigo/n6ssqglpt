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

						
