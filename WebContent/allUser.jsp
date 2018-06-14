<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base  href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/TeaMallBackStage/">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

</head>
<body style="margin: 0px;padding: 5px;">
	<table id="dg" class="easyui-datagrid"  title="用户列表" fitColumns="true" fit="true" toolbar="#tb" 
			data-options="rownumbers:true,pagination:true,singleSelect:true,collapsible:true,url:'admin/UserAction!listUserByPage.action',method:'get'">
		<thead>

			<tr>
				<th data-options="field:'userid',width:60,align:'left'">用户id</th>
				<th data-options="field:'username',width:60,align:'left'">姓名</th>
				<th data-options="field:'nickname',width:60,align:'left'">昵称</th>
				<th data-options="field:'sex',width:60,align:'left'">性别</th>
				<th data-options="field:'age',width:60,align:'left'">年龄</th>			
				<th data-options="field:'image',width:60,align:'left'">头像</th>
				<th data-options="field:'job',width:60,align:'left'">职业</th>
				<th data-options="field:'jialing',width:60,align:'left'">jialing</th>
				<th data-options="field:'email',width:60,align:'left'">邮箱</th>
				<th data-options="field:'tel',width:60,align:'left'">电话</th>
				<th data-options="field:'jianjie',width:60,align:'left'">简介</th>
			</tr>
		</thead>
	</table>
	
	
	
	<script type="text/javascript">
		$(function(){
			var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
			pager.pagination({
				buttons:[{
					iconCls:'icon-search',
					handler:function(){
						alert('search');
					}
				},{
					iconCls:'icon-add',
					handler:function(){
						$("#w").panel({'title':'编辑车辆信息','width':400,'height':400});
						$("#w").html('<iframe scrolling="auto" frameborder="0"  src="addUser.jsp" style="width:100%;height:100%;"></iframe>');
						$("#w").window("open");
					}
				},{
					iconCls:'icon-edit',
					handler:function(){
						$("#w").panel({'title':'编辑车辆信息','width':400,'height':400});
						$("#w").html('<iframe scrolling="auto" frameborder="0"  src="modUser.jsp" style="width:100%;height:100%;"></iframe>');
						$("#w").window("open");
					}
				}]
			});			
		})
	</script>
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
	

</body>
</html>