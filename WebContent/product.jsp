<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<link rel="stylesheet" type="text/css" href="css/uploadify.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/jquery.uploadify.min.js"></script>
</head>
<script type="text/javascript">
var url;
//打开新商品对话框
function openProductAddDialog(){
	//在勾选情况下点击新增要先清除数据
	resetValue();
	$("#dlg").dialog("open").dialog("setTitle","添加商品");
	url="admin/TeaAction!save.action";//为url赋值
}
function reload(){
	//刷新页面
	location.replace(location.href); 
}
//重置对话框内数据
function resetValue(){
	$("#product_brand").val("");
	$("#product_name").val("");
	$("#product_sxwx").val("");
	$("#product_date").datebox("setValue","");
	$("#product_weight").val();
	$("#product_series").combobox("setValue","");
	$("#product_package").combobox("setValue","");
	$("#mall_price").val("");
	$("#cost_price").val("");
	$("#product_num").val("");
	$("#product_image").val("");
	$("#shifoutuiguang").val("");
}
//关闭对话框
function closeAddDialog(){
	$("#dlg").dialog("close");
	resetValue();
}
//提交新商品数据
function addproduct(){
	$("#fm").form("submit",{
		url:url,	
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("系统提示",result.errorMsg);
				return;
			}else{
				$.messager.alert("系统提示","保存成功");
				resetValue();
				$("#dlg").dialog("close");
				$("#dg").datagrid("reload");
			}
		}
	});
}
//删除选中的商品数据
function deleteTea(){
	//获得选中数据对象
	var selectedRows=$("#dg").datagrid('getSelections');
	if(selectedRows.length==0){
		$.messager.alert("系统提示","请选择要删除的数据！");
		return;
	}
	var strIds=[];//要删除的序号组合
	for(var i=0;i<selectedRows.length;i++){
		strIds.push(selectedRows[i].product_id);
	}
	var ids=strIds.join(",");
	$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			//ajax提交 delIds
			$.post("admin/TeaAction!delete.action",{delIds:ids},function(result){
				if(result.success){
					$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert('系统提示',result.errorMsg);
				}
			},"json");
		}
	});
}
//修改产品信息
function openProductUpdateDialog(){
	var selectedRows=$("#dg").datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择一条要编辑的数据！");
		return;
	}
	var row=selectedRows[0];
	$("#dlg").dialog("open").dialog("setTitle","编辑茶叶资料");
	$("#product_brand").val(row.product_brand);
	$("#product_name").val(row.product_name);
	$("#product_sxwx").val(row.product_sxwx);
	$("#product_date").datebox("setValue",row.product_date);
	$("#product_weight").val(row.product_weight);
	$("#product_series").combobox("setValue",row.product_series);
	$("#product_package").combobox("setValue",row.product_package);
	$("#mall_price").val(row.mall_price);
	$("#cost_price").val(row.cost_price);
	$("#product_num").val(row.product_num);
	$("#product_image").val(row.product_image);
	$("#shifoutuiguang").val(row.shifoutuiguang);
	url="admin/TeaAction!save.action?product_id="+row.product_id;
}
//查询符合条件的商品信息
function searchTea(){
	$('#dg').datagrid('load',{
		s_product_name:$('#s_product_name').val(),
		s_minDate:$('#s_minDate').datebox("getValue"),
		s_maxDate:$('#s_maxDate').datebox("getValue"),
		s_product_package:$('#s_product_package').combobox("getValue"),
		s_product_series:$('#s_product_series').combobox("getValue")
	});
}
//倒出excel数据
function exportTea(){
	$('#export').form("submit",{
		url:"admin/TeaAction!exportTea.action"
	})
	
}
//导入数据
function openUploadFileDialog(){
	$("#dlg3").dialog('open').dialog('setTitle','批量导入数据');
}
//弹出报表框
function openChartDialog(){
	$("#dlg5").dialog('open').dialog('setTitle','商品报表');
}
//下载excel表格模板
function downloadExcel(){
	window.open('${pageContext.request.contextPath}/excel/product.xls');
}
//上传excel表格模板
function uploadFile(){
	$("#uploadForm").form("submit",{
		success:function(result){
			var result=eval('('+result+')');
			if(result.errorMsg){
				$.messager.alert("系统提示",result.errorMsg);
			}else{
				$.messager.alert("系统提示","上传成功");
				$("#dlg3").dialog("close");
				$("#dg").datagrid("reload");
			}
		}
	});
}
//uploadfile上传excel表格模板
$(document).ready(function(){
			 $('#file_upload').uploadify({
				 	'auto'    :  true,	
				 	'buttonText' : '选中文件',
			        'swf'      : 'uploadify.swf',
			        'uploader' : 'admin/TeaAction!updateFile.action',
			        'onUploadSuccess' : function(file, data, response) {
			        	$.messager.alert("系统提示","上传成功");
			        	$("#dlg3").dialog("close");
						$("#dg").datagrid("reload");
				        }
				    });
		})
