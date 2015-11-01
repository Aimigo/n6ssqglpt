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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;户籍管理&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
						<form action="zz_update.action" method="post" name="updateform">
							<div class="biaodan-all" >
								<div class="biaodan-title">户籍信息</div>
								<table class="biaodan" cellpadding="0" cellspacing="0">
									<colgroup>
										<col style="width:10%;" />
										<col style="width:40%;" />
										<col style="width:10%;" />
										<col style="width:40%;" />
									</colgroup>
									<tr>
										<td colspan="4" class="title2">户籍详细</td>
									</tr>
									<tr>
										<td width="17%">实有房屋</td>
										<td width="33%">
											<c:if test="${!fn:contains(syfw.fwlx,'平房') }">${syfw.xqdz }${syfw.zhuang }(幢) ${syfw.dy }(单元) ${syfw.shi }(室)</c:if>
											<c:if test="${fn:contains(syfw.fwlx,'平房') }">${syfw.xqdz }${syfw.zhuang }(号)</c:if>
										</td>
										<td width="17%">户口薄号</td>
										<td width="33%">${model.hkbh }
										</td>
									</tr>
									<tr>
										<td>电话</td>
										<td>${model.dh }
										</td>
										<td>手机</td>
										<td>${model.sj }
										</td>
									</tr>
									<tr>
										<td>身份证号</td>
										<td>${model.sfz }
										</td>
										<td>户主姓名</td>
										<td>${model.hzname }
										</td>
									</tr>
								</table>
								<div class="biaodan-title">家庭成员</div>
								<table id="hide_hjxx" class="biaodan" cellpadding="0" cellspacing="0">
									<c:forEach items="${jmxxes }" var="jmxx" varStatus="i">
									<tr name="hjgxx_tr">
										<td colspan="4" class="title2"><a href="jmxx_detail.action?id=${jmxx.id }" >户籍关系${i.index+1 }</a></td>
									</tr>
									<tr name="hjgxx_tr">
										<td>姓名</td>
										<td>${jmxx.name }
										</td>
										<td>与户主关系</td>
										<td>${jmxx.accountRelation }
										</td>
									</tr>
									<tr name="hjgxx_tr">
										<td>户主身份证号</td>
										<td>${jmxx.accountIdnumber }
										</td>
										<td>户主姓名</td>
										<td>${jmxx.accountName}
										</td>
									</tr>
									<tr name="hjgxx_tr">
										<td>户主手机</td>
										<td>${jmxx.accountCellphone }
										</td>
										<td>固定电话</td>
										<td>${jmxx.accountPhone }
										</td>
									</tr>
									<tr name="hjgxx_tr">
										<td>家庭电话</td>
										<td>${jmxx.accountFlphone }
										</td>
										<td>人户状态</td>
										<td>${jmxx.hushaiState }
										</td>
									</tr>
									<tr name="hjgxx_tr">
										<td>是否外出</td>
										<td>${jmxx.ynout}</td>
										<td>户口类别</td>
										<td>${jmxx.accountType }
										</td>
									</tr>
									<tr name="hjgxx_tr">
										<td>外出原因</td>
										<td>${jmxx.outReason }
										</td>
										<td>外出时间</td>
										<td>${fn:substring(jmxx.outTime ,0,10)}
										</td>
									</tr>
									<tr name="hjgxx_tr">
										<td>外出去向</td>
										<td colspan="3">${jmxx.outto }
										</td>
									</tr>
									<tr name="hjgxx_tr">
										<td>家庭称号</td>
										<td colspan="3">${jmxx.familyTitle}</td>
									</tr>
									</c:forEach>
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