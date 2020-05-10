$(document).ready(function () {
    family_edit.onload();
});
let family_edit = (function () {

    // 首次更新标志
    let initTab1 = 0;
    let initTab2 = 0;
    let initTab3 = 0;

    let initTab5 = 0;

    layui.use(['form', 'element', 'laydate', 'table'], function () {
        var element, laydate, form, table;
        element = layui.element;
        laydate = layui.laydate;
        form = layui.form;
        table = layui.table;


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

        // 加载js初始化市的下拉框，待优化
        // $.get("/getRegion", function (data) {
        //     $.each(data, function (index, item) {
        //         $("#city").append(new Option(item.name, item.id));
        //     });
        //     layui.form.render('select');
        // });

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
                    if (res.code == 0) {
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
                    if (res.code = '0') {
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
                    if (res.code = '0') {
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
        element.on('tab(addBaseInfo)', function(data){
            let fid = $("#baseid").val();
            // 家庭成员
            if(data.index == 1) {
                if(initTab1 == 0) {
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
                            , {field: 'xm', title: '姓名',minWidth:90}
                            , {field: 'xb', title: '性别'}
                            , {field: 'zjhm', title: '证件号码',minWidth:195}
                            , {field: 'yhzgx', title: '与户主关系'}
                            , {field: 'mz', title: '民族'}
                            , {field: 'zzmm', title: '政治面貌'}
                            , {field: 'whcd', title: '文化程度'}
                            , {field: 'zxszk', title: '在校生情况'}
                            , {field: 'sxyy', title: '失学或辍学原因'}
                            , {field: 'jkzk', title: '健康状况'}
                            , {field: 'ldjn', title: '劳动技能'}
                            , {field: 'sfhjpth', title: '是否会讲普通话'}
                            , {field: 'sfcjcxjmjbytlbx', title: '是否参加城乡居民医疗保险'}
                            , {field: 'sfcjsybcylbx', title: '是否参加商业补充医疗保险'}
                            , {field: 'sfxsncjmzjshbz', title: '是否享受农村居民最低生活保障'}
                            , {field: 'sfcjcxjmybyanglbx', title: '是否参加农村居民基本养老保险'}
                            , {field: 'sfxsrsywbxbt', title: '是否享受人身意外保险补贴'}
                            , {field: 'lxdh', title: '联系电话',minWidth:130}
                            , {title: '操作', toolbar: '#tableMemberBar', minWidth:130}
                        ]]
                        , page: false
                        , done: function (res, curr, count) {

                            $("[data-field='xb']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("男")
                                } else if ($(this).text() == 0) {
                                    $(this).text("女");
                                }
                            });

                            $("[data-field='yhzgx']").children().each(function () {
                                var s = {'01':'户主', '10':'配偶', '20':'之子', '27':'养子或继子', '28':'女婿', '30':'之女', '37':'养女或继女', '38':'儿媳', '41':'孙子', '42':'孙女', '43':'外孙子', '44':'外孙女', '47':'曾孙子或外曾孙子', '48':'曾孙女或外曾孙女', '51':'父亲', '52':'母亲', '53':'公公', '54':'婆婆', '55':'岳父', '56':'岳母', '57':'继父或养父', '58':'继母或养母', '59':'其他父母关系', '61':'祖父', '62':'祖母', '63':'外祖父', '64':'外祖母', '66':'曾祖父', '67':'曾祖母', '68':'配偶的曾祖父母、外曾祖父母', '69':'其他祖父母或外祖父母关系', '71':'兄', '72':'嫂', '73':'弟', '74':'弟媳', '75':'姐姐', '76':'姐夫', '77':'妹妹', '78':'妹夫', '80':'其他', '81':'伯父', '82':'伯母', '83':'叔父', '84':'叔母', '85':'舅父', '86':'舅母', '87':'姨父', '88':'姨母', '89':'姑父', '90':'姑母', '93':'侄子', '94':'侄女', '95':'外甥', '96':'外甥女',};
                                $(this).text(s[$(this).text()]);
                            });

                            $("[data-field='mz']").children().each(function () {
                                var s = {'01' :'汉族', '02' :'蒙古族', '03' :'回族', '04' :'藏族', '05' :'维吾尔族', '06' :'苗族', '07' :'彝族', '08' :'壮族', '09' :'布依族', '10' :'朝鲜族', '11' :'满族', '12' :'侗族', '13' :'瑶族', '14' :'白族', '15' :'土家族', '16' :'哈尼族', '17' :'哈萨克族', '18' :'傣族', '19' :'黎族', '20' :'傈僳族', '21' :'佤族', '22' :'畲族', '23' :'高山族', '24' :'拉祜族', '25' :'水族', '26' :'东乡族', '27' :'纳西族', '28' :'景颇族', '29' :'柯尔克孜族', '30' :'土族', '31' :'达斡尔族', '32' :'仫佬族', '33' :'羌族', '34' :'布朗族', '35' :'撒拉族', '36' :'毛难族', '37' :'仡佬族', '38' :'锡伯族', '39' :'阿昌族', '40' :'普米族', '41' :'塔吉克族', '42' :'怒族', '43' :'乌孜别克族', '44' :'俄罗斯族', '45' :'鄂温克族', '46' :'崩龙族', '47' :'保安族', '48' :'裕固族', '49' :'京族', '50' :'塔塔尔族', '51' :'独龙族', '52' :'鄂伦春族', '53' :'赫哲族', '54' :'门巴族', '55' :'珞巴族', '56' :'基诺族', '57' :'其他', '58' :'外国血统中国人士'};
                                $(this).text(s[$(this).text()]);
                            });

                            $("[data-field='zzmm']").children().each(function () {
                                var s = {'01':'中共党员', '02':'中共预备党员', '03':'共青团员', '12':'无党派民主人士', '13':'群众'};
                                $(this).text(s[$(this).text()]);
                            });

                            $("[data-field='whcd']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("文盲")
                                } else if ($(this).text() == 2) {
                                    $(this).text("小学");
                                } else if ($(this).text() == 3) {
                                    $(this).text("初中");
                                } else if ($(this).text() == 4) {
                                    $(this).text("高中");
                                } else if ($(this).text() == 5) {
                                    $(this).text("本科");
                                }
                            });

                            $("[data-field='zxszk']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("非在校生")
                                } else if ($(this).text() == 2) {
                                    $(this).text("学前教育");
                                } else if ($(this).text() == 3) {
                                    $(this).text("七年级");
                                } else if ($(this).text() == 4) {
                                    $(this).text("八年级");
                                } else if ($(this).text() == 5) {
                                    $(this).text("九年级");
                                } else if ($(this).text() == 6) {
                                    $(this).text("高一");
                                } else if ($(this).text() == 7) {
                                    $(this).text("高二");
                                } else if ($(this).text() == 8) {
                                    $(this).text("高三");
                                } else if ($(this).text() == 9) {
                                    $(this).text("中职一");
                                } else if ($(this).text() == 0){
                                    $(this).text("");
                                }
                            });

                            $("[data-field='sxyy']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("因病")
                                } else if ($(this).text() == 2) {
                                    $(this).text("因疾");
                                } else if ($(this).text() == 3) {
                                    $(this).text("厌学");
                                } else if ($(this).text() == 4) {
                                    $(this).text("其他");
                                } else if ($(this).text() == 0){
                                    $(this).text("");
                                }
                            });

                            $("[data-field='jkzk']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("健康")
                                } else if ($(this).text() == 2) {
                                    $(this).text("残疾");
                                } else if ($(this).text() == 3) {
                                    $(this).text("大病");
                                } else if ($(this).text() == 4) {
                                    $(this).text("长期慢性病");
                                } else if ($(this).text() == 0){
                                    $(this).text("");
                                }
                            });

                            $("[data-field='ldjn']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("普通劳动力")
                                } else if ($(this).text() == 2) {
                                    $(this).text("技能劳动力");
                                } else if ($(this).text() == 3) {
                                    $(this).text("弱劳动力或半劳动力");
                                } else if ($(this).text() == 4) {
                                    $(this).text("丧失劳动力");
                                } else if ($(this).text() == 4) {
                                    $(this).text("无劳动力");
                                } else if ($(this).text() == 0){
                                    $(this).text("");
                                }
                            });

                            $("[data-field='sfhjpth']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("是")
                                } else if ($(this).text() == 0) {
                                    $(this).text("否");
                                }
                            });

                            $("[data-field='sfcjcxjmjbytlbx']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("是")
                                } else if ($(this).text() == 0) {
                                    $(this).text("否");
                                }
                            });

                            $("[data-field='sfxsncjmzjshbz']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("是")
                                } else if ($(this).text() == 0) {
                                    $(this).text("否");
                                }
                            });

                            $("[data-field='sfcjsybcylbx']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("是")
                                } else if ($(this).text() == 0) {
                                    $(this).text("否");
                                }
                            });

                            $("[data-field='sfcjcxjmybyanglbx']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("是")
                                } else if ($(this).text() == 0) {
                                    $(this).text("否");
                                }
                            });

                            $("[data-field='sfxsrsywbxbt']").children().each(function () {
                                if ($(this).text() == 1) {
                                    $(this).text("是")
                                } else if ($(this).text() == 0) {
                                    $(this).text("否");
                                }
                            });
                        }
                    });

                    initTab1 = 1;
                }
            }
            // 致贫原因
            if(data.index == 2) {
                if(initTab2 == 0) {
                    $.ajax({
                        url : '/yw/jt/getZpyy',
                        data : {fid : fid},
                        dataType: 'JSON',
                        success : function(res) {
                            // console.log(res.data);
                            if(res.data != null) {
                                form.val('form3', {
                                    'additionid' : res.data.id,
                                    'zpyy1' : res.data.zpyy1,
                                    'zpyy2' : res.data.zpyy2,
                                    'zpyy3' : res.data.zpyy3,
                                    'ncjtrks' : res.data.ncjtrks,
                                    'nmjtrks' : res.data.nmjtrks
                                });
                                form.render();
                            }
                        }
                    });
                    initTab2 = 1;
                }
            }
            // 生产生活条件
            if(data.index == 3) {
                if(initTab3 == 0) {
                    $.ajax({
                        url : '/yw/jt/getScshtj',
                        data : {fid : fid},
                        dataType: 'JSON',
                        success : function(res) {
                            if(res.data == null)
                                return;
                            // 单独处理燃料的其他项
                            if(res.data.zyrllx == 5) {
                                $("#label_jtrlmc").css("color", '');
                                $("#jtrlmc").removeClass("layui-disabled");
                                $("#jtrlmc").attr("disabled", false);
                                $("#jtrlmc").attr("lay-verify", "required");
                            }
                            form.val('form4', {
                                'conditionid' : res.data.id,
                                'gdmj' : res.data.gdmj,
                                'ldmj' : res.data.ldmj,
                                'tghlmj' : res.data.tghlmj,
                                'lgmj' : res.data.lgmj,
                                'mcdmj' : res.data.mcdmj,
                                'smmj' : res.data.smmj,
                                'sfjrnmzyhzs' : res.data.sfjrnmzyhzs,
                                'sfyltqybz' : res.data.sfyltqybz,
                                'sfycyzfdtrdd' : res.data.sfycyzfdtrdd,
                                'sftshyd' : res.data.sftshyd,
                                'sftgbds' : res.data.sftgbds,
                                'sfjjaqyys' : res.data.sfjjaqyys,
                                'sfywscs' : res.data.sfywscs,
                                'yczgljl' : res.data.yczgljl,
                                'rhllx' : res.data.rhllx,
                                'zfmj' : res.data.zfmj,
                                'sfwf' : res.data.sfwf,
                                'wfdj' : res.data.wfdj,
                                'wgnd' : res.data.wgnd,
                                'zyrllx' : res.data.zyrllx,
                                'jtrlmc' : res.data.jtrlmc
                            });
                            form.render();
                        }
                    });
                    initTab3 = 1;
                }
            }

            // 新增字段
            if(data.index == 5) {
                if(initTab5 == 0) {
                    $.ajax({
                        url : '/yw/jt/getNew',
                        data : {id : fid},
                        dataType: 'JSON',
                        success : function(res) {
                            if(res.data == null)
                                return;
                            form.val('form6', {
                                'azd' : res.data.azd,
                                'ldfh' : res.data.ldfh,
                                'ndbqrw' : res.data.ndbqrw,
                                'qcdxxdz' : res.data.qcdxxdz,
                                'qcdlx' : res.data.qcdlx,
                                'jtsyrk' : res.data.jtsyrk,
                                'zfmj' : res.data.zfmj,
                                'zczj' : res.data.zczj,
                                'bqrzsj' : res.data.bqrzsj,
                                'sjrzsj' : res.data.sjrzsj,
                                'yyzf' : res.data.yyzf,
                                'yyzfmj' : res.data.yyzfmj,
                                'ccjf' : res.data.ccjf,
                                'bhzfzl' : res.data.bhzfzl,
                                'ccsj' : res.data.ccsj,
                                'ccjfmj' : res.data.ccjfmj,
                                'dxcfjl' : res.data.dxcfjl,
                                'fkflqk' : res.data.fkflqk,
                                'cyfcxm' : res.data.cyfcxm,
                                'nnhdcyfczj' : res.data.nnhdcyfczj,
                                'tpqk' : res.data.tpqk,
                                'hkqy' : res.data.hkqy
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
            let checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    parent.layer.open({
                        title: '新增家庭成员',
                        type: 2,
                        skin: 'layui-layer-rim', //加上边框
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
                    title: '编辑贫困户家庭基本信息',
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



