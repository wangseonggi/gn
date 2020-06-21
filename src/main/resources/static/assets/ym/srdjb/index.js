$(document).ready(function () {
    income.onload();
});

var income = (function () {

    layui.use(['table','layer','laydate'], function () {
        var table = layui.table;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var familyTabel = table.render({
            elem: '#familyListTable',
            url: '/yw/sr/index',
            page: true,
            toolbar: '#toolbarDemo',
            limits: [10, 15, 20, 25],
            limit: 10,
            even: true,
            title: '贫困户家庭基本情况',
            cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', width: 80, unresize: true, sort: true}
                , {field: 'zjhm', title: '证件号码',width: 200}
                , {field: 'hzxm', title: '户主姓名', width: 100}
                // , {field: 'pkhsx', title: '贫困户属性'}
                , {field: 'nf', title: '收入登记表年度'}
                , {title: '操作', toolbar: '#barDemo',fixed: 'right',width: 160}
            ]],
            done: function (res, curr, count) {
                $("[data-field='nf']").children().each(function (i, n) {
                    if ($(this).text() != '' && $(this).text() != '收入登记表年度') {
                        var html = '';
                        var nfList = $(this).text().split(",");
                        $.each(nfList, function(ii, nn) {
                            html += '<a href="/yw/sr/add?fid='+ $($("[data-field='id']").children()[i]).text() +'&year=' + nn + '">' + nn +'</a>,' ;
                        });
                        html = html.substr(0, html.length - 1);
                        $(this).html(html);
                    }
                    if($(this).text() == '') {
                        $(this).css('color', 'red');
                        $(this).text('------');
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
            switch (obj.event) {
                case 'search' :
                    where();
                    break;
            }
        });


        //监听工具条
        table.on('tool(userList)', function (obj) {
            var data = obj.data;

            // 弹框选择年份
            layer.ready(function() {
                layer.open({
                    title: '选择登记表所属年份',
                    type: 1,
                    area: ['600px', '200px'], //根据自己要求调整
                    btn: ['确认', '取消'],
                    content: $('#showcf'),
                    success: function () {
                        laydate.render({
                            elem : "#selectYear",
                            type: 'year'
                        });
                    },
                    btn2: function (index) { //取消按钮事件
                        layer.close(index)
                    },
                    yes : function(index) {
                        var year  = $("#selectYear").val();
                        if(!year) {
                            alert('请选择年份');
                        }
                        else {
                            // todo
                            // 如果该年度没有记录，可以加一个提示
                            window.location.href = "/yw/sr/add?fid=" + obj.data.id + "&year=" + year;
                        }

                        // layer.close(index);
                    }

                });
            });
        });


        // 条件查询操作
        function where() {
            var formData = {};

            var name = $("#search_name").val();
            var sfzhm = $("#search_sfzhm").val();
            formData.name = name;
            formData.sfzhm = sfzhm;
            table.reload('familyListTable', {
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