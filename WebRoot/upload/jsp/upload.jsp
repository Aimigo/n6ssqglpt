<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE html>
<html>
<head>
<title>uploadjsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/upload/css/stream-v1.css"
	rel="stylesheet" type="text/css">
</head>
<body  style="width: 100%;">
	<div style="width: 95%;">
		<button style="float: right;" id="xzwjbut" ></button>
		<div id="i_stream_files_queue" style="width: 100%;"></div>
		<div>
			<button style="float: right;" onclick="javascript:_t.upload();">开始上传</button>
			<div id="msg" style=" color: red">${message}</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/upload/js/stream-v1.js"></script>
	<script type="text/javascript">
		var path = "${pageContext.request.contextPath}";
		path += "/upload/swf/FlashUploader.swf";
		var type = "${lx}";
		var hight = 80;
		var smt = 1;
		var file0 = '';
		var file1 = '';
		var file2 = '';
		mul = false;
		if ("media" == type) {
			filetype = [ '.mp4', '.flv','.f4v','.3gp', '.wmv', '.rmvb', '.rm',
					'.avi', '.mov', '.mpg', '.asf', '.asx', '.mpeg', '.mpe',
					'.wmv9' ];
		} else if ("img" == type) {
			filetype = [ '.bmp', '.jpeg', '.jpg', '.png', '.gif' ];
		} else if ("imgs" == type) {
			filetype = [ '.bmp', '.jpeg', '.jpg', '.png', '.gif' ];
			hight = 300;
			smt = 200;
			mul = true;
		} else if ("doc" == type) {
			filetype = [ '.txt', '.doc', '.docx', '.ppt', '.pptx', '.xls',
					'.xlsx', '.pdf' ];
		} else {
			filetype = [];
		}
		/**
		 * 配置文件（如果没有默认字样，说明默认值就是注释下的值）
		 * 但是，on*（onSelect， onMaxSizeExceed...）等函数的默认行为
		 * 是在ID为i_stream_message_container的页面元素中写日志
		 */
		var config = {
			browseFileId : "xzwjbut",
			/** 选择文件的ID, 默认: i_select_files */
			browseFileBtn : "<div>选择文件</div>",
			/** 显示选择文件的样式, 默认: `<div>请选择文件</div>` */
			filesQueueId : "i_stream_files_queue",
			/** 文件上传容器的ID, 默认: i_stream_files_queue */
			filesQueueHeight : hight,
			/** 文件上传容器的高度（px）, 默认: 450 */
			autoUploading : false,
			/** 选择文件后是否自动上传, 默认: true */
			postVarsPerFile : {
				/** 上传文件时传入的参数，默认: {} */
				lx : type
			},
			swfURL : path,
			/** SWF文件的位置 */
			tokenURL : "uploadfile_tk.action",
			/** 根据文件名、大小等信息获取Token的URI（用于生成断点续传、跨域的令牌） */
			frmUploadURL : "uploadfile_upload.action",
			/** Flash上传的URI */
			uploadURL : "uploadfile_upload.action",
			simLimit : smt,
			/** 单次最大上传文件个数 */
			multipleFiles : mul,
			/** 多个文件一起上传, 默认: false */
			extFilters : filetype,
			/** 允许的文件扩展名, 默认: [] */
			onSelect : function(list) {
				document.getElementById("msg").innerHTML = "";
				try {
					window.parent.uploadfilebegin();
				} catch (e) {
					// TODO: handle exception
				}

			},
			/** 选择文件后的响应事件 */
			onMaxSizeExceed : function(size, limited, name) {
				document.getElementById("msg").innerHTML = "对不起，系统暂不支持上传2G以上文件";

			},
			/** 文件大小超出的响应事件 */
			onExtNameMismatch : function(name, filters) {
				document.getElementById("msg").innerHTML = "对不起，系统暂不支持该文件格式";
			},
			/** 文件的扩展名不匹配的响应事件 */
			onComplete : function(file) {
				var t = eval('(' + file.msg + ')');
				file0 += t.upload_url0 + ",";
				file1 += t.upload_url1 + ",";
				file2 += t.upload_url2 + ",";
			},
			/** 单个文件上传完毕的响应事件 */
			onQueueComplete : function() {
				if(!mul){
				file0=file0.substring(0, file0.length-1);
				file1=file1.substring(0, file1.length-1);
				file2=file2.substring(0, file2.length-1);
				}
				try {
					window.parent.document.getElementById("upload_url0").value = file0;
					window.parent.document.getElementById("upload_url1").value = file1;
					window.parent.document.getElementById("upload_url2").value = file2;
				} catch (e) {
					// TODO: handle exception
				}
				try {
					window.parent.uploadfileend(type,file0, file1, file2);
				} catch (e) {
					// TODO: handle exception
				}
			}
		/** 全部文件完毕的响应事件 */
		};

		var _t = new Stream(config);
	</script>
</body>
</html>