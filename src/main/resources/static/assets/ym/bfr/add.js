$(document).ready(function () {
    family_add.onload();
});
let family_add = (function () {

    layui.use(['form', 'element', 'laydate', 'table'], function () {
        var element, laydate, form, table;
        element = layui.element;
        laydate = layui.laydate;
        form = layui.form;
        table = layui.table;

        var id = $("#id").val();
        // 编辑时初始化表单
        if(id) {
            $.get('/yw/bfr/get', {bfrid : id}, function (res) {
                form.val('member11', {
                    'xm': res.data.xm,
                    'xb': res.data.xb,
                    'sfzhm': res.data.sfzhm,
                    'lxdh': res.data.lxdh,
                    'gzdw': res.data.gzdw
                });
                form.render();
            });
        }

        /* ***** 表单提交开始 **** */
        form.on('submit(member)', function (data) {
            $.ajax({
                url: '/yw/bfr/add',
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



