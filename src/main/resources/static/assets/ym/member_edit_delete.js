$(document).ready(function () {
    member_edit.onload();
});
let member_edit = (function () {

    layui.use(['form'], function () {

        let form = layui.form;

        layui.form.render('select');

        // 提交
        form.on('submit(member)', function (data) {
            data.field.fid = $("#fid").val();
            $.ajax({
                url: '/yw/jt/addCy',
                method: 'post',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'JSON',
                // beforeSend: function (xhr) {
                //     xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                // },
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
                },
                error: function (data) {

                }
            });
        });

        $.ajax({
            url: "/yw/jt/getCy",
            method: 'get',
            data: {id: $("#memberid").val()},
            contentType: "application/json",
            success: function (res) {
                // 初始化表单
                console.log(res.data);

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
                    'zxszk': res.data.zxszk,
                    'sxyy': res.data.sxyy,
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
            },
        });
    });


    return {
        onload: function () {
        }
    };
})();

