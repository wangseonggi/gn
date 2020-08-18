$(document).ready(function () {
    add.onload();
});

var add = (function () {
    layui.use(['form', 'upload'], function () {
        var form = layui.form;
        var upload = layui.upload;

        //
        var id = $("#id").val();
        if(id) {
            $.get('/yw/azd/getAzd',{id : id}, function(res) {
                $('#mc').val(res.data.mc);
                $('#jc').val(res.data.jc);
                $('#dz').val(res.data.dz);
                $('#img').val(res.data.img);
                form.render();
            })
        }

        var uploadInst = upload.render({
            elem: '#upImg' //绑定元素
            , url: '/yw/azd/upload'
            , acceptMime: 'image/*'
            , size: 1024 * 2
            , done: function (res) {
                $('input[name="img"]').val(res.data);
                // 显示图片
            }
            , error: function () {
                //请求异常回调
            }
        });

        form.on('submit(azdAdd)', function (data) {
            $.ajax({
                url: '/yw/azd/addAzd',
                method: 'post',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'JSON',
                success: function (res) {
                    if (res.code = '0') {
                        layer.msg("✔ 保存成功!", {shift: -1, time: 2000}, function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }
                    else {
                        layer.msg("× 保存失败");
                    }
                }
            });
            return false;
        });
    });

    return {
        onload: function () {

        }
    };
})();