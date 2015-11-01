<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	//var baseUrl = "whyldb_";
	$(document).ready(function(){
		$("img").each(function(){
			$(this).error(function(){ 
				 //alert($(this).attr("src"));
				 $(this).attr("src","fun/system/images/no_pic.png");
				 //alert($(this).attr("src"));
				 //this.src="fun/system/images/no_pic.png";
				});
			  });
		});
</script>
<!--版权 开始-->
<div class="nchjbxx-footer">
	<p>技术支持：<a href="http://www.pdwy.com.cn/" target="_blank">北京派得伟业科技发展有限公司</a>    &nbsp;&nbsp;&nbsp;&nbsp;承办单位：<a href="#" target="_blank">第六师共青团农场</a></p>
</div>
<!--版权 结束-->
