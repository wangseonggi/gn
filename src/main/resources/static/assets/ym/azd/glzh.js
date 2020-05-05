$(document).ready(function () {
    bfgx.onload();
});
var bfgx = (function () {

    layui.use(['form', 'element', 'laydate', 'table'], function () {
        var element, laydate, form, table;
        form = layui.form;

        var fwid = $('#id').val();
        // 先尝试加载已存在的住戶信息
        // 如果有，则列出关系，否则初始化表格
        $.post('/yw/azd/getGLZHXX', {id: fwid}, function (res) {;
            if (res.data) {
                // 清空
                $("tbody").html('<tr id="fir">' + $("#fir").html() + '</tr>');
                var addedTr = "<tr class='bfrxx'>" +
                    "<td class='fp_nrbt'><input name='jtid' class='layui-input' lay-filter='id' value='" + res.data.id + "'/></td>" +
                    "<td class='fp_nrxt'><input name='xm' class='layui-input' lay-filter='xm' value='" + res.data.xm + "' readonly/></td>" +
                    "<td class='fp_nrxt'><input name='zjhm' class='layui-input' lay-filter='zjhm' value='" + res.data.zjhm + "' readonly/></td>" +
                    "<td class='fp_nrxt'><input name='jtrks' class='layui-input' lay-filter='num' value='" + res.data.num + "' readonly/></td>" +
                    "<td class='fp_nrxt'><input name='pkhsx' class='layui-input' lay-filter='pkhsx' value='" + res.data.pkhsx + "' readonly/></td>" +
                    "</tr>";
                    $("tbody").append(addedTr);
            }
            else {
                // 初始化一条空的
                addRow();
            }
            blur();
        }).error(function(xhr, status, info) {
            alert(xhr.status);
        });

        form.on('submit(member)', function (data) {
            var bqhids = [];
            // 逐行判断是否有空
            var trs = $('tbody').find('input[name="jtid"]');
            $.each(trs, function (i, n) {
                if (!$(n).val()) {
                    $(n).parent().parent().remove();
                }
                else {
                    bqhids.push(parseInt($(n).val()));
                }
            });
            // 如果没有指定，则加一行，结束
            if (bqhids.length == 0) {
                layer.msg("无数据提交");
                addRow();
                return;
            }

            // 有指定则更新
            $.ajax({
                url: '/yw/azd/rz',
                data: {fwid: $("#id").val(), fid: $("[name='jtid']").val()},
                method: "post",
                dataType: 'JSON',
                success: function (res) {
                    layer.msg(res.msg);
                },
                error: function () {
                    layer.msg("系统错误，请联系管理员。");
                }
            });
        });
    });

    function addRow() {
        var addedTr = "<tr class='bfrxx'>" +
            "<td class='fp_nrbt'><input name='jtid' class='layui-input' lay-filter='id' /></td>" +
            "<td class='fp_nrxt'><input name='xm' class='layui-input' lay-filter='xm'  readonly/></td>" +
            "<td class='fp_nrxt'><input name='zjhm' class='layui-input' lay-filter='zjhm'  readonly/></td>" +
            "<td class='fp_nrxt'><input name='jtrks' class='layui-input' lay-filter='jtrks'  readonly/></td>" +
            "<td class='fp_nrxt'><input name='pkhsx' class='layui-input' lay-filter='pkhsx'  readonly/></td>" +
            "</tr>";
        $("tbody").append(addedTr);
        blur();
    }

    function delRow(obj) {
        var ryRowNum = $(obj).parents('tbody').children('.bfrxx').size();
        if (ryRowNum == 1) {
            layer.msg('最后一行无法删除');
            return false;
        }
        $(obj).parents('tr').remove();
    };

    // 绑定失去焦点事件
    var blur = function () {
        $("[name='jtid']").blur(function () {
            var obj = $(this);
            var jtid = obj.val();
            if (!jtid) {
                obj.val('');
                obj.parent().parent().find('input[name="xm"]').val('');
                obj.parent().parent().find('input[name="zjhm"]').val('');
                obj.parent().parent().find('input[name="jtrks"]').val('');
                obj.parent().parent().find('input[name="pkhsx"]').val('');
                return;
            }
            $.post('/yw/bfr/getBqh', {jtid: parseInt(jtid)}, function (res) {
                if (res.data != null) {
                    obj.parent().parent().find('input[name="jtid"]').val(res.data.id);
                    obj.parent().parent().find('input[name="xm"]').val(res.data.xm);
                    obj.parent().parent().find('input[name="zjhm"]').val(res.data.zjhm);
                    obj.parent().parent().find('input[name="jtrks"]').val(res.data.num);
                    obj.parent().parent().find('input[name="pkhsx"]').val(res.data.pkhsx);
                }
                else {
                    obj.val('');
                    obj.parent().parent().find('input[name="xm"]').val('');
                    obj.parent().parent().find('input[name="zjhm"]').val('');
                    obj.parent().parent().find('input[name="jtrks"]').val('');
                    obj.parent().parent().find('input[name="pkhsx"]').val('');
                }
            });
        });
    };

    return {
        onload: function () {

        },
        addRow: function (obj) {
            addRow(obj);
        },
        delRow: function (obj) {
            delRow(obj);
        }
    };
})();



