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
<script type="text/javascript" src="fun/njb/operator.js"></script>
<script type="text/javascript">
var baseUrl="jmxx_";
function importExcel(){
	fileUpload.click();
}
</script>
</head>

<body>

	<jsp:include page="/WEB-INF/page/system/index/head.jsp"></jsp:include>
<iframe src="jmxx_input.action"  style="display:none;"></iframe>

	<!--主体内容  开始-->
	<div class="nchjbxx-mainbg box">

		<!--right主体内容  开始-->
		<div class="nchjbxx-mainbgright">
			<div class="nchjbxx-main">

				<!--当前位置  开始-->
				<div class="sitebg">
					<div class="site">
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;人员管理&nbsp;&gt;&gt;&nbsp;人员信息管理</b>
					</div>
				</div>
				<!--当前位置  结束-->

				<!--选项卡下方边框  开始-->
				<div class="tdzy-tab-down">
					<!--查询  开始-->
						<div class="chaxun-form">
							<b>查询:</b> <span><form action="" method="post" id="seach"> 
							<label>居民姓名</label> <input name="_page.params.name"
								type="text" value="${_page.params.name }" class="bd-r" />
								<button class="button02" type="submit">
									<img src="fun/system/images/icon-search.png" />查询
								</button> </form></span>
								
								<span>
								<button class="button02" onclick="showgjcx()">
									<img src="fun/system/images/icon-search.png" />高级查询
								</button> 
								<script type="text/javascript">
								function showgjcx(){
										$("#popDiv").show();
								}
								</script>
								</span>
							<c:if test='${fn:contains(operationlist,"增加") }'>
							<span>
								<button class="button02" onclick="operator('a')" type="button">
									<img src="fun/system/images/icon-add.png" />添加
								</button> </span>
							</c:if>
							<c:if test='${fn:contains(operationlist,"删除") }'>
							<span>
								<button class="button02" onClick="operator('d')" type="button">
									<img src="fun/system/images/icon-del.png" />删除
								</button> </span>
							</c:if>
							<span>
								<button class="button02" onclick="javascript:location.href='jmxx_getTemplet.action?flid=${_page.params.ryflid}'" type="button">
									<img src="fun/system/images/icon-add.png" />导出模板
								</button> </span>
							<span>
								<button class="button02" onClick="importExcel();" type="button">
									<img src="fun/system/images/icon-del.png" />导入
								</button> </span>
								<span>
								<button class="button02" onClick="javascript:$('#daoDiv').show();" type="button">
									<img src="fun/system/images/icon-add.png" />导出
								</button> </span>
								<script type="text/javascript">
									function expExcel(){
										var form=$("#expform").serialize();
										var form2=$("#zdform").serialize();
										var param=encodeURI("jmxx_expExcel.action?"+form+"&"+form2);
										$("#exp_iframe").attr("src",param);
									}
								</script>
								<iframe id="exp_iframe" style="display:none"></iframe>
						</div>
					<!--查询  结束-->
					<jsp:useBean id="now" class="java.util.Date" />
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
										onclick="checkAll(this)" /></th>
									<th>户信息</th>
									<th>姓名</th>
									<th>性别</th>
									<th>联系手机</th>
									<th>民族</th>
									<th>年龄</th>
									<th>政治面貌</th><th>行驶证</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="hxx" value="0"></c:set>
								<c:forEach items="${_page.data }" var="model" varStatus="i">
									<tr <c:if test="${i.count%2 == 0 }">class="ancolorbg"</c:if> name="jmxx_${model.accountNumber}" id="jmxx_${model.id }"
									>
										<td><input name="check" type="checkbox"
											value="${model.id }" /></td>
										<td><c:if test="${(model.accountNumber ne hxx) or (model.accountNumber eq '')or (model.accountNumber eq null)}">
										<c:set var="hxx" value="${model.accountNumber}"></c:set>户信息</c:if></td>
										<td>${model.name}<c:if test="${model.isotherwg eq 1}"><span style="color:red;">(多)</span></c:if></td>
										<td>${model.gender}</td>
										<td>${model.cellphone}</td>
										<td>${model.nation}</td>
										<td><c:if test="${model.birthday ne null}">${now.year-model.birthday.year}</c:if></td>
										<td>${model.politics}</td>
										<td>${model.xingshizheng}</td>
										<td>
											<c:if test='${fn:contains(operationlist,"查看") }'> <a
												href="javascript:operator('x','${model.id}');">查看详情</a>&nbsp; 
											</c:if>
											<c:if test='${fn:contains(operationlist,"修改") }'> <a
											href="javascript:operator('e','${model.id}');">编辑</a>&nbsp; </c:if>
											<c:if test='${fn:contains(operationlist,"删除") }'><a
											href="javascript:operator('d','${model.id}');">删除</a>&nbsp;
											</c:if>
										</td>
									</tr>
									<c:if test="${model.accountRelation eq '户主'}">
										<script type="text/javascript">
											$($("[name=jmxx_${model.accountNumber}]:first").find("td").get(1)).html("");
											$($("#jmxx_${model.id}").find("td").get(1)).html("户信息");
											$("[name=jmxx_${model.accountNumber}]:first").before($("#jmxx_${model.id}"));
										</script>
									</c:if>
								</c:forEach>
								<c:forEach begin="${fn:length(_page.data)}"
									end="${_page.pageRows-1}" var="i">
									<tr <c:if test="${i%2 != 0 }">class="ancolorbg"</c:if>>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>	<td>&nbsp;</td>
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
<div class="tab-border" style="width:500px;height:250px;position: absolute;
top:200px;left:40%;display:none;" id="popDiv">
<div class="zhwxx-tab" style="text-align:left;vertical-align:top;height:300px;overflow-y:auto;overflow-x:hidden;">
<form action="" method="post" id="expform">
<div class="chaxun-form" style="text-align: center;height:30px;font-size:15px;padding:10px;"><b>高级查询</b></div>
             	<ul style="padding: 10px;">
                 	<li class="xxchx-from"><b>姓名：<input type="text" class="w100" name="_page.params.name" value="${_page.params.name }"/></b></li>
                     <li class="xxchx-from"><b>性别：</b>
                   	  <input type="radio" value="男" name="_page.params.sex" 
                   	  <c:if test="${_page.params.sex eq '男' }">checked="checked"</c:if>/>男
                    	<input type="radio" value="女" name="_page.params.sex" 
                    	<c:if test="${_page.params.sex eq '女' }">checked="checked"</c:if>/> 女</li>
                     <li class="xxchx-from"><b>年龄：</b>
                     
                     	<input name="_page.params.age" type="checkbox" value="-1" <c:if test="${fn:contains(_page.params.age,'-1') }">checked="checked"</c:if>/><label>全部</label>
                     	<span><input type="checkbox"  value="0_5" name="_page.params.age" <c:if test="${fn:contains(_page.params.age,'0_5') }">checked="checked"</c:if>><label>0~5岁</label></span>
                     	<span><input type="checkbox"  value="6_10" name="_page.params.age" <c:if test="${fn:contains(_page.params.age,'6_10') }">checked="checked"</c:if>><label>6~10岁</label></span>
                     	<span><input type="checkbox"  value="11_15" name="_page.params.age" <c:if test="${fn:contains(_page.params.age,'11_15') }">checked="checked"</c:if>><label>11~15岁</label></span>
                     	<span><input type="checkbox"  value="16_20" name="_page.params.age" <c:if test="${fn:contains(_page.params.age,'16_20') }">checked="checked"</c:if>><label>16~20岁</label></span>
                     	<span><input type="checkbox"  value="21_25" name="_page.params.age" <c:if test="${fn:contains(_page.params.age,'21_25') }">checked="checked"</c:if>><label>21~25岁</label></span>
                     	<span><input type="checkbox"  value="25_30" name="_page.params.age" <c:if test="${fn:contains(_page.params.age,'25_30') }">checked="checked"</c:if>><label>26~30岁</label></span>
                     	<span><input type="checkbox"  value="31_35" name="_page.params.age" <c:if test="${fn:contains(_page.params.age,'31_35') }">checked="checked"</c:if>><label>31~35岁</label></span>
                     	<span><input type="checkbox"  value="36_150" name="_page.params.age" <c:if test="${fn:contains(_page.params.age,'36_150') }">checked="checked"</c:if>><label>36岁以上</label></span>
                    	<input type="text" class="w100" style="width:30px;" name="_page.params.age2" value="${fn:trim(fn:split(_page.params.age2,',')[0]) }"/>
                     -<input type="text" class="w100" style="width:30px;" name="_page.params.age2" value="${fn:trim(fn:split(_page.params.age2,',')[1]) }"/>
                    </li>
                     <li class="xxchx-from"><b>类别：</b>
                     <input  type="checkbox" value="0" name="type" value="-1" 
                     <c:if test="${fn:contains(_page.params.type,'-1') }">checked="checked"</c:if>/><label>全部</label>
                     	<c:forEach items="${tsryfl }" var="fl">
                     	<span><input type="checkbox" name="_page.params.type" value="${fl.id }" 
                     				<c:forEach items="${fn:split(_page.params.type,',') }" var="trid">
								<c:if test="${fn:trim(trid) eq fl.id }">checked="checked"</c:if>
								</c:forEach>
                     	><label>${fl.name}</label></span>
                     	</c:forEach>
            			</li>
            			 <li class="xxchx-from"><b>网格：</b>
                     	<c:forEach items="${mgrid }" var="grid1">
                     	<span><input type="checkbox" name="_page.params.grid" value="${grid1 }" 
                     		<c:if test="${fn:contains(_page.params.grid,grid1) }">checked="checked"</c:if>
                     	><label>${grid1 }</label></span>
                     	</c:forEach>
            			</li>
            			</ul>
                     <center>
                     <button class="button02" type="submit">
                     <img src="fun/system/images/icon-search.png" />查询</button>
                     <button class="button02" onclick="javascript:$('#popDiv').hide();">
                     	<img src="fun/system/images/icon-search.png" />关闭</button>
                     </center>
             	
             	</form>
