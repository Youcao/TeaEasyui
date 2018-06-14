<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base  href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/TeaMallBackStage/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
 		
<form id="ff" method="post" >
<!-- <input  type="hidden"  name=i  value="5"/> -->   <!-- 直接在后面用问号也可以，用hidden也可以 -->
			<div style="margin-bottom:20px">
				输入要删除用户的ID<input class="easyui-textbox"  id="userid" name="name" style="width:350px" data-options="label:'Name:',required:true">
			</div>
	
		</form>
<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" name="Submit" >Submit</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">Clear</a>
		</div>
	<script>
	
 	$("[name='Submit']").click(function(){  //焦点失去事件           
 	//	alert($("#userid").val());
  	 	$.get("admin/UserAction!del.action?userid="+$("#userid").val(),function(data,status){     //结果直接映射在data和status里面
			if(data=='delSuccess')
			{
				alert("成功删除UserID为"+$("#userid").val()+"的用户");
				   //htnl里面写html代码
			}else{
				alert("删除失败");
			 
			}
			
		});  
	}) 
			/*  var userid=document.getElementById("userid").value;
			alert(userid);
			//$("#ff").submit();
			$('#ff').form('submit');  */
	
		function clearForm(){
			
			$('#ff').form('clear');
		}
	</script>



<!-- 输入要删除用户的ID
<input type="text" id="userid">
<button id="a" onclick="del()" >确认</button> -->
</body>
<!-- 
<script type="text/javascript">
function del() {
	var userid=document.getElementById("userid").value;
	var choice=confirm("确认删除ID为"+userid+"的用户吗?");
	if(choice){
	//	location.href="admin/OrderAction!aaaa.action?i=5";
		location.href="admin/UserAction!sss.action?i=5";
	}
}


</script> -->
</html>