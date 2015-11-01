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
$(function(){
	if("${model.xuesheng}"=="1"){
		$("#showjtxx").after($("#hide_xsxx").find("tr[name=xsxx_tr]"));
	}
	if("${model.canbao}"=="1"){
		$("#showjtxx").after($("#hide_cbxx").find("tr[name=cbxx_tr]"));
	}
	if("${model.huzhu}"=="1"){	
		$("#showjtxx").after($("#hide_jtxx").find("tr[name=jtxx_tr]"));
	}
	
	if("${model.register}"=="流动人口"){
		$("#hide_hjxx").append($(document.forms[0]).find("tr[name=hjgxx_tr]"));
	}else{
		$("#hjxx_bor").after($("#hide_hjxx").find("tr[name=hjgxx_tr]"));
	}
});

</script>
</head>

<body>

<div>
<!-- 户籍关系表格区 -->
<table id="hide_hjxx" style="display:none;">
	<tr name="hjgxx_tr">
		<td colspan="4" class="title2">户籍关系</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>户口薄号</td>
		<td>${model.accountNumber }
		</td>
		<td>与户主关系</td>
		<td>${model.accountRelation }
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>户主身份证号</td>
		<td>${model.accountIdnumber }
		</td>
		<td>户主姓名</td>
		<td>${model.accountName}
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>户主手机</td>
		<td>${model.accountCellphone }
		</td>
		<td>固定电话</td>
		<td>${model.accountPhone }
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>家庭电话</td>
		<td>${model.accountFlphone }
		</td>
		<td>人户状态</td>
		<td>${model.hushaiState }
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>是否外出</td>
		<td>${model.ynout }</td>
		<td>户口类别</td>
		<td>${model.accountType }
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>外出原因</td>
		<td>${model.outReason }
		</td>
		<td>外出时间</td>
		<td>${fn:substring(model.outTime ,0,10)}
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>外出去向</td>
		<td colspan="3">${model.outto }
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>家庭称号</td>
		<td colspan="3">${model.familyTitle}</td>
	</tr>
</table>
<!-- 户主（家庭）表格区 -->
<table id="hide_jtxx" style="display:none;">
	<tr name="jtxx_tr">
		<td colspan="4" class="title2">户主（家庭）</td>
	</tr>
	
	<tr name="jtxx_tr">
		<td>户口薄号</td>
		<td>${jtxx.hkbh }
		</td>
		<td>户主编号</td>
		<td>${jtxx.hzbh }
		</td>
	</tr>
	<tr name="jtxx_tr">
		
		<td>家庭总人数</td>
		<td>${jtxx.jtzrs }
		</td>
		<td>年用电量</td>
		<td>${jtxx.nydl }</td>
	</tr>
	<tr name="jtxx_tr">
		<td>住房面积</td>
		<td>${jtxx.zfmj }
		</td>
		<td>套内面积</td>
		<td>${jtxx.tnmj }
		</td>
	</tr>
	<tr name="jtxx_tr">
		<td>家庭收入</td>
		<td>${jtxx.jtsr }
		<td>家庭可支配收入</td>
		<td>${jtxx.jtkzpsr }
		</td>
	</tr>
	<tr name="jtxx_tr">
		<td>家庭总支出</td>
		<td>${jtxx.jtzzc }
		</td>
		<td>食物消费支出</td>
		<td>${jtxx.swxfzc }
		</td>
	</tr>
	<tr name="jtxx_tr">
		<td>教育支出</td>
		<td>${jtxx.jyzc }
		</td>
		<td>医疗支出</td>
		<td>${jtxx.ylzc}</td>
	</tr>
	<tr name="jtxx_tr">
		<td>文化娱乐支出</td>
		<td>${jtxx.whylzc }</td>
		<td>其他支出</td>
		<td>${jtxx.qtzc }</td>
	</tr>
