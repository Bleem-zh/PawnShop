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
        <title>汽车界面</title>
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
               <div class="layui-card">
             <div class="layui-col-md12" id="aaaaaaa">  
                 
				<input  id="editFlag" value="${editFlag}" type="text" name=editFlag  lay-verify="required" autocomplete="off" maxlength="15" class="layui-input layui-hide">
            	<form class="layui-form" action="#" role="form" lay-filter="loanerFilter">
					<input  id="carId" value="${car.carId}" type="text" name="carId" class="layui-input layui-hide">
					
					<div class="layui-form-item" style="padding-top: 20px; padding-bottom:20px;">
						<div class="layui-inline">
							<label class="layui-form-label">品牌</label>
							<div class="layui-input-inline">
								<input  id="brand" value="${car.brand}" type="text" name="brand" required lay-verify="required" autocomplete="off" maxlength="50" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">型号</label>
							<div class="layui-input-inline">
								<input value="${car.model}" type="text" name="model" id="model" required lay-verify="required" placeholder="" maxlength="30" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">生产日期</label>
						    <div class="layui-input-inline">
						        <input type="text" value="${car.dateOfManufacture}" name="dateOfManufacture" id="dateOfManufacture" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
						    </div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">颜色</label>
							<div class="layui-input-inline">
								<input value="${car.color}" type="text" name="color" id="color" required lay-verify="required" placeholder="" maxlength="30" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">行驶证</label>
							<div class="layui-input-inline">
								<input value="${car.drivingLicense}" type="text" name="drivingLicense" id="drivingLicense" required lay-verify="required" placeholder="" maxlength="30" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">发动机号</label>
							<div class="layui-input-inline">
								<input value="${car.engineNo}" type="text" name="engineNo" id="engineNo" required lay-verify="required" placeholder="" maxlength="30" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">裸车价</label>
							<div class="layui-input-inline">
								<input value="${car.nakedPrice}" type="text" name="nakedPrice" id="nakedPrice" required lay-verify="required|number" placeholder="￥" maxlength="30" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">评估价</label>
							<div class="layui-input-inline">
								<input value="${car.evaluationPrice}" type="text" name="evaluationPrice" id="evaluationPrice" required lay-verify="required|number" placeholder="￥" maxlength="30" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					
                	<div class="layui-form-item" id="saveDiv">
					    <div class="layui-input-block" style="float:right; margin-right: 10%;">
					      <button id = "loanerSub" name="loanerSub" type="submit" class="layui-btn " lay-submit="" lay-filter="addLoaner" >保存</button>
					      <!-- <button id = "loanerRe" name="loanerRe" type="reset" class="layui-btn layui-btn-primary layui-btn-lg">重置</button> -->
					    </div>
					</div>
				</form>
			</div>
			
			
			</div>
           </div>
        </div>
    </body>
    

    <script>

       
   layui.use(['form', 'layedit', 'laydate'], function(){      
	   var form = layui.form
	   ,layer = layui.layer
	   ,laydate = layui.laydate
	   ,$ = layui.jquery;
	   
	  var editFlag = document.getElementById("editFlag").value;
	  if("1" === editFlag){
		  //编辑、修改
		  //$("#drivingLicense").prop('disabled','disabled');
		  $("#engineNo").prop('disabled','disabled');
		  $("#brand").prop('disabled','disabled');
		  $("#model").prop('disabled','disabled');
		  $("#dateOfManufacture").prop('disabled','disabled');
	  }
	  
	//日期
	  laydate.render({
	    elem: '#dateOfManufacture'
	  });
	  
	   //监听提交
	   form.on('submit(addLoaner)', function(loaner){
		  /*  alert(JSON.stringify(loaner.field));
		  	 alert(JSON.stringify(loaner.field),{
		       title: '最终的提交信息'
		     });  */
		    // alert(JSON.stringify(loaner.field));
			var indexLoad = layer.load(1); 
			
			var urlStr = "<%=basePath%>car/insertCar";
			if("1" === editFlag){
				//编辑
				urlStr = "<%=basePath%>car/updateCar";
			}
			
		   $.ajax({
				type:'post',
				url: urlStr,
				contentType:'application/json',
				data:JSON.stringify(loaner.field),
				success: function (result) {
					layer.close(indexLoad);
					//alert(JSON.stringify(result));
					if("9999" == result.status){
						layer.msg(result.msg,{time:2000});
					}else{
						layer.msg("保存成功！",{time:1000});
						
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭 
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					layer.close(indexLoad);

					console.log(XMLHttpRequest.status); 
          			console.log(XMLHttpRequest.readyState); 
          			console.log(textStatus); 
          			console.log(XMLHttpRequest.responseText); 
					alert("新增失败!!");
      		 	}
			});
		   
      		return false;
		   
	   });
   
   });
 
  
 
    </script>

</html>