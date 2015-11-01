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
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet"/>
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="fun/njb/Validator.js"></script>
<script type="text/javascript">
	$(function(){
		$("#save").click(function(){
			var form = document.updateform;
			Validator.Validate(form,3);
		});
		
		$("[name='xb'][value='${grjkda.xb}']").attr("checked",true);
		$("[name='hklb'][value='${grjkda.hklb}']").attr("checked",true);
		$("[name='czlx'][value='${grjkda.czlx}']").attr("checked",true);
		$("[name='tszybwxysjcs'][value='${grjkda.tszybwxysjcs}']").attr("checked",true);
		$("[name='xftz'][value='${grjkda.xftz}']").attr("checked",true);
		$("[name='gpcz'][value='${grjkda.gpcz}']").attr("checked",true);
		$("[name='xbts'][value='${grjkda.xbts}']").attr("checked",true);
		$("[name='xdt'][value='${grjkda.xdt}']").attr("checked",true);
		$("[name='wxdq'][value='${grjkda.wxdq}']").attr("checked",true);
		$("[name='dfbs'][value='${grjkda.dfbs}']").attr("checked",true);
		$("[name='sfzy'][value='${grjkda.sfzy}']").attr("checked",true);
		$("[name='ycbs'][value='${grjkda.ycbs}']").attr("checked",true);
		$("[name='sfxy'][value='${grjkda.sfxy}']").attr("checked",true);
		$("[name='xyl'][value='${grjkda.xyl}']").attr("checked",true);
		$("[name='sfyj'][value='${grjkda.sfyj}']").attr("checked",true);
		$("[name='yjlx'][value='${grjkda.yjlx}']").attr("checked",true);
		$("[name='yjl'][value='${grjkda.yjl}']").attr("checked",true);
		$("[name='yjpl'][value='${grjkda.yjpl}']").attr("checked",true);
		$("[name='dlpl'][value='${grjkda.dlpl}']").attr("checked",true);
		$("[name='mcdlsj'][value='${grjkda.mcdlsj}']").attr("checked",true);
		$("[name='dllx'][value='${grjkda.dllx}']").attr("checked",true);
		$("[name='yslx'][value='${grjkda.yslx}']").attr("checked",true);
		$("[name='smzl'][value='${grjkda.smzl}']").attr("checked",true);
		$("[name='xlqk'][value='${grjkda.xlqk}']").attr("checked",true);
		$("[name='jzmhqn'][value='${grjkda.jzmhqn}']").attr("checked",true);
		$("[name='cqjzd'][value='${grjkda.cqjzd}']").attr("checked",true);
		
		$("[name='whcd']").val("${grjkda.whcd}");
		$("[name='xx']").val("${grjkda.xx}");
		$("[name='zjxy']").val("${grjkda.zjxy}");
		$("[name='hyzk']").val("${grjkda.hyzk}");
		$("[name='hyzk']").val("${grjkda.hyzk}");
		$("[name='ylfyzfff']").val("${grjkda.ylfyzfff}");
		$("[name='tslxrq']").val("${grjkda.tslxrq}");
		$("[name='ywgms']").val("${grjkda.ywgms}");
		$("[name='hbs1']").val("${grjkda.hbs1}");
		$("[name='hbs2']").val("${grjkda.hbs2}");
		$("[name='hbs3']").val("${grjkda.hbs3}");
		$("[name='dfblx']").val("${grjkda.dfblx}");
		
		$.each("${grjkda.jzsfq}".split(','),function(i,data){
			$("[name='jzsfq'][value='"+$.trim(data)+"']").attr("checked",true);
		});
		$.each("${grjkda.jzsmq}".split(','),function(i,data){
			$("[name='jzsmq'][value='"+$.trim(data)+"']").attr("checked",true);
		});
		$.each("${grjkda.jzszn}".split(','),function(i,data){
			$("[name='jzszn'][value='"+$.trim(data)+"']").attr("checked",true);
		});
		$.each("${grjkda.jzsxm}".split(','),function(i,data){
			$("[name='jzsxm'][value='"+$.trim(data)+"']").attr("checked","checked");
		});
		$.each("${grjkda.ywcj}".split(','),function(i,data){
			$("[name='ywcj'][value='"+$.trim(data)+"']").attr("checked","checked");
		});
		
		
		var html = '<td>预览图</td>'
					+'<td colspan="3">';
		$.each("${grjkda.jchyd}".substring(0,"${grjkda.jchyd}".length-1).split(','),function(i,data){
			html += '<img src="'+data+'" height="400px" width="300px"/>';
		});
		html+='</td>';
		$("#ylt").html(html);
	});
	
	function uploadfileend(lx,file0,file1,file2){
		//alert(lx +" | "+file0+" | "+file1+" | "+file2);
		$("#jchyd").val(file1);
		
		var file = file1.substring(0,file1.length-1).split(',');
		var html = '<td>预览图</td>'
					+'<td colspan="3">';
		$.each(file,function(i,data){
			html += '<img src="'+data+'" height="400px" width="300px"/>';
		});
		html+='</td>';
		$("#ylt").html(html);
	}
	