</script>
<body style="margin:5px">
<table id="dg" title="商品信息"  class="easyui-datagrid" fitColumns="true" fit="true" toolbar="#tb"
data-options="rownumbers:true,pagination:true,url:'admin/TeaAction!listTeasByPage.action',method:'get'">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>		
				<th data-options="field:'product_id',width:60,align:'left'">商品编号</th>
				<th data-options="field:'product_brand',width:60,align:'left'">茶叶品牌</th>
				<th data-options="field:'product_name',width:60,align:'left'">茶叶名称</th>
				<th data-options="field:'product_sxwx',width:60,align:'left'">色香味形</th>
				<th data-options="field:'product_date',width:80,align:'left'">生产时间</th>
				<th data-options="field:'product_weight',width:60,align:'left'">茶叶重量</th>
				<th data-options="field:'product_series',width:80,align:'left'">茶叶系列</th>
				<th data-options="field:'product_package',width:60,align:'left'">包装盒类型</th>
				<th data-options="field:'mall_price',width:60,align:'left'">市场价格</th>
				<th data-options="field:'cost_price',width:60,align:'left'">成本价格</th>
				<th data-options="field:'product_num',width:60,align:'left'">库存数量</th>
				<th data-options="field:'product_image',width:60,align:'left'">图片路径</th>
				<th data-options="field:'shifoutuiguang',width:60,align:'left'">是否推广</th>
			</tr>
		</thead>
	</table>
	<!-- 搜索控件 -->
	<div id="tb">
		<div>
			<a href="javascript:openProductAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openProductUpdateDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteTea()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			<a href="javascript:exportTea()" class="easyui-linkbutton" iconCls="icon-redo" plain="true">导出Execl</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="openUploadFileDialog()">导入数据</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-sum" plain="true"onclick="openChartDialog()">报表统计</a>
		</div>
		<div>
		<form id="export" method="post">
		&nbsp;商品名称：&nbsp;<input type="text" name="s_product_name" id="s_product_name" size="10"/>
		&nbsp;生产日期：&nbsp;<input class="easyui-datebox" name="s_minDate" id="s_minDate" editable="false" size="15"/>-><input class="easyui-datebox" name="s_maxDate" id="s_maxDate" editable="false" size="15"/>
		&nbsp;包装&nbsp;
		<select class="easyui-combobox" id="s_product_package" name="s_product_package" editable="false" style="width: 120px" panelHeight="auto">
		    <option value="">请选择...</option>
			<option value="生态铁罐">生态铁罐</option>
			<option value="保鲜内袋">保鲜内袋</option>

		</select> 
		&nbsp;系列：&nbsp;<select class="easyui-combobox" id="s_product_series" name="s_product_series" editable="false" style="width: 155px"  panelHeight="auto">
		    <option value="">请选择...</option>
			<option value="2015雨前新茶">2015雨前新茶</option>
			<option value="2016雨前新茶">2016雨前新茶</option>
			<option value="2017雨前新茶">2017雨前新茶</option>
			<option value="2018雨前新茶">2018雨前新茶</option>
		</select>
		<a href="javascript:searchTea()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</form>
		</div>
	</div>
	
	<!-- 添加和编辑框 -->
	<div id="dlg" class="easyui-dialog" style="width: 400px;height: 500px;padding: 10px 20px;position:relative;z-index: 9999" closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>		
					<td>茶叶品牌：</td>
					<td><input type="text" name="tea.product_brand" id="product_brand" class="easyui-validatebox" required="true"style="width: 155px"/></td>
				</tr>
				<tr>		
					<td>茶叶名称：</td>
					<td><input type="text" name="tea.product_name" id="product_name" class="easyui-validatebox" required="true"style="width: 155px"/></td>
				</tr>
				<tr>
					<td valign="top">色香味形：</td>
					<td colspan="4"><input type="text" name="tea.product_sxwx" id="product_sxwx" class="easyui-validatebox" required="true"style="width: 155px"/></td>
				</tr>
				<tr>
					<td>选择日期：</td>
					<td><input class="easyui-datebox" name="tea.product_date" id="product_date" required="true" editable="false" /></td>
				</tr>
				<tr>		
					<td>茶叶重量：</td>
					<td><input type="text" name="tea.product_weight" id="product_weight" class="easyui-validatebox" required="true"style="width: 155px"/></td>
				</tr>
				<tr>
				<td>茶叶系列：</td>
				<td>
				<select class="easyui-combobox" id="product_series" name="tea.product_series" editable="false" panelHeight="auto" style="width: 155px">
			    <option value="">请选择...</option>
				<option value="2015雨前新茶">2015雨前新茶</option>
				<option value="2016雨前新茶">2016雨前新茶</option>
				<option value="2017雨前新茶">2017雨前新茶</option>
				<option value="2018雨前新茶">2018雨前新茶</option>
				</select>
				</td>
				</tr>				
				<tr>
				<td>包装类型：</td>
				<td>
				<select class="easyui-combobox" id="product_package" name="tea.product_package" editable="false" panelHeight="auto" style="width: 155px">
			    <option value="">请选择...</option>
				<option value="生态铁罐">生态铁罐</option>
				<option value="保鲜内袋">保鲜内袋</option>
				</select>
				</td>
				</tr>	
				<tr>		
					<td>市场价格：</td>
					<td><input type="text" name="tea.mall_price" id="mall_price" class="easyui-validatebox" required="true"style="width: 155px"/></td>
				</tr>
				<tr>		
					<td>成本价格：</td>
					<td><input type="text" name="tea.cost_price" id="cost_price" class="easyui-validatebox" required="true"style="width: 155px"/></td>
				</tr>
				<tr>		
					<td>库存数量：</td>
					<td><input type="text" name="tea.product_num" id="product_num" class="easyui-validatebox" required="true"style="width: 155px"/></td>
				</tr>
				<tr>		
					<td>图片路径：</td>
					
					<td><input type="text" name="tea.product_image" id="product_image" class="easyui-validatebox" required="true"style="width: 155px"/></td>
					
				</tr>
				<tr>		
					<td>是否推广：</td>
					<td><input type="text" name="tea.shifoutuiguang" id="shifoutuiguang" class="easyui-validatebox" required="true"style="width: 155px"/></td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<!-- 报表框 -->
	<div id="dlg5" class="easyui-dialog" style="width:700px;height:300px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons3">
        <div style="margin:10px 0;"></div>
		<div style="padding:5px">
		<a href="javascript:reload()" class="easyui-linkbutton" iconCls="icon-reload">刷新</a>
		</div>
		<div style="margin:10px 10;">
		<img id="chart" alt="" src="./jfreechart/ViewResultAction"></div>
	</div>
	
	<!-- 导入数据框 -->
	<div id="dlg3" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons3">
        <form id="uploadForm" action="" method="post" enctype="multipart/form-data">
        	<table>
        		<tr>
        			<td>下载文件：</td>
        			<td><a href="javascript:void(0)" class="easyui-linkbutton"  onclick="downloadExcel()">下载模版</a></td>
        		</tr>
        		<tr>
        			<td>上传文件：</td>
        			<td><input id = "file_upload" type="file" name="file_upload"></td>
        		</tr>
        	</table>
        </form>
	</div>
	
	
	<div id="dlg-buttons">
		<a href="javascript:addproduct()" class="easyui-linkbutton" iconCls="icon-ok">添加</a>
		<a href="javascript:closeAddDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
	</div>
