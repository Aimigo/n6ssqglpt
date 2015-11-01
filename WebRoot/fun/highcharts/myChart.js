(function($){
	var chart;
	
	$.fn.myChart = function(options){
		var defaults = {
			id : $(this).attr("id"),	//目标DIV的ID
			
			title : '',					//标题
			subtitle : '',				//副标题
			
			type : 'line',				//图表的形状
			width : '',					//图表的宽度
			height : '',				//图表的高度
			
			borderWidth : '-1',			//图表边框宽度(IE下'0'有边框)
			borderRadius : '5',			//图表边框圆角角度
			
			xTitle:'',					//X轴标题
			yTitle:'',					//Y轴标题
			
			xCategories : '',			//X轴数据
			yCategories : '',			//Y轴数据
			series : ''					//图表数据
		}
		
		var options = $.extend(defaults,options);
		
		if(options.type == 'pie'){
			chart=new Highcharts.Chart({
				chart:{
					//renderTo:将图表插入到哪里，value为目标DIV的ID
					renderTo : options.id,
					//图表的形状,如：line(直线) 、spline（曲线）、area(直线范围)、areaspline(曲线范围)、column(竖柱状)、bar（横柱状）、scatter(点状)、pie（饼状）
					//默认为直线型的,
					defaultSeriesType : options.type,
					//图表的高度
					height : options.height,
					//图表的宽度
					width : options.width,
					//图表边框宽度,默认为0
					borderWidth : options.borderWidth,
					//图表边框圆角角度,默认为5
					borderRadius : options.borderRadius
				},
				//标题
				title: {
					text: options.title
				},
				//父标题
				subtitle: {
					text: options.subtitle		
				},
				series: options.series,
				plotOptions: {
					pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						events: {
							click: function(e) {
							//   window.href = 'http://www.google.com.hk'
							}
						}
					}
				  }
			});
		}else{
			chart=new Highcharts.Chart({
				chart:{
					//renderTo:将图表插入到哪里，value为目标DIV的ID
					renderTo : options.id,
					//图表的形状,如：line(直线) 、spline（曲线）、area(直线范围)、areaspline(曲线范围)、column(竖柱状)、bar（横柱状）、scatter(点状)、pie（饼状）
					//默认为直线型的,
					defaultSeriesType : options.type,
					//图表的高度
					height : options.height,
					//图表的宽度
					width : options.width,
					//图表边框宽度,默认为0
					borderWidth : options.borderWidth,
					//图表边框圆角角度,默认为5
					borderRadius : options.borderRadius,
					backgroundColor: 'rgba(0,0,0,0)'
				},
				//标题
				title: {
					text: options.title
				},
				//父标题
				subtitle: {
					text: options.subtitle		
				},
				xAxis: {
					title: {
						text: options.xTitle
					},
					categories: options.xCategories
				},
				yAxis: {
					title: {
						text: options.yTitle
					},
					categories: options.yCategories
				},
				legend: {
					//图例垂直布局
					//layout: 'vertical',
					//图例放置
					//align: 'center',
					//图例上下居中
					//verticalAlign: 'bottom',
					//图例边框为0
					borderWidth: 0
				},
				series: options.series
			});
		}
	}
	
	
})(jQuery);

		