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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;医疗卫生&nbsp;&gt;&gt;&nbsp;健康咨询&nbsp;&gt;&gt;&nbsp;健康咨询&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<div class="biaodan-all">
					<div class="biaodan-title">健康咨询信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
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
						<c:if test="${jkzx.hfsj ne null }">
						<tr>
							<td colspan="4" class="title2">咨询回复</td>
						</tr>
						<tr>
							<td valign="top">咨询回复（<font class="fred">*</font>）</td>
							<td colspan="3">
								${jkzx.zxhf }
							</td>
						</tr>
						<tr>
							<td>回复专家（<font class="fred">*</font>）</td>
							<td>
								${jkzx.zjname }
							</td>
							<td>咨询时间（<font class="fred">*</font>）</td>
							<td>
								<fmt:formatDate value="${jkzx.hfsj }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						</c:if>
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