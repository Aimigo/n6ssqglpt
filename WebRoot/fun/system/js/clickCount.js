/**
 * Created with JetBrains WebStorm.
 * User: SDX
 * Date: 13-12-25
 * Time: 上午9:53
 * To change this template use File | Settings | File Templates.
 */

(function () {

    function ClickCount(url) {
        this.contMaxLength = 200;   //发送的内容字符最大长度
        this.targetHref = "";   //去除本页跳转的a标签的统计发送不出去问题，记录a标签的href
        this.postUrl = url;
        this.postInfo = {
            eUrl: null,
            eName: null,
            eContent: null
        };
        this._init();
    }

    ClickCount.prototype = {
        _init: function () {   //初始化添加监听
            var self = this;
            document.attachEvent ? document.attachEvent("onclick", function (e) {
                e = e || window.event;
                var tar = e.target || e.srcElement;
                self.cmouse_click(e, tar);
            }) : document.addEventListener("click", function (e) {
                e = e || window.event;
                var tar = e.target || e.srcElement;
                self.cmouse_click(e, tar);
            }, false);
        },
        cmouse_click: function (e, tar) {  //点击事件处理
            var self = this;
            var tar = tar;
            var currentTar = tar;
            var tagname;
            var currentBlockId = "";
            var inA = false;
            var targetA = null;
            while (tar) {
                tagname = tar.tagName.toLocaleLowerCase()
                if (tagname == 'input' && tar.type == 'text') {
                    break;
                }
                currentBlockId = tar.getAttribute("blockId");
                if (tagname == "a") {
                    inA = true;
                    targetA = tar;
                }
                if (currentBlockId) {
                    var currentBlockType = tar.getAttribute("blockType") || 1
                    if (inA) {
                        if (targetA.getAttribute("target") != "_blank" && e.which == 1) {
                            if (e && e.preventDefault) {
                                e.preventDefault();       //IE中阻止函数器默认动作的方式
                            } else {
                                window.event.returnValue = false;
                            }
                            self.targetHref = targetA.getAttribute("href");
                        }
                    }
                    switch (currentBlockType) {     //0表示点中区域内任何标签都发送，1表示只发送a标签
                        case "0":
                            if (inA) {
                                self.postInfo.eUrl = targetA.href;
                            } else {
                                self.postInfo.eUrl = "";
                            }
                            self.postInfo.eContent = (currentTar.innerHTML).slice(0, self.contMaxLength);
                            self.postInfo.eName = currentBlockId;
                            self.cpost();
                            break;
                        default :
                            if (inA) {
                                self.postInfo.eUrl = targetA.href;
                                self.postInfo.eContent = (targetA.innerHTML).slice(0, self.contMaxLength);
                                self.postInfo.eName = currentBlockId;
                                self.cpost();
                            }
                            break;
                    }
                    break;
                } else {
                    tar = tar.parentNode;
                    if (!tar || tar == document || tar.tagName.toLocaleLowerCase() == "html") {
                        break;
                    }
                }
            }
        },
        cpost: function () {
            var self = this;
            $.ajax({
                url: self.postUrl,
                type: "post",
                data: self.postInfo,
                dataType: "jsonp",
                success: function (a) {

                }
            })
            if (self.targetHref && window.location.href != self.targetHref) {
                setTimeout(function () {
                    window.location.href = self.targetHref;
                }, 300)
            }
        }
    }
    var url = "http://www.zhongsou.com/UserActionsLog.php"
    new ClickCount(url)

})()
