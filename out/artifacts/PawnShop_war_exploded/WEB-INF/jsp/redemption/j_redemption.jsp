<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>在线赎当-奢侈品珠宝</title>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>css/j_continued.css">
</head>
<body>
<!--首页-->
<div class="xu_box_header">
    <div class="xu_box_header_content">
        <ul>
            <li><a href="<%=basePath%>user/backToMain"><img src="<%=basePath%>images/logo.jpg" width="100px"></a></li>
            <li>在线赎当-奢侈品珠宝</li>
        </ul>
        <a class="back" href="<%=basePath%>redemption/toRedemption">返回赎当页面</a>
    </div>
</div>
<!--图文详情-->
<div class="main">
    <div class="dy-content">
        <div class="dy-videos">
            <ul class="dy-video-list" id="dy-video-list">
                <!--动态添加-->
            </ul>
        </div>
    </div>
</div>


<!-- jquery -->
<script src="<%=basePath%>js/jquery.min.js"></script>


<!-- bootstrap -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>

<script type="text/javascript">
    function getJewelleryList() {
        $.ajax({
            type:"get",
            url:"<%=basePath%>redemption/getJewelleryList",
            data:{
            	"uid":13
            },
            success:function(data) {
            	    var j_list = data;
            	    dynamicLoad(j_list.length);  
            	    console.log("j_list长度为"+j_list.length+j_list);
            	    for(i=1;i<=j_list.length;i++){
	            	    $("#id-"+i).text("编号："+j_list[i-1].jid);             //为相应的id传入相应的值
	            	    $("#brand-"+i).text(j_list[i-1].jbrand);
        				$("#material-"+i).text(j_list[i-1].jmaterial);
      				 	$("#inmaterial-"+i).text(j_list[i-1].jinmaterial);
       					$("#id_"+i).text(j_list[i-1].jid);
       					$("#pawntime-"+i).text(j_list[i-1].jpawntime);
       					$("#image-"+i).attr('src',"/images/"+j_list[i-1].jphoto)
       					$("#btn-"+i).attr('onclick',"continue_("+j_list[i-1].jid+")");           //为相应的按钮的onclick传入相应的参数
            	    }
            }
        });
    }
    //页面初始化时得到珠宝典当中列表
    window.onload=function(){
    getJewelleryList();
    }
</script>
<script>
function continue_(i){
    	alert("你的赎当申请已提交，请留意消息");
    	var jid = i;
    	window.location.href = "<%=basePath%>redemption/jewelleryRedemption?jid="+i;
    }
</script>

