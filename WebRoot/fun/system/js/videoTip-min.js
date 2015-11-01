/**
 * videoTip.js
 * @extends jquery.1.9.1
 * @fileOverview 中搜视频tip词
 * @author SDX
 * @email dxshen@ec01.cn
 * @version 1.0
 * @date 2014-2-10
 *
 */


//所有tip词通用
var global = {
    inTipArea: false,                  //判断搜索下拉框是否需要隐藏
    tipAreaShow: false,            //判断下拉框是否打开
    strWordValue: "",               //当前输入框内的内容
    tipWordLength: 0,              //tip词长度
    currentItemNum: -1,         //当前选中tip词的索引
    itemMax: 3,		                  //tip词的总数
    stopChange: false,               //判断用户是否在通过上下键改变的搜索词,false时表示没有用上下键改变tip词
    $currentTip: null                 //当前展开的tip词框
}

//自定义属性
var source =
    '<div class="msg_nr">'
        + '<% for(var i=0;i<tipWord.length;i++){%>'
        + '<div class="msg_out" id="word<%=i%>" word-data="">'
        + ' <ul class="msg_list_ul">'
        + '<li class="li1"><%==tipWordSearch[i]%></li>'
        + ' <li class="li2"><%=tipType[i]%></li>'
        + '<li class="li3"></li>'
        + '</ul>'
        + '<% if (isNeedPic[i]) { %>'
        + '<div class="msg_tab">'
        + '<div class="msg_img"><a href=<%=tipLink[i]%> target="_blank"><img src="<%=tipPicSrc[i]%>" /></a></div>'
        + '<div class="msg_txt">'
        + '<a href=<%=tipLink[i]%> target="_blank"><h2><%=tipWordTitle[i]%></h2></a>'
        + '<div class="nr">'
        + '<span class="tit"><%=tipInfoOneTitle[i]%></span><span><%=tipInfoOneTxt[i]%></span>'
        + '</div>'
        + ' <div class="nr">'
        + '<span class="tit"><%=tipInfoTwoTitle[i]%></span><span><%=tipInfoTwoTxt[i]%></span>'
        + '</div> '
        + ' <div class="nr"> '
        + '<span class="tit"><%=tipInfoThreeTitle[i]%></span><span><%=tipInfoThreeTxt[i]%></span>'
        + ' </div>'
        + '  <a href=<%=tipLink[i]%> target="_blank" class="ckxq">查看详情</a> '
        + '  </div></div>'
        + '<% } %>'
        + '</div>'
        + '<% } %>'
        + '</div>'

var tipData = function () {
    var obj = new Object();
    obj.searchWord = "";            //搜索词
    obj.tipWord = [];                     //提示词全部
    obj.tipWordSearch = [];          //提示词应显示
    obj.tipWordTitle = [];               //提示词用作电影标题
    obj.tipType = [];                       //提示词类型
    obj.tipPicSrc = [];                    //提示词配图
    obj.tipInfoOneTitle = [];                  //提示词的信息第一组标题
    obj.tipInfoOneTxt = [];                  //提示词的信息第一组信息
    obj.tipInfoTwoTitle = [];                  //提示词的信息第二组标题
    obj.tipInfoTwoTxt = [];                  //提示词的信息第二组信息
    obj.tipInfoThreeTitle = [];               //提示词的信息第三组标题
    obj.tipInfoThreeTxt = [];               //提示词的信息第三组信息
    obj.tipLink = [];                        //查看详情的详细链接
    obj.isNeedPic = [];                      //判断是否需要配图
    return obj;
}

