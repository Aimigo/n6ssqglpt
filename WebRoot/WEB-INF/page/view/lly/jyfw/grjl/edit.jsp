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
<link rel="stylesheet" href="fun/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="fun/showDialog/showDialog.js"></script>
<script type="text/javascript" src="fun/javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="fun/javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="fun/njb/Validator.js"></script>
<script type="text/javascript" src="fun/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript">
	$(function(){
		$("#update").click(function(){
			var form = document.updateform;
			Validator.Validate(form,3);
		});
		
		$("input[name='xb'][value='${grjl.xb}']").attr('checked', 'checked');
		$("[name='whcd']").val('${grjl.whcd}');
		$("input[name='rylb'][value='${grjl.rylb}']").attr('checked', 'checked');
		$("input[name='hkzk'][value='${grjl.hkzk}']").attr('checked', 'checked');
	});
	
	function uploadfileend(lx,file0,file1,file2){
		$("#zp").val(file1);
		$("#ylt").attr("src",file1);
	}
	
	var flag;
	//展示zwfl
	function showZwfl(model){
		flag = model;
		$.ajax({
			type : 'post',
			url : 'zwfl.action',
			cache : false,
			dataType : 'html',
			success : function(data) {
				showDialog("window", data, "选择择业工种");
			},
			error : function() {
				return false;
			}
		});
	}
	
	//获取zwfl
	function getZwfl(id,name) {
		$("input[name='qzyw"+flag+"']").val(id);
		$("input[name='qzywname"+flag+"']").val(name);
		sd_remove();
	}
	
	//展示region
	function showRegion(){
		$.ajax({
			type : 'post',
			url : 'region.action',
			cache : false,
			dataType : 'html',
			success : function(data) {
				showDialog("window", data, "选择行政区划");
			},
			error : function() {
				return false;
			}
		});
	}
	
	//获取region
	function getRegion(id,name) {
		$("input[name='zydq']").val(id);
		$("input[name='zydqname']").val(name);
		sd_remove();
	}
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
						<b>当前位置：共青团农场社区管理平台&nbsp;&gt;&gt;&nbsp;就业服务&nbsp;&gt;&gt;&nbsp;个人就业信息管理&nbsp;&gt;&gt;&nbsp;修改</b>
					</div>
				</div>
				<!--当前位置  结束-->
				
				<!--表单内容  开始-->
				<form action="grjl_update.action" method="post" name="updateform">
				<div class="biaodan-all">
					<div class="biaodan-title">个人就业信息管理信息</div>
					<table class="biaodan" cellpadding="0" cellspacing="0">
						<colgroup>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
							<col style="width:10%;"/>
							<col style="width:40%;"/>
						</colgroup>
						<tr>
							<td colspan="4" class="title2">登记信息</td>
						</tr>
						<tr>
							<td>登记表编号（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="djbbh" type="text" class="qbg" maxlength="50" value="${grjl.djbbh }"
									dataType="Require" msg="登记表编号不能为空！" require="true" noRepeat="true" repeatTab="TblGrjl" repeatMsg="登记表编号已经存在！" repeatId="${grjl.id }"/>
								<input name="id" type="hidden" value="${grjl.id }"/>
							</td>
						</tr>
						<tr>
							<td>登记日期（<font class="fred">*</font>）</td>
							<td>
								<input id="djsj" name="djsj" type="text" class="qbg" maxlength="25" value='<fmt:formatDate value="${grjl.djsj }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									dataType="Require" msg="登记日期不能为空！" require="true"/>
							</td>
							<td>有效期至（<font class="fred">*</font>）</td>
							<td>
								<input id="yxq" name="yxq" type="text" class="qbg" maxlength="25" value='<fmt:formatDate value="${grjl.yxq }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'djsj\');}'})"
									dataType="Require" msg="有效期不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">个人信息</td>
						</tr>
						<tr>
							<td>姓名（<font class="fred">*</font>）</td>
							<td>
								<input name="xm" type="text" class="qbg" maxlength="25" value="${grjl.xm }"
									dataType="Require" msg="姓名不能为空！" require="true"/>
							</td>
							<td>身份证号（<font class="fred">*</font>）</td>
							<td>
								<input name="sfzh" type="text" class="qbg" maxlength="25" value="${grjl.sfzh }"
									dataType="Require" msg="身份证号不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>性别（<font class="fred">*</font>）</td>
							<td>
								<input name="xb" type="radio" class="inputcheck" value="男" checked="checked" id="xb_man"/>&nbsp;<label for="xb_man">男</label>
								<input name="xb" type="radio" class="inputcheck" value="女" id="xb_woman"/>&nbsp;<label for="xb_woman">女</label>
							</td>
							<td>出生日期（<font class="fred">*</font>）</td>
							<td>
								<input name="csrq" type="text" class="qbg" maxlength="25" value='<fmt:formatDate value="${grjl.csrq }" pattern="yyyy-MM-dd" />'
									readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									dataType="Require" msg="出生日期不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>民族（<font class="fred">*</font>）</td>
							<td>
								<input name="mz" type="text" class="qbg" maxlength="25" value="${grjl.mz }"
									dataType="Require" msg="民族不能为空！" require="true"/>
							</td>
							<td>文化程度（<font class="fred">*</font>）</td>
							<td>
								<select name="whcd" class="bd-r" dataType="Require" msg="请选择文化程度！" require="true">
									<option value="">请选择</option>
									<option value="00">文盲</option>
									<option value="01">半文盲</option>
									<option value="02">小学</option>
									<option value="03">初中</option>
									<option value="04">高中</option>
									<option value="05">技工学校</option>
									<option value="06">中技</option>
									<option value="07">中专</option>
									<option value="08">大专</option>
									<option value="09">本科</option>
									<option value="10">硕士</option>
									<option value="11">博士</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>照片（<font class="fred">*</font>）</td>
							<td>
								<input name="zp" id="zp" type="hidden" class="qbg" maxlength="50"  value="${grjl.zp }"
									dataType="Require" msg="照片不能为空！" require="true"/>
								<input type="hidden" id="upload_url0" value="" />
								<input type="hidden" id="upload_url1" value="" />
								<input type="hidden" id="upload_url2" value="" />
								<iframe src="uploadfile_uploadfile.action?lx=img" id="_img_iframe" scrolling="no" frameborder="0" style="width: 100%;height: 185px;"></iframe>
							</td>
							<td>预览</td>
							<td>
								<img src="${grjl.zp}" alt="" style="height:185px;" id="ylt"/>
							</td>
						</tr>
						<tr>
							<td>人员类别（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="rylb" type="radio" class="inputcheck" value="01" checked="checked" id="rylb_syzg"/>&nbsp;<label for="rylb_syzg">失业职工</label>
								<input name="rylb" type="radio" class="inputcheck" value="02" id="rylb_syqn"/>&nbsp;<label for="rylb_syqn">失业青年</label>
								<input name="rylb" type="radio" class="inputcheck" value="03" id="rylb_qtsyry"/>&nbsp;<label for="rylb_qtsyry">其他失业人员</label>
								<input name="rylb" type="radio" class="inputcheck" value="10" id="rylb_zyry"/>&nbsp;<label for="rylb_zyry">在业人员</label>
								<input name="rylb" type="radio" class="inputcheck" value="20" id="rylb_xgzg"/>&nbsp;<label for="rylb_xgzg">下岗职工</label>
								<input name="rylb" type="radio" class="inputcheck" value="30" id="rylb_ltxry"/>&nbsp;<label for="rylb_ltxry">离退休人员</label>
								<input name="rylb" type="radio" class="inputcheck" value="40" id="rylb_zxxs"/>&nbsp;<label for="rylb_zxxs">在校学生</label>
								<input name="rylb" type="radio" class="inputcheck" value="99" id="rylb_qtry"/>&nbsp;<label for="rylb_qtry">其他人员</label>
								<input name="rylb" type="radio" class="inputcheck" value="44" id="rylb_zz"/>&nbsp;<label for="rylb_zz">在职</label>
							</td>
						</tr>
						<tr>
							<td>户口所在地（<font class="fred">*</font>）</td>
							<td>
								<input name="hksz" type="text" class="qbg" maxlength="50" value="${grjl.hksz }"
									dataType="Require" msg="户口所在地不能为空！" require="true"/>
							</td>
							<td>户口状况（<font class="fred">*</font>）</td>
							<td>
								<input name="hkzk" type="radio" class="inputcheck" value="1" checked="checked" id="hkzk_cz"/>&nbsp;<label for="hkzk_cz">城镇户口</label>
								<input name="hkzk" type="radio" class="inputcheck" value="2" id="hkzk_nc"/>&nbsp;<label for="hkzk_nc">农村户口</label>
							</td>
						</tr>
						<tr>
							<td>健康状况（<font class="fred">*</font>）</td>
							<td>
								<input name="jkzk" type="text" class="qbg" maxlength="50" value="${grjl.jkzk }"
									dataType="Require" msg="健康状况不能为空！" require="true"/>
							</td>
							<td>婚姻状况（<font class="fred">*</font>）</td>
							<td>
								<input name="hyzk" type="text" class="qbg" maxlength="50" value="${grjl.hyzk }" 
									dataType="Require" msg="婚姻状况不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>职称（<font class="fred">*</font>）</td>
							<td>
								<input name="zc" type="text" class="qbg" maxlength="25" value="${grjl.zc }"
									dataType="Require" msg="职称不能为空！" require="true"/>
							</td>
							<td>政治面貌（<font class="fred">*</font>）</td>
							<td>
								<input name="zzmm" type="text" class="qbg" maxlength="25"  value="${grjl.zzmm }"
									dataType="Require" msg="政治面貌不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>身高（<font class="fred">*</font>）</td>
							<td>
								<input name="sg" type="text" class="qbg" maxlength="25" value="${grjl.sg }" 
									dataType="Require" msg="身高不能为空！" require="true"/>
							</td>
							<td>视力（<font class="fred">*</font>）</td>
							<td>
								<input name="sl" type="text" class="qbg" maxlength="25" value="${grjl.sl }" 
									dataType="Require" msg="视力不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>详细地址（<font class="fred">*</font>）</td>
							<td>
								<input name="xxdz" type="text" class="qbg" maxlength="50" value="${grjl.xxdz }" 
									dataType="Require" msg="详细地址不能为空！" require="true"/>
							</td>
							<td>联系电话（<font class="fred">*</font>）</td>
							<td>
								<input name="lldh" type="text" class="qbg" maxlength="25" value="${grjl.lldh }" 
									dataType="Require" msg="联系电话不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>联系人（<font class="fred">*</font>）</td>
							<td>
								<input name="lxr" type="text" class="qbg" maxlength="50" value="${grjl.lxr }"
									dataType="Require" msg="联系人不能为空！" require="true"/>
							</td>
							<td>邮编（<font class="fred">*</font>）</td>
							<td>
								<input name="yb" type="text" class="qbg" maxlength="25" value="${grjl.yb }" 
									dataType="Require" msg="邮编不能为空！" require="true"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">具有专业</td>
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%">
									<colgroup>
										<col style="width:5%;"/>
										<col style="width:;"/>
										<col style="width:;"/>
										<col style="width:;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">序号</td>
										<td align="center">专业工种</td>
										<td align="center">技能等级</td>
										<td align="center">从事年限</td>
										<td align="center">说明</td>
									</tr>
									<tr>
										<td align="center">1</td>
										<td>
											<input name="zygz1" type="text" class="qbg" maxlength="50" value="${grjl.zygz1 }" 
												dataType="Require" msg="专业工种不能为空！" require="true"/>
										</td>
										<td>
											<input name="jsdj1" type="text" class="qbg" maxlength="50" value="${grjl.jsdj1 }" 
												dataType="Require" msg="技能等级不能为空！" require="true"/>
										</td>
										<td>
											<input name="csnx1" type="text" class="qbg" maxlength="50" value="${grjl.csnx1 }" 
												dataType="Require" msg="从事年限不能为空！" require="true"/>
										</td>
										<td>
											<input name="sm1" type="text" class="qbg" maxlength="100" value="${grjl.sm1 }"
												dataType="Require" msg="说明不能为空！" require="true"/>
										</td>
									</tr>
									<tr>
										<td align="center">2</td>
										<td>
											<input name="zygz2" type="text" class="qbg" maxlength="50" value="${grjl.zygz2 }"/>
										</td>
										<td>
											<input name="jsdj2" type="text" class="qbg" maxlength="50" value="${grjl.jsdj2 }"/>
										</td>
										<td>
											<input name="csnx2" type="text" class="qbg" maxlength="50" value="${grjl.csnx2 }"/>
										</td>
										<td>
											<input name="sm2" type="text" class="qbg" maxlength="100" value="${grjl.sm2 }"/>
										</td>
									</tr>
									<tr>
										<td align="center">3</td>
										<td>
											<input name="zygz3" type="text" class="qbg" maxlength="50" value="${grjl.zygz3 }"/>
										</td>
										<td>
											<input name="jsdj3" type="text" class="qbg" maxlength="50" value="${grjl.jsdj3 }"/>
										</td>
										<td>
											<input name="csnx3" type="text" class="qbg" maxlength="50" value="${grjl.csnx3 }"/>
										</td>
										<td>
											<input name="sm3" type="text" class="qbg" maxlength="100" value="${grjl.sm3 }"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="title2">求职愿望</td>
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%">
									<colgroup>
										<col style="width:5%;"/>
										<col style="width:;"/>
										<col style="width:;"/>
										<col style="width:;"/>
									</colgroup>
									<tr>
										<td align="center">序号</td>
										<td align="center">择业工种</td>
										<td align="center">月薪要求</td>
										<td align="center">经济类型</td>
									</tr>
									<tr>
										<td align="center">1</td>
										<td>
											<input name="qzywname1" type="text" class="qbg" maxlength="50" onclick="showZwfl(1)" readonly="readonly" value="${grjl.qzywname1 }"/>
											<input name="qzyw1" type="hidden" class="qbg" maxlength="50" value="${grjl.qzyw1 }"
												dataType="Require" msg="请选择择业工种！" require="true"/>
										</td>
										<td>
											<input name="yxyq1" type="text" class="qbg" maxlength="50" value="${grjl.yxyq1 }"   
												dataType="Require" msg="月薪要求不能为空！" require="true"/>
										</td>
										<td>
											<input name="jjlx1" type="text" class="qbg" maxlength="50" value="${grjl.jjlx1 }"   
												dataType="Require" msg="经济类型不能为空！" require="true"/>
										</td>
									</tr>
									<tr>
										<td align="center">2</td>
										<td>
											<input name="qzywname2" type="text" class="qbg" maxlength="50" onclick="showZwfl(2)" readonly="readonly" value="${grjl.qzywname2 }"/>
											<input name="qzyw2" type="hidden" class="qbg" maxlength="50" value="${grjl.qzyw2 }"/>
										</td>
										<td>
											<input name="yxyq2" type="text" class="qbg" maxlength="50" value="${grjl.yxyq2 }"/>
										</td>
										<td>
											<input name="jjlx2" type="text" class="qbg" maxlength="50" value="${grjl.jjlx2 }"/>
										</td>
									</tr>
									<tr>
										<td align="center">3</td>
										<td>
											<input name="qzywname3" type="text" class="qbg" maxlength="50" onclick="showZwfl(3)" readonly="readonly" value="${grjl.qzywname3 }"/>
											<input name="qzyw3" type="hidden" class="qbg" maxlength="50" value="${grjl.qzyw3 }"/>
										</td>
										<td>
											<input name="yxyq3" type="text" class="qbg" maxlength="50" value="${grjl.yxyq3 }"/>
										</td>
										<td>
											<input name="jjlx3" type="text" class="qbg" maxlength="50" value="${grjl.jjlx3 }"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>其他求职要求</td>
							<td colspan="3">
								<input name="qtqzyq" type="text" class="qbg" maxlength="500" value="${grjl.qtqzyq }" />
							</td>
						</tr>
						<tr>
							<td>外语及熟练程度</td>
							<td colspan="3">
								<input name="wyslcd" type="text" class="qbg" maxlength="100" value="${grjl.wyslcd }" />
							</td>
						</tr>
						<tr>
							<td>其他技能</td>
							<td colspan="3">
								<input name="qtjn" type="text" class="qbg" maxlength="500" value="${grjl.qtjn }" />
							</td>
						</tr>
						<tr>
							<td>择业地区（<font class="fred">*</font>）</td>
							<td colspan="3">
								<input name="zydqname" type="text" class="qbg" maxlength="500" onclick="showRegion()" readonly="readonly" value="${grjl.zydqname }"/>
								<input name="zydq" type="hidden" class="qbg" maxlength="500" value="${grjl.zydq }"
									dataType="Require" msg="请选择择业地区！" require="true"/>
							</td>
						</tr>
						<tr>
							<td>学习工作培训简历</td>
							<td colspan="3">
								<input name="xxgzpxjl" type="text" class="qbg" maxlength="1000" value="${grjl.xxgzpxjl }" />
							</td>
						</tr>
						<tr>
							<td>备注</td>
							<td colspan="3">
								<input name="bz" type="text" class="qbg" maxlength="1000" value="${grjl.bz }" />
							</td>
						</tr>
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