<!DOCTYPE html>
<html class="x-admin-sm">
    
    <head>
        <meta charset="UTF-8">
        <title>添加房产典当物</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="css/font.css">
        <link rel="stylesheet" href="css/xadmin.css">
        <script src="layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="js/xadmin.js"></script>
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                <form class="layui-form" lay-filter="j_add">
                    <div class="layui-form-item">
                        <label class="layui-form-label">
                             ç å®åç§°
                        </label>
                        <div class="layui-input-block lafite_width_30">
                        <input type="text" name="title" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">
                             ç å®åç
                        </label>
                        <div class="layui-input-block lafite_width_30">
                        <input type="text" name="brand" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                 ä½¿ç¨æåµ
                            </label>
                            <div class="layui-input-block lafite_width_30">
                                <input type="text" name="jUsage" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                è´­ä¹°æ¶é´
                            </label>
                            <div class="layui-input-inline">
                                <input type="date" name="jBuyTime" required  lay-verify="required" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                å¸å½æ¶é´
                            </label>
                            <div class="layui-input-inline">
                                <input type="date" name="jPawnTime" required  lay-verify="required" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                åä»·æ ¼(&yen;)
                            </label>
                            <div class="layui-input-inline">
                               <input type="text" name="jPreMoney" lay-verify="required" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                å¸å½ä»·æ ¼(&yen;)
                            </label>
                           <div class="layui-input-inline">
                               <input type="text" name="jPawnMoney" lay-verify="required" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                åºå®ä»·æ ¼(&yen;)
                            </label>
                            <div class="layui-input-inline">
                                <input type="text" name="jSaleMoney" lay-verify="required" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>

                     <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                ä¸»ä½æè´¨
                            </label>
                            <div class="layui-input-inline">
                                <input type="text" name="jMaterial" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                é¶ç³æè´¨
                            </label>
                            <div class="layui-input-inline">
                                <input type="text" name="jInMaterial" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>


                    <div class="layui-form-item layui-form-text">
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                åé
                            </label>
                            <div class="layui-input-inline">
                                <input type="text" name="jWeight" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                åç ç´å¾(mm)
                            </label>
                            <div class="layui-input-inline">
                                <input type="text" name="jWeight" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                ç å®é¿åº¦(cm)
                            </label>
                            <div class="layui-input-inline">
                                <input type="text" name="jLength" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                ç å®ç§ç±»
                            </label>
                            <div class="layui-input-inline">
                                <input type="text" name="jType" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">
                                ç å®æ¬¾å¼
                            </label>
                            <div class="layui-input-inline">
                                <input type="text" name="jStyle" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">
                            ç å®æ°é
                        </label>
                        <div class="layui-input-inline">
                            <input type="number" name="jCounts" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">
                            ç å®å¶ä»è¯´æ
                        </label>
                        <div class="layui-input-block lafite_width_60">
                            <textarea name="jOther" placeholder="è¯·è¾å¥åå®¹" class="layui-textarea lafite_textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">
                            ç å®ç§ç
                        </label>
                        <div class="layui-upload">
                            <button type="button" class="layui-btn lafite_btn" id="lafite_upload">ç¹å»ä¸ä¼ </button> 
                            <blockquote class="layui-elem-quote layui-quote-nm" style="width: 60%;margin-top: 10px; margin-left: 5%">
                                é¢è§å¾ï¼
                                <div class="layui-upload-list" id="demo2"></div>
                            </blockquote>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="lafite-layui-btn" onclick="xadmin.close()">åæ¶</button>
                            <button lay-submit lay-filter="formSubmit" class="lafite-layui-btn2 lafite_margin_left_20px">ä¿å­</button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&amp;ak=f5bKSDjUlnYT4r664aQrQZB8&s=1"></script>
    <script>
        layui.use(['laydate', 'form', 'upload'],
        function() {
            var $ = layui.jquery
                    , upload = layui.upload
                    ,form=layui.form;

            var laydate = layui.laydate;

            //å¤å¾çä¸ä¼ 
            upload.render({
                elem: '#lafite_upload'
                , url: '/upload/'
                , multiple: true
                , before: function (obj) {
                    //é¢è¯»æ¬å°æä»¶ç¤ºä¾ï¼ä¸æ¯æie8
                    obj.preview(function (index, file, result) {
                        $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
                    });
                }
                , done: function (res) {
                    if (res.code > 0) {
                        return layer.msg('ä¸ä¼ å¤±è´¥');
                    }
                    //ä¸ä¼ å®æ¯
                }
            });

            /*ç¨æ·-åæ¶*/
            function member_del() {
                layer.confirm('ç¦»å¼å°ä¸ä¿å­å¡«ååå®¹ï¼',
                        function (index) {
                            //åå¼æ­¥å é¤æ°æ®
                        });
            };


            /*è¡¨åæäº¤äºä»¶ççå¬*/
            form.on('submit(formSubmit)',function(data){
                console.log(data.field);   //å½åå®¹å¨çå¨é¨è¡¨åå­æ®µï¼åå¼å¯¹å½¢å¼ï¼{name: value}
                return false;
            });
        })
    </script>

</html>