$(function () {
    /******tip词*******/
//DOM元素定义
    var $input = $("#textfield");               //搜索框
    var $tipShow = $("#tipArea");            //提示词框
    var $form = $("#frmTop")

    /**************通用模块*************/
        //1.搜索框聚焦增加搜索词变动判定
    $input.blur();
    $input
        .focus(function () {
            // console.info("focus");
            global.tipAreaShow = true;
            if (!$(this).val()) {
                $tipShow.hide();
            }
            checkInputChange()
        })
        .blur(function () {         //2.搜索框失去焦点判断是否还在tip词框内，不在则隐藏tip词框
            //console.info("blur");
            if (!global.inTipArea) {
                $tipShow.hide();
                global.tipAreaShow = false;
                global.currentItemNum = -1;
            }
        })

//2.鼠标划入划出tip词框判断，滑出时焦点聚焦回搜索框
    $tipShow
        .on("mouseenter", function () {
            global.inTipArea = true;
        })
        .on("mouseleave", function () {
            global.inTipArea = false;
            $input.focus();
        })


//3.判断用户输入框内信息的改变
    function checkInputChange() {
        if (global.tipAreaShow && !global.stopChange) {	//是否需要刷新tip词
            var strWord = encodeURIComponent($input.val());
            if (global.strWordValue != strWord) {                //搜索框文本改变
                if (strWord == "") {
                    $tipShow.hide();
                    $tipShow.html("");
                    strWord == "";
                    global.strWordValue = "";
                } else {
                    GetXmlData(strWord);
                    global.strWordValue = strWord;
                }
            }
            setTimeout(checkInputChange, 150);
        }

    }

//4.当搜索框打开时添加键盘事件
    $(document).on("keydown", function (e) {
        if (global.tipAreaShow) {
            keyDownCode(e);
        }
    })

//5.读取tip词内容
    function GetXmlData(word) {
        var p = "w=" + word + "&y=25&utf=1";
        jQuery.ajax({
            type: "get",
            url: "http://tip.zhongsou.com/tip?",
            data: p,
            dataType: "jsonp",
            success: function (d) {
                var currentData = new tipData();
                var dword = decodeURIComponent(d[0].word);
                if (d[1].length < 1) {
                    $tipShow.hide();
                } else {
                    var nowWord = $input.val();
                    if (dword != nowWord) {
                        $tipShow.hide();
                    } else {
                        global.tipWordLength = d[1].length;
                        if (global.tipWordLength > 6)global.tipWordLength = 6;
                        //解析JOSN数据
                        currentData.searchWord = dword;
                        for (var i = 0; i < global.tipWordLength; i++) {
                            currentData.tipWord[i] = decodeURIComponent(d[1][i].n);              //tip词
                            /******************************************tip词截字***********************************************************************/
                            currentData.tipWordSearch[i] = currentData.tipWord[i];
                            currentData.tipWordSearch[i] = '<b style="color:black"></b>' + currentData.tipWordSearch[i].replace(currentData.searchWord, '<span>' + currentData.searchWord + '</span>');
                            if (currentData.tipWord[i].length > 18) {
                                currentData.tipWordTitle[i] = currentData.tipWord[i].substr(0, 18) + "...";
                            } else {
                                currentData.tipWordTitle[i] = currentData.tipWord[i];
                            }

                            /*****************************************************************************************************************/
                            currentData.tipType[i] = decodeURIComponent(d[1][i].d);
                            if (currentData.tipType[i]) {
                                currentData.isNeedPic[i] = true;
                                currentData.tipType[i] += " >"
                            } else {
                                currentData.isNeedPic[i] = false;
                            }
                            currentData.tipPicSrc[i] = decodeURIComponent(d[1][i].c);
                            if (!currentData.tipPicSrc[i])currentData.tipPicSrc[i] = "images/nopic.jpg";
                            currentData.tipLink[i] = d[1][i].r;

                            var tipInfoAll = decodeURIComponent(d[1][i].g);
                            var t = tipInfoAll.split(";");
                            if (t[0]) {
                                var titleOne = t[0].split(":");
                                currentData.tipInfoOneTitle[i] = titleOne[0] + "：";
                                currentData.tipInfoOneTxt[i] = titleOne[1];
                            }
                            if (t[1]) {
                                var titleTwo = t[1].split(":");
                                currentData.tipInfoTwoTitle[i] = titleTwo[0] + "：";
                                currentData.tipInfoTwoTxt[i] = titleTwo[1];
                            }
                            if (t[2]) {
                                var titleThree = t[2].split(":");
                                currentData.tipInfoThreeTitle[i] = titleThree[0] + "：";
                                currentData.tipInfoThreeTxt[i] = titleThree[1];
                            }
                            //alert( currentData.tipWord[i])
                        }

                        /*将搜索的结果显示在搜索框内*/
                        global.$currentTip = $tipShow;
                        var render = template.compile(source);
                        var tipHtml = render(currentData);
                        $tipShow.html(tipHtml);
                        $tipShow.find("div .msg_out").each(function (index) {
                            var _type = currentData.tipType[index].replace(">", "")
                            $(this).attr("word-data", currentData.tipWord[index] + " " + _type.replace(/\s/g, ""));
                        })
                        $tipShow.find("img").on("error", function () {
                            $(this).attr("src", "http://video.zhongsou.com/images/nopic.jpg");
                        })
                        $tipShow.show();
                        var firstInfoArea = $("#word0").find('.msg_tab');
                        if (firstInfoArea.html()) {
                            firstInfoArea.show();
                            $tipShow.children(".msg_nr").css("height", "156px");
                        } else {
                            $tipShow.children(".msg_nr").css("height", "100%");
                        }
                        global.itemMax = global.tipWordLength;
                    }
                }


            }
        })
    }

//6.tip词鼠标滑入改变选中项索引，改变className
    $tipShow.on("mouseenter", ".msg_out ul", function () {
        var $tipItem = $(this).parent();
        global.currentItemNum = $tipItem.index();
        $tipItem.addClass("msg_on").siblings().removeClass("msg_on");
        var tt = $tipItem.children().length;
        if (tt == 2) {
            $tipShow.children(".msg_nr").css("height", "156px");
            $tipShow.find(".msg_tab").hide();
            $tipItem.find(".msg_tab").show();
        } else {
            $tipShow.children(".msg_nr").css("height", "100%");
            $tipShow.find(".msg_tab").hide();
        }

    })

//7.tip词鼠标点击的时候提交表单
    $tipShow.on("click", ".msg_out ul", function () {
        var word = $(this).parent().attr("word-data");
        $input.val(word);
        $form.submit();
    })
//8.tip词词条变色并赋值tip词到搜索框
    function setItem(index) {
        var className = "msg_on";
        var item = global.$currentTip.find(".msg_out").eq(index);
        item.addClass(className).siblings().removeClass(className);
        var word = item.attr("word-data");
        //alert(word)
        $input.val(word);
        var tt = item.children().length;
        if (tt == 2) {
            $tipShow.children(".msg_nr").css("height", "156px");
            $tipShow.find(".msg_tab").hide();
            item.find(".msg_tab").show();
        } else {
            $tipShow.children(".msg_nr").css("height", "100%");
            $tipShow.find(".msg_tab").hide();
        }

    }

//8.键盘事件
    function keyDownCode(event) {
        if (!global.tipAreaShow) { //如果提示框未打开则不调用
            return false;
        }
        if (event.keyCode == 40) //下箭头
        {
            global.currentItemNum++;
            if (global.currentItemNum >= global.itemMax)//到达最下一个选项
            {
                global.currentItemNum = 0;
            }
            setItem(global.currentItemNum);
            global.stopChange = true; //停止更换tip词
        }
        else if (event.keyCode == 38) //上箭头
        {

            global.currentItemNum--;
            if (global.currentItemNum < 0)//到达最下一个选项
            {
                global.currentItemNum = global.itemMax - 1;
            }
            setItem(global.currentItemNum);
            global.stopChange = true; //停止更换tip词

        }
        else {
            global.stopChange = false; //开始更换tip词
            global.currentItemNum = -1;
            checkInputChange()
        }
    }

})
/**主程序结束**/


