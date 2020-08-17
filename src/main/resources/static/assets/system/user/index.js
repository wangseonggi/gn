$(document).ready(function () {
    family.onload();
});

var family = (function () {
    layui.use('table', function () {
        var table = layui.table;
        var familyTabel = table.render({
            elem: '#familyListTable',
            url: '/xt/user/index',
            page: true,
            toolbar: '#toolbarDemo',
            limits: [10, 15, 20, 25],
            limit: 10,
            even: true,
            title: '管理员列表',
            cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', width: 80, unresize: true, sort: true}
                , {field: 'username', title: '账号', width: 120}
                , {field: 'nc', title: '昵称', width: 120}
                , {field: 'dh', title: '电话', width: 140}
                , {field: 'dzyx', title: '电子邮箱'}
                , {field: 'zhyxq', title: '账户有效期'}
                , {field: 'mmyxq', title: '密码有效期'}
                , {field: 'zt', title: '状态', width: 80}
                , {title: '操作', toolbar: '#barDemo', fixed: 'right', width: 200}
            ]],
            done: function (res, curr, count) {
                $("[data-field='zt']").children().each(function () {
                    if($(this).text() == '1') {
                        $(this).html("<span style='color: green'>启用</span>")
                    }
                    if($(this).text() == '0') {
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
                        title: '新增用户',
                        type: 2,
                        area: ['508px', '360px'], //宽高
                        content: '/xt/user/add',
                        offset: ['15%'],
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
                    layer.confirm('是否删除' + ids, {
                        btn: ['是', '否'] //按钮
                    }, function () {
                        layer.msg("删除操作");
                    });
                    break;
               X
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
                        url: '/xt/user/del',
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
                    title: '编辑管理员信息',
                    type: 2,
                    area: ['508px', '360px'], //宽高
                    content: '/xt/user/add',
                    offset: ['15%'],
                    success : function(layero, index) {
                        var iframe = window['layui-layer-iframe' + index];
                        iframe.setBaseId(data.id);
                    },
                    end: function () {
                        parent.layui.table.reload('familyListTable');
                    }
                });

            } else if(obj.event === 'setRole') {
                layer.open({
                    title: '角色',
                    type: 2,
                    area: ['562px', '235px'], //宽高
                    content: '/xt/user/setRole?id=' + data.id,
                    offset: '15%',
                    success : function(layero, index) {
                    },
                    end: function () {
                        parent.layui.table.reload('familyListTable');
                    }
                });
            }
        });

        bindClick();
        // 条件查询操作
        function where() {
            var formData = {};

            var username = $("#search_name").val();
            formData.username = username;

            familyTabel.reload({
                elem: '#familyListTable',
                url: '/xt/user/index',
                page: true,
                where : formData,
                toolbar: '#toolbarDemo',
                limits: [10, 15, 20, 25],
                limit: 10,
                even: true,
                title: '管理员列表',
                cols: [[
                    {type: 'checkbox'}
                    , {field: 'id', title: 'ID', width: 80, unresize: true, sort: true}
                    , {field: 'username', title: '账号', width: 120}
                    , {field: 'nc', title: '昵称', width: 120}
                    , {field: 'dh', title: '电话', width: 140}
                    , {field: 'dzyx', title: '电子邮箱'}
                    , {field: 'zhyxq', title: '账户有效期'}
                    , {field: 'mmyxq', title: '密码有效期'}
                    , {field: 'zt', title: '状态', width: 80}
                    , {title: '操作', toolbar: '#barDemo', fixed: 'right', width: 200}
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
                    $("#search_name").val(username);
                    bindClick();
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