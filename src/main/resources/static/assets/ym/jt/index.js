$(document).ready(function () {
    jt.onload();
});

var jt = (function () {
    layui.use('table', function () {
        var table = layui.table;
        var familyTabel = table.render({
            elem: '#indexTable',
            url: '/yw/jt/getList',
            page: true,
            toolbar: '#toolbarDemo',
            limits: [10, 15, 20, 25],
            limit: 10,
            even: true,
            title: '贫困户家庭基本情况',
            cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: '家庭ID', width: 90, unresize: true, sort: true}
                , {field: 'pkhsx', title: '贫困户属性'}
                , {field: 'hzxm', title: '户主姓名', width: 120}
                , {field: 'zjhm', title: '户主证件号码'}
                // , {field: 'jhtpnd', title: '计划脱贫年度'}
                // , {field: 'sfbqh', title: '是否易地搬迁户'}
                , {title: '操作', toolbar: '#barDemo', fixed: 'right', width: 160}
            ]],
            done: function (res, curr, count) {
                $("[data-field='jhtpnd']").children().each(function () {
                    if($(this).text() == 0) {
                        $(this).text("");
                    }

                });
                $("[data-field='sfbqh']").children().each(function () {
                    if ($(this).text() == 1) {
                        $(this).text("是")
                    } else if ($(this).text() == 0) {
                        $(this).text("否")
                    }
                });
                $("#search_name").unbind();
                $("#search_sfzhm").unbind();
                bindClick();
            },
            skin: 'row'
        });


        //工具栏事件
        table.on('toolbar(userList)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        title: '新增贫困户家庭基本信息',
                        type: 2,
                        area: ['100%', '100%'], //宽高
                        content: '/yw/jt/add',
                        end: function () {
                            parent.layui.table.reload('indexTable'); // 刷新index页表格
                        }
                    });
                    break;
                case 'del':
                    var data = checkStatus.data;

                    if (data.length == 0) {
                        layer.msg("请勾选需要删除的数据");
                        return;
                    }

                    var ids = [];
                    $.each(data, function (i, n) {
                        ids.push(n.id);
                    });
                    // layer.alert(JSON.stringify(ids));
                    layer.confirm('是否删除' + ids, {
                        btn: ['是', '否'] //按钮
                    }, function () {
                        $.ajax({
                            url: '/yw/jt/delAll',
                            data: JSON.stringify(ids),
                            method: "POST",
                            contentType: 'application/json',
                            dataType: 'JSON',
                            // beforeSend: function (xhr) {
                            //     xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                            // },
                            success: function (res) {
                                if (res.code == 0) {
                                    layer.msg("✔ 删除成功!", {shift: -1, time: 2000}, function () {
                                        parent.layui.table.reload('indexTable');
                                    });
                                }
                                else {
                                    layer.msg("× 删除失败！");
                                }
                            },
                            error: function (data) {

                            }
                        });
                    });
                    break;
                case 'search' :
                    where();
                    break;
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    layer.confirm('是否删除' + ids, {
                        btn: ['是', '否'] //按钮
                    }, function () {
                        layer.msg("删除操作");
                    });
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选')
                    break;
            }
            ;
        });

        //监听工具条
        table.on('tool(userList)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.id + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('是否删除该记录？', function (index) {
                    $.ajax({
                        url: '/yw/jt/del',
                        method: 'get',
                        data: {id: data.id},
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
                layer.open({
                    title: '编辑贫困户家庭基本信息',
                    type: 2,
                    // skin: 'layui-layer-rim', //加上边框
                    area: ['100%', '100%'], //宽高
                    content: '/yw/jt/edit',
                    success : function(layero, index) {
                        var iframe = window['layui-layer-iframe' + index];
                        iframe.setBaseId(data.id);
                    },
                    end: function () {
                        parent.layui.table.reload('indexTable');
                    }
                });

            }
        });

        // 条件查询操作
        function where() {
            var formData = {};

            var name = $("#search_name").val();
            var sfzhm = $("#search_sfzhm").val();
            formData.name = name;
            formData.sfzhm = sfzhm;

            table.reload('indexTable', {
                url : '/yw/jt/getList',
                where : formData,
                page: {
                    curr: 1
                },
                done : function() {
                    $("#search_name").val(name);
                    $("#search_sfzhm").val(sfzhm);
                    $("#search_name").unbind();
                    $("#search_sfzhm").unbind();
                    bindClick();
                }
            });
        }

        // 绑定回车事件
        function bindClick() {
            $("#search_name").keydown(function(e){
                if(e.keyCode==13){
                    where();
                }
            });

            $("#search_sfzhm").keydown(function(e){
                if(e.keyCode==13){
                    where();
                }
            });
        }
    });



    return {
        onload: function () {

        }
    };
})();