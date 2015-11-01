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
<!doctype html>
<html>
<head>
    <title>FlexPaper</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style type="text/css" media="screen">
        html, body	{ height:100%; }
        body { margin:0; padding:0; overflow:auto; }
        #flashContent { display:none; }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/upload/flexpaper/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/upload/flexpaper/js/flexpaper.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/upload/flexpaper/js/flexpaper_handlers.js"></script>
</head>
<body>
<div style="position:absolute;left:10px;top:10px;">
<div id="documentViewer"  style="width:${width}px;height:${height}px"></div>
<script type="text/javascript">

    $('#documentViewer').FlexPaperViewer(
            { config : {

                SWFFile : '${src}',

                Scale : 0.6,
                ZoomTransition : 'easeOut',
                ZoomTime : 0.5,
                ZoomInterval : 0.2,
                FitPageOnLoad : true,
                FitWidthOnLoad : false,
                FullScreenAsMaxWindow : false,
                ProgressiveLoading : false,
                MinZoomSize : 0.2,
                MaxZoomSize : 5,
                SearchMatchAll : false,
                InitViewMode : 'Portrait',
                RenderingOrder : 'flash,html',
                StartAtPage : '',

                ViewModeToolsVisible : true,
                ZoomToolsVisible : true,
                NavToolsVisible : true,
                CursorToolsVisible : true,
                SearchToolsVisible : true,
                WMode : 'window'
            }}
    );
</script>

</div>
</body>
</html>