<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
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
		<script type="text/javascript" src="fun/njb/operator.js"></script>
		<script type="text/javascript">
			var baseUrl = "whyl_";
			var mouseX;
			var mouseY;
			function tpyl(e,src){
				document.getElementById('tpylimg').src = src;
				var obj=document.getElementById('tpyl');
				 mouseOver(e);
			      obj.style.left = mouseX + 230 + "px";
			      obj.style.top = mouseY + 0 + "px";    
			      obj.style.display = 'block';
			
			}
			function mouseOver(obj) {
			    e = obj || window.event;
			    // 此处记录鼠标停留在组建上的时候的位置, 可以自己通过加减常量来控制离鼠标的距离.
			    mouseX =  e.layerX|| e.offsetX;
			    mouseY =  e.layerY|| e.offsetY; 
			}
			
		</script>
</head>

<body onload="MM_preloadImages('fun/system/images/nchjbxx-nav01-.png')">
	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>

	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
		
		
	<!--图片预览  开始-->    
    <div class="yiyuan-weizhi" id="tpyl" style="display:none">
	<div class="border-top1" style="width: 350px; height: 350px">
		<div class="border-top-title-bottom">
    		<div class="border-top-title">
    			<div class="iw_poi_title" >图片预览</div>
                <div style="float:right;position:absolute;top:0px;right:0px;height:30px;background-color:#f9f9f9"><div style="cursor:pointer;width:32px;height:30px"><img  style="position:absolute;top:9px;right:12px;width:10px;height:10px;-moz-user-select:none;cursor:pointer;z-index:10000;"  onclick="javascript:document.getElementById('tpyl').style.display = 'none'; " src="fun/system/images/iw_close.gif"></div></div>
            </div>
        </div>
        <div class="biaoshi-img">
        	<img id="tpylimg" style="width: 300px; height: 290px" src="fun/system/images/yiyuan.jpg" />
        </div>
    </div>
</div>
	<!--图片预览 结束-->

		
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;数码文化娱乐信息系统&nbsp;&gt;&gt;&nbsp;数码文化娱乐资源&nbsp;&gt;&gt;&nbsp;数码文化娱乐管理</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
					<form action="" method="post" id="seach">
						<div class="chaxun-form">
							<b>查询:</b> <span><label>分类名称</label> <input
								name="_page.params.flname" type="text"
								value="${_page.params.flname}" class="bd-r" /> <span> <label>标题</label>
									<input name="_page.params.bt" type="text"
									value="${_page.params.bt}" class="bd-r" /> </span> <span>
									<button class="button02" type="submit">
										<img src="fun/system/images/icon-search.png" />查询
									</button> </span> <c:if test='${fn:contains(operationlist,"增加") }'> <span>
									<button class="button02" onclick="operator('a')" type="button">
										<img src="fun/system/images/icon-add.png" />添加
									</button> </span></c:if><c:if test='${fn:contains(operationlist,"删除") }'> <span>
									<button class="button02" onClick="operator('d')" type="button">
										<img src="fun/system/images/icon-del.png" />删除
									</button> </span></c:if>
						</div>
					</form>
					<!--查询  结束-->

					<!--表格内容  开始-->
					<div class="tab-border">
						<table class="zhwxx-tab">
							<colgroup>
								<col style="width:5%;" />
								<col style="" />
								<col style="width:15%;" />
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="checkAll" id="checkAll"
										onclick="checkAll(this)" />
									</th>
									<th>标题</th>
									<th>预览图</th>
									<th>所属分类</th>
									<th>来源</th>
									<th>上传时间</th>
									<th>上传用户</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${_page.data }" var="model" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if>>
										<td><input name="check" type="checkbox"
											value="${model.id }" />
										</td>
										<td>${model.bt}</td>
										<td><img width=100px height=75px src="${model.tp}" onMouseOver="tpyl(event,'${model.tp}')" onmouseout="javascript:document.getElementById('tpyl').style.display = 'none';"/></td>
										<td>${model.flname}</td>
										<td>${model.ly}</td>
										<td><fmt:formatDate value="${model.scsj}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${model.username}</td>
										<td>
											<c:if test='${fn:contains(operationlist,"查看") }'> <a
											href="javascript:operator('x','${model.id}');">查看详情</a>&nbsp;
											</c:if><c:if test='${fn:contains(operationlist,"修改") }'>
											<a href="javascript:operator('e','${model.id}');">编辑</a>&nbsp;
											</c:if>
											<c:if test='${fn:contains(operationlist,"删除") }'> <a
											href="javascript:operator('d','${model.id}');">删除</a>&nbsp; </c:if>
										</td>
									</tr>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)}"
									end="${_page.pageRows-1}" var="i">
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