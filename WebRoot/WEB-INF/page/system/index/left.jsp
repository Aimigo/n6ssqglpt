<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<link rel="stylesheet" href="fun/showDialog/showDialog.css" type="text/css"/>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>

<!--left主体内容  开始-->
<div class="nchjbxx-mainbgleft">
	<!--登录用户基本信息  开始-->
	<div class="nchjbxx-usermain" style="height: 80px;">
		<div class="nchjbxx-user">
			<span class="nchjbxx-user-touxiang"><img
				src="fun/system/images/nchjbxx04.png" />
			</span> 
			<span class="nchjbxx-user-mingcheng">${sessionScope.user.realname }，你好！</span>
		</div>
		<span class="nchjbxx-user-more"  style="float:right;">
			<a href="javascript:showEditPwd();"><img src="fun/system/images/nchjbxx05.png" /><span class="nchjbxx-user-mingcheng">&nbsp;修改密码</span></a>&nbsp;
			<a href="login_clearUser.action"><img src="fun/system/images/nchjbxx05.png" /><span class="nchjbxx-user-mingcheng">&nbsp;退出</span></a>&nbsp;&nbsp;
		</span>
	</div>
	<!--登录用户基本信息  结束-->
	<!--菜单伸缩js  开始-->
	<script language="javascript" type="text/javascript" id="clientEventHandlersJS">
	<!--
		var number = ${fn:length(sessionScope.functionMap[sessionScope.function_one.code]) };

		function LMYC() {
			for (i = 1; i <= number; i++) {
				$("#LM"+i).hide();
			}
		}

		function ShowFLT(i) {
			if ($("#LM"+i).is(":hidden")) {
				LMYC();
				$("#LM"+i).show();
			} else {
				$("#LM"+i).hide();
			}
		}
		
		function showEditPwd(){
			var html = new Array();
			html.push('<div class="biaodan-all">');
			html.push('	<form action="login_editPwd.action" method="post" name="editPwdForm" id="editPwdForm">');
			html.push('		<table class="biaodan" cellpadding="0" cellspacing="0" style="width: 100%;">');
			html.push('			<colgroup>');
			html.push('				<col style="width:30%;"/>');
			html.push('				<col style="width:;"/>');
			html.push('			</colgroup>');
			html.push('			<tr>');
			html.push('				<td>原始密码</td>');
			html.push('				<td>');
			html.push('					<input name="oldpwd" type="password" class="qbg" maxlength="25"/>');
			html.push('				</td>');
			html.push('			</tr>');
			html.push('			<tr>');
			html.push('				<td>新 密 码</td>');
			html.push('				<td>');
			html.push('					<input name="newpwd" type="password" class="qbg" maxlength="25"/>');
			html.push('				</td>');
			html.push('			</tr>');
			html.push('			<tr>');
			html.push('				<td>重复新密码</td>');
			html.push('				<td>');
			html.push('					<input name="renewpwd" type="password" class="qbg" maxlength="25"/>');
			html.push('				</td>');
			html.push('			</tr>');
			html.push('		</table>');
			html.push('		<div class="bcan">');
			html.push('			<a href="javascript:editPassword();">');
			html.push('				<img src="fun/system/images/bc.gif">');
			html.push('			</a>');
			html.push('		</div>');
			html.push('	</form>');
			html.push('</div>');
			showDialog("window", html.join(''), "修改密码");
		}
		
		function editPassword(){
			var editform = document.editPwdForm;
			var oldpwd = editform.oldpwd.value;
			var newpwd = editform.newpwd.value;
			var renewpwd = editform.renewpwd.value;
			
			if($.trim(oldpwd)==""){
				alert("原始密码不能为空！");
				editform.oldpwd.focus();
				return;
			}
			
			if($.trim(newpwd)==""){
				alert("新密码不能为空！");
				editform.newpwd.focus();
				return;
			}
			
			if($.trim(renewpwd)==""){
				alert("重复新密码不能为空！");
				editform.renewpwd.focus();
				return;
			}
			
			if(renewpwd!=newpwd){
				alert("俩次输入的密码不一致！");
				editform.renewpwd.focus();
				return;
			}
			
			var url = "login_editPwd.action";
			var data = $("#editPwdForm").serialize();
			$.post(url,data,function(d){
				if(d=="1"){
					alert("原始密码输入错误，请重新输入！");
					editform.oldpwd.focus();
					oldpwd = "";
				}else if(d=="2"){
					alert("俩次输入的密码不一致，请重新输入！");
				}else if(d=="-1"){
					alert("输入参数有误，请重新输入！");
				}else{
					alert("密码修改成功，您需要退出系统重新登录！");
					location.href = "login.jsp";
				}
			},"html");
		}
	//-->
	</script>
	<!--菜单伸缩js  结束-->
	<ul class="yiji">
		<c:forEach items="${sessionScope.functionMap[sessionScope.function_one.code] }" var="function" varStatus="i">
			<c:choose>
				<c:when test="${function.url eq null or function.url eq '' }">
					<li class="yiji-<c:if test="${sessionScope.function_two.code ne function.code }">no</c:if>selected">
						<img src="fun/system/images/icon-ndljy<c:if test="${sessionScope.function_two.code eq function.code }">-</c:if>.png" />
						<a onclick="javascript:ShowFLT(${i.count})" href="javascript:void(null)">${function.name }</a>
					</li>
					<%-- 三级菜单 --%>
					<li class="erji" style="display: none;" id="LM${i.count}">
						<ul class="erji-ul">
							<c:forEach items="${sessionScope.functionMap[function.code] }" var="fun">
								<li class="erji-<c:if test="${sessionScope.function_three.code ne fun.code }">no</c:if>selected">
									<a href="${fun.url }?functioncode=${sessionScope.function_one.code}&functioncode2=${function.code}&functioncode3=${fun.code}">${fun.name }</a>
								</li>
							</c:forEach>
						</ul>
					</li>
					<c:if test="${sessionScope.function_two.code eq function.code }">
						<script type="text/javascript">ShowFLT(${i.count});</script>
					</c:if>
				</c:when>
				<c:otherwise>
					<li class="yiji-<c:if test="${asessionScope.function_two.code ne function.code }">no</c:if>selected">
						<img src="fun/system/images/icon-ndljy<c:if test="${sessionScope.function_two.code eq function.code}">-</c:if>.png" />
						<a href="${function.url}?functioncode=${sessionScope.function_one.code}&functioncode2=${function.code}">${function.name }</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
</div>
<!--left主体内容 结束-->