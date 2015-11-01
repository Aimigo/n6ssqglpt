$.fn.extend({
	//图片新闻滚动展示并自动滚动
	/*
	 * 
	 * 页面代码为下面格式，必定要含有.tab的元素，用来切换，并含有与.tab数量相同的.flag元素，用来切换效果，当前展示的tab对应的flag会自动添加.active样式，
	 * 页面必定要包含.prev_button和.next_button元素，用来切换上一个或下一个.tab，走到末端会自动跳转到开头继续切换
	 * 调用方法：$("#playtab-div').playtab();
	<div id="playtab-div">
		<div>
			<div class="tab"></div>
			<div class="tab"></div>
			…
		</div>
		<div>
			<div class="prev_button"></div>												
				<ul>
					<li class="active flag"></li>
					<li class="flag"></li>
					…
				</ul>
			<div class="next_button"></div>
		</div>
	</div>
	
	 */
	playtab:function(time){
		if(!time) time = 4000;
		var panel = $(this);
		var tabs = panel.find(".tab");
		var prevBtn = panel.find(".prev_button");
		var nextBtn = panel.find(".next_button");
		var flags = panel.find(".flag");
		var index = 0, next = 1, max = tabs.length - 1;
		var width = panel.width();
		tabs.parent().css({"position":"relative"});
		tabs.css({"position":"absolute"}).hide();
		
		var changeTab = function(operate){
			if(operate < 0){
				if(index == 0) next = max;
				else next = index - 1;
			}else if(operate > 0){
				if(index == max) next = 0;
				else next = index + 1;
			}
			var cur_tab = $(tabs[index]);
			var next_tab = $(tabs[next]);
			next_tab.css({left:(width + 10) * operate + "px", top:"0px"});
			cur_tab.animate({left: (operate == -1 ? "+=" : "-=") + (width + 10) + "px"}, 500, function(){
				$(this).hide();
			});
			next_tab.show().animate({left: "0px"}, 500);
			panel.find(".active").removeClass("active");
			$(flags[next]).addClass("active");
			index = next;
		};
		
		prevBtn.bind("click", function(){
			changeTab(-1);
		});
		nextBtn.bind("click", function(){
			changeTab(1);
		});
		var changeTabTime;
		function startChangeTab(){
			clearInterval(changeTabTime);
			changeTabTime = setInterval("$('" + nextBtn.selector + "').click()", time);
		}

		function stopChangeTab(){
			clearInterval(changeTabTime);
		}
		
		panel.hover(stopChangeTab, startChangeTab);
		
		$(tabs[index]).css({"left":"0px", "top":"0px"}).show();
		$(flags[index]).addClass("active");
		
		
		startChangeTab();
	},
	//自定义下拉菜界面单项，菜单项需要div包裹的ul设定，对选中菜单将添加.choose样式，需要展示选中状态的可以设定此样式的展现，此脚本只进行菜单的切换效果，样式请在css中自己定义。
	/*以下面方式定义菜单，需要有个.text的元素，用来显示选择结果。
	 * 调用方法：$("#drop-div").dropdown();
	 * 
	<div id="drop-div">
		<span class="text">半个月</span>
		<span class="down"></span>
		<ul>
			<li onclick="">半个月</li>
			<li onclick="">一个月</li>
			<li onclick="">三个月</li>
		</ul>
	</div>
	
	 */
	dropdown:function(){
		var panel = $(this);
		var options = panel.find("ul");
		options.css({"display":"none"});
		var hidePanel = function(type){
			if(type != "self"){
				panel.click();
			}
			$("body").unbind("click", hidePanel);
			options.slideUp(100).appendTo(panel);
		};
		panel.toggle(function(){
			options.css({
				"position":"absolute", 
				"z-index":"9999", 
				"left":(panel.offset().left - options.width() / 2)+"px", 
				"top":panel.offset().top + panel.height()+"px",
				//"width":panel.width(),
				"cursor":"default"
			}).appendTo("body");
			options.slideDown(500);
			$("body").bind("click", hidePanel);
		}, function(){
			hidePanel("self");
		});
		options.find("li").click(function(){
			var li = $(this);
			panel.find(".text").text(li.text());
			options.find(".choose").removeClass("choose");
			li.addClass("choose");
			panel.click();
		});
	},
	/*
	 * 显示图片半透明蒙板，蒙板使用“.mask”的css。此脚本只定义此蒙板的显示及消失动画切换，效果请在.mask中定义。
	 * 如果此蒙板同级下有a标记，此蒙板将响应第一个a标记的超链接设定，包括href和target。
	
	 * 此div内可以添加其他布局，只要以DIV包裹，均会在显示蒙板时隐藏。如不想隐藏，可用其他标记元素并添加display:block;的方式代替DIV。
	 * 最外层的div需要position:relative样式。
	 * 调用方式：$(".video-pic").showMask();
	 * 
	<div class="video-pic">
		<a href=""><img src="imagesvideo/videopic02.jpg" /></a>
		<div>...</div>
		...
	</div>

	 */
	showMask:function(){
		var get_dir = function(elem, mouse_pos){
			if(!elem) return false;
			var pos = $(elem).offset(),
				size = {"width": $(elem).width(), "height": $(elem).height()},
				dx = mouse_pos.x - pos.left - size.width/2,
				dy = (mouse_pos.y - pos.top - size.height/2)*-1,
				eve_tan = dy/dx,
				tan = size.height/size.width;
			if(dx != 0){
				if(eve_tan > tan*-1 && eve_tan < tan && dx < 0){
					return "left";
				}else if(eve_tan > tan*-1 && eve_tan < tan && dx > 0){
					return "right";
				}else if((eve_tan > tan || eve_tan < tan*-1) && dy > 0){
					return "top";
				}else if((eve_tan > tan || eve_tan < tan*-1) && dy <= 0){
					return "bottom";
				}
			}else if(dy > 0){
				return "top";
			}else {
				return "bottom";
			}
		};
		$(this).hover(function(event){
			var animateSpeed = 200;
			var mask = $("<a></a>").addClass("mask");
			mask.hide();
			var panel = $(this);
			panel.find("div").fadeOut();
			panel.append(mask);
			//mask.fadeIn();
			var a = panel.find("a");
			mask.attr("href", a.attr("href")).attr("target", a.attr("target"));
			var dir = get_dir(panel[0], {"x":event.pageX, "y":event.pageY});
			switch(dir){
			case "left":
				mask.show().css({"width":"0px", "left":"0px", "top":"0px", "height":"100%"}).animate({"width":"100%", "opacity":0.5}, animateSpeed);
				break;
			case "right": 
				mask.show().css({"width":"0px", "left":panel.width(), "top":"0px", "height":"100%"}).animate({"width":"100%", "left":"0", "opacity":0.5}, animateSpeed);
				break;
			case "top": 
				mask.show().css({"width":"100%", "left":"0px", "top":"0px", "height":"0px"}).animate({"height":"100%", "opacity":0.5}, animateSpeed);
				break;
			case "bottom":
				mask.show().css({"width":"100%", "left":"0px", "height":"0px", "top":panel.height()}).animate({"height":"100%", "top":"0", "opacity":0.5}, animateSpeed);
				break;
			default: break;
			}
		}, function(){
			var animateSpeed = 200;
			var panel = $(this);
			var mask =panel.find(".mask");
			panel.find("div").fadeIn();
			mask.attr("href", "").attr("target", "");
			/*mask.fadeOut("normal", function(){
				$("body").append(mask);
			});*/
			var mask_finish = function(){
				mask.remove();
			};
			var dir = get_dir(panel[0], {"x":event.pageX, "y":event.pageY});
			switch(dir){
			case "left":
				mask.animate({"width":"0", "opacity":0.5}, animateSpeed, "linear", mask_finish);
				break;
			case "right": 
				mask.animate({"width":"", "left":panel.width(), "opacity":0.5}, animateSpeed, "linear", mask_finish);
				break;
			case "top": 
				mask.animate({"height":"0", "opacity":0.5}, animateSpeed, "linear", mask_finish);
				break;
			case "bottom":
				mask.animate({"height":"0", "top":panel.height(), "opacity":0.5}, animateSpeed, "linear", mask_finish);
				break;
			default: break;
			}
		});
	},
	/*
	 * 元素高亮选择，会在对应的元素内添加一层，以20%的透明度放在底层，此层的样式为.chart-mask，显示颜色需在css中自定。
	 * 调用方法：$(".chart").highlight();
	 * 理论上，此方法可以应用在任何元素上，不受界面影响，但需要高亮显示的元素的CSS为position:relative;
	<div>
		<ul>
			<li><a href="" class="chart"><img src=""/></a></li>
			<li><a href="" class="chart"><img src=""/></a></li>
			...					
		</ul>
	</div>
	 */
	highlight: function(){
		$(this).hover(function(){
			var chart = $(this);
			var chartMask = $("<div></div>");
			chartMask.addClass("chart-mask");
			chartMask.appendTo(chart).animate({"opacity":0.2});
		}, function(){
			var chart = $(this);
			chart.find(".chart-mask").remove();
		});
	}
});