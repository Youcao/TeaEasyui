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
<title>搜索评论</title>
<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">

</head>
<body style="margin:0px">
	<div style="width:1010px;height:80px">
		<div style="width:100%;height:50px;font-family: 微软雅黑;margin-top:20px;padding-left: 12px;float:left;display: block">关键字：<input id="commentKeyWords" style="width:150px;"></input><br>
			<!-- 日期区域 -->
			<div style="float:left;margin-left:250px;margin-top:-28px">
				<table>
					<tr>
						<td>评论日期:</td>
						<td>
							<input id="commentDate" class="easyui-datebox" data-options="sharedCalendar:'#cc'">
						</td>
					</tr>
				</table>
				<div id="cc" class="easyui-calendar"></div>
			</div>
			
			<input id="searchComments" type="button" value="search" style="margin-top:-26px;margin-left:50px"></input>
		</div>
		<!-- 评论区域 -->
			<div style="background-color: white;border:1px solid red;z-index: 9999;position: relative;top:80px;left:1px;border-bottom-left-radius: 5px;border-bottom-right-radius: 5px;width:1006px;display: block">
					<ul id="searchedComments" style="list-style-type: none;margin:0px;padding:0px;">
					</ul>
			</div>
	</div>
</body>
</html>

<script>
	$(document).ready(function(){
		searchAjax("","");
	});

	$("#searchComments").click(function(){
		//先获取到关键字和日期参数
		var keyWords = $("#commentKeyWords").val();
		var date = $("#commentDate").val();
		searchAjax(keyWords,date); 
	});
	//ajax搜索评论
	function searchAjax(keyWords,date){
		$("#searchedComments").children("li").remove();
		//进行按条件搜索评论的ajax请求
		$.get("CommentAction!searchCommentsByAjax.action?keyWords="+keyWords+"&date="+date,function(data){
			if(data.length==0){
				var comn ="<li style='border-bottom:gray 1px dashed;height:80px;'><span style='position: relative;left:300px;top:-35px'>未搜索到相关评论噢o(╥﹏╥)o</span></li>";
				$("#searchedComments").append(comn);
			}
			else{
				for(var n=0;n<data.length;n++){
					if(n==0){
						var comnFirst="<li id='commentID"+data[n].commentId+"' style='border-bottom:gray 1px dashed;height:80px;'><img style='width:30px;height:30px;border-radius: 5px;position: relative;left:30px;top:-60px' alt='' src='"+data[n].commentImage+"'><span style='font-weight: bold;position: relative;left:50px;top:-60px'>"+data[n].commentUsername+"</span><span style='position: relative;left:80px;top:-60px'>"+data[n].commentDate+"<span style='margin-left:60px'>对应商品ID为："+data[n].productId+"</span></span><span style='position: relative;left:-220px;top:-25px'>"+data[n].commentWords+"</span><div style='width:100px;height:20px;position: relative;top:-60px;left:900px'><a style='text-decoration: none' href='#'>回复</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='text-decoration: none' href='javascript:deleteCommentById("+data[n].commentId+")'>删除</a></div></li>";
						$("#searchedComments").append(comnFirst);
					}else{
						var comn = "<li id='commentID"+data[n].commentId+"' style='border-bottom:gray 1px dashed;height:80px'><img style='width:30px;height:30px;border-radius: 5px;position: relative;left:30px;top:10px' alt='' src='"+data[n].commentImage+"'><span style='font-weight: bold;position: relative;left:50px;top:10px'>"+data[n].commentUsername+"</span><span style='left:-650px;position: relative;left:80px;top:10px'>"+data[n].commentDate+"<span style='margin-left:60px'>对应商品ID为："+data[n].productId+"</span></span><span style='position: relative;left:-210px;top:45px'>"+data[n].commentWords+"</span><div style='width:100px;height:20px;position: relative;top:7px;left:900px'><a style='text-decoration: none' href='#'>回复</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='text-decoration: none' href='javascript:deleteCommentById("+data[n].commentId+")'>删除</a></div></li>";
						$("#searchedComments").append(comn);
					}
				}
			}
		});
	}
	
	//删除评论方法
	function deleteCommentById(id){
		var userChoice = window.confirm("您确定要删除该条评论吗？");
		if(userChoice){
			$("#commentID"+id).remove();
			$.get("CommentAction!deleteComment.action?commentId="+id,function(data){
				if(data=="deleteSuccess"){
					alert("已成功删除该条评论");
				}else{
					alert("删除评论失败！");
				}
			})
		}
	}
	
	$("#commentKeyWords").keyup(function(){
		//先获取到关键字和日期参数
		var keyWords = $("#commentKeyWords").val();
		var date = $("#commentDate").val();
		searchAjax(keyWords,date); 
	});
</script>