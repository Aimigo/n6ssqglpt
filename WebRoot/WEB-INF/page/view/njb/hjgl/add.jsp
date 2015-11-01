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
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="fun/showDialog/showDialog.css" type="text/css"/>
<link rel="stylesheet" href="fun/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript" src="fun/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript">
	$(function() {
		//验证和提交
		$("#save").click(function() {
			Validator.Validate(document.forms[0], 3);
		});
	});
	function showTree(){
		$.ajax({
			type : 'post',
			url : 'hjgl_getZzList.action',
			cache : false,
			dataType : 'html',
			success : function(data) {
				showDialog("window", data, "实有房屋");
			},
			error : function() {
				alert("error");
				return false;
			}
		});
	}
	function getRegion(id,name) {
		$("#popDiv").hide();
		$("#syfwid").val(id);
		$("#syfwname").val(name);
		sd_remove();
	}
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;户籍管理&nbsp;&gt;&gt;&nbsp;添加</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--表单内容  开始-->
				<form action="hjgl_save.action" method="post" name="saveform">
					<div class="biaodan-all">
						<div class="biaodan-title">户籍管理</div>
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="title2">基本信息</td>
							</tr>
							<tr>
								<td width="17%">实有房屋（<font class="fred">*</font>）</td>
								<td width="33%">
									<input type="text" name="syfwname" dataType="Require"  class="qbg" 
									readonly="readonly" msg="实有房屋不能为空。" id="syfwname"
									onclick="showTree();"
									<%--onclick="javascript:$('#popDiv').show();"--%>/>
									<input type="hidden" name="syfwid" id="syfwid" dataType="Require" msg="实有房屋不能为空。"/>	
								</td>
								<td width="17%">户口薄号（<font class="fred">*</font>）</td>
								<td width="33%"><input name="hkbh" type="text" class="qbg"
									dataType="LimitB" min="1" max="50" msg="户口薄号不能为空."
									require="true" noRepeat="true" repeatTab="TblHjgl" repeatMsg="户口薄号已存在"/>
								</td>
							</tr>
							<tr>
								<td>电话</td>
								<td>
								<input name="dh" id="dh" type="text" class="qbg"  
								/>
								</td>
								<td>手机</td>
								<td><input name="sj" type="text" class="qbg" />
								</td>
							</tr>
							<tr>
								<td>身份证号</td>
								<td><input name="sfz" type="text" class="qbg" />
								</td>
								<td>户主名称</td>
								<td><input name="hzname" type="text" class="qbg" />
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
						<c:forEach items="${syfwgl}" var="syfw">
							d.add('${syfw.id}',0,'${syfw.xqdz}',"javascript:getid('${syfw.id}','${syfw.xqdz}');");
						</c:forEach>
						$("#showTree").html(d.toString());
						function getid(id,name){
							$("#popDiv").hide();
							$("#syfwid").val(id);
							$("#syfwname").val(name);
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