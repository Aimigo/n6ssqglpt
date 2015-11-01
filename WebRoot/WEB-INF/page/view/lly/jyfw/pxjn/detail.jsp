<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript">

</script>

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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;技能培训多媒体&nbsp;&gt;&gt;&nbsp;多媒体管理&nbsp;&gt;&gt;&nbsp;详细</b>
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
								${pxjn.bt }
							</td>
						</tr>
						<tr>
							<td>类型（<font class="fred">*</font>）</td>
							<td>
								<c:if test="${pxjn.type eq 1 }">文档</c:if>
								<c:if test="${pxjn.type eq 2 }">视频</c:if>
							</td>
							<td>多媒体分类（<font class="fred">*</font>）</td>
							<td>
								${pxjn.flname }
							</td>
						</tr>
						<tr valign="top">
							<td valign="top">描述（<font class="fred">*</font>）</td>
							<td colspan="3">
								${pxjn.ms }
							</td>
						</tr>
						<c:if test="${pxjn.type eq 1 }">
						<tr>
							<td valign="top">在线浏览（<font class="fred">*</font>）</td>
							<td colspan="3">
								<iframe src="uploadfile_readonline.action?w=745&h=580&s=<%=basePath %>${pxjn.dz}"
									style="width:98%;height:600px;" id="_img_iframe" ></iframe>
							
							</td>
						</tr>
						</c:if>
						<c:if test="${pxjn.type eq 2 }">
						<tr>
						<td>视频预览</td>
								<td colspan="3">
									<iframe src="uploadfile_videoplayer.action?w=600&h=400&vs=<%=basePath %>${pxjn.dz}&is=<%=basePath %>${pxjn.tp}&ms=${pxjn.ms}&p=0&wh=16:9"
		style="width:600px; height: 400px;border: 0px" id="_img_iframe" scrolling="no" ></iframe>
								</td>
							<%--<td valign="top">视频浏览（<font class="fred">*</font>）</td>
							<td colspan="3">
								<object id="vcastr3" width="650" height="500" data="upload/tools/vcastr3.swf" type="application/x-shockwave-flash">
									<param value="upload/tools/vcastr3.swf" name="movie">
									<param value="true" name="allowFullScreen">
									<param value="xml= <vcastr> <channel> <item> <source><%=basePath %>${pxjn.dz}</source> </item> </channel> </vcastr>" name="FlashVars">
								</object>
							</td>
						--%></tr>
						</c:if>
						<tr>
							<td>上传用户（<font class="fred">*</font>）</td>
							<td>
								${pxjn.username }
							</td>
							<td>上传时间（<font class="fred">*</font>）</td>
							<td>
								<fmt:formatDate value="${pxjn.scsj }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
					</table>
					<div class="bcan">
						<a href="javascript:history.go(-1);">
							<img src="fun/system/images/fh.gif">
						</a>
					</div>
				</div>
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