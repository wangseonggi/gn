$(document).ready(function () {
    income_add.onload();
});

var income_add = (function () {
    var loadedSr3 = 0;

    layui.use(['form', 'element', 'layer'], function () {
        var form = layui.form;
        var element = layui.element;
        var layer = layui.layer;

        // 加载生产经营收支数据
        $.ajax({
            url : '/yw/sr/getSr1',
            method : 'POST',
            contentType: 'application/json',
            data : JSON.stringify({nf : getQueryVariable("year"), fid : parseInt(getQueryVariable("fid"))}),
            success : function(result) {
                form.val("income1", result.data);
                subtotal_1(); // 小计
                form.render();
                changInputColor($("form[lay-filter='income1']").find("input"));
            }
        });

        // 生产经营性收支数据提交
        form.on('submit(income1)', function(data){
            var obj = {};
            obj.year = getQueryVariable("year");
            obj.fid = getQueryVariable("fid");
            obj.data = data.field;
            var str = JSON.stringify(obj);

            $.ajax({
                url : '/yw/sr/addSr1',
                method : 'POST',
                contentType: 'application/json',
                data : str,
                success: function (data) {
                    layer.msg(data.msg);
                    subtotal_1();
                    changInputColor($("form[lay-filter='income1']").find("input"));
                },
                error: function (data) {
                    layer.msg("操作失败! " + data.msg);
                }
            });
            return false;
        });

        // 工资性收入 & 财产性收入 提交
        form.on('submit(income3)', function(data){

            var obj = {};
            obj.year = getQueryVariable("year");
            obj.fid = getQueryVariable("fid");

            var ryList= [];
            var xh = 1;
            $("#income3_tbody").find('tr.ry').each(function() {
                var ryObj = {};
                var tdArr = $(this).children();
                $.each(tdArr , function(i, n){
                    var inpu = $(n).children('input');
                    if(i == 0) {    // 处理序号
                        ryObj[$(inpu).attr('name')] = xh++;
                    }
                    else {
                        if($(inpu).attr('name')) {
                            ryObj[$(inpu).attr('name')] = $(inpu).val();
                        }
                    }
                });
                ryObj.fid = obj.fid;
                ryObj.nf = obj.year;
                ryObj.lb = '01';
                ryList.push(ryObj);
            });

            var dd = data.field;
            delete dd.xh;
            delete dd.xm;
            delete dd.wggz;
            delete dd.wgdz;
            delete dd.wgljsj;
            delete dd.wgqymc;
            delete dd.item1;
            delete dd.item2;
            delete dd.item3;
            delete dd.item4;
            delete dd.item5;
            delete dd.item6;
            delete dd.item7;
            delete dd.item8;
            delete dd.item9;
            delete dd.item10;
            delete dd.item11;
            delete dd.item12;
            obj.field = dd;
            obj.list = ryList;

            $.ajax({
                url : '/yw/sr/addSr3',
                method : 'POST',
                contentType: 'application/json',
                data : JSON.stringify(obj),
                success: function (data) {
                    layer.msg(data.msg);
                    subtotal_2();
                    changInputColor($("form[lay-filter='income3']").find("input"));
                },
                error: function (data) {
                    layer.msg("操作失败! " + data.msg);
                }
            });
            return false;
        });


        // 转移性收入 提交
        form.on('submit(income5)', function(data){

            var obj = {};
            obj.year = getQueryVariable("year");
            obj.fid = getQueryVariable("fid");
            obj.data = data.field;
            var str = JSON.stringify(obj);

            $.ajax({
                url : '/yw/sr/addSr5',
                method : 'POST',
                contentType: 'application/json',
                data : str,
                success: function (data) {
                    layer.msg(data.msg);
                    subtotal_5();
                    changInputColor($("form[lay-filter='income5']").find("input"));
                },
                error: function (data) {
                    layer.msg("操作失败! " + data.msg);
                }
            });
            return false;
        });


        // 不计入家庭稳定脱贫收入项目 提交
        form.on('submit(income9)', function(data){

            var obj = {};
            obj.year = getQueryVariable("year");
            obj.fid = getQueryVariable("fid");
            obj.data = data.field;
            var str = JSON.stringify(obj);

            $.ajax({
                url : '/yw/sr/addSr9',
                method : 'POST',
                contentType: 'application/json',
                data : str,
                success: function (data) {
                    layer.msg(data.msg);
                    subtotal_9();
                    changInputColor($("form[lay-filter='income9']").find("input"));
                },
                error: function (data) {
                    layer.msg("操作失败! " + data.msg);
                }
            });
            return false;
        });


        element.on('tab(incomeTab)', function (data) {
            if (data.index == 1) {
                if(!loadedSr3) {
                    $.ajax({
                        url: "/yw/sr/getSr3",
                        method : 'POST',
                        contentType: 'application/json',
                        data : JSON.stringify({nf : getQueryVariable("year"), fid: getQueryVariable("fid")}),
                        success: function (result) {
                            // 如果没有数据，初始化界面
                            if(result.data.ry.length == 0) {
                                addSr3Row(null);
                            }
                            else {
                                // 调整
                                $("#srtd").attr("rowspan", 4 + result.data.ry.length);
                                $.each(result.data.ry, function(i,n) {
                                    var addedTr = "<tr class='ry'>" +
                                        "<td hidden='hidden'><input name='xh' class='layui-input' type='text' value=''/></td>" +
                                        "<td class='income-input'><input name='xm' class='layui-input' type='text' value='" + n.xm + "'/></td>" +
                                        "<td class='income-input'><input name='wggz' class='layui-input' type='text' value='" + n.wggz + "'/></td>" +
                                        "<td class='income-input'><input name='wgdz' class='layui-input' type='text' value='" + n.wgdz + "'/></td>" +
                                        "<td class='income-input'><input name='wgljsj' class='layui-input' type='text' value='" + n.wgljsj + "'/></td>" +
                                        "<td class='income-input'><input name='wgqymc' class='layui-input' type='text' value='" + n.wgqymc + "'/></td>" +
                                        "<td class='income-input'><input name='item1' class='layui-input' type='text' value='" + n.item1 + "'/></td>" +
                                        "<td class='income-input'><input name='item2' class='layui-input' type='text' value='" + n.item2 + "'/></td>" +
                                        "<td class='income-input'><input name='item3' class='layui-input' type='text' value='" + n.item3 + "'/></td>" +
                                        "<td class='income-input'><input name='item4' class='layui-input' type='text' value='" + n.item4 + "'/></td>" +
                                        "<td class='income-input'><input name='item5' class='layui-input' type='text' value='" + n.item5 + "'/></td>" +
                                        "<td class='income-input'><input name='item6' class='layui-input' type='text' value='" + n.item6 + "'/></td>" +
                                        "<td class='income-input'><input name='item7' class='layui-input' type='text' value='" + n.item7 + "'/></td>" +
                                        "<td class='income-input'><input name='item8' class='layui-input' type='text' value='" + n.item8 + "'/></td>" +
                                        "<td class='income-input'><input name='item9' class='layui-input' type='text' value='" + n.item9 + "'/></td>" +
                                        "<td class='income-input'><input name='item10' class='layui-input' type='text' value='" + n.item10 + "'/></td>" +
                                        "<td class='income-input'><input name='item11' class='layui-input' type='text' value='" + n.item11 + "'/></td>" +
                                        "<td class='income-input'><input name='item12' class='layui-input' type='text' value='" + n.item12 + "'/></td>" +
                                        "<td><div style='width: 95px;overflow: hidden'><a class='layui-btn layui-btn-xs' onclick='income_add.addSr3Row(this);'>+</a><a class='layui-btn layui-btn-primary layui-btn-xs' onclick='income_add.delSr3Row(this);'>-</a></td></div></tr>";
                                    $('#je35').before(addedTr);
                                });
                            }

                            // 否则加载数据
                            form.val('income3', result.data.qt);
                            form.render();

                            subtotal_2();
                            loadedSr3 = 1; // 更改标记

                            // 刷新背景颜色
                            changInputColor($("form[lay-filter='income3']").find("input"));
                        }
                    });
                }
            }

            if (data.index == 2) {
                $.ajax({
                    url: "/yw/sr/getSr5",
                    method : 'POST',
                    contentType: 'application/json',
                    data : JSON.stringify({nf : getQueryVariable("year"), fid: getQueryVariable("fid")}),
                    success: function (data) {
                        if(data.data) {
                            form.val('income5', data.data.d);
                            subtotal_5();
                            form.render();

                            var jtwdzsr = data.data.jtwdzsr;
                            //
                            var jtzzc = data.data.jtzzc;
                            //
                            // var num = data.data.jtwdzsr.num; // 家庭成员数
                            //
                            // 填总收入
                            $("#jtwdzsr").val(jtwdzsr.toFixed(2));
                            // 填纯收入
                            $("#jtwdcsr").val((jtwdzsr - jtzzc).toFixed(2));
                            // // 填人均纯收入
                            // if(num) {
                            //     $("#rjcsr2019").val(((jtwdzsr2019 - jtwdzzc2019) / num).toFixed(2));
                            // }
                            // else {
                            //     $("#rjcsr2019").val("无家庭成员信息");
                            // }
                            changInputColor($("form[lay-filter='income5']").find("input"));
                        }

                    }
                });
            }
            if (data.index == 3) {
                $.ajax({
                    url: "/yw/sr/getSr9",
                    method : 'POST',
                    contentType: 'application/json',
                    data : JSON.stringify({nf : getQueryVariable("year"), fid: getQueryVariable("fid")}),
                    success: function (data) {
                        form.val('income9', data.data);
                        subtotal_9();
                        form.render();
                        changInputColor($("form[lay-filter='income9']").find("input"));
                    }
                });
            }
            if (data.index == 4) {

            }
        });
    });


    // 小计函数 1
    function subtotal_1() {
        // 逐行累加，再写入
        var inputsJe11 = $("#je11").children().find("input");
        var inputsJe12 = $("#je12").children().find("input");
        var inputsJe13 = $("#je13").children().find("input");
        var jeqt11 = $("#jeqt11").children().find("input");
        var jeqt12 = $("#jeqt12").children().find("input");

        var xjData1 = [];
        for(var i = 0; i < inputsJe11.length; i++) {
            xjData1[i] = 0;
        }
        inputsJe11.each(function (index, e) {
            if ($(e).val())
                xjData1[index] += parseFloat($(e).val());
        });
        inputsJe12.each(function (index, e) {
            if ($(e).val())
                xjData1[index] += parseFloat($(e).val());
        });
        inputsJe13.each(function (index, e) {
            if ($(e).val())
                xjData1[index] += parseFloat($(e).val());
        });
        jeqt11.each(function (index, e) {
            if ($(e).val())
                xjData1[index] += parseFloat($(e).val());
        });

        var xjRow1 = $("#xj1").children().find("input");
        xjRow1.each(function (index, e) {
            if (xjData1[index] != 0)
                $(e).val(xjData1[index]);
            else
                $(e).val("");
        });

        // 逐行累加，再写入
        var inputsJe121 = $("#je21").children().find("input");
        var inputsJe122 = $("#je22").children().find("input");
        var inputsJe123 = $("#je23").children().find("input");

        var xjData2 = [];
        for(var i = 0; i < inputsJe121.length; i++) {
            xjData2[i] = 0;
        }

        inputsJe121.each(function (index, e) {
            if ($(e).val())
                xjData2[index] += parseFloat($(e).val());
        });
        inputsJe122.each(function (index, e) {
            if ($(e).val())
                xjData2[index] += parseFloat($(e).val());
        });
        inputsJe123.each(function (index, e) {
            if ($(e).val())
                xjData2[index] += parseFloat($(e).val());
        });
        jeqt12.each(function (index, e) {
            if ($(e).val())
                xjData2[index] += parseFloat($(e).val());
        });

        var xjRow2 = $("#xj2").children().find("input");
        xjRow2.each(function (index, e) {
            if (xjData2[index] != 0)
                $(e).val(xjData2[index]);
            else
                $(e).val("");
        });
    }

    // 小计函数 3
    function subtotal_2() {

        // 表3小计

        // 初始化0
        let xjData3 = [];
        for(let i = 0; i < 12; i++) {
            xjData3[i] = 0;
        }

        $("#income3_tbody").find('tr.ry').each(function() {
            var inputArr = $(this).children().find("input");
            inputArr.each(function (index, e) {
                if (index >= 6) {
                    if ($(e).val())
                        xjData3[index - 6] += parseFloat($(e).val());
                }
            });
        });

        let inputsJe35 = $("#je35").children().find("input");


        // 逐行累加
        inputsJe35.each(function (index, e) {
            if ($(e).val())
                xjData3[index] += parseFloat($(e).val());
        });

        let xjRow3 = $("#xj3").children().find("input");
        xjRow3.each(function (index, e) {
            if (xjData3[index] != 0)
                $(e).val(xjData3[index]);
            else
                $(e).val("");
        });


        // 表4小计
        let inputsJe41 = $("#je41").children().find("input");
        let inputsJe42 = $("#je42").children().find("input");

        let xjData4 = [];
        for(let i = 0; i < inputsJe41.length; i++) {
            xjData4[i] = 0;
        }
        inputsJe41.each(function (index, e) {
            if ($(e).val())
                xjData4[index] += parseFloat($(e).val());
        });
        inputsJe42.each(function (index, e) {
            if ($(e).val())
                xjData4[index] += parseFloat($(e).val());
        });

        let xjRow4 = $("#xj4").children().find("input");
        xjRow4.each(function (index, e) {
            if (xjData4[index] != 0)
                $(e).val(xjData4[index]);
            else
                $(e).val("");
        });
    }

    // 小计函数 5
    function subtotal_5() {
        // 表5小计
        let jhsyj = $("#jhsyj").children().find("input");
        let dbjA = $("#dbjA").children().find("input");
        let dbjB = $("#dbjB").children().find("input");
        let dbjC = $("#dbjC").children().find("input");
        let tkgyj = $("#tkgyj").children().find("input");
        let stbcj = $("#stbcj").children().find("input");
        let ylbxj = $("#ylbxj").children().find("input");

        let shbz = $("#shbz").children().find("input");
        let nybt = $("#nybt").children().find("input");
        let glbt = $("#glbt").children().find("input");
        let skymbt = $("#skymbt").children().find("input");
        let qtcqxbt = $("#qtcqxbt").children().find("input");

        let xjData6_10 = []; // 6-10小计, 1-10小计再累加前四行
        for(let i = 0; i < jhsyj.length; i++) {
            xjData6_10[i] = 0;
        }

        // 6-10逐行累加
        shbz.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        nybt.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        glbt.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        skymbt.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        qtcqxbt.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });

        let xjRow5 = $("#xj5").children().find("input");
        xjRow5.each(function (index, e) {
            if (xjData6_10[index] != 0)
                $(e).val(xjData6_10[index]);
            else
                $(e).val("");
        });

        // 累加前四行作为1-10
        jhsyj.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        dbjA.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        dbjB.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        dbjC.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        tkgyj.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        stbcj.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });
        ylbxj.each(function (index, e) {
            if ($(e).val())
                xjData6_10[index] += parseFloat($(e).val());
        });

        let xjRow6 = $("#xj6").children().find("input");
        xjRow6.each(function (index, e) {
            if (xjData6_10[index] != 0)
                $(e).val(xjData6_10[index]);
            else
                $(e).val("");
        });
    }

    // 小计函数 9
    function subtotal_9() {
        // 表9小计
        let jybzl = $("#jybzl").children().find("input");
        let ylbzl = $("#ylbzl").children().find("input");
        let zfbzl = $("#zfbzl").children().find("input");
        let cyjbl = $("#cyjbl").children().find("input");
        let jrl = $("#jrl").children().find("input");
        let shbzl = $("#shbzl").children().find("input");
        let bxpfj = $("#bxpfj").children().find("input");
        let cjrbt = $("#cjrbt").children().find("input");
        let jzjd = $("#jzjd").children().find("input");
        let dqfxjd = $("#dqfxjd").children().find("input");
        let qtdqxbz = $("#qtdqxbz").children().find("input");

        let xjData9 = [];
        for(let i = 0; i < jybzl.length; i++) {
            xjData9[i] = 0;
        }
        jybzl.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        ylbzl.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        zfbzl.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        cyjbl.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        jrl.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        shbzl.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        bxpfj.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        cjrbt.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        jzjd.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        dqfxjd.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });
        qtdqxbz.each(function (index, e) {
            if ($(e).val())
                xjData9[index] += parseFloat($(e).val());
        });

        let xjRow9 = $("#xj10").children().find("input");
        xjRow9.each(function (index, e) {
            if (xjData9[index] != 0)
                $(e).val(xjData9[index]);
            else
                $(e).val("");
        });
    }


    // 表三加行 obj为+按钮
    function addSr3Row(obj) {
        var addedTr = "<tr class='ry'>" +
            "<td hidden='hidden'><input name='xh' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='xm' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='wggz' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='wgdz' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='wgljsj' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='wgqymc' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item1' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item2' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item3' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item4' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item5' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item6' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item7' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item8' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item9' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item10' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item11' class='layui-input' type='text' value=''/></td>" +
            "<td class='income-input'><input name='item12' class='layui-input' type='text' value=''/></td>" +
            "<td><div style='width: 95px;overflow: hidden'><a class='layui-btn layui-btn-xs' onclick='income_add.addSr3Row(this);'>+</a><a class='layui-btn layui-btn-primary layui-btn-xs' onclick='income_add.delSr3Row(this);'>-</a></td></div></tr>" ;
        if(obj) {
            $("#srtd").attr("rowspan", parseInt($("#srtd").attr("rowspan")) + 1);
            $(obj).parents('tr').after(addedTr);
        }
        else {
            $("#srtd").attr("rowspan", 5);
            $('#je35').before(addedTr); // 无数据时初始化一条
        }
    }

    // 表三减行
    function delSr3Row(obj) {
        var ryRowNum = $(obj).parents('tbody').children('.ry').size()
        if(ryRowNum == 1) {
            layer.msg('最后一行无法删除');
            return false;
        }
        $("#srtd").attr("rowspan", parseInt($("#srtd").attr("rowspan")) - 1);
        $(obj).parents('tr').remove();
    }

    // 获取请求参数
    function getQueryVariable(variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
                return pair[1];
            }
        }
        return (false);
    }

    return {
        onload: function () {
        },

        addSr3Row : function(obj){
            addSr3Row(obj);
        },

        delSr3Row : function(obj) {
            delSr3Row(obj);
        },

        getQueryVariable : function(variable) {
            return getQueryVariable(variable);
        }
    };
})();