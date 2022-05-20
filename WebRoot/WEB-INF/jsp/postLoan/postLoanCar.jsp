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
        <title>汽车状态更新</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="<%=basePath%>css/font.css">
        <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
        <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
        <script src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
    </head>
    <body>
        <div class="layui-fluid" id="topDiv">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12" >
                
                
                <input id="statusFlag" style="display:none" type="text" name="statusFlag" autocomplete="off" class="layui-input">
                <input id="viewFlag" style="display:none" type="text" name="viewFlag" autocomplete="off" class="layui-input">
                
                <div class="layui-card" style="padding-top: 10px;padding-bottom: 20px">
                
                <form class="layui-form" action="#" role="form" autocomplete="off" lay-filter="pcForm">
                   
                   
                <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
			  		<legend>借款概述</legend>
				</fieldset>
                   
                <div class="layui-form-item">
					<input style="display:none" id="contractId" name="contractId" type="text" value="${contractId}"  class="layui-input" disabled="">
					<div id="contractNumDiv" class="layui-inline">
						<label class="layui-form-label">借款编号</label>
						<div class="layui-input-inline">
							<input id="contractNum" name="contractNum" type="text" value="${contractNum}"  class="layui-input" disabled="">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">典当物类型</label>
						<div class="layui-input-inline">
							<input id="productType" name="productType" type="text" value="${productType}" class="layui-input" disabled="">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">借款成数(%)</label>
						<div class="layui-input-inline">
							<input disabled type="text" name="percentage" id="percentage" lay-verify="required"  autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				
                 <div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">借款金额(&yen;)</label>
						<div class="layui-input-inline">
							<input disabled id="balance" type="text" name="balance"  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-inline">
						<label class="layui-form-label">借款月息(%)</label>
						<div class="layui-input-inline">
							<input disabled type="text" name="interest" id="interest" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">还款方式</label>
					    <div class="layui-input-inline">
							<input id="repayMethod" type="text" name="repayMethod" value="${contract.repayMethod}" class="layui-input" disabled="">
					    </div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div id="beginDateDiv" class="layui-inline">
						<label class="layui-form-label">起始日期</label>
						<div class="layui-input-inline">
							<input id="beginDay" disabled="" name="beginDay" type="text" class="layui-input" lay-verify="required">
						</div>
				    </div>
				
					<div class="layui-inline" id="termDiv" >
						<label class="layui-form-label">借款期限(月)</label>
						<div class="layui-input-inline">
							<input id="term" type="text" disabled="" name="term"  lay-verify="required" onblur="termOblur(this)" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div id="endDateDiv" class="layui-inline" >
						<label class="layui-form-label">结束日期</label>
						<div class="layui-input-inline">
						<input id="endDateInput" name="endDateInput" disabled="" type="text" class="layui-input" lay-verify="required" placeholder="yyyy-MM-dd" disabled="">
						</div>
				    </div>
				</div>
				
				<div class="layui-form-item" id="repayDiv" >
					<div id="grossRepaymentDiv" class="layui-inline" >
						<label class="layui-form-label">还款总额(&yen;)</label>
						<div class="layui-input-inline">
							<input id="grossRepayment" disabled="" type="text" name="grossRepayment" autocomplete="off" lay-verify="required" class="layui-input" disabled="">
						</div>
					</div>
					
					<div id="grossInterestDiv" class="layui-inline" >
						<label class="layui-form-label">利息总额(&yen;)</label>
						<div class="layui-input-inline">
							<input id="grossInterest" disabled="" type="text" name="grossInterest" lay-verify="required"  autocomplete="off" class="layui-input" disabled="">
						</div>
					</div>
					
					<div id="viewCalculateRepayDiv" class="layui-inline">
						<label class="layui-form-label"></label>
						<div class="layui-input-inline">
							<button id="viewCalculateRepay" type="button" class="layui-btn layui-btn-warm">查看还款计划</button>						
						</div>
					</div>
					
				</div>
                
                <div id="loanDiv">
	                <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
				  		<legend>借款人信息</legend>
					
		                <div class="layui-card-body">
		                	<table class="layui-table" id="loanerTable" lay-filter="loanerFilter"></table>
		                </div>
		             </fieldset>
	             </div> 
	             
			           <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
				  			<legend>抵押车信息</legend>
						</fieldset>
						<div class="layui-card-body">
							<table class="layui-table" id="mortgageProperty" lay-filter="propertyFilter"></table>
						</div>
                
		                <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
				  			<legend>其它信息</legend>
						</fieldset>   
	                    <div class="layui-form-item">
						    <label class="layui-form-label">还款来源</label>
						    <div class="layui-input-block">
						      <input disabled type="text" name="repaymentSource" id="repaymentSource"  placeholder="请输入100字以内" lay-verify="required" maxlength="150" class="layui-input lafite_width_60" >
						    </div>
						</div>
    				
	                    <div class="layui-form-item layui-form-text">
	                        <label class="layui-form-label">说明</label>
	                        <div class="layui-input-block lafite_width_60">
	                            <textarea id="comments" name="comments" placeholder="经营情况、借款用途、收入来源" lay-verify="required" maxlength="1500" class="layui-textarea lafite_textarea" disabled></textarea>
	                        </div>
	                    </div>
	                    
                    	<div id="imagesDiv"  style=" margin-left: 5%; margin-right: 5%">
	                		<table class="layui-table" id="imagesNames" lay-filter="imagesFilter"></table>
	                	</div>
                    
                    </form>
                    
                    </div>
                    <!--  审核模块          审核模块  -->
                    <div class="layui-card" id="reviewDiv" name="reviewDiv" style="padding-top:10px; padding-bottom: 20px; background:white">
	                    <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
		  					<legend>审核信息</legend>
						</fieldset>   
	                    
	                    <form class="layui-form" action="" lay-filter="rewForm">
						    <div class="layui-form-item layui-form-text">
							    <label class="layui-form-label">审核说明</label>
							    <div class="layui-input-block lafite_width_60">
							      <textarea id="rComments" name="rComments" lay-verify="required" placeholder="请输入审核内容" class="layui-textarea" disabled></textarea>
							    </div>
							</div>
							
							<div class="layui-form-item" pane="">
							    <!--<label class="layui-form-label">审核结果</label>
							     <div class="layui-input-block layui-inline">
							      <input type="radio" name="rStatus" value="审核不通过" title="审核不通过" checked="" >
							      <input type="radio" name="rStatus" value="审核通过" title="审核通过" >
							    </div> -->
							    
							    <div class="layui-form-item  layui-inline" id="reviewRecordDiv" style="float:right;margin-right:10%; padding-bottom: 20px;">
			                    	<button id="reviewRecordBtn" lay-submit="" class="layui-btn" lay-filter="reviewRecord" >查看审核记录</button>
			                	</div>
							</div>
	                    </form>    
                    </div>
                    
                    <!--  贷后状态更新模块          贷后状态更新模块  -->
                    <div id="reviewDiv" name="reviewDiv" style="padding-top:10px; padding-bottom: 20px; background:white">
	                    <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
		  					<legend>状态更新</legend>
						</fieldset>   
	                    
	                    <div id="postLoanTableDiv" style="padding: 10px 15px">
		                    <table class="layui-table" id="postLoanRecordTable" lay-filter=""></table>
		                </div>
						
	                    <form class="layui-form" action="" lay-filter="rewForm">
	                    	<div class="layui-form-item">
		                    	<div class="layui-inline">
								    <label class="layui-form-label">目前状态</label>
								    <div class="layui-input-block">
								      <input type="text" name="beforeStatus" id="beforeStatus" class="layui-input" disabled >
								    </div>
								</div>
							</div>
	                    
						    <div id="updateDiv1" class="layui-form-item layui-form-text">
							    <label class="layui-form-label">更新说明</label>
							    <div class="layui-input-block lafite_width_60">
							      <textarea id="plComments" name="plComments" lay-verify="required" placeholder="请输入更新说明" class="layui-textarea"></textarea>
							    </div>
							</div>
							
							
							<div id="updateDiv2" class="layui-form-item" pane="">
							    <!-- <div class="layui-inline" >
								    <label class="layui-form-label">更新原因</label>
									    <div class="layui-input-block layui-input-inline">
									      <select name="updateReason" id="updateReason" lay-verify="required" lay-filter="aihao">
									      		<option value=""></option>
									      </select>
									    </div>
								</div> -->
								<div>
									<div style="padding-left:60px;">
										  <button class="layui-btn layui-inline" id="demo100" >
										  	更新原因
										  	<i class="layui-icon layui-icon-down layui-font-12"></i>
										  </button>
										  <div class="layui-inline"  style="padding-left:-60px;">
										  		<input id="updateReason" type="text" name="updateReason" autocomplete="off" lay-verify="required" class="layui-input" disabled="">
										  </div>
									 </div>
								</div>
							    
							    <div class="layui-form-item" id="subDiv" style="margin-left: 30%;">
			                        <div class="layui-input-block">
			                            <button id="update" lay-submit="" lay-filter="updateStatus" class="lafite-layui-btn2 " >更新</button>
			                        </div>
			                    </div>
			                    
							</div>
	                    </form>    
                    </div>
                    
                </div>
            </div>
        </div>
    </body>
