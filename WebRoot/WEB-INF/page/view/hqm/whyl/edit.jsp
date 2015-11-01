<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="fun/showDialog/showDialog.css"
	type="text/css" />
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/javascript/treejs.js"></script>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fun/njb/Validator.js"></script>
<script type="text/javascript">
var sclx='';
	$(function() {
		showCont();
		showxglx();
		$("input[name=lx]").click(function() {
			showxglx();
		});
		$("#save").click(function() {
			if ($("#upload_url1").val() != "") {
				$("#tp").val($("#upload_url1").val());
				$("#dz").val($("#upload_url2").val());
			}
			var selectedvalue = '${whyl.ly}';
			switch (selectedvalue) {
			case "来自网络":
				$("#dz").val($("#dz2").val());
				
				break;
			case "拷贝文件":
				$("#dz").val($("#dz1").val());
				break;
			case "系统上传":
				if(sclx=="img")$("#dz").val('${whyl.dz}');
				break;
			default:
				break;
			}
			Validator.Validate(document.forms[0], 3);
		});
	});
	function showCont() {
		var selectedvalue = '${whyl.ly}';
		switch (selectedvalue) {
		case "来自网络":
			$("#xglx").hide();
			$("#sctp").show();
			$("#wjlj").hide();
			$("#ljdz").show();
			break;
		case "拷贝文件":
			$("#xglx").hide();
			$("#sctp").show();
			$("#wjlj").show();
			$("#ljdz").hide();
			break;
		case "系统上传":
			$("#xglx").show();
			$("#wjlj").hide();
			$("#ljdz").hide();
			break;
		default:
			break;
		}
	}
	function showxglx() {
		var selectedvalue = $("input[name='lx']:checked").val();
		switch (selectedvalue) {
		case "xgtp":
			$("#scsp").hide();
			$("#sctp").show();
			break;
		case "xgsp":
			$("#scsp").show();
			$("#sctp").hide();
			break;
		default:
			break;
		}
	}
	function getfl(id, name) {
		$("#flid").val(id);
		$("#flname").val(name);
		sd_remove();
	}
	//上传完毕回电函数
	function uploadfileend(type, file0, file1, file2) {
		sclx=type;
		if (type == "img") {
			$("#tpyl").attr("src", file1);
		}
		if (type == "media") {
			sleep(1000);
			$("#tpyl1").attr("src", file1);
		}
	}
	//等待时间函数
			function sleep(numberMillis) {
				var now = new Date();
				var exitTime = now.getTime() + numberMillis;
				while (true) {
					now = new Date();
					if (now.getTime() > exitTime)
						return;
				}
			}
	/*显示树形结构*/
	function showTree() {

		$.ajax({
			type : "post",
			url : "whyl_tree.action",
			dataType : "html",
			success : function(data) {
				/*
					1.mode 模式
						a. confirm [确认模式]
						b. info [显示信息]
						b. window [AJAX获取网页内容]
						c. alert [警告模式]
					2. msg 内容
						显示弹出宽口的内容
					3. t 标题
						显示弹出窗口的标题
					4. sd_width 宽度
						显示弹出窗口的宽度
				 */
				showDialog("window", data, "选择分类操作");
			}
		});
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;数码文化娱乐信息系统&nbsp;&gt;&gt;&nbsp;数码文化娱乐资源&nbsp;&gt;&gt;&nbsp;数码文化娱乐管理&nbsp;&gt;&gt;&nbsp;修改</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--表单内容  开始-->
				<form action="whyl_update.action" method="post" name="saveform">
					<div class="biaodan-all" style="height:100%;">
						<div class="biaodan-title">数码文化娱乐资源</div>
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<colgroup>
								<col style="width:10%;" />
								<col style="width:40%;" />
								<col style="width:10%;" />
								<col style="width:40%;" />
							</colgroup>
							<tr>
								<td colspan="4" class="title2">文化娱乐资源管理</td>
							</tr>
							<tr>
								<td>标题（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="id" type="hidden"
									value="${whyl.id}" /> <input name="scsj" type="hidden"
									value="${whyl.scsj}" />
									<input name="ly" type="hidden"
									value="${whyl.ly}" /> <input name="bt" type="text"
									class="qbg" value="${whyl.bt}" dataType="LimitB" min="1"
									max="50" msg="标题必填，并且长度不得大于50." require="true" />
								</td>
							</tr>
							<tr>
								<td>文化娱乐分类（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="flid" id="flid" type="hidden"
									class="qbg" value="${whyl.flid}" /> <input name="flname"
									id="flname" type="text" value="${whyl.flname}"
									onclick="showTree()" readonly="readonly" class="qbg"
									dataType="Require" msg="分类不能为空" />
								</td>
							</tr>
							<tr id="xglx">
								<td>修改类型（<font class="fred">*</font>）</td>
								<td colspan="3"><label><input name="lx"
										type="radio" value="xgtp" checked="checked" class="inputcheck" />修改预览图片</label>&nbsp;&nbsp;<label>
										<input name="lx" type="radio" value="xgsp" class="inputcheck" />修改上传文件</label>
								</td>
							</tr>
							<tr id="sctp" style="height:180px;">
								<td>修改预览图片（<font class="fred">*</font>）</td>
								<td colspan="3">
									<div style="float: left;width:70%;">
										<iframe src="uploadfile_uploadfile.action?lx=img"
											style="width:100%; height: 180px; border: 0px"
											id="_img_iframe" scrolling="no"></iframe>
									</div>
									<div style="float: left;width:30%;">
										<img id="tpyl" width=100% height=100% src="${whyl.tp}" />
									</div>
								</td>
							</tr>
							<tr id="scsp">
								<td>修改上传视频（<font class="fred">*</font>）</td>
								<td colspan="3">
									<div style="float: left;width:70%;">
										<input type="hidden" id="upload_url0" value="" /> <input
											type="hidden" id="upload_url1" value="" /> <input
											type="hidden" id="upload_url2" value="" />
										<iframe src="uploadfile_uploadfile.action?lx=media"
											style="width:100%; height: 200px; border: 0px"
											id="_img_iframe" scrolling="no"></iframe>
										<input id="tp" name="tp" type="hidden" value="${whyl.tp}" />
										<input name="dz" id="dz" type="hidden" value="${whyl.dz}"
											dataType="Require" msg="请选择文件并上传" />
									</div>
									<div style="float: left;width:30%;">
										<img id="tpyl1" width=100% height=100% src="${whyl.tp}" />
									</div></td>
							</tr>

							<tr id="wjlj">
								<td>填写文件路径（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="dz1" id="dz1" type="text"
									class="qbg" dataType="Require" value="${whyl.dz}" msg="请填写文件路径" />
								</td>
							</tr>
							<tr id="ljdz">
								<td>填写链接地址（<font class="fred">*</font>）</td>
								<td colspan="3"><input name="dz2" id="dz2" type="text"
									class="qbg" dataType="Require" value="${whyl.dz}" msg="请填写链接地址" />
								</td>
							</tr>

							<tr>
								<td>描述</td>
								<td colspan="3"><textarea name="ms" style="width:99%;"
										rows="15" dataType="Limit" max="500" msg="不得超过500字">${whyl.ms}</textarea>
								</td>
							</tr>

						</table>
						<div class="bcan">
							<a href="#" id="save"> <img src="fun/system/images/bc.gif" />
							</a> &nbsp; <a href="javascript:history.go(-1);"> <img
								src="fun/system/images/fh.gif" /> </a>
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