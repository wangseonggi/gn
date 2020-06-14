$(document).ready(function () {
    edit.onload();
});
let edit = (function () {

    layui.use(['form', 'element',  'table', 'laydate'], function () {
        var element, laydate, form, table;
        element = layui.element;
        form = layui.form;
        table = layui.table;
        laydate = layui.laydate;

        var id = $("#id").val();

        laydate.render({
            elem: '#zhyxq',
            type: 'datetime',
            trigger: 'click'
        });
        laydate.render({
            elem: '#mmyxq',
            type: 'datetime',
            trigger: 'click'
        });

        // 编辑时初始化表单
        if(id) {
            // 禁用用户名input
            $("#username").attr("disabled","disabled");

            $.get('/xt/user/get', {id : id}, function (res) {
                form.val('member11', {
                    'username': res.data.username,
                    'nc': res.data.nc,
                    'dh': res.data.dh,
                    'dzyx': res.data.dzyx,
                    'zhyxq': res.data.zhyxq,
                    'mmyxq': res.data.mmyxq,
                    'zt': res.data.zt
                });
                form.render();
            });
        }

        /* ***** 表单提交开始 **** */
        form.on('submit(member)', function (data) {

            var password = $.trim($("#password").val());
            var opassword = $.trim($("#opassword").val());

            if(password !== opassword) {
                layer.msg("两次输入的密码不一致。", function() {
                    $("#password").val();
                    $("#opassword").val();
                });
                return;
            }

            $.ajax({
                url: '/xt/user/add',
                method: 'post',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'JSON',
                success: function (res) {
                    if (res.code == 0) {
                        layer.msg("✔ 保存成功!", {shift: -1, time: 2000}, function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }
                    else {
                        layer.msg(res.msg)();
                    }
                }
            });
        });
    });

    return {
        onload: function () {

        }
    };
})();