//改变URL（复用自www.zhongsou.com）
function churl(o, q) {
    var v = document.getElementById('textfield');
    if (v) {
        v = v.value;
    } else {
        v = '';
    }
    v = ztrim(v);
    if (v == '输入您感兴趣的话题,呈现更多精彩！') {
        v = '';
    }
    //if(v!=''){
    var u = q;
    if (u.indexOf('mp3.zhongsou.com/musicResult.html') != -1) {
        v = encodeURI(v);
    } else {
        a = '';
        for (var i = 0; i < v.length; ++i) {
            var c = v.charAt(i);
            if (v.charCodeAt(i) < 128) {
                c = escape(c);//  */+@
                if (c == '+') {
                    c = '%2B';
                }
            }
            a += c;
        }
        v = a;
        a = null;
    }
    if (u.indexOf('w=') != -1) {
        q = u.replace(new RegExp('w=[^&$]*'), 'w=' + v);
    } else if (u.indexOf('?') != -1) {
        q += '&w=' + v;
    } else {
		if(u.indexOf('#!poi!!q=') != -1)q += v;
        else q += '?w=' + v;
    }
    if (u.indexOf('v=百科') != -1) {
        q += '&word=' + v + '&type=3';
    } else if (u.indexOf('v=社区') != -1) {
        q += '&word=' + v;
    }
    if (u.indexOf('v=门户') != -1) {
        q += '&tp=2';
    }
    o.href = q;
    //}
}


function ztrim(s) {
    if (s == null || s == undefined) {
        return s;
    }
    var c, b = 0, e = s.length;
    for (var i = 0; i < e; i++) {
        c = s.charAt(i);
        if (c == ' ' || c == '   ') {
            b++;
        } else {
            break;
        }
    }
    for (var i = e - 1; i >= b; i--) {
        c = s.charAt(i);
        if (c == ' ' || c == '   ') {
            e--;
        } else {
            break;
        }
    }
    return s.substring(b, e);
}