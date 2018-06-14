<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base  href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/TeaMallBackStage/">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<title>所有评论</title>

</head>
<body style="margin:0px;min-width: 1000px">
	<div style="border:1px solid black;display: block;width:500px">
		产品ID：&nbsp;&nbsp;<input id="productID" style="width:205px"></input><br><br>
		评论文字：<textarea id="commentWords" style="width:200px;height:100px"></textarea><br><br>
		评论图片：<input id="commentImage" type="file"><br><br>
		评论视频：<input id="commentVideo" type="file"><br><br>
		<input id="addComment" type="button" value="添加" style="margin-left:100px">
	</div>
</body>
</html>

<script>
	$("#addComment").click(function(){
		var proId = $("#productID").val();
		var comWords = $("#commentWords").val();
		
		if(proId==""||comWords==""){
			$("#addComment").attr('disabled',true);
			alert("请输入数据");
			$("#addComment").attr('disabled',false);
		}
		
		$.get("CommentAction!addComment.action?producuId="+proId+"&commentWords="+comWords,function(data){
			if(data=="addCommentSuccess"){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
		});
	});
</script>