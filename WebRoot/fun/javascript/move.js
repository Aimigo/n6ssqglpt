/**
 * 定义事件对象标准化函数
 */
function e(event) {
    if(!event) {    // 兼容IE浏览器
        event = window.event;
        event.target = event.srcElement;
        event.layerX = event.offsetX;
        event.layerY = event.offsetY;
    }
    event.mx = event.pageX || event.clientX + document.body.scrollLeft; // 计算鼠标指针X轴距离
    event.my = event.pageY || event.clientY + document.body.scrollTop;  // 计算鼠标指针Y轴距离
    return event;   // 返回标准化的事件对象
}

/**
 * 鼠标移动处理函数
 */
function move(event) {
    event = e(event);
    var obj=document.getElementById("css_style");
    obj.style.left = event.mx-50+ "px";
    obj.style.top = event.my-13 + "px";
    $("#css_style").show();
    return false;
}
/**
 * 工具：获得某个元素的四个顶点的位置。
 */
function getposition(id){
	var op=$("#"+id);
	var offset2=op.offset();
	var h2=op.height();
	var w2=op.width();
	position1=[offset2.top,offset2.left,offset2.top+h2,offset2.left+w2];
	return position1;
}
/**
 * 松开鼠标处理函数
 */
function stop(event) {
    event = e(event);
    $("#css_style").hide();
    setValues(["tjsj_input","cczd_td","tjzd_td"],event);//给视角和查询字段 赋值
    //setCXTJ();
    document.onmousemove = document.onmouseup = null;  // 释放所有操作对象
    return false;
}
/**
 * 给查询条件赋值
 */
function setCXTJ(){
	var popdiv=$("#css_style");
	$("input[name=conditions]").each(function(){
		var th=$(this);
		var offset=th.offset();
		var left=offset.left;
		var top=offset.top;
		var h=th.height();
		var w=th.width();
		var cX=event.clientX;
		var cY=event.clientY;
		if(cX>left && cX < (left+w) && cY >top-20 && cY < top+h){
			this.value=popdiv.attr("zdname");
			this.zdid=popdiv.attr("zdid");
		}
	});
}
/**
 * 拖动停止时，将值付给当前鼠标位置的元素。
 */
function setValues(ids,event){
	var popdiv=$("#css_style");
	for(var i=0;i<ids.length;i++){
		 var pst=getposition(ids[i]);
		 var curt=document.getElementById(ids[i]);
		 if(event.mx>pst[1]-10&&event.mx<pst[3]+10&&event.my>pst[0]-10&&event.my<pst[2]+10){
			 if(ids[i]=="cczd_td"){
				 curt.innerHTML=curt.innerHTML+"<div style='border:1px solid red;' name='tjzd_div' zdid='"+popdiv.attr("zdid")+"' zdname='"+popdiv.attr("zdname")+"' zdlx='"+popdiv.attr("zdlx")+"'>"+popdiv.attr("zdname")+"</div>";
			 }else if(ids[i]=="tjsj_input"){
				 curt.value=popdiv.attr("zdname");
				 curt.zdid=popdiv.attr("zdid");
			 }else{
				 curt.innerHTML=curt.innerHTML+"<div style='border:1px solid red;' name='conditions' ><span name='conditions' zdid='"+popdiv.attr("zdid")+"'>"+popdiv.attr("zdname")+"</span>=<input type='text' name='value' value=''></div>";
			 }
		 }
	}
	
}
/**
 * 鼠标开始拖动的时，给正在移动的元素css_style复值
 */
function preparemove(op){
	var mdiv=$("#css_style");
	var mzd=$(op);
	mdiv.attr("zdid",mzd.attr("zdid"));
	mdiv.text(mzd.text());
	mdiv.attr("zdname",mzd.text());
	mdiv.attr("datazd",mzd.attr("datazd"));
	mdiv.attr("zdlx",mzd.attr("zdlx"));
	document.onmouseup = stop;
	document.onmousemove = move;
}