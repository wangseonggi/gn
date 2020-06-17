$(document).ready(function () {
    bfr.onload();
});

var bfr = (function () {
    layui.use('table', function () {
        var table = layui.table;
        var bfrTable = table.render({
            elem: '#sss',
            url: '/yw/bfr/getList',
            page: true,
            method : 'POST',
            toolbar: '#toolbarDemo',
            limits: [10, 15, 20, 25],
            limit: 10,
            even: true,
            title: '帮扶人列表',
            cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', width: 60, unresize: true, sort: true}
                , {field: 'xm', title: '帮扶人姓名',width: 115}
                , {field: 'xb', title: '性别',width: 60}
                , {field: 'gzdw', title: '工作单位'}
                , {field: 'lxdh', title: '联系电话'}
                , {field: 'hzxm', title: '帮扶家庭户主'}
                , {field: 'fid', title: '帮扶家庭id'}
                , {title: '操作', toolbar: '#barDemo', fixed: 'right'}
            ]],
            done: function (res, curr, count) {
                $("[data-field='xb']").children().each(function () {
                    if ($(this).text() == '') {
                        $(this).text('***');
                    }
                });
                $("[data-field='hzxm']").children().each(function () {
                    if ($(this).text() == '') {
                        $(this).css('color', 'red');
                        $(this).text('未指定');
                    }
                });
                $("[data-field='fid']").children().each(function () {
                    if (!$(this).text()) {
                        $(this).css('color', 'red');
                        $(this).text('未指定');
                    }
                });
                $("#search_name").unbind();
                $("#search_gzdw").unbind();
                $("#search_lxdh").unbind();
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
                        title: '新增帮扶人',
                        type: 2,
                        skin:'layui-layer-molv',
                        area: ['50%', '60%'], //宽高
                        content: '/yw/bfr/add',
                        end: function () {
                            parent.layui.table.reload('familyListTable');
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
                            url: '/yw/bfr/delAll',
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
                                        parent.layui.table.reload('familyListTable');
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
            };
        });

        //监听工具条
        table.on('tool(userList)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('是否删除该记录？', function (index) {
                    $.ajax({
                        url: '/yw/bfr/del',
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
                    title: '编辑帮扶人',
                    type: 2,
                    skin:'layui-layer-molv',
                    area: ['50%', '60%'], //宽高
                    content: '/yw/bfr/add?id=' + data.id,
                    success : function(layero, index) {
                    },
                    end: function () {
                        parent.layui.table.reload('id','familyListTable');
                    }
                });

            }
            else if (obj.event === 'bfgx') {
                layer.open({
                    title: '帮扶关系',
                    type: 2,
                    // skin: 'layui-layer-rim', //加上边框
                    skin:'layui-layer-molv',
                    area: ['50%', '60%'], //宽高
                    content: '/yw/bfr/bfgx?id=' + data.id,
                    success : function(layero, index) {
                    },
                    end: function () {
                        parent.layui.table.reload('id','familyListTable');
                    }
                });
            }
        });

        // 条件查询操作
        function where() {
            var formData = {};

            var name = $("#search_name").val();
            var gzdw = $("#search_gzdw").val();
            var lxdh = $("#search_lxdh").val();
            formData.name = name;
            formData.gzdw = gzdw;
            formData.lxdh = lxdh;

            table.reload('sss', {
                url : '/yw/bfr/getList',
                where : formData,
                page: {
                    curr: 1
                },
                done : function() {
                    $("#search_name").val(name);
                    $("#search_gzdw").val(gzdw);
                    $("#search_lxdh").val(lxdh);
                    $("#search_name").unbind();
                    $("#search_sfzhm").unbind();
                    $("#search_lxdh").unbind();
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

            $("#search_gzdw").keydown(function(e){
                if(e.keyCode==13){
                    where();
                }
            });

            $("#search_lxdh").keydown(function(e){
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