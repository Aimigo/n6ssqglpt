
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
  }else{
  
}
function ok_submitzm(type){ 