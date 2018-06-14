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
<title>所有产品</title>
</head>
<style type="text/css">
	ul li div a:link {color:#79A5E5} /* 未访问的链接 */
	ul li div a:visited {color:#79A5E5} /* 已访问的链接 */
	ul li div a:hover {color: green} /* 鼠标移动到链接上 */
</style>
<body style="margin:0px;min-width: 1000px">
	<div style="width:1087px;height:80px;text-align: center;background-color:#222222;letter-spacing: 3px;color:white;margin-top:-20px;margin-left:-20px">
		<span style="font-size: 32px;font-family: 黑体;font-weight: 16px;line-height: 80px;">巴山雀舌茶叶商城商品列表</span>
	</div>

	<div >
		<ul id="teas" style="list-style-type: none">
			<!-- ajax动态请求数据 -->
			
			<!-- 回复区域 -->
							<!-- <div style="border-left:2px solid gray;width:925px;display:block;margin-left:80px;margin-top:30px;z-index: 100;position: relative;">
								<ul id="replyComments" style="list-style-type: none"> -->
									<!-- <li style="border-bottom:1px dashed green;min-height: 80px;margin-left:-30px;">
										<span><a id="respondent1" style="cursor:pointer">匿名</a>：@<a style="cursor:pointer">Song</a></span>
										<span style="margin-left:30px">2018-06-10</span><br><br>
										<span>这什么乱七八糟的评论</span>
										<a style="margin-left:664px">回复</a>&nbsp;&nbsp;&nbsp;&nbsp;<a>删除</a>
									</li>
									
									<li style="border-bottom:1px dashed green;min-height: 80px;margin-left:-30px;">
										<span><a id="respondent2" style="cursor:pointer">Song</a>：@<a style="cursor:pointer">匿名</a></span>
										<span style="margin-left:30px">2018-06-10</span><br><br>
										<span>这什么乱七八糟的评论</span>
										<a style="margin-left:664px">回复</a>&nbsp;&nbsp;&nbsp;&nbsp;<a>删除</a>
									</li>
									
									<li style="min-height: 80px;margin-left:-30px;">
										<span><a id="respondent3" style="cursor:pointer">123</a>：@<a style="cursor:pointer">Song</a></span>
										<span style="margin-left:30px">2018-06-10</span><br><br>
										<span>这什么乱七八糟的评论</span>
										<a style="margin-left:664px">回复</a>&nbsp;&nbsp;&nbsp;&nbsp;<a>删除</a>
									</li> -->
									<!-- <div style="border-left:2px solid gray;width:925px;display:block;margin-left:80px;margin-top:30px;z-index: 100;position: relative;">
								<ul id="replyComments" style="list-style-type: none">
								</ul>
							</div> -->
							<!-- 写评论区域 -->
							<div id="writeComment" style="border:1px solid red;width:925px;display:block;margin-left:80px;margin-top:30px;height:100px;background-color: gray;z-index: 9999;position: relative;display: none">
								<textarea id="writtenCommentWords" style="width:100%;height:70%;border:1px solid green;z-index: 9999"></textarea>
								<input id="sentComment" type="button" value="发送" style="display: block;margin-left:800px">
								<input id="cancel" type="button" value="取消" style="margin-left:860px;margin-top:-23px">
							</div>
		</ul>
	</div>
	<!-- 分页栏 -->
	<div id="paging"  style="float:none;" align="center">
   	 <a id="firstPage" href="javascript:void(0);" ">首页</a>
     <a id="previousPage" href="javascript:void(0);" ">&lt;上一页</a>
	   当前第<a id="nowPage" href="javascript:void(0);" class="on">1</a>页
	 <a id="nextPage" href="javascript:void(0);" ">下一页&gt;</a>
	 <a id="overPage" href="javascript:void(0);" ">尾页</a>
	<span ><em>共<b id="allPages">&nbsp;1&nbsp;</b>页&nbsp;&nbsp;到第</em><input name="page_num" style="width:50px;height:20px;margin-left:10px" value="1" ><em>&nbsp;页</em>&nbsp;&nbsp;<input type="button" value="确定" name="go_btn"></input></span>
   </div>
   
</body>
</html>

<script>
		var nowPage=1;
		var prePage = 1;
		var nextPage = 2;
		var allPages = 0;
		var count=3;
		//这里是展开和收起评论方法
		function showAllComments(x){
			if($('#'+x+'').css('display')=='none'){
				/* $('#'+x+'').css("display","block"); */
				listCommentsByAjax(x.substring(3));
				$('#'+x+'').fadeIn();
				$('#view-'+x+'').attr('src','images/toggle-collapse.png');
			}else{
				/* $('#'+x+'').css("display","none"); */
				$('#'+x+'').fadeOut();
				$('#comments'+x.substring(3)).children("li").remove();
				$('#view-'+x+'').attr('src','images/toggle-expand.png');
			}
		}
		
		//网页一加载就获取分页数据
		$(document).ready(function(){
			listTeaByPageAjax(nowPage,count);
		})
		//根据page和count参数对数据进行分页by ajax
		function listTeaByPageAjax(page,count){
			$("#teas").children("li").remove();
			var promotion="否";
			$.get("TeaAction!listTeasByPage.action?page="+page+"&rows="+rows,function(data){
				for(var n=0;n<data.length;n++){
					console.log(data);
					if(data[n].adPromotion==1){
						promotion="是";
					}else{
						promotion="否";
					}
					var teaId = 'tea'+data[n].productId ;
					var tea = "<li style='border:1px buttonhighlight solid;height:140px;padding:10px;border-radius: 5px;margin-top:20px'><div style='width:100px;height:100px;padding:20px 20px;border:1px solid buttonhighlight;float: left'><img alt='' src='"+data[n].productImage+"' style='width:100%;height:100%'></div><div style='margin-left:60px;width:530px;float: left;font-size:16px'><span>茶业名称："+data[n].productDate+data[n].productSeries+data[n].productBrand+data[n].productWeight+'克'+data[n].productName+data[n].productPackage+"</span></div><br><div style='margin-left: 60px;width:530px;float:left;font-size: 16px;margin-top:5px'>市场价格："+data[n].productMallPrice+"￥</div><br><div style='margin-left: 60px;width:530px;float:left;font-size: 16px;margin-top:5px'>本店价格："+data[n].productPrice+"￥</div><br><div style='margin-left: 60px;width:530px;float:left;font-size: 16px;margin-top:5px'>剩余库存："+data[n].productNum+"件</div><br><div style='margin-left: 60px;width:530px;float:left;font-size: 16px;margin-top:5px'>是否推广："+promotion+"</div><br><div style='width:25px;height:25px;position:relative;left:920px;top:-30px'><img id='view-"+teaId+"' alt='' style='width:100%;height:100%;cursor:pointer' src='images/toggle-expand.png' onclick='showAllComments(&quot;"+teaId+"&quot;)'></div><div id='"+teaId+"' style='background-color: white;border:1px solid red;z-index: 9999;position: relative;top:50px;left:-11px;border-bottom-left-radius: 5px;border-bottom-right-radius: 5px;width:1006px;display: none'><ul id='comments"+data[n].productId+"' style='list-style-type: none;margin:0px;padding:0px;'></ul></div></li>";
					$("#teas").append(tea);
				}
			})
			paging(page,count);
		}
		//分页条的数据 by ajax
		function paging(page,rows){
			$.get("TeaAction!pageBean.action?page="+page+"&rows="+count,function(data){
				console.log(data);
				//修改当前页、上一页、下一页、总页数数据
				nowPage = data.nowPage;
				prePage = data.previousPage;
				nextPage = data.nextPage;
				allPages = data.allPages;
				
				//刷新页面上的当前页
				$("#nowPage").text(nowPage);
				//刷新页面上的总页数
				$("#allPages").text(allPages);
			})
		}
		//ajax列出评论数据
		function listCommentsByAjax(id){
			$.get("CommentAction!listCommentByProductId.action?producuId="+id,function(data){
				console.log(data);
				if(data.length==0){
					var comn ="<li style='border-bottom:gray 1px dashed;height:80px;'><img style='width:30px;height:30px;border-radius: 5px;position: relative;left:-700px;top:10px' alt='' src='images/people.png'><span style='font-weight: bold;position: relative;left:-680px'>木有人啦</span><span style='position: relative;left:-650px'>未来ing...</span><span style='position: relative;left:-780px;top:30px'>暂无评论</span><div style='width:100px;height:20px;position: relative;top:7px;left:900px'><a style='text-decoration: none' href='#'>添加评论</a></div></li>";
					$("#comments"+id).append(comn);
				}
				else{
					for(var n=0;n<data.length;n++){
						if(n==0){
							var comnFirst="<li id='commentID"+data[n].commentId+"' style='border-bottom:gray 1px dashed;min-height:80px;'><img style='width:30px;height:30px;border-radius: 5px;position: relative;left:-700px;top:10px' alt='' src='"+data[n].commentImage+"'><span id='commentUsername"+data[n].commentId+"' style='font-weight: bold;position: relative;left:-680px'>"+data[n].commentUsername+"</span><span style='position: relative;left:-650px'>"+data[n].commentDate+"</span><span style='position: relative;left:-835px;top:30px'>"+data[n].commentWords+"</span><div style='width:100px;height:20px;position: relative;top:7px;left:900px'><a style='text-decoration: none' href='javascript:toWriteComment(&quot;replyComments"+data[n].commentId+"&quot;,&quot;commentUsername"+data[n].commentId+"&quot;)'>回复</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='text-decoration: none' href='javascript:deleteCommentById("+data[n].commentId+")'>删除</a></div><div style='border-left:2px solid gray;width:925px;display:block;margin-left:80px;margin-top:30px;z-index: 100;position: relative;'><ul id='replyComments"+data[n].commentId+"' style='list-style-type: none'></ul></div></li>";
							$("#comments"+data[n].productId).append(comnFirst);
							listAllReplyByCommentId(data[n].commentId);
						}else{
							var comn = "<li id='commentID"+data[n].commentId+"' style='border-bottom:gray 1px dashed;min-height:80px'><img style='width:30px;height:30px;border-radius: 5px;position: relative;left:30px;top:10px' alt='' src='"+data[n].commentImage+"'><span id='commentUsername"+data[n].commentId+"' style='font-weight: bold;position: relative;left:50px'>"+data[n].commentUsername+"</span><span style='left:-650px;position: relative;left:80px'>"+data[n].commentDate+"</span><span style='position: relative;left:-105px;top:30px'>"+data[n].commentWords+"</span><div style='width:100px;height:20px;position: relative;top:7px;left:900px'><a style='text-decoration: none' href='javascript:toWriteComment(&quot;replyComments"+data[n].commentId+"&quot;,&quot;commentUsername"+data[n].commentId+"&quot;)'>回复</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='text-decoration: none' href='javascript:deleteCommentById("+data[n].commentId+")'>删除</a></div><div style='border-left:2px solid gray;width:925px;display:block;margin-left:80px;margin-top:30px;z-index: 100;position: relative;'><ul id='replyComments"+data[n].commentId+"' style='list-style-type: none'></ul></div></li>";
							$("#comments"+data[n].productId).append(comn);
							listAllReplyByCommentId(data[n].commentId);
						}
					}
				}
			})
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
		//列出所有评论下的回复
		function listAllReplyByCommentId(x){
			$.get("CommentAction!getAllReplyComments.action?commentId="+x,function(data){
				for(var n=0;n<data.length;n++){
					var replyComment = "<li id='replyID"+data[n].replyId+"' style='border-bottom:1px dashed green;min-height: 80px;margin-left:-30px;'><span><a id='respondent"+data[n].replyId+"' style='cursor:pointer'>"+data[n].respondent+"</a>：@<a style='cursor:pointer'>"+data[n].byReply+"</a></span><span style='margin-left:30px'>"+data[n].replyDate+"</span><br><br><span>"+data[n].replyWords+"</span><a style='margin-left:664px;cursor:pointer' href='javascript:toWriteComment(&quot;replyComments"+data[n].commentId+"&quot;,&quot;respondent"+data[n].replyId+"&quot;)'>回复</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer' href='javascript:deleteReplyByReplyId("+data[n].replyId+")'>删除</a></li>";
					$("#replyComments"+x).append(replyComment);
				}
			});
		}
		
		//首页
		$("#firstPage").click(function(){
			nowPage = 1;
			listTeaByPageAjax(nowPage,count);
		});
		//上一页
		$("#previousPage").click(function(){
			if(nowPage==1){
				return false;
			}else{
				nowPage--;
				listTeaByPageAjax(nowPage,count);
			}
		});
		//下一页
		$("#nextPage").click(function(){
			if(nowPage==allPages){
				return false;
			}else{
				nowPage++;
				listTeaByPageAjax(nowPage,count);
			}
		});
		//尾页
		$("#overPage").click(function(){
			nowPage = allPages;
			listTeaByPageAjax(nowPage,count);
		});
		//跳转页面
		$("input[name='page_num']").keydown(function(e){
	    if(e.keyCode == 13){
	        $("input[name='go_btn']").click();
	    }
		});
		
		$("input[name='go_btn']").click(function(){
		    var goPage = $("input[name='page_num']").val();
		    if(goPage >= 1 && goPage <=allPages && goPage != nowPage){
		        nowPage = goPage;
		        listTeaByPageAjax(nowPage,count);
		    } else{
		        return false;
		    }
		});
		
		//点击回复
		function toWriteComment(x,y){
			if($('#writeComment').css('display')=='none'){
				$('#writeComment').fadeIn();
				sendCommentToIt(x,y);
			}else{
				$('#writeComment').fadeOut();
				/* $('#comments'+x.substring(3)).children("li").remove(); */
			}
		}

		//点击发送按钮发送评论
		function sendCommentToIt(x,y){
			var respondentUser = $("#"+y+"").text();
			var commentId = x.substring(13);
			$("#sentComment").click(function(){
				var words = $("#writtenCommentWords").val();
				if(words==""){
					alert("不能填写空评论哦");
				}else{
					addReply(respondentUser,commentId,words);
					$("#"+x+"").children("li").remove();
					listAllReplyByCommentId(commentId);
					$('#writeComment').fadeOut();
				}
			});

		}
		
		
		function addReply(byReply,commentId,replyWords){
			$.get("CommentAction!replyComment.action?byReply="+byReply+"&commentId="+commentId+"&replyWords="+replyWords,function(data){
				if(data=="replySuccess"){
					alert("回复成功");
				}else{
					alert("回复失败");
				}
			});
		}
		
		//删除回复
		function deleteReplyByReplyId(id){
			var userChoice = window.confirm("您确定要删除该条回复吗？");
			if(userChoice){
				$("#replyID"+id).remove();
				$.get("CommentAction!deleteReplyByReplyId.action?replyId="+id,function(data){
					if(data=="deleteReplySuccess"){
						alert("已成功删除该条评论");
					}else{
						alert("删除评论失败！");
					}
				})
			}
		}
		//点击取消按钮取消评论
		$("#cancel").click(function(){
			$('#writeComment').fadeOut();
		});
		//获取当前时间
		function getNowDate(){
			var nowTime = new Date();
			var year = nowTime.getFullYear();
			var month = nowTime.getMonth()+1;
			var day = nowTime.getDate();
			if(month<10){
				month = "0"+month
			}
			if(day<10){
				day = "0"+month;
			}
			
			return year+"-"+month+"-"+day;
		}
</script>