<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>共青团农场社区管理平台</title>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/fun/njb/Validator.js"></script>
<script src="${pageContext.request.contextPath}/fun/mobilemodify/js/vdata/form.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/fun/mobilemodify/css/vdata/form.css" type="text/css" rel="stylesheet">
<style type="text/css">
#bottom_info {position: relative;top: -3.8em;margin: 0 0.5em;padding: .8em 0;text-align: center;background-color: #18c178;color: #ffffff;border: medium hidden;border-radius: 0.1em;box-sizing: border-box;display: none;}
#bottom_info a {width: 100%;text-decoration: none;outline: none;}
#bottom_jump {position: relative;top: -3em;margin: 0 0.5em;padding: .8em 0;text-align: center;background-color: #62A9E3;color: #ffffff;border: medium hidden;border-radius: 0.1em;box-sizing: border-box;display: none;}
</style>
<script type="text/javascript">

</script>
</head>
<body>

<div id="subjects">
	<form id="main_form" action="jmxx_goOneStemp.action" method="post" onSubmit="try{Validator.Validate(document.forms[0], 3);}finally{return false;}" accept-charset="utf-8" style="padding-bottom: 4em;">
		<div class="form_ctrl page_head" id="1" title="订房" style="color: white; background-color: gray;"><h2>人员采集</h2></div>
		<div class="form_ctrl page_text" id="2" title="">
			<p>请填写人员基本信息。<span style="color:blue;">(蓝色为必填项)</span></p>
		</div>
		<div class="form_ctrl input_text" id="3" title="姓名">
			<label class="ctrl_title"><span style="color:blue;">姓名</span></label>
			<input type="text" name="name" value="" 
			placeholder="姓名必填，并且长度不得大于20" 
			dataType="LimitB"  min="1" max="20" msg="姓名必填，并且长度不得大于20." require="true" />
		</div>
		<div class="form_ctrl input_text" id="3" title="身份证">
			<label class="ctrl_title"><span style="color:blue;">身份证</span></label>
			<input type="text" name="idNumber" value="" placeholder="身份证" 
			onblur="showBirthday(this);" 
			dataType="LimitB"  min="1" max="20" msg="身份证号输入不合法" require="true" />
		</div>
		<div class="form_ctrl form_select" id="5" title="性别">
			<label class="ctrl_title"><span style="color:blue;">性别</span></label>
			<select name="select" num="2"  name="gender" dataType="Require"  >
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
			<div></div>
		</div>
		<div class="form_ctrl form_select" id="5" title="户籍状态">
			<label class="ctrl_title"><span style="color:blue;">户籍状态（勾选,需要录入对应的户籍或租房信息）</span></label>
			<select name="select" num="2" name="register" dataType="Require"  >
				<option value="户籍人口">户籍人口<span style="color:red">（勾选,需要录入对应的户籍信息）</span></option>
				<option value="流动人口">流动人口<span style="color:red">（勾选,需要录入对应的租房信息）</span></option>
			</select>
			<div></div>
		</div>
		<div class="form_ctrl input_text" id="3" title="曾用名/别名">
			<label class="ctrl_title"><span style="color:green;">曾用名/别名</span></label>
			<input type="text" name="alias" value="" 
			placeholder="长度不得大于20" 
			dataType="LimitA"  max="20" msg="曾用名/别名" />
		</div>
		<div class="form_ctrl form_select" id="5" title="所属网格">
			<label class="ctrl_title"><span style="color:blue;">所属网格</span></label>
			<select name="select" num="2" name="wangge" dataType="Require"  msg="所属网格不能为空" >
				<c:forEach items="${gridlist}" var="grid">
					<option value="${fn:trim(grid.name)}">${fn:trim(grid.name)}</option>
				</c:forEach>
			</select>
			<div></div>
		</div>
		<div class="form_ctrl form_select"  id="tr_otherwg"  title="其他网格">
			<label class="ctrl_title"><span style="color:green;">其他网格</span></label>
			<select name="select" num="2" name="otherwg"  multiple="multiple" >
				<c:forEach items="${allgrid}" var="grid">
					<option value="${fn:trim(grid.name )}">${fn:trim(grid.name)}</option>
				</c:forEach>
			</select>
			<div></div>
		</div>
		<div class="form_ctrl input_text" id="4" title="联系电话">
			<label class="ctrl_title"><span style="color:green;">联系电话</span></label>
			<input type="text" name="cellphone" value="" placeholder="请输入电话号码">
		</div>
		<div class="form_ctrl input_date" id="7" title="出生日期">
			<label class="ctrl_title"><span style="color:green;">出生日期</span></label>
			<input type="text" readonly="readonly" name="birthday" placeholder="请输入日期" onClick="editDate(event);" 
			 dataType="Date" format="ymd" min="1700-1-1" msg="出生日期输入不合法">
		</div>
		<div class="form_ctrl input_text" id="4" title="民族">
			<label class="ctrl_title"><span style="color:green;">民族</span></label>
			<input type="text" name="nation" value="" placeholder="民族">
		</div>
		<div class="form_ctrl input_text" id="4" title="政治面貌">
			<label class="ctrl_title"><span style="color:green;">政治面貌</span></label>
			<input type="text" name="politics" value="" placeholder="政治面貌">
		</div>
		<div class="form_ctrl form_select"  id="educational"  title="文化程度">
			<label class="ctrl_title"><span style="color:green;">文化程度</span></label>
			<select name="select" num="2" name="educational" >
				<option value="博士">博士</option>
				<option value="硕士">硕士</option>
				<option value="研究生">研究生</option>
				<option value="本科">本科</option>
				<option value="大专">大专</option>
				<option value="中专和中技">中专和中技</option>
				<option value="技工学校">技工学校</option>
				<option value="高中">高中</option>
				<option value="初中">初中</option>
				<option value="小学">小学</option>
				<option value="文盲与半文盲">文盲与半文盲</option>
				<option value="其他">其他</option>
			</select>
			<div></div>
		</div>
		<div class="form_ctrl input_text" id="4" title="职业">
			<label class="ctrl_title"><span style="color:green;">职业</span></label>
			<input type="text" name="profession" value="" placeholder="职业">
		</div>
		
		<div class="form_ctrl form_select" id="8" title="户籍类型">
			<label class="ctrl_title"><span style="color:green;">户籍类型</span></label>
			<select  num="1" name="householdType">
				<option value="农村">农村</option>
				<option value="非农村">非农村</option>
				<option value="其他">其他</option>
			</select>
			<div></div>
		</div>
		<div class="form_ctrl input_text" id="4" title="户籍详细地址">
			<label class="ctrl_title"><span style="color:green;">户籍详细地址</span></label>
			<input type="text" name="domicileDetailedAddress" value="" placeholder="户籍详细地址" 
			dataType="Limit" max="50" msg="不得超过50字且不能为空" />
		</div>
		<div class="form_ctrl form_select" id="8" title="居民身份">
			<label class="ctrl_title"><span style="color:green;">居民身份</span></label>
			<select  num="1" name="residentialStatus">
				<option value="居民">居民</option>
				<option value="职工">职工</option>
				<option value="管理人员">管理人员</option>
				<option value="在职干部">在职干部</option>
				<option value="其他">其他</option>
			</select>
			<div></div>
		</div>
		<div class="form_ctrl form_select" id="8" title="收支(家庭)、保险、教育信息">
			<label class="ctrl_title"><span style="color:green;">收支(家庭)、保险、教育信息</span></label>
			<input type="checkbox" name="huzhu" value="1"/><span>家庭收支<br/></span>
			<input type="checkbox"  name="canbao" value="1"/>保险
			<input type="checkbox"  name="xuesheng" value="1"/>教育
			
		</div>
		<div class="form_ctrl input_text" id="4" title="备注">
			<label class="ctrl_title"><span style="color:green;">备注</span></label>
			<textarea type="text" name="remark" value="" placeholder="备注" 
			dataType="Limit" max="100" msg="不能超过100字" ></textarea>
		</div>
		<div class="form_ctrl form_submit" id="9" title="点击按钮进入下一步">
			<label class="ctrl_title">点击按钮进入下一步</label>
			<input type="submit" name="submit" value="下一步" style="color: white; background-color: rgb(255, 108, 0);">
		</div>
	</form>

	<div id="bottom_info" style="display: none;">共<span id="collects">44</span>人填写</div>
	<div id="bottom_jump">点击查看</div>

	<div id="logo">
		<div style="margin-bottom: 5px;"></div>
<p>单位：<a href="###" >第六师共青团农场</a></p>
	</div>

</div>


</body>
</html>