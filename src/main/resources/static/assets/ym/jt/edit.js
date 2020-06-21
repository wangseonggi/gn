$(document).ready(function () {
    family_edit.onload();
});
var family_edit = (function () {

    // 首次更新标志
    var initTab1 = 0;
    var initTab2 = 0;
    var initTab3 = 0;
    var initTab4 = 0;
    var initTab5 = 0;

    layui.use(['form', 'element', 'laydate', 'table'], function () {
        var element, laydate, form, table;
        element = layui.element;
        laydate = layui.laydate;
        form = layui.form;
        table = layui.table;

        // 先加载下拉
        var selectArr = ['rhllx', 'wfjb', 'pkhsx', 'rhllx', 'fpyy'];
        $.post("/dm/getDmByNames",
            {param: selectArr},
            function (res) {
                $.each(res.data, function (key, val) {
                    $.each(val, function (index, item) {
                        $("#" + key).append(new Option(item.name, item.dm));
                    });
                });
                layui.form.render('select');

                var id = $("#baseid").val();
                $.ajax({
                    url: '/yw/jt/get',
                    data: {id: id},
                    async: false,
                    success: function (res) {
                        var resData = res.data;
                        $.ajax({
                            url: "/xzqhdm/get",
                            async: false,
                            dataType: 'JSON',
                            success: function (data) {
                                $.each(data, function (index, item) {
                                    $("#shi").append(new Option(item.xzqhmz, item.xzqhdm));
                                });
                            }
                        });

                        $.ajax({
                            url: "/xzqhdm/get",
                            async: false,
                            dataType: 'JSON',
                            data: {sjxzqhdm: resData.shi},
                            success: function (data) {
                                $.each(data, function (index, item) {
                                    $("#xian").append(new Option(item.xzqhmz, item.xzqhdm));
                                });
                            }
                        });

                        $.ajax({
                            url: "/xzqhdm/get",
                            async: false,
                            dataType: 'JSON',
                            data: {sjxzqhdm: resData.xian},
                            success: function (data) {
                                $.each(data, function (index, item) {
                                    $("#zhen").append(new Option(item.xzqhmz, item.xzqhdm));
                                });
                            }
                        });

                        $.ajax({
                            url: "/xzqhdm/get",
                            async: false,
                            dataType: 'JSON',
                            data: {sjxzqhdm: resData.zhen},
                            success: function (data) {
                                $.each(data, function (index, item) {
                                    $("#xzc").append(new Option(item.xzqhmz, item.xzqhdm));
                                });
                            }
                        });

                        form.val('form1', {
                            'shi': resData.shi,
                            'xian': resData.xian,
                            'zhen': resData.zhen,
                            'xzc': resData.xzc,
                            'zrct': resData.zrct,
                            'lxdh': resData.lxdh,
                            'khyh': resData.khyh,
                            'yhkh': resData.yhkh,
                            'pkhsx': resData.pkhsx,
                            'jhtpnd': resData.jhtpnd,
                            'fpnd': resData.fpnd,
                            'fpyy': resData.fpyy,
                            'sfjls': resData.sfjls,
                            'sfbqh': resData.sfbqh,
                            'bqfs': resData.bqfs,
                            'bqdz': resData.bqdz
                        });

                        form.render();
                    }
                });

            }
        );
        // 单独处理
        // 致贫/返贫原因
        $.get("/dm/fpyy", function (res) {
            $.each(res.data, function (index, item) {
                $("#fpyy").append(new Option(item.name, item.dm));
                $("#zpyy1").append(new Option(item.name, item.dm));
                $("#zpyy2").append(new Option(item.name, item.dm));
                $("#zpyy3").append(new Option(item.name, item.dm));
            });
            layui.form.render('select');
        });

        var zpyy1_value, zpyy2_value, zpyy3_value;

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
            elem: '#jtrknd',
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
        // 年度搬迁任务
        laydate.render({
            elem: '#ndbqrw',
            type: 'year',
            trigger: 'click'
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
            data.field.id = $("#additionid").val();
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
            data.field.id = $("#conditionid").val();
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

        // 变更情况说明
        form.on('submit(base5)', function(data) {
            data.field.fid = $("#baseid").val();
            data.field.id = $("#changeid").val();
            $.ajax({
                url: '/yw/jt/addBgqksm',
                method: 'post',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'JSON',
                success: function(res) {
                    if(res.code == '0') {
                        $("#changeid").val(res.data);
                        layer.msg("✔ 保存成功!");
                    }
                    else {
                        layer.msg("× 保存失败");
                    }
                }
            });
        });

        // 其他信息
        form.on('submit(base6)', function (data) {
            console.log(data);
            data.field.baseid = $("#baseid").val();
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

        /* ***** tab切换事件开始 **** */
        element.on('tab(addBaseInfo)', function (data) {
            var fid = $("#baseid").val();
            // 家庭成员
            if (data.index == 1) {
                if (initTab1 == 0) {
                    /**
                     * 家庭成员列表
                     */
                    table.render({
                        elem: '#tableMember'
                        , url: '/yw/jt/getCyList'
                        , toolbar: '#tableMemberToolbar' //开启头部工具栏，并为其绑定左侧模板
                        , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                            title: '提示'
                            , layEvent: 'LAYTABLE_TIPS'
                            , icon: 'layui-icon-tips'
                        }]
                        , where: {
                            fid: fid
                        }
                        , title: '家庭成员表'
                        , cols: [[
                            {field: 'memberid', title: 'ID'}
                            , {field: 'xm', title: '姓名', minWidth: 90}
                            , {field: 'xb', title: '性别'}
                            , {field: 'zjhm', title: '证件号码', minWidth: 195}
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
                            , {field: 'lxdh', title: '联系电话', minWidth: 130}
                            , {title: '操作', toolbar: '#tableMemberBar', minWidth: 130}
                        ]]
                        , page: false
                        , done: function (res, curr, count) {
                        }
                    });

                    initTab1 = 1;
                }
            }
            // 致贫原因
            if (data.index == 2) {
                if (initTab2 == 0) {
                    $.ajax({
                        url: '/yw/jt/getZpyy',
                        data: {fid: fid},
                        dataType: 'JSON',
                        success: function (res) {
                            if (res.data != null) {
                                form.val('form3', {
                                    'additionid': res.data.additionid,
                                    'zpyy1': res.data.zpyy1,
                                    'zpyy2': res.data.zpyy2,
                                    'zpyy3': res.data.zpyy3,
                                    'ncjtrks': res.data.ncjtrks,
                                    'nmjtrks': res.data.nmjtrks
                                });
                            }
                        }
                    });
                    layui.form.render('select');
                    initTab2 = 1;
                }
            }
            // 生产生活条件
            if (data.index == 3) {
                if (initTab3 == 0) {
                    $.ajax({
                        url: '/yw/jt/getScshtj',
                        data: {fid: fid},
                        dataType: 'JSON',
                        success: function (res) {
                            if (res.data == null)
                                return;
                            // 单独处理燃料的其他项
                            if (res.data.zyrllx == 5) {
                                $("#label_jtrlmc").css("color", '');
                                $("#jtrlmc").removeClass("layui-disabled");
                                $("#jtrlmc").attr("disabled", false);
                                $("#jtrlmc").attr("lay-verify", "required");
                            }
                            form.val('form4', {
                                'conditionid': res.data.conditionid,
                                'gdmj': res.data.gdmj,
                                'ldmj': res.data.ldmj,
                                'tghlmj': res.data.tghlmj,
                                'lgmj': res.data.lgmj,
                                'mcdmj': res.data.mcdmj,
                                'smmj': res.data.smmj,
                                'sfjrnmzyhzs': res.data.sfjrnmzyhzs,
                                'sfyltqybz': res.data.sfyltqybz,
                                'sfycyzfdtrdd': res.data.sfycyzfdtrdd,
                                'sftshyd': res.data.sftshyd,
                                'sftgbds': res.data.sftgbds,
                                'sfjjaqyys': res.data.sfjjaqyys,
                                'sfywscs': res.data.sfywscs,
                                'yczgljl': res.data.yczgljl,
                                'rhllx': res.data.rhllx,
                                'zfmj': res.data.zfmj,
                                'sfwf': res.data.sfwf,
                                'wfjb': res.data.wfjb,
                                'wgnd': res.data.wgnd,
                                'zyrllx': res.data.zyrllx,
                                'jtrlmc': res.data.jtrlmc
                            });
                            form.render();
                        }
                    });
                    initTab3 = 1;
                }
            }
            // 变更说明
            if(data.index == 4) {
                if(initTab4 == 0) {
                    $.ajax({
                        url: '/yw/jt/getBgqksm',
                        data: {fid: fid},
                        dataType: 'JSON',
                        success: function(res){
                            form.val('form5',{
                                'changeid': res.data.changeid,
                                'nr': res.data.nr
                            });
                            form.render();
                        }
                    });
                }
                initTab4 = 1;
            }

            // 新增字段
            if (data.index == 5) {
                if (initTab5 == 0) {
                    $.ajax({
                        url: '/yw/jt/getNew',
                        data: {id: fid},
                        dataType: 'JSON',
                        success: function (res) {
                            if (res.data == null)
                                return;
                            form.val('form6', {
                                'azd': res.data.azd,
                                'ldfh': res.data.ldfh,
                                'ndbqrw': res.data.ndbqrw,
                                'qcdxxdz': res.data.qcdxxdz,
                                'qcdlx': res.data.qcdlx,
                                'jtsyrk': res.data.jtsyrk,
                                'zfmj': res.data.zfmj,
                                'zczj': res.data.zczj,
                                'bqrzsj': res.data.bqrzsj,
                                'sjrzsj': res.data.sjrzsj,
                                'yyzf': res.data.yyzf,
                                'yyzfmj': res.data.yyzfmj,
                                'ccjf': res.data.ccjf,
                                'bhzfzl': res.data.bhzfzl,
                                'ccsj': res.data.ccsj,
                                'ccjfmj': res.data.ccjfmj,
                                'dxcfjl': res.data.dxcfjl,
                                'fkflqk': res.data.fkflqk,
                                'cyfcxm': res.data.cyfcxm,
                                'nnhdcyfczj': res.data.nnhdcyfczj,
                                'tpqk': res.data.tpqk,
                                'hkqy': res.data.hkqy,
                                'fczblhq': res.data.fczblhq
                            });
                            form.render();
                        }
                    });
                    initTab5 = 1;
                }
            }

            // console.log(this); //当前Tab标题所在的原始DOM元素
            // console.log(data.index); //得到当前Tab的所在下标
            // console.log(data.elem); //得到当前的Tab大容器
            // console.log($("#"))
        });
        /* ***** tab切换事件结束 **** */

        form.on('select(shi)', function (data) {
            $.get("/xzqhdm/get", {sjxzqhdm: data.value}, function (data) {
                $("#xian").empty();
                $.each(data, function (index, item) {
                    $("#xian").append(new Option(item.xzqhmz, item.xzqhdm));
                });
                $("#zhen").empty();
                $("#xzc").empty();
                layui.form.render('select');
                // layui.form.render('select', 'xian');
            });
        });


        //
        form.on('select(xian)', function (data) {
            $.get("/xzqhdm/get", {sjxzqhdm: data.value}, function (data) {
                $("#zhen").empty();
                $.each(data, function (index, item) {
                    $("#zhen").append(new Option(item.xzqhmz, item.xzqhdm));
                });
                $("#xzc").empty();
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

        form.on('select(xzc)', function (data) {
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


        // 新增家庭成员
        table.on('toolbar(tableMember)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    parent.layer.open({
                        title: '新增家庭成员',
                        type: 2,
                        area: ['70%', '60%'], //宽高
                        content: '/yw/jt/cyAdd?id=0',
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
            }
            else if (obj.event === 'del') {
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

    function settabheight() {
        var winheight = $(window).height();
        var tabheight = winheight - 95;
        $("#t1").height(tabheight);
        $(".layui-tab-content").css("overflow", "auto");
        $(".layui-tab-content").height(tabheight - 60);
    };
    return {
        onload: function () {

        }
    };
})();



