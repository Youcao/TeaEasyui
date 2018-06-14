<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link href="css/style.css" type="text/css" rel="stylesheet">

</head>
<body>

<div id="container">
	<div class="timeline_container">
		<div class="timeline">
			<div class="plus"></div>
			<div class="plus2"></div>
		</div>
	</div>
	<div class="item">
		<h3><span class="fl">柳州一通网</span><span class="fr">IP：221.7.207***&nbsp;&nbsp;&nbsp;&nbsp;2013-02-21 08:46:29</span></h3>
		<p class="con">帮我做的网站很好，很满意<br />支持一下下。。。</p>
		<h4><b>Reply：</b>管理员尚未回复</h4>
	</div>
	<div class="item">
		<h3><span class="fl">柳州</span><span class="fr">IP：221.7.207***&nbsp;&nbsp;&nbsp;&nbsp;2013-02-21 08:39:32</span></h3>
		<p class="con">很有创意</p>
		<h4><b>Reply：</b>管理员尚未回复</h4>
	</div>
	<div class="item">
		<h3><span class="fl">伪装的心</span><span class="fr">IP：218.56.32***&nbsp;&nbsp;&nbsp;&nbsp;2013-02-21 08:07:48</span></h3>
		<p class="con">很不错的页面啊<br />支持一下下。。。</p>
		<h4><b>Reply：</b>管理员尚未回复</h4>
	</div>
	<div class="item">
		<h3><span class="fl">千里</span><span class="fr">IP：111.11.189.***&nbsp;&nbsp;&nbsp;&nbsp;2013-02-20 23:52:25</span></h3>
		<p class="con">给你留言</p>
		<h4><b>Reply：</b>管理员尚未回复</h4>
	</div>
	<div class="item">
		<h3><span class="fl">漫画之迷</span> <span class="fr">IP：119.137.61.***&nbsp;&nbsp;&nbsp;&nbsp;2013-02-20 23:00:25</span></h3>
		<p class="con">网站真不错啊，漫画情不自禁地在此留了个言，哈哈。。。</p>
		<h4><b>Reply：</b>管理员尚未回复</h4>
	</div>
	<div class="item">
		<h3><span class="fl">水墨寒</span> <span class="fr">IP：27.224.56***&nbsp;&nbsp;&nbsp;&nbsp;2013-02-20 22:42:43</span></h3>
		<p class="con">自己先把沙发坐了！水墨寒个人官网（<a href="http://www.xwcms.net/" target="_blank">http://www.xwcms.net/</a>）正式开通！欢迎大家留意</p>
		<h4><b>Reply：</b>管理员尚未回复</h4>
	</div>
</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/smohan.blog.plug.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	$('#container').masonry({itemSelector : '.item'});
	
	function Arrow_Points(){
		var s = $("#container").find(".item");
		$.each(s,function(i,obj){
			var posLeft = $(obj).css("left");
			if(posLeft == "0px"){
				html = "<span class='rightcorner'></span>";
				$(obj).prepend(html);
			} else {
				html = "<span class='leftcorner'></span>";
				$(obj).prepend(html);
			}
		});
	}
	
	Arrow_Points();
	
});
</script>

</body>
</html>
