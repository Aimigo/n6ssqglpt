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
<script type="text/javascript" src="fun/njb/Validator.js"></script>
<script type="text/javascript">
	$(function(){
		$("#update").click(function(){
			var form = document.updateform;
			Validator.Validate(form,3);
		});
		
    	var type = '${type}';
    	var clyj = '<td valign="top">处理意见（<font class="fred">*</font>）</td>'
				  +'<td colspan="3">'
				  +'	<textarea name="clyj" id="clyj" type="text" style="width:98%;" rows="15"'
				  +'		dataType="Require" msg="处理意见不能为空！" require="true">${mdcl.clyj }</textarea>'
				  +'</td>';
		var dkbm = '<td>上报部门（<font class="fred">*</font>）</td>'
				  +'<td colspan="3">'
				  +'<select class="bd-r" name="dkbm" dataType="Require" msg="上报部门不能为空" require="true">'
				  +'	<option value="">请选择</option>'
				  		<c:forEach items="${departmentlist}" var="department">
				  +'	<option value="${department.id}">${department.name}</option>'
				  		</c:forEach>
				  +'</select>'
				  +'</td>';
		var fgld = '<td>分管领导（<font class="fred">*</font>）</td>'
				  +'<td colspan="3">'
				  +'<select class="bd-r" name="bmldid" dataType="Require" msg="上报的分管领导不能为空" require="true">'
				  +'	<option value="">请选择</option>'
				  		<c:forEach items="${userlist}" var="user">
				  +'	<option value="${user.id}">${user.realname}</option>'
				  		</c:forEach>
				  +'</select>'
				  +'</td>';
    	$("input[name='isjj']").click(function(){
    		if("0"==this.value){
    			if(type=="2"){//网格管理员需要选择对口部门
    				$("#showsome").html(dkbm);
    			}else if(type=="3"){
    				$("#showsome").html(fgld);
    			}else{
    				$("#showsome").html("");
    			}
    		}else if("1"==this.value){
    			$("#showsome").html(clyj);
    		}
    	});
    	
    	$("input[name='isjj1']").click(function(){
    		if("0"==this.value){
    			$("#showsome1").html("");
    			$("input[name='isjj']").val(0);
    		}else if("1"==this.value){
    			$("#showsome1").html(clyj);
    			$("input[name='isjj']").val(1);
    		}
    	});
    	$("input[name='isjj'][value='1']").click();
    	$("input[name='isjj1'][value='1']").click();
    	$("select[name='jcfs']").val("${mdcl.jcfs}");
	});
