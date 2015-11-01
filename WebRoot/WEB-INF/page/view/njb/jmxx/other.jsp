<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<div>
<!-- 户籍关系表格区 -->
<table id="hide_hjxx" style="display:none;">
	<tr name="hjgxx_tr">
		<td colspan="4" class="title2">户籍关系</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>户口薄号（<font class="fred">*</font>）</td>
		<td><input name="accountNumber" type="text" class="qbg" value="${model.accountNumber }"
			dataType="Limit" max="50" require="true" msg="不能为空" />
			<input type="button" value="选择户籍" onclick="showHjxx();" />
		</td>
		<td>与户主关系</td>
		<td>
		<input name="accountRelation" type="radio" value="户主"
			<c:if test="${model.accountRelation eq '户主'}">checked=checked</c:if>
			class="inputcheck" dataType="Require" msg="不能为空"/>户主
			<input name="accountRelation" type="radio" value="妻"
			<c:if test="${model.accountRelation eq '妻'}">checked=checked</c:if>
			class="inputcheck"  dataType="Require" msg="不能为空"/>妻
			<input name="accountRelation" type="radio" value="长子"
			<c:if test="${model.accountRelation eq '长子'}">checked=checked</c:if>
			class="inputcheck"  dataType="Require" msg="不能为空"/>长子
			<input name="accountRelation" type="radio" value="次子"
			<c:if test="${model.accountRelation eq '次子'}">checked=checked</c:if>
			class="inputcheck"  dataType="Require" msg="不能为空"/>次子
			<input name="accountRelation" type="radio" value="长女"
			<c:if test="${model.accountRelation eq '长女'}">checked=checked</c:if>
			class="inputcheck"  dataType="Require" msg="不能为空"/>长女
			<input name="accountRelation" type="radio" value="次女"
			<c:if test="${model.accountRelation eq '次女'}">checked=checked</c:if>
			class="inputcheck"  dataType="Require" msg="不能为空"/>次女
			<input name="accountRelation" type="radio"  id="accountRelation_id"
									class="inputcheck" />其他
			<input  type="text" class="qbg"
					style="width:180px;display:none;" 
					id="accountRelation_text"/>
			<script type="text/javascript">
					$(function(){
						$("input[name=accountRelation]").each(function(index){
							if(index<=5){
								$(this).click(function(){
									$("#accountRelation_text").hide();
								});
							}else{
								$(this).click(function(){
									$("#accountRelation_text").show();
								});
							}
						});
						$("#accountRelation_text").change(function(){
							$("#accountRelation_id").val(this.value);
						});
						var sta="${model.accountRelation}";
						if(sta!="户主"&&sta!="妻"&&sta!="长子"&&sta!="次子"&&sta!="长女"&&sta!="次女"){
							$("#accountRelation_id").attr("checked",true);
							$("#accountRelation_id").val(sta);
							$("#accountRelation_text").val(sta);
							$("#accountRelation_text").show();
						}
					});
					
					</script>
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>户主身份证号</td>
		<td><input name="accountIdnumber" type="text" class="qbg" value="${model.accountIdnumber }"
			dataType="LimitB"  min="1" max="20" msg="输入不合法" require="true"/>
		</td>
		<td>户主姓名</td>
		<td><input name="accountName" type="text" class="qbg" value="${model.accountName}"/>
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>户主手机</td>
		<td><input name="accountCellphone" type="text" class="qbg" value="${model.accountCellphone }"/>
		</td>
		<td>固定电话</td>
		<td><input name="accountPhone" type="text" class="qbg" value="${model.accountPhone }"/>
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>家庭电话</td>
		<td><input name="accountFlphone" type="text" class="qbg" value="${model.accountFlphone }"/>
		</td>
		<td>人户状态</td>
		<td><input name="hushaiState" type="text" class="qbg" value="${model.hushaiState }"/>
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>是否外出</td>
		<td><input name="ynout" type="radio" value="是" 
			<c:if test="${model.ynout eq '是' }"> checked="checked" </c:if>
			class="inputcheck" />是<input name="ynout" type="radio" value="否"
			<c:if test="${model.ynout eq '否' }"> checked="checked" </c:if>
			class="inputcheck" />否</td>
		<td>户口类别</td>
		<td><input name="accountType" type="text" class="qbg"
			dataType="Limit" max="50" value="${model.accountType }"/>
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>外出原因</td>
		<td><input name="outReason" type="text" class="qbg"
			dataType="Limit" max="50" value="${model.outReason }"/>
		</td>
		<td>外出时间</td>
		<td><input name="outTime" type="text" class="qbg" readonly="readonly"
			dataType="Limit" max="50" onfocus="WdatePicker();" value="${fn:substring(model.outTime ,0,10)}"/>
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>外出去向</td>
		<td colspan="3"><input name="outto" type="text" class="qbg" value="${model.outto }"/>
		</td>
	</tr>
	<tr name="hjgxx_tr">
		<td>家庭称号</td>
		<td colspan="3"><input name="familyTitle" type="checkbox" 
			<c:if test="${model.familyTitle =='五好家庭'}" > checked="checked" </c:if>
			value="五好家庭" class="inputcheck" />五好家庭&nbsp;&nbsp;<input
			<c:if test="${model.familyTitle =='平安家庭'}" > checked="checked" </c:if>
			name="familyTitle" type="checkbox" value="平安家庭"
			class="inputcheck" />平安家庭</td>
	</tr>
