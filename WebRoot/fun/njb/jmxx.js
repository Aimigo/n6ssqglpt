/**
 * 户籍选择户口薄号弹窗
 */
function showHjxx(){
	$.ajax({
		type : 'post',
		url : 'jmxx_getZzList.action',
		cache : false,
		dataType : 'html',
		success : function(data) {
			showDialog("window", data, "户籍");
		},
		error : function() {
			alert("error");
				return false;
			}
	});
}

/**
 * 户籍弹窗调用
 * @param id
 * @param name
 */
function getHkxx(id,name) {
	sd_remove();
	$.ajax({
		type : 'post',
		data:{
			"hjid":id
		},
		url : 'jmxx_getHjxx.action',
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
		},
		error : function() {
			alert("error");
			return false;
		}
	});
}
/**
 * 图片上传调用
 * @param lx
 */
function uploadfileend(lx,file0,file1,file2){
	
	var lc=location.href.split(cp)[0];
	$("#m_image").val(lc+cp+"/"+file1);//
	$("#i_image").attr("src",lc+cp+"/"+file1);
}
/**
 * 选择连队
 */
function getLd(){
	$.ajax({
		type : 'post',
		url : 'jmxx_getLd.action',
		cache : false,
		dataType : 'html',
		success : function(data) {
			showDialog("window", data, "选择连队");
		},
		error : function() {
			alert("error");
			return false;
		}
	});
}
//显示 家庭，参保，学生信息
function showjtxx(el,t){
	if($(el).prop("checked")){
		$("#showjtxx").after($("#hide_"+t).find("tr[name="+t+"_tr]"));
	}else{
		$("#hide_"+t).append($(document.forms[0]).find("tr[name="+t+"_tr]"));	
	}
}


$(function(){
	$("input[name=accountRelation]").change(function(){
		checkValHK();
	});
	$("input[name=accountNumber]").change(function(){
		checkValHK();
	});
});
function checkValHK(){
	var an=$("input[name=accountNumber]").val();
	if(an==null||an==""){
		return;
	}
	var val=$("input[name=accountRelation]:checked").val();
	if(val!="户主"&&val!="妻"){
		return;
	}
	$.ajax({
		type : "post",
		async: false,
		url : "jmxx_hjgx.action",
		data : {"ar":val,"an":an,"ryid":ryid},
		dataType : "json",
		success : function(data) {
			if(data!="1"){
				alert("与户主关系为"+val+" 的关系已经存在");
				$("input[name=accountRelation]:checked").attr("checked",false);
			}else{
				if(val=="户主"){
					$("input[name=accountIdnumber]").val($("input[name=idNumber]").val());
					$("input[name=accountName]").val($("input[name=name]").val());
					$("input[name=accountCellphone]").val($("input[name=cellphone]").val());
					$("input[name=accountPhone]").val($("input[name=phone]").val());
					$("input[name=parentsidno]").val($("input[name=idNumber]").val());
					$("input[name=parentsname]").val($("input[name=name]").val());
					$("input[name=parentscellphone]").val($("input[name=cellphone]").val());
					$("input[name=parentsphone]").val($("input[name=phone]").val());
					
					$("input[name=idNumber]").change(function(){
						$("input[name=accountIdnumber]").val($(this).val());
						$("input[name=parentsidno]").val($(this).val());
					});
					$("input[name=name]").change(function(){
						$("input[name=accountName]").val($("input[name=name]").val());
						$("input[name=parentsname]").val($("input[name=name]").val());
					});
					$("input[name=cellphone]").change(function(){
						$("input[name=accountCellphone]").val($("input[name=cellphone]").val());
						$("input[name=parentscellphone]").val($("input[name=cellphone]").val());
					});
					$("input[name=phone]").change(function(){
						$("input[name=accountPhone]").val($("input[name=phone]").val());
						$("input[name=parentsphone]").val($("input[name=phone]").val());
					});
				}else{
					$("input[name=idNumber]").unbind("change");
					$("input[name=accountName]").unbind("change");
					$("input[name=accountCellphone]").unbind("change");
					$("input[name=accountPhone]").unbind("change");
				}
			}
		}
	});
}
//选择户籍关系
$(function(){
	if(hjzt==undefined||hjzt==""||hjzt=="户籍人口"){
		$("#hjxx_bor").after($("#hide_hjxx").find("tr[name=hjgxx_tr]"));
	}else{
		$("#hjxx_bor").after($("#hide_zzfw").find("tr[name=zzfw_tr]"));
	}
	
	$("input[name=register]").change(function(){
		if($(this).val()=="户籍人口"){
			$("#hjxx_bor").after($("#hide_hjxx").find("tr[name=hjgxx_tr]"));
			$("#hide_zzfw").append($(document.forms[0]).find("tr[name=zzfw_tr]"));
		}else{
			$("#hjxx_bor").after($("#hide_zzfw").find("tr[name=zzfw_tr]"));
			$("#hide_hjxx").append($(document.forms[0]).find("tr[name=hjgxx_tr]"));
		}
	});
});

function showZzrxx(){
	$.ajax({
		type : 'post',
		url : 'jmxx_getSyfwTree.action',
		cache : false,
		dataType : 'html',
		success : function(data) {
			showDialog("window", data, "出租房屋");
		},
		error : function() {
			alert("error");
			return false;
		}
	});
}
function getZzrxx(id,name) {
	$("#ccfwid").val(id);
	$("#ccfwname").val(name);
	sd_remove();
}