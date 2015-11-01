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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;流出人口信息&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--表单内容  开始-->
				<form action="lcrk_update.action" method="post" name="saveform">
					<div class="biaodan-all">
						<div class="biaodan-title">流出人口</div>
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="title2">基本信息</td>
							</tr>
							<tr>
								<td width="17%">户主（<font class="fred">*</font>）</td>
								<td width="33%">
									${model.huzhu }	
								</td>
								<td width="17%">子女</td>
								<td width="33%">${model.zinv }
								</td>
							</tr>
							<tr>
								<td>年龄</td>
								<td>
								${model.nianling }
								</td>
								<td>流出时间</td>
								<td>${fn:substring(model.liuchushijian,0,10) }
								</td>
							</tr>
							<tr>
								<td>单位</td>
								<td>${model.danwei }
								</td>
								<td>电话</td>
								<td>${model.dianhua }
								</td>
							</tr>
							<tr>
								<td>备注</td>
								<td colspan="3">${model.beizhu }
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
</body>
</html>