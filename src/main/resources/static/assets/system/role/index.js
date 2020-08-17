$(document).ready(function () {
    role.onload();
});

var role = (function () {
    layui.use('table', function () {
        var table = layui.table;
        var roleTable = table.render({
            elem: '#familyListTable',
            url: '/xt/role/index',
            page: true,
            toolbar: '#toolbarDemo',
            limits: [10, 15, 20, 25],
            limit: 10,
            even: true,
            title: '授权',
            cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', width: 80, unresize: true, sort: true}
                , {field: 'mc', title: '标签', width: 150}
                , {field: 'sm', title: '角色名',width: 100}
                , {field: 'bz', title: '备注'}
                , {field: 'zt', title: '状态',width: 60}
                , {field: 'cjsj', title: '创建时间',width: 200}
                , {title: '操作', toolbar: '#barDemo', fixed: 'right', width: 160}
            ]],
            done: function (res, curr, count) {
                $("[data-field='zt']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).html("<span style='color: green'>启用</span>")
                    }
                    if ($(this).text() == '0') {
                        $(this).html("<span style='color: red'>禁用</span>")
                    }
                });
            },
            skin: 'row'
        });


        //工具栏事件
        table.on('toolbar(userList)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        title: '新增角色',
                        type: 2,
                        area: ['470px', '220px'], //宽高
                        content: '/xt/role/add',
                        offset: '15%' ,
                        end: function () {
                            parent.layui.table.reload('familyListTable');
                        }
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
            }
        });

        //监听工具条
        table.on('tool(userList)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.id + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('是否删除该记录？', function (index) {
                    $.ajax({
                        url: '/xt/role/del',
                        method: 'get',
                        data: {id: data.id},
                        contentType: "application/json",
                        success: function (res) {
                            if(res.data == 1) {
                                layer.msg("✔ 删除成功!", {shift: -1, time: 2000}, function () {
                                    obj.del();
                                    layer.close(index);
                                });
                            }

                        },
                        error: function (data) {
                            layer.msg("删除失败!");
                        }
                    });
                });
            }
            else if (obj.event === 'grant') {
                layer.open({
                    title: '授权',
                    type: 2,
                    area: ['470px', '556px'], //宽高
                    content: '/xt/role/grant?id=' + data.id,
                    offset: '10%',
                    success : function(layero, index) {
                    }
                });

            }
        });

        bindClick();
        // 条件查询操作
        function where() {
            var formData = {};

            var mc = $("#search_mc").val();
            var sm = $("#search_sm").val();
            formData.mc = mc;
            formData.sm = sm;

            roleTable.reload({
                elem: '#familyListTable',
                url: '/xt/role/index',
                page: true,
                where : formData,
                toolbar: '#toolbarDemo',
                limits: [10, 15, 20, 25],
                limit: 10,
                even: true,
                title: '授权',
                cols: [[
                    {type: 'checkbox'}
                    , {field: 'id', title: 'ID', width: 80, unresize: true, sort: true}
                    , {field: 'mc', title: '标签'}
                    , {field: 'sm', title: '角色名'}
                    , {field: 'bz', title: '备注'}
                    , {field: 'zt', title: '状态'}
                    , {field: 'cjsj', title: '创建时间'}
                    , {field: 'gxsj', title: '更新时间'}
                    , {title: '操作', toolbar: '#barDemo', fixed: 'right', width: 160}
                ]],
                done: function (res, curr, count) {
                    $("[data-field='zt']").children().each(function () {
                        if ($(this).text() == '1') {
                            $(this).html("<span style='color: green'>启用</span>")
                        }
                        if ($(this).text() == '0') {
                            $(this).html("<span style='color: red'>禁用</span>")
                        }
                    });
                },
                skin: 'row'
            });
        }

        // 绑定回车事件
        function bindClick() {
            $("#search_name").bind("keypress", function(){
                if (event.keyCode == 13){
                    $("#search_btn").click();
                }
            });
            $("#search_sfzhm").bind("keypress", function(){
                if (event.keyCode == 13){
                    $("#search_btn").click();
                }
            });
        }
    });



    return {
        onload: function () {

        }
    };
})();