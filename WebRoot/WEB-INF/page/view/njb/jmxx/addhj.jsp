<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


	<form action="hjgl_save.action" method="post" name="saveform">
					<div class="biaodan-all" style="border:1px solid black">
					<table class="biaodan" cellpadding="0" cellspacing="0" style="margin-left:0px;margin-right:0px;width:490px;">
							<tr>
								<td colspan="4" class="title2">基本信息</td>
							</tr>
							<tr>
								<td width="17%">实有房屋（<font class="fred">*</font>）</td>
								<td width="33%">
									<input type="text" name="syfwname"   class="qbg" 
									readonly="readonly"  id="syfwname"
									onclick="showHjTree();"
									<input type="hidden" name="syfwid" id="syfwid" dataType="Require" msg="实有房屋不能为空。"/>	
									<script type="text/javascript">
											function showHjTree(){
													$.ajax({
														type : 'post',
														url : 'hjgl_getZzList.action',
														cache : false,
														dataType : 'html',
														success : function(data) {
															showDialog("window", data, "实有房屋");
														},
														error : function() {
															alert("error");
															return false;
														}
													});
												}
												function getRegion(id,name) {
													$("#popDiv").hide();
													$("#syfwid").val(id);
													$("#syfwname").val(name);
													sd_remove();
												}
												function submitHjxx(){
													var syfwid=$("#syfwid").val();
													alert(syfwid);
													if(syfwid==""){
														alert("实有房屋不能为空");
													}
													var hkbh=$("input[name=hkbh]").val();
													if(hkbh==""){
														alert("户口薄号不能为空");
													}
													var dh=$("input[name=dh]").val();
													var sj=$("input[name=sj]").val();
													var sfz=$("input[name=sfz]").val();
													var hzname=$("input[name=hzname]").val();
													$.ajax({
															type : 'post',
															url : 'jmxx_hjadd.action',
															data: {"syfwid":syfwid,"hkbh":hkbh
																	,"dh":dh,"sj":sj,"sfz":sfz,"hzname":hzname},
															cache : false,
															dataType : 'json',
															success : function(data) {
																$("input[name=accountNumber]").val(data[0].hkbh);
																	$("input[name=accountIdnumber]").val(data[0].sfz);
																	$("input[name=accountName]").val(data[0].hzname);
																	$("input[name=accountCellphone]").val(data[0].dh);
																	$("input[name=accountPhone]").val(data[0].sj);
																	$("input[name=familyno]").val(data[0].hkbh);
																	$("input[name=parentsidno]").val(data[0].sfz);
																	$("input[name=parentsname]").val(data[0].hzname);
																	$("input[name=parentscellphone]").val(data[0].dh);
																	$("input[name=parentsphone]").val(data[0].sj);
																	$("input[name=road]").val(data[1].xqdz);
																	$("input[name=ridgepole]").val(data[1].zhuang);
																	$("input[name=element]").val(data[1].dy);
																	$("input[name=roomnumber]").val(data[1].shi);
																	$("input[name='jtxx.hkbh']").val(data[0].hkbh);
																	$("input[name='jtxx.hzbh']").val(data[0].sfz);
																	$('#showaddHjxx').hide();
															},
															error : function() {
																alert("error");
																return false;
															}
														});
												}
									</script>
								</td>
								<td width="17%">户口薄号（<font class="fred">*</font>）</td>
								<td width="33%"><input name="hkbh" type="text" class="qbg" readonly="readonly"
									 value="${hkbh }"/>
								</td>
							</tr>
							<tr>
								<td>电话</td>
								<td>
								<input name="dh" id="dh" type="text" class="qbg"  
								/>
								</td>
								<td>手机</td>
								<td><input name="sj" type="text" class="qbg" />
								</td>
							</tr>
							<tr>
								<td>身份证号</td>
								<td><input name="sfz" type="text" class="qbg" />
								</td>
								<td>户主名称</td>
								<td><input name="hzname" type="text" class="qbg" />
								</td>
							</tr>
						</table>
						<div class="bcan">
							<a href="javascript:submitHjxx();"> <img src="fun/system/images/bc.gif">
							</a> &nbsp; <a href="javascript:void(0);" onclick="javascript:$('#showaddHjxx').hide()"> <img
								src="fun/system/images/fh.gif">
							</a>
						</div>
					</div>
					<!--表单内容  结束-->
				</form>

						