</table>
<!-- 参保信息表格区 -->
<table id="hide_cbxx" style="display:none;">
	<tr name="cbxx_tr">
		<td colspan="4" class="title2">参保信息</td>
	</tr>
	<tr name="cbxx_tr">
		<td>参加养老保险</td>
		<td>
		<c:if test="${cbxx.cjylbx }"> 是 </c:if>
		<c:if test="${!cbxx.cjylbx }"> 否 </c:if> </td>
		<td>养老保险月缉费</td>
		<td>${cbxx.ylbxyjf}
		</td>
	</tr>
	<tr name="cbxx_tr">
		<td>养老保险参加时间</td>
		<td>${fn:substring(cbxx.ylbxcbsj,0,10) }
		</td>
		<td></td>
		<td>
		</td>
	</tr>
	<tr name="cbxx_tr">
		<td>参加医疗保险</td>
		<td>
			<c:if test="${cbxx.cjylbx1 }">是 </c:if> 
			<c:if test="${!cbxx.cjylbx1 }"> 否</c:if> 
		</td>
		<td>养老保险月缉费</td>
		<td>${cbxx.ylbxyjf1 }</td>
	</tr>
	<tr name="cbxx_tr">
		<td>医疗保险参加时间</td>
		<td>${fn:substring(cbxx.ylbxcbsj1,0,10) }
		</td>
		<td></td>
		<td>
		</td>
	</tr>
	<tr name="cbxx_tr">
		<td>参加失业保险</td>
		<td>
		<c:if test="${cbxx.cjsybx }"> 是 </c:if> 
		<c:if test="${!cbxx.cjsybx }">否 </c:if> 
		</td>
		<td>失业保险月缉费</td>
		<td>${cbxx.sybxyjf }</td>
	</tr>
	<tr name="cbxx_tr">
		<td>失业保险参保时间</td>
		<td>${fn:substring(cbxx.sybxcbsj,0,10 )}
		</td>
		<td></td>
		<td>
		</td>
	</tr>
	<tr name="cbxx_tr">
		<td>参加工伤保险</td>
		<td>
			<c:if test="${cbxx.cjgsbx }">是</c:if> 
			<c:if test="${!cbxx.cjgsbx }">否</c:if> 
			</td>
		<td>参加生育保险</td>
		<td>
			<c:if test="${cbxx.cjsybx1 }">是</c:if> 
			<c:if test="${!cbxx.cjsybx1 }">否</c:if> 
		</td>
	</tr>
</table>
<!-- 学生信息表格区 -->
<table id="hide_xsxx" style="display:none;">
	<tr name="xsxx_tr">
		<td colspan="4" class="title2">学生信息</td>
	</tr>
	<tr name="xsxx_tr">
		<td>性别</td>
		<td>${xsxx.xb }</td>
		<td>出生年份</td>
		<td>${xsxx.csnf }
		</td>
	</tr>
	<tr name="xsxx_tr">
		<td>学习阶段</td>
		<td>${xsxx.xxjd }
		</td>
		<td>学校</td>
		<td>${xsxx.xx }
		</td>
	</tr>
	<tr name="xsxx_tr">
		<td>入学时间</td>
		<td>${fn:substring(xsxx.rxsj,0,10) }</td>
		<td>预计毕业时间</td>
		<td>${fn:substring(xsxx.yjbysj,0,10) }
		</td>
	</tr>
</table>
</div>




