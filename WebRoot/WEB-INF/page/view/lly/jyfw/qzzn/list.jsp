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
<link rel="stylesheet" href="fun/showDialog/showDialog.css" type="text/css"/>
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript">
	function sendMessage(grid,qyid,type){
		$.ajax({
			type : "post",
			url : "qzzn_sendMessage.action",
			data : {
				"grid" : grid,
				"qyid" : qyid,
				"type" : type
			},
			dataType : "html",
			success : function(data) {
				alert("发送成功！");
			}
		});
	}
	
	function pplist(id){
		var type = '${_page.params.type}';
		$.ajax({
			type : "post",
			url : "qzzn_pplist.action",
			data : {
				"id" : id,
				"type" : type
			},
			dataType : "html",
			success : function(data) {
				if(type=="1")
					showDialog("window",data, "智能匹配 - 个人简历信息" , 1280);
				else if(type=="2")
					showDialog("window",data, "智能匹配 - 企业信息" , 1280);
			}
		});
	}
	
	$(function(){
		$("td a").click(function(event){
			event.stopPropagation(); // do something
		});
	})
	
</script>
<style>
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;求职咨询智能服务</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡  开始-->
			    <div class="tdzy-tab">
			        <ul>
			          <li class="tab-<c:if test='${_page.params.type ne 1}'>no</c:if>selected" style="cursor:pointer;" onclick="javascript:location.href='qzzn_list.action?dtype=1';"><a href="#">企业智能匹配</a></li>
			          <li class="tab-<c:if test='${_page.params.type ne 2}'>no</c:if>selected" style="cursor:pointer;" onclick="javascript:location.href='qzzn_list.action?dtype=2';"><a href="#">个人智能匹配</a></li>
			        </ul>
			    </div>
				<!--选项卡  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					
					<form action="qzzn_list.action?dtype=${_page.params.type }" method="post">
					<div class="chaxun-form">
						<b>查询:</b>
						<c:if test='${_page.params.type eq 1}'>
						<span>
							<label>招聘职位</label>
							<input name="_page.params.zpzw" type="text" value="${_page.params.zpzw}" class="bd-r" />
						</span>
						<span>
							<label>发布日期</label>
							<input name="_page.params.begintime" type="text" value='${_page.params.begintime}' class="bd-r" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							 - <input name="_page.params.endtime" type="text" value='${_page.params.endtime}' class="bd-r" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</span>
						</c:if>
						<c:if test='${_page.params.type eq 2}'>
						<span>
							<label>登记表编号</label>
							<input name="_page.params.djbbh" type="text" value="${_page.params.djbbh }" class="bd-r" />
						</span>
						<span>
							<label>姓名</label>
							<input name="_page.params.xm" type="text" value="${_page.params.xm }" class="bd-r" />
						</span>  
						<span>
							<label>身份证号</label>
							<input name="_page.params.sfzh" type="text" value="${_page.params.sfzh }" class="bd-r" />
						</span>
						</c:if>
						<span>
							<button class="button02" type="submit">
								<img src="fun/system/images/icon-search.png" />查询
							</button>
						</span>
					</div>
					</form>
					<!--查询  结束-->
					
					<!--表格内容  开始-->
					<div class="tab-border">
						<table class="zhwxx-tab">
						<c:if test='${_page.params.type eq 1}'>
							<colgroup>
								<col style=""/>
								<col style="width:15%;"/>
								<col style="width:15%;"/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:15%;"/>
							</colgroup>
							<thead>
								<tr>
									<th>企业名称</th>
									<th>招聘职位</th>
									<th>职位类别</th>
									<th>工作地点</th>
									<th>最低学历</th>
									<th>发布日期</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${_page.data }" var="obj" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
										<td>${obj.qymc}</td>
										<td>${obj.zpzw}</td>
										<td>${obj.zwlbname}</td>
										<td>${obj.gzddname}</td>
										<td>
											<c:choose>
												<c:when test="${obj.zdxl eq '00'}">文盲</c:when>
												<c:when test="${obj.zdxl eq '01'}">半文盲</c:when>
												<c:when test="${obj.zdxl eq '02'}">小学</c:when>
												<c:when test="${obj.zdxl eq '03'}">初中</c:when>
												<c:when test="${obj.zdxl eq '04'}">高中</c:when>
												<c:when test="${obj.zdxl eq '05'}">技工学校</c:when>
												<c:when test="${obj.zdxl eq '06'}">中技</c:when>
												<c:when test="${obj.zdxl eq '07'}">中专</c:when>
												<c:when test="${obj.zdxl eq '08'}">大专</c:when>
												<c:when test="${obj.zdxl eq '09'}">本科</c:when>
												<c:when test="${obj.zdxl eq '10'}">硕士</c:when>
												<c:when test="${obj.zdxl eq '11'}">博士</c:when>
											</c:choose>
										</td>
										<td><fmt:formatDate value="${obj.fbrq}" pattern="yyyy-MM-dd"/></td>
										<td>
											<c:if test='${fn:contains(operationlist,"查看") }'>
												<a href="javascript:void(0);" onclick="pplist('${obj.id}')">智能匹配</a>&nbsp;
												<a href="javascript:location.href='qyzp_detail.action?id=${obj.id}';">查看详情</a>&nbsp;
											</c:if>
											<c:if test='${fn:contains(operationlist,"修改") }'>
												<a href="javascript:sendMessage(null,'${obj.id }',1);">群发短信</a>
											</c:if>
										</td>
									</tr>
									<%--
									<tr id="tr${i.count}" style="display:none;">
										<td colspan="7">
											<c:if test="${fn:length(map[obj.id]) <= 0 }">没有匹配的人员</c:if>
											<c:if test="${fn:length(map[obj.id]) > 0 }">
											<!-- MAP -->
											<table style="width:98%;float:right;" class="zhwxx-tab">
												<colgroup>
													<col style="width:10%;"/>
													<col style=""/>
													<col style="width:10%;"/>
													<col style="width:10%;"/>
													<col style="width:10%;"/>
													<col style="width:10%;"/>
													<col style="width:10%;"/>
													<col style="width:10%;"/>
												</colgroup>
												<thead>
													<tr>
														<th>登记表编号</th>
														<th>姓名</th>
														<th>身份证号</th>
														<th>择业地区</th>
														<th>学历</th>
														<th>联系电话</th>
														<th>有效期</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${map[obj.id] }" var="grjl" varStatus="j">
														<tr <c:if test="${j.count%2 == 0 }">class="ancolorbg"</c:if>>
															<td>${grjl.djbbh}</td>
															<td>${grjl.xm}</td>
															<td>${grjl.sfzh}</td>
															<td>${grjl.zydqname}</td>
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
															<td>${grjl.lldh}</td>
															<td><fmt:formatDate value="${grjl.yxq }" pattern="yyyy-MM-dd" /> </td>
															<td>
																<c:if test='${fn:contains(operationlist,"查看") }'>
																	<a href="javascript:location.href='grjl_detail.action?id=${grjl.id}';">查看详情</a>&nbsp;
																</c:if>
																<c:if test='${fn:contains(operationlist,"修改") }'>
																	<a href="javascript:sendMessage('${grjl.id }','${obj.id }',2);">发送短信</a>
																</c:if>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<!-- MAP -->
											</c:if>
										</td>
									</tr>
									--%>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)}" end="${_page.pageRows-1}" var="i">
									<tr <c:if test="${i%2 != 0 }">class="ancolorbg"</c:if>>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>
						<c:if test='${_page.params.type eq 2}'>
							<colgroup>
								<col style="width:10%;"/>
								<col style=""/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:10%;"/>
								<col style="width:15%;"/>
							</colgroup>
							<thead>
								<tr>
									<th>登记表编号</th>
									<th>姓名</th>
									<th>身份证号</th>
									<th>择业地区</th>
									<th>学历</th>
									<th>联系电话</th>
									<th>有效期</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="currentday">
									<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd" type="date"/>
								</c:set>
								<c:forEach items="${_page.data }" var="obj" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if> style="<c:if test='${obj.yxq < currentday }'>color:gray;</c:if>">
										<td>${obj.djbbh}</td>
										<td>${obj.xm}</td>
										<td>${obj.sfzh}</td>
										<td>${obj.zydqname}</td>
										<td>
											<c:choose>
												<c:when test="${obj.whcd eq '00'}">文盲</c:when>
												<c:when test="${obj.whcd eq '01'}">半文盲</c:when>
												<c:when test="${obj.whcd eq '02'}">小学</c:when>
												<c:when test="${obj.whcd eq '03'}">初中</c:when>
												<c:when test="${obj.whcd eq '04'}">高中</c:when>
												<c:when test="${obj.whcd eq '05'}">技工学校</c:when>
												<c:when test="${obj.whcd eq '06'}">中技</c:when>
												<c:when test="${obj.whcd eq '07'}">中专</c:when>
												<c:when test="${obj.whcd eq '08'}">大专</c:when>
												<c:when test="${obj.whcd eq '09'}">本科</c:when>
												<c:when test="${obj.whcd eq '10'}">硕士</c:when>
												<c:when test="${obj.whcd eq '11'}">博士</c:when>
											</c:choose>
										</td>
										<td>${obj.lldh}</td>
										<td><fmt:formatDate value="${obj.yxq }" pattern="yyyy-MM-dd" /> </td>
										<td>
											<c:if test='${fn:contains(operationlist,"查看") }'>
												<a href="javascript:void(0);" onclick="pplist('${obj.id}')">智能匹配</a>&nbsp;
												<a href="javascript:location.href='grjl_detail.action?id=${obj.id }';">查看详情</a>&nbsp;
											</c:if>
											<c:if test='${fn:contains(operationlist,"修改") }'>	
												<a href="javascript:sendMessage('${obj.id }',null,3);">群发短信</a>
											</c:if>
										</td>
									</tr>
									<%--
									<tr id="tr${i.count}" style="display:none;">
										<td colspan="8">
											<c:if test="${fn:length(map[obj.id]) <= 0 }">没有匹配的企业</c:if>
											<c:if test="${fn:length(map[obj.id]) > 0 }">
											<!-- MAP -->
											<table style="width:98%;float:right;" class="zhwxx-tab">
												<colgroup>
													<col style=""/>
													<col style="width:15%;"/>
													<col style="width:15%;"/>
													<col style="width:10%;"/>
													<col style="width:10%;"/>
													<col style="width:10%;"/>
													<col style="width:10%;"/>
												</colgroup>
												<thead>
													<tr>
														<th>企业名称</th>
														<th>招聘职位</th>
														<th>职位类别</th>
														<th>工作地点</th>
														<th>最低学历</th>
														<th>发布日期</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${map[obj.id] }" var="qyzp" varStatus="j">
														<tr <c:if test="${j.count%2 == 0 }">class="ancolorbg"</c:if>>
															<td>${qyzp.qymc}</td>
															<td>${qyzp.zpzw}</td>
															<td>${qyzp.zwlbname}</td>
															<td>${qyzp.gzddname}</td>
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
															<td><fmt:formatDate value="${qyzp.fbrq}" pattern="yyyy-MM-dd"/></td>
															<td>
																<c:if test='${fn:contains(operationlist,"查看") }'>
																	<a href="javascript:location.href='qyzp_detail.action?id=${qyzp.id}';">查看详情</a>&nbsp;
																</c:if>
																<c:if test='${fn:contains(operationlist,"修改") }'>
																	<a href="javascript:sendMessage('${obj.id }','${qyzp.id }',2);">发送短信</a>
																</c:if>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<!-- MAP -->
											</c:if>
										</td>
									</tr>
									--%>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)}" end="${_page.pageRows-1}" var="i">
									<tr <c:if test="${i%2 != 0 }">class="ancolorbg"</c:if>>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>
						</table>
					</div>
					<!--表格内容  结束-->

					<jsp:include page="/WEB-INF/page/public/pager.jsp"></jsp:include>
				</div>
				<!--选项卡下方边框  结束-->
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