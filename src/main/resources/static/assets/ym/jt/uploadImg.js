layui.use(['upload', 'laytpl'], function () {
    var $ = layui.jquery, upload = layui.upload, laytpl = layui.laytpl, layer = layui.layer;
    $("[id^='listImg']").on('click', function () {
        var result = $(this)[0].src;
        // 放大预览图片
        parent.layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            maxmin: true,
            shadeClose: true,
            area: ['90%', '90%'],
            content: "<div style='text-align: center'><img src=" + result + " /></div>"
        });
    });
    //多图片上传
    upload.render({
        elem: '#sele_imgs' //开始
        , acceptMime: 'image/*'
        , url: '/yw/yxh/upload2'
        , auto: false
        , bindAction: '#upload_imgs'
        , multiple: true
        , size: 1024 * 5
        , choose: function (obj) { //选择图片后事件
            var fileArr = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            $('#upload_imgs').prop('disabled', false);
            obj.preview(function (index, file, result) {
                var data = {
                    index: index,
                    name: file.name,
                    result: result,
                    imgId: 'showImg' + index,
                    btnId: 'upload_img_' + index,
                    containerId: 'upContainer' + index
                };
                //将预览html 追加
                laytpl(img_template.innerHTML).render(data, function (html) {
                    $('#imgs').append(html);
                });
                //删除某图片
                $("#upload_img_" + index).bind('click', function () {
                    delete fileArr[index];
                    if($.isEmptyObject(fileArr)) { // 如果没有待上传文件，则禁用
                        $('#upload_imgs').prop('disabled', true);
                    }
                    $("#upContainer" + index).remove();
                });
                $('#showImg' + index).bind('click', function () {
                    var width = $("#showImg" + index).width();
                    var height = $("#showImg" + index).height();
                    var scaleWH = width / height;
                    var bigH = 600;
                    var bigW = scaleWH * bigH;
                    if (bigW > 900) {
                        bigW = 900;
                        bigH = bigW / scaleWH;
                    }
                    // 放大预览图片
                    parent.layer.open({
                        type: 1,
                        title: false,
                        closeBtn: 1,
                        maxmin: true,
                        shadeClose: true,
                        area: ['90%', '90%'],
                        content: "<div style='text-align: center;'><img src=" + result + " /></div>"
                    });
                });
            });
        }
        , before: function (obj) { //上传前回函数
            this.data = {'fid': $('#fid').val(), 'type': 'jtjbqk'};
            layer.load(); //上传loading
        }
        , done: function (res, index, upload) { //上传完毕后事件
            if (res.code == 0) {
                return delete this.files[index];
            }
            else {
                top.layer.msg("上传失败.");
            }
        }
        ,allDone: function(obj){ //当文件全部被提交后，才触发
            if(obj.successful == obj.total) {
                top.layer.msg("上传成功");
            }
            else {
                top.layer.msg("已上传" + obj.successful + "张图片");
            }
            $('#imgs').html("");
            layer.closeAll('loading');
        }
        , error: function (index, upload) {
            layer.closeAll('loading'); //关闭loading
            top.layer.msg("上传失败");
        }
    });
});