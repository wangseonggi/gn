$(document).ready(function () {
    fwxx.onload();
});

var fwxx = (function () {
    var aid = getQueryVariable('id');
    layui.use('table', function () {
        var table = layui.table;
        var fwxxTable = table.render({
            elem: '#familyListTable',
            url: '/yw/azd/getFwxxList',
            where : {aid : aid},
            page: true,
            method : 'POST',
            toolbar: '#toolbarDemo',
            limits: [10, 15, 20, 25],
            limit: 10,
            even: true,
            title: 'xxx房屋信息',
            cols: [[
                {type: 'checkbox'}
                // , {field: 'aid'}
                , {field: 'id', title: 'ID', sort: true}
                , {field: 'mc', title: '名称'}
                , {field: 'ld', title: '楼栋'}
                , {field: 'dy', title: '单元'}
                , {field: 'fh', title: '房号'}
                , {field: 'xm', title: '户主姓名'}
                , {field: 'zjhm', title: '证件号码'}
                // , {field: 'mj', title: '房屋面积'}
                // , {field: 'hx', title: '户型'}
                , {title: '操作', toolbar: '#barDemo', fixed: 'right', width:'20%'}
            ]],
            skin: 'row'
        });

        // 表格上部工具条
        table.on('toolbar(userList)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        title: '新增房屋信息',
                        type: 2,
                        area: ['622px', '261px'], //宽高
                        content: '/yw/azd/addFwxx',
                        offset: '15%',
                        success : function(layero, index) {
                            var iframe = window['layui-layer-iframe' + index];
                            // todo 传入adi
                            iframe.setBaseAId(getQueryVariable('id'));
                        },
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
                            url: '/yw/azd/delAll',
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
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    layer.confirm('是否删除' + ids, {
                        btn: ['是', '否'] //按钮
                    }, function () {
                        layer.msg("删除操作");
                    });
                    break;
            };
        });

        // 表格右侧工具栏
        table.on('tool(userList)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.id + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('是否删除该记录？', function (index) {
                    $.ajax({
                        url: '/yw/azd/del',
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
            }
            // 关联住户
            else if(obj.event == 'gxzh') {
                layer.open({
                    title: '关联住户',
                    type: 2,
                    area: ['752px', '247px'], //宽高
                    content: '/yw/azd/glzh',
                    offset: '15%',
                    success : function(layero, index) {
                        var iframe = window['layui-layer-iframe' + index];
                        iframe.setBaseId(data.id);
                    },
                    end: function () {
                        parent.layui.table.reload('familyListTable');
                    }
                });
            }
            else if (obj.event === 'edit') {
                layer.open({
                    title: '编辑房屋信息',
                    type: 2,
                    area: ['622px', '261px'], //宽高
                    content: '/yw/azd/addFwxx',
                    offset: '15%',
                    success : function(layero, index) {
                        var iframe = window['layui-layer-iframe' + index];
                        iframe.setBaseId(data.id);
                        iframe.setBaseAId(data.aid);
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



            var ld = $("#search_ld").val();
            var dy = $("#search_dy").val();
            var fh = $("#search_fh").val();
            var mjgt = $("#search_mjgt").val();
            var mjlt = $("#search_mjlt").val();

            formData.aid = aid;
            formData.ld = ld ;
            formData.dy = dy ;
            formData.fh = fh ;
            formData.mjgt = mjgt ;
            formData.mjlt = mjlt ;

            fwxxTable.reload({
                id : "id",
                elem: '#familyListTable',
                url: '/yw/azd/getFwxxList',
                page: true,
                where : formData,
                method : 'POST',
                toolbar: '#toolbarDemo',
                limits: [10, 15, 20, 25],
                limit: 10,
                even: true,
                title: 'xxx房屋信息',
                cols: [[
                    {type: 'checkbox'}
                    // , {field: 'aid'}
                    , {field: 'id', title: 'ID', width: 60, sort: true}
                    , {field: 'mc', title: '名称'}
                    , {field: 'ld', title: '楼栋'}
                    , {field: 'dy', title: '单元'}
                    , {field: 'fh', title: '房号'}
                    , {field: 'xm', title: '户主姓名'}
                    , {field: 'zjhm', title: '证件号码'}
                    // , {field: 'mj', title: '房屋面积'}
                    // , {field: 'hx', title: '户型'}
                    , {title: '操作', toolbar: '#barDemo', fixed: 'right'}
                ]],
                done: function (res, curr, count) {
                    $("#search_ld").val(ld);
                    $("#search_dy").val(dy);
                    $("#search_fh").val(fh);
                    $("#search_mjgt").val(mjgt);
                    $("#search_mjlt").val(mjlt);
                    bindClick();
                },
                skin: 'row'
            });
        }

        // 绑定回车事件
        function bindClick() {
            $("#search_ld").bind("keypress", function(){
                if (event.keyCode == 13){
                    $("#search_btn").click();
                }
            });
            $("#search_dy").bind("keypress", function(){
                if (event.keyCode == 13){
                    $("#search_btn").click();
                }
            });
            $("#search_fh").bind("keypress", function(){
                if (event.keyCode == 13){
                    $("#search_btn").click();
                }
            });
            $("#search_mjgt").bind("keypress", function(){
                if (event.keyCode == 13){
                    $("#search_btn").click();
                }
            });
            $("#search_mjlt").bind("keypress", function(){
                if (event.keyCode == 13){
                    $("#search_btn").click();
                }
            });
        }
    });

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

        }
    };
})();