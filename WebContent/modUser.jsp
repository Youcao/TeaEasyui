<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>修改用户信息</title>
</head>
<body>
	<form id="ff" method="post">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="userid" id = "userid" style="width:300px" data-options="label:'UserID:',required:true">
				
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="password" style="width:300px" data-options="label:'Password:'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="username" style="width:300px" data-options="label:'UserName:'">
			</div>
		
		</form>
<div style="text-align:left;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" name="Submit" style="width:80px">Submit</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">Clear</a>
		</div>
<script>   
$("[name='Submit']").click(function(){  //Submit点击提交事件              Ajax
 	$.get("admin/UserAction!update.action?userid="+$("[name='userid']").val()+"&password="+$("[name='password']").val()+"&username="+$("[name='username']").val(),function(data,status){     //结果直接映射在data和status里面
		 if(data=='updateSuccess')
		{
			alert("修改成功")
		}else{
			alert("修改失败")
		} 
	}); 
});

		//$(document).ready(function(){    //当界面加载完成注入事件。因为现在就在ready中，所以不需要这句
			$("[name='userid']").blur(function(){  //焦点失去事件           
			alert("666");
				/* $.get("admin/UserAction!update.action?userid="+$("[name='userid']").val(),function(data,status){     //结果直接映射在data和status里面
				//	var result=xhr.responseText;
					//所有的http流传过来的数据，看似是true和false，实际是string内型
					if(data=='true')
					{
			
					}else{
						//$("#userExist").html("<b style='color:red'>X</b>");
					}
					
				}); */
				
				
			});
	//	})

function clearForm(){
	$('#ff').form('clear');
}


</script>



</body>
</html>