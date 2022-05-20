<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html class="x-admin-sm">
    
<head>
    <meta charset="UTF-8">
    <title>查看审核记录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
    <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>
<body>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
                <div class="layui-card">
                
                	<div class="layui-card-body" style="padding-top: 20px;padding-bottom: 20px">
						<label class="layui-form-label">借款编号</label>
						<div class="layui-input-inline">
							<input id="contractId" name="contractId" type="text" value="${contractId}" class="layui-input" disabled="">
						</div>
					</div>
					
                    <div class="layui-card-body">
                        <table class="layui-table" id="loanerList" lay-filter=loanerTable></table>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
    </body>


<script>
	var contractId;

	    layui.use(['layer', 'form','table'], function() {
   	        var  $ = layui.jquery, layer=layui.layer, 
   	        	table=layui.table,form=layui.form,util=layui.util;
   	
   			var conIdValue = document.getElementById("contractId").value;

   	        table.render({
   	            elem:'#loanerList',	
   	            url:'<%=basePath%>pawn/getReviewRecordList',
   	            page:true,
   	            cols:[[
	        		{field:'rStatus',align:'center', fixed: true, title:'审核结果',width: '10%'},
   	                {field:'rComments',title:'审核说明', align:'center'},
	       			{field:'updateTime',sort: true,align:'center', fixed: 'right',width: '15%' ,title:'审核时间',
   	                	templet:'<div>{{ layui.util.toDateString(d.updateTime) }}</div>'	
	       			
	       			},
   	            ]],
   	            where: {contractId: conIdValue}
   	        });
	    });
	    	                

</script>

</html>