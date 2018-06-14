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
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<title>巴山雀舌茶叶商城后台管理系统-登录</title>
</head>
<style type="text/css">
	span a:link {color: black} /* 未访问的链接 */
	span a:visited {color: black} /* 已访问的链接 */
	span a:hover {color: green} /* 鼠标移动到链接上 */
</style>

<body style="margin:0px;padding: 0px">
	<!-- 页面的底部背景图片 -->
	<div style="background-image: url('images/admin-login-bg.jpg');width:1366px;height:610px;background-size: 90% 123%;">
	</div>
	<!-- 标题文字 -->
	<div style="width:1366px;height:60px;position: absolute;top:20px">
		<span style="display:block;letter-spacing:3px;line-height:40px;text-align: center;color: black;font-family: 宋体;font-weight:bold;font-size:32px;text-shadow: 1px 1px 10px red;">巴山雀舌茶叶商城后台管理系统</span>
	</div>
	
	<!-- 这里是登录的表单 -->
	<div style="width:617px;height:368px;border:solid buttonhighlight 1px;border-radius: 5px;position: absolute;left:380px;top:140px;background-color: #fff;background-color: rgba(0,0,0,0.1);">
		<div style="width:100%;max-width:400px;padding:30px 60px;position:relative;top:40px;left:40px">
			<form id="ff" method="post" action="UserAction!login.action">
				<div style="margin-bottom:30px">
					<input class="easyui-textbox" id="username" name="username" prompt="Username" iconWidth="28" style="width:100%;height:34px;padding:10px;" data-options="label:'用户名:',required:true">
				</div>
				<div style="margin-bottom:30px">
					<input class="easyui-passwordbox" id="password" name="password" prompt="Password" iconWidth="28" style="width:100%;height:34px;padding:10px" data-options="label:'密&nbsp;&nbsp;&nbsp;码:',required:true">
				</div>
				<div style="margin-bottom:30px">
					<input class="easyui-textbox" name="code" style="width:50%;height:34px;padding:10px" data-options="label:'验证码:'">
					<img id="code" src="CodeAction.action" style="width: 90px;height: 33px;position: relative;top: 14px;margin-left: 10px;border-radius: 3px" />
					<span id="changeOne" style="position: relative;top:9px;margin-left: 10px;font-size: 12px"><a style="cursor:pointer;">看不清,换一张</a></span>
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" id="loginButton" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">登录</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">重置</a>
			</div>
		</div>
		
	</div>
	
	
	<!-- 底部版权声明 -->
	<div style="width:100%;height:52px;background-color: #426374;position: absolute;left:0px;top:610px">
		<span style="display:block;line-height:40px;text-align: center;color: white">Copyright 2010 - 2015 巴山雀舌 四川巴山雀舌茗茶实业有限公司 zuipin.cn All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;川ICP备10200063号-1</span>
	</div>
	<!-- 登录失败，弹出提示框 -->
	<c:if test="${requestScope.loginMessage eq 'loginFail' }">
		<script type="text/javascript">
		$.messager.alert('温馨提示','用户名或密码错误，登陆失败!','error');
		</script>
	</c:if>
</body>
</html>
<script>
	/* 消息提示框 */
	function alert1(title,message){
		$.messager.alert(title,message);
	}
	function errorMessage(title,message){
		$.messager.alert(title,message,'error');
	}
	function infoMessage(title,message){
		$.messager.alert(title,message,'info');
	}
	function questionMessage(title,message){
		$.messager.alert(title,message,'question');
	}
	function warningMessage(title,message){
		$.messager.alert(title,message,'warning');
	}
	/* 点击提交按钮后前台表单验证 */
	function submitForm(){
		/* 获取到表单上的参数值 */
		var username=$("[name='username']").val();
		var password=$("[name='password']").val();
		var  code=$("[name='code']").val();
		/* 先进行用户名和密码的验证，看是否为空 */
		/* 
		$("#username").validatebox({ 
		  required:true, 
		  validType:['length[1,6]','zhongwen'] 
		 }); 
		 $.extend($.fn.validatebox.defaults.rules,{ 
		  zhongwen:{ 
		   validator:function(value){ 
		    //如果符合中文 
		    if(username!=null){ 
		     return true; 
		    }else{
		    	message:'姓名必须填'
		    	return ;
		    } 
		   }, 
		  } 
		 }); 
		 */
		/* ajax验证验证码的正确性,如果验证码不正确，那么就不用去验证用户名和密码了 */
				$.get("CodeAction!checkCode.action?code="+code,function(data){
					if(data=='codeError')
					{
						$('#dlg').html("<span style='color:red;font-weight:bold'>验证码错误！</span>");
						errorMessage('温馨提示','亲，您的验证码填写错误了噢！');
						$('#dlg').dialog('open'); 
					}else
					{
						$('#ff').submit();
					}
				})
	}
		
	/* 清空表单 */
	function clearForm(){
		$('#ff').form('clear');
	}
	
	$(document).ready(function(){
		$('#dlg').dialog('close');//网页一打开让提示框消失
		
		$("#changeOne").click(function(){
			$("#code").attr("src","CodeAction.action?time="+new Date())
		})
		
	})
</script>
