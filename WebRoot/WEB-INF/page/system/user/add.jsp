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
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
		$("#save").click(function(){
			var form = document.saveform;
			var username = form.username.value;
			var password = form.password.value;
			var realname = form.realname.value;
			var phone = form.phone.value;
			
			if($.trim(username)==""){
				alert("用户名不能为空！");
				form.username.focus();
				return;
			}
			
			if($.trim(password)==""){
				alert("密码不能为空！");
				form.password.focus();
				return;
			}
			
			if($.trim(realname)==""){
				alert("真实姓名不能为空！");
				form.realname.focus();
				return;
			}
			
			if($.trim(phone)==""){
				alert("电话不能为空！");
				form.phone.focus();
				return;
			}
			
			var rolename = $("input[name='rolecode']:checked").next().text();
			if(rolename == "职能部门"){
				if($("#tr_department select").val()==""){
					alert('请为"'+rolename+'"角色选择部门！');
					return;
				}
			}else if(rolename == "信息采集员"){
				if($("#tr_grid select").val()==""){
					alert('请为"'+rolename+'"角色选择网格！');
					return;
				}
			}else if(rolename == "网格管理员"){
				var ma="";
				$('input[type="checkbox"][name="gridname"]:checked').each(function() {
					ma+="1";
				})
				if(ma==""){
					alert('请为"'+rolename+'"角色选择网格！');
					return;
				}
			}else if(rolename == "专家用户"){
				if($("#tr_health select").val()==""){
					alert('请为"'+rolename+'"角色选择健康分类！');
					return;
				}
			}else if(null == rolename || rolename == ""){
				alert("请选择角色！");
				return;
			}
			
			var url = "user_isRepeat.action";
			var data = {"username":username};
			$.post(url,data,function(data){
				if(data=="1"){
					alert("用户名重复，请重新输入用户名！");
					form.username.focus();
					username = "";
				}else{
					form.submit();
				}
			},"html");
		});
		
		$("input[name='rolecode']").click(function(){
			var rolename = $(this).next().text();
			var grid1 = '<td>所属网格（<font class="fred">*</font>）</td>'
					   +'<td colspan="3">'
					   +'	<select class="bd-r" name="gridname">'
					   +'		<option value="">请选择</option>'
					   		<c:forEach items="${gridlist}" var="grid">
					   +'		<option value="${grid.name}">${grid.name}</option>'
					   		</c:forEach>
					   +'	</select>'
					   +'</td>';
			var grid2 = '<td>管理网格（<font class="fred">*</font>）</td>'
						+'<td colspan="3">'
								<c:forEach items="${gridlist}" var="grid">
						+'		<span class="inpcheck"><lable><input name="gridname" type="checkbox" '
						+'			value="${grid.name}" class="inputcheck" />${grid.name}</lable></span>'
								</c:forEach>
						+'</td>';
			if(rolename == "职能部门"){
				$("#tr_department").show();
				$("#tr_grid").hide();
				$("#tr_health").hide();
				$("#tr_department select").val("");
				$("#tr_grid select").val("");
				$("#tr_health select").val("");
				qxxz();
			}else if(rolename == "信息采集员"){
				$("#tr_department").hide();
				$("#tr_grid").html(grid1).show();
				$("#tr_health").hide();
				$("#tr_department select").val("");
				$("#tr_grid select").val("");
				$("#tr_health select").val("");
				qxxz();
			}else if(rolename == "专家用户"){
				$("#tr_department").hide();
				$("#tr_grid").hide();
				$("#tr_health").show();
				$("#tr_department select").val("");
				$("#tr_grid select").val("");
				$("#tr_health select").val("");
				qxxz();
			}else if(rolename == "网格管理员"){
				$("#tr_department").hide();
				$("#tr_grid").html(grid2).show();
				$("#tr_health").hide();
				$("#tr_department select").val("");
				$("#tr_grid select").val("");
				$("#tr_health select").val("");
				qxxz();
			}else{
				$("#tr_department").hide();
				$("#tr_grid").hide();
				$("#tr_health").hide();
				$("#tr_department select").val("");
				$("#tr_grid select").val("");
				$("#tr_health select").val("");
				qxxz();
			}
		});
		
	});
	function qxxz(){
		$('input[type="checkbox"][name="gridname"]:checked').each(function() {
		 $(this).attr("checked",false);
		 });
	}
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;系统管理&nbsp;&gt;&gt;&nbsp;用户管理&nbsp;&gt;&gt;&nbsp;添加</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="user_save.action" method="post" name="saveform">
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
								<input name="username" type="text" class="qbg" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<td>密码（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="password" type="text" class="qbg" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">详细信息</td>
						</tr>
						<tr>
							<td>真实姓名（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="realname" type="text" class="qbg" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<td>出生日期</td>
							<td>
								<input name="age" type="text" class="qbg" maxlength="25" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
							<td>性别</td>
							<td>
								<input type="radio" name="sex" class="input" value="男" checked="checked"/>&nbsp;<label>男</label>
								<input type="radio" name="sex" class="input" value="女" />&nbsp;<label>女</label>
							</td>
						</tr>
						<tr>
							<td>邮箱</td>
							<td>
								<input name="email" type="text" class="qbg" maxlength="40"/>
							</td>
							<td>电话（<font class="fred">*</font>）</td>
							<td>
								<input name="phone" type="text" class="qbg" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<td>地址</td>
							<td colspan="3"><input name="address" type="text" class="qbg" maxlength="40"/></td>
						</tr>
						<tr>
							<td>描述</td>
							<td colspan="3"><input name="ms" type="text" class="qbg" maxlength="250"/></td>
						</tr>
						<tr>
							<td>是否禁用（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input type="radio" name="state" class="input" value="启用" checked="checked"/>&nbsp;<label>启用</label>
								<input type="radio" name="state" class="input" value="禁用" />&nbsp;<label>禁用</label>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">角色选择</td>
						</tr>
						<tr>
							<td>角色（<font class="fred">*</font>）</td>
							<td colspan="3">
								<c:forEach items="${roleList }" var="role" varStatus="i">
									<input type="radio" name="rolecode" value="${role.code }" id="role${role.code }"/>&nbsp;<label for="role${role.code }">${role.name }</label>  
									<%-- <c:if test="${i.count%5 eq 0}"><br/></c:if> --%>
								</c:forEach>
							</td>
						</tr>
						<tr id="tr_department" style="display:none;">
							<td>所属部门（<font class="fred">*</font>）</td>
							<td colspan="3">
								<select class="bd-r" name="departmentid">
									<option value="">请选择</option>
									<c:forEach items="${departmentlist}" var="department">
										<option value="${department.id}">${department.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr id="tr_grid" style="display:none;">
							
						</tr>
						<tr id="tr_health" style="display:none;">
							<td>健康分类（<font class="fred">*</font>）</td>
							<td colspan="3">
								<select class="bd-r" name="healthid">
									<option value="">请选择</option>
									<c:forEach items="${jkzxfllist}" var="jkzxfl">
										<option value="${jkzxfl.id}">${jkzxfl.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<div class="bcan">
						<a href="#" id="save">
							<img src="fun/system/images/bc.gif">
						</a>
						&nbsp;
						<a href="javascript:history.go(-1);">
							<img src="fun/system/images/fh.gif">
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