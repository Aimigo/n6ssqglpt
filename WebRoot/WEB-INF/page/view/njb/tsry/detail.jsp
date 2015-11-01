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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;特殊人员管理&nbsp;&gt;&gt;&nbsp;${fl.name }&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
						<form action="zz_update.action" method="post" name="updateform">
							<div class="biaodan-all" >
								<div class="biaodan-title">${fl.name }</div>
								<table class="biaodan" cellpadding="0" cellspacing="0">
									<colgroup>
										<col style="width:10%;" />
										<col style="width:40%;" />
										<col style="width:10%;" />
										<col style="width:40%;" />
									</colgroup>
									<tr>
										<td>人员姓名</td>
										<td>${model.ryname }</td>
										<td>身份证</td>
										<td>${model.sfz }</td>
										</tr>
									<c:forEach items="${flzd }" var="zd" varStatus="d">
										<c:if test="${d.index%2==0 }"><tr></c:if>
										<td>${zd.zdname }</td>
										<td>${model[zd.datazd] }</td>
										<c:if test="${d.index%2==1 }"></tr></c:if>
									</c:forEach>
									<c:if test="${fn:length(flzd)%2==1 }"><td></td><td></td></tr></c:if>
								</table>
								<%--<div class="bcan">
									</a> &nbsp; <a href="javascript:history.go(-1);"> <img
										src="fun/system/images/fh.gif">
									</a>
								</div>
							--%></div>
						</form>
						<!--表单内容 结束 -->
						<div id="jmxxinfo"></div>
						<script type="text/javascript">
						$(function(){
							$.ajax({
					    		type : "post",
					    		async: false,
					    		url : "jmxx_detail.action",
					    		data : {"sfz":"${model.sfz }","include":1},
					    		dataType : "html",
					    		success : function(data) {
					    			$("#jmxxinfo").html(data);
					    		}
					    			
							});
						});
						</script>
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