</script>
</head>

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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;医疗卫生&nbsp;&gt;&gt;&nbsp;健康档案&nbsp;&gt;&gt;&nbsp;修改</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="jkda_update.action" method="post" name="updateform">
				<input type="hidden" name="id" value="${grjkda.id }"/>
				<div class="biaodan-all">
					<div class="biaodan-title">健康档案信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">档案信息</td>
						</tr>
						<tr>
							<td>个人档案号（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="grdah" type="text" class="qbg" maxlength="14" value="${grjkda.grdah }"
									dataType="Require" msg="个人档案号不能为空！" require="true" noRepeat="true" repeatTab="TblGrjkda" repeatMsg="个人档案号已经存在！" repeatId="${grjkda.id }"/>
							</td>
						</tr>
						<tr>
							<td>建档单位（<font class="fred">*</font>）</td>
							<td>
								<input name="jddw" type="text" class="qbg" maxlength="50" value="${grjkda.jddw }"
									dataType="Require" msg="建档单位不能为空！" require="true"/>
							</td>
							<td>建档日期（<font class="fred">*</font>）</td>
							<td>
								<input name="jdrq" type="text" class="qbg" maxlength="25" value='<fmt:formatDate value="${grjkda.jdrq }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									dataType="Require" msg="建档日期不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>建档医生（<font class="fred">*</font>）</td>
							<td>
								<input name="jdys" type="text" class="qbg" maxlength="25" value="${grjkda.jdys }"
									dataType="Require" msg="建档医生不能为空！" require="true"/>
							</td>
							<td>建档护士（<font class="fred">*</font>）</td>
							<td>
								<input name="jdhs" type="text" class="qbg" maxlength="25" value='${grjkda.jdhs }'
									dataType="Require" msg="建档护士不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>责任医生（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="zrys" type="text" class="qbg" maxlength="14" value="${grjkda.zrys }"
									dataType="Require" msg="责任医生不能为空！" require="true"/>
							</td>
						</tr>
						
						
						
						<tr>
							<td colspan="4" class="title2">个人基本信息</td>
						</tr>
						<tr>
							<td>姓名（<font class="fred">*</font>）</td>
							<td>
								<input name="xm" type="text" class="qbg" maxlength="25" value="${grjkda.xm }"
									dataType="Require" msg="姓名不能为空！" require="true"/>
							</td>
							<td>身份证号（<font class="fred">*</font>）</td>
							<td>
								<input name="grsfz" type="text" class="qbg" maxlength="25" value="${grjkda.grsfz }"
									dataType="Require" msg="身份证号不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>性别（<font class="fred">*</font>）</td>
							<td>
								<input name="xb" type="radio" class="inputcheck" value="男" checked="checked" id="xb_man"/>&nbsp;<label for="xb_man">男</label>
								<input name="xb" type="radio" class="inputcheck" value="女" id="xb_woman"/>&nbsp;<label for="xb_woman">女</label>
							</td>
							<td>籍贯（<font class="fred">*</font>）</td>
							<td>
								<input name="jg" type="text" class="qbg" maxlength="50" value="${grjkda.jg }"
									dataType="Require" msg="籍贯不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>民族（<font class="fred">*</font>）</td>
							<td>
								<input name="mz" type="text" class="qbg" maxlength="25" value="${grjkda.mz }"
									dataType="Require" msg="民族不能为空！" require="true"/>
							</td>
							<td>出生日期（<font class="fred">*</font>）</td>
							<td>
								<input name="csrq" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.csrq }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									dataType="Require" msg="出生日期不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>宗教信仰</td>
							<td>
								<select style="width:30%;" name="zjxy" class="bd-r">
									<option value="">请选择</option>
									<option value="1">无信仰</option>
									<option value="2">无神论</option>
									<option value="3">佛教</option>
									<option value="4">伊斯兰教</option>
									<option value="5">道教</option>
									<option value="6">天主教</option>
									<option value="7">其他</option>
								</select>
							</td>
							<td>婚姻状况</td>
							<td>
								<select style="width:30%;" name="hyzk" class="bd-r">
									<option value="">请选择</option>
									<option value="1">已婚</option>
									<option value="2">未婚</option>
									<option value="3">离婚</option>
									<option value="4">丧偶</option>
									<option value="5">分居</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>文化程度</td>
							<td>
								<select style="width:30%;" name="whcd" class="bd-r">
									<option value="">请选择</option>
									<option value="1">文盲/没上过小学</option>
									<option value="2">小学</option>
									<option value="3">初中</option>
									<option value="4">高中/中专</option>
									<option value="5">大专以上学历</option>
									<option value="6">硕士及以上学历</option>
								</select>
							</td>
							<td>常住类型</td>
							<td>
								<input name="czlx" type="radio" class="inputcheck" value="1" checked="checked" id="czlx1"/>&nbsp;<label for="czlx1">户籍</label>
								<input name="czlx" type="radio" class="inputcheck" value="2" id="czlx2"/>&nbsp;<label for="czlx2">非户籍</label>
							</td>
						</tr>
						<tr>
							<td>户口类别</td>
							<td colspan="3">
								<input name="hklb" type="radio" class="inputcheck" value="1" checked="checked" id="hklb1"/>&nbsp;<label for="hklb1">农业</label>
								<input name="hklb" type="radio" class="inputcheck" value="2" id="hklb2"/>&nbsp;<label for="hklb2">非农业</label>
							</td>
						</tr>
						<tr>
							<td>来兵团日期</td>
							<td>
								<input name="lbtrq" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.lbtrq }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
							<td>职业</td>
							<td>
								<input name="zy" type="text" class="qbg" maxlength="25" value="${grjkda.zy }"/>
							</td>
						</tr>
						<tr>
							<td>现住址</td>
							<td>
								<input name="xzz" type="text" class="qbg" maxlength="25"  value="${grjkda.xzz }"/>
							</td>
							<td>邮政编码</td>
							<td>
								<input name="yzbm" type="text" class="qbg" maxlength="25"  value="${grjkda.yzbm }"/>
							</td>
						</tr>
						<tr>
							<td>所属派出所</td>
							<td colspan="3">
								<input name="sspcs" type="text" class="qbg" maxlength="50"  value="${grjkda.sspcs }"/>
							</td>
						</tr>
						<tr>
							<td>所属居委会</td>
							<td colspan="3">
								<input name="ssjwh" type="text" class="qbg" maxlength="50"  value="${grjkda.ssjwh }"/>
							</td>
						</tr>
						<tr>
							<td>住宅电话</td>
							<td>
								<input name="zzdh" type="text" class="qbg" maxlength="25"  value="${grjkda.zzdh }"/>
							</td>
							<td>手机</td>
							<td>
								<input name="sj" type="text" class="qbg" maxlength="25"  value="${grjkda.sj }"/>
							</td>
						</tr>
						<tr>
							<td>E-Mail</td>
							<td colspan="3">
								<input name="email" type="text" class="qbg" maxlength="50"  value="${grjkda.email }"/>
							</td>
						</tr>
						<tr>
							<td>工作单位</td>
							<td>
								<input name="gzdw" type="text" class="qbg" maxlength="50"  value="${grjkda.gzdw }"/>
							</td>
							<td>单位电话</td>
							<td>
								<input name="dwdh" type="text" class="qbg" maxlength="25"  value="${grjkda.dwdh }"/>
							</td>
						</tr>
						<tr>
							<td>医疗费用<br/>支付方式（<font class="fred">*</font>）</td>
							<td colspan="3">
								<select style="width:15%;" name="ylfyzfff" class="bd-r" dataType="Require" msg="请选择医疗费用支付方式！" require="true">
									<option value="">请选择</option>
									<option value="1">全公费</option>
									<option value="2">部分公费</option>
									<option value="3">职工医保</option>
									<option value="4">居民医保</option>
									<option value="5">新农合</option>
									<option value="6">工伤保险</option>
									<option value="7">生育保险</option>
									<option value="8">商业医疗保险</option>
									<option value="9">医疗救助</option>
									<option value="10">全自费</option>
									<option value="11">其他</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>医保号</td>
							<td>
								<input name="ybh" type="text" class="qbg" maxlength="50"  value="${grjkda.ybh }"/>
							</td>
							<td>定点医疗单位</td>
							<td>
								<input name="ddyldw" type="text" class="qbg" maxlength="25"  value="${grjkda.ddyldw }"/>
							</td>
						</tr>
						<tr>
							<td>特殊类型人群</td>
							<td colspan="3">
								<select style="width:15%;" name="tslxrq" class="bd-r">
									<option value="">请选择</option>
									<option value="1">低保</option>
									<option value="2">特困</option>
									<option value="3">残疾</option>
									<option value="4">医保签约</option>
									<option value="5">持慈善卡</option>
									<option value="6">离退休局级干部</option>
									<option value="7">其他</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>特殊职业病<br/>危险因素接触史</td>
							<td colspan="3">
								<input name="tszybwxysjcs" type="radio" class="inputcheck" value="1" checked="checked" id="tszybwxysjcs1"/>&nbsp;<label for="tszybwxysjcs1">否</label>
								<input name="tszybwxysjcs" type="radio" class="inputcheck" value="2" id="tszybwxysjcs2"/>&nbsp;<label for="tszybwxysjcs2">是</label>
								描述：<input name="zybwxysjcsms" type="text" class="qbg" maxlength="25"  value="${grjkda.zybwxysjcsms }" style="width:80%;"/>
							</td>
						</tr>
						
						
						
						<tr>
							<td colspan="4" class="title2">个人健康相关信息</td>
						</tr>
						<tr>
							<td>身高（m）</td>
							<td>
								<input name="sg" type="text" class="qbg" maxlength="25"  value="${grjkda.sg }"/>
							</td>
							<td>体重（kg）</td>
							<td>
								<input name="tz" type="text" class="qbg" maxlength="25"  value="${grjkda.tz }"/>
							</td>
						</tr>
						<tr>
							<td>腰围（cm）</td>
							<td>
								<input name="yw" type="text" class="qbg" maxlength="25"  value="${grjkda.yw }"/>
							</td>
							<td>臀围（cm）</td>
							<td>
								<input name="tw" type="text" class="qbg" maxlength="25"  value="${grjkda.tw }"/>
							</td>
						</tr>
						<tr>
							<td>体质指数</td>
							<td>
								<input name="tzzs" type="text" class="qbg" maxlength="25"  value="${grjkda.tzzs }"/>
							</td>
							<td>腰臀比</td>
							<td>
								<input name="ytb" type="text" class="qbg" maxlength="25"  value="${grjkda.ytb }"/>
							</td>
						</tr>
						<tr>
							<td>心率（次/分）</td>
							<td>
								<input name="xl" type="text" class="qbg" maxlength="25"  value="${grjkda.xl }"/>
							</td>
							<td>血压（mmHg）</td>
							<td>
								<input name="xy" type="text" class="qbg" maxlength="25"  value="${grjkda.xy }"/>
							</td>
						</tr>
						<tr>
							<td>血糖（mmol/L）</td>
							<td>
								<input name="xt" type="text" class="qbg" maxlength="25"  value="${grjkda.xt }"/>
							</td>
							<td>血型</td>
							<td>
								<select style="width:30%;" name="xx" class="bd-r">
									<option value="">请选择</option>
									<option value="1">A型</option>
									<option value="2">B型</option>
									<option value="3">O型</option>
									<option value="4">AB型</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>RH类型</td>
							<td colspan="3">
								<input name="rhlx" type="text" class="qbg" maxlength="25"  value="${grjkda.rhlx }"/>
							</td>
						</tr>
						<tr>
							<td>心肺听诊</td>
							<td colspan="3">
								<input name="xftz" type="radio" class="inputcheck" value="1" checked="checked" id="xftz1"/>&nbsp;<label for="xftz1">阴性</label>
								<input name="xftz" type="radio" class="inputcheck" value="2" id="xftz2"/>&nbsp;<label for="xftz2">阳性</label>
								症状：<input name="xftzzz" type="text" class="qbg" maxlength="50"  value="${grjkda.xftzzz }" style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td>肝脾触诊</td>
							<td colspan="3">
								<input name="gpcz" type="radio" class="inputcheck" value="1" checked="checked" id="gpcz1"/>&nbsp;<label for="gpcz1">阴性</label>
								<input name="gpcz" type="radio" class="inputcheck" value="2" id="gpcz2"/>&nbsp;<label for="gpcz2">阳性</label>
								症状：<input name="gpczz" type="text" class="qbg" maxlength="50"  value="${grjkda.gpczz }" style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td>胸部透视</td>
							<td colspan="3">
								<input name="xbts" type="radio" class="inputcheck" value="1" checked="checked" id="xbts1"/>&nbsp;<label for="xbts1">阴性</label>
								<input name="xbts" type="radio" class="inputcheck" value="2" id="xbts2"/>&nbsp;<label for="xbts2">阳性</label>
								症状：<input name="xbtszz" type="text" class="qbg" maxlength="50"  value="${grjkda.xbtszz }" style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td>心电图</td>
							<td colspan="3">
								<input name="xdt" type="radio" class="inputcheck" value="1" checked="checked" id="xdt1"/>&nbsp;<label for="xdt1">阴性</label>
								<input name="xdt" type="radio" class="inputcheck" value="2" id="xdt2"/>&nbsp;<label for="xdt2">阳性</label>
								症状：<input name="xdtzz" type="text" class="qbg" maxlength="50"  value="${grjkda.xdtzz }" style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td>生育史</td>
							<td colspan="3">
								孕（<input name="sys" type="text" class="qbg" maxlength="3"  value="${grjkda.sys }" style="width:20px;"/>）
								产（<input name="sysc" type="text" class="qbg" maxlength="3"  value="${grjkda.sysc }" style="width:20px;"/>）
							</td>
						</tr>
						<tr>
							<td>月经史</td>
							<td colspan="3">
								初期年龄（<input name="yjs" type="text" class="qbg" maxlength="3"  value="${grjkda.yjs }" style="width:20px;"/>岁）-
								绝经年龄（<input name="yjs1" type="text" class="qbg" maxlength="3"  value="${grjkda.yjs1 }" style="width:20px;"/>岁），
								持续天数（<input name="yjs2" type="text" class="qbg" maxlength="3"  value="${grjkda.yjs2 }" style="width:20px;"/>天），
								间隔天数（<input name="yjs3" type="text" class="qbg" maxlength="3"  value="${grjkda.yjs3 }" style="width:20px;"/>天）
							</td>
						</tr>
						<tr>
							<td>药物过敏史</td>
							<td colspan="3">
								<select name="ywgms" class="bd-r">
									<option value="1">无</option>
									<option value="2">青霉素</option>
									<option value="3">磺酸</option>
									<option value="4">链霉素</option>
									<option value="5">其他过敏物质</option>
								</select>
								其他过敏物质：<input name="ywgms1" type="text" class="qbg" maxlength="50"  value="${grjkda.ywgms1 }" style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td valign="top">患病史</td>
							<td colspan="3">
								<table width="100%">
									<colgroup>
										<col style="width:15%;"/>
										<col />
									</colgroup>
									<tr>
										<td align="center">
											<select name="hbs1" class="bd-r" style="width:90%;">
												<option value="">请选择</option>
												<option value="1">高血压</option>
												<option value="2">糖尿病</option>
												<option value="3">冠心病</option>
												<option value="4">恶性肿瘤</option>
												<option value="5">脑卒中</option>
												<option value="6">COPD</option>
												<option value="7">结核病</option>
												<option value="8">精神分裂症</option>
												<option value="9">肝炎</option>
												<option value="10">其他</option>
											</select>
										</td>
										<td>
											确诊时间：<input name="hbsdate1" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.hbsdate1 }" pattern="yyyy-MM-dd" />'
												readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:90%;"/>
										</td>
									</tr>
									<tr>
										<td align="center">
											<select name="hbs2" class="bd-r" style="width:90%;">
												<option value="">请选择</option>
												<option value="1">高血压</option>
												<option value="2">糖尿病</option>
												<option value="3">冠心病</option>
												<option value="4">恶性肿瘤</option>
												<option value="5">脑卒中</option>
												<option value="6">COPD</option>
												<option value="7">结核病</option>
												<option value="8">精神分裂症</option>
												<option value="9">肝炎</option>
												<option value="10">其他</option>
											</select>
										</td>
										<td>
											确诊时间：<input name="hbsdate2" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.hbsdate2 }" pattern="yyyy-MM-dd" />'
												readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:90%;"/>
										</td>
									</tr>
									<tr>
										<td align="center">
											<select name="hbs3" class="bd-r" style="width:90%;">
												<option value="">请选择</option>
												<option value="1">高血压</option>
												<option value="2">糖尿病</option>
												<option value="3">冠心病</option>
												<option value="4">恶性肿瘤</option>
												<option value="5">脑卒中</option>
												<option value="6">COPD</option>
												<option value="7">结核病</option>
												<option value="8">精神分裂症</option>
												<option value="9">肝炎</option>
												<option value="10">其他</option>
											</select>
										</td>
										<td>
											确诊时间：<input name="hbsdate3" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.hbsdate3 }" pattern="yyyy-MM-dd" />'
												readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:90%;"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>手术史</td>
							<td colspan="3">
								名称 1<input name="sss1" type="text" class="qbg" maxlength="3"  value="${grjkda.sss1 }" style="width:25%;"/>
								时间<input name="sssdate1" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.sssdate1 }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:15%;"/>
								<span style="color:blue;font-weight: bolder;">/</span>
								名称 2<input name="sss2" type="text" class="qbg" maxlength="3"  value="${grjkda.sss2 }" style="width:25%;"/>
								时间<input name="sssdate2" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.sssdate2 }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:15%;"/>
							</td>
						</tr>
						<tr>
							<td>外伤史</td>
							<td colspan="3">
								名称 1<input name="wss1" type="text" class="qbg" maxlength="3"  value="${grjkda.wss1 }" style="width:25%;"/>
								时间<input name="wssdate1" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.wssdate1 }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:15%;"/>
								<span style="color:blue;font-weight: bolder;">/</span>
								名称 2<input name="wss2" type="text" class="qbg" maxlength="3"  value="${grjkda.wss2 }" style="width:25%;"/>
								时间<input name="wssdate2" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.wssdate2 }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:15%;"/>
							</td>
						</tr>
						<tr>
							<td>输血史</td>
							<td colspan="3">
								名称 1<input name="sxs1" type="text" class="qbg" maxlength="3"  value="${grjkda.sxs1 }" style="width:25%;"/>
								时间<input name="sxsdate1" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.sxsdate1 }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:15%;"/>
								<span style="color:blue;font-weight: bolder;">/</span>
								名称 2<input name="sxs2" type="text" class="qbg" maxlength="3"  value="${grjkda.sxs2 }" style="width:25%;"/>
								时间<input name="sxsdate2" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.sxsdate2 }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:15%;"/>
							</td>
						</tr>
						<tr>
							<td valign="top">地方病史</td>
							<td colspan="3">
								<table width="100%">
									<colgroup>
										<col style="width:12%;"/>
										<col style="width:;"/>
										<col style="width:;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td colspan="2" align="center">是否到过地方病危地区</td>
										<td colspan="2">
											<input name="wxdq" type="radio" class="inputcheck" value="1" checked="checked" id="wxdq1"/>&nbsp;<label for="wxdq1">否</label>
											<input name="wxdq" type="radio" class="inputcheck" value="2" id="wxdq2"/>&nbsp;<label for="wxdq2">是</label>
											<input name="wxdq1" type="text" class="qbg" maxlength="25"  value='${grjkda.wxdq1 }' style="width:80%;"/>
										</td>
									</tr>
									<tr>
										<td rowspan="3" align="center">是否患有地方病</td>
										<td colspan="3">
											<input name="dfbs" type="radio" class="inputcheck" value="1" checked="checked" id="dfbs1"/>&nbsp;<label for="dfbs1">否</label>
											<input name="dfbs" type="radio" class="inputcheck" value="2" id="dfbs2"/>&nbsp;<label for="dfbs2">是</label>
											患病时间：<input name="dfbsdate" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.dfbsdate }" pattern="yyyy-MM-dd" />'
												readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:77%;"/>
										</td>
									</tr>
									<tr>
										<td>患病类型</td>
										<td colspan="2">
											<select name="dfblx" class="bd-r">
												<option value="">请选择</option>
												<option value="1">包虫病</option>
												<option value="2">碘缺乏病（大脖子病）</option>
												<option value="3">氟中毒</option>
												<option value="4">砷中毒</option>
												<option value="5">其他</option>
											</select>
											其他：<input name="dfblx1" type="text" class="qbg" maxlength="25"  value='${grjkda.dfblx1 }' style="width:72%;"/>
										</td>
									</tr>
									<tr>
										<td>是否治愈</td>
										<td colspan="2">
											<input name="sfzy" type="radio" class="inputcheck" value="1" checked="checked" id="sfzy1"/>&nbsp;<label for="sfzy1">否</label>
											<input name="sfzy" type="radio" class="inputcheck" value="2" id="sfzy2"/>&nbsp;<label for="sfzy2">是</label>
											治愈时间：<input name="sfzydate" type="text" class="qbg" maxlength="25"  value='<fmt:formatDate value="${grjkda.sfzydate }" pattern="yyyy-MM-dd" />'
												readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:72%;"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="top">家族病史</td>
							<td colspan="3">
								<table width="100%">
									<tr>
										<td align="center">父亲</td>
										<td>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="1" id="jzsfq1"/>&nbsp;<label for="jzsfq1">高血压</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="2" id="jzsfq2"/>&nbsp;<label for="jzsfq2">糖尿病</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="3" id="jzsfq3"/>&nbsp;<label for="jzsfq3">冠心病</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="4" id="jzsfq4"/>&nbsp;<label for="jzsfq4">恶性肿瘤</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="5" id="jzsfq5"/>&nbsp;<label for="jzsfq5">过敏症</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="6" id="jzsfq6"/>&nbsp;<label for="jzsfq6">精神分裂</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="7" id="jzsfq7"/>&nbsp;<label for="jzsfq7">结核病</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="8" id="jzsfq8"/>&nbsp;<label for="jzsfq8">肝炎</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="9" id="jzsfq9"/>&nbsp;<label for="jzsfq9">脑卒中</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="10" id="jzsfq10"/>&nbsp;<label for="jzsfq10">先天畸形</label>
											<input name="jzsfq" type="checkbox" class="inputcheck" value="11" id="jzsfq11"/>&nbsp;<label for="jzsfq11">其他</label>
										</td>
									</tr>
									<tr>
										<td align="center">母亲</td>
										<td>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="1" id="jzsmq1"/>&nbsp;<label for="jzsmq1">高血压</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="2" id="jzsmq2"/>&nbsp;<label for="jzsmq2">糖尿病</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="3" id="jzsmq3"/>&nbsp;<label for="jzsmq3">冠心病</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="4" id="jzsmq4"/>&nbsp;<label for="jzsmq4">恶性肿瘤</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="5" id="jzsmq5"/>&nbsp;<label for="jzsmq5">过敏症</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="6" id="jzsmq6"/>&nbsp;<label for="jzsmq6">精神分裂</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="7" id="jzsmq7"/>&nbsp;<label for="jzsmq7">结核病</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="8" id="jzsmq8"/>&nbsp;<label for="jzsmq8">肝炎</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="9" id="jzsmq9"/>&nbsp;<label for="jzsmq9">脑卒中</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="10" id="jzsmq10"/>&nbsp;<label for="jzsmq10">先天畸形</label>
											<input name="jzsmq" type="checkbox" class="inputcheck" value="11" id="jzsmq11"/>&nbsp;<label for="jzsmq11">其他</label>
										</td>
									</tr>
									<tr>
										<td align="center">子女</td>
										<td>
											<input name="jzszn" type="checkbox" class="inputcheck" value="1" id="jzszn1"/>&nbsp;<label for="jzszn1">高血压</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="2" id="jzszn2"/>&nbsp;<label for="jzszn2">糖尿病</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="3" id="jzszn3"/>&nbsp;<label for="jzszn3">冠心病</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="4" id="jzszn4"/>&nbsp;<label for="jzszn4">恶性肿瘤</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="5" id="jzszn5"/>&nbsp;<label for="jzszn5">过敏症</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="6" id="jzszn6"/>&nbsp;<label for="jzszn6">精神分裂</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="7" id="jzszn7"/>&nbsp;<label for="jzszn7">结核病</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="8" id="jzszn8"/>&nbsp;<label for="jzszn8">肝炎</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="9" id="jzszn9"/>&nbsp;<label for="jzszn9">脑卒中</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="10" id="jzszn10"/>&nbsp;<label for="jzszn10">先天畸形</label>
											<input name="jzszn" type="checkbox" class="inputcheck" value="11" id="jzszn11"/>&nbsp;<label for="jzszn11">其他</label>
										</td>
									</tr>
									<tr>
										<td align="center">兄弟姐妹</td>
										<td>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="1" id="jzsxm1"/>&nbsp;<label for="jzsxm1">高血压</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="2" id="jzsxm2"/>&nbsp;<label for="jzsxm2">糖尿病</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="3" id="jzsxm3"/>&nbsp;<label for="jzsxm3">冠心病</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="4" id="jzsxm4"/>&nbsp;<label for="jzsxm4">恶性肿瘤</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="5" id="jzsxm5"/>&nbsp;<label for="jzsxm5">过敏症</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="6" id="jzsxm6"/>&nbsp;<label for="jzsxm6">精神分裂</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="7" id="jzsxm7"/>&nbsp;<label for="jzsxm7">结核病</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="8" id="jzsxm8"/>&nbsp;<label for="jzsxm8">肝炎</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="9" id="jzsxm9"/>&nbsp;<label for="jzsxm9">脑卒中</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="10" id="jzsxm10"/>&nbsp;<label for="jzsxm10">先天畸形</label>
											<input name="jzsxm" type="checkbox" class="inputcheck" value="11" id="jzsxm11"/>&nbsp;<label for="jzsxm11">其他</label>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>遗传病史</td>
							<td colspan="3">
								<input name="ycbs" type="radio" class="inputcheck" value="1" checked="checked" id="ycbs1"/>&nbsp;<label for="ycbs1">否</label>
								<input name="ycbs" type="radio" class="inputcheck" value="2" id="ycbs2"/>&nbsp;<label for="ycbs2">是</label>
								疾病名称：<input name="ycbs1" type="text" class="qbg" maxlength="50"  value='${grjkda.ycbs1 }' style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td>有无残疾</td>
							<td colspan="3">
								<input name="ywcj" type="checkbox" class="inputcheck" value="1" id="ywcj1"/>&nbsp;<label for="ywcj1">无残疾</label>
								<input name="ywcj" type="checkbox" class="inputcheck" value="2" id="ywcj2"/>&nbsp;<label for="ywcj2">听力残疾</label>
								<input name="ywcj" type="checkbox" class="inputcheck" value="3" id="ywcj3"/>&nbsp;<label for="ywcj3">言语残疾</label>
								<input name="ywcj" type="checkbox" class="inputcheck" value="4" id="ywcj4"/>&nbsp;<label for="ywcj4">肢体残疾</label>
								<input name="ywcj" type="checkbox" class="inputcheck" value="5" id="ywcj5"/>&nbsp;<label for="ywcj5">智力残疾</label>
								<input name="ywcj" type="checkbox" class="inputcheck" value="6" id="ywcj6"/>&nbsp;<label for="ywcj6">视力残疾</label>
								<input name="ywcj" type="checkbox" class="inputcheck" value="7" id="ywcj7"/>&nbsp;<label for="ywcj7">精神残疾</label>
								残疾证号：<input name="cjzh" type="text" class="qbg" maxlength="50"  value='${grjkda.cjzh }' style="width:42%;"/>
							</td>
						</tr>
						
						
						
						<tr>
							<td colspan="4" class="title2">生活行为习惯</td>
						</tr>
						<tr>
							<td valign="top">吸烟史</td>
							<td colspan="3">
								<table width="100%">
									<colgroup>
										<col style="width:18%;"/>
										<col style="width:;"/>
										<col style="width:18%;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">是否吸烟</td>
										<td colspan="3">
											<input name="sfxy" type="radio" class="inputcheck" value="1" checked="checked" id="sfxy1"/>&nbsp;<label for="sfxy1">从不吸烟</label>
											<input name="sfxy" type="radio" class="inputcheck" value="2" id="sfxy2"/>&nbsp;<label for="sfxy2">已戒烟</label>
											<input name="sfxy" type="radio" class="inputcheck" value="3" id="sfxy3"/>&nbsp;<label for="sfxy3">吸烟</label>
										</td>
									</tr>
									<tr>
										<td align="center">开始吸烟年龄</td>
										<td>
											<input name="ksxynl" type="text" class="qbg" maxlength="50"  value='${grjkda.ksxynl }' style="width:80%;"/>岁
										</td>
										<td align="center">戒烟年龄</td>
										<td>
											<input name="jynl" type="text" class="qbg" maxlength="50"  value='${grjkda.jynl }' style="width:80%;"/>岁
										</td>
									</tr>
									<tr>
										<td align="center">开始吸烟时间</td>
										<td>
											<input name="ksxysj" type="text" class="qbg" maxlength="25"  value='${grjkda.ksxysj }'
												readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
										</td>
										<td align="center">戒烟原因</td>
										<td>
											<input name="jyyy" type="text" class="qbg" maxlength="50"  value='${grjkda.jyyy }'/>
										</td>
									</tr>
									<tr>
										<td align="center">吸烟量</td>
										<td colspan="3">
											<input name="xyl" type="radio" class="inputcheck" value="1" id="xyl1"/>&nbsp;<label for="xyl1">偶尔</label>
											<input name="xyl" type="radio" class="inputcheck" value="2" id="xyl2"/>&nbsp;<label for="xyl2">少量</label>
											<input name="xyl" type="radio" class="inputcheck" value="3" id="xyl3"/>&nbsp;<label for="xyl3">经常</label>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="top">饮酒史</td>
							<td colspan="3">
								<table width="100%">
									<colgroup>
										<col style="width:18%;"/>
										<col style="width:;"/>
										<col style="width:18%;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">是否饮酒</td>
										<td colspan="3">
											<input name="sfyj" type="radio" class="inputcheck" value="1" checked="checked" id="sfyj1"/>&nbsp;<label for="sfyj1">从不饮酒</label>
											<input name="sfyj" type="radio" class="inputcheck" value="2" id="sfyj2"/>&nbsp;<label for="sfyj2">已戒酒</label>
											<input name="sfyj" type="radio" class="inputcheck" value="3" id="sfyj3"/>&nbsp;<label for="sfyj3">饮酒</label>
										</td>
									</tr>
									<tr>
										<td align="center">开始饮酒年龄</td>
										<td>
											<input name="ksyjnl" type="text" class="qbg" maxlength="50"  value='${grjkda.ksyjnl }' style="width:80%;"/>岁
										</td>
										<td align="center">饮酒年龄</td>
										<td>
											<input name="jjnl" type="text" class="qbg" maxlength="50"  value='${grjkda.jjnl }' style="width:80%;"/>岁
										</td>
									</tr>
									<tr>
										<td align="center">开始饮酒时间</td>
										<td>
											<input name="ksyjsj" type="text" class="qbg" maxlength="25"  value='${grjkda.ksyjsj }'
												readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
										</td>
										<td align="center">戒酒原因</td>
										<td>
											<input name="jjyy" type="text" class="qbg" maxlength="50"  value='${grjkda.jjyy }'/>
										</td>
									</tr>
									<tr>
										<td align="center">饮酒类型</td>
										<td colspan="3">
											<input name="yjlx" type="radio" class="inputcheck" value="1" id="yjlx1"/>&nbsp;<label for="yjlx1">色酒</label>
											<input name="yjlx" type="radio" class="inputcheck" value="2" id="yjlx2"/>&nbsp;<label for="yjlx2">啤酒</label>
											<input name="yjlx" type="radio" class="inputcheck" value="3" id="yjlx3"/>&nbsp;<label for="yjlx3">白酒</label>
										</td>
									</tr>
									<tr>
										<td align="center">饮酒量</td>
										<td colspan="3">
											<input name="yjl" type="radio" class="inputcheck" value="1" id="yjl1"/>&nbsp;<label for="yjl1">少量</label>
											<input name="yjl" type="radio" class="inputcheck" value="2" id="yjl2"/>&nbsp;<label for="yjl2">中量</label>
											<input name="yjl" type="radio" class="inputcheck" value="3" id="yjl3"/>&nbsp;<label for="yjl3">大量</label>
										</td>
									</tr>
									<tr>
										<td align="center">饮酒频率</td>
										<td colspan="3">
											<input name="yjpl" type="radio" class="inputcheck" value="1" id="yjpl1"/>&nbsp;<label for="yjpl1">偶尔</label>
											<input name="yjpl" type="radio" class="inputcheck" value="2" id="yjpl2"/>&nbsp;<label for="yjpl2">少量</label>
											<input name="yjpl" type="radio" class="inputcheck" value="3" id="yjpl3"/>&nbsp;<label for="yjpl3">经常</label>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="top">体育锻炼情况</td>
							<td colspan="3">
								<table width="100%">
									<colgroup>
										<col style="width:18%;"/>
										<col style="width:;"/>
										<col style="width:18%;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">锻炼频率</td>
										<td colspan="3">
											<input name="dlpl" type="radio" class="inputcheck" value="1" id="dlpl1"/>&nbsp;<label for="dlpl1">每天锻炼</label>
											<input name="dlpl" type="radio" class="inputcheck" value="2" id="dlpl2"/>&nbsp;<label for="dlpl2">每周3次以上</label>
											<input name="dlpl" type="radio" class="inputcheck" value="3" id="dlpl3"/>&nbsp;<label for="dlpl3">偶尔</label>
											<input name="dlpl" type="radio" class="inputcheck" value="4" id="dlpl4"/>&nbsp;<label for="dlpl4">从不</label>
										</td>
									</tr>
									<tr>
										<td align="center">每次锻炼时间</td>
										<td colspan="3">
											<input name="mcdlsj" type="radio" class="inputcheck" value="1" id="mcdlsj1"/>&nbsp;<label for="mcdlsj1">小于30分钟</label>
											<input name="mcdlsj" type="radio" class="inputcheck" value="2" id="mcdlsj2"/>&nbsp;<label for="mcdlsj2">30-60分钟</label>
											<input name="mcdlsj" type="radio" class="inputcheck" value="3" id="mcdlsj3"/>&nbsp;<label for="mcdlsj3">1小时以上</label>
										</td>
									</tr>
									<tr>
										<td align="center">锻炼类型</td>
										<td colspan="3">
											<input name="dllx" type="radio" class="inputcheck" value="1" id="dllx1"/>&nbsp;<label for="dllx1">无氧运动</label>
											<input name="dllx" type="radio" class="inputcheck" value="2" id="dllx2"/>&nbsp;<label for="dllx2">有氧运动</label>
										</td>
									</tr>
									<tr>
										<td align="center">坚持锻炼时间</td>
										<td colspan="3">
											<input name="jcdlsj" type="text" class="qbg" maxlength="50"  value='${grjkda.jcdlsj }' style="width:15%;"/>年
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="top">饮食习惯</td>
							<td colspan="3">
								<table width="100%">
									<colgroup>
										<col style="width:18%;"/>
										<col style="width:;"/>
										<col style="width:18%;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">饮食类型</td>
										<td colspan="3">
											<input name="yslx" type="radio" class="inputcheck" value="1" id="yslx1"/>&nbsp;<label for="yslx1">偏咸</label>
											<input name="yslx" type="radio" class="inputcheck" value="2" id="yslx2"/>&nbsp;<label for="yslx2">偏甜</label>
											<input name="yslx" type="radio" class="inputcheck" value="3" id="yslx3"/>&nbsp;<label for="yslx3">偏油</label>
											<input name="yslx" type="radio" class="inputcheck" value="4" id="yslx4"/>&nbsp;<label for="yslx4">嗜热食</label>
											<input name="yslx" type="radio" class="inputcheck" value="5" id="yslx5"/>&nbsp;<label for="yslx5">素食</label>
											<input name="yslx" type="radio" class="inputcheck" value="6" id="yslx6"/>&nbsp;<label for="yslx6">辛辣</label>
											<input name="yslx" type="radio" class="inputcheck" value="7" id="yslx7"/>&nbsp;<label for="yslx7">其他</label>
										</td>
									</tr>
									<tr>
										<td align="center">每次饮食量</td>
										<td colspan="3">
											<input name="mcysl" type="text" class="qbg" maxlength="50"  value='${grjkda.mcysl }' style="width:15%;"/>克
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="top">睡眠情况</td>
							<td colspan="3">
								<table width="100%">
									<colgroup>
										<col style="width:18%;"/>
										<col style="width:;"/>
										<col style="width:18%;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">睡眠质量</td>
										<td colspan="3">
											<input name="smzl" type="radio" class="inputcheck" value="1" id="smzl1"/>&nbsp;<label for="smzl1">正常</label>
											<input name="smzl" type="radio" class="inputcheck" value="2" id="smzl2"/>&nbsp;<label for="smzl2">睡眠困难</label>
											<input name="smzl" type="radio" class="inputcheck" value="3" id="smzl3"/>&nbsp;<label for="smzl3">入睡困难</label>
											<input name="smzl" type="radio" class="inputcheck" value="4" id="smzl4"/>&nbsp;<label for="smzl4">早醒</label>
											<input name="smzl" type="radio" class="inputcheck" value="5" id="smzl5"/>&nbsp;<label for="smzl5">梦游</label>
											<input name="smzl" type="radio" class="inputcheck" value="6" id="smzl6"/>&nbsp;<label for="smzl6">其他</label>
										</td>
									</tr>
									<tr>
										<td align="center">每天睡眠</td>
										<td colspan="3">
											<input name="mtsm" type="text" class="qbg" maxlength="50"  value='${grjkda.mtsm }' style="width:15%;"/>小时
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="top">生活方式</td>
							<td colspan="3">
								<table width="100%">
									<colgroup>
										<col style="width:18%;"/>
										<col style="width:;"/>
										<col style="width:;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">心理状况</td>
										<td colspan="3">
											<input name="xlqk" type="radio" class="inputcheck" value="1" id="xlqk1"/>&nbsp;<label for="xlqk1">紧张</label>
											<input name="xlqk" type="radio" class="inputcheck" value="2" id="xlqk2"/>&nbsp;<label for="xlqk2">抑郁</label>
											<input name="xlqk" type="radio" class="inputcheck" value="3" id="xlqk3"/>&nbsp;<label for="xlqk3">焦虑</label>
											<input name="xlqk" type="radio" class="inputcheck" value="4" id="xlqk4"/>&nbsp;<label for="xlqk4">其他</label>
										</td>
									</tr>
									<tr>
										<td rowspan="2" align="center">居住环境</td>
										<td>家中煤火取暖</td>
										<td colspan="2">
											<input name="jzmhqn" type="radio" class="inputcheck" value="1" id="jzmhqn1"/>&nbsp;<label for="jzmhqn1">否</label>
											<input name="jzmhqn" type="radio" class="inputcheck" value="2" id="jzmhqn2"/>&nbsp;<label for="jzmhqn2">有</label>
											已有：<input name="jzmhqn1" type="text" class="qbg" maxlength="50"  value='${grjkda.jzmhqn1 }' style="width:15%;"/>年
										</td>
									</tr>
									<tr>
										<td>长期居住地</td>
										<td colspan="2">
											<input name="cqjzd" type="radio" class="inputcheck" value="1" id="cqjzd1"/>&nbsp;<label for="cqjzd1">城市</label>
											<input name="cqjzd" type="radio" class="inputcheck" value="2" id="cqjzd2"/>&nbsp;<label for="cqjzd2">农村</label>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td colspan="4" class="title2">检查化验单上传</td>
						</tr>
						<tr>
							<td>检查化验单</td>
							<td colspan="3">
								<input name="jchyd" id="jchyd" type="hidden" class="qbg" maxlength="50" value="${grjkda.jchyd }"/>
									
								<input type="hidden" id="upload_url0" value="" />
								<input type="hidden" id="upload_url1" value="" />
								<input type="hidden" id="upload_url2" value="" />
								<iframe src="uploadfile_uploadfile.action?lx=imgs" id="_img_iframe" scrolling="no" frameborder="0" style="width: 100%;height: 390px;"></iframe>
							</td>
						</tr>
						<tr id="ylt">
							
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