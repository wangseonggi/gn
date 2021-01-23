$(document).ready(function () {
    family.onload();
});

var family = (function () {
    layui.use('table', function () {
        var table = layui.table;
        var familyTabel = table.render({
            elem: '#familyListTable',
            url: '/query/jt/getList',
            page: true,
            toolbar: '#toolbarDemo',
            limits: [10, 15, 20, 25],
            limit: 10,
            even: true,
            title: '贫困户家庭基本情况',
            cols: [[
                // {type: 'checkbox'}
                // , {field: 'id', title: '家庭ID', width: 90, unresize: true, sort: true}
                 {field: 'pkhsx', title: '贫困户属性'}
                , {field: 'hzxm', title: '户主姓名', minWidth:90}
                , {field: 'zjhm', title: '户主证件号码', minWidth:195}
                , {field: 'tpqk', title: '脱贫情况'}
                , {field: 'ndbqrw', title: '年度搬迁任务'}
                , {field: 'sjrzsj', title: '实际入住时间'}
                , {field: 'zfmj', title: '住房面积m²'}
                , {field: 'syrk', title: '受益人口'}
                , {title: '操作', toolbar: '#barDemo', fixed: 'right'}
            ]],
            done: function (res, curr, count) {
                $("[data-field='pkhsx']").children().each(function () {
                    if($(this).text() == '') {
                        $(this).html("<span style='color: red'>未填写</span>")
                    }
                });
                $("[data-field='zfmj']").children().each(function () {
                    if($(this).text() == 0) {
                        $(this).text("");
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
                case 'search' :
                    where();
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
                        url: '/query/jt/del',
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
                    title: '贫困户家庭基本信息',
                    type: 2,
                    // skin: 'layui-layer-rim', //加上边框
                    area: ['100%', '100%'], //宽高
                    content: '/query/jt/edit',
                    success : function(layero, index) {
                        var iframe = window['layui-layer-iframe' + index];
                        iframe.setBaseId(data.id);
                    },
                    end: function () {
                        parent.layui.table.reload('familyListTable');
                    }
                });

            }
        });

        // 条件查询操作
        function where() { console.log("where");
            var formData = {};
            var name = $("#search_name").val();
            var sfzhm = $("#search_sfzhm").val();
            formData.name = name;
            formData.sfzhm = sfzhm;
            table.reload('familyListTable', {
                url : '/query/jt/getList',
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