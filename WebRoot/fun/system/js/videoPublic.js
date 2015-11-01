/**
 * Created with JetBrains WebStorm.
 * User: SDX
 * Date: 13-12-23
 * Time: 上午10:28
 * 视频3.4 公共方法
 * vision:1.0
 *
 *  1.选项卡切换
 *  2.页面中鼠标划入划出图片的效果
 *  3.用script标签实现异步加载图片
 *  4.解析url中的参数
 *  5.轮播
 *  6.顶部滑动栏
 *  7.分享与回到顶部区域
 */

/**
 *1.选项卡切换
 * @param {
		    $Tag:(object)        		导航标签
		    $conts:(object)       	    内容区
		    checkClass:(string)         选中标签的Class
			type:(string)
		  }
 */

function changeTag($Tag, $conts, checkClass, type, fn) {
    $Tag.each(function (index, ele) {
        $(this).on(type, function () {
            $Tag.removeClass(checkClass);
            $conts.hide();
            $(this).addClass(checkClass);
            delScript($conts.eq(index));
            $conts.eq(index).show();
            if (fn) {
                fn($(this))
            }
        })
    });
}

/**
 2.页面中鼠标划入划出图片的效果
 **/
function showPic() {
    $(".pic_tmc_box").off();
    $(".pic_tmc_box").on("mouseenter", function () {
        $(this).find(".tmz").show()
    })
    $(".pic_tmc_box").on("mouseleave", function () {
        $(this).find(".tmz").hide()
    })
}

/**
 *3.用script标签实现异步加载图片
 */
function delScript($item) {
    var $script = $item.find("script");
    if ($script.html()) {
        var realText = $script.html();
        $script.replaceWith(realText);
        showPic();
    }
}

/**
 *4.解析url中的参数
 */
function getString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURIComponent(r[2]);
    return null;
}


/**
 *5.轮播
 */
function ScrollBar(settings) {
    this.leftBtn = settings.leftBtn;     //向左
    this.rightBtn = settings.rightBtn;     //向右
    this.scrollBody = settings.scrollBody || null; //轮播的整体区域
    this.holder = settings.holder;           //轮播图片
    this.title = settings.title || null;   //标题区域
    this.indexBtn = settings.indexBtn || null;      //快捷跳转按钮
    this._autoPlay = settings._autoPlay || false;    //是否自动播放
    this.scrollType = settings.scrollType || "opacity"; //滚动方式 默认为渐隐
    this._init().bindEvent();
}

ScrollBar.prototype = {
    _init: function () {
        this.currentIndex = 0;  //当前轮播到第几张图
        this.targetIndex = 0;   //当前轮播图要到第几张去
        this.itemNum = this.holder.length;         //一共几张图
        //this.holderWidth = this.holder.parent().width(); //轮播区域宽度
        this.scrollSpeed = 500;          //滚动动画速度
        if (this._autoPlay) {   //是否自动播放
            this.autoPlayLag = 5000;     //自动播放间隔
            this.autoPlay();
        }
        return this;
    },
    bindEvent: function () {
        var self = this;
        this.leftBtn.on("click", function () {     //左翻
            self.targetIndex--;
            if (self.targetIndex < 0)self.targetIndex = self.itemNum - 1;
            self.scroll("left");
        })
        this.rightBtn.on("click", function () {    //右翻
            self.targetIndex++;
            if (self.targetIndex >= self.itemNum)self.targetIndex = 0;
            self.scroll("right");
        })
        if (this.indexBtn) {                   //直接跳转
            this.indexBtn.on("click", function () {
                if ($(this).index() > self.targetIndex) {
                    self.targetIndex = $(this).index();
                    self.scroll("right");
                } else if ($(this).index() < self.targetIndex) {
                    self.targetIndex = $(this).index();
                    self.scroll("left");
                }
            })
        }
        if (this._autoPlay) {
            this.scrollBody.on("mouseenter",function () {
                clearInterval(self.timer);
            }).on("mouseleave", function () {
                    self.autoPlay();
                })
        }
        return this;
    },
    scroll: function (type) {
        var self = this;
        this.indexBtn.removeClass("active").eq(this.targetIndex).addClass("active")
        var $current = this.holder.eq(this.currentIndex).show();
        var $target = this.holder.eq(this.targetIndex).show();
        if (this.title) {
            this.title.eq(this.currentIndex).hide();
            this.title.eq(this.targetIndex).show();
        }
        switch (this.scrollType) {
            case "opacity":
                $target.css("opacity", 0)
                $current.animate({
                    opacity: 0
                }, self.scrollSpeed, function () {
                    $current.hide();
                })
                $target.animate({
                    opacity: 1
                }, self.scrollSpeed, function () {
                })
                break;
            case "static":
                $current.hide();
                $target.show();
                break;
        }
        this.currentIndex = this.targetIndex;
    },
    autoPlay: function () {
        var self = this;
        if (this.timer)clearInterval(this.timer)
        this.timer = setInterval(function () {
            self.targetIndex++;
            if (self.targetIndex >= self.itemNum)self.targetIndex = 0;
            self.scroll("right");
        }, self.autoPlayLag)
    }
}

