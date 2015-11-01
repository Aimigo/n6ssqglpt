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
<script type="text/javascript" src="fun/javascript/treejs.js"></script>
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="fun/showDialog/showDialog.css" type="text/css"/>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript">
			$(function() {
				$("#update").click(function() {
					Validator.Validate(document.forms[0], 3);
				});
			});
			function getGrid(){
				$.ajax({
					type : 'post',
					url : 'zz_getGrid.action',
					cache : false,
					dataType : 'html',
					success : function(data) {
						showDialog("window", data, "选择连队");
					},
					error : function() {
						alert("error");
						return false;
					}
				});
			}
			function getWg(){
				$.ajax({
					type : 'post',
					url : 'zz_getWg.action',
					cache : false,
					dataType : 'html',
					success : function(data) {
						showDialog("window", data, "选网格宇信息");
					},
					error : function() {
						alert("error");
						return false;
					}
				});
			}
</script>
		<body >
			<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>

			<!--主体内容  开始-->
			<div class="nchjbxx-mainbg box">

				<!--right主体内容  开始-->
				<div class="nchjbxx-mainbgright">
					<div class="nchjbxx-main">

						<!--当前位置  开始-->
						<div class="sitebg">
							<div class="site">
								<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;楼宇管理&nbsp;&gt;&gt;&nbsp;修改</b>
							</div>
						</div>
						<!--当前位置  结束-->

						<!--表单内容  开始-->
						<form action="zz_update.action" method="post" name="updateform">
							<div class="biaodan-all" style="height:800px;">
								<div class="biaodan-title">楼宇管理</div>
								<table class="biaodan" cellpadding="0" cellspacing="0">
									<colgroup>
										<col style="width:10%;" />
										<col style="width:40%;" />
										<col style="width:10%;" />
										<col style="width:40%;" />
									</colgroup>
									<tr>
										<td colspan="4" class="title2">楼宇管理</td>
									</tr>
									<tr>
										<td>房屋性质（<font class="fred">*</font>）</td>
										<td><input name="grade" type="text"
											dataType="LimitB"  min="1" max="20" msg="输入不正确" require="true" 
											value="${fn:trim(model.grade) }" class="qbg" /> 
											<input name="objectid" type="hidden" value="${model.objectid }" class="qbg"
											/>  </td>
										<td>名称（<font class="fred">*</font>）</td>
										<td><input name="name" type="text"
											dataType="LimitB"  min="1" max="20" msg="名称不正确" require="true" 
											value="${fn:trim(model.name) }" class="qbg" maxlength="25" />
										</td>
									</tr>
									<tr>
										<td>所属连队（<font class="fred">*</font>）</td>
										<td><input name="owner" id="owner" type="text" onclick="getGrid()"
											dataType="LimitB"  min="1" max="200" msg="输入不正确" require="true" 
											value="${fn:trim(model.owner) }" class="qbg" maxlength="25" /> 
											</td>
										<td>网格位置（<font class="fred">*</font>）</td>
										<td><input name="grid" type="text" id="grid" onclick="getWg()"
											dataType="LimitB"  min="1" max="200" msg="输入不正确" require="true" 
											readonly="readonly"
											value="${fn:trim(model.grid) }" class="qbg" /> </td>
									</tr>
									<tr>
										<td>房屋类型</td>
										<td><input name="type" type="text"
											value="${fn:trim(model.type) }" class="qbg" /> 
											 </td>
										<td>街道</td>
										<td><input name="crop" type="text"
											value="${fn:trim(model.crop) }" class="qbg" maxlength="25" />
										</td>
									</tr>
									<tr>
										<td>面积</td>
										<td><input name="area" type="text" style="width:90%"
											dataType="Double" msg="必须是数字"
											value="${fn:trim(model.area) }" class="qbg" /><span style="display: inline;">㎡</span>
											 </td>
										<td>社区</td>
										<td><input name="shequ" type="text"
											value="${fn:trim(model.shequ) }" class="qbg" maxlength="25" />
										</td>
									</tr>
									<tr>
										<td>小区</td>
										<td><input name="xiaoqu" type="text"
											value="${fn:trim(model.xiaoqu) }" class="qbg" /> 
											 </td>
										<td>栋</td>
										<td><input name="dong" type="text"
											value="${fn:trim(model.dong) }" class="qbg" maxlength="25" />
										</td>
									</tr>
								</table>
								<div class="bcan">
									<a href="javascript:void(0);" id="update"> <img
										src="fun/system/images/bc.gif">
									</a> &nbsp; <a href="javascript:history.go(-1);"> <img
										src="fun/system/images/fh.gif">
									</a>
								</div>
							</div>
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