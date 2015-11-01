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
var cp="${pageContext.request.contextPath}";
var ryid="${model.id }";
			$(function() {
				//验证和提交
				$("#save").click(function() {
					Validator.Validate(document.forms[0], 3);
				});
				if("${model.register}"=="流动人口"){
					$("#hide_hjxx").append($(document.forms[0]).find("tr[name=hjgxx_tr]"));
				}else{
					$("#hjxx_bor").after($("#hide_hjxx").find("tr[name=hjgxx_tr]"));
				}
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
					    					html+='<td colspan="3"><input name="id'+ts_id+'_'+data[i].datazd+'"  type="hidden" id="m_image" value=""/>'+
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
			var hjzt="${model.register }";
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;人员信息管理&nbsp;&gt;&gt;&nbsp;修改</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--表单内容  开始-->
				<form action="jmxx_update.action" method="post" name="saveform">
					<div class="biaodan-all">
						<div class="biaodan-title">人员信息</div>
						<style>
							.mcart a{
							}
							.mcart a:hover {
								text-decoration:underline;
							}
						</style><br/>
						<div style="font-size:18px;margin-right:150px;float:right;" class="mcart">
							
							<c:forEach items="${tjcy}" var="family" varStatus="n">
								<c:if test="${n.index ne 0 }">
								<span>|</span>
								</c:if>
								<c:if test="${model.id eq family.id}" >
									<a style="text-decoration:underline;font-weight:bold;color:green;">${model.name }</a>
								</c:if>
								<c:if test="${model.id ne family.id}" >
								<a href="jmxx_edit.action?id=${family.id }" >${family.name }</a></c:if>
								
							</c:forEach>
						</div>
						
							<br/>
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="title2">基本信息</td>
							</tr>
							<tr>
								<td width="20%">身份证号（<font class="fred">*</font>）</td>
								<td width="30%">
								<input name="id" type="hidden" value="${model.id }" /><input name="idNumber" type="text" 
									class="qbg" value="${model.idNumber }"<%--dataType="IdCard"--%> 
									onblur="vaildNumber(this,'${model.id }');"
									dataType="LimitB"  min="1" max="18" msg="身份证号输入不合法" require="true"
									noRepeat="true" repeatTab="TblJmxx" repeatId="${model.id }" repeatMsg="身份证号已存在" />
								</td>
								<td width="17%">姓名（<font class="fred">*</font>）</td>
								<td width="33%"><input name="name" type="text" class="qbg" value="${model.name }"
									dataType="LimitB" min="1" max="20" msg="姓名字数必填，并且长度不得大于20."
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
											name="img" id="tpk" type="hidden" class="qbg"
											 value="${model.img }"/>
									</div>
									<div style="float: left;width:30%;">
										<img id="tpyl" width=100% height=100% src="${model.img }" />
									</div>
								</td>
							</tr>
							 <tr>
								<td>户籍状态（<font class="fred">*</font>）</td>
								<td colspan="3"><label><input name="register"
										type="radio" value="户籍人口" 
										<c:if test="${fn:contains(model.register,'户籍人口') }">checked="checked" </c:if>class="inputcheck" 
										/>户籍人口（勾选户籍人口，需要录入对应的户籍信息和家庭信息）</label>&nbsp;&nbsp;
									<label><input name="register" type="radio"
										value="流动人口" class="inputcheck"  
										<c:if test="${fn:contains(model.register,'流动人口') }">checked="checked" </c:if>
										/>流动人口（勾选流动人口，不需要录入对应的户籍信息）</label></td>
							</tr>
							<tr>
								<td>性别（<font class="fred">*</font>）</td>
								<td><label><input name="gender" type="radio"
										value="男"  id="nansheng"
										<c:if test="${fn:contains(model.gender,'男') }">checked="checked" </c:if>
									class="inputcheck" />男</label>&nbsp;&nbsp;<label>
										<input name="gender" type="radio" value="女"
										 id="nvsheng"
										<c:if test="${fn:contains(model.gender,'女') }">checked="checked" </c:if>
										class="inputcheck" />女</label>
								</td>
								<td>曾用名/别名</td>
								<td><input name="alias" type="text" class="qbg" value="${model.alias }"/>
								</td>
							</tr>
							<tr>
								<td>所属网格（<font class="fred">*</font>）</td>
								<td>
								<select class="bd-r" name="wangge" dataType="Require"  msg="所属网格不能为空">
									<option value="">请选择</option>
									<c:forEach items="${gridlist}" var="grid">
										<option value="${fn:trim(grid.name)}" 
										<c:if test="${fn:contains(model.wangge,fn:trim(grid.name)) }"> selected="selected" </c:if>>
										${fn:trim(grid.name)}</option>
									</c:forEach>
								</select>
								</td>
								<td>是否属于其他网格</td>
								<td><label><input name="isotherwg" type="radio" value="0"  
								 <c:if test="${model.isotherwg eq 0}">checked="checked" </c:if>
									checked="checked"  onclick="javascript:$('#tr_otherwg').hide();"
									class="inputcheck" />否</label>&nbsp;&nbsp;<label>
										<input name="isotherwg" type="radio" value="1"
										<c:if test="${model.isotherwg eq 1}">checked="checked" </c:if>
										class="inputcheck" onclick="javascript:$('#tr_otherwg').show();"/>是</label>
								</td>
							</tr>
							<tr id="tr_otherwg" <c:if test="${model.isotherwg eq 0 }">style="display:none;"</c:if>>
								<td>其他网格</td>
								<td colspan="3">
									<c:forEach items="${allgrid}" var="grid">
										<span class="inpcheck"><lable><input name="otherwg" type="checkbox" 
										<c:if test="${fn:contains(model.otherwg ,fn:trim(grid.name)) }"> checked="checked" </c:if>
											value="${fn:trim(grid.name )}" class="inputcheck" />${grid.name }</lable></span>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td>联系手机</td>
								<td><input name="cellphone" type="text" class="qbg" value="${model.cellphone }"/>
								</td>
								<td>固定电话</td>
								<td><input name="phone" type="text" class="qbg" value="${model.phone }"
									/>
								</td>
							</tr>
							<tr>
								<td>出生日期</td>
								<td><input name="birthday" type="text" class="qbg" value="${fn:substring(model.birthday,0,10) }"
									readonly="readonly" onfocus="WdatePicker();" 
									dataType="Date" format="ymd" min="1700-1-1" msg="出生日期输入不合法"/>
								</td>
								<td>民族</td>
								<td><input name="nation" type="text" class="qbg" value="${model.nation }"/>
								</td>
							</tr>
							<tr>
								<td>政治面貌</td>
								<td><input name="politics" type="text" class="qbg" value="${model.politics }"/>
								</td>
								<td>文化程度</td>
								<td><%--<input name="educational" type="text" class="qbg" value="${model.educational }"/>
								--%><label><input name="educational" type="radio" 
										value="博士" <c:if test="${model.educational=='博士' }">checked="checked"</c:if>
										class="inputcheck" />博士</label>&nbsp;&nbsp;
									<label>
										<input name="educational" type="radio" value="硕士" 
										 <c:if test="${model.educational=='硕士' }">checked="checked"</c:if>
										class="inputcheck" />硕士</label>
									<label><input name="educational" type="radio" 
										<c:if test="${model.educational=='本科' }">checked="checked"</c:if>
										value="本科" class="inputcheck"  checked="checked"/>本科</label>&nbsp;&nbsp;
									<label>
										<input name="educational" type="radio" value="大专" 
										<c:if test="${model.educational=='大专' }">checked="checked"</c:if>
										class="inputcheck" />大专</label>
									<label><input name="educational" type="radio" 
										value="中专和中技"  
										<c:if test="${model.educational=='中专和中技' }">checked="checked"</c:if>
									class="inputcheck" />中专和中技</label>&nbsp;&nbsp;
									<label>
										<input name="educational" type="radio" value="技工学校" 
										<c:if test="${model.educational=='技工学校' }">checked="checked"</c:if>
										class="inputcheck" />技工学校</label>
									<label><input name="educational" type="radio" 
										value="高中"  <c:if test="${model.educational=='高中' }">checked="checked"</c:if>
										class="inputcheck" />高中</label>&nbsp;&nbsp;<label>
									<label><input name="educational" type="radio" value="初中" 
									<c:if test="${model.educational=='初中' }">checked="checked"</c:if>
										class="inputcheck" />初中</label>
									<label><input name="educational" type="radio" 
										value="小学"  <c:if test="${model.educational=='小学' }">checked="checked"</c:if>
										class="inputcheck" />小学</label>&nbsp;&nbsp;
									<label>
										<input name="educational" type="radio" value="文盲与半文盲" 
										<c:if test="${model.educational=='文盲与半文盲' }">checked="checked"</c:if>
										class="inputcheck" />文盲与半文盲</label>
								
								</td>
							</tr>
							<tr>
								<td>受教育年限</td>
								<td colspan="3"><input name="educationYears" type="text"
									class="qbg"  value="${model.educationYears }"/>
								</td>
							</tr>
							<tr>
								<td>职业</td>
								<td><input name="profession" type="text" class="qbg" value="${model.profession }" />
								</td>
								<td>工作单位</td>
								<td><input name="unit" type="text" class="qbg" value="${model.unit }"
									dataType="Limit" max="50" />
								</td>
							</tr>
							<tr>
								<td>婚姻状况</td>
								<td><%--<input name="marital" type="text" class="qbg" value="${model.marital }"/>
								
								--%><label><input name="marital" type="radio" 
									<c:if test="${model.marital=='已婚' }">checked="checked"</c:if>
										value="已婚"  checked="checked"
									class="inputcheck" />已婚</label>&nbsp;&nbsp;<label>
									<input name="marital" type="radio" value="未婚" 
										<c:if test="${model.marital=='未婚' }">checked="checked"</c:if>
										class="inputcheck" />未婚</label>
								</td>
								<td>是否死亡</td>
								<td><lable> <input name="bedied" type="radio" 
									<c:if test="${model.bedied eq '是' }"> checked="checked" </c:if>
										value="是" class="inputcheck" />是</lable> <lable> <input
										name="bedied" type="radio" value="否" class="inputcheck"
										<c:if test="${model.bedied eq '否' }"> checked="checked" </c:if>
										checked="checked" />否</lable>
								</td>
							</tr>
							<tr>
								<td>身高</td>
								<td><input name="height" type="text" class="ddw2" value="${model.height }"
									dataType="Double" msg="必须是数字" />cm</td>
								<td>血型</td>
								<td><%--<input name="bloodType" type="text" class="qbg" value="${model.bloodType }"/>
								
								--%><label><input name="bloodType" type="radio" 
								<c:if test="${model.bloodType=='A' }">checked="checked"</c:if>
										value="A"  checked="checked"
									class="inputcheck" />A</label>&nbsp;&nbsp;<label>
										<input name="bloodType" type="radio" value="B" 
										<c:if test="${model.bloodType=='B' }">checked="checked"</c:if>
										class="inputcheck" />B</label>
										<label><input name="bloodType" type="radio" 
										value="O"  <c:if test="${model.bloodType=='O' }">checked="checked"</c:if>
									class="inputcheck" />O</label>&nbsp;&nbsp;<label>
										<input name="bloodType" type="radio" value="AB" 
										<c:if test="${model.bloodType=='AB' }">checked="checked"</c:if>
										class="inputcheck" />AB</label>
								</td>
							</tr>
							<tr>
								<td>宗教信仰</td>
								<td><input name="faith" type="text" class="qbg" value="${model.faith }"
									dataType="Limit" max="50" msg="不得超过50字" />
								</td>
								<td>电子邮箱</td>
								<td><input name="email" type="text" class="qbg"
									dataType="Email" msg="格式不正确" value="${model.email }"/>
								</td>
							</tr>
							<tr>
								<td>籍贯</td>
								<td colspan="3"><input name="birthplace" type="text" class="qbg"
									dataType="Limit" max="50" msg="不得超过50字" value="${model.birthplace }"/>
								</td>
								<%--<td>联系方式</td>
								<td><input name="contact" type="text" class="qbg" value="${model.contact }"/>
								</td>
							--%></tr>
							<tr>
								<td>户籍详细地址（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="domicileDetailedAddress"
									type="text" class="qbg" dataType="Limit" max="50"
									value="${model.domicileDetailedAddress}"
									msg="不得超过50字且不能为空" require="true" />
								</td>
							</tr>
							<tr>
								<td>户籍类型（<font class="fred">*</font>）</td>
								<td colspan="3"><label><input name="householdType"
										<c:if test="${model.householdType eq '非农村' }"> checked="checked" </c:if>
										type="radio" value="非农村" class="inputcheck" checked="checked"/>非农村</label>&nbsp;&nbsp;<label>
										<input
										name="householdType" type="radio" value="农村"
										<c:if test="${model.householdType eq '农村' }"> checked="checked" </c:if>
										class="inputcheck" />农村</label>
										<input
										name="householdType" type="radio" value="其他"
										<c:if test="${model.householdType eq '其他' }"> checked="checked" </c:if>
										class="inputcheck" />其他</label>
										</td>
							</tr>
							<tr>
								<td>所属区域（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="subordinateCompany"
									value="${model.subordinateCompany }" onclick="getLd()"
									type="text" class="qbg" dataType="Limit" max="50" require="true" msg="必填"/>
								</td>
							</tr>
							<tr>
								<td>居民身份（<font class="fred">*</font>）</td>
								<td colspan="3">
									<input name="residentialStatus" 
									<c:if test="${model.residentialStatus eq '居民' }"> checked="checked" </c:if>
									type="radio" value="居民" class="inputcheck" />居民&nbsp;&nbsp;
									<input <c:if test="${model.residentialStatus eq '职工' }"> checked="checked" </c:if>
										name="residentialStatus" type="radio" value="职工" class="inputcheck" />职工&nbsp;&nbsp;
									<input name="residentialStatus" type="radio" value="管理人员"
									<c:if test="${model.residentialStatus eq '管理人员' }"> checked="checked" </c:if>
									class="inputcheck" />管理人员&nbsp;&nbsp;
									<input
									name="residentialStatus" type="radio" value="在职干部"
									<c:if test="${model.residentialStatus eq '在职干部' }"> checked="checked" </c:if>
									class="inputcheck" />在职干部&nbsp;&nbsp;
									<input type="radio" name="residentialStatus" value="" 
									id="residentialStatus_id"
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
											var sta="${model.residentialStatus}";
											if(sta!="居民"&&sta!="职工"&&sta!="管理人员"&&sta!="在职干部"){
												$("#residentialStatus_id").attr("checked",true);
												$("#residentialStatus_id").val(sta);
												$("#residentialStatus_text").val(sta);
												$("#residentialStatus_text").show();
											}
										});
										
									</script>
									</td>
							</tr>
							<tr>
								<td>公民自身民主权利满意度（<font class="fred">*</font>）</td>
								<td colspan="1"><input name="citizenSatisfaction" value="${model.citizenSatisfaction }"
									type="text" class="ddw1" 
									dataType="Range" require="true" min="0" max="100" msg="必须为数字1-100"/>%</td>
								<td>兵役情况</td>
								<td colspan="1"><input name="bingyi"
									type="text" class="ddw1" value="${model.bingyi }"/></td>
							</tr>
							<tr>
								<td>流入时间</td>
								<td colspan="1"><input name="liurutime" onfocus="WdatePicker();" 
									type="text" class="ddw1" value="${fn:substring(model.liurutime,0,10) }"/></td>
								<td>流出时间</td>
								<td colspan="1"><input name="liuchutime" onfocus="WdatePicker();" 
									type="text" class="ddw1" value="${fn:substring(model.liuchutime,0,10) }"/></td>
							</tr>
							<tr>
								<td>有无住房（<font class="fred">*</font>）</td>
								<td colspan="1">
									<label><input name="presenceHousing" type="radio" 
										value="有"  
										<c:if test="${model.presenceHousing eq '有' }">checked="checked"</c:if>
									class="inputcheck" />有</label>&nbsp;&nbsp;<label>
										<input name="presenceHousing" type="radio" value="无"
										<c:if test="${model.presenceHousing eq '无' }">checked="checked"</c:if>
										class="inputcheck" />无</label>
								</td>
								<td>行驶证</td>
								<td>
									<input name="xingshizheng" type="text" value="${model.xingshizheng }"
									class="qbg" />
								</td>
							</tr>
							<tr>
								<td>住房地址/无房原因（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="nohouse" type="text"
									value="${model.nohouse }"
									class="qbg" dataType="Limit" max="50" require="true" msg="不能为空且不能超过50字"/>
								</td>
							</tr>
							<tr>
								<td>社区（<font class="fred">*</font>）</td>
								<td colspan="3"><%--<input name="community" type="text"
									value="${model.community }"
									class="qbg" dataType="Limit" max="50" require="true" msg="不能为空且不能超过50字"/>
									--%>
									<label><input name="community" type="radio" 
									<c:if test="${model.community eq '青晨社区' or model.community eq '青城社区'  }"> checked="checked" </c:if>
										value="青城社区"  checked="checked"
									class="inputcheck" />青城社区</label>&nbsp;&nbsp;<label>
										<input name="community" type="radio" value="滨河社区" 
										<c:if test="${model.community eq '滨河社区' or model.community eq '宾河社区' }"> checked="checked" </c:if>
										class="inputcheck" />滨河社区</label>
								</td>
							</tr>
							<tr>
								<td>小区(路)（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="road" type="text" class="qbg"
									value="${model.road }"
									dataType="Limit" max="50" require="true" msg="不能为空且不能超过50字"/>
									<input type="button" value="选择" onclick="roadclick('0');"/>
								</td>
							</tr>
							<tr>
								<td>栋(排)</td>
								<td colspan="3"><input name="ridgepole" type="text" value="${model.ridgepole }"
									 class="qbg" dataType="Limit" max="50" require="true" msg="不能为空且不能超过50字"/>
									 <input type="button" value="选择" onclick="roadclick('1');"/>
								</td>
							</tr>
							<tr>
								<td>单元</td>
								<td colspan="3"><input name="element" type="text" value="${model.element }"
									 class="qbg" dataType="Limit" max="50" require="true" msg="不能为空且不能超过50字"/>
									 <input type="button" value="选择" onclick="roadclick('2');"/>
								</td>
							</tr>
							<tr>
								<td>室(号)（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="roomnumber" type="text" value="${model.roomnumber}"
									class="qbg" dataType="Limit" max="50" require="true" msg="不能为空且不能超过50字"/>
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
								<td colspan="3"><input name="remark" type="text" value="${model.remark }"
									dataType="Limit" max="100" class="qbg" msg="不能超过100字"/>
									<input name="createtime" type="hidden" value="${model.createtime}"/>
								</td>
							</tr>
							
							<tr>
								<td colspan="4" class="title2">家庭信息</td>
							</tr>
							<tr>
								<td>家庭编号</td>
								<td><input name="familyno" type="text" class="qbg" value="${model.familyno }"/>
								</td>
								<td>与家长关系</td>
								<td><input name="parentsRelationship" type="text" value="${model.parentsRelationship }"
									class="qbg" />
								</td>
							</tr>
							<tr>
								<td>家长证件号码</td>
								<td><input name="parentsidno" type="text" class="qbg" value="${model.parentsidno }"/>
								</td>
								<td>家长姓名</td>
								<td><input name="parentsname" type="text" class="qbg" value="${model.parentsname }"/>
								</td>
							</tr>
							<tr>
								<td>家长手机</td>
								<td><input name="parentscellphone" type="text" class="qbg" value="${model.parentscellphone }"/>
								</td>
								<td>家长电话</td>
								<td><input name="parentsphone" type="text" class="qbg" value="${model.parentsphone }"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="title2">特殊人群</td>
							</tr>
							<tr id="sel_tsrq">
								<td colspan="4">
									<c:forEach items="${spl_type}" var="psnl" varStatus="ps">
										<span class="inpcheck"><lable>
										<input name="tsryflids" type="checkbox" 
											<c:forEach items="${tsry }" var="ts">
											<c:if test="${fn:contains(ts.key,psnl.id)}">checked="checked"</c:if>
											</c:forEach>
											<%--<c:forEach items="${fn:split(model.tsryflids,',') }" var="trid">
											<c:if test="${fn:trim(trid) eq psnl.id}">checked="checked"</c:if>
											</c:forEach>--%>
											value="${psnl.id}" class="inputcheck" />${psnl.name }</lable></span>
									</c:forEach>
								</td>
							</tr>
							<c:forEach items="${tsry }" var="ts">
								<tr name="tsrq_tr${fn:split(ts.key,'_')[1]}" >
										<td colspan="4" class="title2">${fn:split(ts.key,'_')[0]}</td>
								</tr>
								<c:forEach items="${ts.value }" var="s" varStatus="p">
									<c:if test="${p.index%2==0 }">
										<tr name="tsrq_tr${fn:split(ts.key,'_')[1]}">
									</c:if>
									<td>${s.zdname}</td>
									<c:choose>
										<c:when test="${s.zdlx=='datetime' }">
										<td><input name="id${s.flid}_${s.datazd}" type="text" class="qbg" value="${fn:substring(s.value,0,10) }" readonly="readonly" onfocus="WdatePicker();"/></td>
										</c:when>
										<c:when test="${s.zdlx=='checkbox' }">
										<td>
											<c:forEach items="${fn:split(s.zddata,',') }" var="zddata">
												<span class="inpcheck"><lable>
												<input name="id${s.flid}_${s.datazd}" type="checkbox"
													<c:if test="${fn:contains(s.value,fn:trim(zddata)) }"> checked="checked"</c:if>
													 value="${fn:trim(zddata) }" class="inputcheck" />${zddata }</lable></span>
											</c:forEach>
										</td>
										</c:when>
										<c:when test="${s.zdlx=='radio' }">
										<td>
											<c:forEach items="${fn:split(s.zddata,',') }" var="zddata">
												<span class="inpcheck"><lable><input name="id${s.flid}_${s.datazd}" type="radio"
													<c:if test="${fn:contains(s.value,fn:trim(zddata)) }"> checked="checked"</c:if>
													 value="${fn:trim(zddata) }" class="inputcheck" />${zddata }</lable></span>
											</c:forEach>
										</td>
										</c:when>
										<c:when test="${s.zdlx=='select' }">
										<td>${s.value}<span class="inpcheck"><select name="id${s.flid}_${s.datazd}"/>';
											<c:forEach items="${fn:split(s.zddata,',') }" var="zddata">
												<option value="${fn:trim(zddata) }" 
													<c:if test="${fn:contains(s.value,fn:trim(zddata)) }"> selected="selected" </c:if>>zddata</option>
											</c:forEach>
										</td></select></span>
										</c:when>
										<c:when test="${s.zdlx=='image' }">
										<td><span class="inpcheck">
										<input name="id${s.flid}_${s.datazd}"  type="hidden" id="m_image" value="${s.value}"/>
												<iframe src="uploadfile_uploadfile.action?lx=img" id="_img_iframe" scrolling="no" frameborder="0" style="width: 70%;height: 185px;"></iframe>
												<img src="${s.value}" id="i_image" style="max-height:200px;max-width:200px;vertical-align:top;"/></span></td>;
										</c:when>
										<c:otherwise>
										<td><input name="id${s.flid}_${s.datazd}" type="text" value="${s.value }" class="qbg" /></td>
										</c:otherwise>
									</c:choose>
									<c:if test="${p.index%2==1 }">
										</tr>
									</c:if>
								</c:forEach>
								<c:if test="${fn:length(ts.value)%2 eq 1 }">
									<td></td><td></td>
									</tr>
								</c:if>
							</c:forEach>
							
							<tr>
								<td colspan="4" class="title2">收支(家庭)、保险、教育信息</td>
							</tr>
							<tr id="showjtxx">
								<td colspan="4"><span class="inpcheck">
									<input name="huzhu" type="checkbox" value="1" class="inputcheck" 
										onclick="showjtxx(this,'jtxx');" <c:if test="${model.huzhu eq '1' }"> checked="checked" </c:if>/>户主（家庭）</span>
									<span class="inpcheck"><input name="canbao" type="checkbox"  onchange="javascript:showjtxx(this,'cbxx');"
										value="1" class="inputcheck"  <c:if test="${model.canbao eq '1' }"> checked="checked" </c:if>/>是否参保</span><span class="inpcheck">
										<input  onchange="javascript:showjtxx(this,'xsxx');"
										 <c:if test="${model.xuesheng eq '1' }"> checked="checked" </c:if>
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