<c:if test="${include ne '1' }">
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>

	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box" style="margin-left:0px;">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;人员信息管理&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div></c:if>
				<!--当前位置  结束-->

				<!--表单内容  开始-->
				<form action="jmxx_update.action" method="post" name="saveform">
					<div class="biaodan-all">
						<div class="biaodan-title">人员信息</div>
						<style type="text/css">
							.mcart a{
							}
							.mcart a:hover {
								text-decoration:underline;
							}
						</style><br/>
						<c:if test="${include ne '1' }">
						<div style="font-size:18px;margin-right:150px;float:right;min-width:100px;" class="mcart">
							<c:forEach items="${tjcy}" var="family" varStatus="n">
								<c:if test="${n.index ne 0 }">
								<span>|</span>
								</c:if>
								<c:if test="${model.id eq family.id}" >
									<a style="text-decoration:underline;font-weight:bold;color:green;">${model.name }</a>
								</c:if>
								<c:if test="${model.id ne family.id}" >
									<a href="jmxx_detail.action?id=${family.id }" >${family.name }</a>
								</c:if>
							</c:forEach>
						</div>
						</c:if>
						<!-- script type="text/javascript">
						var tjcy={};
						var curfa='${model.id}';
						<c:forEach items="${tjcy}" var="family" varStatus="n">
							tjcy['${family.accountRelation }']='${family.id }';
						</c:forEach>
						 var html2="";
						 var style2=' style="text-decoration:underline;font-weight:bold;color:green;"';
						 if(tjcy['户主']!=null&&tjcy['户主']!=undefined){
						 	var str=' <a ';
						 	if(tjcy['户主']==curfa){
						 		str+=style2;
						 	}else{
						 		str+=' href="jmxx_detail.action?id='+tjcy['户主']+'"';
						 	}
						 	str+='>户主</a>';
						 	html2+=str;
						 }
						 if(tjcy['妻']!=null&&tjcy['妻']!=undefined){
						 	var str=' <a ';
						 	if(tjcy['妻']==curfa){
						 		str+=style2;
						 	}else{
						 		str+=' href="jmxx_detail.action?id='+tjcy['妻']+'"';
						 	}
						 	str+='>妻子</a>';
						 	if(html2!=""){
						 		html2+='<span>|</span>';
						 	}
						 	html2+=str;
						 }
						 for(var key in tjcy){
						 	if(key!='妻'&&key!='户主'){
						 		if(html2!=""){
						 			html2+='<span>|</span>';
						 		}
						 		var str=' <a ';
							 	if(tjcy[key]==curfa){
							 		str+=style2;
							 	}else{
							 		str+=' href="jmxx_detail.action?id='+tjcy[key]+'"';
							 	}
							 	str+='>'+key+'</a>';
							 	html2+=str;
							 }
						 }
						 $(".mcart").html(html2);
							</script> -->
							<br/>
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="title2">基本信息</td>
							</tr>
							<tr>
								<td width="20%">身份证号</td>
								<td width="30%">
								<input name="idNumber" type="hidden" value="${model.id }" />${model.idNumber }
								</td>
								<td width="17%">姓名</td>
								<td width="33%">${model.name }
								</td>
							</tr>
							 <tr>
								<td>户籍状态</td>
								<td colspan="3">${model.register }</td>
							</tr>
							<tr>
								<td>性别</td>
								<td>${model.gender }
								</td>
								<td>曾用名/别名</td>
								<td>${model.alias }
								</td>
							</tr>
							<tr>
								<td>所属网格</td>
								<td>${model.wangge} 
								</td>
								<td>其他网格</td>
								<td> 
								 <c:if test="${model.isotherwg eq 1}">
								 ${model.otherwg }
								 </c:if>
								</td>
							</tr>
							
							<tr>
								<td>联系手机</td>
								<td>${model.cellphone }
								</td>
								<td>固定电话</td>
								<td>${model.phone }
								</td>
							</tr>
							<tr>
								<td>出生日期</td>
								<td>${fn:substring(model.birthday,0,10) }
								</td>
								<td>民族</td>
								<td>${model.nation }
								</td>
							</tr>
							<tr>
								<td>政治面貌</td>
								<td>${model.politics }
								</td>
								<td>文化程度</td>
								<td>${model.educational }
								</td>
							</tr>
							<tr>
								<td>受教育年限</td>
								<td colspan="3">${model.educationYears }
								</td>
							</tr>
							<tr>
								<td>职业</td>
								<td>${model.profession }
								</td>
								<td>工作单位</td>
								<td>${model.unit }
								</td>
							</tr>
							<tr>
								<td>婚姻状况</td>
								<td>${model.marital }
								</td>
								<td>是否死亡</td>
								<td>${model.bedied}
								</td>
							</tr>
							<tr>
								<td>身高</td>
								<td>${model.height }cm</td>
								<td>血型</td>
								<td>${model.bloodType }
								</td>
							</tr>
							<tr>
								<td>宗教信仰</td>
								<td>${model.faith }
								</td>
								<td>电子邮箱</td>
								<td>${model.email }
								</td>
							</tr>
							<tr>
								<td>籍贯</td>
								<td colspan="3">${model.birthplace }
								</td>
								<%--<td>联系方式</td>
								<td>${model.contact }
								</td>
							--%></tr>
							<tr>
								<td>户籍详细地址</td>
								<td colspan="3">${model.domicileDetailedAddress}
								</td>
							</tr>
							<tr>
								<td>户籍类型</td>
								<td colspan="3">${model.householdType} </td>
							</tr>
							<tr>
								<td>所属区域</td>
								<td colspan="3">${model.subordinateCompany }
								</td>
							</tr>
							<tr>
								<td>居民身份</td>
								<td colspan="3">
									${model.residentialStatus }</td>
							</tr>
							<tr>
								<td>公民自身民主权利满意度</td>
								<td colspan="1">${model.citizenSatisfaction }%</td>
								<td>兵役情况</td>
								<td colspan="1">${model.bingyi }</td>
							</tr>
							<tr>
								<td>流入时间</td>
								<td colspan="1">${fn:substring(model.liurutime,0,10) }</td>
								<td>流出时间</td>
								<td colspan="1">${fn:substring(model.liuchutime,0,10) }</td>
							</tr>
							<tr>
								<td>有无住房</td>
								<td colspan="1">${model.presenceHousing }
								</td>
								<td>行驶证</td>
								<td>
								${model.xingshizheng }
								</td>
							</tr>
							<tr>
								<td>住房地址/无房原因</td>
								<td colspan="3">${model.nohouse }
								</td>
							</tr>
							<tr>
								<td>社区</td>
								<td colspan="3">${model.community }
								</td>
							</tr>
							<tr>
								<td>小区(路)</td>
								<td colspan="3">${model.road }
								</td>
							</tr>
							<tr>
								<td>栋(排)</td>
								<td colspan="3">${model.ridgepole }
								</td>
							</tr>
							<tr>
								<td>单元</td>
								<td colspan="3">${model.element }
								</td>
							</tr>
							<tr>
								<td>室(号)</td>
								<td colspan="3">${model.roomnumber}
								</td>
							</tr>
							<tr id="hjxx_bor">
								<td>备注</td>
								<td colspan="3">${model.remark }
								</td>
							</tr>
							<c:if test="${model.register eq '流动人口' }">
								<tr name="zzfw_tr">
		<td colspan="4" class="title2">租住房屋</td>
	</tr>
	<tr name="zzfw_tr">
		<td width="17%">出租房屋（<font class="fred">*</font>）</td>
		<td width="33%">
			<input type="text" name="zzrxx.ccfwname" dataType="Require"  class="qbg" 
			readonly="readonly" msg="实有房屋不能为空。" id="ccfwname" value="<c:if test="${syfw ne null }">${syfw.xqdz }${syfw.zhuang }(幢) ${syfw.dy }(单元) ${syfw.shi }(室)</c:if>"
			onclick="showZzrxx();"/>
			<input type="hidden" name="zzrxx.ccfwid" id="ccfwid" value="${zzrxx.ccfwid }" dataType="Require" msg="实有房屋不能为空。"/>	
			<input type="hidden" name="zzrxx.id" id="zzrxx.id" value="${zzrxx.id }"/>	
		</td>
		<td>关注度</td>
		<td><input name="zzrxx.gzd" type="text" class="qbg"  value="${zzrxx.gzd }"/>
		</td>
	</tr>
	<tr name="zzfw_tr">
		<td>自身流入地点</td>
		<td><input name="zzrxx.lrdd" type="text" class="qbg"  value="${zzrxx.lrdd }"/>
		</td>
		<td>流入时间</td>
		<td><input name="zzrxx.lrsj" type="text" class="qbg"  value="${fn:substring(zzrxx.lrsj,0,10) }"
		readonly="readonly" onfocus="WdatePicker();"/>
		</td>
	</tr>
	<tr name="zzfw_tr">
		<td>暂住时间</td>
		<td><input name="zzrxx.zzsj" type="text" class="qbg"  value="${fn:substring(zzrxx.zzsj,0,10) }"
		readonly="readonly" onfocus="WdatePicker();"/>
		</td>
		<td>暂住证</td>
		<td><input name="zzrxx.zzz" type="text" class="qbg"  value="${zzrxx.zzz }"/>
		</td>
	</tr>
	<tr name="zzfw_tr">
		<td>避孕措施</td>
		<td><input name="zzrxx.bycs" type="text" class="qbg"  value="${zzrxx.bycs }"/>
		</td>
		<td>避孕时间</td>
		<td><input name="zzrxx.bysj" type="text" class="qbg"  value="${fn:substring(zzrxx.bysj,0,10) }"
		readonly="readonly" onfocus="WdatePicker();"/>
		</td>
	</tr>
	<tr name="zzfw_tr">
		<td>健康状况</td>
		<td><input name="zzrxx.jjzk" type="text" class="qbg"  value="${zzrxx.jjzk }"/>
		</td>
		<td>孕育证明</td>
		<td><input name="zzrxx.hyzm" type="text" class="qbg"  value="${zzrxx.hyzm }"/>
		</td>
	</tr>
								</c:if>
							<tr>
								<td colspan="4" class="title2">家庭信息</td>
							</tr>
							<tr>
								<td>家庭编号</td>
								<td>${model.familyno }
								</td>
								<td>与家长关系</td>
								<td>${model.parentsRelationship }
								</td>
							</tr>
							<tr>
								<td>家长证件号码</td>
								<td>${model.parentsidno }
								</td>
								<td>家长姓名</td>
								<td>${model.parentsname }
								</td>
							</tr>
							<tr>
								<td>家长手机</td>
								<td>${model.parentscellphone }
								</td>
								<td>家长电话</td>
								<td>${model.parentsphone }
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
										<td>${fn:substring(s.value,0,10) }</td>
										</c:when>
										<c:otherwise>
										<td>${s.value }</td>
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
							<tr id="showjtxx" style="display:none;">
								<td colspan="4">
								</td>
							</tr> 
							<!-- <c:forEach items="${tjcy}" var="family" varStatus="n">
								<tr>
									<td colspan="4" class="title2">户籍关系：${family.name }（${family.accountRelation }）
									<span>
								<button class="button02" onclick="javascript:location.href='jmxx_edit.action?id=${family.id}'" type="button">
									<img src="fun/system/images/icon-add.png" />编辑
								</button> 
								</span>
								<span>
								<button class="button02" onclick="javascript:location.href='jmxx_detail.action?id=${family.id}'" type="button">
									<img src="fun/system/images/icon-search.png" />详细
								</button> 
								</span>
								</td>
								</tr>
								<tr>
									<td>家庭电话</td>
									<td>${family.accountFlphone }
									</td>
									<td>人户状态</td>
									<td>${family.hushaiState }
									</td>
								</tr>
								<tr>
									<td>是否外出</td>
									<td>${family.ynout }</td>
									<td>户口类别</td>
									<td>${family.accountType }
									</td>
								</tr>
								<tr>
									<td>外出原因</td>
									<td>${family.outReason }
									</td>
									<td>外出时间</td>
									<td>${fn:substring(family.outTime ,0,10)}
									</td>
								</tr>
								<tr>
									<td>外出去向</td>
									<td colspan="3">${family.outto }
									</td>
								</tr>
								
							</c:forEach> -->
						</table>
						<div class="bcan">
							<a href="javascript:history.go(-1);"> <img
								src="fun/system/images/fh.gif">
							</a>
							<a href="jmxx_edit.action?id=${model.id }"> <img
								src="fun/system/images/bj.png">
							</a>
						</div>
					</div>
					<!--表单内容  结束-->


				</form>
				<!--表单内容 结束 -->
				<c:if test="${include ne '1' }">
			</div>
		</div>
		<!--right主体内容  结束-->

		<jsp:include page="/WEB-INF/page/system/index/left.jsp"></jsp:include>
		
		<div class="clearit"></div>
	</div>
	<!--主体内容 结束-->
	<jsp:include page="/WEB-INF/page/system/index/foot.jsp"></jsp:include>
</c:if>

</body>
</html>