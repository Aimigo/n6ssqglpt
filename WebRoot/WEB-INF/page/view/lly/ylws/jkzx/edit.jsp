<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<script type="text/javascript" src="fun/njb/Validator.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="fun/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="fun/ueditor/ueditor.all.js"></script>
<script type="text/javascript">
	$(function(){
		$("#update").click(function(){
			var form = document.updateform;
			Validator.Validate(form,3);
		});
		
		/* 实例化编辑器  
		<c:if test="${type eq '1' }">
    	var ue = UE.getEditor('zxnr');
    	</c:if>
    	<c:if test="${type eq '2'}">
    	var ue = UE.getEditor('zxhf');
    	</c:if>
    	*/
    	
    	$("#flid").change(function(){
    		var flid = this.value;
    		
    		var html = '<option value="">请选择</option>';
    		$("#zjid").html(html);
    		
    		$.post("jkzx_getZjs.action",{flid:flid},function(data){
    			var zjid = "${jkzx.zjid}";
    			$.each(data,function(index,zj){
    				if(zjid!="" && zjid==zj.id){
    					html += '<option value="'+zj.id+'" selected="selected">'+zj.realname+'</option>';
    				}else{
    					html += '<option value="'+zj.id+'">'+zj.realname+'</option>';
    				}
    			});
    			$("#zjid").html(html);
    		},"json");
    	});
    	
    	$("#flid").val('${jkzx.flid}').change();
	});
	
	function uploadfileend(lx,file0,file1,file2){
		$("#zxtp").val(file1);
		$("#ylt").attr("src",file1);
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;医疗卫生&nbsp;&gt;&gt;&nbsp;健康咨询&nbsp;&gt;&gt;&nbsp;健康咨询&nbsp;&gt;&gt;&nbsp;<c:if test="${type eq '1' }">修改</c:if><c:if test="${type eq '2' }">咨询回复</c:if></b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="jkzx_update.action" method="post" name="updateform">
				<div class="biaodan-all">
					<div class="biaodan-title">健康咨询信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">健康咨询信息</td>
						</tr>
						<c:if test="${type eq '1' }">
						<tr>
							<td>咨询标题（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="zxbt" type="text" class="qbg" maxlength="50" value="${jkzx.zxbt }"
									dataType="Require" msg="咨询标题不能为空！" require="true"/>
								<input name="id" type="hidden" value="${jkzx.id }"/>
								<input name="zxsj" type="hidden" value='<fmt:formatDate value="${jkzx.zxsj }" pattern="yyyy-MM-dd HH:mm:ss" />'/>
								<input name="yjid" type="hidden" value="${jkzx.yjid }"/>
							</td>
						</tr>
						<tr>
							<td>咨询分类（<font class="fred">*</font>）</td>
							<td>
								<select name="flid" id="flid" class="bd-r" dataType="Require" msg="请选择咨询分类！" require="true">
									<option value="">请选择</option>
									<c:forEach items="${jkzxfllist }" var="jkzxfl">
										<option value="${jkzxfl.id }">${jkzxfl.name }</option>
									</c:forEach>
								</select>
							</td>
							<td>选择专家</td>
							<td>
								<select name="zjid" id="zjid" class="bd-r">
									<option value="">请选择</option>
								</select>
							</td>
						</tr>
						<tr>
							<td valign="top">咨询内容（<font class="fred">*</font>）</td>
							<td colspan="3">
								<textarea name="zxnr" id="zxnr" type="text" style="width:98%;" rows="15"
									dataType="Require" msg="咨询内容不能为空！" require="true">${jkzx.zxnr }</textarea>
							</td>
						</tr>
						<tr>
							<td valign="top">咨询图片（<font class="fred">*</font>）</td>
							<td>
								<input name="zxtp" id="zxtp" type="hidden" class="qbg" maxlength="50" value="${jkzx.zxtp}"
									dataType="Require" msg="咨询图片不能为空！" require="true"/>
									
								<input type="hidden" id="upload_url0" value="" />
								<input type="hidden" id="upload_url1" value="" />
								<input type="hidden" id="upload_url2" value="" />
								<iframe src="uploadfile_uploadfile.action?lx=img" id="_img_iframe" scrolling="no" frameborder="0" style="width: 100%;height: 185px;"></iframe>
							</td>
							<td valign="top">预览</td>
							<td>
								<img src="${jkzx.zxtp }" alt="" style="height:185px;" id="ylt"/>
							</td>
						</tr>
						</c:if>
						<c:if test="${type eq '2' }">
						<tr>
							<td>咨询标题（<font class="fred">*</font>）</td>
							<td colspan="3">
								${jkzx.zxbt }
								<input name="id" type="hidden" value="${jkzx.id }"/>
								<input name="zxbt" type="hidden" value="${jkzx.zxbt }"/>
								<input name="zxsj" type="hidden" value='<fmt:formatDate value="${jkzx.zxsj }" pattern="yyyy-MM-dd HH:mm:ss" />'/>
								<input name="yjid" type="hidden" value="${jkzx.yjid }"/>
								<input name="flid" type="hidden" value="${jkzx.flid }"/>
								<input name="zxnr" type="hidden" value="${jkzx.zxnr }"/>
								<input name="zxtp" type="hidden" value="${jkzx.zxtp }"/>
							</td>
						</tr>
						<tr>
							<td>咨询分类（<font class="fred">*</font>）</td>
							<td>
								${jkzx.flname }
							</td>
							<td>选择专家</td>
							<td>
								<c:if test='${jkzx.zjname eq "" or jkzx.zjname eq null}'>未选择</c:if>${jkzx.zjname}
							</td>
						</tr>
						<tr>
							<td valign="top">咨询内容（<font class="fred">*</font>）</td>
							<td colspan="3">
								${jkzx.zxnr }
							</td>
						</tr>
						<tr>
							<td valign="top">咨询图片（<font class="fred">*</font>）</td>
							<td colspan="3">
								<img src="${jkzx.zxtp }" alt="" style="height:185px;" id="ylt"/>
							</td>
						</tr>
						<tr>
							<td>咨询人（<font class="fred">*</font>）</td>
							<td>
								${jkzx.yhname }
							</td>
							<td>咨询时间（<font class="fred">*</font>）</td>
							<td>
								<fmt:formatDate value="${jkzx.zxsj }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">咨询回复</td>
						</tr>
						<tr>
							<td valign="top">咨询回复（<font class="fred">*</font>）</td>
							<td colspan="3">
								<textarea name="zxhf" id="zxhf" type="text" style="width:98%;" rows="15"
									dataType="Require" msg="咨询回复不能为空！" require="true">${jkzx.zxhf }</textarea>
							</td>
						</tr>
						
						</c:if>
					</table>
					<div class="bcan">
						<a href="#" id="update">
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