</div>
</div>



<div class="tab-border" style="width:600px;height:400px;position: absolute;
top:200px;left:40%;display:none;" id="daoDiv">
<div class="zhwxx-tab" style="text-align:left;vertical-align:top;height:400px;overflow-y:auto;overflow-x:hidden;">
<form action="" method="post" id="zdform">
<div class="chaxun-form" style="text-align: center;height:30px;font-size:15px;padding:10px;"><b>选择需要导出信息</b></div>
             	<ul style="padding: 10px;">
                     
                     <c:forEach items="${zdes}" var="mzd" varStatus="ig">
                      <c:if test="${ig.index eq 0 }"><li class="xxchx-from"><b>基本信息：<input type="checkbox" onclick="selectQB(this)"/>全部</b></c:if>
                      <c:if test="${ig.index eq 38 }"><li class="xxchx-from"><b>家庭信息：<input type="checkbox" onclick="selectQB(this)"/>全部</b></c:if>
                      <c:if test="${ig.index eq 43 }"><li class="xxchx-from"><b>户籍信息：<input type="checkbox" onclick="selectQB(this)"/>全部</b></c:if>
                      <c:if test="${ig.index eq 57 }"><li class="xxchx-from"><b>户主信息：<input type="checkbox" onclick="selectQB(this)"/>全部</b></c:if>
                      <c:if test="${ig.index eq 71 }"><li class="xxchx-from"><b>参保信息：<input type="checkbox" onclick="selectQB(this)"/>全部</b></c:if>
                      <c:if test="${ig.index eq 82 }"><li class="xxchx-from"><b>学生信息：<input type="checkbox" onclick="selectQB(this)"/>全部</b></c:if>
                     	<span><input type="checkbox"  value="${ig.index }" name="daochuziduan" <c:if test="${ig.index lt 10 }">checked="checked"</c:if>><label>${mzd }</label></span>
                     <c:if test="${ig.index eq 37 }"></li></c:if>
                     <c:if test="${ig.index eq 42 }"></li></c:if>
                     <c:if test="${ig.index eq 56 }"></li></c:if>
                     <c:if test="${ig.index eq 70 }"></li></c:if>
                     <c:if test="${ig.index eq 81 }"></li></c:if>
                      <c:if test="${ig.index eq 87 }"></li></c:if>
                     </c:forEach>
                    
       			</ul>
                <center>
                <input type="button" value="导出" class="button02" onclick="expExcel();"/>
                <input type="button" value="关闭" class="button02" onclick="javascript:$('#daoDiv').hide();"/>
                </center>
        		
        	</form>
        	<script type="text/javascript">
        	function selectQB(th){
        		if($(th).attr("checked")){
        			$(th).parent().parent().find("input").attr("checked","checked");
        		}else{
        			$(th).parent().parent().find("input").attr("checked",false);
        		}
        		
        	}
        	</script>
</div>
</div>


	<jsp:include page="/WEB-INF/page/system/index/foot.jsp"></jsp:include>

</body>
</html>