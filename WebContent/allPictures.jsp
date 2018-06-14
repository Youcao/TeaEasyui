<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="/struts-tags"  prefix="s" %>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
</head>
<body style="margin: 5px;padding: 0px;">

    <div style="margin:5px 0;">
	<div style="margin:5px;">
	<span style="font-size:15px;">查询: </span>
		<input class="easyui-searchbox"  data-options="prompt:'请输入商品ID',searcher:doSearch" style="width:150px">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	
	    &nbsp;&nbsp;&nbsp;
		<span style="font-size:15px;">选择: </span>
		<select onchange="$('#dg').datagrid({singleSelect:(this.value==0)})">
			<option value="0">单选</option>
			<option value="1">多选</option>
		</select>
	
	</div>
	</div>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="$('#w').window('open')">Append</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="getSelected()">删除一行</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="getSelections()">删除多行</a>
	</div>
	<div id="w" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:450px;padding:10px;">
		
	
  <s:form action="admin/UploadAction!upload.action" method="post" enctype="multipart/form-data" validate="true">
      	<s:file  name="myfile"  label="请选择文件"></s:file>
        <s:file  name="myfile"  label="请选择文件"></s:file>
        <s:file  name="myfile"  label="请选择文件"></s:file>
        <s:file  name="myfile"  label="请选择文件"></s:file>
        <s:file  name="myfile"  label="请选择文件"></s:file>
  		<s:submit id="up" value  ="提交"></s:submit>
  </s:form>
	</div>
	
	<table id="dg" class="easyui-datagrid" title="" style="width:100%;height:500px;margin:5px;"
			data-options="singleSelect:true,pagination:true,collapsible:true,url:'admin/PictureAction!listPictureByPage.action',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'image_id',width:80,align:'left'">图片编号</th>
				<th data-options="field:'image_shoutu',width:100,align:'left'">首图</th>
				<th data-options="field:'image_qita1',width:100,align:'left'">系列图1</th>
				<th data-options="field:'image_qita2',width:100,align:'left'">系列图2</th>
				<th data-options="field:'image_qita3',width:100,align:'left'">系列图3</th>
				<th data-options="field:'image_qita4',width:100,align:'left'">系列图4</th>
			</tr>
		</thead>
	</table>
	
<script>
		function doSearch(value){
			$.get("admin/SearchAction!search.action?u.image_id="+value,function(data,status){
				alert(data);
				$('#d').window('open');
				
			});
		}
			
		
		
		function alert1()
		{
			$.messager.alert('温馨提示','图片插入失败','error');
		}
</script>

<script type="text/javascript">
		function getSelected(){
			var row = $('#dg').datagrid('getSelected');
			alert("sdf");
			$.messager.confirm('确认信息', '确认删除编号为:'+row.image_id+'茶叶图片?', function(r){
				if (r){
					$.messager.alert('图片编号:', row.image_id+":"+row.image_shoutu+":"+row.image_qita1);
					$.get("admin/DeleteAction!delete.action?u.image_id="+row.image_id,function(data,status){
						var noticeMessage='删除失败!';
						if(data==true)
							{
								var noticeMessage='删除成功!';
							   	$('#dg').datagrid('reload');
							}
							$.messager.show({
							title:'操作提示',
							msg:noticeMessage,
							timeout:2000,
							showType:'slide'})
					});
				}
			});
		}
		
		/* function getSelections(){
			var ss = [];
			var rows = $('#dg').datagrid('getSelections');
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				ss.push('<span>'+row.item_id+":"+row.productid+":"+row.attr1+'</span>');
			}
			$.messager.alert('Info', ss.join('<br/>'));
		} */
		
		function getSelections(){
			var ids = [];
			var rows = $('#dg').datagrid('getSelections');
			for(var i=0; i<rows.length; i++){
				ids.push(rows[i].image_id);
			}
			alert(ids.join('\n'));
		}

</script>
<c:if test="${requestScope.message eq 'uploadfail' }">
<script type="text/javascript">
$.messager.alert('温馨提示','图片插入失败','error');
</script>
</c:if>

	<script type="text/javascript">
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickCell(index, field){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					var ed = $('#dg').datagrid('getEditor', {index:index,field:field});
					if (ed){
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
					}
					editIndex = index;
				} else {
					setTimeout(function(){
						$('#dg').datagrid('selectRow', editIndex);
					},0);
				}
			}
		}
		function onEndEdit(index, row){
			var ed = $(this).datagrid('getEditor', {
				index: index,
				field: 'productid'
			});
			row.productname = $(ed.target).combobox('getText');
		}
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
		}
		function accept(){
			if (endEditing()){
				$('#dg').datagrid('acceptChanges');
			}
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		function getChanges(){
			var rows = $('#dg').datagrid('getChanges');
			alert(rows.length+' rows are changed!');
		}
		
		
		function submitForm(){
			var  code=$("[name='myfile']").val();
			alert(code);
			$.get("admin/CodeAction!checkCode.action?code="+code,function(data){
				if(data=='false')
				{
					$('#dlg').html("<span style='color:red;font-weight:bold'>验证码错误！</span>");
					errorMessage('温馨提示','验证码错误！');
					$('#dlg').dialog('open');
				}else
				{
					$('#ff').submit();
				}
			})
		}
	</script>
	
</body>
</html>