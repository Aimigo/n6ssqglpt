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
<link rel="stylesheet" href="fun/showDialog/showDialog.css" type="text/css"/>
<link rel="stylesheet" href="fun/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="fun/njb/Validator.js"></script>
<script type="text/javascript" src="fun/ztree/js/jquery.ztree.all-3.5.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="fun/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="fun/ueditor/ueditor.all.js"></script>
<script type="text/javascript">
	$(function(){
		$("#save").click(function(){
			var form = document.saveform;
			Validator.Validate(form,3);
		});
		
		/* 实例化编辑器  */
    	var bz = UE.getEditor('bz');
    	var zwms = UE.getEditor('zwms');
	});
	
	function uploadfileend(lx,file0,file1,file2){
		$("#zpgs").val(file1);
	}
	
	//展示zwfl
	function showZwfl(){
		$.ajax({
			type : 'post',
			url : 'zwfl.action',
			cache : false,
			dataType : 'html',
			success : function(data) {
				showDialog("window", data, "选择职位类别");
			},
			error : function() {
				return false;
			}
		});
	}
	
	//获取zwfl
	function getZwfl(id,name) {
		$("input[name='zwlb']").val(id);
		$("input[name='zwlbname']").val(name);
		sd_remove();
	}
	
	//展示region
	function showRegion(){
		$.ajax({
			type : 'post',
			url : 'region.action',
			cache : false,
			dataType : 'html',
			success : function(data) {
				showDialog("window", data, "选择行政区划");
			},
			error : function() {
				return false;
			}
		});
	}
	
	//获取region
	function getRegion(id,name) {
		$("input[name='gzdd']").val(id);
		$("input[name='gzddname']").val(name);
		sd_remove();
	}
	
</script>
<style type="text/css">
.edui-default .edui-editor-bottomContainer td{border: 1px solid #ccc;}
</style>
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;职位信息动态&nbsp;&gt;&gt;&nbsp;添加</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="qyzp_save.action" method="post" name="saveform">
				<div class="biaodan-all">
					<div class="biaodan-title">职位信息动态</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">企业信息</td>
						</tr>
						<tr>
							<td>企业名称（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="qymc" type="text" class="qbg" maxlength="50" 
									dataType="Require" msg="企业名称不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>联系人（<font class="fred">*</font>）</td>
							<td>
								<input name="lxr" type="text" class="qbg" maxlength="50" dataType="Require" msg="联系人不能为空！" require="true"/>
							</td>
							<td>联系电话（<font class="fred">*</font>）</td>
							<td>
								<input name="lxdh" type="text" class="qbg" maxlength="50" dataType="Require" msg="联系电话不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>企业地址（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="dz" type="text" class="qbg" maxlength="100" dataType="Require" msg="企业地址不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td valign="top">企业描述</td>
							<td colspan="3">
								<textarea name="bz" id="bz" type="text" class="qbg" style="width:98%;"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">职位信息</td>
						</tr>
						<tr>
							<td>招聘职位（<font class="fred">*</font>）</td>
							<td>
								<input name="zpzw" type="text" class="qbg" maxlength="50"
									dataType="Require" msg="招聘职位不能为空！" require="true"/>
							</td>
							<td>职位类别（<font class="fred">*</font>）</td>
							<td>
								<input name="zwlbname" type="text" class="qbg" maxlength="25" onclick="showZwfl()" readonly="readonly"/>
								<input name="zwlb" type="hidden" class="qbg" maxlength="25"
									dataType="Require" msg="职位类别不能为空！" require="true"/>
							</td>
						</tr>
						</tr>
						<tr>
							<td>职位月薪（<font class="fred">*</font>）</td>
							<td>
								<input name="zwyx" type="text" class="qbg" maxlength="25"
									dataType="Require" msg="职位月薪不能为空！" require="true"/>
							</td>
							<td>工作地点（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="gzddname" type="text" class="qbg" maxlength="100" onclick="showRegion()" readonly="readonly"/>
								<input name="gzdd" type="hidden" class="qbg" maxlength="100"
									dataType="Require" msg="请选择工作地点！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>最低学历（<font class="fred">*</font>）</td>
							<td>
								<select name="zdxl" class="bd-r" dataType="Require" msg="请选择文化程度！" require="true">
									<option value="">请选择</option>
									<option value="00">文盲</option>
									<option value="01">半文盲</option>
									<option value="02">小学</option>
									<option value="03">初中</option>
									<option value="04">高中</option>
									<option value="05">技工学校</option>
									<option value="06">中技</option>
									<option value="07">中专</option>
									<option value="08">大专</option>
									<option value="09">本科</option>
									<option value="10">硕士</option>
									<option value="11">博士</option>
								</select>
							</td>
							<td>发布日期（<font class="fred">*</font>）</td>
							<td>
								<input name="fbrq" type="text" class="qbg" maxlength="25" 
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									dataType="Require" msg="发布日期不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td valign="top">职位描述（<font class="fred">*</font>）</td>
							<td colspan="3">
								<textarea name="zwms" id="zwms" type="text" class="qbg" style="width:98%;"
									dataType="Require" msg="职位类别不能为空！" require="true"></textarea>
							</td>
						</tr>
						<tr>
							<td valign="top">招聘启事（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="zpgs" id="zpgs" type="hidden" class="qbg" maxlength="50"
									dataType="Require" msg="招聘启事不能为空！" require="true"/>
								<input type="hidden" id="upload_url0" value="" />
								<input type="hidden" id="upload_url1" value="" />
								<input type="hidden" id="upload_url2" value="" />
								<iframe src="uploadfile_uploadfile.action?lx=file" id="_img_iframe" scrolling="no" frameborder="0" style="width: 100%;height: 185px;"></iframe>
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