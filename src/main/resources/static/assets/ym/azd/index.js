$(document).ready(function () {
    azd.onload();
});
var layer;
var azd = (function () {
    layui.use(['layer'], function(){
        layer = layui.layer;
        $('#xzazd').click(function(){
            layer.open({
                title : '新增安置点',
                type : 2,
                content : ['/yw/azd/add','no'],
                area: ['470px','420px'],
                offset: '15%',
                end : function(){
                    location.reload();
                }
            });
        });


    });



    // 列表
    $.ajax({
        url : '/yw/azd/getList',
        method : 'POST',
        contentType: 'application/json',
        dataType: 'JSON',
        success : function(res) {
            if(res.length != 0) {
                var column = 4;  // 每行显示四列
                var rowNum = Math.ceil(res.length / column); // 共有几行
                var yu = res.length % column;  // 取余
                var html = '';
                for(var i = 0; i < rowNum - 1; i++) {
                    var rowHtml = "<div class='row'>";
                    for(var j = (i * column); j < i * column + column; j++) {
                        var colHtml = '';
                        colHtml += "<div class='col-xs-6 col-sm-4 col-md-3 col-lg-2'>";
                        colHtml += "<div class='thumbnail search-thumbnail ss'>";
                        // if(j == 0) {
                        //     colHtml += '<span class="search-promotion label label-success arrowed-in arrowed-in-right">标记</span>'
                        // }
                        colHtml += '<img class="media-object" style="height: 149px" src="' +  (res[j].img != '' ? res[j].img : '/assets/images/gallery/image-5.jpg') + '"/>';
                        colHtml += '<div class="caption"><div class="clearfix"><span class="pull-right label label-grey info-label">钦南区</span></div></div>';
                        colHtml += '<h3 class="search-title"><a href="/yw/azd/fwxx?id=' + res[j].id +'" class="blue" title="' + res[j].mc +'">'+ res[j].jc +'</a></h3><p>' + res[j].dz +'</p>';
                        colHtml += ' <div class="tools tools-top"><a href="javascript:void(0);" onclick="azd.editAzd('+ res[j].id + ');"><i class="ace-icon fa fa-pencil"></i></a><a href="javascript:void(0);" onclick="azd.delAzd('+ res[j].id +');"><i class="ace-icon fa fa-times red"></i></a></div>';
                        colHtml += '</div></div>';
                        rowHtml += colHtml;
                    }
                    rowHtml += '</div>';
                    html += rowHtml;
                }

                if(yu == 0) {
                    var rowHtml = "<div class='row'>";
                    for(var j = (i * column); j < i * column + column; j++) {
                        var colHtml = '';
                        colHtml += "<div class='col-xs-6 col-sm-4 col-md-3 col-lg-2'>";
                        colHtml += "<div class='thumbnail search-thumbnail ss'>";
                        // if(j == 0) {
                        //     colHtml += '<span class="search-promotion label label-success arrowed-in arrowed-in-right">标记</span>'
                        // }
                        colHtml += '<img class="media-object" style="height: 149px" src="' +  (res[j].img != '' ? res[j].img : '/assets/images/gallery/image-5.jpg') + '"/>';
                        colHtml += '<div class="caption"><div class="clearfix"><span class="pull-right label label-grey info-label">钦南区</span></div></div>';
                        colHtml += '<h3 class="search-title"><a href="/yw/azd/fwxx?id=' + res[j].id +'" class="blue" title="' + res[j].mc +'">'+ res[j].jc +'</a></h3><p>' + res[j].dz +'</p>';
                        colHtml += ' <div class="tools tools-top"><a href="javascript:void(0);" onclick="azd.editAzd('+ res[j].id +');"><i class="ace-icon fa fa-pencil"></i></a><a href="javascript:void(0);" onclick="azd.delAzd('+ res[j].id +');"><i class="ace-icon fa fa-times red"></i></a></div>';
                        colHtml += '</div></div>';
                        rowHtml += colHtml;
                    }
                    rowHtml += '</div>';
                    html += rowHtml;
                }

                if(yu > 0) {
                    var rowHtml = "<div class='row'>";
                    for(var j = (column * (rowNum - 1)); j < column * (rowNum - 1) + yu; j++) {
                        var colHtml = '';
                        colHtml += "<div class='col-xs-6 col-sm-4 col-md-3 col-lg-2'>";
                        colHtml += "<div class='thumbnail search-thumbnail ss'>";
                        // if(j == 0) {
                        //     colHtml += '<span class="search-promotion label label-success arrowed-in arrowed-in-right">标记</span>'
                        // }
                        colHtml += '<img class="media-object" style="height: 149px" src="' +  (res[j].img != '' ? res[j].img : '/assets/images/gallery/image-5.jpg') + '"/>';
                        colHtml += '<div class="caption"><div class="clearfix"><span class="pull-right label label-grey info-label">钦南区</span></div></div>';
                        colHtml += '<h3 class="search-title"><a href="/yw/azd/fwxx?id=' + res[j].id +'" class="blue" title="' + res[j].mc +'">'+ res[j].jc +'</a></h3><p>' + res[j].dz +'</p>';
                        colHtml += ' <div class="tools tools-top"><a href="javascript:void(0);" onclick="azd.editAzd('+ res[j].id +');"><i class="ace-icon fa fa-pencil"></i></a><a href="javascript:void(0);" onclick="azd.delAzd('+ res[j].id +');"><i class="ace-icon fa fa-times red"></i></a></div>';
                        colHtml += '</div></div>';
                        rowHtml += colHtml;
                    }
                    rowHtml += '</div>';
                    html += rowHtml;
                }

                $("#items").html(html);
            }
            else {
                // todo 处理无数据的情况
            }
        },
        error: function (res) {
        }
    });
    return {
        onload: function () {
        },
        editAzd: function(id) {
            layer.open({
                title : '更新安置点',
                type : 2,
                content : ['/yw/azd/add?id=' + id ,'no'],
                area: ['470px','420px'],
                offset: '15%',
                end : function(){
                    location.reload();
                }
            });
        },
        delAzd: function(id) {
            var name = $(event.currentTarget).parents('.ss').find('a.blue').text();
            layer.confirm('确定删除安置点-' + name +'?', function(layerid){
                $.ajax({
                    url: '/yw/azd/delAzd',
                    data: {id: id},
                    method: 'get',
                    contentType: "application/json",
                    success: function(res) {
                        if(res.code == -1) {
                            layer.msg(res.msg);
                        }
                        if(res.data > 0) {
                            layer.msg('✔ 删除成功!', function() {
                                location.reload();
                            });
                        }
                    },
                    error: function() {
                        layer.msg('删除失败，请稍后再试.');
                    }
                });
                layer.close(layerid);
            }, function(){
                alert("取消删除");
            });
        }
    };
})();