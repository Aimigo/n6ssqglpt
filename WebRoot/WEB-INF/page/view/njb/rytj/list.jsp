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
<script type="text/javascript" src="fun/njb/operator.js"></script>
<script type="text/javascript">
var baseUrl="czfwgl_";
</script>
<link href="fun/system/css/nchjbxx.css" type="text/css"
		rel="stylesheet">
		<script type="text/javascript"
			src="fun/javascript/jquery-1.8.2.min.js"></script>
		<script src="fun/highcharts/highcharts.js" type="text/javascript"></script>
		<script src="fun/highcharts/myChart.js" type="text/javascript"></script>

		<script type="text/javascript">
			
			$(function() {
				$("#one").myChart({
					title : '比例图',
					subtitle : '居民信息年龄的数据分布情况',
					width : '448',
					type : 'pie',
					series : [ {
						name : '分类下数据量',
						data :${agec}//[[' 综艺节目',21],[' 体育世界',5],[' 相声小品',3],[' 卡通动漫',3],[' 其他',3]]
					} ]
				});
				$("#two").myChart({
					title : '比例图',
					subtitle : '居民信息特殊人员的数据分布情况',
					width : '448',
					type : 'pie',
					series : [ {
						name : '分类下数据量',
						data :${tsrybt}//[[' 综艺节目',21],[' 体育世界',5],[' 相声小品',3],[' 卡通动漫',3],[' 其他',3]]
					} ]
				});
				$("#three").myChart({
					title : '户籍与非户籍人口分布图',
					width : '448',
					type : 'pie',
					series : [ {
						name : '分类下数据量',
						data :[[' 户籍人口',${data3[0][0]}],[' 非户籍人口',${data3[0][1]}]]
					} ]
				});
				
			});
			
			function goNianling(d,r){
				var s=d.split("岁")[0].split("~");
				if(s.length==2){
					location.href="jmxx_list.action?_page.params.age2="+s[0]+","+s[1];
				}else{
					location.href="jmxx_list.action?_page.params.age2="+s[0]+",200";
				}
			}
			function goTsryUrl(d){
				location.href="jmxx_list.action?_page.params.typename="+encodeURI(encodeURI(d));
			}
			function sethjrk(val){
			
				if(val==0){
					$("#three").myChart({
					title : '户籍与非户籍人口分布图',
					width : '448',
					type : 'pie',
					series : [ {
						name : '分类下数据量',
						data :[[' 户籍人口',${data3[0][0]}],[' 非户籍人口',${data3[0][1]}]]
					} ]
				});
				}else if(val==1){
					$("#three").myChart({
						title : '每月/日人口新增状况',
						//subtitle : '人口流出状况图',
						width : '448',
						type : 'column',
				         xCategories: ['户籍人口', '非户籍人口'],
						series: [{  //数据列 
				            name: '人口统计', 
				            data: [ ${data3[0][0]},${data3[0][1]}] 
				        }]
					});
				}else{
					$("#three").myChart({
						title : '时间分布图',
						//subtitle : '居民信息年龄的数据分布情况',
						width : '448',
						type : 'line',
				        xCategories: ['户籍人口', '非户籍人口'],
						series: [{  //数据列 
				            name: '人口统计', 
				            data: [ ${data3[0][0]},${data3[0][1]}] 
				        }]
					});
				}
			}
			var dat4=eval('(${json4})');
			function sethjrk2(){
				var m_grid_type_val=$("#sel_grid_type").val();
				var m_chart_type_val=$("#sel_chart_type").val();
				var type="line";
				if(m_chart_type_val==1){
					type="column";
				}
				
					var chart=new Highcharts.Chart({
							chart:{
								renderTo : "four",
								defaultSeriesType :type,
							},
							//标题
							title: {
								text: "网格户籍人口状况统计"
							},
							
			            	xAxis: [{
							    categories: dat4[0],
							    gridLineWidth:1,
							    labels : {
							                     rotation: -45
							             }
							    }],
			            	yAxis: {
					            min: 0,
					            title: {
					                text: '数量'
					            }
					        },
							series: [dat4[1][m_grid_type_val]]
						});
				}
			$(function(){
				sethjrk2();
			});
		</script>
