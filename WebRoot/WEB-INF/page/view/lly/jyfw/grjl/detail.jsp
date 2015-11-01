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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;个人就业信息管理&nbsp;&gt;&gt;&nbsp;详细</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<div class="biaodan-all">
					<div class="biaodan-title">个人就业信息管理信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">登记信息</td>
						</tr>
						<tr>
							<td>登记表编号（<font class="fred">*</font>）</td>
							<td colspan="3">
								${grjl.djbbh }
							</td>
						</tr>
						<tr>
							<td>登记日期（<font class="fred">*</font>）</td>
							<td>
								<fmt:formatDate value="${grjl.djsj }" pattern="yyyy-MM-dd" />
							</td>
							<td>有效期至（<font class="fred">*</font>）</td>
							<td>
								<fmt:formatDate value="${grjl.yxq }" pattern="yyyy-MM-dd" />
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">个人信息</td>
						</tr>
						<tr>
							<td>姓名（<font class="fred">*</font>）</td>
							<td>
								${grjl.xm }
							</td>
							<td>身份证号（<font class="fred">*</font>）</td>
							<td>
								${grjl.sfzh }
							</td>
						</tr>
						<tr>
							<td>性别（<font class="fred">*</font>）</td>
							<td>
								${grjl.xb }
							</td>
							<td>出生日期（<font class="fred">*</font>）</td>
							<td>
								<fmt:formatDate value="${grjl.csrq }" pattern="yyyy-MM-dd" />
							</td>
						</tr>
						<tr>
							<td>民族（<font class="fred">*</font>）</td>
							<td>
								${grjl.mz }
							</td>
							<td>文化程度（<font class="fred">*</font>）</td>
							<td>
								<c:choose>
									<c:when test="${grjl.whcd eq '00'}">文盲</c:when>
									<c:when test="${grjl.whcd eq '01'}">半文盲</c:when>
									<c:when test="${grjl.whcd eq '02'}">小学</c:when>
									<c:when test="${grjl.whcd eq '03'}">初中</c:when>
									<c:when test="${grjl.whcd eq '04'}">高中</c:when>
									<c:when test="${grjl.whcd eq '05'}">技工学校</c:when>
									<c:when test="${grjl.whcd eq '06'}">中技</c:when>
									<c:when test="${grjl.whcd eq '07'}">中专</c:when>
									<c:when test="${grjl.whcd eq '08'}">大专</c:when>
									<c:when test="${grjl.whcd eq '09'}">本科</c:when>
									<c:when test="${grjl.whcd eq '10'}">硕士</c:when>
									<c:when test="${grjl.whcd eq '11'}">博士</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>照片（<font class="fred">*</font>）</td>
							<td colspan="3">
								<img src="${grjl.zp}" alt="" style="height:185px;" id="ylt"/>
							</td>
						</tr>
						<tr>
							<td>人员类别（<font class="fred">*</font>）</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${grjl.rylb eq '01'}">失业职工</c:when>
									<c:when test="${grjl.rylb eq '02'}">失业青年</c:when>
									<c:when test="${grjl.rylb eq '03'}">其他失业人员</c:when>
									<c:when test="${grjl.rylb eq '10'}">在业人员</c:when>
									<c:when test="${grjl.rylb eq '20'}">下岗职工</c:when>
									<c:when test="${grjl.rylb eq '30'}">离退休人员</c:when>
									<c:when test="${grjl.rylb eq '40'}">在校学生</c:when>
									<c:when test="${grjl.rylb eq '99'}">其他人员</c:when>
									<c:when test="${grjl.rylb eq '44'}">在职</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>户口所在地（<font class="fred">*</font>）</td>
							<td>
								${grjl.hksz }
							</td>
							<td>户口状况（<font class="fred">*</font>）</td>
							<td>
								<c:choose>
									<c:when test="${grjl.hkzk eq '1'}">城镇户口</c:when>
									<c:when test="${grjl.hkzk eq '2'}">农村户口</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>健康状况（<font class="fred">*</font>）</td>
							<td>
								${grjl.jkzk }
							</td>
							<td>婚姻状况（<font class="fred">*</font>）</td>
							<td>
								${grjl.hyzk }
							</td>
						</tr>
						<tr>
							<td>职称（<font class="fred">*</font>）</td>
							<td>
								${grjl.zc }
							</td>
							<td>政治面貌（<font class="fred">*</font>）</td>
							<td>
								${grjl.zzmm }
							</td>
						</tr>
						<tr>
							<td>身高（<font class="fred">*</font>）</td>
							<td>
								${grjl.sg }
							</td>
							<td>视力（<font class="fred">*</font>）</td>
							<td>
								${grjl.sl }
							</td>
						</tr>
						<tr>
							<td>详细地址（<font class="fred">*</font>）</td>
							<td>
								${grjl.xxdz }
							</td>
							<td>联系电话（<font class="fred">*</font>）</td>
							<td>
								${grjl.lldh }
							</td>
						</tr>
						<tr>
							<td>联系人（<font class="fred">*</font>）</td>
							<td>
								${grjl.lxr }
							</td>
							<td>邮编（<font class="fred">*</font>）</td>
							<td>
								${grjl.yb }
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">具有专业</td>
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%">
									<colgroup>
										<col style="width:5%;"/>
										<col style="width:;"/>
										<col style="width:;"/>
										<col style="width:;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">序号</td>
										<td align="center">专业工种</td>
										<td align="center">技能等级</td>
										<td align="center">从事年限</td>
										<td align="center">说明</td>
									</tr>
									<tr>
										<td align="center">1</td>
										<td>
											${grjl.zygz1 }
										</td>
										<td>
											${grjl.jsdj1 }
										</td>
										<td>
											${grjl.csnx1 }
										</td>
										<td>
											${grjl.sm1 }
										</td>
									</tr>
									<tr>
										<td align="center">2</td>
										<td>
											${grjl.zygz2 }
										</td>
										<td>
											${grjl.jsdj2 }
										</td>
										<td>
											${grjl.csnx2 }
										</td>
										<td>
											${grjl.sm2 }
										</td>
									</tr>
									<tr>
										<td align="center">3</td>
										<td>
											${grjl.zygz3 }
										</td>
										<td>
											${grjl.jsdj3 }
										</td>
										<td>
											${grjl.csnx3 }
										</td>
										<td>
											${grjl.sm3 }
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">求职愿望</td>
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%">
									<colgroup>
										<col style="width:5%;"/>
										<col style="width:;"/>
										<col style="width:;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">序号</td>
										<td align="center">择业工种</td>
										<td align="center">月薪要求</td>
										<td align="center">经济类型</td>
									</tr>
									<tr>
										<td align="center">1</td>
										<td>
											${grjl.qzywname1 }
										</td>
										<td>
											${grjl.yxyq1 }
										</td>
										<td>
											${grjl.jjlx1 }
										</td>
									</tr>
									<tr>
										<td align="center">2</td>
										<td>
											${grjl.qzywname2 }
										</td>
										<td>
											${grjl.yxyq2 }
										</td>
										<td>
											${grjl.jjlx2 }
										</td>
									</tr>
									<tr>
										<td align="center">3</td>
										<td>
											${grjl.qzywname3 }
										</td>
										<td>
											${grjl.yxyq3 }
										</td>
										<td>
											${grjl.jjlx3 }
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>其他求职要求</td>
							<td colspan="3">
								${grjl.qtqzyq }
							</td>
						</tr>
						<tr>
							<td>外语及熟练程度</td>
							<td colspan="3">
								${grjl.wyslcd }
							</td>
						</tr>
						<tr>
							<td>其他技能</td>
							<td colspan="3">
								${grjl.qtjn }
							</td>
						</tr>
						<tr>
							<td>择业地区（<font class="fred">*</font>）</td>
							<td colspan="3">
								${grjl.zydqname }
							</td>
						</tr>
						<tr>
							<td>学习工作培训简历</td>
							<td colspan="3">
								${grjl.xxgzpxjl }
							</td>
						</tr>
						<tr>
							<td>备注</td>
							<td colspan="3">
								${grjl.bz }
							</td>
						</tr>
						<tr>
							<td>填写人</td>
							<td colspan="3">
								${grjl.username }
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