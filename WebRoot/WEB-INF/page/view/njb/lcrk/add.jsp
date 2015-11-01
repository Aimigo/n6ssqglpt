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
<link href="fun/system/css/nchjbxx.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript"
		src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/fun/njb/Validator.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="fun/javascript/treejs.js"></script>
<script type="text/javascript">
	$(function() {
		//验证和提交
		$("#save").click(function() {
			Validator.Validate(document.forms[0], 3);
		});
	});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>

	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;流出人口信息&nbsp;&gt;&gt;&nbsp;添加</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--表单内容  开始-->
				<form action="lcrk_save.action" method="post" name="saveform">
					<div class="biaodan-all">
						<div class="biaodan-title">流出人口</div>
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="title2">基本信息</td>
							</tr>
							<tr>
								<td width="17%">户主（<font class="fred">*</font>）</td>
								<td width="33%">
									<input type="text" name="huzhu" dataType="Require"  class="qbg" 
									msg="户主不能为空。" id="ccfwname" value="${model.huzhu }"
									/><%--onclick="javascript:$('#popDiv').show();"/>
									--%>	
								</td>
								<td width="17%">子女（<font class="fred">*</font>）</td>
								<td width="33%"><input name="zinv" type="text" class="qbg"  value="${model.zinv }"
									dataType="LimitB" min="1" max="50" msg="子女不能为空."
									require="true" />
								</td>
							</tr>
							<tr>
								<td>年龄</td>
								<td>
								<input name="nianling" type="text" class="qbg" value="${model.nianling }" 
								dataType="Number"  msg="年龄输入不合法."/>
								</td>
								<td>流出时间</td>
								<td><input name="liuchushijian" type="text" class="qbg" value="${fn:substring(model.liuchushijian,0,10) }"
								readonly="readonly" onfocus="WdatePicker();"/>
								</td>
							</tr>
							<tr>
								<td>单位</td>
								<td><input name="danwei" type="text" class="qbg"  value="${model.danwei }"/>
								</td>
								<td>电话</td>
								<td><input name="dianhua" type="text" class="qbg"  value="${model.dianhua }"/>
								</td>
							</tr>
							<tr>
								<td>备注</td>
								<td><input name="beizhu" type="text" class="qbg"  value="${model.beizhu }"/>
								</td>
								
							</tr>
						</table>
						<div class="bcan">
							<a href="#" id="save"> <img src="fun/system/images/bc.gif">
							</a> &nbsp; <a href="javascript:history.go(-1);"> <img
								src="fun/system/images/fh.gif">
							</a>
						</div>
					</div>
					<!--表单内容  结束-->
				</form>
				<!--表单内容 结束 -->
			</div>
		</div>
		<!--right主体内容  结束-->

		<jsp:include page="/WEB-INF/page/system/index/left.jsp"></jsp:include>
		<div class="clearit"></div>
	</div>
	<!--主体内容 结束-->

	<jsp:include page="/WEB-INF/page/system/index/foot.jsp"></jsp:include>
<!--表格内容  开始-->
<div class="tab-border" style="width:400px;height:200px;position: absolute;top:30%;left:40%;display:none;" id="popDiv">
	<table class="zhwxx-tab">
		<thead>
			<tr>
				<th align="left">
					<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">收起所有</a>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<!--  弹出中心内容  -->
					<div id="showTree" style="text-align:left;vertical-align:top;height:377px;overflow-y:auto;"></div>
					<script type="text/javascript">
						//<!--
						d = new dTree('d');
						d.add(0, -1, '楼宇信息');
						<c:forEach items="${syfwgl}" var="fw">
							d.add('${fw.id}',0,'${fw.xqdz}',"javascript:getid('${fw.id}','${fw.xqdz}');");
						</c:forEach>
						$("#showTree").html(d.toString());
						function getid(id,name){
							$("#popDiv").hide();
							$("#ccfwid").val(id);
							$("#ccfwname").val(name);
						}
						//-->
					</script>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<!--表格内容  结束-->
</body>
</html>