</body>
<script>
		(function($){
			function pagerFilter(data){
				if ($.isArray(data)){	// is array
					data = {
						total: data.length,
						rows: data
					}
				}
				var target = this;
				var dg = $(target);
				var state = dg.data('datagrid');
				var opts = dg.datagrid('options');
				if (!state.allRows){
					state.allRows = (data.rows);
				}
				if (!opts.remoteSort && opts.sortName){
					var names = opts.sortName.split(',');
					var orders = opts.sortOrder.split(',');
					state.allRows.sort(function(r1,r2){
						var r = 0;
						for(var i=0; i<names.length; i++){
							var sn = names[i];
							var so = orders[i];
							var col = $(target).datagrid('getColumnOption', sn);
							var sortFunc = col.sorter || function(a,b){
								return a==b ? 0 : (a>b?1:-1);
							};
							r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
							if (r != 0){
								return r;
							}
						}
						return r;
					});
				}
				var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
				var end = start + parseInt(opts.pageSize);
				data.rows = state.allRows.slice(start, end);
				return data;
			}

			var loadDataMethod = $.fn.datagrid.methods.loadData;
			var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
			$.extend($.fn.datagrid.methods, {
				clientPaging: function(jq){
					return jq.each(function(){
						var dg = $(this);
                        var state = dg.data('datagrid');
                        var opts = state.options;
                        opts.loadFilter = pagerFilter;
                        var onBeforeLoad = opts.onBeforeLoad;
                        opts.onBeforeLoad = function(param){
                            state.allRows = null;
                            return onBeforeLoad.call(this, param);
                        }
                        var pager = dg.datagrid('getPager');
						pager.pagination({
							onSelectPage:function(pageNum, pageSize){
								opts.pageNumber = pageNum;
								opts.pageSize = pageSize;
								pager.pagination('refresh',{
									pageNumber:pageNum,
									pageSize:pageSize
								});
								dg.datagrid('loadData',state.allRows);
							}
						});
                        $(this).datagrid('loadData', state.data);
                        if (opts.url){
                        	$(this).datagrid('reload');
                        }
					});
				},
                loadData: function(jq, data){
                    jq.each(function(){
                        $(this).data('datagrid').allRows = null;
                    });
                    return loadDataMethod.call($.fn.datagrid.methods, jq, data);
                },
                deleteRow: function(jq, index){
                	return jq.each(function(){
                		var row = $(this).datagrid('getRows')[index];
                		deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
                		var state = $(this).data('datagrid');
                		if (state.options.loadFilter == pagerFilter){
                			for(var i=0; i<state.allRows.length; i++){
                				if (state.allRows[i] == row){
                					state.allRows.splice(i,1);
                					break;
                				}
                			}
                			$(this).datagrid('loadData', state.allRows);
                		}
                	});
                },
                getAllRows: function(jq){
                	return jq.data('datagrid').allRows;
                }
			})
		})(jQuery);

		
		
		$(function(){
			$('#dg').datagrid({data:getData()}).datagrid('clientPaging');
		});
	</script>

</html>