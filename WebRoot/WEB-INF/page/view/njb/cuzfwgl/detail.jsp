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
<link href="fun/system/css/nchjbxx.css" type="text/css"
		rel="stylesheet">
<script type="text/javascript"
			src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
			src="${pageContext.request.contextPath}/fun/njb/Validator.js"></script>
<script type="text/javascript">
			$(function() {
				$("#update").click(function() {
					Validator.Validate(document.forms[0], 3);
				});
			});
</script>
		<body >
			<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>

			<!--主体内容  开始-->
			<div class="nchjbxx-mainbg box">

				<!--right主体内容  开始-->
				<div class="nchjbxx-mainbgright">
					<div class="nchjbxx-main">

						<!--当前位置  开始-->
						<div class="sitebg">
							<div class="site">
								<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;住户管理&nbsp;&gt;&gt;&nbsp;出租房屋管理&nbsp;&gt;&gt;&nbsp;修改</b>
							</div>
						</div>
						<!--当前位置  结束-->

						<!--表单内容  开始-->
							<div class="biaodan-all" >
								<div class="biaodan-title">出租房屋管理</div>
										<table class="biaodan" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="title2">基本信息</td>
							</tr>
							<tr>
								<td width="17%">所属网格</td>
								<td width="33%">
									${fn:trim(model.sswg) }
								</td>
								<td width="17%">实有房屋</td>
								<td width="33%">
								<c:if test="${!fn:contains(syfw.fwlx,'平房') }">${syfw.xqdz }${syfw.zhuang }(幢) ${syfw.dy }(单元) ${syfw.shi }(室)</c:if>
											<c:if test="${fn:contains(syfw.fwlx,'平房') }">${syfw.xqdz }${syfw.zhuang }(号)</c:if>
								</td>
							</tr>
							<tr>
								<td>出租人</td>
								<td>
								${model.czr }
								</td>
								<td>单位和地址</td>
								<td>${model.dwjdz }
								</td>
							</tr>
							<tr>
								<td>房子使用性质</td>
								<td>${model.fzsyxz }
								</td>
								<td>租房时间</td>
								<td>${fn:substring(model.zfsj,0,10) }
								</td>
							</tr>
						</table>
						<div class="biaodan-title">租住人信息</div>
						<c:forEach items="${zzrxxes }" var ="zzrxx">
						<table class="biaodan" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="title2">${zzrxx.xm }</td>
							</tr>
							<tr>
								<td>性别</td>
								<td>
								<c:if test="${zzrxx.xb=='男' }">男</c:if>
								<c:if test="${zzrxx.xb=='女' }">女</c:if>
								</td>
								<td>出生年月</td>
								<td>${fn:substring(zzrxx.csny ,0,10)}
								</td>
							</tr>
							<tr>
								<td>身份证号</td>
								<td>${zzrxx.sfz }
								</td>
								<td>籍贯</td>
								<td>${zzrxx.jg }
								</td>
							</tr>
							<tr>
								<td>文化程度</td>
								<td>${zzrxx.whcd }
								</td>
								<td>政治面貌</td>
								<td>${zzrxx.zzmm }
								</td>
							</tr>
							<tr>
								<td>暂住证</td>
								<td>${zzrxx.zzz }
								</td>
								<td>户口所在地</td>
								<td>${zzrxx.hkszd }
								</td>
							</tr>
							<tr>
								<td>自身流入地点</td>
								<td>${zzrxx.lrdd }
								</td>
								<td>流入时间</td>
								<td>${fn:substring(zzrxx.lrsj,0,10) }
								</td>
							</tr>
							<tr>
								<td>暂住时间</td>
								<td>${fn:substring(zzrxx.zzsj ,0,10)}
								</td>
								<td>婚姻状况</td>
								<td>${zzrxx.hyzk }
								</td>
							</tr>
							<tr>
								<td>避孕措施</td>
								<td>${zzrxx.bycs }
								</td>
								<td>避孕时间</td>
								<td>${fn:substring(zzrxx.bysj,0,10) }
								</td>
							</tr>
							<tr>
								<td>健康状况</td>
								<td>${zzrxx.jjzk }
								</td>
								<td>孕育证明</td>
								<td>${zzrxx.hyzm }
								</td>
							</tr>
							<tr>
								<td>电话</td>
								<td>${zzrxx.dh }
								</td>
								<td></td>
								<td>
								</td>
							</tr>
						</table>
						</c:forEach>
								<div class="bcan">
									</a> &nbsp; <a href="javascript:history.go(-1);"> <img
										src="fun/system/images/fh.gif">
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