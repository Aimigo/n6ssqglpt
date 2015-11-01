/**list*/
$(function() {
	$("tr").click(function() {
		var checked = $(this).find("td").first().find("input");
		checked.attr("checked", !checked.attr("checked"));
	});

	$(":checkbox").click(function(event) {
		event.stopPropagation(); // do something   
	});
	
	$("td a").click(function(event){
		event.stopPropagation(); // do something
	});
});
/**list-复选框全选和取消全选*/
function checkAll(o) {
	var list = document.getElementsByName("check");
	for ( var i = 0; i < list.length; i++) {
		list[i].checked = o.checked;
	}
}
/**增删改查*/
function operator(op,id){
	var curpage=$("input[name='_page.curPage']");
	//if(curpage!=undefined && curpage!=null){
		$.ajax({
			type : "post",
			url : "jmxx_setSessionPage.action",
			data : {
				"curPage" : curpage.val()
			},
			dataType : "html",
			success : function(data) {
				
				if(op=="a"){//添加操作
					window.location.href = baseUrl+"add.action";
				}else if(op=="e"){//跳转到修改页
					window.location.href = baseUrl+"edit.action?id=" + id;
				}else if(op=="d"){//删除
					var ids="";
					if(id){
						ids=id;
					}else{
						$("[name='check']").each(function() {
							if (this.checked) {
								if (ids == "")
									ids = $(this).val();
								else
									ids += "," + $(this).val();
							}
						});
						if (ids == ""){
							alert("请选择您要删除的信息！");
							return;
						}
					}
					if (confirm("您确定要删除吗？")) {
						$.ajax({
							type : "post",
							url : baseUrl+"delete.action",
							data : {
								"ids" : ids
							},
							dataType : "html",
							success : function(data) {
								window.location.href = baseUrl+"list.action";
							}
						});
					}
				}else if(op=="x"){//跳转到详细页
					window.location.href = baseUrl+"detail.action?id=" + id;
				}else{
					
				}
			}
		});
	//}
	
}
/**
 * 判断是否重复
 * table 表名，field 字段名，th 表单元素 id id msg 提示信息
 */
