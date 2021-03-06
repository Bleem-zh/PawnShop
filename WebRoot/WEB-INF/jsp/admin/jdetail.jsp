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
    <title >珠宝详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
    <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>
    <body>
    <input type="hidden" id="jid" class="id" name="id"><!-- 使用隐藏域用于保存编辑项的ID值，便于提交修改 -->
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                <div>
                    珠宝详情
                </div>
      <form class="layui-form" id="j_detail">                  <!-- 珠宝详情表单 -->
        <div class="layui-form-item">
            <label class="layui-form-label">
                珠宝名称
            </label>
            <div class="layui-input-block lafite_width_30">
                <input id="name" type="text" readonly="readonly" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">
                珠宝品牌
            </label>
            <div class="layui-input-block lafite_width_30">
                <input id="brand" type="text" name="brand" readonly="readonly" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    使用情况
                </label>
                <div class="layui-input-block lafite_width_30">
                    <input id="usage" type="text" name="jUsage" readonly="readonly" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    购买时间
                </label>
                <div class="layui-input-inline">
                    <input id="buytime" type="text" name="jBuyTime" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    典当时间
                </label>
                <div class="layui-input-inline">
                    <input id="pawntime"  type="text" name="jPawnTime" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    原价格(&yen;)
                </label>
                <div class="layui-input-inline">
                    <input id="premoney"  type="text" name="jPreMoney" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    典当价格(&yen;)
                </label>
                <div class="layui-input-inline">
                    <input id="pawnmoney" type="text" name="jPawnMoney" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    出售价格(&yen;)
                </label>
                <div class="layui-input-inline">
                    <input id="salemoney" type="text" name="jSaleMoney" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    主体材质
                </label>
                <div class="layui-input-inline">
                    <input id="material" type="text" name="jMaterial" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    镶石材质
                </label>
                <div class="layui-input-inline">
                    <input id="inmaterial" type="text" name="jInMaterial" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>


        <div class="layui-form-item layui-form-text">
            <div class="layui-inline">
                <label class="layui-form-label">
                    克重
                </label>
                <div class="layui-input-inline">
                    <input id="weight" type="text" name="jWeight" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    单珠直径(mm)
                </label>
                <div class="layui-input-inline">
                    <input id="diam" type="text" name="jDiam" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    珠宝长度(cm)
                </label>
                <div class="layui-input-inline">
                    <input id="length" type="text" name="jLength" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <div class="layui-inline">
                <label class="layui-form-label">
                    珠宝种类
                </label>
                <div class="layui-input-inline">
                    <input id="type" type="text" name="jType" readonly="readonly" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    珠宝款式
                </label> 
                <div class="layui-input-inline">          
                    <input id="style_" type="text" name="jStyle" readonly="readonly" autocomplete="off" class="layui-input">  
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">
                珠宝数量
            </label>
            <div class="layui-input-inline">
                <input id="count" type="number" name="jCounts" readonly="readonly" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">
                珠宝其他说明
            </label>
            <div class="layui-input-block lafite_width_60">
                <textarea id="other" name="jOther" readonly="readonly" class="layui-textarea lafite_textarea"></textarea>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">
                珠宝照片
            </label>
            <div class="layui-upload">
                <blockquote class="layui-elem-quote layui-quote-nm" style="width: 60%;margin-top: 10px; margin-left: 5%">
                    预览图：
                    <img id="image" alt="" src="" >
                </blockquote>
            </div>
        </div>
    </form>
                </div>
            </div>
        </div>
    </body>
<!-- jquery -->
<script>
function save(){
    	alert("保存成功");
    }
</script>
<script src="<%=basePath%>js/jquery-3.4.1.min.js"></script>
    <script>layui.use(['laydate', 'form', 'upload'],
        function() {
            var $ = layui.jquery
            ,upload = layui.upload;

            var laydate = layui.laydate;
            var form = layui.form;
            //多图片上传
//            upload.render({
//                elem: '#lafite_upload'
//                ,url: '/upload/'
//                ,multiple: true
//                ,before: function(obj){
//                //预读本地文件示例，不支持ie8
//                obj.preview(function(index, file, result){
//                    $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
//                });
//                }
//                ,done: function(res){
//                //上传完毕
//                }
//            });

//            function previewImg(Url) {
//                var img = new Image();
//                img.src = Url;
//                var height = img.height; //获取图片高度
//                var width = img.width; //获取图片宽度
//                var imgHtml = "<img src='" + Url + "' />";
//                //弹出层
//                layer.open({
//                    type: 1,
//                    shade: 0.8,
//                    offset: 'auto',
//                    area: [width + 'px',height+'px'],
//                    shadeClose:true,//点击外围关闭弹窗
//                    scrollbar: false,//不现实滚动条
//                    title: "图片预览", //不显示标题
//                    content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
//                    cancel: function () {
//                        //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
//                    }
//                });
//            }

            $("#demo2 img").click(function() {
                previewImg($(this).attr("src"));
            });
            
            form.on('select(state)', function(data){         //监听下拉框的选择
            	console.log(document.getElementById("jid").value);//获取输入框元素);
  				console.log(data.value); //得到被下拉框选中的值
  				$.ajax({
            				type:"get",
            				url:"<%=basePath%>admin/changeJstate",
            				data:{
            					"jid":document.getElementById("jid").value,
            					"state":data.value
            				},
            				success:function(data) {
            				console.log("Change state successfully !");
           				 }
        			});
			});  

        });


    </script>


</html>