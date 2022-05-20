<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>登录页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<link href="<%=basePath%>css/login.css" rel="stylesheet" type="text/css" media="all" />
<script src="<%=basePath%>js/jquery-3.4.1.min.js"></script>
<script src="<%=basePath%>js/jquery-3.4.1.js"></script>
<script src="<%=basePath%>js/easyResponsiveTabs.js" type="text/javascript"></script>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>

<script>
 $(document).ready(function () {
	
	 
	layui.use(['form', 'layer'],function() {
	    var $ = layui.jquery
	    	,form = layui.form 
	    	, layer = layui.layer;
	 
	 $(".but").click(function(){
		
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();
		
		if(!username){
			layer.msg("用户名不能为空",{time:1000});
			
			return false;
		}
		if(!password){
			layer.msg("密码不能为空",{time:1000});

			return false;
		}
	
		var indexLoad = layer.load(1); 
		 
		$.ajax({
			type:'post',
			url:"<%=basePath%>login/loginIn",
			contentType:'application/json',
			dataType:'json',
			data:JSON.stringify({username:username,password:password}),
			success: function (result) {
				if(result==0 || result==1){
					layer.close(indexLoad);

					window.location.href = "<%=basePath%>login/toAdminMain";
				}else{
					layer.close(indexLoad);

					layer.msg("用户名或密码不对!",{time:1000});
					return false;
				}
			},error: function(XMLHttpRequest, textStatus, errorThrown){
				layer.close(indexLoad);
				
				alert(textStatus);

				console.log("登录失败");
				layer.msg("登录失败"+textStatus,{time:1000});
				return false;
            }
		});
	});
	});
});

</script>

</head>
<body>
	 <div id="login">  
        <h1>系统登录</h1>  
        <form method="post">  
            <input type="text" name="username" required="required" placeholder="用户名" name="u"></input>  
            <input type="password" name="password" required="required" placeholder="密码" name="p"></input>  
            <button class="but" type="button">登录</button>  
        </form>  
     </div>
     
       
</body>

<script type="text/javascript">
	if(top != window){
		 top.location.href = window.location.href;
	}
</script>

</html>
