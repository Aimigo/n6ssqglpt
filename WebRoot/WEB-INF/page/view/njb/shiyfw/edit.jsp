<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fun/javascript/My97DatePicker/WdatePicker.js"></script>
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="fun/showDialog/showDialog.css" type="text/css"/>
<link rel="stylesheet" href="fun/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript" src="fun/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript">
			$(function() {
				$("#update").click(function() {
					Validator.Validate(document.forms[0], 3);
				});
			});
			function showTree(){
				$.ajax({
					type : 'post',
					url : 'syfw_getZzList.action',
					cache : false,
					dataType : 'html',
					success : function(data) {
						showDialog("window", data, "选择楼宇信息");
					},
					error : function() {
						alert("error");
						return false;
					}
				});
			}
			function getRegion(id,name) {
				$("#popDiv").hide();
				$("#lyid").val(id);
				$("#lyname").val(name);
				sd_remove();
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
								<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;实有房屋管理&nbsp;&gt;&gt;&nbsp;修改</b>
							</div>
						</div>
						<!--当前位置  结束-->

						<!--表单内容  开始-->
						<form action="syfw_update.action" method="post" name="updateform">
							<div class="biaodan-all" style="height:800px;">
								<div class="biaodan-title">实有房屋信息</div>
								<table class="biaodan" cellpadding="0" cellspacing="0">
									<tr>
										<td colspan="4" class="title2">基本信息</td>
									</tr>
									<tr id="fwxz">
										<td width="20%">房屋性质（<font class="fred">*</font>）</td>
										<td width="30%">
										<input name="id" id="id" type="hidden" class="qbg" value="${model.id }"/>
										<input name="fwlx" type="checkbox" value="公房" <c:if test="${fn:contains(model.fwlx,'公房') }">checked=checked </c:if>
													class="inputcheck" />公房
											<input name="fwlx" type="checkbox" value="私房" <c:if test="${fn:contains(model.fwlx,'私房') }">checked=checked </c:if>
													class="inputcheck" />私房
											<input name="fwlx" type="checkbox" value="楼房" <c:if test="${fn:contains(model.fwlx,'楼房') }">checked=checked </c:if>
													class="inputcheck" />楼房
											<input name="fwlx" type="checkbox" value="平房" <c:if test="${fn:contains(model.fwlx,'平房') }">checked=checked </c:if>
													class="inputcheck" id="pfinput"/>平房
											<script type="text/javascript">
											$("#pfinput").click(function(){
												if($(this).attr("checked")){
													$("#fwxz").after($("#xiaoqdz").find("tr[name=pf]"));
													$("#xiaoqdz").append($(document.forms[0]).find("tr[name=lf]"));
												}else{
													$("#fwxz").after($("#xiaoqdz").find("tr[name=lf]"));
													$("#xiaoqdz").append($(document.forms[0]).find("tr[name=pf]"));
												}
												
											});	
											$(function(){
												<c:if test="${fn:contains(model.fwlx,'平房') }">
													$("#fwxz").after($("#xiaoqdz").find("tr[name=pf]"));
													$("#xiaoqdz").append($(document.forms[0]).find("tr[name=lf]"));
												</c:if>
												<c:if test="${!fn:contains(model.fwlx,'平房') }">
													$("#fwxz").after($("#xiaoqdz").find("tr[name=lf]"));
													$("#xiaoqdz").append($(document.forms[0]).find("tr[name=pf]"));
												</c:if>
											});
											</script>
											</td>
											<td>楼宇（<font class="fred">*</font>）</td>
										<td>
										<input name="lyname" id="lyname" type="text" class="qbg" readonly="readonly" value="${model.lyname }"
											dataType="Require" msg="楼宇必选" onclick="showTree();" disabled="disabled"/>
										<input name="lyid" id="lyid" type="hidden" class="qbg" dataType="Require" msg="楼宇必选" value="${model.lyid }"/>
										</td>
									</tr>
									<tr name="lf">
										<td width="17%">小区名称（<font class="fred">*</font>）</td>
										<td width="33%"><input name="xqdz" type="text" class="qbg" value="${model.xqdz }"
											dataType="LimitB" min="1" max="50" msg="小区地址必填，并且长度不得大于20."
											require="true" />
										</td>
										<td>幢</td>
										<td><input name="zhuang" type="text" class="qbg" value="${model.zhuang }"/>
										</td>
									</tr>
									<tr name="lf">
										<td>单元</td>
										<td><input name="dy" type="text" class="qbg" value="${model.dy }"/>
										</td>
										<td>室</td>
										<td><input name="shi" type="text" class="qbg" value="${model.shi }"
											/>
										</td>
									</tr>
									<tr>
										<td>房屋居住情况（<font class="fred">*</font>）</td>
										<td><select name="fwjzqk" dataType="Require" msg="房屋居住情况不能为空。">
												<option value="自己家庭居住"  <c:if test="${model.fwjzqk eq '自己家庭居住' }">selected=selected </c:if>>自己家庭居住</option>
												<option value="出租"  <c:if test="${model.fwjzqk eq '出租' }">selected=selected </c:if>>出租</option>
												<option value="亲属居住"  <c:if test="${model.fwjzqk eq '亲属居住' }">selected=selected </c:if>>亲属居住</option>
											</select>
										</td>
										<td>房产证号（<font class="fred">*</font>）</td>
										<td><input name="fczh" type="text" class="qbg" dataType="Require" msg="房产证号不能为空。" value="${model.fczh }"/>
										</td>
									</tr>
									<tr>
										<td>产权人（<font class="fred">*</font>）</td>
										<td><input name="cqr" type="text" class="qbg" dataType="Require" msg="产权人不能为空。" value="${model.cqr }"/>
										</td>
										<td>建筑面积</td>
										<td><input name="jzmj" type="text" class="qbg" value="${model.jzmj }"/>
										</td>
									</tr>
									<tr>
										<td>人均面积</td>
										<td colspan="3"><input name="rjmj" type="text" value="${model.rjmj }"
											class="qbg" />
										</td>
									</tr>
									<tr>
								<td>建房时间</td>
								<td><input name="jfsj" type="text" class="qbg"
								 onclick="WdatePicker();" 
								 value="<fmt:formatDate value="${model.jfsj}" pattern="yyyy-MM-dd"/>"/>
								</td>
								<td>结构</td>
								<td>
								<input name="jg" type="checkbox" value="砖混"
									<c:if test="${fn:contains(model.jg,'砖混') }">checked=checked </c:if>
											class="inputcheck" />砖混
									<input name="jg" type="checkbox" value="砖木"
									<c:if test="${fn:contains(model.jg,'砖木') }">checked=checked </c:if>
											class="inputcheck" />砖木
									<input name="jg" type="checkbox" value="土木"
									<c:if test="${fn:contains(model.jg,'土木') }">checked=checked </c:if>
											class="inputcheck" />土木
									<input name="jg" type="checkbox" value="临时搭建"
									<c:if test="${fn:contains(model.jg,'临时搭建') }">checked=checked </c:if>
											class="inputcheck" />临时搭建
									<input name="jg" type="checkbox" value="多层"
									<c:if test="${fn:contains(model.jg,'多层') }">checked=checked </c:if>
											class="inputcheck" />多层
									<input name="jg" type="checkbox" value="高层"
									<c:if test="${fn:contains(model.jg,'高层') }">checked=checked </c:if>
											class="inputcheck" />高层
								</td>
							</tr>
							<tr>
								<td>是否是出租房屋</td>
								<td colspan="3"><input name="isCzfw" type="radio" value="false" checked="checked"
									class="inputcheck"/>否<input name="isCzfw" type="radio"
									<c:if test="${model.isCzfw  }"> checked="checked"</c:if>
									class="inputcheck" value="true" />是
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
			<table id="xiaoqdz" style="display:none;">
					<tr name="pf">
						<td>路（<font class="fred">*</font>）</td>
						<td><input name="xqdz" type="text" class="qbg" value="${model.xqdz }"
							dataType="LimitB" min="1" max="50" msg="路必填，并且长度不得大于20."
											require="true" />
						</td>
						<td>号（<font class="fred">*</font>）</td>
						<td><input name="zhuang" type="text" class="qbg" value="${model.zhuang }"
							dataType="LimitB" min="1" max="50" msg="号必填，并且长度不得大于20."
											require="true"/>
						</td>
					</tr>
			</table>
				<jsp:include page="/WEB-INF/page/system/index/left.jsp"></jsp:include>
				<div class="clearit"></div>
			</div>
			<!--主体内容 结束-->

			<jsp:include page="/WEB-INF/page/system/index/foot.jsp"></jsp:include>

		</body>
</html>