</table>
<!-- 户主（家庭）表格区 -->
<table id="hide_jtxx" style="display:none;">
	<tr name="jtxx_tr">
		<td colspan="4" class="title2">户主（家庭）</td>
	</tr>
	
	<tr name="jtxx_tr">
		<td>户口薄号</td>
		<td><input name="jtxx.hkbh" type="text" class="qbg" value="${jtxx.hkbh }"/>
		</td>
		<td>户主编号</td>
		<td><input name="jtxx.hzbh" type="text" class="qbg" value="${jtxx.hzbh }"/>
		</td>
	</tr>
	<tr name="jtxx_tr">
		
		<td>家庭总人数</td>
		<td><input name="jtxx.jtzrs" type="text" class="qbg" value="${jtxx.jtzrs }"/>
		</td>
		<td>年用电量</td>
		<td><input name="jtxx.nydl"  type="text" class="qbg" value="${jtxx.nydl }"/></td>
	</tr>
	<tr name="jtxx_tr">
		<td>住房面积</td>
		<td><input name="jtxx.zfmj" type="text" class="qbg" value="${jtxx.zfmj }"/>
		</td>
		<td>套内面积</td>
		<td><input name="jtxx.tnmj" type="text" class="qbg" value="${jtxx.tnmj }"/>
		</td>
	</tr>
	<tr name="jtxx_tr">
		<td>家庭收入</td>
		<td><input name="jtxx.jtsr"  type="text" class="qbg" value="${jtxx.jtsr }"/>
		<td>家庭可支配收入</td>
		<td><input name="jtxx.jtkzpsr" type="text" class="qbg" value="${jtxx.jtkzpsr }"/>
		</td>
	</tr>
	<tr name="jtxx_tr">
		<td>家庭总支出</td>
		<td><input name="jtxx.jtzzc" type="text" class="qbg" value="${jtxx.jtzzc }" />
		</td>
		<td>食物消费支出</td>
		<td><input name="jtxx.swxfzc" type="text" class="qbg" value="${jtxx.swxfzc }"/>
		</td>
	</tr>
	<tr name="jtxx_tr">
		<td>教育支出</td>
		<td><input name="jtxx.jyzc" type="text" class="qbg" value="${jtxx.jyzc }"/>
		</td>
		<td>医疗支出</td>
		<td><input name="jtxx.ylzc"  type="text" class="qbg" value="${jtxx.ylzc}"/></td>
	</tr>
	<tr name="jtxx_tr">
		<td>文化娱乐支出</td>
		<td><input name="jtxx.whylzc"  type="text" class="qbg" value="${jtxx.whylzc }"/></td>
		<td>其他支出</td>
		<td><input name="jtxx.qtzc"  type="text" class="qbg" value="${jtxx.qtzc }"/></td>
	</tr>
