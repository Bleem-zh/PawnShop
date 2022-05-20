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
    <title >汽车典当物管理</title>
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
                    
                   <!--  <div class="layui-card-body ">
                        <form class="layui-form layui-col-space5" style="padding-top: 20px">
                            <div class="layui-input-inline">
                                <input type="text" name="balanceSearch" placeholder="请输入借款编号" lay-verify="required" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-input-inline  lafite_search layui-show-xs-block">
                                <button type="submit" class="layui-btn" lay-submit="" lay-filter="sreach">借款编号查询 </button>
                            </div>
                        </form>
                    </div> -->
                    <div class="layui-card-body">
                        <table class="layui-table" id="proData" lay-filter="proTable"></table>
                    </div>
                    <!--<div id="lafite_pages"></div>-->
                </div>
            </div>
        </div>
    </div>
    </body>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>

</script>
<!--     <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a> -->
<!--     <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="tbReflesh" style="position:relative; float:right;"><i class="layui-icon layui-icon-refresh-3"> </i></a>
    <a class="layui-btn  layui-btn-xs " lay-event="addProTab" style="background:#d91715;position:relative; float:right">新增</a> -->
<!-- <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a> -->
<script>

	var uId='${sessionScope.user.uid}';
	var urole='${sessionScope.user.urole}';
	if(!uId){
		alert("登录超时,请重新登录!"); 
	    top.location.href="<%=basePath%>";  
	}

	var aaa = null;
	var editEdCon;
	
    layui.use(['layer', 'form','table','laydate','upload'],function() {
        var  $ = layui.jquery,laydate=layui.laydate, 
        						layer=layui.layer,
        						table=layui.table, 
        						upload=layui.upload,
        						form=layui.form;
        
        table.render({
            elem:'#proData',	
            url: "<%=basePath%>admin/getProList",
            page:true,
            toolbar: '#barDemo',
            defaultToolbar: [{title: '新增' //标题
				              ,layEvent: 'addProTab' //事件名，用于 toolbar 事件中使用
				              ,icon: 'layui-icon-add-1'},
				             {title: '刷新' //标题
			                  ,layEvent: 'tbReflesh' //事件名，用于 toolbar 事件中使用
			                  ,icon: 'layui-icon-refresh-3'},
			                  {title: '删除' //标题
				                  ,layEvent: 'tbDelete' //事件名，用于 toolbar 事件中使用 
				                  ,icon: 'layui-icon-delete'},
                  'filter', 'exports', 'print'],
            limit:10,
            cols:[[
				{fixed: 'left',type: 'radio',title:'选择',align:'center'},	
                //{field:'contractId', sort: true, fixed: true,title:'借款编号',align:'center'},
                {field:'balance', sort: true,title:'借款金额', align:'center'},
                {field:'interest',title:'借款月息', align:'center'},
                {field:'term',title:'借款期限', align:'center'},
                {field:'percentage',title:'借款成数', align:'center'},
                {field:'status',title:'状态', align:'center'},
            ]],
            where:{productType: "A0002"}
        });
        
        //监听工具条
       table.on('toolbar(proTable)', function(obj){
    	   if(obj.event === 'tbReflesh'){
  	        	table.reload("proData",{
  	 				where: {
  	 					productType: "A0002"
  	 					}
  	 				,page: {
  	 					curr:1
  	 				}
  	 			});
  	        	
  	        	return false;
           }else if(obj.event === 'addProTab'){
        	   parent.xadmin.add_tab('新增汽车典当物','<%=basePath%>pawn/toAddProperty?productType=A0002');
           }
    	  
        	
    	   var checkStatus = table.checkStatus("proData"),
			checkData = checkStatus.data; //获取选中的数据
			
			if(checkData.length === 0){
	   			layer.msg("请选中一行数据", {icon: 5,time:1000}); 
				return false;
			}    			
				
			editEdCon = checkData[0];
        	
        	//editEdCon = obj.data;
        	
            if(obj.event === 'edit'){
               layer.open({
                   type:2,
                   title:'编辑汽车典当物',
                   content: '<%=basePath%>pawn/toAddProperty?contractId=' + editEdCon.contractId,
                   area:['100%','100%'],
                   success: function (layero, index) {
						var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
						var editEd = obj.data;
						editEdCon = "";
            	   },end: function(){
	           			table.reload('proData',{
	        				where: {productType: "A0002"}
	        			});
         			}
               });
        
            }  else if(obj.event === 'tbDelete'){
                layer.confirm('真的删除行么', function(index){
                	$.ajax({
	     			       url:'<%=basePath%>pawn/delContract',
	     			       type:'post',
	     			       data:{"contractId":editEdCon.contractId},
	     			       dataType:"json",
	     			       success:function(result){
	     			    	   //var dataForm = result.data;
	     			    	  table.reload("proData",{
	     			 				url: '<%=basePath%>admin/getProList'
	     			 				,methods:"GET"
	     			 				,where: {
	     			 					productType: "A0002"
	     			 					}
	     			 				,page: {
	     			 					curr:1
	     			 				}
	     			 			});
	     			    	   
	     			       },error:function(XMLHttpRequest, textStatus, errorThrown){
	   						console.log(XMLHttpRequest.status); 
	               			console.log(XMLHttpRequest.readyState); 
	               			console.log(textStatus); 
	               			console.log(XMLHttpRequest.responseText); 
							alert("修改失败");
	           		 }
     			 	});
                	
                	
                    layer.close(index);
                });
            } 
        });
        
        
     form.on('submit(sreach)', function(data){
 	       /*  layer.alert(JSON.stringify(data.field), {
 	          title: '最终的提交信息'
 	        })
 	        return false; */
 	    	table.reload("proData",{
 				url: '<%=basePath%>admin/getProList'
 				,methods:"GET"
 				,where: {
 					balance: data.field.balanceSearch,
 					productType: "A0002"
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