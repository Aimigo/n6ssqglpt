<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共青团农场社区管理平台</title>
<link href="fun/system/css/css.css" type="text/css" rel="stylesheet">
<link href="fun/system/css/nchjbxx.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script src="fun/highcharts/highcharts.js" type="text/javascript"></script>
<script src="fun/highcharts/myChart.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		var chart=new Highcharts.Chart({
				chart:{
					renderTo : "one",
					defaultSeriesType :'column',
				},
				//标题
				title: {
					text: "比例图"
				},
				//父标题
				subtitle: {
					text: '关于系统中多媒体管理的分布情况'	
				},
				xAxis: {
            		categories:${cate}
            	},
            	yAxis: {
		            min: 0,
		            title: {
		                text: '数量'
		            }
		        },
				series: ${seri}/* ,
				plotOptions: {
					column: {
						allowPointSelect: true,
						cursor: 'pointer',
						events: {
							click: function(e) {
								if(e.point.series.name=="视频"){
									location.href=encodeURI("pxjn_list.action?dtype=2&fl="+e.point.category);
								}else if(e.point.series.name=="文档"){
									location.href=encodeURI("pxjn_list.action?dtype=1&fl="+e.point.category);
								}
							}
						}
					}
				} */
			});
	});
</script>
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;技能培训多媒体&nbsp;&gt;&gt;&nbsp;多媒体管理</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--表格内容  开始-->
				<div class="tab-border">
					<table class="zhwxx-tab">
						<colgroup>
							<col style="width:70%;" />
							<col style="width:10%;" />
							<col style="width:10%;" />
							<col style="width:10%;" />
						</colgroup>
						<thead>
							<tr>
								<th colspan="4">
									多媒体管理的分布图
									<button class="button02" type="button" style="float:right;" onclick="javascript:location.href='pxjn_list.action';">
										<img src="fun/system/images/icon-search.png" />进入列表
									</button>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr class="ancolorbg">
								<td rowspan="${fn:length(map['Cate'])*2 + 1}" id="blt" style="overflow:auto;">
									<div id="one" class="right_foot"></div>
								</td>
								<td style="font-weight: bolder;">多媒体分类</td>
								<td style="font-weight: bolder;">多媒体类型</td>
								<td style="font-weight: bolder;">多媒体数量</td>
							</tr>
							<c:forEach items="${map['Cate'] }" var="cate" varStatus="i">
								<tr>
									<td rowspan="2">${cate}</td>
									<c:forEach items="${map['Seri'] }" var="seri" varStatus="j">
										<c:if test="${j.count eq 2}"><tr></c:if>
											<td>${seri.name}</td>
											<td>${seri.data[i.index] }</td>
										</tr>
									</c:forEach>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!--表格内容  结束-->
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