$(document).ready(function () {
    add.onload();
});
var add = (function () {

    layui.use(['form', 'element',  'table', 'laydate'], function () {
        var laydate, form;
        form = layui.form;
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

            $.ajax({
                url:'/xt/user/get',
                method: 'get',
                async : false,
                data: {id : id},
                contentType: "application/json",
                success: function (res) {
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
                }
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
            if(!data.field.zt) {    // 如果选禁用，则zt不存在
                data.field.zt = 0;
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



