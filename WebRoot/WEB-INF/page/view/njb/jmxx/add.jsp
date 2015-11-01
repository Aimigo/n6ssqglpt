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
		src="${pageContext.request.contextPath}/fun/njb/sex.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="fun/javascript/treejs.js"></script>
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="fun/showDialog/showDialog.css" type="text/css"/>
<link rel="stylesheet" href="fun/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript" src="fun/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/fun/njb/jmxx.js"></script>
		<script type="text/javascript">
		var hjzt="${model.register }";
		var cp="${pageContext.request.contextPath}";
		var ryid="";
			$(function() {
				//验证和提交
				$("#save").click(function() {
					Validator.Validate(document.forms[0], 3);
				});
				
				//选择和显示特殊人员模块
				$("input[name=tsryflids]").each(function(){
					$(this).click(function(){
						var ts_id=$(this).val();
						var ts_name=$(this).parent().text();
						if($(this).prop("checked")==true){
							$.ajax({
					    		type : "post",
					    		async: false,
					    		url : "jmxx_getFlzd.action",
					    		data : {"flid":$(this).val()},
					    		dataType : "json",
					    		success : function(data) {
					    			var cursor=0;
					    			var html='<tr name="tsrq_tr'+ts_id+'">'+
										'<td colspan="4" class="title2">'+ts_name+'</td>'+
										'</tr>';
					    			for(var i=0;i<data.length;i++){
					    				if(cursor%2==0){
					    					html+='<tr name="tsrq_tr'+ts_id+'">';
					    				}
					    				
					    				if(data[i].zdlx=="datetime"){
					    					html+='<td>'+data[i].zdname+'</td>';
					    					html+='<td><input name="id'+ts_id+'_'+data[i].datazd+'" type="text" class="qbg" readonly="readonly" onfocus="WdatePicker();"/></td>';
					    				}else if(data[i].zdlx=="checkbox"){
					    					html+='<td>'+data[i].zdname+'</td>';
					    					var dt=data[i].zddata.split(",");
					    					html+='<td>';
					    					for(var j=0;j<dt.length;j++){
					    						html+='<span class="inpcheck"><lable><input name="id'+ts_id+'_'+data[i].datazd+'" type="checkbox"'+
													' value="'+dt[j]+'" class="inputcheck" />'+dt[j]+'</lable></span>';
					    					}
					    					html+='</td>';
					    				}else if(data[i].zdlx=="radio"){
					    					html+='<td>'+data[i].zdname+'</td>';
					    					var dt=data[i].zddata.split(",");
					    					html+='<td>';
					    					for(var j=0;j<dt.length;j++){
					    						html+='<span class="inpcheck"><lable><input name="id'+ts_id+'_'+data[i].datazd+'" type="radio"'+
													' value="'+dt[j]+'" class="inputcheck" />'+dt[j]+'</lable></span>';
					    					}
					    					html+='</td>';
					    				}else if(data[i].zdlx=="select"){
					    					html+='<td>'+data[i].zdname+'</td>';
					    					var dt=data[i].zddata.split(",");
					    					html+='<td><span class="inpcheck"><select name="id'+ts_id+'_'+data[i].datazd+'"/>';
					    					for(var j=0;j<dt.length;j++){
					    						html+='<option value="'+dt[j]+'">'+dt[j]+'</option>';
					    					}
					    					html+='</select></span></td>';
					    				}else if(data[i].zdlx=="image"){
					    					if(cursor%2==1){
					    						html+='<td></td><td></td></tr>';
					    						cursor++;
					    						html+='<tr name="tsrq_tr'+ts_id+'">';
					    					}
					    					html+='<td>'+data[i].zdname+'</td>';
					    					html+='<td colspan="3"><input name="id'+ts_id+'_'+data[i].datazd+'"  type="hidden" id="m_image" />'+
												'<iframe src="uploadfile_uploadfile.action?lx=img" id="_img_iframe" scrolling="no" frameborder="0" style="width: 70%;height: 185px;"></iframe>'+
												'<img src="" id="i_image" style="max-height:200px;max-width:200px;vertical-align:top;"/></td></tr>';
					    					cursor++;
					    				}else{
					    					html+='<td>'+data[i].zdname+'</td>';
					    					html+='<td><input name="id'+ts_id+'_'+data[i].datazd+'" type="text" class="qbg" /></td>';
					    				}
										
										if(cursor%2==1){
											html+='</tr>';
										}
										cursor++;
					    			}
					    			if(cursor%2==1){
					    				html+='<td></td><td></td></tr>';
					    			}
					    			$("#sel_tsrq").after(html);
					    		},error:function(){
					    			alert("请稍后再试、");
					    		}
					    	});
						}
						else{
							$(document.forms[0]).find("tr[name=tsrq_tr"+ts_id+"]").remove();
						}
					});
				});
			});
			
			//上传完毕回电函数
			function uploadfileend(type, file0, file1, file2) {
				if (type == "img") {
					$("#tpyl").attr("src", file1);
					$("#tpk").val(file1);
				}
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;人员信息管理&nbsp;&gt;&gt;&nbsp;添加</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--表单内容  开始-->
				<form action="jmxx_save.action" method="post" name="saveform" >
					<div class="biaodan-all">
						<div class="biaodan-title">人员信息</div>
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="title2">基本信息</td>
							</tr>
							<tr>
								<td width="20%">身份证号（<font class="fred">*</font>）</td>
								<td width="30%"><input name="idNumber" type="text"
									class="qbg" <%--dataType="IdCard"  ,showBirthday(this);--%>
									onblur="vaildNumber(this);"
									dataType="LimitB"  min="1" max="18" msg="身份证号输入不合法" require="true" 
									noRepeat="true" repeatTab="TblJmxx" repeatMsg="身份证号已存在" 
									/>
								</td>
								<td width="17%">姓名（<font class="fred">*</font>）</td>
								<td width="33%"><input name="name" type="text" class="qbg"
									dataType="LimitB" min="1" max="20" msg="姓名必填，并且长度不得大于20."
									require="true" />
								</td>
							</tr>
							<tr id="sctp" style="height:180px">
								<td>上传图片</td>
								<td colspan="3">
									<div style="float: left;width:70%;">
										<iframe src="uploadfile_uploadfile.action?lx=img"
											style="width:100%; height: 180px; border: 0px"
											id="_img_iframe" scrolling="no"></iframe>
											<input
											name="img" id="tpk" type="hidden" class="qbg" />
									</div>
									<div style="float: left;width:30%;">
										<img id="tpyl" width=100% height=100% src="" />
									</div>
								</td>
							</tr>
							 <tr>
								<td>户籍状态（<font class="fred">*</font>）</td>
								<td colspan="3"><label><input name="register"
										type="radio" value="户籍人口" checked="checked" class="inputcheck" />户籍人口（勾选户籍人口，需要录入对应的户籍信息和家庭信息）</label>&nbsp;&nbsp;
									<label><input name="register" type="radio"
										value="流动人口" class="inputcheck"  />流动人口（勾选流动人口，不需要录入对应的户籍信息）</label></td>
							</tr>
							<tr>
								<td>性别（<font class="fred">*</font>）</td>
								<td><label><input name="gender" type="radio" id="nansheng"
										value="男"  checked=" checked"
									class="inputcheck" />男</label>&nbsp;&nbsp;<label>
										<input name="gender" type="radio" value="女" id="nvsheng"
										class="inputcheck" />女</label>
								</td>
								<td>曾用名/别名</td>
								<td><input name="alias" type="text" class="qbg" />
								</td>
							</tr>
							<tr>
								<td>所属网格（<font style="color:red;">*</font>）</td>
								<td>
								<select class="bd-r" name="wangge" dataType="Require"  msg="所属网格不能为空">
									<option value="">请选择</option>
									<c:forEach items="${gridlist}" var="grid">
										<option value="${fn:trim(grid.name)}">${fn:trim(grid.name)}</option>
									</c:forEach>
								</select>
								</td>
								<td>是否属于其他网格</td>
								<td><label><input name="isotherwg" type="radio"
										value="0"  checked="checked"  onclick="javascript:$('#tr_otherwg').hide();"
									class="inputcheck" />否</label>&nbsp;&nbsp;<label>
										<input name="isotherwg" type="radio" value="1"
										class="inputcheck" onclick="javascript:$('#tr_otherwg').show();"/>是</label>
								</td>
							</tr>
							<tr id="tr_otherwg" style="display:none;">
								<td>其他网格</td>
								<td colspan="3">
									<c:forEach items="${allgrid}" var="grid">
										<span class="inpcheck"><lable><input name="otherwg" type="checkbox" 
											value="${fn:trim(grid.name )}" class="inputcheck" />${grid.name }</lable></span>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td>联系手机</td>
								<td><input name="cellphone" type="text" class="qbg" />
								</td>
								<td>固定电话</td>
								<td><input name="phone" type="text" class="qbg"
									/>
								</td>
							</tr>
							<tr>
								<td>出生日期</td>
								<td><input name="birthday" type="text" class="qbg"
									readonly="readonly" onfocus="WdatePicker();" 
									dataType="Date" format="ymd" min="1700-1-1" msg="出生日期输入不合法"/>
								</td>
								<td>民族</td>
								<td><input name="nation" type="text" class="qbg" />
								</td>
							</tr>
							<tr>
								<td>政治面貌</td>
								<td><input name="politics" type="text" class="qbg" />
								
								</td>
								<td>文化程度</td>
								<td><%--<input name="educational" type="text" class="qbg" />
								博士，硕士，本科，大专，中专和中技，技工学校，高中，初中，小学，文盲与半文盲
								--%><label><input name="educational" type="radio" 
										value="博士" 
										class="inputcheck" />博士</label>&nbsp;&nbsp;
									<label>
										<input name="educational" type="radio" value="硕士" 
										class="inputcheck" />硕士</label>
									<label><input name="educational" type="radio" 
										value="本科" class="inputcheck"  checked="checked"/>本科</label>&nbsp;&nbsp;
									<label>
										<input name="educational" type="radio" value="大专" 
										class="inputcheck" />大专</label>
									<label><input name="educational" type="radio" 
										value="中专和中技"  
									class="inputcheck" />中专和中技</label>&nbsp;&nbsp;
									<label>
										<input name="educational" type="radio" value="技工学校" 
										class="inputcheck" />技工学校</label>
									<label><input name="educational" type="radio" 
										value="高中"  
										class="inputcheck" />高中</label>&nbsp;&nbsp;<label>
									<label><input name="educational" type="radio" value="初中" 
										class="inputcheck" />初中</label>
									<label><input name="educational" type="radio" 
										value="小学"  
										class="inputcheck" />小学</label>&nbsp;&nbsp;
									<label>
										<input name="educational" type="radio" value="文盲与半文盲" 
										class="inputcheck" />文盲与半文盲</label>
								</td>
							</tr>
							<tr>
								<td>受教育年限</td>
								<td colspan="3"><input name="educationYears" type="text"
									class="qbg" />
								</td>
							</tr>
							<tr>
								<td>职业</td>
								<td><input name="profession" type="text" class="qbg" />
								</td>
								<td>工作单位</td>
								<td><input name="unit" type="text" class="qbg"
									dataType="Limit" max="50" />
								</td>
							</tr>
							<tr>
								<td>婚姻状况</td>
								<td><%--<input name="marital" type="text" class="qbg" />
								--%><label><input name="marital" type="radio" 
										value="已婚"  checked="checked"
									class="inputcheck" />已婚</label>&nbsp;&nbsp;<label>
										<input name="marital" type="radio" value="未婚" 
										class="inputcheck" />未婚</label>
								</td>
								<td>是否死亡</td>
								<td><lable> <input name="bedied" type="radio"
										value="是" class="inputcheck" />是</lable> <lable> <input
										name="bedied" type="radio" value="否" class="inputcheck"
										checked="checked" />否</lable>
								</td>
							</tr>
							<tr>
								<td>身高</td>
								<td><input name="height" type="text" class="ddw2"
									dataType="Double" msg="必须是数字" />cm</td>
								<td>血型</td>
								<td><%--<input name="bloodType" type="text" class="qbg" />
								--%><label><input name="bloodType" type="radio" 
										value="A"  checked="checked"
									class="inputcheck" />A</label>&nbsp;&nbsp;<label>
										<input name="bloodType" type="radio" value="B" 
										class="inputcheck" />B</label>
										<label><input name="bloodType" type="radio" 
										value="O"  
									class="inputcheck" />O</label>&nbsp;&nbsp;<label>
										<input name="bloodType" type="radio" value="AB" 
										class="inputcheck" />AB</label>
								</td>
							</tr>
							<tr>
								<td>宗教信仰</td>
								<td><input name="faith" type="text" class="qbg"
									dataType="Limit" max="50" msg="不得超过50字" />
								</td>
								<td>电子邮箱</td>
								<td><input name="email" type="text" class="qbg"
									dataType="Email" msg="格式不正确" />
								</td>
							</tr>
							<tr>
								<td>籍贯</td>
								<td colspan="3"><input name="birthplace" type="text" class="qbg"
									dataType="Limit" max="50" msg="不得超过50字" />
								</td>
								<%--<td>联系方式</td>
								<td><input name="contact" type="text" class="qbg" />
								</td>
							--%></tr>
							<tr>
								<td>户籍详细地址（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="domicileDetailedAddress"
									type="text" class="qbg" dataType="Limit" max="50"
									msg="不得超过50字且不能为空" require="true" />
								</td>
							</tr>
							<tr>
								<td>户籍类型（<font class="fred">*</font>）</td>
								<td colspan="3"><label><input name="householdType"
										type="radio" value="非农村" class="inputcheck" checked="checked"/>非农村</label>&nbsp;&nbsp;<label><input
										name="householdType" type="radio" value="农村"
										class="inputcheck" />农村</label>
										<input
										name="householdType" type="radio" value="其他"
										class="inputcheck" />其他</label>
										</td>
							</tr>
							<tr>
								<td>所属区域（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="subordinateCompany" onclick="getLd();"
									type="text" class="qbg" dataType="Limit" max="50" require="true" msg="必填"/>
								</td>
							</tr>
							<tr>
								<td>居民身份（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="residentialStatus"
									type="radio" value="居民" class="inputcheck" checked="checked"/>居民&nbsp;&nbsp;<input
									name="residentialStatus" type="radio" value="职工"
									class="inputcheck" />职工&nbsp;&nbsp;<input
									name="residentialStatus" type="radio" value="管理人员"
									class="inputcheck" />管理人员&nbsp;&nbsp;<input
									name="residentialStatus" type="radio" value="在职干部"
									class="inputcheck" />在职干部&nbsp;&nbsp;<input
									name="residentialStatus"
									 type="radio"  id="residentialStatus_id"
									class="inputcheck" />其他
									<input  type="text" class="qbg"
									 	style="width:180px;display:none;" id="residentialStatus_text"
									/>
									<script type="text/javascript">
									$(function(){
										$("input[name=residentialStatus]").each(function(index){
											if(index<=3){
												$(this).click(function(){
													$("#residentialStatus_text").hide();
												});
											}else{
												$(this).click(function(){
													$("#residentialStatus_text").show();
												});
											}
										});
										$("#residentialStatus_text").change(function(){
											$("#residentialStatus_id").val(this.value);
										});
									});
									
									</script>
									</td>
							</tr>
							<tr>
								<td>公民自身民主权利满意度（<font class="fred">*</font>）</td>
								<td colspan="1"><input name="citizenSatisfaction"
									type="text" class="ddw1" dataType="Range" require="true" min="0" max="100" msg="必须为数字1-100"/>%</td>
								<td>兵役情况</td>
								<td colspan="1"><input name="bingyi"
									type="text" class="ddw1" /></td>
							</tr>
							<tr>
								<td>流入时间</td>
								<td colspan="1"><input name="liurutime" onfocus="WdatePicker();" 
									type="text" class="ddw1" /></td>
								<td>流出时间</td>
								<td colspan="1"><input name="liuchutime" onfocus="WdatePicker();" 
									type="text" class="ddw1" /></td>
							</tr>
							<tr>
								<td>有无住房（<font class="fred">*</font>）</td>
								<td>
									<%--<input name="presenceHousing" type="text"
									class="qbg" dataType="Limit" max="50" 
									require="true" msg="不能为空且不能超过50字"/>
									
									--%>
									<label><input name="presenceHousing" type="radio" 
										value="有"  checked="checked"
									class="inputcheck" />有</label>&nbsp;&nbsp;<label>
										<input name="presenceHousing" type="radio" value="无"
										class="inputcheck" />无</label>
								</td>
								<td>行驶证</td>
								<td>
									<input name="xingshizheng" type="text"
									class="qbg"  max="50"/>
								</td>
							</tr>
							<tr>
								<td>住房地址/无房原因（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="nohouse" type="text"
									class="qbg" dataType="Limit" max="50" require="true" msg="不能为空且不能超过50字"/>
								</td>
							</tr>
							<tr>
								<td>社区（<font class="fred">*</font>）</td>
								<td colspan="3">
								<%--<input name="community" type="text"
									class="qbg" dataType="Limit" max="50" 
									require="true" msg="不能为空且不能超过50字"/>
								
									--%><label><input name="community" type="radio" 
										value="青城社区"  checked="checked"
									class="inputcheck" />青城社区</label>&nbsp;&nbsp;<label>
										<input name="community" type="radio" value="滨河社区" 
										class="inputcheck" />滨河社区</label>
								</td>
							</tr>
							<tr>
								<td>小区(路)（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="road" type="text" class="qbg"
									dataType="Limit" max="50" require="true" msg="不能为空且不能超过50字"/>
									<input type="button" value="选择" onclick="roadclick('0');"/>
								</td>
							</tr>
							<tr>
								<td>栋(号)（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="ridgepole" type="text"
									 class="qbg" />
									 <input type="button" value="选择" onclick="roadclick('1');"/>
								</td>
							</tr>
							<tr>
								<td>单元</td>
								<td colspan="3"><input name="element" type="text"
									 class="qbg" />
									 <input type="button" value="选择" onclick="roadclick('2');"/>
								</td>
							</tr>
							<tr>
								<td>室</td>
								<td colspan="3"><input name="roomnumber" type="text"
									class="qbg" />
									<input type="button" value="选择" onclick="roadclick('3');"/>
									<script type="text/javascript">
									
										function roadclick(ints){
											var name="选择"+(ints=="0"?"小区":(ints=="1"?"栋":(ints=="2"?"单元":"室")))
											var datas={
													"ints":ints,
													"road":$("input[name=road]").val(),
													"ridgepole":$("input[name=ridgepole]").val(),
													"element":$("input[name=element]").val()}
											$.ajax({
												type : 'post',
												url : 'jmxx_getXqdz.action',
												data:datas,
												cache : false,
												dataType : 'html',
												success : function(data) {
													showDialog("window", data, name);
												},
												error : function() {
													alert("error");
													return false;
												}
											});
										}
									</script>
								</td>
							</tr>
							<tr id="hjxx_bor">
								<td>备注</td>
								<td colspan="3"><input name="remark" type="text"
									dataType="Limit" max="100" class="qbg" msg="不能超过100字"/>
								</td>
							</tr>
							
							<tr>
								<td colspan="4" class="title2">家庭信息</td>
							</tr>
							<tr>
								<td>家庭编号</td>
								<td><input name="familyno" type="text" class="qbg" />
								</td>
								<td>与家长关系</td>
								<td><input name="parentsRelationship" type="text"
									class="qbg" />
								</td>
							</tr>
							<tr>
								<td>家长证件号码</td>
								<td><input name="parentsidno" type="text" class="qbg" />
								</td>
								<td>家长姓名</td>
								<td><input name="parentsname" type="text" class="qbg" />
								</td>
							</tr>
							<tr>
								<td>家长手机</td>
								<td><input name="parentscellphone" type="text" class="qbg" />
								</td>
								<td>家长电话</td>
								<td><input name="parentsphone" type="text" class="qbg" />
								</td>
							</tr>
							<tr>
								<td colspan="4" class="title2">特殊人群</td>
							</tr>
							<tr id="sel_tsrq">
								<td colspan="4">
								<c:forEach items="${spl_type}" var="psnl"
										varStatus="ps">
										<span class="inpcheck"><lable><input name="tsryflids" type="checkbox" 
											value="${psnl.id}" class="inputcheck" />${psnl.name }</lable></span>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="title2">收支(家庭)、保险、教育信息</td>
							</tr>
							<tr id="showjtxx">
								<td colspan="4"><span class="inpcheck">
									<input name="huzhu" type="checkbox" value="1" class="inputcheck" 
										onclick="showjtxx(this,'jtxx');"/>户主（家庭）</span>
									<span class="inpcheck"><input name="canbao" type="checkbox"  onchange="javascript:showjtxx(this,'cbxx');"
										value="1" class="inputcheck" />是否参保</span><span class="inpcheck"><input  onchange="javascript:showjtxx(this,'xsxx');"
										name="xuesheng" type="checkbox" value="1" class="inputcheck" />是否学生</span>
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
<jsp:include page="/WEB-INF/page/view/njb/jmxx/other.jsp"></jsp:include>

</body>
</html>