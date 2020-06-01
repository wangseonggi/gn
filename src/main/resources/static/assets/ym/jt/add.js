$(document).ready(function () {
    family_add.onload();
});
var family_add = (function () {

    layui.use(['form', 'element', 'laydate', 'table'], function () {
        var element, laydate, form, table;
        element = layui.element;
        laydate = layui.laydate;
        form = layui.form;
        table = layui.table;


        var zpyy1_value, zpyy2_value, zpyy3_value;

        // 提交限制
        element.on('tab(addBaseInfo)', function (data) {
            if (!$("#baseid").val() && data.index != 0) {
                layer.open('请先保存基本信息', {btn: ['确定']}, function () {
                });
                layer.open({
                    title: '提示',
                    skin: 'layui-layer-lan',
                    content: '请先提交基本信息', btn: ['确定'],
                    end: function (index, layero) {
                        element.tabChange('addBaseInfo', '1');
                    }
                });
            }
        });

        laydate.render({
            elem: '#jhtpnd',
            type: 'year',
            trigger: 'click'
        });

        laydate.render({
            elem: '#fpnd',
            type: 'year',
            trigger: 'click'
        });

        laydate.render({
            elem: '#bqrzsj',
            trigger: 'click'
        });

        laydate.render({
            elem: '#sjrzsj',
            trigger: 'click'
        });

        laydate.render({
            elem: '#ccsj',
            type: 'month',
            trigger: 'click'
        });

        // 危改年度
        laydate.render({
            elem: '#wgnd',
            type: 'year',
            trigger: 'click'
        });

        // 加载js初始化市的下拉框，待优化
        $.get("/xzqhdm/get", function (data) {
            $.each(data, function (index, item) {
                $("#shi").append(new Option(item.xzqhmz, item.xzqhdm));
            });
            layui.form.render('select');
        });

        // 贫困户属性
        $.get("/dm/pkhsx", function (res) {
            $.each(res.data, function (index, item) {
                $("#pkhsx").append(new Option(item.name, item.dm));
            });
            layui.form.render('select');
        });

        // 返贫原因
        $.get("/dm/fpyy", function (res) {
            $.each(res.data, function (index, item) {
                $("#fpyy").append(new Option(item.name, item.dm));
                $("#zpyy1").append(new Option(item.name, item.dm));
                $("#zpyy2").append(new Option(item.name, item.dm));
                $("#zpyy3").append(new Option(item.name, item.dm));
            });
            layui.form.render('select');
        });

        // 入戶路类型
        $.get("/dm/rhllx", function (res) {
            $.each(res.data, function (index, item) {
                $("#rhllx").append(new Option(item.name, item.dm));
            });
            layui.form.render('select');
        });

        // 危房级别
        $.get("/dm/wfjb", function (res) {
            $.each(res.data, function (index, item) {
                $("#wfjb").append(new Option(item.name, item.dm));
            });
            layui.form.render('select');
        });

        // 主要燃料类型
        $.get("/dm/zyrllx", function (res) {
            $.each(res.data, function (index, item) {
                $("#zyrllx").append(new Option(item.name, item.dm));
            });
            layui.form.render('select');
        });

        /* ***** 表单提交开始 **** */

        // 基本信息
        form.on('submit(base1)', function (data) {
            $.ajax({
                url: '/yw/jt/add',
                method: 'post',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'JSON',
                // beforeSend: function (xhr) {
                //     xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                // },
                success: function (res) {
                    console.log(res);
                    if (res.code == '0') {
                        if (res.data) {
                            $("#baseid").val(res.data);
                        }
                        layer.msg("✔ 保存成功!");
                    }
                    else {
                        layer.msg("× 保存失败");
                    }
                },
                error: function (data) {

                }
            });
        });

        // 致贫原因及家庭人口情况
        form.on('submit(base3)', function (data) {
            data.field.fid = $("#baseid").val();
            $.ajax({
                url: '/yw/jt/addZpyy',
                method: 'post',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'JSON',
                // beforeSend: function (xhr) {
                //     xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                // },
                success: function (res) {
                    if (res.code == '0') {
                        console.log(res.data);
                        $("#additionid").val(res.data);
                        layer.msg("✔ 保存成功!");
                    }
                    else {
                        layer.msg("× 保存失败");
                    }
                },
                error: function (data) {

                }
            });
        });

        // 生产生活条件表单
        form.on('submit(base4)', function (data) {
            data.field.fid = $("#baseid").val();
            $.ajax({
                url: '/yw/jt/addScshtj',
                method: 'post',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'JSON',
                // beforeSend: function (xhr) {
                //     xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                // },
                success: function (res) {
                    if (res.code == '0') {
                        $("#conditionid").val(res.data);
                        layer.msg("✔ 保存成功!");
                    }
                    else {
                        layer.msg("× 保存失败");
                    }
                },
                error: function (data) {

                }
            });
        });

        // 其他信息
        form.on('submit(base6)', function (data) {
            data.field.baseid = $("#baseid").val();
            // console.log(data.field);
            $.ajax({
                url: '/yw/jt/updateQTXX',
                method: 'post',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'JSON',
                // beforeSend: function (xhr) {
                //     xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                // },
                success: function (res) {
                    if (res.code == '0') {
                        $("#conditionid").val(res.data);
                        layer.msg("✔ 保存成功!");
                    }
                    else {
                        layer.msg("× 保存失败");
                    }
                },
                error: function (data) {

                }
            });
        });
        /* ***** 表单提交结束 **** */


        form.on('select(shi)', function (data) {
            $.get("/xzqhdm/get", {sjxzqhdm: data.value}, function (data) {
                $("#xian").empty();
                $.each(data, function (index, item) {
                    $("#xian").append(new Option(item.xzqhmz, item.xzqhdm));
                });
                $("#zhen").empty();
                layui.form.render('select');
                // layui.form.render('select', 'xian');
            });
        });
        form.on('select(xian)', function (data) {
            $.get("/xzqhdm/get", {sjxzqhdm: data.value}, function (data) {
                $("#zhen").empty();
                $.each(data, function (index, item) {
                    $("#zhen").append(new Option(item.xzqhmz, item.xzqhdm));
                });
                layui.form.render('select');
            });
        });
        form.on('select(zhen)', function (data) {
            $.get("/xzqhdm/get", {sjxzqhdm: data.value}, function (data) {
                $("#xzc").empty();
                $.each(data, function (index, item) {
                    $("#xzc").append(new Option(item.xzqhmz, item.xzqhdm));
                });
                layui.form.render('select');
            });
        });

        // 致贫原因1的事件
        form.on("select(zpyy1)", function (data) {
            zpyy1_value = data.value;
            if (data.value == '') {
                // 将选型二三恢复
                $("#zpyy2 option").attr("disabled", false);
                $("#zpyy3 option").attr("disabled", false);
                // 第一项
                $('#zpyy2').val('');
                $('#zpyy3').val('');
                layui.form.render('select');
                return;
            }
            $("#zpyy2 option").attr("disabled", false);
            $("#zpyy3 option").attr("disabled", false);
            // 以1为主，如果1选择了，则其余两个无法选择，存在冲突则以1为准
            // 如果2的值跟1相等则2还原
            if ($("#zpyy2").val() == data.value) {
                $('#zpyy2').val('');
            }
            // 将2中1已选择的禁用
            $('#zpyy2').find("option[value=" + data.value + "]").attr('disabled', true);

            // 如果3的值与1相等则还原三
            if ($("#zpyy3").val() == data.value) {
                $('#zpyy3').val('');
            }
            // 将3中1已选择的禁用
            $('#zpyy3').find("option[value=" + data.value + "]").attr('disabled', true);
            // 如果2不为空，则还需要将2已选择的值禁用
            if (zpyy2_value != '') {
                $('#zpyy3').find("option[value=" + zpyy2_value + "]").attr('disabled', true);
            }
            layui.form.render('select');
        });

        // 致贫原因2的事件
        form.on("select(zpyy2)", function (data) {
            zpyy2_value = data.value;
            // 如果2不选择，则3置空
            if (data.value == '') {
                $("#zpyy3 option").attr("disabled", false);
                if (zpyy1_value != '') {
                    $('#zpyy3').find("option[value=" + zpyy1_value + "]").attr('disabled', true);
                    $('#zpyy3').val('');
                }
                layui.form.render('select');
                return;
            }
            $("#zpyy3 option").attr("disabled", false);
            if (zpyy1_value != '') {
                $('#zpyy3').find("option[value=" + zpyy1_value + "]").attr('disabled', true);
            }

            if ($("#zpyy3").val() == data.value) {
                $('#zpyy3').val('');
            }
            // 将3中2已选择的禁用
            $('#zpyy3').find("option[value=" + data.value + "]").attr('disabled', true);
            layui.form.render('select');
        });


        // 主要燃料类型
        // 如选择“其他”则放开“具体燃料名称”，反之禁用
        form.on("select(zyrllx)", function (data) {
            if (data.value == 5) {
                $("#label_jtrlmc").css("color", '');
                $("#jtrlmc").removeClass("layui-disabled");
                $("#jtrlmc").attr("disabled", false);
                $("#jtrlmc").attr("lay-verify", "required");
            }
            else {
                $("#label_jtrlmc").css("color", 'gainsboro');
                $("#jtrlmc").addClass("layui-disabled");
                $("#jtrlmc").val('');
                $("#jtrlmc").attr("disabled", true);
                $("#jtrlmc").removeAttr("lay-verify", "required");
            }
            layui.form.render();
        });

        /**
         * 家庭成员列表
         */
        table.render({
            elem: '#tableMember',
            url: '/yw/jt/getCyList',
            page: false,
            toolbar: '#tableMemberToolbar',
            limits: [10, 15, 20, 25],
            limit: 10,
            even: true,
            title: '家庭成员表',
            cols: [[
                 {field: 'memberid', title: 'ID'}
                , {field: 'xm', title: '姓名',minWidth:100}
                , {field: 'xb', title: '性别'}
                , {field: 'zjhm', title: '证件号码', minWidth:180}
                , {field: 'yhzgx', title: '与户主关系'}
                , {field: 'mz', title: '民族'}
                , {field: 'zzmm', title: '政治面貌'}
                , {field: 'whcd', title: '文化程度'}
                , {field: 'zxsqk', title: '在校生情况'}
                , {field: 'sxhcxyy', title: '失学或辍学原因'}
                , {field: 'jkzk', title: '健康状况'}
                , {field: 'ldjn', title: '劳动技能'}
                , {field: 'sfhjpth', title: '是否会讲普通话'}
                , {field: 'sfcjcxjmjbytlbx', title: '是否参加城乡居民医疗保险'}
                , {field: 'sfcjsybcylbx', title: '是否参加商业补充医疗保险'}
                , {field: 'sfxsncjmzjshbz', title: '是否享受农村居民最低生活保障'}
                , {field: 'sfcjcxjmybyanglbx', title: '是否参加城乡居民基本养老保险'}
                , {field: 'sfxsrsywbxbt', title: '是否享受人身意外保险补贴'}
                , {field: 'lxdh', title: '联系电话',minWidth:130}
                , {title: '操作', toolbar: '#tableMemberBar', minWidth:130}
            ]]
            , done: function (res, curr, count) {

                $("[data-field='xb']").children().each(function () {
                    if ($(this).text() == 1) {
                        $(this).text("男")
                    } else if ($(this).text() == 0) {
                        $(this).text("女");
                    }
                });


            },
            skin: 'row'
        });


        // 新增家庭成员按钮事件
        table.on('toolbar(tableMember)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    parent.layer.open({
                        title: '新增家庭成员',
                        type: 2,
                        skin: 'layui-layer-rim', //加上边框
                        area: ['70%', '60%'], //宽高
                        content: '/yw/jt/cyAdd',
                        success: function (layero, index) {
                            // 兄弟窗口传值
                            var baseid = $("#baseid").val();
                            window.parent.$("#layui-layer-iframe" + index).contents().find('.fid').val(baseid);
                        },
                        end: function () {
                            layui.table.reload('tableMember', {
                                where: {
                                    fid: $("#baseid").val()
                                }
                            });
                        }
                    });
                    break;
                case 'del':
                    var data = checkStatus.data;
                    var ids = [];
                    $.each(data, function (i, n) {
                        ids.push(n.id);
                    });
                    layer.alert(JSON.stringify(ids));
                    break;
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
            }
            ;
        });

        //监听工具条
        table.on('tool(tableMember)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.id + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('是否删除该记录？', function (index) {
                    $.ajax({
                        url: '/yw/jt/delCy',
                        method: 'get',
                        data: {id: obj.data.memberid},
                        contentType: "application/json",
                        success: function (data) {
                            obj.del();
                            layer.close(index);
                        },
                        error: function (data) {
                            layer.msg("删除失败!");
                        }
                    });
                });
            } else if (obj.event === 'edit') {
                parent.layer.open({
                    title: '编辑家庭成员基本信息',
                    type: 2,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['70%', '60%'], //宽高
                    content: '/yw/jt/cyAdd?id=' + data.memberid,
                    end: function () {
                        layui.table.reload('tableMember', {
                            where: {
                                fid: $("#baseid").val()
                            }
                        });
                    }
                });
            }
        });
    });

    return {
        onload: function () {

        }
    };
})();



