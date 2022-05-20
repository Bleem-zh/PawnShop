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
        <title>新增房产信息</title>
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
             	<div class="layui-card" style="padding-top: 20px;padding-bottom: 20px;">    
            	<form class="layui-form" action="#" role="form" >
            	
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">小区名称</label>
							<div class="layui-input-inline">
								<input type="tel" id="communityName" name="communityName" lay-verify="required" autocomplete="off" class="layui-input" maxlength="80">
							</div>
						</div>
					</div>
                   
                   
					<div class="layui-form-item">
						<label class="layui-form-label">小区地址</label>
						<div class="layui-input-block lafite_width_60">
							<input type="text" id="communityAddress" name="communityAddress" lay-verify="required"  autocomplete="off" class="layui-input" maxlength="150">
						</div>
					</div>
                   
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">类型</label>
							<div class="layui-input-inline">
								<input type="text" id="functional" name="functional" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">面积(平方)</label>
							<div class="layui-input-inline">
								<input type="text" id="area" name="area" lay-verify="required|number" autocomplete="off" class="layui-input" maxlength="25">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">房型</label>
							<div class="layui-input-inline">
								<input type="text" id="proType" name="proType" lay-verify="required" autocomplete="off" class="layui-input" maxlength="80">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">装修</label>
							<div class="layui-input-inline">
								<input type="text" id="decoration" name="decoration" lay-verify="required" autocomplete="off" class="layui-input" maxlength="80">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">状态</label>
							<div class="layui-input-inline">
								<input type="text" id="status" name="status" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">房龄</label>
							<div class="layui-input-inline">
								<input type="text" id="years" name="years" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
					</div>
                   
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">权利人</label>
							<div class="layui-input-inline">
								<input type="text" id="obligee" name="obligee" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">权利人关系</label>
							<div class="layui-input-inline">
								<input type="text" id="obligeeRelation" name="obligeeRelation" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">老人小孩</label>
							<div class="layui-input-inline">
								<input type="text" id="olderChildren" name="olderChildren" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">户口</label>
							<div class="layui-input-inline">
								<input type="text" id="registe" name="registe" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">房产总价</label>
							<div class="layui-input-inline">
								<input type="text" id="procount" name="procount" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">房产单价</label>
							<div class="layui-input-inline">
								<input type="text" id="univalent" name="univalent" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">抵押贷款</label>
							<div class="layui-input-inline">
								<input type="text" id="mortgage" name="mortgage" lay-verify="required" autocomplete="off" class="layui-input" maxlength="80">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">贷款成数</label>
							<div class="layui-input-inline">
								<input type="text" id="percentage" name="percentage" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">产权来源</label>
							<div class="layui-input-inline">
								<input type="text" id="source" name="source" lay-verify="required" autocomplete="off" class="layui-input" maxlength="15">
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
					    <div class="layui-input-block">
					      <button id = "propertySub" name="propertySub" type="submit" class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addLoaner">立即提交</button>
					      <button id = "propertyRe" name="propertyRe" type="reset" class="layui-btn layui-btn-primary layui-btn-lg">重置</button>
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

	   //监听提交
	   form.on('submit(addLoaner)', function(loaner){
		  /*  alert(JSON.stringify(loaner.field));
		  	 alert(JSON.stringify(loaner.field),{
		       title: '最终的提交信息'
		     });   */
		   $.ajax({
				type:'post',
				url:"<%=basePath%>pawn/insertProperty",
				contentType:'application/json',
				data:JSON.stringify(loaner.field),
				success: function (result) {
					if("0000" == result){
						alert("新增成功！");
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					}else{
						alert(result,{
							title:'提示'
						});
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
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