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
    <title>添加抵押车界面</title>
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

					<div class="layui-cart-header">
						<div class="layui-btn-container ">
	                		<button id="jump" class="layui-btn lafite_main_color"  style="margin-left:20px">新增抵押车</button>
						</div>
					</div>
                
                    <div class="layui-card-body ">
                        <form class="layui-form layui-col-space5">
                            <div class="layui-input-inline  ">
                                <input type="text" name="drivingLicense" lay-verify="required"  class="layui-input">
                            </div>
                            <div class="layui-input-inline  lafite_search layui-show-xs-block">
                                <button type="submit" class="layui-btn" lay-submit="" lay-filter="sreach" >行驶证查询</button>
                            </div>
                        </form>
                    </div>
                    <div class="layui-card-body">
                        <table class="layui-table" id="loanerList" lay-filter="loanerTable"></table>
                    </div>
                    <!-- <div id="lafite_pages"></div> -->
                </div>
                
                
            </div>
        </div>
    </div>
    </body>

<script th:inline="javascript">
	var callbackdata = function () {
		var  $ = layui.jquery,table=layui.table
		
		var checkStatus = table.checkStatus("loanerList"),checkData = checkStatus.data; //获取选中的数据
		
	    var data = {
			data:checkData
	    };
	    return data;
	}
</script>

<script type="text/html" id="barDemo">
    	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>
</script>

<!-- 	{{# if('未提交' == document.getElementById("statusFlag").value || ('审核不通过' == document.getElementById("statusFlag").value && '1' === document.getElementById("viewFlag").value )){  }}		
    	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	{{#  } }}	 -->

<script>
	var contractId;

	initListTb();

    layui.use(['layer','table'], function(){
 	      var $ = layui.jquery, layer = layui.layer,table = layui.table,form=layui.form;
 	      
 	      $(document).on('click','#jump',function(){
 	    	 parent.layer.open({
		            type:2, 	
		            content: '<%=basePath%>car/toCar',   
		            area: ['80%','100%'],
		            title: '新增抵押车',
		            shadeClose:true,
		            end: function(){
		            	initListTb();
		        	}
		       });
		  });
 	      
 	     form.on('submit(sreach)', function(data){
 	       /*  layer.alert(JSON.stringify(data.field), {
 	          title: '最终的提交信息'
 	        })
 	        return false; */
 	    	table.reload("loanerList",{
 				url: '<%=basePath%>car/getCarList'
 				,methods:"GET"
 				,where: {
 					drivingLicense: data.field.drivingLicense
 					}
 				,page: {
 					curr:1
 				}
 			});
 	     	return false;
 	     });
 	      

	        // 借款人监听工具条
	        table.on('tool(loanerTable)', function(obj){
	            var loanerLine = obj.data;
	            var carId = loanerLine.carId;
	            
	           // contentStr += loanerId;
	            //alert(contentStr);
	            
	            if(obj.event === 'edit'){
		            var contentStr = "<%=basePath%>car/toCar?carId="+carId+"&editFlag=1";
		            
	            	parent.layer.open({	
	                   type:2,
	                   title:'编辑抵押车详情',
	                   content: contentStr,
	                   area:['80%','100%'],
	                   end: function(){
			            	initListTb();
			        	}
	               });
	        
	            }
	            
	        });
 	      
 	      
 	 });
    
	function initListTb(){
	    layui.use(['layer', 'form','table'], function() {
   	        var  $ = layui.jquery, 
   	            layer=layui.layer, 
   	            table=layui.table,
   	            form=layui.form;
   	
   	        table.render({
   	            elem:'#loanerList',	
   	            url:'<%=basePath%>car/getCarList',
   	            page:true,
   	            cols:[[
   					{fixed: 'left',type: 'radio',title:'添加'},
   	                {field:'drivingLicense', title:'行驶证',sort: true},
   	                {field:'brand', title:'品牌'},
   	                {field:'model', title:'型号'},
   	                {field:'color', title:'颜色'},
   	                {field:'evaluationPrice', title:'评估价',sort: true},
	                {fixed:'right', width:80, align:'center', toolbar: '#barDemo',title:'操作'},
   	            ]]
   	        });
	    });
	    	                
	}
    

</script>

</html>