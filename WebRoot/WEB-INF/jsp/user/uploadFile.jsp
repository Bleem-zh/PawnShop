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
            <div class="layui-col-md12">
                <div class="layui-card">
                
                	<div class="layui-card-body" style="padding-top: 20px;padding-bottom: 20px;display:none;">
						<label class="layui-form-label">借款编号</label>
						<div class="layui-input-inline">
							<input id="contractId" name="contractId" type="text" value="${contractId}" class="layui-input" disabled="">
						</div>
					</div>
					
                	<div class="layui-card-body" style="padding-top: 20px;padding-bottom: 20px">
	                    <div class="layui-upload layui-form-item" style="margin-top: 10px; margin-left: 5%; margin-right: 5%">
							  <div class="layui-upload-list" >
							    <table class="layui-table" id="tableImages">
							      <colgroup>
							        <col>
							        <col width="10%">
							        <col width="20%">
							        <col width="20%">
							      </colgroup>
							      <thead>
							        <tr>
							        	<th style="text-align: center">文件名</th>
								        <th style="text-align: center">大小</th>
								        <th style="text-align: center">上传进度</th>
								        <th style="text-align: center">操作</th>
							      	</tr>
							      </thead>
							      <tbody id="demoList"></tbody>
							    </table>
							  </div>
							  <button type="button" class="layui-btn layui-btn-normal" id="testList">选择文件</button> 
							  <button type="button" class="layui-btn" id="testListAction">开始上传</button>
						</div>
					</div> 
                </div>
            </div>
        </div>
    </div>
</body>


<script>
	var contractId;

	layui.use(['form', 'upload','table','layer','element'],function() {
	    var $ = layui.jquery
	    	,upload = layui.upload
	    	,form = layui.form 
	    	,table = layui.table
	    	, layer = layui.layer
	    	, element = layui.element;
   	
   			var conIdValue = document.getElementById("contractId").value;

   		    //演示多文件列表
   		    var uploadListIns = upload.render({
   		      elem: '#testList'
   		      ,elemList: $('#demoList') //列表元素对象
   		      ,url: '<%=basePath%>ticket/upLoadFile' 
   		      ,accept: 'file'
   		      ,multiple: true
   		      ,auto: false
   		      ,acceptMime: 'image/*'
   		      ,bindAction: '#testListAction'
   		      ,data: {
   		    	  contractId: conIdValue
   		      }
   		      ,choose: function(obj){   
   		        var that = this;
   		        var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
   		        //读取本地文件
   		        obj.preview(function(index, file, result){
   			          var tr = $(['<tr id="upload-'+ index +'">'
   			            ,'<td>'+ file.name +'</td>'
   			            ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
   			            ,'<td><div class="layui-progress" lay-showpercent="true" lay-filter="progress-demo-'+ index +'"><div class="layui-progress-bar" lay-percent="0%"></div></div></td>'
   			            ,'<td style="text-align: center">'
   			              ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
   			              ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete layui-hide">删除</button>'
   			            ,'</td>'
   			          ,'</tr>'].join(''));
   			          
   			          //单个重传
   			          tr.find('.demo-reload').on('click', function(){
   			            obj.upload(index, file);
   			            return false;
   			          });
   			          
   			          
   			          //上传前的删除
   			          tr.find('.before-delete').on('click', function(){
   				           delete files[index]; //删除对应的文件
   				           tr.remove();
   				           uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
   			        	  
   			        	   return false;
   			          });
   			          
   			          
   			          that.elemList.append(tr);
   			          element.render('progress'); //渲染新加的进度条组件
   			          
   			          /* var tr = that.elemList.find('tr#upload-'+ index)
   			          ,tds = tr.children(); */
   			          //tds.eq(3).html(''); //清空操作
   		          
   		        });
   		      }
   		      ,done: function(res, index, upload){ //成功的回调
   					var that = this;
   		      
   					if(res.code == '0000'){ //上传成功
   						//element.progress('progress-demo-'+ index, '100%'); //执行进度条。n 即为返回的进度百分比
   						var tr = that.elemList.find('tr#upload-'+ index)
   						,tds = tr.children();
   						tds.eq(3).html(''); //清空操作
   						/* tds.eq(3).find('.demo-view').removeClass('layui-hide'); //显示查看
   						tds.eq(3).find('.before-delete').addClass('layui-hide'); //隐藏上传前删除
   						tds.eq(3).find('.demo-delete').removeClass('layui-hide'); //显示上传后删除 */
   						delete this.files[index]; //删除文件队列已经上传成功的文件
   				        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
   						
   						return;
   					}else{
   						tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
   					 	this.error(index, upload);
   					}
   		      }
   		      ,allDone: function(obj){ //多文件上传完毕后的状态回调
   			      var that = this;

   			    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
   				parent.layer.close(index); //再执行关闭 
   		  		          
   		          /* var tr = that.elemList.find('tr#upload-'+ 'index')
   		          ,tds = tr.children(); */
   		          //tds.eq(3).html(''); //清空操作
   		      }
   		      ,error: function(index, upload){ //错误回调
   			        var that = this;
   			        var tr = that.elemList.find('tr#upload-'+ index)
   			        ,tds = tr.children();
   			        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
   		      }
   		      ,progress: function(n, elem, e, index){ //注意：index 参数为 layui 2.6.6 新增
   		          element.progress('progress-demo-'+ index, n + '%'); //执行进度条。n 即为返回的进度百分比
   		       }
   		    });
   		    
   		    

   		    
	    });
	    	                

</script>

</html>