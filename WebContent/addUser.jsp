<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base  href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/TeaMallBackStage/">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	
	<title>添加用户</title>
</head>
<body>
<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="添加用户" style="width:100%;max-width:400px;padding:30px 60px;">
		<span id="namemessage"></span>
		<form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
			<div style="margin-bottom:20px">
				<input id="userid" class="easyui-textbox" name="userid" style="width:100%" data-options="label:'userid:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input id="password" class="easyui-passwordbox" name="password" style="width:100%" data-options="label:'password:',required:true">
			</div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="registerUser()" style="width:80px" handler:queding>提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
		</div>
	</div>
	<script>
		function clearForm(){
			$('#ff').form('clear');
		}
		
		function infoMessage(title,message){
    		$.messager.alert(title,message,'info');
    	}
		
		function errorMessage(title,message){
    		$.messager.alert(title,message,'error');
    	}
	</script>
	
	<script>
	function registerUser(){
		
		$.get("admin/UserAction!add.action?userid="+$("#userid").val()+"&password="+$("password").val(),function(response){
	            if(response=('registerSuccess'))
	            {
	            	infoMessage("温馨提示","注册成功");
	        
	            }else{
	            	errorMessage("温馨提示","注册失败")
	            }
	            $('#ff').form('clear');
	      
		});   	
	}
	</script>
	

</body>

</html>