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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fun/javascript/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			
			$(function() {
				$("#one").myChart({
					title : '本月/本日人口新增状况',
					width : '800',
					type : 'column',
			        xCategories :eval('${data1[0]}'),
					series: eval('${data1[1]}')
				});
				$("#two").myChart({
					title : '时间分布图',
					width : '800',
					type : 'line',
			        xCategories: eval('${data2[0]}'),
					series: eval('([${data2[1]}])')
				});
			});
			function timetj(){
				$.ajax({
		    		type : "post",
		    		async: false,
		    		url : "jmxx_getFlzd.action",
		    		data : {"flid":$(this).val()},
		    		dataType : "json",
		    		success : function(data) {
		    			$("#three").myChart({
							title : '时间分布图',
							width : '800',
							type : 'line',
					        xCategories: eval('${data2[0]}'),
							series: eval('([${data2[1]}])')
						});
		    		}
				});
				
			}
			
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;流出人口统计&nbsp;&gt;&gt;&nbsp;</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--表格内容  开始-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:70%;" />
								<col style="width:14%;" />
								<col style="width:16%;" />
							</colgroup>
							<thead>
								<tr>
									<th colspan="3">人口流出状况图</th>
								</tr>
							</thead>
							<tbody>
								<tr class="ancolorbg">
								<td rowspan="32" style="overflow: auto;">
								<form action="">
								<select id="data_type" onchange="gocha(this.value);">
									<option value="0">年</option>
									<option value="1">月</option>
								</select>
								<span id="data_span"><input type="text"  id="time_d" style="width:50px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'});" value="" onchange="goSerach();"/></span>
								网格<select id="grid_d" onchange="goSerach();">
									<option value="0">全部</option>
									<option value="场部一区网格">场部一区网格</option>
									<option value="场部二区网格">场部二区网格</option>
									<option value="场部三区网格">场部三区网格</option>
									<option value="场部四区网格">场部四区网格</option>
									<option value="场部五区网格">场部五区网格</option>
									<option value="场部六区网格">场部六区网格</option>
									<option value="场部七区网格">场部七区网格</option>
									<option value="一连一区网格">一连一区网格</option>
									<option value="一连二区网格">一连二区网格</option>
									<option value="二连一区网格">二连一区网格</option>
									<option value="二连二区网格">二连二区网格</option>
									<option value="三连网格">三连网格</option>
									<option value="四连一区网格">四连一区网格 </option>
									<option value="四连二区网格">四连二区网格</option>
									<option value="五连网格">五连网格</option>
									<option value="六连网格 ">六连网格 </option>
									<option value="七连网格">七连网格</option>
									<option value="八连一区网格">八连一区网格 </option>
									<option value="八连二区网格">八连二区网格</option>
									<option value="葡萄公司网格">葡萄公司网格</option>
								</select>
								<select id="shape" onchange="_tj();">
									<option value="column">柱状</option>
									<option value="line">折线</option>
								</select>
								<script type="text/javascript">
									var tj_data={};
									function goSerach(){ 
										var grid=$("#grid_d").val();
										var time=$("#time_d").val();
										var type=$("#data_type").val();
										if(time==null||time==""){
											alert("时间不能为空");
											return;
										}
										if(type=="0" && time.length!=4){
											alert("日期输入不合法。1");
											return ;
										}
										if( type=="1" &&time.length!=7&&time.length!=6){
											alert("日期输入不合法。2");
											return ;
										}
										$.ajax({
								    		type : "post",
								    		async: false,
								    		url : "rktj_lcrytj.action",
								    		data : {"grid":grid,"time":time,"type":type},
								    		dataType : "json",
								    		success : function(data) {
								    			tj_data=data;
								    			_tj();
								    			d_show();
								    		}
										});
									}
									function d_show(){
										var tj=tj_data[0];
										var html="<table style='width:100%'>"
										for(var i=0;i<tj.length;i++){
											html+="<tr><td style='width:50%'>"+tj[i]+"</td><td style='width:50%'>"+tj_data[1].data[i]+"</td></tr>";
										}
										html+="</table>";
										$("#d_show").html(html);
									}
									function gocha(val){
										var dataFmt="yyyy";
										var d=new Date();
										var value=d.getFullYear();
										if(val!=0){
											dataFmt="yyyy-MM";
											value=d.getFullYear()+"-"+(d.getMonth()+1);
										}
										$("#data_span").html('<input type="text"  id="time_d" style="width:50px;" onfocus="WdatePicker({skin:\'whyGreen\',dateFmt:\''+dataFmt+'\'});" value="'+value+'" onchange="goSerach();"/>');
										goSerach();
									}
									function _tj(){
										var type=$("#shape").val();
										var chart=new Highcharts.Chart({
											chart:{
												renderTo : "three",
												defaultSeriesType :type,
											},
											//标题
											title: {
												text: "人口流入 统计"
											},
											
							            	xAxis: [{
											    categories: tj_data[0],
											    gridLineWidth:1,
											    labels : { rotation: -45}
											    }],
							            	yAxis: {
									            min: 0,
									            title: {
									                text: '数量'
									            }
									        },
											series: [tj_data[1]]
										});
									}
									$(function(){
										$("#time_d").val(new Date().getFullYear());
										goSerach();
									});
								</script>
								</form>
								<div id="three" class="right_foot"></div>
								</td>
									<td>日期</td>
									<td>数量</td>
								</tr>
								<tr><td colspan="2"  style="padding:0px;overflow-y:scroll;height:400px;">
								<div id="d_show"  style="padding:0px;overflow-y:scroll;height:400px;"> </div></td></tr>
							</tbody>
						</table>
					</div>
					
					
					
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
									<th colspan="4">人口流出状况图</th>
								</tr>
							</thead>
							<tbody>
								<tr class="ancolorbg">
								<td rowspan="9" id="blt" style="overflow: auto;">
								<div id="one" class="right_foot"></div></td>
									<td>所属网格</td>
									<td>今日新增</td>
									<td>本月新增</td>
								</tr>
								<script type="text/javascript">
								var dt1=eval('${data1[0]}');
								var dt2= eval('${data1[1]}');
								var html="";
								for(var i=0;i<dt1.length;i++){
									//var br_tr="";
									var br=(dt2[1].data[i]?dt2[1].data[i]:"0");
									var br_tr=(br<=1500?"green":(br<=2000?"yellow":"red"));
									var by=(dt2[0].data[i]?dt2[0].data[i]:"0");
									var by_tr=(by<=1500?"green":(by<=2000?"yellow":"red"));
									html+="<tr "+((i%2==1)?"class='ancolorbg'":"")+"><td>"+dt1[i]+"</td>"+
									"<td><span style='color:"+br_tr+"'>"+br+"</span></td>"+
									"<td><span style='color:"+by_tr+"'>"+by+"</span></td></tr>";
								}
								document.write(html);
								$("#blt").attr("rowspan",dt1.length+1);
								</script>
								
							</tbody>
						</table>
					</div>
					<!--表格内容  结束-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:70%;" />
								<col style="width:15%;" />
								<col style="width:15%;" />
							</colgroup>
							<thead>
								<tr>
									<th colspan="3">时间统计图</th>
								</tr>
							</thead>
							<tbody>
								<tr class="ancolorbg">
								<td rowspan="13"><div id="two" class="right_foot"></div></td>
									<td>时间</td>
									<td>人数</td>
								</tr>
								<script type="text/javascript">
								var dt1=eval('${data2[0]}');
								var dt2= eval('(${data2[1]})');
								var html="";
								for(var i=0;i<dt1.length;i++){
									var br=(dt2.data[i]?dt2.data[i]:"0");
									var br_tr=(br<=1500?"green":(br<=2000?"yellow":"red"));
									html+="<tr "+((i%2==1)?"class='ancolorbg'":"")+"><td>"+dt1[i]+"</td><td><span style='color:"+br_tr+"'>"+br+"</span></tr>";
								}
								document.write(html);
								</script>
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