</table>
<!-- 参保信息表格区 -->
<table id="hide_cbxx" style="display:none;">
	<tr name="cbxx_tr">
		<td colspan="4" class="title2">参保信息</td>
	</tr>
	<tr name="cbxx_tr">
		<td>参加养老保险（<font class="fred">*</font>）</td>
		<td><input name="cbxx.cjylbx" type="radio" 
		<c:if test="${cbxx.cjylbx }"> checked="checked" </c:if> 
			value="true" class="inputcheck" />是&nbsp;&nbsp;<input
			<c:if test="${!cbxx.cjylbx }"> checked="checked" </c:if> 
			name="cbxx.cjylbx" type="radio" value="false"
			class="inputcheck" />否</td>
		<td>养老保险月缉费</td>
		<td><input name="cbxx.ylbxyjf" type="text" class="qbg" value="${cbxx.ylbxyjf}"/>
		</td>
	</tr>
	<tr name="cbxx_tr">
		<td>养老保险参加时间</td>
		<td><input name="cbxx.ylbxcbsj" type="text" class="qbg" value="${fn:substring(cbxx.ylbxcbsj,0,10) }"
			  onfocus="WdatePicker();" readonly="readonly" />
		</td>
		<td></td>
		<td>
		</td>
	</tr>
	<tr name="cbxx_tr">
		<td>参加医疗保险（<font class="fred">*</font>）</td>
		<td><input name="cbxx.cjylbx1" type="radio" 
			<c:if test="${cbxx.cjylbx1 }"> checked="checked" </c:if> 
			value="true" class="inputcheck" />是&nbsp;&nbsp;<input
			name="cbxx.cjylbx1" type="radio" value="false"
			<c:if test="${!cbxx.cjylbx1 }"> checked="checked" </c:if> 
			class="inputcheck" />否</td>
		<td>养老保险月缉费</td>
		<td><input name="cbxx.ylbxyjf1" type="text" class="qbg" value="${cbxx.ylbxyjf1 }"/></td>
	</tr>
	<tr name="cbxx_tr">
		<td>医疗保险参加时间</td>
		<td><input name="cbxx.ylbxcbsj1" type="text" class="qbg" value="${fn:substring(cbxx.ylbxcbsj1,0,10) }"
			  onfocus="WdatePicker();" readonly="readonly"/>
		</td>
		<td></td>
		<td>
		</td>
	</tr>
	<tr name="cbxx_tr">
		<td>参加失业保险</td>
		<td><input name="cbxx.cjsybx" type="radio"
		<c:if test="${cbxx.cjsybx }"> checked="checked" </c:if> 
			value="true" class="inputcheck" />是&nbsp;&nbsp;<input
			name="cbxx.cjsybx" type="radio" value="false"
			<c:if test="${!cbxx.cjsybx }"> checked="checked" </c:if> 
			class="inputcheck" />否</td>
		<td>失业保险月缉费</td>
		<td><input name="cbxx.sybxyjf" type="text" class="qbg" value="${cbxx.sybxyjf }"/></td>
	</tr>
	<tr name="cbxx_tr">
		<td>失业保险参保时间</td>
		<td><input name="cbxx.sybxcbsj" type="text" class="qbg" value="${fn:substring(cbxx.sybxcbsj,0,10 )}"
			  onfocus="WdatePicker();" readonly="readonly"/>
		</td>
		<td></td>
		<td>
		</td>
	</tr>
	<tr name="cbxx_tr">
		<td>参加工伤保险</td>
		<td><input name="cbxx.cjgsbx" type="radio"
			<c:if test="${cbxx.cjgsbx }"> checked="checked" </c:if> 
			value="true" class="inputcheck" />是&nbsp;&nbsp;<input
			name="cbxx.cjgsbx" type="radio" value="false"
			<c:if test="${!cbxx.cjgsbx }"> checked="checked" </c:if> 
			class="inputcheck" />否</td>
		<td>参加生育保险</td>
		<td><input name="cbxx.cjsybx1" type="radio"
			<c:if test="${cbxx.cjsybx1 }"> checked="checked" </c:if> 
			value="true" class="inputcheck" />是&nbsp;&nbsp;<input
			name="cbxx.cjsybx1" type="radio" value="false"
			<c:if test="${cbxx.cjsybx1 }"> checked="checked" </c:if> 
			class="inputcheck" />否</td>
	</tr>
