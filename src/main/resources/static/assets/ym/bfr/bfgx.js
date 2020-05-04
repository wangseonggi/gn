$(document).ready(function () {
    bfgx.onload();
});
var bfgx = (function () {



    layui.use(['form', 'element', 'laydate', 'table'], function () {
        var element, laydate, form, table;
        form = layui.form;

        var bfrid = $('#id').val();
        // 先尝试加载已有的帮扶关系
        // 如果有，则列出关系，否则初始哈表格
        $.post('/yw/bfr/getBBfrXX', {bfrid: bfrid}, function (res) {
            if (res.data.length > 0) {
                // 清空
                $("tbody").html('<tr id="fir">' + $("#fir").html() + '</tr>');
                $.each(res.data, function (i, n) {
                    var addedTr = "<tr class='bfrxx'>" +
                        "<td class='fp_nrbt'><input name='jtid' class='layui-input' lay-filter='jtid' value='" + n.id + "'/></td>" +
                        "<td class='fp_nrxt'><input name='xm' class='layui-input' lay-filter='xm' value='" + n.xm + "' readonly/></td>" +
                        "<td class='fp_nrxt'><input name='zjhm' class='layui-input' lay-filter='zjhm' value='" + n.zjhm + "' readonly/></td>" +
                        "<td class='fp_nrxt'><input name='jtrks' class='layui-input' lay-filter='jtrks' value='" + n.num + "' readonly/></td>" +
                        "<td class='fp_nrxt'><input name='pkhsx' class='layui-input' lay-filter='pkhsx' value='" + n.pkhsx + "' readonly/></td>" +
                        "<td class='fp_nrbt'><div style='min-width: 58px;width: 100%;overflow: hidden; text-align: center'><a class='layui-btn layui-btn-xs' onclick='bfgx.addRow(this);'>+</a><a class='layui-btn layui-btn-primary layui-btn-xs' onclick='bfgx.delRow(this);'>-</a></td></div></tr>";
                    $("tbody").append(addedTr);
                });
            }
            else {
                // 初始化一条空的
                addRow();
            }
            blur();
        });

        form.on('submit(member)', function (data) {
            var bqhids = [];
            // 逐行判断是否有空
            var trs = $('tbody').find('input[name="jtid"]');
            $.each(trs, function(i, n){
                if(!$(n).val()) {
                    $(n).parent().parent().remove();
                }
                else {
                    bqhids.push(parseInt($(n).val()));
                }
            });
            // 如果没有指定，则加一行，结束
            if(bqhids.length == 0) {
                layer.msg("无数据提交");
                addRow();
                return;
            }

            // 有指定则更新
            $.ajax({
                url : '/yw/bfr/zd',
                data: {bfrid: parseInt(bfrid), bqhids : bqhids},
                method: "post",
                dataType: 'JSON',
                success : function(res) {
                    layer.msg(res.msg);
                },
                error : function() {
                    layer.msg("系统错误，请联系管理员。");
                }
            });
        });
    });

    function addRow(obj) {
        var addedTr = "<tr class='bfrxx'>" +
            "<td class='fp_nrbt'><input name='jtid' class='layui-input' lay-filter='jtid' /></td>" +
            "<td class='fp_nrxt'><input name='xm' class='layui-input' lay-filter='xm'  readonly/></td>" +
            "<td class='fp_nrxt'><input name='zjhm' class='layui-input' lay-filter='zjhm'  readonly/></td>" +
            "<td class='fp_nrxt'><input name='jtrks' class='layui-input' lay-filter='jtrks'  readonly/></td>" +
            "<td class='fp_nrxt'><input name='pkhsx' class='layui-input' lay-filter='pkhsx'  readonly/></td>" +
            "<td class='fp_nrbt'><div style='min-width: 58px;width: 100%;overflow: hidden; text-align: center'><a class='layui-btn layui-btn-xs' onclick='bfgx.addRow(this);'>+</a><a class='layui-btn layui-btn-primary layui-btn-xs' onclick='bfgx.delRow(this);'>-</a></td>"+
            "</tr>";
        if (obj)
            $(obj).parents('tr').after(addedTr);
        else
            $("#fir").after(addedTr);

        blur();
    };

    function delRow(obj) {
        var ryRowNum = $(obj).parents('tbody').children('.bfrxx').size();
        if (ryRowNum == 1) {
            layer.msg('最后一行无法删除');
            return false;
        }
        $(obj).parents('tr').remove();
    };

    // 绑定失去焦点事件
    var blur = function() {
        $("[name='jtid']").blur(function () {
            var obj = $(this);
            var jtid = obj.val();
            if(!jtid) {
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
        addRow : function (obj) {
            addRow(obj);
        },
        delRow : function (obj) {
            delRow(obj);
        }
    };
})();



