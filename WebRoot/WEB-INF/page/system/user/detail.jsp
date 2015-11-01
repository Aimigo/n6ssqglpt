<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
		var rolename = '${urole.name}';
		if(rolename == "职能部门"){
			$("#tr_department").show();
		}else if(rolename == "社区管理员" ||rolename == "网格管理员" || rolename == "信息采集员"){
			$("#tr_grid").show();
		}else if(rolename == "专家用户"){
			$("#tr_health").show();
		}
	});
</script>
<body onload="MM_preloadImages('fun/system/images/nchjbxx-nav01-.png')">
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>
	
	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;系统管理&nbsp;&gt;&gt;&nbsp;用户管理&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<input type="hidden" name="oldusername" value="${tblUser.username }"/>
				<input type="hidden" name="id" value="${tblUser.id }"/>
				<input type="hidden" name="usercode" value="${tblUser.usercode }"/>
				<div class="biaodan-all" style="height:800px;">
					<div class="biaodan-title">用户管理</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">账户信息</td>
						</tr>
						<tr>
							<td>用户名（<font class="fred">*</font>）</td>
							<td colspan="3">
								${tblUser.username }
							</td>
						</tr>
						<tr>
							<td>密码（<font class="fred">*</font>）</td>
							<td colspan="3">
								${tblUser.password }
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">详细信息</td>
						</tr>
						<tr>
							<td>真实姓名（<font class="fred">*</font>）</td>
							<td colspan="3">
								${tblUser.realname }
							</td>
						</tr>
						<tr>
							<td>出生日期</td>
							<td>
								<fmt:formatDate value="${tblUser.age }" pattern="yyyy-MM-dd"/>
							</td>
							<td>性别</td>
							<td>
								${tblUser.sex}
							</td>
						</tr>
						<tr>
							<td>邮箱</td>
							<td>
								${tblUser.email}
							</td>
							<td>电话</td>
							<td>
								${tblUser.phone}
							</td>
						</tr>
						<tr>
							<td>地址</td>
							<td colspan="3">${tblUser.address }</td>
						</tr>
						<tr>
							<td>描述</td>
							<td colspan="3">${tblUser.ms }</td>
						</tr>
						<tr>
							<td>是否禁用（<font class="fred">*</font>）</td>
							<td colspan="3">
								${tblUser.state}
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">角色选择</td>
						</tr>
						<tr>
							<td>角色（<font class="fred">*</font>）</td>
							<td colspan="3">
								${urole.name }
							</td>
						</tr>
						<tr id="tr_department" style="display:none;">
							<td>所属部门（<font class="fred">*</font>）</td>
							<td colspan="3">
								${tblUser.departmentname }
							</td>
						</tr>
						<tr id="tr_grid" style="display:none;">
							<td>所属网格（<font class="fred">*</font>）</td>
							<td colspan="3">
								${tblUser.gridname}
							</td>
						</tr>
						<tr id="tr_health" style="display:none;">
							<td>健康分类（<font class="fred">*</font>）</td>
							<td colspan="3">
								${tblUser.healthname }
							</td>
						</tr>
					</table>
					<div class="bcan">
						<a href="javascript:history.go(-1);">
							<img src="fun/system/images/fh.gif">
						</a>
					</div>
				</div>
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