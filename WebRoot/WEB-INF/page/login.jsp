<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<LINK href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="fun/system/css/nchjbxx.css" />
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/system/Scripts/bgstretcher.js"></script>
<script type="text/javascript">
	$(document).keydown(function(e) {
		if (e.keyCode == 13) {
			$('#submit').click();
			e.preventDefault();
		}
	});

	/*点击换验证码*/
	function changeAuthCode() {
		var _authCodeImage = document.getElementById("authCodeImage");
		_authCodeImage.src = "${pageContext.request.contextPath}/authCodeImage.jsp?timeStamp="
				+ new Date().getTime();
	}

	/*登录验证*/
	$(function() {
		$("#submit").click(
				function() {
					var username = $("#username").val();
					var password = $("#password").val();
					var code = $("#code").val();
					if ($.trim(username).length <= 0) {
						alert("登录帐号不能为空！");
						return;
					}
					if ($.trim(password).length <= 0) {
						alert("登录密码不能为空！");
						return;
					}
					if ($.trim(code).length <= 0) {
						alert("验证码不能为空！！");
						return;
					}
					$.ajax({
						type : 'post',
						url : 'login_loginUser.action',
						cache : false,
						data : {
							"username" : username,
							"password" : password,
							"code" : code
						},
						dataType : 'text',
						success : function(data) {
							if (data == '1') {
								if (jQuery("#rmbUser").attr("checked")) {
									setCookie('username', username, 'password',
											password, 365);
								} else {
									cleanCookie('username', 'password');
								}
								location.href = "login_enter.action"//登录成功跳转到相应页面
							} else {
								alert(data);
								changeAuthCode();//登录失败改变验证码
							}
						},
						error : function() {
							return;
						}
					})
				});

		checkCookie();
	});
</script>
</head>
<body>
	<!--title 开始-->
	<div class="index-titlebg">
		<div class="index-title-main">
			<div class="index-title"></div>
			<div class="pingtai-home"></div>
		</div>
	</div>
	<!--title 结束-->
	<!--主题图片 开始-->
	<div class="index-mainbg">
		<div class="index-main">
			<div class="login">
				<div class="dlk">
					<div class="yhm">
						<span>用户名：</span> <input name="username" type="text" id="username"
							class="dlkinput" maxlength="25" />
					</div>
					<div class="yhm">
						<span>密 码：</span> <input name="password" type="password"
							id="password" class="dlkinput" maxlength="25" />&nbsp;&nbsp;
						<!-- <a href="#">忘记密码？</a> -->
					</div>
					<div class="yhm">
						<span>验证码：</span> <input name="code" type="text" id="code"
							class="dlkinput" style="width:80px;" maxlength="4" /> <span>
							<img id="authCodeImage" src="authCodeImage.jsp" height="23px"
							width="75px" align="absmiddle" onclick="changeAuthCode()"
							title="点击换一张" style="cursor:hand" /> </span>
					</div>
					<div class="dl">
						<a href="#"> <img id="submit"
							src="fun/system/images/dl-btn.gif" /> </a>&nbsp;&nbsp;&nbsp;&nbsp; <input
							name="rmbUser" id="rmbUser" type="checkbox" value="" />记住账号
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--主题图片 结束-->



	<!--导航内容  开始-->
	<div class="index-nav-mainbg">
		<div class="index-nav-main box">
			<ul>
			    <li class="banner41"><a href="#">医疗卫生</a>
				</li>
				<li class="banner21"><a href="#">就业服务</a>
				</li>
				<li class="banner61"><a href="#">文化娱乐</a>
				</li>
				<li class="banner51"><a href="#">社会民生</a>
				</li>
				<li class="banner31"><a href="#">人员管理</a>
				</li>
			</ul>

		</div>
	</div>
	<!--导航内容 结束-->

	<!--版权 开始-->
	<div class="nchjbxx-footer">
		<p>
			版权所有：新疆生产建设兵团&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术支持：<a
				href="http://www.nercita.org.cn" target="_blank">北京农业信息技术研究中心</a>
		</p>
	</div>
	<!--版权 结束-->
	<script type="text/javascript">
		function getCookie(c_name) //根据分隔符每个变量的值
		{
			if (document.cookie.length > 0) {
				c_start = document.cookie.indexOf(c_name + "=");
				if (c_start != -1) {
					c_start = c_start + c_name.length + 1;
					c_end = document.cookie.indexOf("^", c_start);
					if (c_end == -1)
						c_end = document.cookie.length;
					return unescape(document.cookie.substring(c_start, c_end));
				}
			}
			return "";
		}

		function setCookie(c_name, n_value, p_name, p_value, expiredays) //设置cookie
		{
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + expiredays);
			document.cookie = c_name
					+ "="
					+ escape(n_value)
					+ "^"
					+ p_name
					+ "="
					+ escape(p_value)
					+ ((expiredays == null) ? "" : "^;expires="
							+ exdate.toGMTString());
		}

		function checkCookie() //检测cookie是否存在，如果存在则直接读取，否则创建新的cookie
		{
			var username = getCookie('username');
			var password = getCookie('password');
			if (username != null && username != "" && password != null
					&& password != "") {
				jQuery("#username").val(username);
				jQuery("#password").val(password);
				jQuery("#rmbUser").attr("checked", "checked");
			}
		}

		function cleanCookie(c_name, p_name) { //使cookie过期
			document.cookie = c_name + "=" + ";" + p_name + "="
					+ ";expires=Thu, 01-Jan-70 00:00:01 GMT";
		}
	</script>
</body>
</html>
