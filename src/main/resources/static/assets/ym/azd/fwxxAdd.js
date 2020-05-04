$(document).ready(function () {
    fwxxAdd.onload();
});
let fwxxAdd = (function () {

    layui.use(['form', 'element',  'table'], function () {
        var element, laydate, form, table;
        element = layui.element;
        form = layui.form;
        table = layui.table;

        var id = $("#id").val();
        // 编辑时初始化表单
        if(id) {
            $.get('/yw/azd/fwxxGet', {id : id}, function (res) {
                form.val('member11', {
                    'ld': res.data.ld,
                    'dy': res.data.dy,
                    'fh': res.data.fh,
                    'mc': res.data.mc
                });
                form.render();
            });
        }

        /* ***** 表单提交开始 **** */
        form.on('submit(member)', function (data) {
            $.ajax({
                url: '/yw/azd/fwxxAdd',
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
        });
    });

    return {
        onload: function () {

        }
    };
})();



