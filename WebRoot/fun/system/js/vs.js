//����������ǰȷ�� 
 function frmSubmit(f)
{
  var _name = f.id;
  if(_name=="frmTop"){
	 var top = $.trim($("#textfield").val()); //����      	 
  }
  if(_name =="frm3g3g2"){
	 var tail = $.trim($("#tailInput").val());//�ײ�  
  }  
  if(top || tail){ 
	f.submit();		
  }else{     $("#textfield").val("");     $("#tailInput").val("");	 	 f.submit();	  }
  
}
function ok_submitzm(type){ 	var top = $("#textfield").val().replace(/\s/g,''); //���� 	if(!top){		return false;	}else{		$("#captionFlag").val(type);		$("#frmTop").submit();		return false;		}	}
