<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ckplayer</title>

</head>

<body>
	<div id="video"
		style="position:relative;z-index: 100;width:${width}px;height:${height}px;">
		<div id="a1"></div>
	</div>
	<div id="flashcontent"></div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/upload/ckplayer64/js/offlights.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/upload/ckplayer64/ckplayer/ckplayer.js"
		charset="utf-8"></script>
	<script type="text/javascript">
	function ckmarqueeadv(){return '${ms}'}
	var spjs='{a href="javascript_window.location.href()"}{font color="#FFFFFF" size="12"}${ms}{/font}{/a}';
		var flashvars = {
			f : '${videosrc}',//视频地址
			i : '${imgsrc}',//初始图片地址
			my_url:encodeURIComponent(window.location.href),
			c : 0,
			b : 1
		};
		var video = [ 'http://movie.ks.js.cn/flv/other/1_0.mp4->video/mp4',
				'http://www.ckplayer.com/webm/0.webm->video/webm',
				'http://www.ckplayer.com/webm/0.ogv->video/ogg' ];
		CKobject.embed('upload/ckplayer64/ckplayer/ckplayer.swf', 'a1', 'ckplayer_a1', '${width}',
				'${height}', false, flashvars, video)
		//开关灯
		var box = new LightBox("flashcontent");
		function closelights() {//关灯
			box.Show();
			CKobject._K_('video').style.top = '-120px';
			CKobject._K_('video').style.left = '-120px';
			CKobject._K_('video').style.width = '940px';
			CKobject._K_('video').style.height = '550px';
			CKobject.getObjectById('ckplayer_a1').width = 940;
			CKobject.getObjectById('ckplayer_a1').height = 550;
		}
		function openlights() {//开灯
			box.Close();
			CKobject._K_('video').style.top = '0px';
			CKobject._K_('video').style.left = '0px';
			CKobject._K_('video').style.width = '${width}px';
			CKobject._K_('video').style.height = '${height}px';
			CKobject.getObjectById('ckplayer_a1').width = ${width};
			CKobject.getObjectById('ckplayer_a1').height = ${height};
		}
	</script>
</body>
</html>