/**
 *6.顶部滑动栏
 */
function AutoNav(settings) {
    this.topBar = settings.topBar;
    this.fixNav = settings.fixNav;
    this._init().autoNav();
}

AutoNav.prototype = {
    _init: function () {
        this.topBar._top = this.topBar.offset() ? this.topBar.offset().top : 0;
        return this;
    },
    autoNav: function () {
        var self = this;
        if (!self.topBar._top)return false;
        $(window).on("scroll", function () {
            if ($(this).scrollTop() >= self.topBar._top) {
                self.fixNav.show()
            } else {
                self.fixNav.hide()
            }
        })

        $(window).on('resize', function () {
            if ($(this).scrollTop() >= self.topBar._top) {
                self.fixNav.show()
            } else {
                self.fixNav.hide()
            }
        })
    }
}

/**
 *7.分享与回到顶部区域
 */
function VideoShareBox(shareBtn, shareBox, backTopBtn) {
    this.shareBox = shareBox.hide();
    this.shareBtn = shareBtn;
    this.shareItem = this.shareBox.children('div');
    this.backTopBtn = backTopBtn || null;
    this.bindEvent();
}

VideoShareBox.prototype = {
    shareInfo: [
        {
            title: '新浪微博',
            url: 'http://service.weibo.com/share/share.php?url=[$url]&title=[$text]&source=中搜网&sourceUrl=[$url]&content=gb2312&pic=[$pic]'
        },
        {
            title: '腾讯微博',
            url: 'http://share.v.t.qq.com/index.php?c=share&a=index&url=[$url]&title=[$text]&pic=[$pic]'
        },
        {
            title: 'QQ空间',
            url: 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=[$url]&&summary=[$text]&pics=[$pic]'
        },
        {
            title: 'QQ好友',
            url: 'http://connect.qq.com/widget/shareqq/index.html?url=[$url]&title=[$text]&pics=[$pic]'
        }
    ],
    bindEvent: function () {
        var self = this;
        var shareBoxNeedShow = false;
        //划入显示
        this.shareBtn
            .on('mouseenter', function () {
                shareBoxNeedShow = true
                self.shareBox.show();
            })
            .on('mouseleave', function () {
                shareBoxNeedShow = false;
                setTimeout(function () {
                    if (!shareBoxNeedShow)self.shareBox.hide();
                }, 150)
            })
        this.shareItem.on('click', function () {
            var $url = window.location.href;
            var $title = document.title;
            self.changeShareCont($url, $title);
        })

        if (this.backTopBtn) {  //如果没有的话就不用了
            $(window).on('scroll', function () {
                if ($(this).scrollTop() > 300) {
                    self.backTopBtn.fadeIn();
                } else {
                    self.backTopBtn.fadeOut('fast');
                }
            })
        }

        //改变分享块的定位位置
        $(window).on('resize', function () {
            posFix();
        })
        posFix();
        function posFix() {//改变分享块的定位位置
            var $nav = $('.navigation').children('ul')
            var _left = $nav.offset().left + $nav.width() + 20;
            $(".video34_floatr").css("left", _left + 'px').show();
        }

    },
    changeShareCont: function ($url, $title, $pic) {
        var self = this;
        var contLength = $title.length;
        if (contLength > 120) {
            $title = $title.substring(0, 120) + "...";
        } else {
            $title = $title;
        }
        $pic = $pic || "";
        this.shareItem.find("a").each(function (index) {
            var hrefData = self.shareInfo[index].url;
            hrefData = hrefData.replace(/\[\$text\]/g, encodeURIComponent($title));
            hrefData = hrefData.replace(/\[\$pic\]/g, $pic);
            hrefData = hrefData.replace(/\[\$url\]/g, encodeURIComponent($url));
            $(this).attr("href", hrefData);
        })
    }
}
/**
 * 8. 导航栏二级菜单 操作
 */
 function secondNavDeal(){
	var nowCheckedNav;
	$(".navigation .navigation_ul1 li>a").each(function(index, element) {
        if($(this).attr("class") == "index_active"){
			nowCheckedNav = index;
			}
    });
	var topBarLi = $(".navigation_ul1").children("li"),
		childA,
		childDiv;
	topBarLi.on("mouseenter",function(){
		childA = $(this).children("a:first");
		childDiv = $(this).children("div:first");			
		childA.addClass("index_active");
		childA.children("span").addClass("spanHover");
		childDiv.show();
		})
		.on("mouseleave",function(){
			if($(this).index() != nowCheckedNav){
				childA.removeClass("index_active");				
			    childA.children("span").removeClass("spanHover");
				}
			if( $(this).children("div").length>0)childDiv.hide();
				
			});
	
	};
	
