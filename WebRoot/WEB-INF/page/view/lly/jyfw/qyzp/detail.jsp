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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;职位信息动态&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
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
								${qyzp.qymc }
							</td>
						</tr>
						<tr>
							<td>联系人（<font class="fred">*</font>）</td>
							<td>
								${qyzp.lxr }
							</td>
							<td>联系电话（<font class="fred">*</font>）</td>
							<td>
								${qyzp.lxdh }
							</td>
						</tr>
						<tr>
							<td>企业地址（<font class="fred">*</font>）</td>
							<td colspan="3">
								${qyzp.dz }
							</td>
						</tr>
						<tr>
							<td valign="top">企业描述</td>
							<td colspan="3">
								${qyzp.bz }
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">职位信息</td>
						</tr>
						<tr>
							<td>招聘职位（<font class="fred">*</font>）</td>
							<td>
								${qyzp.zpzw }
							</td>
							<td>职位类别（<font class="fred">*</font>）</td>
							<td>
								${qyzp.zwlbname }
							</td>
						</tr>
						</tr>
						<tr>
							<td>职位月薪（<font class="fred">*</font>）</td>
							<td>
								${qyzp.zwyx }
							</td>
							<td>工作地点（<font class="fred">*</font>）</td>
							<td colspan="3">
								${qyzp.gzddname }
							</td>
						</tr>
						<tr>
							<td>最低学历（<font class="fred">*</font>）</td>
							<td>
								<c:choose>
									<c:when test="${qyzp.zdxl eq '00'}">文盲</c:when>
									<c:when test="${qyzp.zdxl eq '01'}">半文盲</c:when>
									<c:when test="${qyzp.zdxl eq '02'}">小学</c:when>
									<c:when test="${qyzp.zdxl eq '03'}">初中</c:when>
									<c:when test="${qyzp.zdxl eq '04'}">高中</c:when>
									<c:when test="${qyzp.zdxl eq '05'}">技工学校</c:when>
									<c:when test="${qyzp.zdxl eq '06'}">中技</c:when>
									<c:when test="${qyzp.zdxl eq '07'}">中专</c:when>
									<c:when test="${qyzp.zdxl eq '08'}">大专</c:when>
									<c:when test="${qyzp.zdxl eq '09'}">本科</c:when>
									<c:when test="${qyzp.zdxl eq '10'}">硕士</c:when>
									<c:when test="${qyzp.zdxl eq '11'}">博士</c:when>
								</c:choose>
							</td>
							<td>发布日期（<font class="fred">*</font>）</td>
							<td>
								<fmt:formatDate value="${qyzp.fbrq }" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
						<tr>
							<td valign="top">职位描述（<font class="fred">*</font>）</td>
							<td colspan="3">
								${qyzp.zwms }
							</td>
						</tr>
						<tr>
							<td valign="top">招聘启事（<font class="fred">*</font>）</td>
							<td colspan="3">
								<a href="${qyzp.zpgs }" target="_blank" style="color:blue;">下载招聘启事</a>
							</td>
						</tr>
						<tr>
							<td>填写人</td>
							<td colspan="3">
								${qyzp.username }
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