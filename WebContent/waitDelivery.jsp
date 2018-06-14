<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body style="margin: 0px; padding: 5px;">

	<table id="dg" style="width:100%;height:300px" class="easyui-datagrid" fitColumns="true" fit="true"
			data-options="rownumbers:true,pagination:true,url:'CartAction!listDeliveryByPage.action',method:'get',toolbar:'#tb',footer:'#ft'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'cart_id',width:80">订单编号</th>
				<th data-options="field:'user_id',width:100">用户编号</th>
				<th data-options="field:'product_id',width:80,align:'right'">产品编号</th>
				<th data-options="field:'product_number',width:80,align:'right'">购买数量</th>
			</tr>
		</thead>
	</table>
	<!-- 这是选择框 --><!-- 
	<div id="ft" style="margin:10px 0;">
		<span>Selection Mode: </span>
		<select onchange="$('#dg').datagrid({singleSelect:(this.value==0)})">
			<option value="0">Single Row</option>
			<option value="1">Multiple Rows</option>
		</select>
		SelectOnCheck: <input type="checkbox" checked onchange="$('#dg').datagrid({selectOnCheck:$(this).is(':checked')})">
		CheckOnSelect: <input type="checkbox" checked onchange="$('#dg').datagrid({checkOnSelect:$(this).is(':checked')})"><br/><br/><br/><br/><br/>
	</div> -->
	<!-- 这是上面选择框的设计 -->
	<div id="tb" style="padding:2px 5px;">
		
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser()">删除用户</a>
	</div>

	<div id="dlg" class="easyui-dialog" style="width:400px;height:250px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post">
        	<table cellspacing="10px;">
        		<tr>
        			<td>cart_id(订单编号)</td>
        			<td><input name="cart_id" class="easyui-validatebox" required="true" style="width: 100px;"></td>
        		</tr>
        		<tr>
        			<td>user_id(用户编号)</td>
        			<td><input name="user_id" class="easyui-validatebox" required="true" style="width: 100px;"></td>
        		</tr>
        		<tr>
        			<td>product_id(商品编号)</td>
        			<td><input name="product_id" class="easyui-validatebox" required="true" style="width: 100px;"></td>
        		</tr>
        		<tr>
        			<td>product_number(商品数量)</td>
        			<td><input name="product_number" class="easyui-validatebox" required="true" style="width: 100px;"></td>
        		</tr>
        	</table>
        </form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
	</div>

<script type="text/javascript">
	var url;
	function deleteUser(){
		var row=$('#dg').datagrid('getSelected');
		if(row){
			$.messager.confirm("系统提示","您确定要删除编号为"+row.cart_id+"的订单吗?",function(r){
				if(r){
					$.post('CartAction!deleteCart.action',{delId:row.cart_id},function(r){
						if(r.result=='true'){
							$.messager.alert("系统提示","已成功删除这条记录!");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示",result.errorMsg);
						}
					},'json');
				}
			});
		}
	}
	
	function newUser(){
		$("#dlg").dialog('open').dialog('setTitle','添加订单');
		$('#fm').form('clear');
		url='CartAction!addCart.action';
	}
	
	
	function editUser(){
		var row=$('#dg').datagrid('getSelected');
		if(row){
			$("#dlg").dialog('open').dialog('setTitle','编辑订单');
			$('#fm').form('load',row);
			url='CartAction!editCart.action?cart_id='+row.cart_id;
		}
	}
	
	
	function saveUser(){
		$('#fm').form('submit',{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					$('#dlg').dialog('close');
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	$(function(){
		var pager = $('#dg').datagrid().datagrid('getPager');	//得到页码
	})
		
</script>
  
</body>
</html>