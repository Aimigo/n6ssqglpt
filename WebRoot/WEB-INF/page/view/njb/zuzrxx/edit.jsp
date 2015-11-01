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
<script type="text/javascript"
		src="${pageContext.request.contextPath}/fun/njb/sex.js"></script>
<script type="text/javascript">
	$(function() {
		//验证和提交
		$("#save").click(function() {
			Validator.Validate(document.forms[0], 3);
		});
	});
	function showTree2(){
		$.ajax({
			type : 'post',
			url : 'zzrxx_getSyfwTree.action',
			cache : false,
			dataType : 'html',
			success : function(data) {
				showDialog("window", data, "出租房屋");
			},
			error : function() {
				alert("error");
				return false;
			}
		});
	}
	function getRegion2(id,name) {
		$("#ccfwid").val(id);
		$("#ccfwname").val(name);
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;租住人信息&nbsp;&gt;&gt;&nbsp;修改</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--表单内容  开始-->
				<form action="zzrxx_update.action" method="post" name="saveform">
					<div class="biaodan-all">
						<div class="biaodan-title">租住人信息</div>
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="title2">基本信息</td>
							</tr>
							<tr>
								<td width="17%">出租房屋（<font class="fred">*</font>）</td>
								<td width="33%">
									<input type="text" name="ccfwname" dataType="Require"  class="qbg" 
									readonly="readonly" msg="实有房屋不能为空。" id="ccfwname" 
									<c:if test="${!fn:contains(syfw.fwlx,'平房') }">value="${syfw.xqdz }${syfw.zhuang }(幢) ${syfw.dy }(单元) ${syfw.shi }(室)"</c:if>
											<c:if test="${fn:contains(syfw.fwlx,'平房') }">value="${syfw.xqdz }${syfw.zhuang }(号)"</c:if>
									onclick="showTree2();"/>
									<input type="hidden" name="ccfwid" id="ccfwid" value="${model.ccfwid }" dataType="Require" msg="实有房屋不能为空。"/>	
									<input type="hidden" name="id" id="id" value="${model.id }"/>	
								</td>
								<td width="17%">姓名（<font class="fred">*</font>）</td>
								<td width="33%"><input name="xm" type="text" class="qbg"  value="${model.xm }"
									dataType="LimitB" min="1" max="50" msg="姓名不能为空."
									require="true" />
								</td>
							</tr>
							<tr>
								<td>身份证号</td>
								<td><input name="sfz" type="text" class="qbg"
								  value="${model.sfz }"
								   onblur="showBirthday(this);"/>
								</td>
								<td>籍贯</td>
								<td><input name="jg" type="text" class="qbg"  value="${model.jg }"/>
								</td>
							</tr>
							<tr>
								<td>性别</td>
								<td>
								<span class="inpcheck">
								<lable for="nansheng"><input name="xb" id="nansheng" type="radio" 
								<c:if test="${model.xb=='男' }">checked=checked</c:if>
								value="男" class="inputcheck" />男</lable>
								<lable for="nvsheng"><input name="xb" type="radio" id="nvsheng" value="女" 
								<c:if test="${model.xb=='女' }">checked=checked</c:if>
								class="inputcheck" />女</lable></span>
								</td>
								<td>出生年月</td>
								<td><input name="csny" type="text" class="qbg" value="${fn:substring(model.csny,0,10) }"
								readonly="readonly" onfocus="WdatePicker();"
								dataType="Date" format="ymd" min="1700-1-1" msg="出生日期输入不合法"/>
								</td>
							</tr>
							
							<tr>
								<td>文化程度</td>
								<td><input name="whcd" type="text" class="qbg"  value="${model.whcd }"/>
								</td>
								<td>政治面貌</td>
								<td><input name="zzmm" type="text" class="qbg"  value="${model.zzmm }"/>
								</td>
							</tr>
							<tr>
								<td>暂住证</td>
								<td><input name="zzz" type="text" class="qbg"  value="${model.zzz }"/>
								</td>
								<td>户口所在地</td>
								<td><input name="hkszd" type="text" class="qbg"  value="${model.hkszd }"/>
								</td>
							</tr>
							<tr>
								<td>自身流入地点</td>
								<td><input name="lrdd" type="text" class="qbg"  value="${model.lrdd }"/>
								</td>
								<td>流入时间</td>
								<td><input name="lrsj" type="text" class="qbg"  value="${fn:substring(model.lrsj,0,10) }"
								readonly="readonly" onfocus="WdatePicker();"/>
								</td>
							</tr>
							<tr>
								<td>暂住时间</td>
								<td><input name="zzsj" type="text" class="qbg"  value="${fn:substring(model.zzsj,0,10) }"
								readonly="readonly" onfocus="WdatePicker();"/>
								</td>
								<td>婚姻状况</td>
								<td><input name="hyzk" type="text" class="qbg"  value="${model.hyzk }"/>
								</td>
							</tr>
							<tr>
								<td>避孕措施</td>
								<td><input name="bycs" type="text" class="qbg"  value="${model.bycs }"/>
								</td>
								<td>避孕时间</td>
								<td><input name="bysj" type="text" class="qbg"  value="${fn:substring(model.bysj,0,10) }"
								readonly="readonly" onfocus="WdatePicker();"/>
								</td>
							</tr>
							<tr>
								<td>健康状况</td>
								<td><input name="jjzk" type="text" class="qbg"  value="${model.jjzk }"/>
								</td>
								<td>孕育证明</td>
								<td><input name="hyzm" type="text" class="qbg"  value="${model.hyzm }"/>
								</td>
							</tr>
							<tr>
								<td>民族</td>
								<td><input name="mz" type="text" class="qbg"  value="${model.mz }"/>
								</td>
								<td>关注度</td>
								<td><input name="gzd" type="text" class="qbg"  value="${model.gzd }"/>
								</td>
							</tr>
							<tr>
								<td>电话</td>
								<td><input name="dh" type="text" class="qbg"  value="${model.dh }"/>
								</td>
								<td></td>
								<td>
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
						d.add(0, -1, '实有房屋');
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