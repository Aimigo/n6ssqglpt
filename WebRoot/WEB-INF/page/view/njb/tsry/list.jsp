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
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/njb/operator.js"></script>
<style type="text/css">
.flex{
	table-layout : fixed;
}
.flex td {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	text-align: center;
}
</style>
<script type="text/javascript">
var baseUrl="tsry_";
function importExcel(){
	fileUpload.click();
}
//var filename = "";

function showZd(imgname,code){
	///filename = imgname;
	$("#imgname").val(imgname);
	$.ajax({
		type:"post",
	    async:false,
		url:'excelimp_selectZd.action',
		data:{"imgname":imgname,"flid":"${_page.params.ryflid}"},
		dataType:"json",
		success:function(data){
			var select="";
			$.each(data,function(i,d){
				select+="<option value='"+i+"'>"+d+"</option>";
			});
			select+="";
			$("#nameSel").html("");
			var html="<table>";
			var i_index=0;
			html+="<tr><td style='width:300px;'>身份证</td>"+
				"<td style='width:300px;'><select name='sfz' id='id_0'>"+select+"</select></td></tr>";
			html+="<tr><td style='width:300px;'>人员姓名</td>"+
				"<td style='width:300px;'><select name='ryname' id='id_1'>"+select+"</select></td></tr>"
			<c:forEach items="${allflzd}" var="zd" varStatus="i">
				html+="<tr><td style='width:300px;'>${zd.zdname}</td>"+
					"<td style='width:300px;'><select name='${zd.datazd}' id='id_${i.index+2}'>"+select+"</select></td></tr>"
				i_index=${i.index}+3;
			</c:forEach>
			html+="</table>";
			$("#nameSel").html(html);
			$("#popDiv").show();
			for(var j=0;j<i_index;j++){
				$("#id_"+j).val(j);
			}
		}	
	});
}
$(function(){
	if("${message}"!=""&&"${message}"!="null"){
		alert("${message}");
	}
});
</script>
</head>

<body>
	<iframe src="excelimp_input.action"  style="display:none;"></iframe>
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>

	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;特殊人员管理&nbsp;&gt;&gt;&nbsp;${ryfl.name }</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<form action="" method="post" id="seach">
						<div class="chaxun-form">
							<b>查询:</b> <span> <label>人员姓名</label>
							<input type="hidden" name="_page.params.ryflid" value="${_page.params.ryflid}"/> 
								<input name="_page.params.name"
								type="text" value="${_page.params.name }" class="bd-r" /> </span> <span>
								<button class="button02" type="submit">
									<img src="fun/system/images/icon-search.png" />查询
								</button> </span>
								<span>
								<button class="button02" onclick="javascript:location.href='excelimp_getTemplet.action?flid=${_page.params.ryflid}'" type="button">
									<img src="fun/system/images/icon-add.png" />导出模板
								</button> </span>
							<%--</c:if>--%>
							<%--<c:if test='${fn:contains(operationlist,"删除") }'>--%>
							<span>
								<button class="button02" onClick="importExcel();" type="button">
									<img src="fun/system/images/icon-del.png" />导入
								</button> </span>
							<button class="button02" onclick="expExcel();" type="button">
									<img src="fun/system/images/icon-add.png" />导出
								</button> </span>
								<iframe src="" style="display:none; " id="exp_iframe"></iframe>
								<script type="text/javascript">
									function expExcel(){
										var form=$("#seach").serialize();
										$("#exp_iframe").attr("src","tsry_expExcel.action?"+form);
									}
								</script>
							<%--</c:if>--%>
							
						</div>
					</form>
					<!--查询  结束-->

					<!--表格内容  开始-->
					<div class="tab-border">
						<table class="flex zhwxx-tab">
							<colgroup>
								<col style="width:5%;" />
								<c:forEach items="${flzd }" var="zd">
									<col style="" />
								</c:forEach>
								<col style="width:15%;" />
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="checkAll" id="checkAll"
										onclick="checkAll(this)" /></th>
									<th>人员名称</th>
									<c:forEach items="${flzd }" var="zd">
										<th>${zd.zdname }</th>
									</c:forEach>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${_page.data }" var="model" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
										<td><input name="check" type="checkbox"
											value="${model.id }" /></td>
										<td>${model.ryname }</td>
										<c:forEach items="${flzd }" var="zd">
											<td>${model[zd.datazd] }</td>
										</c:forEach>
										<td>
											<c:if test='${fn:contains(operationlist,"查看") }'>
											<a href="javascript:operator('x','${model.id}');">查看详情</a>&nbsp; 
											</c:if>
										</td>
									</tr>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)}"
									end="${_page.pageRows-1}" var="i">
									<tr <c:if test="${i%2 != 0 }">class="ancolorbg"</c:if>>
										<td>&nbsp;</td><td></td>
										<c:forEach items="${flzd }" var="zd">
											<td></td>
										</c:forEach>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!--表格内容  结束-->

					<jsp:include page="/WEB-INF/page/public/pager.jsp"></jsp:include>
				</div>
				<!--选项卡下方边框  结束-->
			</div>
		</div>
		<!--right主体内容  结束-->

		<jsp:include page="/WEB-INF/page/system/index/left.jsp"></jsp:include>
		<div class="clearit"></div>
	</div>
	<!--主体内容 结束-->

	<jsp:include page="/WEB-INF/page/system/index/foot.jsp"></jsp:include>

<div class="tab-border" style="width:400px;height:200px;
	position: absolute;top:30%;left:40%;display:none;" id="popDiv">
	<form action="excelimp_excelRead.action" method="post">
		<table class="zhwxx-tab">
			<thead>
				<tr>
					<th align="left">
						选择字段
						<input type="hidden" value="${_page.params.ryflid}" name="flid" />
						<input type="hidden" value="" name="imgname" id="imgname"/>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<ul class="bgzl" id="nameSel" style="width:100%">
		            		
		                </ul>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th>
						<input type="button" value="取消" onclick="javascript:$('#popDiv').hide();"/>
		            		<input type="submit" value="提交"/>
					</th>
				</tr>
			</thead>
		</table>
	</form>
</div>
</body>
</html>