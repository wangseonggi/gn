layui.use(['upload', 'laytpl', 'form'], function () {

    var $ = layui.jquery

        , upload = layui.upload

        , laytpl = layui.laytpl

        , form = layui.form;
    var layer = layui.layer;
    var fileCount = 0;//控制文件数量


    $("[id^='delImg']").on('click',function () {
        var obj  = this;
        layer.confirm("确定删除？",{btn:['确定','取消'],title: '提示'},function () {
            var id = $(obj).prev().val();
            console.log(id);
            $.ajax({
                type:'post',
                url:'/yw/yxh/delPhoto',
                data:{'id':id},
                async:false,
                dataType:'json',
                success:function (data) {
                    if(data.code==0){
                        $(obj).closest('div').remove();//当前父div删除
                        layer.msg('删除成功！',{icon:1});

                    }
                }
            })
        });

    });


    $('#add-new').click(function () {
        var e = $(this),
            i = '/sys/dept',
            t = 222;
        layui.router();
        //F.tabsPage.elem = e;
        var l = parent === self ? layui : top.layui;
        l.index.openTabsPage(i, t || e.text())
    });


//批量删除 单击事件

    $('#dele_imgs').click(function () {

        $('input:checked').each(function (index, value) {

            var filename=$(this).parent().attr("filename");

            delete imgFiles[filename];

            $(this).parent().remove()

        });

    });



    var imgFiles;


//多图片上传

    var uploadInst = upload.render({

        elem: '#sele_imgs' //开始

        , acceptMime: 'image/*'

        , url: '/yw/yxh/upload'

        , auto: false

        , bindAction: '#upload_imgs'

        , multiple: true

        , size: 1024 * 12

        , choose: function (obj) { //选择图片后事件

            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列

            fileCount++;//选择文件后，文件个数加1

            imgFiles = files;


            $('#upload_imgs').prop('disabled',false);


//预读本地文件示例，不支持ie8

            obj.preview(function (index, file, result) {

                var data = {

                    index: index,

                    name: file.name,

                    result: result,
                    imgId: 'showImg'+index,
                    btnId: 'upload_img_'+index

                };



                //将预览html 追加

                laytpl(img_template.innerHTML).render(data, function (html) {

                    $('#imgs').append(html);
                    fileCount--;//删除一个文件

                });

                //删除某图片
                $("#upload_img_" + index).bind('click', function () {
                    delete files[index];
                    $("#container"+index).remove();
                });


                $('#showImg'+index).bind('click',function () {
                    var width = $("#showImg"+index).width();
                    var height = $("#showImg"+index).height();
                    var scaleWH = width/height;
                    var bigH = 600;
                    var bigW = scaleWH*bigH;
                    if(bigW>900){
                        bigW = 900;
                        bigH = bigW/scaleWH;
                    }

                    // 放大预览图片
                    parent.layer.open({
                        type: 1,
                        title: false,
                        closeBtn: 1,
                        maxmin: true,
                        shadeClose: true,
                        area:['90%','90%'],
                        //area: [bigW + 'px', bigH + 'px'], //宽高
                        content:"<img src="+result+" />"
                        //content: "<img width='"+bigW+"' height='"+bigH+"' src=" + result + " />"
                    });

                });


                //绑定单击事件

                $('#imgs div:last-child>img').click(function () {

                    var isChecked = $(this).siblings("input").prop("checked");

                    $(this).siblings("input").prop("checked", !isChecked);

                    return false;

                });


            });

        }

        , before: function (obj) { //上传前回函数
            var fid = $('#fid').val()
            this.data = {'fid':fid};
            console.log(this.data);

            layer.load(); //上传loading

        }

        , done: function (res,index,upload) { //上传完毕后事件


            layer.closeAll('loading'); //关闭loading

//上传完毕


            $('#imgs').html("");//清空操作


            top.layer.msg("上传成功！");


            return delete imgFiles[index]; //删除文件队列已经上传成功的文件


        }

        , error: function (index, upload) {


            layer.closeAll('loading'); //关闭loading


            top.layer.msg("上传失败！");


        }

    });


});