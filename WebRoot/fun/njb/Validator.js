var Validator = {
    Require : /.+/,																			//不能为空
    Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,								//email
    Phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,//phone
    Mobile : /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/,											//电话
    Url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,		//url
    IdCard : "this.IsIdCard(value)",														//身份证号
    Currency : /^\d+(\.\d+)?$/,																//货币
    Number : /^\d+$/,																		//数字
    Zip : /^[1-9]\d{5}$/,																	//邮政编码
    QQ : /^[1-9]\d{4,8}$/,																	//QQ
    Integer : /^[-\+]?\d+$/,																//整数
    Double : /^[-\+]?\d+(\.\d+)?$/,															//小数
    English : /^[A-Za-z]+$/,																//英文
    Chinese :  /^[\u0391-\uFFE5]+$/,														//中文
    Username : /^[a-z]\w{3,}$/i,															//用户名
    UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,//货币
    IsSafe : function(str){return !this.UnSafe.test(str);},									//
    SafeString : "this.IsSafe(value)",														//
    Filter : "this.DoFilter(value, getAttribute('accept'))",								//验证上传文件的格式
    Limit : "this.limit(value.length,getAttribute('min'),  getAttribute('max'))",			//验证字符最大和最短区间
    LimitB : "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",
    Date : "this.IsDate(value, getAttribute('format'), getAttribute('min'))",
    Repeat : "value == document.getElementsByName(getAttribute('to'))[0].value",
    Range : "getAttribute('min') < (value|0) && (value|0) < getAttribute('max')",
    Compare : "this.compare(value,getAttribute('operator'),getAttribute('to'))",
    Custom : "this.Exec(value, getAttribute('regexp'))",
    Group : "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",
    ErrorItem : [document.forms[0]],
    ErrorMessage : ["以下原因导致提交失败：\t\t\t\t"],
    Validate : function(theForm, mode){
        var obj = theForm || event.srcElement;
        var count = obj.elements.length;
        this.ErrorMessage.length = 1;
        this.ErrorItem.length = 1;
        this.ErrorItem[0] = obj;
        for(var i=0;i<count;i++){
            with(obj.elements[i]){
                var _dataType = getAttribute("dataType");
                if(typeof(_dataType) == "object" || typeof(this[_dataType]) == "undefined"){
                	if(!this.verifyRepeat(getAttribute("noRepeat"),getAttribute("repeatTab"),
            				getAttribute("name"),value,getAttribute("repeatId"))){
            			this.AddError(i, getAttribute("msg"));
            		}
                	continue;
                }
                this.ClearState(obj.elements[i]);
                if(getAttribute("require") == "true" && value == "") {
                	this.AddError(i, getAttribute("msg"));
                	continue;
                }
                if(_dataType!="Require"&&value==""){
                	continue;
                }
                switch(_dataType){
                    case "IdCard" :
                    case "Date" :
                    case "Repeat" :
                    case "Range" :
                    case "Compare" :
                    case "Custom" :
                    case "Group" :
                    case "Limit" :
                    case "LimitB" :
                    case "SafeString" :
                    case "Filter" :
                        if(!eval(this[_dataType]))    {
                            this.AddError(i, getAttribute("msg"));
                        }else{
                        	if(!this.verifyRepeat(getAttribute("noRepeat"),getAttribute("repeatTab"),
                    				getAttribute("name"),value,getAttribute("repeatId"))){
                    			this.AddError(i, getAttribute("repeatMsg"));
                    		}
                        }
                        break;
                    default :
                        if(!this[_dataType].test(value)){
                            this.AddError(i, getAttribute("msg"));
                        }else{
                        	if(!this.verifyRepeat(getAttribute("noRepeat"),getAttribute("repeatTab"),
                    				getAttribute("name"),value,getAttribute("repeatId"))){
                    			this.AddError(i, getAttribute("repeatMsg"));
                    		}
                        }
                        break;
                }
            }
        }
        if(this.ErrorMessage.length > 1){
            mode = mode || 1;
            var errCount = this.ErrorItem.length;
            switch(mode){
            case 2 :
                for(var i=1;i<errCount;i++)
                    this.ErrorItem[i].style.color = "red";
            case 1 :
                alert(this.ErrorMessage.join("\n"));
                this.ErrorItem[1].focus();
                break;
            case 3 :
                for(var i=1;i<errCount;i++){
                try{
                    var span = document.createElement("SPAN");
                    span.id = "__ErrorMessagePanel";
                    span.style.color = "red";
                    this.ErrorItem[i].parentNode.appendChild(span);
                    span.innerHTML = this.ErrorMessage[i].replace(/\d+:/,"*");
                    }
                    catch(e){alert(e.description);}
                }
                this.ErrorItem[1].focus();
                break;
            default :
                alert(this.ErrorMessage.join("\n"));
                break;
            }
            return false;
        }
        $(obj).submit();//验证完自动提交表单。
        return true;
    },
    limit : function(len,min, max){
        min = min || 0;
        max = max || Number.MAX_VALUE;
        return min <= len && len <= max;
    },
    LenB : function(str){
        return str.replace(/[^\x00-\xff]/g,"**").length;
    },
    ClearState : function(elem){
        with(elem){
            if(style.color == "red")
                style.color = "";
            var lastNode = parentNode.childNodes[parentNode.childNodes.length-1];
            if(lastNode.id == "__ErrorMessagePanel")
                parentNode.removeChild(lastNode);
        }
    },
    AddError : function(index, str){
        this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].elements[index];
        this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + ":" + str;
    },
    Exec : function(op, reg){
        return new RegExp(reg,"g").test(op);
    },
    compare : function(op1,operator,op2){
        switch (operator) {
            case "NotEqual":
                return (op1 != op2);
            case "GreaterThan":
                return (op1 > op2);
            case "GreaterThanEqual":
                return (op1 >= op2);
            case "LessThan":
                return (op1 < op2);
            case "LessThanEqual":
                return (op1 <= op2);
            default:
                return (op1 == op2);           
        }
    },
    MustChecked : function(name, min, max){
        var groups = document.getElementsByName(name);
        var hasChecked = 0;
        min = min || 1;
        max = max || groups.length;
        for(var i=groups.length-1;i>=0;i--)
            if(groups[i].checked) hasChecked++;
        return min <= hasChecked && hasChecked <= max;
    },
    DoFilter : function(input, filter){
    	return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(/\s*,\s*/).join("|")), "gi").test(input);
    },
    IsIdCard : function(number){
        var date, Ai;
        var verify = "10x98765432";
        var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
        //var area = null;//['','','','','','','','','','','','北京','天津','河北','山西','内蒙古','','','','','','辽宁','吉林','黑龙江','','','','','','','','上海','江苏','浙江','安微','福建','江西','山东','','','','河南','湖北','湖南','广东','广西','海南','','','','重庆','四川','贵州','云南','西藏','','','','','','','陕西','甘肃','青海','宁夏','新疆','','','','','','台湾','','','','','','','','','','香港','澳门','','','','','','','','','国外'];
        var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
        if(re == null) return false;
        //if(re[1] >= area.length || area[re[1]] == "") return false;
        if(re[2].length == 12){
            Ai = number.substr(0, 17);
            date = [re[9], re[10], re[11]].join("-");
        }
        else{
            Ai = number.substr(0, 6) + "19" + number.substr(6);
            date = ["19" + re[4], re[5], re[6]].join("-");
        }
        if(!this.IsDate(date, "ymd")) return false;
        var sum = 0;
        for(var i = 0;i<=16;i++){
            sum += Ai.charAt(i) * Wi[i];
        }
        Ai +=  verify.charAt(sum%11);
        return (number.length ==15 || number.length == 18 && number == Ai);
    },
    IsDate : function(op, formatString){
        formatString = formatString || "ymd";
        var m=0, year=0, month=0, day=0;
        switch(formatString){
            case "ymd" :
                m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
                if(m == null ) return false;
                day = m[6];
                month = m[5]*1;
                year =  (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
                break;
            case "dmy" :
                m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
                if(m == null ) return false;
                day = m[1];
                month = m[3]*1;
                year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
                break;
            default :
                break;
        }
        if(!parseInt(month)) return false;
        month = month==0 ?12:month;
        var date = new Date(year, month-1, day);
        return (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth()+1) && day == date.getDate());
        function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
    },
    verifyRepeat : function(rp,repeatTab,repeatZd,value,repeatId){
    	if(typeof(rp) == "object" ||rp!="true" || value==""){
    		return true;
    	}
    	var zt=true;
    	$.ajax({
    		type : "post",
    		async: false,
    		url : "verifyRepeat.action",
    		data : {"repeatTab":repeatTab,"repeatZd":repeatZd,"value":value,"repeatId":repeatId},
    		dataType : "html",
    		success : function(data) {
    			if(data=="1"){
    				zt=true;
    			}else{
    				zt=false;
    			}
    			
    		},error:function(){
    			zt=false;
    		}
    	});
    	return zt;
    }
 };