<!--     <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&amp;ak=f5bKSDjUlnYT4r664aQrQZB8&s=1"></script>
 -->    
    <script type="text/html" id="barDemo">
    	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>	
	</script>
<!--     	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a> -->
    
    <script>
    
    layui.use('form', function(){
    	  var form = layui.form;
    	  form.render();  
    	  });
    
    //留给弹出层用
    var faContractId;
    
    var parentEdCon = parent.editEdCon;
    
	layui.use(['form', 'upload','table','layer','element'],function() {
	    var $ = layui.jquery
	    	,upload = layui.upload
	    	,form = layui.form 
	    	,table = layui.table
	    	, layer = layui.layer
	    	, element = layui.element;
	    

		var conIdDoc = document.getElementById("contractId");
		
		var uId='${sessionScope.user.uid}';
		
	 	if(conIdDoc.value){
	 		faContractId = conIdDoc.value;
	 		//已经有了合同id
	 		$.ajax({
			       url:'<%=basePath%>admin/getPropertyPage',
			       type:'post',
			       data:{"contractId":conIdDoc.value},
			       dataType:"json",
			       success:function(result){
			    	   var dataForm = result.data;
 			    	   
 			    	  $('#statusFlag').val(dataForm.status);
 			    	  $('#beforeStatus').val(dataForm.status);
 			    	 
 			    	 //初始化状态更新下拉框
 			    	 initStatusSelect();
 			    	   //var statusFlag = document.getElementById("statusFlag");
	//					alert(document.getElementById("statusFlag").value);
 			    	   
			    	   form.val('pcForm', {
                           "balance": dataForm.balance,
                           "interest": dataForm.interest,
                           "contractNum": dataForm.contractNum,
                           "term": dataForm.term,
                           "percentage": dataForm.percentage,
                           "repaymentSource": dataForm.repaymentSource,
                           "comments": dataForm.comments,
                           "productType": dataForm.productType == "A0001" ? "房产":"汽车",
                           "beginDay": dataForm.beginDay,
                           "endDateInput": dataForm.endDateInput,
                           "grossRepayment": dataForm.grossRepayment,
                           "grossInterest": dataForm.grossInterest,
                           "repayMethod": dataForm.repayMethod
                       }); 
			    	   
			    	   form.val('rewForm', {
                           "rComments": dataForm.rComments ,
                           "rStatus": dataForm.rStatus
                       }); 			    	   
			       }
			 });
		}
	 	
	 	
	 	var viewFlag = ${viewFlag};
	 	if(1 === viewFlag){
	 		document.getElementById("subDiv").style.display="none";
	 		document.getElementById("updateDiv1").style.display="none";
	 		document.getElementById("updateDiv2").style.display="none";
	 	}
	    
	    //借款人表渲染
	    window.renderLoaner('#loanerTable','loaner','loanDiv');
	    
	    //渲染已上传的文件列表
	    renderImagesList();
	    
	  	//查看还款计划
		$(document).on('click','#viewCalculateRepay',function(){
			calculateRepayPlan();
		});
   		
	    //抵押房表渲染
	    window.renderProperty('#mortgageProperty','mort','mortgageDiv');
	    
	    //渲染状态更新记录
	    window.renderPostLoan();
	    
	    
  	      //审核记录 按钮是否展示
            $.ajax({
				type:'get',
				url:"<%=basePath%>pawn/getReviewRecordList",
				contentType:'application/json',
				data:{
					"contractId":conIdDoc.value
				},
				success: function (result) {
					if(result.count < 2){
						document.getElementById("reviewRecordDiv").style.display="none";
					}
				}
			});
            
            //查看审核记录按钮
            form.on('submit(reviewRecord)', function(){ 
                 layer.open({
	                   type:2,
	                   title:'审核记录',
	                   content:'<%=basePath%>pawn/toReviewRecord?contractId=' + conIdDoc.value,
	                   area:['100%','100%']
	               });
            	
            	return false;
            });
            
            //更新提交按钮
            form.on('submit(updateStatus)', function(data){ 
            	var indexLoad = layer.load(1); 
            	
            	data = data.field;
            	data['contractId']=conIdDoc.value;
            	data['uId']=uId;
            	 
            	//alert(JSON.stringify(data));
            	//return false;
 
                 $.ajax({
					type:'post',
					url:"<%=basePath%>user/updateStatus",
					contentType:'application/json',
					data:JSON.stringify(data),
					success: function (result) {
						layer.msg("更新成功！", {icon: 5,time:1000})
		            	layer.close(indexLoad);
						parent.layui.$('.layui-this i').click();
						//location.reload();
						
						//element.init();
						//parent.layui.admin.events.closeThisTabs();
		            	//$("#subDiv").hide();
					},error:function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest.status); 
               			console.log(XMLHttpRequest.readyState); 
               			console.log(textStatus); 
               			console.log(XMLHttpRequest.responseText); 
						alert("修改失败");
           		 }
				}); 
                return false;
            });
            
 			function renderImagesList(){
				    var conNum = document.getElementById("contractId");
				    
			        table.render({
			            elem: '#imagesNames'
			            ,url: '<%=basePath%>ticket/reRenderFileList'
			            ,where:{contractId: conNum.value}
			            ,cols:[[
			                {field:'fileName', sort: true,title:'文件名'},
			        		{field:'fileStatus',width: '10%', title:'上传进度',align:'center'},
			                {fixed:'right', width: '20%', align:'center', toolbar: '#barDemo',title:'操作'}
			            ]]
			            ,done: function(res, curr, count){
			            	if(count < 1){
			            	    document.getElementById("imagesDiv").style.display="none";
			            	}
			            } 
			        });

			        // 监听工具条
			        table.on('tool(imagesFilter)', function(obj){
			            var loanerLine = obj.data;
			        	
			            if(obj.event === 'detail'){
			            	var urlStr = '<%=basePath%>ticket/viewImage?contractId=' + conNum.value + '&fileName=' + loanerLine.fileName ;
				        	  //alert(urlStr);
				        	  
				        	window.open(urlStr, "newwindow",'height=600,width=900');
				        	  
							return false;
			            }
			        });	        
			}
	
	});
   
	//初始化状态更新下拉框
	function initStatusSelect(){
		
		layui.use(['element','dropdown','util'],function() {
		    var $ = layui.jquery
		    	, element = layui.element
		    	,dropdown = layui.dropdown
		    	,util = layui.util;
		    
		  //审核状态选择框
	        var beforeStatus = document.getElementById("beforeStatus").value;
	        
		    if(beforeStatus === '待放款'){
		    	dropdown.render({
				      elem: '#demo100'
				      ,data: [{
		            	title: '已放款'
	          	        ,id: 101
		              }]      
				      ,click: function(item,othis){
				        document.getElementById("updateReason").value=item.title;
				      }
				});
		    }else if(beforeStatus === '待还款'){
		    	dropdown.render({
				      elem: '#demo100'
				      ,data: [{
			            	title: '按计划还款',
			            	id: 102
		              },{
		            	  title: '已赎回',
		            	  id: 103
		              },{
		            	  title: '已逾期',
		            	  id: 104 ,
		            	  type: 'parent',
		            	  child: [
		            	     {title: 'M1'
		                      ,id: 10401},
		            	     {title: 'M2'
		                      ,id: 10402},
		            	     {title: 'M3'
		                      ,id: 10403},
		            	     {title: 'M3+'
		                      ,id: 10404}]
		              }]
				      ,click: function(item,othis){
				        document.getElementById("updateReason").value=item.title;
				      }
				});
		    }else if(beforeStatus == 'M1' 
		    		|| beforeStatus == 'M2' 
		    		|| beforeStatus == 'M3' 
		    		|| beforeStatus == 'M3+'){
		    	dropdown.render({
				      elem: '#demo100'
				      ,data: [{
			            	title: '逾期处理完成'
		          	        ,id: 105
		              },{
		            		title: '无偿还能力'
			          	    ,id: 106
		              }]      
				      ,click: function(item,othis){
				        document.getElementById("updateReason").value=item.title;
				      }
		    	});
		    }
		});
	}


   
	function renderLoaner(tableId,loanType,divName){
	    var conNum = document.getElementById("contractId");
	    //alert(conNum.value);
	    
	    layui.use(['table','layer'],function() {
		    var $ = layui.jquery,table = layui.table,layer=layui.layer;
		    
	        var abcTable = table.render({
	            elem:tableId
	            ,url:'<%=basePath%>pawn/getApproveLoaner'
	            ,where:{contractId: conNum.value,
	            	   loanerType: loanType}
	            ,cols:[[
	                {field:'name', sort: true, fixed: true,title:'姓名'},
	        		{field:'age', title:'年龄'},
	       			{field:'citizenID', title:'身份证号码'},
	        		{field:'loanerId',title:'主键',hide:true},
	                {fixed:'right', width:150, align:'center', toolbar: '#barDemo',title:'操作'}
	            ]]
	            ,done: function(res, curr, count){
	                if(count === 0){
	            	    document.getElementById(divName).style.display="none";
	                }
	            }
	        });

	        // 借款人监听工具条
	        table.on('tool(loanerFilter)', function(obj){
	            var loanerLine = obj.data;
	            if(obj.event === 'detail'){
	               layer.open({
	                   type:2,
	                   title:'借款人详情',
	                   content:'<%=basePath%>pawn/toLoaner',
	                   area:['80%','100%'],
	                   success: function (layero, index) {
	                   		var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
	                   		body.find("#loanerSub").css('display','none');
	                   		//body.find("#loanerRe").css('display','none');
	                   		
	    	               // layer.alert('编辑行：<br>'+ JSON.stringify(data));

	    	                // 循环所有键
	    	                for(var key in loanerLine) {
	    	                	if("gender"==key){
	    	                		body.find("input[name=gender][value=m]").attr("checked", loanerLine[key] == "m" ? true : false);
	    	                        body.find("input[name=gender][value=f]").attr("checked", loanerLine[key] == "f" ? true : false);
	    	                	}else{
		    	                	body.find("#"+key).val(loanerLine[key]);
	    	                	}
	    	                }
	                   		
	                   		/* body.find("#name").text(11111111111111);
	                   		var j_find = $(layero).find("iframe").contents().find("#j_find");//获取子页面元素
	      					//使用#id就可以获取到弹出层里面的子元素。前面的写法都是固定的。
	        				j_find.find("#name").text("testtesttest"); */

	            		}
	               })
	        
	            }
	        });	        
	    });
	}
	
	//汽车的渲染
	function renderProperty(tableId,mortgageTypeStr){
	    var conNum = document.getElementById("contractId")
	    //alert(conNum.value);
	    
	    layui.use(['table','layer'],function() {
		    var $ = layui.jquery,table = layui.table,layer=layui.layer;
		    
	        var abcTable = table.render({
	            elem:tableId
	            ,url:'<%=basePath%>pawn/getPropertyByConId'
	            ,where:{contractId: conNum.value,
	            		mortgageType: mortgageTypeStr}
	            ,cols:[[
	                {field:'drivingLicense',fixed: true,title:'行驶证',sort: true},
   	                {field:'brand', title:'品牌'},
   	                {field:'model', title:'型号'},
   	                {field:'color', title:'颜色'},
   	                {field:'evaluationPrice', title:'评估价',sort: true},
	                {fixed:'right', width:150, align:'center', toolbar: '#barDemo',title:'操作'}
	            ]]
	        });

	        // 房产监听工具条
	        table.on('tool(propertyFilter)', function(obj){
	            var propertyLine = obj.data;
	            if(obj.event === 'detail'){
	               layer.open({
	                   type:2,
	                   title:'抵押车详情',
	                   content:'<%=basePath%>car/toCar?carId='+propertyLine.carId,
	                   area:['80%','80%'],
	                   success: function (layero, index) {
	                   		var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
	                   		body.find("#saveDiv").css('display','none');
	            		}
	               })
	        
	            }
	        });	        
	    });
	}
	
	//贷后更新记录的渲染
	function renderPostLoan(){
	    var conNum = document.getElementById("contractId")
	    //alert(conNum.value);
	    
	    layui.use(['table','layer'],function() {
		    var $ = layui.jquery,table = layui.table,layer=layui.layer;
		    
	        var abcTable = table.render({
	            elem: "#postLoanRecordTable"
	            ,url:'<%=basePath%>pawn/getPostLoanRecord'
	            ,page:true
	            ,limit: 5
	            ,where:{
	            	contractId: conNum.value
	            	}
	            ,cols:[[
	                {field:'beforeStatus',title:' 更新前状态',align:'center'},
	        		{field:'updateReason', title:'更新原因',align:'center'},
	        		{field:'plComments', title:'更新说明',align:'center'},
	        		{field:'plStatus', title:'更新结果',align:'center'},
	       			{field:'updateTime',sort: true,align:'center', fixed: 'right',width: '15%' ,title:'更新时间',
   	                	templet:'<div>{{ layui.util.toDateString(d.updateTime) }}</div>'	
	       			}
	            ]]
	            ,done: function(res, curr, count){
	                if(count === 0){
	            	    document.getElementById("postLoanTableDiv").style.display="none";
	                }
	            }
	        });
	        
	    });
	    
	}
	
	//查看还款计划	
	function calculateRepayPlan(){
    	layui.use(['form','laydate','layer'],function () {
    		var form = layui.form
    			,laydate = layui.laydate
    			,layer = layui.layer; 
    		
	    	var formData = form.val('pcForm');
	    	var checked = checkRepayForm(formData);
	    	
	    	//alert(JSON.stringify(formData));
	    	
	    	if(checked != false){
	    		//var aaa = calculateRepaymentPlan(formData);
		    	
 		    	var dataJson = "balance="+formData.balance
		    					+"&interest="+formData.interest
		    					+"&repayMethod="+formData.repayMethod
		    					+"&beginDay="+formData.beginDay
		    					+"&endDateInput="+formData.endDateInput
		    					+"&term="+formData.term; 
		    	
            	//打开还款计划界面
		    	parent.layer.open({
	                   type:2,
	                   title:'还款计划',
	                   content:'<%=basePath%>user/toCalculatePage?' + dataJson,
	                   area:['70%','100%'],
	            	   cancel: function(index, layero){
	            	   }  
	            });
	    	}
    	});
	}
	
	
    </script>

</html>