/*****************通用的均执行部分*****************/
$(function () {
     //导航条
    var $fixNav = $(".video331_floatw").hide();
    var $topBar = $(".navigation");
	$(".second_nav").remove();
	new secondNavDeal();
    new AutoNav({
        fixNav: $fixNav,
        topBar: $topBar
    });


    //分享
    var shareModule = $('.video34_floatr').find('li');
    var $shareBtn = shareModule.eq(0);
    var $shareBox = $shareBtn.find('.sp34_share_box');
    var $backTopBtn = shareModule.eq(2);
    new VideoShareBox($shareBtn, $shareBox, $backTopBtn);

})


/*************************************cookie相关*************************************/
function setCookie(c_name, value, expiredays) {
    var exdate = new Date()
    exdate.setHours(0);
    exdate.setMinutes(0);
    exdate.setSeconds(0);
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = c_name + "=" + encodeURIComponent(value) +
        ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
}

function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1) c_end = document.cookie.length
            return decodeURIComponent(document.cookie.substring(c_start, c_end))
        }
    }
    return ""
}


/*************************老虎机脚本***************************/
function SlotMachine(settings) {
    this.holder = settings.holder;
    this.startBtn = settings.startBtn;
    this.numBoard = settings.numBoard;
    this.itemHeight = settings.itemHeight;
    this.kindNum = settings.kindNum;
    this.machineState = "stop";   //运行状态
    this.userInfo = {
        giftId: 0,
        giftLevel: 0,
        giftName: '',
        giftSnNum: 0,
        againLink: ""
    };
    this.bingo = false;
    this._init().bindEvent();


}