</script>
<style type="text/css">
.edui-default .edui-editor-bottomContainer td{border: 1px solid #ccc;}
</style>
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;民生事务&nbsp;&gt;&gt;&nbsp;矛盾处理&nbsp;&gt;&gt;&nbsp;<c:if test="${_page.params.type eq '1' }">修改</c:if><c:if test="${_page.params.type ne '1' }">解决问题</c:if></b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="mdcl_update.action" method="post" name="updateform">
				<div class="biaodan-all">
					<div class="biaodan-title">矛盾处理信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">矛盾信息</td>
						</tr>
						<c:if test="${type eq '1' }">
						<tr>
							<td>标题（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="bt" type="text" class="qbg" maxlength="50" value="${mdcl.bt }"
									dataType="Require" msg="标题不能为空！" require="true"/>
								<input name="id" type="hidden" value="${mdcl.id }"/>
								<input name="cjrid" type="hidden" value="${mdcl.cjrid }"/>
								<input name="txsj" type="hidden" value="${mdcl.txsj }"/>
								<input name="wg" type="hidden" value="${mdcl.wg }"/>
								<input name="zt" type="hidden" value="${mdcl.zt }"/>
							</td>
						</tr>
						<tr>
							<td>所属分类（<font class="fred">*</font>）</td>
							<td>
								<select name="flid" class="bd-r" dataType="Require" msg="请选择所属分类！" require="true">
									<option value="">请选择</option>
									<c:forEach items="${mdfllist }" var="mdfl">
									<option value="${mdfl.id }" <c:if test="${mdcl.flid eq mdfl.id }">selected="selected"</c:if>>${mdfl.name }</option>
									</c:forEach>
								</select>
							</td>
							<td>解决方式（<font class="fred">*</font>）</td>
							<td>
								<select name="jcfs" class="bd-r" dataType="Require" msg="请选择解决方式！" require="true">
									<option value="">请选择</option>
									<option value="民事手段">民事手段</option>
									<option value="司法手段">司法手段</option>
									<option value="行政手段">行政手段</option>
								</select>
							</td>
						</tr>
						<tr>
							<td valign="top">内容（<font class="fred">*</font>）</td>
							<td colspan="3">
								<textarea name="nr" id="nr" type="text" max="1000" style="width:98%;" rows="15"
									dataType="Require" msg="内容不能为空！" require="true">${mdcl.nr }</textarea>
							</td>
						</tr>
						<tr>
							<td>信息采集员（<font class="fred">*</font>）</td>
							<td>
								${mdcl.cjrname }
							</td>
							<td>填写时间（<font class="fred">*</font>）</td>
							<td>
								<fmt:formatDate value="${mdcl.txsj }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">问题处理</td>
						</tr>
						<tr>
							<td>是否解决（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input type="radio" name="isjj1" value="1" id="isjj1_yes"/>&nbsp;<label for="isjj1_yes">解决问题</label>
								<input type="radio" name="isjj1" value="0" id="isjj1_no"/>&nbsp;<label for="isjj1_no">上报问题</label>
								<input type="hidden" name="isjj" value="0"/>
							</td>
						</tr>
						<tr id="showsome1">
						</tr>
						</c:if>
						<c:if test="${type ne '1' }">
						<tr>
							<td>标题（<font class="fred">*</font>）</td>
							<td colspan="3">
								${mdcl.bt }
								<input name="id" type="hidden" value="${mdcl.id }"/>
								<input name="bt" type="hidden" value="${mdcl.bt }"/>
								<input name="nr" type="hidden" value='${mdcl.nr }'/>
								<input name="cjrid" type="hidden" value="${mdcl.cjrid }"/>
								<input name="txsj" type="hidden" value="${mdcl.txsj }"/>
								<input name="wg" type="hidden" value="${mdcl.wg }"/>
								<input name="zt" type="hidden" value="${mdcl.zt }"/>
								<input name="flid" type="hidden" value="${mdcl.flid }"/>
								<c:if test="${type ne '2' }">
									<input name="dkbm" type="hidden" value="${mdcl.dkbm }"/>
								</c:if>
								<c:if test="${type eq '3' }">
									<input name="glyid" type="hidden" value="${mdcl.glyid }"/>
								</c:if>
								<c:if test="${type eq '4' }">
									<input name="glyid" type="hidden" value="${mdcl.glyid }"/>
									<input name="dkbmryid" type="hidden" value="${mdcl.dkbmryid }"/>
								</c:if>
								<c:if test="${type eq '5' }">
									<input name="glyid" type="hidden" value="${mdcl.glyid }"/>
									<input name="dkbmryid" type="hidden" value="${mdcl.dkbmryid }"/>
									<input name="bmldid" type="hidden" value="${mdcl.bmldid }"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>所属分类（<font class="fred">*</font>）</td>
							<td>
								${mdcl.flname }
							</td>
							<td>解决方式（<font class="fred">*</font>）</td>
							<td>
								${mdcl.jcfs }
							</td>
						</tr>
						<tr>
							<td valign="top">内容（<font class="fred">*</font>）</td>
							<td colspan="3">
								${mdcl.nr }
							</td>
						</tr>
						<tr>
							<td>信息采集员（<font class="fred">*</font>）</td>
							<td>
								${mdcl.cjrname }
							</td>
							<td>填写时间（<font class="fred">*</font>）</td>
							<td>
								<fmt:formatDate value="${mdcl.txsj }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<c:if test="${type eq '2'}"><%-- 网格管理员 --%>
							<tr>
								<td valign="top">所属区域（<font class="fred">*</font>）</td>
								<td colspan="3">
									${mdcl.wg }
								</td>
							</tr>
						</c:if>
						<c:if test="${type eq '3'}"><%-- 职能部门 --%>
							<tr>
								<td valign="top">网格管理员（<font class="fred">*</font>）</td>
								<td>
									${mdcl.glyname }
								</td>
								<td valign="top">所属区域（<font class="fred">*</font>）</td>
								<td>
									${mdcl.wg }
								</td>
							</tr>
							<tr>
								<td valign="top">部门名称（<font class="fred">*</font>）</td>
								<td colspan="3">
									${mdcl.dkbmname }
								</td>
							</tr>
						</c:if>
						<c:if test="${type eq '4'}"><%-- 分管领导 --%>
							<tr>
								<td valign="top">网格管理员（<font class="fred">*</font>）</td>
								<td>
									${mdcl.glyname }
								</td>
								<td valign="top">所属区域（<font class="fred">*</font>）</td>
								<td>
									${mdcl.wg }
								</td>
							</tr>
							<tr>
								<td valign="top">职能部门（<font class="fred">*</font>）</td>
								<td>
									${mdcl.dkbmryname }
								</td>
								<td valign="top">部门名称（<font class="fred">*</font>）</td>
								<td>
									${mdcl.dkbmname }
								</td>
							</tr>
						</c:if>
						<c:if test="${type eq '5' }"><%-- 领导 --%>
							<tr>
								<td valign="top">网格管理员（<font class="fred">*</font>）</td>
								<td>
									${mdcl.glyname }
								</td>
								<td valign="top">所属区域（<font class="fred">*</font>）</td>
								<td>
									${mdcl.wg }
								</td>
							</tr>
							<tr>
								<td valign="top">职能部门（<font class="fred">*</font>）</td>
								<td>
									${mdcl.dkbmryname }
								</td>
								<td valign="top">部门名称（<font class="fred">*</font>）</td>
								<td>
									${mdcl.dkbmname }
								</td>
							</tr>
							<tr>
								<td valign="top">分管领导（<font class="fred">*</font>）</td>
								<td colspan="3">
									${mdcl.bmldname }
								</td>
							</tr>
						</c:if>
						<tr>
							<td valign="top">处理状态（<font class="fred">*</font>）</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${mdcl.zt eq -1}">未解决</c:when>
									<c:when test="${mdcl.zt eq 0}">信息采集员上报未解决</c:when>
									<c:when test="${mdcl.zt eq 1}">管理员上报未解决</c:when>
									<c:when test="${mdcl.zt eq 2}">职能部门上报未解决</c:when>
									<c:when test="${mdcl.zt eq 3}">分管领导上报未解决</c:when>
									<c:when test="${mdcl.zt eq 4}">已解决</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">问题处理</td>
						</tr>
						<tr>
							<td>是否解决（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input type="radio" name="isjj" value="1" id="isjj_yes"/>&nbsp;<label for="isjj_yes">解决问题</label>
								<c:if test="${type ne '5' and type ne '1'}">
								<input type="radio" name="isjj" value="0" id="isjj_no"/>&nbsp;<label for="isjj_no">上报问题</label>
								</c:if> 
							</td>
						</tr>
						<tr id="showsome">
							
						</tr>
						</c:if>
					</table>
					<div class="bcan">
						<a href="#" id="update">
							<img src="fun/system/images/bc.gif">
						</a>
						&nbsp;
						<a href="javascript:history.go(-1);">
							<img src="fun/system/images/fh.gif">
						</a>
					</div>
				</div>
				</form>
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