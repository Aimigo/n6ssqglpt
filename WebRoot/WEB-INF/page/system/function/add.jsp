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
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/javascript/treejs.js"></script>
<script type="text/javascript">
	$(function(){
		$("#save").click(function(){
			var form = document.saveform;
			var name = form.name.value;
			
			if(null==name||$.trim(name)==""){
				alert("请填写功能名称！");
				form.name.focus();
				return;
			}
			
			var isParent = $("[name='isParent']:checked"); 
			if(isParent.length>0){//判断是否为父功能单选框是否被选中
				if(isParent.val()==0){//如果不是父功能则需要填写URL和操作
					var url = $("#url").val();
					if(null==url||$.trim(url)==""){
						alert("请为此功能填写功能地址(URL)！");
						form.url.focus();
						return;
					}
					
					var operationxx = $("[name='operationxx']:checked");
					if(operationxx.length<=0){
						alert("请为此功能选择操作项！");
						return;
					}
				}
				
			}else{
				alert("请为此功能选择是否含有子功能！");
				return;
			}
			
			var url = "function_isRepeat.action";
			var data = {"name":name,"fcode":form.fcode.value};
			$.post(url,data,function(data){
				if(data=="1"){
					alert("功能名称重复，请重新输入功能名称！");
					form.name.focus();
					name = "";
				}else{
					form.submit();
				}
			},"html");
			
		});
	});
	/**
	 * 上传图片功能
	 */
	var imageCount = 0;
	function setImage(name){
	  	//alert(name);
	  	document.getElementById("_img"+imageCount).src="picture/"+name;
	  	document.getElementById("funicon"+imageCount).value=name;
	}
	
	function openfile(i){
		imageCount = i;
		fileUpload.click();
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;系统管理&nbsp;&gt;&gt;&nbsp;功能管理&nbsp;&gt;&gt;&nbsp;添加</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="function_save.action" method="post" name="saveform">
				<div class="biaodan-all" style="height:800px;">
					<div class="biaodan-title">功能管理</div>
					<c:if test="${null ne tblfunction.url and fn:length(tblfunction.url) > 0}">
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">功能信息</td>
						</tr>
						<tr>
							<td colspan="4">
								该功能无法添加功能！
							</td>
						</tr>
					</table>
					<div class="bcan">
						<a href="javascript:history.go(-1);">
							<img src="fun/system/images/fh.gif">
						</a>
					</div>
					</c:if>
					<c:if test="${null eq tblfunction.url or fn:length(tblfunction.url) <= 0}">
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">功能信息</td>
						</tr>
						<tr>
							<td>功能名称（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="name" id="name" type="text" class="qbg" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<td>功能图标</td>
							<td>
								<img src='fun/images/none.jpg' id="_img0" width="100px" height="100px" />
								<button class="button02" onclick="openfile(0)" type="button">
									<img src="fun/system/images/icon-add.png" />选择图片
								</button>
					 			<input type="hidden" name="imgpath" id="imgpath" class="input" />
								<input type="hidden" id="funicon0" name="funicon" value="" />
							</td>
							<td>功能图标</td>
							<td>
								<img src='fun/images/none.jpg' id="_img1" width="100px" height="100px"/>
								<button class="button02" onclick="openfile(1)" type="button">
									<img src="fun/system/images/icon-add.png" />选择图片
								</button>
								<input type="hidden" id="funicon1" name="funicon2" value="" />
								
								<iframe src="upLoadAction.action" style="display:none" id="_img_iframe" scrolling="no" frameborder="0"></iframe>
							</td>
						</tr>
						<tr>
							<td>父功能名称</td>
							<td>
								${tblfunction.name }
								<input type="hidden" name="fcode" id="fcode" readonly="readonly" value="${tblfunction.code }"/>
							</td>
							<td>所属项目</td>
							<td>
								${tblproject.name }
								<input type="hidden" name="projectcode" id="projectcode" readonly="readonly" value="${tblproject.code }"/>
							</td>
						</tr>
						<tr>
							<td>是否有子功能（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input type="radio" name="isParent" value="1" onclick="javascript:$('#isNoParent').hide();" />&nbsp;是
				  				<input type="radio" name="isParent" value="0" onclick="javascript:$('#isNoParent').show();" />&nbsp;否
							</td>
						</tr>
						<tr id="isNoParent" style="display:none">
							<td>功能URL（<font class="fred">*</font>）</td>
							<td>
								<input type="text" name="url" id="url" class="qbg" maxlength="40"/>
							</td>
							<td>功能操作（<font class="fred">*</font>）</td>
							<td>
								<input type="checkbox" name="operationxx" value="1" />&nbsp;增加
						  	 	<input type="checkbox" name="operationxx" value="2" />&nbsp;修改
						  	 	<input type="checkbox" name="operationxx" value="3" />&nbsp;删除
						  	 	<input type="checkbox" name="operationxx" value="4" />&nbsp;查看
						  	 	<input type="checkbox" name="operationxx" value="5" />&nbsp;审核
						  	 	<input type="checkbox" name="operationxx" value="6" />&nbsp;上传
						  	 	<input type="checkbox" name="operationxx" value="7" />&nbsp;打印
						  	 	<input type="checkbox" name="operationxx" value="8" />&nbsp;导出
							</td>
						</tr>
						<tr>
							<td>功能描述：</td>
							<td colspan="3">
								<input type="text" name="ms" id="ms" class="qbg" maxlength="250"/>
							</td>
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
					</c:if>
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