SlotMachine.prototype = {
    _init: function () {
        this.numBoard.item = this.numBoard.children;
        return this;
    },
    bindEvent: function () {
        var self = this;
        this.startBtn.onclick = function () {
            if (self.machineState == "stop") {
                var uid = getCookie('uid');
                var random = Math.random() + Math.random();
                var _data = uid ? {type: 1, uid: uid, random: random} : {type: 1, random: random}
                $.ajax({
                    url: 'http://video.zhongsou.com/cgigame',
                    data: _data,
                    type: 'get',
                    dataType: 'json',
                    success: function (data) {
                        if (data) {
                            self.userInfo.againLink = data[0].w;
                            self.userInfo.againLink = self.userInfo.againLink ? self.userInfo.againLink + '.html' : "";
                            if (self.userInfo.againLink == undefined) self.userInfo.againLink = "";
                            try {
                                console.log(self.userInfo.againLink)
                            } catch (e) {

                            }
                            self.userInfo.uid = data[0].uid; //用户id
                            setCookie("uid", data[0].uid, 1);
                            if (data[0].flag == 0) {   //调试处01
                                //self.bingo = true;
                                self.start(self.createGiftAry(0));
                                //self.userInfo.giftLevel = 5;
                            } else {
                                self.bingo = true;
                                self.userInfo.giftId = data[0].g;
                                self.userInfo.giftLevel = data[0].gl;
                                self.userInfo.giftName = decodeURI(data[0].gn);
                                self.userInfo.giftSnNum = data[0].sn;
                                self.start(self.createGiftAry(parseInt(data[0].gl)));
                            }
                        }
                    },
                    error: function () {
                        self.start(self.createGiftAry(0));
                    },
                    timeout: 2000
                })
            }
        };
    },
    createGiftAry: function (giftType) {
        var giftAry = [];
        var _ary = [2, 3, 4, 5, 6, 7, 8]
        switch (giftType) {
            case 7:  //特等奖[ok]
                giftAry = [1, 1, 1, 1];
                break;
            case 6:  //一等奖[ok]
                giftAry = [1, 1, 1, this.tools.itemRandom(2, 8)];
                break;
            case 5:  //二等奖[ok]
                giftAry = [1, 1, this.tools.itemRandom(2, 8), this.tools.itemRandom(2, 8)];
                break;
            case 4:   //三等奖[ok]
                giftAry = [1, this.tools.itemRandom(2, 8), this.tools.itemRandom(2, 8), this.tools.itemRandom(2, 8)];
                if (giftAry[1] == giftAry[2]) {
                    giftAry[1] = giftAry[2] + 1;
                    if (giftAry[1] > 8)giftAry[1] = 6
                }
                break;
            case 3:   //四等奖[ok]
                var m = this.tools.itemRandom(2, 8);
                giftAry = [m, m, m, m];
                break;
            case 2:   //四等奖[ok]
                var m = this.tools.itemRandom(2, 8);
                giftAry = [m, m, m, m];
                break;
            case 1:   //20中搜币
                var m = this.tools.itemRandom(2, 8);
                var n = m + 1;
                if (n > 8)n = m - 1;
                giftAry = [m, m, m, n ];
                break;
            default :  //没中奖[ok]
                giftAry = _ary;
                giftAry.length = 4;
                break;
        }
        this.tools.randomAry(giftAry);
        return giftAry
    },
    start: function (giftAry) {
        this.machineState = "run";
        for (var i = 0; i < this.numBoard.item.length; i++) {
            this.scrollItem(this.numBoard.item[i], giftAry[i]);
        }
    },
    scrollItem: function (obj, num) {
        var self = this;
        var roundNum = 3;
        var roundHeight = this.itemHeight * this.kindNum;
        if (obj.timer)clearInterval(obj.timer);
        obj.state = "running";
        var currentTop = parseInt(this.tools.getStyle(obj, "backgroundPosition").split(" ")[1]);
        var targetTop = roundNum * roundHeight + (num - 1) * self.itemHeight;
        var speed = 0;
        var _a = Math.random() * 0.3 + 0.3; //加速度
        obj.timer = setInterval(function () {
            speed += _a;
            if ((targetTop - currentTop) <= roundHeight) {   //最后一圈转动减速
                _a = Math.abs(_a) * -1.2;
                if (speed + _a <= 15)speed = 15;
            }
            currentTop += speed;
            obj.style.backgroundPosition = "center " + currentTop + "px";
            if (currentTop >= targetTop) {
                clearInterval(obj.timer);
                obj.style.backgroundPosition = "center " + targetTop % roundHeight + "px";
                obj.state = "finish";
                self.checkFinish();
            }
        }, 30)
    },
    checkFinish: function () {
        var self = this;
        var items = this.numBoard.item;
        var allFinish = true;
        for (var i = 0; i < items.length; i++) {
            if (items[i].state == "running")allFinish = false;
        }
        if (allFinish) {
            self.machineState = "stop";
            self.checkResult();
        }
    },
    checkResult: function () {
        if (getStyle(this.holder, 'display') == 'none')return false
        if (this.bingo) {
            this.openGiftBoard();
        } else {
            this.openAgainBoard();
        }
    },
    createMask: function () {
        //遮罩
        var oMask = document.createElement('div');
        oMask.id = 'screenMask';
        var windowHeight = $(document).height();
        setStyle(oMask, {
                position: 'absolute',
                height: '100%',
                background: 'black',
                opacity: '0.8',
                filter: 'alpha(opacity:80)',
                top: '0px',
                height: windowHeight + 'px',
                width: '100%',
                zIndex: 100000
            }
        )
        document.body.appendChild(oMask);
        var oDiv = document.getElementById('tigerHolder');
        startMove(oDiv, -400, function () {
            oDiv.parentNode.removeChild(oDiv);
        });
        return oMask;
    },
    createShareInfo: function () {
        var html = '【中搜视频摇摇乐】我中奖了，中了' + this.userInfo.giftName + '，小伙伴们快来围观'
        html = encodeURIComponent(html);
        return html
    },
    openGiftBoard: function () {
        var self = this;
        var userInfo = this.userInfo;
        var oMask = this.createMask();
        //弹出框
        var oAlertBox = document.createElement('div');
        oAlertBox.className = 'happytiger_tc2';
        oAlertBox.id = 'alertBox'
        oAlertBox.innerHTML = this.createAlertBoxHtml();
        setStyle(oAlertBox, {
            position: 'fixed',
            top: Math.max(0, $(window).height() / 2 - 300) + 'px',
            left: '50%',
            marginLeft: '-212px',
            zIndex: 100001
        })
        document.body.appendChild(oAlertBox);
        var alertCloseBtn = document.getElementById('alertCloseBtn');
        alertCloseBtn.onclick = function () {
            oMask.parentNode.removeChild(oMask);
            oAlertBox.parentNode.removeChild(oAlertBox);
        }
        var submitInput = document.getElementById('submitInput');
        submitInput.onclick = function () {
            var qq = document.getElementById('qqInput').value;
            var tel = document.getElementById('telInput').value;
            var mail = document.getElementById('mailInput').value;
            if (self.tools.checkInput()) {
                submitInput.onclick = null;
                $.ajax({
                    url: 'http://video.zhongsou.com/cgigame',
                    data: {"type": 3, "uid": userInfo.uid, "g": userInfo.giftId, "sn": userInfo.giftSnNum, "le": userInfo.giftLevel, "ad": qq, "p": tel, "em": mail, "gl": userInfo.giftName},
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        if (data) {
                            var _flag = parseInt(data[0].flag);
                            switch (_flag) {
                                case 1:
                                    var giftPic = 'http://video.zhongsou.com/images/tigerActive2/gift' + self.userInfo.giftLevel + '.jpg';
                                    $("#congrat").html('<div class="zhn1">恭喜您，提交成功！工作人员将在三个工作日内与您联系~</div><div class="zhn2"></div><div class="zhn3 clearfix"><div class="zhn3l">嘚瑟给好友看</div><div class="zhn3r"><a target="_blank" href="http://service.weibo.com/share/share.php?url=http://video.zhongsou.com&title=' + self.createShareInfo() + '&content=gb2312&pic=' + giftPic + '"><img src="http://video.zhongsou.com/images/ds_03.png"></a><a target="_blank" href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=http://video.zhongsou.com&&summary=' + self.createShareInfo() + '&pics=' + giftPic + '"><img src="http://video.zhongsou.com/images/ds_05.png"></a><a target="_blank" href="http://widget.renren.com/dialog/share?url=http://video.zhongsou.com&title=&description=' + self.createShareInfo() + '&pic=' + giftPic + '"><img src="http://video.zhongsou.com/images/ds_07.png"></a></div></div></div>');
                                    break;
                                case 101:
                                    $("#congrat").html('<div class="zhn1">亲，您今天已经中过两次奖了，这个奖品就留给其他小伙伴吧，欢迎明天再来~</div>');
                                    break;
                                case 102:
                                    $("#congrat").html('<div class="zhn1">亲，您今天已经中过两次奖了，这个奖品就留给其他小伙伴吧，欢迎明天再来~</div>');
                                    break;
                                default :
                                    $("#congrat").html('<div class="zhn1">亲，您没有在规定时间内完成信息录入，奖品作废了，重新开始摇奖吧~</div>');
                                    break;
                            }
                            if (_flag == 1 || _flag == 103 || _flag == 104 || _flag == 105) {
                                $("#submitInput").html("再来一次").attr("href", 'http://video.zhongsou.com/dt/' + self.userInfo.againLink)
                            } else {
                                $("#submitInput").html("确定").on("click", function () {
                                    oMask.parentNode.removeChild(oMask);
                                    oAlertBox.parentNode.removeChild(oAlertBox);
                                })
                            }
                        }
                    },
                    error: function () {
                        $("#congrat").html('<div class="zhn1">亲，您没有在规定时间内完成信息录入，奖品作废了，重新开始摇奖吧~</div>');
                        $("#submitInput").html("确定").on("click", function () {
                            oMask.parentNode.removeChild(oMask);
                            oAlertBox.parentNode.removeChild(oAlertBox);
                        })
                    },
                    timeout: 5000
                })
            }
        }
    },
    createAlertBoxHtml: function () {
        var html;
        var gn = this.userInfo.giftLevel;
        // console.info(gn)
        /*if (gn == 5 || gn == 1) {
         //alert("中搜币")
         html = '<div class="happy_tiger2_zwyc3"><div class="close_button" id="alertCloseBtn"><img src="http://video.zhongsou.com/images/close11.png"></div><div class="good_show clearfix"><div class="good_show_l"><img src="http://video.zhongsou.com/images/tigerActive2/gift' + gn + '.jpg"></div><div class="good_show_r"><span class="span1">' + '哇塞，人气大爆发，亲，您中了:' + this.userInfo.giftName + '</span><span class="span2"><img src="http://video.zhongsou.com/images/jt.png"></span></div></div><div class="ewm clearfix"><div class="ewml"><p class="p1">中搜币领奖：需填写注册搜悦账号时所用的<span>手机号码</span>和<span>邮箱</span>。</p><p class="p2">10中搜币=1元<span><a target="_blank" href="http://bbs.zhongsou.com/1/20140318/12936034.html">详情&gt;&gt;</a></span></p></div><div class="ewmr"><ul class="clearfix"><li><img src="http://video.zhongsou.com/images/tigerActive2/erweima.jpg"><p>iPhone版</p></li><li><img src="http://video.zhongsou.com/images/tigerActive2/erweima.jpg"><p>Android版</p></li></ul></div></div><div class="zhn"  id="congrat"><div class="zhn1">请在五分钟以内填写以下领取信息</div><div class="zhn2 zhn22"></div><p class="sj">手机<input type="text" class="sj_input" id="telInput"></p><p class="sj yx">邮箱<input type="text" class="sj_input" id="mailInput"></p></div><div class="zwyc2"><a href="javascript:;" id="submitInput">提交</a></div></div>'
         } else {  */
        //alert("其他奖品");
        html = '<div class="happy_tiger2_zwyc2"><div class="close_button" id="alertCloseBtn"><img src="http://video.zhongsou.com/images/close11.png"></div><div class="good_show clearfix"><div class="good_show_l"><img src="http://video.zhongsou.com/images/tigerActive2/gift' + gn + '.jpg"></div><div class="good_show_r"><span class="span1">' + '哇塞，人气大爆发，亲，您中了:' + this.userInfo.giftName + '</span><span class="span2"><img src="http://video.zhongsou.com/images/jt.png"></span></div></div><div class="zhn" id="congrat"><div class="zhn1" >请在五分钟以内填写以下领取信息</div><div class="zhn2"></div><p class="sj qq"><span>QQ</span><input type="text" class="sj_input" id="qqInput"></p><p class="sj">手机<input type="text" class="sj_input" id="telInput"></p><p class="sj yx">邮箱<input type="text" class="sj_input" id="mailInput"></p></div><div class="zwyc2"><a href="javascript:;" id="submitInput">提交</a></div></div>'
        //}
        return html;
    },
    openAgainBoard: function () {
        var alertInfo = ['哎呀，什么都没中，换个姿势再来一次吧~', '差那么一点点就中了，再来一次肯定中~', '据说吹口气可以增加中奖概率，再试试吧~', '哎呀，什么都没中，先原地转三圈试试~', '离中奖肯定不远了，奖品在向你招手呢~']
        var oMask = this.createMask();
        //弹出框
        var oAlertBox = document.createElement('div');
        oAlertBox.className = 'happytiger_tc1';
        oAlertBox.id = 'alertBox'
        oAlertBox.innerHTML = '<div class="happytiger_tc1_box"><div class="close_button" id="alertCloseBtn"><img src="http://video.zhongsou.com/images/close.png" /></div><p class="p_agian">' + alertInfo[parseInt(Math.random() * alertInfo.length)] + '</p><div class="once_again"><a href="http://video.zhongsou.com/dt/' + this.userInfo.againLink + '">再来一次</a></div><div class="active_rule"><a href="http://video.zhongsou.com/activity/hd2014.html" target="_blank">活动规则</a></div></div>';
        setStyle(oAlertBox, {
            position: 'fixed',
            top: Math.max(0, $(window).height() / 2 - 170) + 'px',
            left: '50%',
            marginLeft: '-212px',
            zIndex: 100001
        })
        document.body.appendChild(oAlertBox);
        var alertCloseBtn = document.getElementById('alertCloseBtn');
        alertCloseBtn.onclick = function () {
            oMask.parentNode.removeChild(oMask);
            oAlertBox.parentNode.removeChild(oAlertBox);
        }
    },
    tools: {
        getStyle: function (obj, attr) {
            if (obj.currentStyle) {
                if (attr == "backgroundPosition") {
                    return obj.currentStyle.backgroundPositionX + " " + obj.currentStyle.backgroundPositionY;
                } else {
                    return obj.obj.currentStyle[attr];
                }
            } else {
                return  getComputedStyle(obj, false)[attr]
            }
        },
        itemRandom: function (low, high) {
            return parseInt(low + Math.random() * (high + 1 - low))
        },
        randomAry: function (ary) {
            ary.sort(function () {
                return Math.random() < 0.5 ? -1 : 1;
            })
            return ary;
        },
        checkInput: function () {
            var qq = document.getElementById('qqInput');
            var tel = document.getElementById('telInput');
            var mail = document.getElementById('mailInput');
            var mailExp = /^[\w\.]+@[0-9a-z\-]+(\.[a-z]{2,4}){1,2}$/i;
            var mailExp2 = /@zhongsou.com/i;
            var qqExp = /^[1-9]\d{4,12}$/;
            var telExp = /^0?(13[0-9]|15[012356789]|18[02356789]|14[57])[0-9]{8}$/;
            var testOk = true;
            expTest(qq, qqExp);
            expTest(tel, telExp);
            expTest(mail, mailExp);
            expTest(mail, mailExp2, 1);
            function expTest(obj, exp, type) {
                var m = exp.test(obj.value);
                m = type ? m : !m;
                if (m) {
                    testOk = false;
                    obj.style.border = '1px solid red';
                    $(obj).one('focus', function () {
                        $(this).css('border', 'none');
                    })
                }
            }

            return testOk;
        }
    }
}


