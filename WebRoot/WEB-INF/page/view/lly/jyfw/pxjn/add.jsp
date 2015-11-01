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
<script type="text/javascript" src="fun/njb/Validator.js"></script>
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
    	var ue = UE.getEditor('ms');
    	
	});
	
	function uploadfileend(lx,file0,file1,file2){
		//alert(lx +" | "+file0+" | "+file1+" | "+file2);
		<c:if test='${_page.params.type eq 2}'>
		$("#tp").val(file1);
		</c:if>
		$("#dz").val(file2);
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;技能培训多媒体&nbsp;&gt;&gt;&nbsp;多媒体管理&nbsp;&gt;&gt;&nbsp;添加</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--选项卡  开始-->
			    <div class="tdzy-tab">
			        <ul>
			          <li class="tab-<c:if test='${_page.params.type ne 1}'>no</c:if>selected"  style="cursor:pointer;" onclick="javascript:location.href='pxjn_list.action?dtype=1';"><a href="#">文档</a></li>
			          <li class="tab-<c:if test='${_page.params.type ne 2}'>no</c:if>selected"  style="cursor:pointer;" onclick="javascript:location.href='pxjn_list.action?dtype=2';"><a href="#">视频</a></li>
			        </ul>
			    </div>
				<!--选项卡  结束-->
				
				<!--表单内容  开始-->
				<form action="pxjn_save.action" method="post" name="saveform">
				<div class="biaodan-all">
					<div class="biaodan-title">多媒体管理信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">多媒体管理信息</td>
						</tr>
						<tr>
							<td>标题（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="bt" type="text" class="qbg" maxlength="50" 
									dataType="Require" msg="标题不能为空！" require="true"/>
								<input name="_page.params.type" type="hidden" value="${_page.params.type }"/>
								<input name="type" type="hidden" value="${_page.params.type }"/>
							</td>
						</tr>
						<tr>
							<td>多媒体分类（<font class="fred">*</font>）</td>
							<td colspan="3">
								<select name="flid" dataType="Require" msg="多媒体分类不能为空！" require="true">
									<option value="">请选择</option>
									<c:forEach items="${jnfllist }" var="jnfl">
										<option value="${jnfl.id }">${jnfl.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td valign="top">描述（<font class="fred">*</font>）</td>
							<td colspan="3">
								<textarea name="ms" id="ms" type="text" style="width:98%;"
									dataType="Require" msg="描述不能为空！" require="true"></textarea>
							</td>
						</tr>
						<c:if test='${_page.params.type eq 1}'>
						<tr>
							<td valign="top">上传文件（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="dz" id="dz" type="hidden" class="qbg" maxlength="50" value=""
									dataType="Require" msg="附件不能为空！" require="true"/>
									
								<input type="hidden" id="upload_url0" value="" />
								<input type="hidden" id="upload_url1" value="" />
								<input type="hidden" id="upload_url2" value="" />
								<iframe src="uploadfile_uploadfile.action?lx=doc" id="_img_iframe" scrolling="no" frameborder="0" style="width: 100%;height: 185px;"></iframe>
							</td>
						</tr>
						</c:if>
						<c:if test='${_page.params.type eq 2}'>
						<tr>
							<td valign="top">上传视频（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="dz" id="dz" type="hidden" class="qbg" maxlength="50" value=""
									dataType="Require" msg="附件不能为空！" require="true"/>
									
								<input type="hidden" id="tp" name="tp" value=""/>
								<input type="hidden" id="upload_url0" value="" />
								<input type="hidden" id="upload_url1" value="" />
								<input type="hidden" id="upload_url2" value="" />
								<iframe src="uploadfile_uploadfile.action?lx=media" id="_img_iframe" scrolling="no" frameborder="0" style="width: 100%;height: 185px;"></iframe>
							</td>
						</tr>
						</c:if>
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