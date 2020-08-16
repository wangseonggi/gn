$(document).ready(function () {
    add.onload();
});

var add = (function () {
    layui.use(['form', 'upload'], function () {
        var form = layui.form;
        var upload = layui.upload;

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

            console.log(data.field);
            return false;
        });
    });

    // 获取请求参数
    function getQueryVariable(variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
                return pair[1];
            }
        }
        return (false);
    }

    return {
        onload: function () {

        }
    };
})();