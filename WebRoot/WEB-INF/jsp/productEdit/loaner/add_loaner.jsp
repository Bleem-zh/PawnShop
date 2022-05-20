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
        <title>新增借款人或共借人</title>
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
             <div class="layui-col-md12" id="aaaaaaa">      
				<input  id="editFlag" value="${editFlag}" type="text" name=editFlag  lay-verify="required" autocomplete="off" maxlength="15" class="layui-input layui-hide">
            	<form class="layui-form" action="#" role="form" lay-filter="loanerFilter">
					<input  id="loanerId" value="${loaner.loanerId}" type="text" name="loanerId" class="layui-input layui-hide">
					
                   <div class="layui-card">
					<div class="layui-form-item" style="padding-top: 20px; padding-bottom:20px;">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-inline">
							<input  id="name" value="${loaner.name}" type="text" name="name" required lay-verify="required" autocomplete="off" maxlength="15" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">身份证号码</label>
						<div class="layui-input-inline">
							<input value="${loaner.citizenID}" type="text" name="citizenID" id="citizenID" required lay-verify="identity|required" placeholder="" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item" >
					    <label class="layui-form-label">性别</label>
					    <div class="layui-input-inline">
					      <input type="radio" name="gender" value="m" title="男" checked="">
					      <input type="radio" name="gender" value="f" title="女">
					    </div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">年龄</label>
						<div class="layui-input-inline">
							<input type="text" value="${loaner.age}" name="age" id="age" required	 lay-verify="number|required" maxlength="8" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">户籍</label>
						<div class="layui-input-inline">
							<input type="text" value="${loaner.register}" name="register" id="register" required lay-verify="required" maxlength="150" placeholder="" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">婚姻状况</label>
						<div class="layui-input-inline">
							<input type="text" name="marriage" value="${loaner.marriage}" id="marriage" required lay-verify="required" maxlength="15" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">征信</label>
						<div class="layui-input-inline">
							<input type="text" name="credit" id="credit" value="${loaner.credit}" required lay-verify="required" maxlength="30"autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">手机号码</label>
						<div class="layui-input-inline">
							<input type="text" name="telphoneNum" id="telphoneNum" value="${loaner.telphoneNum}" required lay-verify="required|phone" maxlength="30"autocomplete="off" class="layui-input">
						</div>
					</div>
					
                	<div class="layui-form-item">
					    <div class="layui-input-block">
					      <button id = "loanerSub" name="loanerSub" type="submit" class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addLoaner">保存</button>
					      <!-- <button id = "loanerRe" name="loanerRe" type="reset" class="layui-btn layui-btn-primary layui-btn-lg">重置</button> -->
					    </div>
					</div>
                   </div>
				</form>
			</div>
           </div>
        </div>
    </body>
    

    <script>

       
   layui.use(['form', 'layedit', 'laydate'], function(){      
	   var form = layui.form
	   ,layer = layui.layer;
	   
	  var editFlag = document.getElementById("editFlag").value;
	  if("1" === editFlag){
		  $("#citizenID").prop('disabled','disabled');
		  
		  form.val('loanerFilter',{
			  gender: '${loaner.gender}'
		  });
	  }
	  
	   //监听提交
	   form.on('submit(addLoaner)', function(loaner){
		  /*  alert(JSON.stringify(loaner.field));
		  	 alert(JSON.stringify(loaner.field),{
		       title: '最终的提交信息'
		     });  */
			var indexLoad = layer.load(1); 
		   
			
			var urlStr = "<%=basePath%>pawn/insertLoanerInfo";
			if("1" === editFlag){
				//编辑
				urlStr = "<%=basePath%>pawn/updateLoanerInfo";
			}
			
		   $.ajax({
				type:'post',
				url: urlStr,
				contentType:'application/json',
				data:JSON.stringify(loaner.field),
				success: function (result) {
					layer.close(indexLoad);
					
					if("0000" == result){

						layer.msg("保存成功！",{time:1000});
						
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					}else{
						layer.msg(result,{time:2000});
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