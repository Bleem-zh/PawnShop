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
        <title>添加房产典当物</title>
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
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12" >
                
                <input id="statusFlag" style="display:none" type="text" name="statusFlag" autocomplete="off" value="${status}" class="layui-input">
                <input id="viewFlag" style="display:none" type="text" name="viewFlag" value="${viewFlag}" autocomplete="off" class="layui-input">
                
                <div class="layui-card" style="padding-top: 10px;padding-bottom: 20px">
                
                <form class="layui-form" action="#" role="form" autocomplete="off" lay-filter="pcForm">
                
                   
                   
                <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
			  		<legend>借款概述</legend>
				</fieldset>
                   
                <div class="layui-form-item">
					<input style="display:none" id="contractId" name="contractId" type="text" value="${contractId}"  class="layui-input" disabled="">
					<div id="contractNumDiv" class="layui-inline" style="display:none">
						<label class="layui-form-label">借款编号</label>
						<div class="layui-input-inline">
							<input id="contractNum" name="contractNum" type="text" value="${contractNum}"  class="layui-input" disabled="">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">典当物类型</label>
						<div class="layui-input-inline">
							<input id="productType" name="productType" type="text" value="${productType}"  class="layui-input" disabled="">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">借款成数(%)</label>
						<div class="layui-input-inline">
							<input type="text" name="percentage" id="percentage" lay-verify="required"  autocomplete="off" class="layui-input">
						</div>
					</div>
					
				</div>
				
                <div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">借款金额(&yen;)</label>
						<div class="layui-input-inline">
							<input id="balance" type="text" name="balance"  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-inline">
						<label class="layui-form-label">借款月息(%)</label>
						<div class="layui-input-inline">
							<input type="text" name="interest" id="interest" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">还款方式</label>
					    <div class="layui-input-inline">
							<select name="repayMethod" lay-verify="required">
								<option value="" >请选择</option>
								<option value="等额本息" selected="">等额本息</option>
								<option value="等额本金">等额本金</option>
								<option value="先息后本">先息后本</option>
							</select>
					    </div>
					</div>
					
				</div>
				
				<div class="layui-form-item">
					<div id="beginDateDiv" class="layui-inline">
						<label class="layui-form-label">起始日期</label>
						<div class="layui-input-inline">
							<input id="beginDay" name="beginDay" type="text" class="layui-input" lay-verify="required">
						</div>
				    </div>
				
					<div class="layui-inline" id="termDiv" style="display:none">
						<label class="layui-form-label">借款期限(月)</label>
						<div class="layui-input-inline">
							<input id="term" type="text" name="term"  lay-verify="required" onblur="termOblur(this)" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div id="endDateDiv" class="layui-inline" style="display:none">
						<label class="layui-form-label">结束日期</label>
						<div class="layui-input-inline">
						<input id="endDateInput" name="endDateInput" type="text" class="layui-input" lay-verify="required" placeholder="yyyy-MM-dd" disabled="">
						</div>
				    </div>
				</div>
                
				<div class="layui-form-item" id="repayDiv" >
					<div id="grossRepaymentDiv" class="layui-inline" style="display:none">
						<label class="layui-form-label">还款总额(&yen;)</label>
						<div class="layui-input-inline">
							<input id="grossRepayment" type="text" name="grossRepayment" autocomplete="off" lay-verify="required" class="layui-input" disabled="">
						</div>
					</div>
					
					<div id="grossInterestDiv" class="layui-inline" style="display:none">
						<label class="layui-form-label">利息总额(&yen;)</label>
						<div class="layui-input-inline">
							<input id="grossInterest" type="text" name="grossInterest" lay-verify="required"  autocomplete="off" class="layui-input" disabled="">
						</div>
					</div>
					<div id="calculateRepayDiv" class="layui-inline" >
						<label class="layui-form-label"></label>
						<div class="layui-input-inline">
							<button id="calculateRepay" type="button" class="layui-btn layui-btn-warm">计算还款计划</button>						
						</div>
					</div>
					
					<div id="viewCalculateRepayDiv" class="layui-inline" style="display:none">
						<label class="layui-form-label"></label>
						<div class="layui-input-inline">
							<button id="viewCalculateRepay" type="button" class="layui-btn layui-btn-warm">查看还款计划</button>						
						</div>
					</div>
					
				</div>
                
                <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
			  		<legend>借款人信息</legend>
				
	                <div class="layui-card-body">
		                <button type="button" id="addLoaner" class="layui-btn"  style="float:right; margin-right: 5%">添加借款人</button>
	                	<table class="layui-table" id="loanerTable" lay-filter="loanerFilter"></table>
	                </div>
	            </fieldset>
	                
				<fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
		  			<legend>抵押车信息</legend>
				</fieldset>
              		<div class="layui-card-body">
		                <button type="button" id="addMortProperty" class="layui-btn"  style="float:right; margin-right: 5%">添加抵押车</button>
	                	<table class="layui-table" id="mortgageProperty" lay-filter="propertyFilter"></table>
	                </div>
                
                   
                <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
		  			<legend>其它信息</legend>
				</fieldset>   
                    <div class="layui-form-item">
					    <label class="layui-form-label">还款来源</label>
					    <div class="layui-input-block">
					      <input type="text" name="repaymentSource" id="repaymentSource"  placeholder="请输入100字以内" lay-verify="required" maxlength="150" class="layui-input lafite_width_60" >
					    </div>
					</div>
    				
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">说明</label>
                        <div class="layui-input-block lafite_width_60">
                            <textarea id="comments" name="comments" placeholder="经营情况、借款用途、收入来源" lay-verify="required" maxlength="1500" class="layui-textarea lafite_textarea" ></textarea>
                        </div>
                    </div>

              		<div id="imagesDiv"  style=" margin-left: 5%; margin-right: 5%">
	                	<table class="layui-table" id="imagesNames" lay-filter="imagesFilter"></table>
	                </div>
	                
	                <div id="uploadFileDiv">
	                	<button type="button" id="addFile" class="layui-btn"  style="margin-left: 5%">上传文件</button>
	                </div>

                    <div class="layui-form-item" id="subDiv" style="margin-left: 30%;">
                        <div class="layui-input-block">
                            <button id="update" lay-submit="" lay-filter="suu" class="lafite-layui-btn2 lafite_margin_left_20px">提交</button>
                            <button id="tempUpdate" lay-submit="" lay-filter="tempSuu" class="lafite-layui-btn2 lafite_margin_left_20px">临时保存</button>
                        </div>
                    </div>
                    
                    </form>
                </div>	
                    
                    <!--  审核模块          审核模块  -->
                    <div class="layui-card" id="reviewDiv" name="reviewDiv" style="padding-top:10px; padding-bottom: 20px; background:white;display: none">
	                    <fieldset class="layui-elem-field layui-field-title " style="border-color: #009688;">
		  					<legend>审核信息</legend>
						</fieldset>   
	                    
	                    <form class="layui-form" action="" lay-filter="rewForm">
	                    
						    <div class="layui-form-item layui-form-text">
							    <label class="layui-form-label">审核说明</label>
							    <div class="layui-input-block lafite_width_60">
							      <textarea id="rComments" name="rComments" lay-verify="required" placeholder="请输入审核内容" class="layui-textarea"></textarea>
							    </div>
							</div>
							
							<div class="layui-form-item" pane="">
							    <label class="layui-form-label">审核结果</label>
							    <div class="layui-input-block layui-inline">
							      <input type="radio" name="rStatus" value="审核不通过" title="审核不通过" checked="">
							      <input type="radio" name="rStatus" value="审核通过" title="审核通过">
							    </div>
							    
							    <div class="layui-form-item  layui-inline" id="reviewRecordDiv" style="float:right; padding-bottom: 20px;">
			                    	<button id="reviewRecordBtn" lay-submit="" class="layui-btn" lay-filter="reviewRecord" >查看审核记录</button>
			                	</div>
							</div>
							
							<div class="layui-form-item" id="subDiv" name="subDiv" style="margin-left: 30%;">
		                        <div class="layui-input-block">
		                            <button id="rUpdate" lay-submit="" lay-filter="reviewSubmit" class="lafite-layui-btn2 lafite_margin_left_20px">提交</button>
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


	{{# if('未提交' == document.getElementById("statusFlag").value){  }}		
    	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	{{#  } }}	
	</script>
<!-- 	{{# if('未提交' == document.getElementById("statusFlag").value || ('审核不通过' == document.getElementById("statusFlag").value && '1' === document.getElementById("viewFlag").value )){  }}		
    	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	{{#  } }}	 -->
<!--     	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a> -->
    
    <script>
    
    layui.use('form', function(){
    	  var form = layui.form;
    	  form.render();  
    	  });
    
    
    //留给弹出层用
    var faContractId;
    
    var parentEdCon = parent.editEdCon;
    
	layui.use(['form', 'upload','table','layer','element','laydate'],function() {
	    var $ = layui.jquery
	    	,upload = layui.upload
	    	,form = layui.form 
	    	,table = layui.table
	    	, layer = layui.layer
	    	, element = layui.element
	    	,laydate = layui.laydate;
	    
	  //起始日期
	    laydate.render({
	      elem: '#beginDay'
	      ,min: 0
	      ,showBottom: false
	      ,done: function(value, date, endDate){
	    	  //alert(value);
	    	  
	    	  //放开借款期限
	    	  var termDicvObj = document.getElementById("termDiv");
	    	  var term = document.getElementById("term").value;
	    	  termDicvObj.setAttribute("style","display");  
	    	  
	    	  if(term != "" && term != null){
	    	  		termOblur(term);
	    	  }
	       }
	      
	    });
	    
	    if(parentEdCon){
			//alert(JSON.stringify(parentEdCon));
			$('#contractId').val(parentEdCon.contractId);
			//alert(parentEdCon.contractId);
		}
	    
	  	//创建contract表,拿到主键
	 // alert(JSON.stringify(parent.editEdCon.contractId));
		var conIdDoc = document.getElementById("contractId");
		//alert(JSON.stringify(conIdDoc.value));
		
		var uId='${sessionScope.user.uid}';
		if(!uId){
			//layer.msg("登录超时,请重新登录!");
			alert("登录超时,请重新登录!"); 
            top.location.href="<%=basePath%>login/toLogin";  
		}
		
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
 			    	   
			    	   form.val('pcForm', {
                           "balance": dataForm.balance,
                           "interest": dataForm.interest,
                           "term": dataForm.term,
                           "percentage": dataForm.percentage,
                           "beginDay": dataForm.beginDay,
                           "endDateInput": dataForm.endDateInput,
                           "grossRepayment": dataForm.grossRepayment,
                           "grossInterest": dataForm.grossInterest,
                           "repaymentSource": dataForm.repaymentSource,
                           "comments": dataForm.comments
                       }); 
			    	   
			    	   if(dataForm.repayMethod){
			    		   form.val('pcForm', {
	                           "repayMethod": dataForm.repayMethod
	                       }); 
			    	   }
			    	   
			    	   initPCForm();
			    	   
			    	   form.val('rewForm', {
                           "rComments": dataForm.rComments ,
                           "rStatus": dataForm.rStatus
                       }); 			    	   
			       }
			 });
		}
	    
	    //借款人表渲染
	    window.renderLoaner('#loanerTable','loaner');
	    
	    //添加借款人
		$(document).on('click','#addLoaner',function(){
			openAddLoanPage('loanerTable','添加借款人','loaner');
		});
	    
		//计算还款计划
		$(document).on('click','#calculateRepay',function(){
			calculateRepayPlan("1");
		});
   		
   		//查看还款计划
		$(document).on('click','#viewCalculateRepay',function(){
			calculateRepayPlan("0");
		});
   		
	    //渲染已上传的文件列表
	    renderImagesList();
   		//打开文件上传界面
   		$(document).on('click','#addFile',function(){
   			openUploadFile();
		});
	    
   		
	    //汽车表渲染
	    window.renderProperty('#mortgageProperty','mort');
	    
	    //添加抵押汽车
		$(document).on('click','#addMortProperty',function(){
			openAddProertyPage('mortgageProperty','添加抵押车信息','mort');
		});
	    
   		var conidStr = document.getElementById("contractId").value;
   		
            //业务员提交按钮
            form.on('submit(suu)', function(data){ 
            	data = data.field;
            	data['status']="待审核";
            	data['uId']=uId;
            	// alert(JSON.stringify(data));
            	
                $.ajax({
					type:'post',
					url:"<%=basePath%>user/updatePropertyCon",
					contentType:'application/json',
					data:JSON.stringify(data),
					success: function (result) {
						alert("提交成功");
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
						
						parent.layui.$('.layui-this i').click();

		            	//$("#subDiv").hide();
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest.status); 
               			console.log(XMLHttpRequest.readyState); 
               			console.log(textStatus); 
               			console.log(XMLHttpRequest.responseText); 
						alert("修改失败");
           		 }
				});
            	
            	return false;
            });
            
            
            //管理员提交按钮
            form.on('submit(reviewSubmit)', function(data){ 
            	data = data.field;
            	data['contractId']=conIdDoc.value;
            	data['uId']=uId;
            	 //alert(JSON.stringify(data));
            	 
            	// return false;
 
                $.ajax({
					type:'post',
					url:"<%=basePath%>user/submitReview",
					contentType:'application/json',
					data:JSON.stringify(data),
					success: function (result) {
						alert("提交成功");
						
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭 */
						
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
            	 //alert(JSON.stringify(data));
            	 
            	// return false;
 
                 parent.layer.open({
	                   type:2,
	                   title:'审核记录',
	                   content:'<%=basePath%>pawn/toReviewRecord?contractId=' + conIdDoc.value,
	                   area:['100%','100%'],
	                   success: function (layero, index) {
	                   		/* var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
	                   		body.find("#loanerSub").css('display','none');
	                   		body.find("#loanerRe").css('display','none');
	                   		
	    	               // layer.alert('编辑行：<br>'+ JSON.stringify(data));

	    	                // 循环所有键
	    	                for(var key in loanerLine) {
	    	                	if("gender"==key){
	    	                		body.find("input[name=gender][value=m]").attr("checked", loanerLine[key] == "m" ? true : false);
	    	                        body.find("input[name=gender][value=f]").attr("checked", loanerLine[key] == "f" ? true : false);
	    	                	}else{
		    	                	body.find("#"+key).val(loanerLine[key]);
	    	                	}
	    	                } */
	                   		
	                   		/* body.find("#name").text(11111111111111);
	                   		var j_find = $(layero).find("iframe").contents().find("#j_find");//获取子页面元素
	      					//使用#id就可以获取到弹出层里面的子元素。前面的写法都是固定的。
	        				j_find.find("#name").text("testtesttest"); */

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
 			                {field:'fileName', sort: true, fixed: true,title:'文件名'},
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
 			            } else if(obj.event === 'del'){
 			                layer.confirm('真的删除么?', function(index){
 			                	$.ajax({
 								       url: '<%=basePath%>ticket/deleteImage',
 								       type:'post',
 								       data:{"contractId" : conNum.value,
 								    	   	 "fileName" : loanerLine.fileName},
 								       dataType:"text",
 							    	   success:function(data){
 				 				    	   if(data = '0000'){
 							                   obj.del();
 				 				    	   }else{
 				 				    		   alert(JSON.stringify(data));
 				 				    	   }
 					 				   }	   	 
 								    	   	 
 								  });
 					        	  
 			                	
 			                    layer.close(index);
 			                });
 			        	  return false;
 			            } 
 			        });	        
 			}
 			
 			//临时保存按钮
 			form.on('submit(tempSuu)', function(data){
 				data = data.field;
 				
 				//校验
 				var re = /^[0-9]+$/ ;
 			    if(data.term!=""&&data.term!=null&&(data.term==0 || re.test(data.term) == false)){
 			    	layer.msg("借款期限必须是正整数！", {icon: 5,time:3000}); 
 			    	return false;
 			    }
 				
 			    var parnt = /^[1-9]\d*(\.\d+)?$/;//大于0的数
 	    		if(data.balance!=""&&data.balance!=null&&(!parnt.test(data.balance))){
 	    			layer.msg("借款金额格式不正确！", {icon: 5,time:3000}); 
 		    		return false;
 	    		}
 	    		if(data.interest!=""&&data.interest!=null&&(!parnt.test(data.interest))){
 		    		layer.msg("借款月息格式不正确！", {icon: 5,time:3000}); 
 		    		return false;
 	    		}
 			    
 				data['uId']=uId;
 				// alert(JSON.stringify(data));
 				       	
 				$.ajax({
 					type:'post',
 					url:"<%=basePath%>user/updatePropertyCon",
 					contentType:'application/json',
 					data:JSON.stringify(data),
 					success: function (result) {
 						alert("保存成功");
 						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
 						parent.layer.close(index); //再执行关闭
 						
 						$(".layui-this i ",parent.document).click();
 					},
 					error:function(XMLHttpRequest, textStatus, errorThrown){
 						console.log(XMLHttpRequest.status); 
 						console.log(XMLHttpRequest.readyState); 
 						console.log(textStatus); 
 						console.log(XMLHttpRequest.responseText); 
 						alert("修改失败");
 					}
 				});
 				return false;
 			});
            
	
	});
   
	
	function renderLoaner(tableId,loanType){
	    var conNum = document.getElementById("contractId")
	    //alert(conNum.value);
	    
	    layui.use(['table','layer'],function() {
		    var $ = layui.jquery,table = layui.table,layer=layui.layer;
		    
	        var abcTable = table.render({
	            elem:tableId
	            ,url:'<%=basePath%>pawn/getLoanerByConId'
	            ,where:{contractId: conNum.value,
	            	   loanerType: loanType}
	            ,cols:[[
	                {field:'name', sort: true, fixed: true,title:'姓名'},
	        		{field:'age', title:'年龄'},
	       			{field:'citizenID', title:'身份证号码'},
	        		{field:'loanerId',title:'主键',hide:true},
	                {fixed:'right', width:150, align:'center', toolbar: '#barDemo',title:'操作'}
	            ]]
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
	        
	            } else if(obj.event === 'del'){
	                layer.confirm('真的删除么?', function(index){
	                	$.ajax({
	 				       url:'<%=basePath%>pawn/delLoanerBetween',
	 				       type:'post',
	 				       data:{
	 				    	 'loanerBetweenId':loanerLine.loanerBetweenId  
	 				       },success:function(data){
	 				    	   if(data){
	 				    		   alert(JSON.stringify(data));
	 				    	   }else{
				                   obj.del();
	 				    	   }
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
	    });
	}
	

	
	//打开上传文件界面
	function openUploadFile(){
	    var conNum = document.getElementById("contractId");

	    layui.use(['table','layer'],function() {
		    var $ = layui.jquery,table = layui.table,layer=layui.layer;
	    
			var index = parent.layer.open({
	            type: 2, 	
	            title: '上传文件',
	            content: '<%=basePath%>ticket/toUploadFile?contractId=' + conNum.value,   
	            area: ['80%', '100%'],
	            shadeClose: true,
	            end: function(index,btn1){
					
	            	table.reload('imagesNames',{
	    				url: '<%=basePath%>ticket/reRenderFileList'
	    				,method: "GET"
	    				,where: {contractId: conNum.value}
	            	,done: function(res, curr, count){
		            	if(count > 1){
		            	    document.getElementById("imagesDiv").style.display="";
		            	}
		            } 
	    		});
	            	
	            	
	            	
	            	<%-- table.reload("loanerList",{
	     				url: '<%=basePath%>pawn/getLonerList'
	     				,methods:"GET"
	     				,where: {
	     					citizenID: data.field.citizenID
	     					}
	     				,page: {
	     					curr:1
	     				}
	     			}); --%>
	               
	        	}
	       });
	    });
	}
	
	//打开添加借款人界面的方法
	function openAddLoanPage(tableName,titleName,loanerType){
		 layui.use(['table'],function() {
			    var $ = layui.jquery,table = layui.table;
		
			var index = layer.open({
	            type: 2, 	
	            title: titleName,
	            content: '<%=basePath%>pawn/toLoanerList',   
	            area: ['80%', '100%'],
	            btn: ['选择'],
	            shadeClose: true,
	            yes: function(index,btn1){
	                var arr=$(btn1).find("iframe")[0].contentWindow.callbackdata();
	
					if(arr.data.length != 0){
						var cacheArra = layui.table.cache[tableName];
		                var closeFlag = false;
						
		 		        for(var k in cacheArra){
		 		        	if(cacheArra.length == 0){
		 		        		closeFlag = true;
		 		        		break;
		 		        	}else  if(arr.data[0].loanerId == cacheArra[k].loanerId){
								layer.msg(JSON.stringify('身份证号['+arr.data[0].citizenID+']已添加！'),{time:3000});
			 		        	closeFlag = true;
			 		        	break;
		 		        	}
		 		       	}
		 		        
						if(!closeFlag){
							//新增借款人信息
			                reloadTableData(arr.data[0].loanerId,loanerType,tableName);
						}
					}
					
					layer.close(index);
	               
	        	}
	       });
		 });
	}
	
	
	//从借款人信息表拿到的借款人跟合同绑定并重载
	function reloadTableData(loanerIdNum,loanerType,tableNam){
		layui.use(['table'],function() {
		    var $ = layui.jquery,table = layui.table;
		    
			var conIdDoc = document.getElementById("contractId");
			
			//alert(conIdDoc);
			
			var contracNum = conIdDoc.value;
			
			table.reload(tableNam,{
				url: '<%=basePath%>pawn/bindLoanBetween',
				where: {contractId: contracNum,
						loanerId: loanerIdNum,
						loanerType: loanerType}
			});
			
			//table.render();
		});
	}
	
	//打开添加抵押车界面的方法
	function openAddProertyPage(tableName,titleName,loanerType){
		 layui.use(['table'],function() {
			    var $ = layui.jquery,table = layui.table;
		
			var index = layer.open({
	            type:2, 	
	            title: titleName,
	            content: '<%=basePath%>car/toAddCarList',   
	            area: ['80%', '100%'],
	            btn: ['选择'],
	            shadeClose: true,
	            yes: function(index,btn1){
	                var arr=$(btn1).find("iframe")[0].contentWindow.callbackdata();
	
	                //alert(JSON.stringify(arr));
	                
					if(arr.data.length != 0){
						var cacheArra = layui.table.cache[tableName];
		                var closeFlag = false;
						
		 		        for(var k in cacheArra){	
		 		        	if(cacheArra.length == 0){
		 		        		closeFlag = true;
		 		        		break;
		 		        	}else  if(arr.data[0].carId == cacheArra[k].carId){
			 		        	
								layer.msg(JSON.stringify('该车辆已添加为抵押车！'),{time:3000});
			 		        	
			 		        	closeFlag = true;
			 		        	break;
		 		        	}
		 		       	}
		 		        
						if(!closeFlag){
							//添加车辆绑定信息
			                bindProperty(arr.data[0].carId,loanerType,tableName);
						}
					}
					
					layer.close(index);
	               
	        	}
	       });
		 });
	}
	
	//汽车信息表拿到的汽车跟合同绑定并重载
	function bindProperty(carId,mortgageTp,tableNam){
		layui.use(['table'],function() {
		    var $ = layui.jquery,table = layui.table;
		    
			var conIdDoc = document.getElementById("contractId");
			var contracNum = conIdDoc.value;
			
			table.reload(tableNam,{
				url: '<%=basePath%>pawn/bindProBetween',
				method: 'post',
				contentType: 'application/json',
				where: {contractId: contracNum,
						productId: carId,
						mortgageType: mortgageTp}
			});
			
			//table.render();
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
	                   area:['80%','100%'],
	                   success: function (layero, index) {
	                   		var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
	                   		body.find("#saveDiv").css('display','none');
	            		}
	               })
	        
	            } else if(obj.event === 'del'){
	                layer.confirm('真的删除么?', function(index){
	                	$.ajax({
	 				       url:'<%=basePath%>pawn/delPropertyBetween',
	 				       type:'post',
	 				       data:{
	 				    	 'proBetId':propertyLine.proBetId  
	 				       },
	 				       success:function(data){
	 				    	   if(data){
	 				    		   alert(JSON.stringify(data));
	 				    	   }else{
				                   obj.del();
	 				    	   }
	 				       }
	 				  	});
	                	
	                    layer.close(index);
	                });
	            } 
	        });	        
	    });
	}
	
	function termOblur(obj){
		layui.use(['laydate'],function() {
			 var $ = layui.jquery
 	    	,laydate = layui.laydate;
			
			var term = document.getElementById("term").value;
		    var re = /^[0-9]+$/ ;
		    
		    if(term==0 || re.test(term) == false){
		    	layer.msg("借款期限必须是正整数！", {icon: 5,time:3000}); 
		    }else{
		    	var endDateDiv = document.getElementById("endDateDiv");
		    	
				endDateDiv.setAttribute("style","display");  
		    	
		    	var resultStr = getTerminateDay(term);
		    	
		    	//初始赋值
			    laydate.render({
					elem: '#endDateInput'
					,value: resultStr
			    });
		    	
		    }
		});
	}
	
	//计算还款计划	
	function calculateRepayPlan(viewFlag){
    	layui.use(['form','laydate','layer'],function () {
    		var form = layui.form
    			,laydate = layui.laydate
    			,layer = layui.layer; 
    		
	    	var formData = form.val('pcForm');
	    	var checked = checkRepayForm(formData);
	    	
	    	//alert(JSON.stringify(formData));
	    	
	    	if(checked != false){
	    		//var aaa = calculateRepaymentPlan(formData);
		    	
		    	var grossInterestDiv = document.getElementById("grossInterestDiv");
		    	var grossRepaymentDiv = document.getElementById("grossRepaymentDiv");
		    	
		    	grossInterestDiv.setAttribute("style","display");  
		    	grossRepaymentDiv.setAttribute("style","display");
	 
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
							var repayPlan=layero.find("iframe")[0].contentWindow.callbackdata();
	            		   
							if(viewFlag == "1"){//计算
								form.val('pcForm', {
									"grossRepayment": repayPlan.grossRepayment,
									"grossInterest": repayPlan.grossInterest
								}); 
							}
	            	   }  
	            });
            	
            	
	    	}
    	});
	}
	
	function initPCForm(){
		layui.use(['form','laydate'],function() {
		    var $ = layui.jquery 
		    	,form = layui.form
		    	,laydate = layui.laydate; 
			var data = form.val('pcForm');
			
			if(data.term){
				document.getElementById("termDiv").style.display="";
			}
			if(data.endDateInput){
				document.getElementById("endDateDiv").style.display="";
			}
			if(data.grossRepayment){
				document.getElementById("grossRepaymentDiv").style.display="";
			}
			if(data.grossInterest){
				document.getElementById("grossInterestDiv").style.display="";
			}
			
			//alert(JSON.stringify(data));
		});
	}
	
	
    </script>

</html>