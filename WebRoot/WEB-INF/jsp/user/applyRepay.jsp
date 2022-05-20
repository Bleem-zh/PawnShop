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
        <title>预生成还款计划</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="<%=basePath%>css/font.css">
        <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
        <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
        <script src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
        <!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
        
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12" >
                	<form id="contractForm" class="layui-form"  role="form" autocomplete="off" lay-filter="pcForm">
		                <div class="layui-card" style="padding-top: 20px;padding-bottom: 20px">
			                <div class="layui-form-item">
			                	<div id="repayMethodDiv" class="layui-inline" >
									<label class="layui-form-label">还款方式</label>
									<div class="layui-input-inline">
										<input id="repayMethod" type="text" name="repayMethod" value="${contract.repayMethod}" class="layui-input" disabled="">
									</div>
								</div>
							
				                <div id="grossRepaymentDiv" class="layui-inline">
									<label class="layui-form-label">每月还款(&yen;)</label>
									<div class="layui-input-inline">
										<input id="monthRey" type="text" name="monthRey" autocomplete="off" class="layui-input" disabled="">
									</div>
								</div>
			                </div>
							
							<div class="layui-form-item">
								<div id="grossRepaymentDiv" class="layui-inline">
									<label class="layui-form-label">还款总额(&yen;)</label>
									<div class="layui-input-inline">
										<input id="grossRepayment" type="text" name="grossRepayment" autocomplete="off" class="layui-input" disabled="">
									</div>
								</div>
								
								<div id="grossInterestDiv" class="layui-inline" >
									<label class="layui-form-label">利息总额(&yen;)</label>
									<div class="layui-input-inline">
										<input id="grossInterest" type="text" name="grossInterest"  autocomplete="off" class="layui-input" disabled="">
									</div>
								</div>
							</div>
			             </div>
			             <div id="timeLineDiv" class="layui-card" style="padding-top: 10px;padding-bottom: 10px;padding-left: 20px">
								<fieldset class='layui-elem-field layui-field-title' style='margin-top: 10px;'>
									<legend id="timeLineField">详细还款计划</legend>
								</fieldset>
								<div style="padding-left: 20px">
									<ul class='layui-timeline' id="ultask">
										
									</ul>
								</div>
						</div>
					</form>
                </div>
            </div>
        </div>
    </body>
    
    
    <script th:inline="javascript">
		var callbackdata = function () {
			var  $ = layui.jquery,form=layui.form;
			
			var formData = form.val('pcForm'); //获取选中的数据
			
		    return formData;
		}
	</script>
    
    <script>
    
    initPage();
    
	layui.use(['form', 'table','layer','element','laydate'],function() {
	    var $ = layui.jquery
	    	,form = layui.form 
	    	,table = layui.table
	    	, layer = layui.layer
	    	, element = layui.element
	    	,laydate = layui.laydate;
	    	
	    var contractObj	={balance:'${contract.balance}'
	    		,interest:'${contract.interest}'
	    		,repayMethod:'${contract.repayMethod}'		
	    		,beginDay:'${contract.beginDay}'		
	    		,term:'${contract.term}'		
	    		,endDateInput:'${contract.endDateInput}'};
	    
	    var repayPlan = calculateRepaymentPlan(contractObj);
	    
	    form.val('pcForm', {
            "monthRey": repayPlan.monthRey,
            "grossRepayment": repayPlan.grossRepayment,
            "grossInterest": repayPlan.grossInterest
        }); 
	    
	    //展示时间线
	    if("等额本息"!='${contract.repayMethod}'){
		    showTimePlan(repayPlan);
		}
	    
	   
	    
	    
	});
	function showTimePlan(repayPlanObj){
		var repayPlan = repayPlanObj.repayPlan;
		
	    //alert(JSON.stringify(repayPlan));
		 for(var i=0;i<repayPlan.length;i++){
				var tempStr = '';
				
				tempStr = '<li class="layui-timeline-item">'
						 + '<div class="layui-timeline-content layui-text">'
						 + '<strong>'+repayPlan[i].repayDate+'&nbsp</strong>'
						 + '<i class="layui-icon layui-timeline-axis"></i>'
						 + '<strong>&nbsp&nbsp&nbsp&nbsp'+repayPlan[i].monthReyStr+'</strong>'
						 + '<p style="padding-left: 100px">本金'+repayPlan[i].monthBalance+'+利息'+repayPlan[i].repayRate+'</p>'
						 + '</div></li>';
						 
				var he = document.getElementById("timeLineDiv").innerHTML;
				
				document.getElementById("timeLineDiv").innerHTML= he + tempStr;  
		    }
		 
		 	var hee = document.getElementById("timeLineField").innerHTML;
			document.getElementById("timeLineField").innerHTML= hee + '&nbsp&nbsp（共'+repayPlan.length+'期）'; 
	}
	
	//页面初始化的显示逻辑
	function initPage(){
		if("等额本息"=='${contract.repayMethod}'){
			document.getElementById("timeLineDiv").style.display="none";
		}else{
			document.getElementById("grossRepaymentDiv").style.display="none";
		}
	}
	
	    

		
	
    </script>

</html>