</head>

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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;人员信息统计&nbsp;&gt;&gt;&nbsp;</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:50%;" />
								<col style="width:10%;" />
								<col style="width:10%;" />
								<col style="width:10%;" />
								<col style="width:10%;" />
								<col style="width:10%;" />
							</colgroup>
							<thead>
								<tr>
									<th colspan="6">户籍/非户籍人口分布统计</th>
								</tr>
							</thead>
							<tbody>
								<tr class="ancolorbg">
									<td  rowspan="2" style="overflow: auto;">
										<select onchange="sethjrk2()" id="sel_chart_type">
											<option value="1">柱状图</option>
											<option value="2">折线图</option>
										</select>
										<select onchange="sethjrk2()" id="sel_grid_type">
											<option value="0">全部</option>
											<option value="1">户籍人口</option>
											<option value="2">非户籍人口</option>
											<option value="3">户籍户</option>
										</select>
										<center><div id="four" class="right_foot"></div></center>
									</td>
									<td>网格</td>
									<td>全部</td>
									<td>户籍</td>
									<td>非户籍</td>
									<td>户籍户</td>
								</tr>
								<tr>
									<td colspan="5" style="padding:0px;margin:0px;">
										<div  style="padding:0px;margin:0px;height:400px;overflow-y: scroll;line-height: 300px; ">
										<table style="margin:0px; width:100%">
											<c:set var="s0" value="0"></c:set>
											<c:set var="s1" value="0"></c:set>
											<c:set var="s2" value="0"></c:set>
											<c:set var="s3" value="0"></c:set>
											<c:forEach var ="mxdat" items="${data4[0]}" varStatus="d1">
												<tr>
												<td style="width:20%;">${mxdat }</td>
												<td style="width:20%;">${data4[1][0].data[d1.index]}</td>
												<td style="width:20%;">${data4[1][1].data[d1.index]}</td>
												<td style="width:20%;">${data4[1][2].data[d1.index]}</td>
												<td style="width:17%;">${data4[1][3].data[d1.index]}</td>
												</tr>
												<c:set var="s0" value="${s0+data4[1][0].data[d1.index] }"></c:set>
												<c:set var="s1" value="${s1+data4[1][1].data[d1.index]}"></c:set>
												<c:set var="s2" value="${s2+data4[1][2].data[d1.index]}"></c:set>
												<c:set var="s3" value="${s3+data4[1][3].data[d1.index]}"></c:set>
											</c:forEach>
											<tr>
												<td style="width:20%;">总和</td>
												<td style="width:20%;">${s0 }</td>
												<td style="width:20%;">${s1 }</td>
												<td style="width:20%;">${s2 }</td>
												<td style="width:17%;">${s3 }</td>
												</tr>
										</table>
										</div>
									</td>
								</tr>
								
							</tbody>
						</table>
					</div>
					
					<!--查询  结束-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:50%;" />
								<col style="width:25%;" />
								<col style="width:25%;" />
							</colgroup>
							<thead>
								<tr>
									<th colspan="5">户籍/非户籍人口比例</th>
								</tr>
							</thead>
							<tbody>
								<tr class="ancolorbg">
									<td  rowspan="9" style="overflow: auto;">
										<select onchange="sethjrk(this.value)">
											<option value="0">饼状图</option>
											<option value="1">柱状图</option>
											<option value="2">折线图</option>
										</select>
										<center><div id="three" class="right_foot"></div></center>
									</td>
									<td>户籍</td>
									<td>非户籍</td>
								</tr>
								<tr>
									<td>${data3[0][0] }</td>
									<td>${data3[0][1] }</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!--表格内容  开始-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:15%;" />
								<col style="width:15%;" />
								<col style="width:15%;" />
							</colgroup>
							<thead>
								<tr>
									<th colspan="3">居民年龄统计比例</th>
								</tr>
							</thead>
							<tbody>
								<tr class="ancolorbg">
								<td rowspan="11"><div id="one" class="right_foot"></div></td>
									<td>年龄</td>
									<td>人数</td>
								</tr>
								<c:forEach items="${mage}" var="ag" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
									<td>${ag[0]}</td><td><a href="javascript:goNianling('${ag[0]}','${ag[1]}')">${ag[1]}</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!--表格内容  结束-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:15%;" />
								<col style="width:15%;" />
								<col style="width:15%;" />
							</colgroup>
							<thead>
								<tr>
									<th colspan="3">特殊人员统计比例</th>
								</tr>
							</thead>
							<tbody>
								<tr class="ancolorbg">
								<td rowspan="25"><div id="two" class="right_foot"></div></td>
									<td>特殊人员</td>
									<td>人数</td>
								</tr>
								<c:forEach items="${tsrylst}" var="ag" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
										<td>${ag[0]}</td><td><a href="javascript:goTsryUrl('${ag[0]}')">${ag[1]}</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
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