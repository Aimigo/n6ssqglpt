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
<body>
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>
	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;实有房屋管理&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
						<form action="zz_update.action" method="post" name="updateform">
							<div class="biaodan-all" style="height:800px;">
								<div class="biaodan-title">实有房屋信息</div>
								<table class="biaodan" cellpadding="0" cellspacing="0">
									<colgroup>
										<col style="width:10%;" />
										<col style="width:40%;" />
										<col style="width:10%;" />
										<col style="width:40%;" />
									</colgroup>
									<tr>
										<td colspan="4" class="title2">基本信息</td>
									</tr>
									<tr>
										<td>房屋性质</td>
										<td>${fn:trim(model.fwlx) }</td>
										<td>楼宇</td>
										<td>${fn:trim(model.lyname) } 
											</td>
									</tr>
									<tr>
									<td><c:if test="${fn:contains(model.fwlx,'平房') }">路</c:if>
									<c:if test="${!fn:contains(model.fwlx,'平房') }">小区名称</c:if></td>
										<td>${fn:trim(model.xqdz) }
										</td>
										<td>
										<c:if test="${fn:contains(model.fwlx,'平房') }">号</c:if>
									<c:if test="${!fn:contains(model.fwlx,'平房') }">幢</c:if></td>
										<td>${fn:trim(model.zhuang) }</td>
									</tr>
									<c:if test="${!fn:contains(model.fwlx,'平房') }">
									<tr>
										<td>单元</td>
										<td>${fn:trim(model.dy) }</td>
										<td>室</td>
										<td>${fn:trim(model.shi) }
											</td>
									</tr></c:if>
									<tr>
										<td>房屋居住情况</td>
										<td>${fn:trim(model.fwjzqk) } 
											</td>
										<td>房产证号</td>
										<td>${fn:trim(model.fczh) }</td>
									</tr>
									<tr>
										<td>产权人</td>
										<td>${model.cqr }
										</td>
										<td>建筑面积</td>
										<td>${model.jzmj }
										</td>
									</tr>
									<tr>
										<td>人均面积</td>
										<td colspan="3">${model.rjmj }
										</td>
									</tr>
									<tr>
								<td>建房时间</td>
								<td>${fn:substring(model.jfsj,0,10)}
								</td>
								<td>结构</td>
								<td>
								${model.jg}
								</td>
								</table>
								<div class="bcan">
									</a> &nbsp; <a href="javascript:history.go(-1);"> <img
										src="fun/system/images/fh.gif">
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