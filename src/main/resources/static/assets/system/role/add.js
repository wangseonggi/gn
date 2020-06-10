$(document).ready(function () {
    edit.onload();
});
let edit = (function () {

    layui.use(['form', 'element',  'table'], function () {
        var element, laydate, form, table;
        element = layui.element;
        form = layui.form;
        table = layui.table;

        var id = $("#id").val();

        /* ***** 表单提交开始 **** */
        form.on('submit(role1)', function (data) {

            $.ajax({
                url: '/xt/role/add',
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
                },
                error: function() {
                    alert("提交错误");
                }
            });
        });
    });

    return {
        onload: function () {

        }
    };
})();



