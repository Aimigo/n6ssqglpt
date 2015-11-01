/**
 * Created with JetBrains WebStorm.
 * User: SDX
 * Date: 13-12-27
 * Time: 下午15:28
 * 视频3.4 综艺首页
 * vision:1.0
 */


$(function () {
    function VideoMain() {
        this.$videoBody = $(".whshh-hot");       //综艺页主体
        this.$column = this.$videoBody.children(".video331_zy_content"); //综艺页栏目
        this._init();
    };

    VideoMain.prototype = {
        _init: function () {
            //showPic();          //图片信息展示
            //this.$videoBody.find("img").lazyload({ effect: "fadeIn", skip_invisible: false});
            this.banner().changeColumn();
        },
        banner: function () {  //顶部轮播图
            var $leftBtn = $("#bannerLeftBtn");      //左翻按钮
            var $rightBtn = $("#bannerRightBtn");     //右翻按钮
            var $holder = $(".focus_piclist").find("ul");     //轮播的图片
            var $scrollBody = $(".zy_focus_boxmain");   //轮播主体区域
            new ZyScrollBar({
                leftBtn: $leftBtn,
                rightBtn: $rightBtn,
                scrollBody: $scrollBody,
                holder: $holder,
                _autoPlay: true
            })
            return this;
        },
        changeColumn: function () {
            $(".relax_theatre").each(function () {
                var $tag = $(this).find(".qsjc_ul li");
                var $cont = $(this).find(".tele_listul");
                changeTag($tag, $cont, "active dsj_active", "mouseover", function (o) {
                    var moreBtn = o.parent().siblings('.qsjc_more').find('a');
                    var currentHref = o.find('a').attr('href');
                    if (moreBtn.html()) {
                        moreBtn.attr('href', currentHref);
                    }
                });
            });

            var $topTen = $(".video331_tv_r");
            if ($topTen) {
                var $tag2 = $topTen.find(".phb_list li");
                var $cont2 = $topTen.children("ul");
                changeTag($tag2, $cont2, "active", "mouseover");
            }

            return this;
        }
    }

    new VideoMain()
})

/*轮播*/
function ZyScrollBar(settings) {
    this.leftBtn = settings.leftBtn;     //向左
    this.rightBtn = settings.rightBtn;     //向右
    this.scrollBody = settings.scrollBody || null; //轮播的整体区域
    this.holder = settings.holder;           //轮播图片
    this.scrollIng = false;
    this._autoPlay = settings._autoPlay || false;    //是否自动播放
    this.openPic = null;
    this._init().bindEvent();
}

ZyScrollBar.prototype = {
    _init: function () {
        this.currentIndex = 0;  //当前轮播到第几张图
        this.targetIndex = 0;   //当前轮播图要到第几张去
        this.item = this.holder.find("li");
        this.itemNum = this.item.length;         //一共几张图
        this.itemWidth = 126;  //一个的item的宽度

        //复制一份列表区
        var newHtml = this.holder.html();
        newHtml += newHtml + newHtml;
        this.holder.html(newHtml);
        this.item = this.holder.find("li");  //重置item区
        this.holder.css("left", -(this.itemWidth * this.itemNum) + "px")
        this.picOpen(this.item.eq(this.itemNum + 2))
        this.scrollSpeed = 500;          //滚动速度
        if (this._autoPlay) {   //是否自动播放
            this.autoPlayLag = 5000;     //自动播放间隔
            this.autoPlay();
        }
        return this;
    },
    bindEvent: function () {
        var self = this;
        this.leftBtn.on("click", function () {     //左翻
            if (!self.scrollIng) {
                self.targetIndex--;
                if (self.targetIndex < 0)self.targetIndex = self.itemNum - 1;
                self.scroll("left");
            }
        })
        this.rightBtn.on("click", function () {    //右翻
            if (!self.scrollIng) {
                self.targetIndex++;
                if (self.targetIndex >= self.itemNum)self.targetIndex = 0;
                self.scroll("right");
            }
        })
        this.holder.on("mouseenter", "li", function () {
            if (!self.scrollIng) {
                self.picOpen($(this))
            }
        })
        if (this._autoPlay) {
            this.scrollBody.on("mouseenter",function () {
                clearInterval(self.timer);
            }).on("mouseleave", function () {
                    self.autoPlay();
                })
        }
        return this;
    },
    picOpen: function (obj) {  //展开图片
        this.picClosed()
        this.openPic = obj;
        var $img = obj.find("img")
        var $mask = obj.find(".zyfocus_pic_tmc");
        $img.eq(0).hide();
        $img.eq(1).show();
        $mask.hide();
    },
    picClosed: function () {  //关闭展开的图片
        var obj = this.openPic;
        if (obj) {
            var $img = obj.find("img")
            var $mask = obj.find(".zyfocus_pic_tmc");
            $img.eq(0).show();
            $img.eq(1).hide();
            $mask.show();
        }
    },
    scroll: function (type) {
        if (this.scrollIng)return false;
        this.picClosed();
        this.scrollIng = true;
        var self = this;
        var targetLeft = parseInt(this.holder.css("left"));
        switch (type) {    //左右滚动
            case "right":
                targetLeft -= self.itemWidth;
                break;
            case "left":
                targetLeft += self.itemWidth;
                break;
        }
        self.holder.animate({
            left: targetLeft + "px"
        }, 150, function () {
            self.scrollIng = false;
            if (self.targetIndex == 0) {//归位
                self.holder.css("left", -(self.itemWidth * self.itemNum) + "px")
            } else if (self.targetIndex == self.itemNum - 1) {
                self.holder.css("left", -(self.itemWidth * self.itemNum - self.itemWidth) + "px");
            }
            setTimeout(function () {  //滚动后自动展开居中的图片
                if (!self.scrollIng) {
                    self.picOpen(self.item.eq(Math.abs(parseInt(self.holder.css("left")) / self.itemWidth) + 2));
                }
            }, 150)
        })
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