</table>
<!-- 学生信息表格区 -->
<table id="hide_xsxx" style="display:none;">
	<tr name="xsxx_tr">
		<td colspan="4" class="title2">学生信息</td>
	</tr>
	<tr name="xsxx_tr">
		<td>性别（<font class="fred">*</font>）</td>
		<td><input name="xsxx.xb" type="radio"
			<c:if test="${xsxx.xb eq '男'}"> checked="checked" </c:if> 
			value="男" class="inputcheck" />男&nbsp;&nbsp;<input
			name="xsxx.xb" type="radio" value="女"
			<c:if test="${xsxx.xb eq '女'}"> checked="checked" </c:if> 
			class="inputcheck" />女</td>
		<td>出生年份</td>
		<td><input name="xsxx.csnf" type="text" class="qbg" value="${xsxx.csnf }"/>
		</td>
	</tr>
	<tr name="xsxx_tr">
		<td>学习阶段</td>
		<td><input name="xsxx.xxjd" type="text" class="qbg" value="${xsxx.xxjd }"/>
		</td>
		<td>学校</td>
		<td><input name="xsxx.xx" type="text" class="qbg" value="${xsxx.xx }"/>
		</td>
	</tr>
	<tr name="xsxx_tr">
		<td>入学时间</td>
		<td><input name="xsxx.rxsj"  type="text" class="qbg" value="${fn:substring(xsxx.rxsj,0,10) }"
		 onfocus="WdatePicker();" readonly="readonly"/></td>
		<td>预计毕业时间</td>
		<td><input name="xsxx.yjbysj" type="text" class="qbg" value="${fn:substring(xsxx.yjbysj,0,10) }"
		 onfocus="WdatePicker();" readonly="readonly"/>
		</td>
	</tr>
</table>
<!-- 租住人信息表格区 -->
<table id="hide_zzfw" style="display:none;">
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
</table>
</div>
<!--户籍  开始-->
<div class="tab-border" style="width:400px;height:200px;position: absolute;top:1300px;left:40%;display:none;" id="popDiv">
	<table class="zhwxx-tab">
		<thead>
			<tr>
				<th align="left">
					<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">收起所有</a>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<!--  弹出中心内容  -->
					<div id="showTree" style="text-align:left;vertical-align:top;height:377px;overflow-y:auto;"></div>
					<script type="text/javascript">
						//<!--
						d = new dTree('d');
						d.add(0, -1, '户口薄信息');
						<c:forEach items="${hjgl}" var="huji">
							d.add('${huji.id}',0,'${huji.hkbh}',"javascript:gethkbh('${huji.hkbh}');");
						</c:forEach>
						$("#showTree").html(d.toString());
						function showhk(){
							$("#popDiv").show();
						}
						function gethkbh(hkbh){
							$("#popDiv").hide();
							$("input[name=accountNumber]").val(hkbh);
							checkValHK();
						}
						//-->
					</script>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<script type="text/javascript">
$(function(){
	if("${model.huzhu}"=="1"){	
		$("#showjtxx").after($("#hide_jtxx").find("tr[name=jtxx_tr]"));
	}
	if("${model.canbao}"=="1"){
		$("#showjtxx").after($("#hide_cbxx").find("tr[name=cbxx_tr]"));
	}
	if("${model.xuesheng}"=="1"){
		$("#showjtxx").after($("#hide_xsxx").find("tr[name=xsxx_tr]"));
	}
});

</script>
</body>
</html>