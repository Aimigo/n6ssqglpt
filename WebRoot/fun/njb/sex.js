function showBirthday(t){
	var val=t.value;
   var birthdayValue="";
   var sex="";
   if(15==val.length){ //15位身份证号码
	    birthdayValue = val.charAt(6)+val.charAt(7);
	    if(parseInt(birthdayValue)<10){
	    	birthdayValue = '20'+birthdayValue;
	    }
	    else{
	    	birthdayValue = '19'+birthdayValue;
		    }
	    birthdayValue=birthdayValue+'-'+val.charAt(8)+val.charAt(9)+'-'+val.charAt(10)+val.charAt(11);
	    if(parseInt(val.charAt(14)/2)*2!=val.charAt(14))
	    	sex='男';
	    else
	    	sex='女';
	    //document.all.birthday.value=birthdayValue;
	}
   if(18==val.length) { //18位身份证号码
	   birthdayValue=val.charAt(6)+val.charAt(7)+val.charAt(8)+val.charAt(9)+'-'+val.charAt(10)+val.charAt(11)
	   					+'-'+val.charAt(12)+val.charAt(13);
	    if(parseInt(val.charAt(16)/2)*2!=val.charAt(16))
	    	sex='男';
	    else
	    	sex='女';
	    if(val.charAt(17)!=IDCard(val)){
	    	$(t).attr("background",'#ffc8c8');
	    }
	    else{
	    	$(t).attr("background",'white');
	    	//document.all.idCard.style.backgroundColor='white';
	    }
	    //document.all.birthday.value=birthdayValue;
   }
   if(sex=='女'){
		$("#nvsheng").attr("checked",true);
	}else{
		$("#nansheng").attr("checked",true);
	}
   $("input[name=birthday]").val(birthdayValue);
   $("input[name=csny]").val(birthdayValue);
   //return [birthdayValue,sex];
}

  // 18位身份证号最后一位校验
  function IDCard(Num){
   if (Num.length!=18)
	   return false;
   var x=0;
   var y='';

   for(i=18;i>=2;i--)
	   x = x + (square(2,(i-1))%11)*parseInt(Num.charAt(19-i-1));
   x%=11;
   y=12-x;
   if (x==0)
    y='1';
   if (x==1)
    y='0';
   if (x==2)
    y='X';
   return y;
  }

  // 求得x的y次方
  function square(x,y){
   var i=1;
   for (j=1;j<=y;j++)
    i*=x;
   return i;
  }
  
  function vaildNumber(ts,jmxxid){
		var val=$.trim($(ts).val());
		$.ajax({
  		type : "post",
  		url : "jmxx_valideNumber.action",
  		data : {"idNumber":val,"jmxxid":jmxxid},
  		dataType : "html",
  		success : function(data) {
  			if(data=="1"){
					$(ts).next("<span style='color:green;'>该身份证可以使用。</span>");
  			}else{
  				var ss=data.split("#");
  				alert(val+"身份证号已被使用,请联系  "+ss[0]+"管理员,联系方式："+ss[1]);
  				$(ts).val("");
  			}
  		}
		});
		showBirthday(ts);
	}