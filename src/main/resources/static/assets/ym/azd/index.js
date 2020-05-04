$(document).ready(function () {
    azd.onload();
});

var azd = (function () {

    // 列表
    $.ajax({
        url : '/yw/azd/getList',
        method : 'POST',
        contentType: 'application/json',
        dataType: 'JSON',
        success : function(res) {
            if(res.length != 0) {

                var column = 4;  // 每行显示四列
                var rowNum = res.length % column + 1;
                var html = '';
                for(var i = 0; i < rowNum; i++) {
                    var count = 0;
                    if(i == rowNum - 1) {
                        count = res.length % column;
                    }
                    else {
                        count = column;
                    }

                    var rowHtml = "<div class='row'>";
                    for(var j = (i * column); j < i * column + count; j++) {
                        var colHtml = '';
                        colHtml += "<div class='col-xs-6 col-sm-4 col-md-3'>";
                        colHtml += "<div class='thumbnail search-thumbnail'>";
                        if(j == 0) {
                            colHtml += '<span class="search-promotion label label-success arrowed-in arrowed-in-right">标记</span>'
                        }
                        colHtml += '<img class="media-object" src="/assets/images/gallery/image-5.jpg"/>';
                        colHtml += '<div class="caption"><div class="clearfix"><span class="pull-right label label-grey info-label">钦南区</span></div></div>';
                        colHtml += '<h3 class="search-title"><a href="/yw/azd/fwxx?id=' + res[j].id +'" class="blue">'+ res[j].jc +'</a></h3><p>' + res[j].dz +'</p>';
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

        }
    };
})();