<script>
	
    function dynamicLoad(len) {
/*     ${requestScope.j_list}.forEach(function(element) {
    console.log(element);
	}); */
/* 	console.log(array); */
        var html='';
        var modal='';    
        var data=1;
        for(var i=1;i<=len;i++){
             /* 点击弹出的详情框 */
        	modal = `<div class="modal fade" id="jModal`+ i +`" data-backdrop="static" tabindex="-1" role="dialog">
                          <div class="modal-dialog modal-lg  modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">是否赎当？</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <div class="modal-body">
                                       您要赎当的是编号为:
                                        <span id="id_`+ i +`"></span> <br>
                                        典当时间为:
                                        <span id="pawntime-`+ i +`"></span> <br>
                                        <hr>
                                        您需要结清前期所有的利息和综合费用:<br>
                                        当前的利息是:
                                        <span>5</span> <br>
                                        当前的综合费用为:
                                        <span>100</span> <br>
                                        <hr>
                                        你一共需要支付:
                                        <span>105</span>
                                </div>
                                <div class="modal-footer">
                                    <button id="btn-`+ i +`" class="btn btn-primary btn-danger" onclick="">赎当</button>
                                </div>
                            </div>
                        </div>
                    </div> `;
            if(i%3===0){               //如果位于每一行的最右边 则拥有不同的css样式
                html =`<li data- class="dy-video-item dy-video-meta-left">
                    <!--图片-->
                    <div class="dy-video-meta">
                        <!--放背景图片的-->
                        <div class="dy-video-meta-bg"></div>
                        <div class="dy-video-meta-dy">
                            <h4 class="dy-video-title">
                                <a id="id-`+ i +`" >编号</a>
                            </h4>
                            <ul class="dy-video-meta-list">
                                <li class="dy-video-actors">
									<span class="dy-video-tip">
										品牌
									</span>
                                    <a id="brand-`+ i +`"></a>
                                </li>
                                <li class="dy-video-types">
                                    <span class="dy-video-tip">主体材质:</span>
                                    <span id="material-`+ i +`" class="dy-video-meta-filter"></span>
                                </li>
                                <li class="dy-video-areas">
                                    <span class="dy-split">|</span>
                                    <span class="dy-video-tip">镶石材质:</span>
                                    <span id="inmaterial-`+ i +`" class="dy-video-meta-filter"></span>
                                </li>
                            </ul>
                            <p class="dy-video-intro">
                                	距离典当时间已经过了:
                                	距离典当期限还剩  天
                            </p>
                        </div>
                        <div class="dy-video-meta-bg"></div>
                    </div>
                    <!--点击出来的详情-->
                    <div class="dy-video-poster">
                        <a class="dy-video-link">
                            <img id="image-`+ i +`" class="dy-video-img" src="" alt="编号">
                        </a>
                    </div>
                    <!--详情开头-->
                    <div class="dy-video-primary">
                        <h4 class="dy-video-title">
                            <a href="#jModal`+ i +`" data-toggle="modal">在线赎当</a>
                        </h4>
                    </div>
                </li>`;
                
            }
            else {              //如果不位于每一行最右 则拥有正常的css样式
                html =`<li data- class="dy-video-item dy-video-meta-right">
                    <!--图片-->
                    <div class="dy-video-meta">
                        <!--放背景图片的-->
                        <div class="dy-video-meta-bg"></div>
                        <div class="dy-video-meta-dy">
                            <h4 class="dy-video-title">
                                <a id="id-`+ i +`">编号</a>
                            </h4>
                            <ul class="dy-video-meta-list">
                                <li class="dy-video-actors">
									<span class="dy-video-tip">
										品牌:
									</span>
                                    <a id="brand-`+ i +`"></a>
                                </li>
                                <li class="dy-video-types">
                                    <span class="dy-video-tip">主体材质:</span>
                                    <span id="material-`+ i +`" class="dy-video-meta-filter"></span>
                                </li>
                                <li class="dy-video-areas">
                                    <span class="dy-split">|</span>
                                    <span class="dy-video-tip">镶石材质:</span>
                                    <span id="inmaterial-`+ i +`" class="dy-video-meta-filter"></span>
                                </li>
                            </ul>
                            <p class="dy-video-intro">
                                距离典当时间已经过了:<br>
                                距离典当期限还剩天
                            </p>
                        </div>
                        <div class="dy-video-meta-bg"></div>
                    </div>
                    <!--点击出来的详情-->
                    <div class="dy-video-poster">
                        <a class="dy-video-link">
                            <img id="image-`+ i +`" class="dy-video-img" src="" alt="编号">
                        </a>
                    </div>
                    <!--详情开头-->
                    <div class="dy-video-primary">
                        <h4 class="dy-video-title">
                            <a id="detail" href="#jModal`+ i +`" data-toggle="modal">在线赎当</a>
                        </h4>
                    </div>
                </li>`;          
            }
        //每一次循环后都把已经生成的html标签添加到页面中，并动态生成id
        $("#dy-video-list").append(html);         
        $("body").append(modal);
        $("#dy-video-list").trigger("create");

/*         $("#id").attr('id',"id-"+i);         //动态生成id           //使用反引号``可以提取数据直接生成id 不需要再在这里生成了
        $("#brand").attr('id',"brand-"+i);
        $("#material").attr('id',"material-"+i);
        $("#inmaterial").attr('id',"inmaterial-"+i);
        $("#detail").attr('href',"jModal"+i);
        $("#jModal").attr('id',"jModal"+i);
        $("#id_").attr('id',"id_-"+i);
        $("#pawntime").attr('id',"pawntime-"+i);
        $("#btn").attr('id',"btn-"+i);
        console.log("btn-"+i); */
       }
       
}
</script>
</body>
</html>