/*************************************3-8抽奖活动*************************************/

$(function () {
    var viewInfoStr = getCookie('userView');
    checkPage();

    function checkPage() {
        var currentPage = window.location.href;
        var userViewInfo = viewInfoStr ? viewInfoStr.split('|') : [];
        if (!checkPageSame(currentPage, userViewInfo)) {   //是第一次访问
            userViewInfo.unshift(currentPage);
            if (userViewInfo.length >= 50)userViewInfo.length = 50;
            setCookie('userView', userViewInfo.join("|"), 1);
            //showTiger();//欢乐老虎机 2014年3月31日 已下线
        }
        //showTiger(); //改
    }

    function checkPageSame(currentPage, arr) {
        var theSame = false;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == currentPage)theSame = true;
        }
        return theSame;
    }

    function showTiger() {
        /****************添加样式***************/
        var oLink = document.createElement('link');
        var oLink2 = document.createElement('link');
        oLink.href = 'http://video.zhongsou.com/css/happy_tiger.css';
        oLink2.href = 'http://video.zhongsou.com/css/hllhj2.css';
        oLink.rel = 'stylesheet';
        oLink2.rel = 'stylesheet';
        oLink.type = 'text/css';
        oLink2.type = 'text/css';
        document.getElementsByTagName('head')[0].appendChild(oLink);
        document.getElementsByTagName('head')[0].appendChild(oLink2);
        var oDiv = document.createElement('div');
        oDiv.id = 'tigerHolder';
        oDiv.className = 'happytiger_tc3';
        oDiv.innerHTML = '<div class="happytiger_tc3_box"><div class="close_button" id="closeBtn"><img src="http://video.zhongsou.com/images/close.png" /></div><div class="zj_logo clearfix" id="numBoard"><div class="zj_logo1"></div><div class="zj_logo1 zj_logo2"></div><div class="zj_logo1 zj_logo3"></div><div class="zj_logo1"></div></div><div class="tiger_logo"><img src="http://video.zhongsou.com/images/tiger.png"></div><div class="begin_yj"><a href="javascript:;" id="startBtn">开始摇奖</a></div><div class="active_rule"><a href="http://video.zhongsou.com/activity/hd2014.html" target="_blank">详细规则>></a></div></div>'
        setStyle(oDiv, {
            position: 'fixed',
            right: "0px",
            bottom: "-340px",
            zIndex: 10001
        });
        document.body.appendChild(oDiv);
        startMove(oDiv, -20, function () {
            var closeBtn = $("#closeBtn");
            closeBtn.on('click', function () {
                $(oDiv).hide();
            })
            var startBtn = document.getElementById("startBtn");
            var numBoard = document.getElementById("numBoard");
            new SlotMachine({
                holder: oDiv,
                startBtn: startBtn,
                numBoard: numBoard,
                itemHeight: 67,           //一个item的高度
                kindNum: 8                  //有几种摇奖数字
            })
        });
    }

})


function startMove(obj, target, fnSuccess) {
    clearInterval(obj.timer);
    var current = parseInt(getStyle(obj, 'bottom'));
    var speed = target > current ? 10 : -10;
    obj.timer = setInterval(function () {
        current += speed;
        obj.style.bottom = current + 'px';
        if (Math.abs(target - current) < Math.abs(speed)) {
            obj.style.bottom = '-20px';
            clearInterval(obj.timer);
            fnSuccess && fnSuccess(obj);
        }
    }, 30)
}

function getStyle(obj, attr) {
    return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj, false)[attr];
}

function setStyle(obj, json) {
    for (i in json) {
        obj.style[i] = json[i];
    }
}










