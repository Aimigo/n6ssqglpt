<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

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
<title>ckplayer简单调用演示</title>

</head>

<body>
	<div id="video"
		style="position:relative;z-index: 100;width:600px;height:400px;">
		<div id="a1"></div>
	</div>
	<div id="flashcontent"></div>
	<script type="text/javascript" src="js/offlights.js"></script>
	<script type="text/javascript" src="ckplayer/ckplayer.js"
		charset="utf-8"></script>
	<script type="text/javascript">
	var spjs='{a href="http://www.ckplayer.com"}{font color="#FFFFFF" size="12"}hhhhhhhhh{/font}{/a}';
		var flashvars = {
			f : '<%=basePath %>videos/transcod/1413014975305.flv',//视频地址
			i : '<%=basePath %>videos/images/1413014975305.jpg',//初始图片地址
			my_url:encodeURIComponent(window.location.href),
			c : 0,
			b : 1
		};
		var video = [ 'http://movie.ks.js.cn/flv/other/1_0.mp4->video/mp4',
				'http://www.ckplayer.com/webm/0.webm->video/webm',
				'http://www.ckplayer.com/webm/0.ogv->video/ogg' ];
		CKobject.embed('ckplayer/ckplayer.swf', 'a1', 'ckplayer_a1', '600',
				'400', false, flashvars, video)

		//开关灯
		var box = new LightBox("flashcontent");
		function closelights() {//关灯
			box.Show();
			CKobject._K_('video').style.width = '940px';
			CKobject._K_('video').style.height = '550px';
			CKobject.getObjectById('ckplayer_a1').width = 940;
			CKobject.getObjectById('ckplayer_a1').height = 550;
		}
		function openlights() {//开灯
			box.Close();
			CKobject._K_('video').style.width = '600px';
			CKobject._K_('video').style.height = '400px';
			CKobject.getObjectById('ckplayer_a1').width = 600;
			CKobject.getObjectById('ckplayer_a1').height = 400;
		}
		/* function closelights(){//关灯
			alert(' 本演示不支持开关灯');
		}
		function openlights(){//开灯
			alert(' 本演示不支持开关灯');
		} */
	</script>
</body>
</html>
