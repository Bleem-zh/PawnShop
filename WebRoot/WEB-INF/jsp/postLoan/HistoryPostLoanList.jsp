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
        <title>历史借款列表界面</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="<%=basePath%>css/font.css">
        <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
        <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
    </head>
    <body>
        <!-- <div class="x-nav">
            <span class="layui-breadcrumb">
                <a href="">工作台</a>
                <a>
                    <cite>待审核</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
                <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
            </a>
        </div> -->
        <div class="layui-fluid" >
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card" >
                            <form class="layui-form layui-col-space5" style="padding: 20px">
                                <div class="layui-input-inline layui-show-xs-block" >
                                    <input type="text" name="contractIdSerach" placeholder="借款编号查询" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-input-inline lafite_search layui-show-xs-block">
                                    <button class="layui-btn" lay-submit="" lay-filter="sreach">借款编号查询</button>
                                </div>
                            </form>
                        <div class="layui-card-body">
                        	<table class="layui-table" id="postLoanData" lay-filter="demo" ></table>          <!-- 真正的数据表格 -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

<script type="text/html" id="toolBtn">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>

    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="tbReflesh" style="float:right;"><i class="layui-icon layui-icon-refresh-3"></i></a>
</script>

    
<script>
	
	var uId='${sessionScope.user.uid}';
	var urole='${sessionScope.user.urole}';
<%-- 	if(!uId){
		alert("登录超时,请重新登录!"); 
	    top.location.href="<%=basePath%>";  
	}
	 --%>
	//子页面需要的数据
	var editEdCon;
	
	/* 新的js逻辑 */
    layui.use(['layer', 'form','table','laydate','upload'],
    function() {
        var  $ = layui.jquery, laydate=layui.laydate, layer=layui.layer,
                table=layui.table, upload=layui.upload,form=layui.form;
        
        table.render({
            elem:'#postLoanData',
            url:'<%=basePath%>admin/getHistoryPostLoanList',
            page:true,
            toolbar: '#toolBtn',
            defaultToolbar :['filter', 'exports', 'print'],
            cols:[[
  				{fixed: 'left',type: 'radio',title:'选择'},
                {field:'contractNum', sort: true, fixed: true,title:'借款编号'},
                {field:'productType', 
                	title: '典当物',
                	sort: true,
                	templet: function(data){
                		if(data.productType === "A0001"){
                			return "房产";
                		}else if(data.productType === "A0002"){
                			return "汽车";
                		}
                	}
            	},
            	{field:'loanerName', title:'借款人'},
                {field:'balance', title:'借款金额'},
                {field:'interest',title:'借款月息'},
                {field:'term',title:'借款期限'},
                {field:'percentage',title:'借款成数'},
                {field:'status',title:'状态', sort: true},
            ]],
            where:{uId:uId,urole:urole}
        });

        //监听工具条
        table.on('toolbar(demo)', function(obj){
        	if(obj.event === 'tbReflesh'){
   	        	table.reload("postLoanData",{
   	 				where: {
   	 					uId:uId,
   	 					urole:urole
   	 					}
   	 				,page: {
   	 					curr:1
   	 				}
   	 			});
   	        	
   	        	return false;
            }
        	
    		var checkStatus = table.checkStatus("postLoanData"),
    			checkData = checkStatus.data; //获取选中的数据
    			
			if(checkData.length === 0){
        		layer.msg("请选中一行数据", {icon: 5,time:1000}); 
    			return false;
			}    			
    			
    		editEdCon = checkData[0];

            if(obj.event === 'detail'){
                parent.xadmin.add_tab('查看历史借款',"<%=basePath%>admin/toPostLoan?contractId="+ editEdCon.contractId + "&viewFlag=1",'true');
            } else if(obj.event === 'updateStatus'){
            	if(uId === checkData[0].uId){
	                parent.xadmin.add_tab('贷后状态更新',"<%=basePath%>admin/toPostLoan?contractId="+ editEdCon.contractId,'true');
            	}else{
            		layer.msg("只能更新自己的借款状态！", {icon: 5,time:1000})
            	}
            }
        });
        
	     form.on('submit(sreach)', function(data){
	 	       /*  layer.alert(JSON.stringify(data.field), {
	 	          title: '最终的提交信息'
	 	        })
	 	        return false; */
	 	    	table.reload("postLoanData",{
	 				url: '<%=basePath%>admin/getHistoryPostLoanList'
	 				,methods:"GET"
	 				,where: {
	 					contractId: data.field.contractIdSerach,
	 					uId:uId,
	 					urole:urole
	 					}
	 				,page: {
	 					curr:1
	 				}
	 			});
	 	     	return false;
	 	     });
        
    });


</script>

</html>