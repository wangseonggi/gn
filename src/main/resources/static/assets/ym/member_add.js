$(document).ready(function () {
    member_add.onload();
});
 var member_add = (function () {
    layui.use(['form'], function () {

         var form = layui.form;

        layui.form.render('select');

        if($("#memberid").val()) {
            $.ajax({
                url: "/yw/jt/getCy",
                method: 'get',
                data: {id: $("#memberid").val()},
                contentType: "application/json",
                success: function (res) {
                    // 初始化表单
                    form.val('member11', {
                        'memberid': res.data.memberid,
                        'fid': res.data.fid,
                        'xb': res.data.xb,
                        'yhzgx': res.data.yhzgx,
                        'mz': res.data.mz,
                        'lxdh': res.data.lxdh,
                        'xm': res.data.xm,
                        'zjhm': res.data.zjhm,
                        'zzmm': res.data.zzmm,
                        'whcd': res.data.whcd,
                        'zxsqk': res.data.zxsqk,
                        'sxhcxyy': res.data.sxhcxyy,
                        'jkzk': res.data.jkzk,
                        'ldjn': res.data.ldjn,
                        'sfhjpth': res.data.sfhjpth,
                        'sfcjcxjmjbytlbx': res.data.sfcjcxjmjbytlbx,
                        'sfcjsybcylbx': res.data.sfcjsybcylbx,
                        'sfxsncjmzjshbz': res.data.sfxsncjmzjshbz,
                        'sfcjcxjmybyanglbx': res.data.sfcjcxjmybyanglbx,
                        'sfxsrsywbxbt': res.data.sfxsrsywbxbt
                    });
                    form.render();
                }
            });
        }


        // 绑定与户主关系
        form.on('select(yhzgx)', function(data) {
            // 如果选择户主，则检查是否已经存在户主
            var id = $('#fid').val();
            if(data.value == '01') {
                $.get('/yw/jt/getHz',{id : id},function (data) {
                    if(data.data) {
                        alert("该搬迁户已存在户主！");
                        $("#yhzgx").val('');
                        layui.form.render('select');
                    }
                });
            }
        });

        // 绑定提交事件
        form.on('submit(member)', function (data) {
            data.field.fid = $("#fid").val();
            $.ajax({
                url: '/yw/jt/addCy',
                method: 'post',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'JSON',
                success: function (res) {
                    if (res.code = '0') {
                        $("#memberid").val(res.data);
                        layer.msg("✔ 保存成功!", {shift: -1, time: 2000}, function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }
                    else {
                        layer.msg("× 保存失败");
                    }
                },
                error: function (data) {

                }
            });
        });
    });

    return {
        onload: function () {

        }
    };
})();

