$(document).ready(function () {
    photo.onload();
});

var photo = (function () {
    layui.use('layer', function() {
        var layer = layui.layer;

        var fid = $("#fid").val();
        var type = $('#type').val();

        $('#add').click(function() {
            layer.open({
                title: '影像资料上传',
                type: 2,
                area: ['50%', '68%'],
                content: '/yw/jt/uploadImg?fid=' + fid + "&type=" + type,
                end: function () {
                    $(window.parent.document).find("iframe")[0].contentWindow.location.reload(true); // 刷新父iframe
                }
            });
        });

        // 进入页面加载
        $.get('/yw/jt/getImgList',{fid : fid}, function(res){
            $("#imgUl").html('');
            var imgStr = "";
            $.each(res.data, function(i, n) {
                var tmp =
                '<li><a href="' + n.url +'" data-rel="colorbox">' +
                '<img width="150" height="150" alt="150x150" src="' + n.url +'" /></a>' +
                '<div class="tools tools-top">' +
                // '<a href="#"><i class="ace-icon fa fa-link"></i></a>' +
                // '<a href="#"><i class="ace-icon fa fa-paperclip"></i></a>' +
                // '<a href="#"><i class="ace-icon fa fa-pencil"></i></a>' +
                '<a href="javascript:void(0);" onclick="photo.del(' + n.id +')"> <i class="ace-icon fa fa-times red"></i></a>' +
                '</div>' +
                '</li>';
                imgStr += tmp;
            });
            $("#imgUl").html(imgStr);
            showImg();
        });
    });

    // 以图片框展示函数
    var showImg = function() {
        var $overflow = '';
        var colorbox_params = {
            rel: 'colorbox',
            reposition:true,
            scalePhotos:true,
            scrolling:false,
            previous:'<i class="ace-icon fa fa-arrow-left"></i>',
            next:'<i class="ace-icon fa fa-arrow-right"></i>',
            close:'&times;',
            current:'{current} / {total}',
            maxWidth:'100%',
            maxHeight:'100%',
            onOpen:function(){
                $overflow = document.body.style.overflow;
                document.body.style.overflow = 'hidden';
            },
            onClosed:function(){
                document.body.style.overflow = $overflow;
            },
            onComplete:function(){
                $.colorbox.resize();
            }
        };

        $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
        $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon

        $(document).one('ajaxloadstart.page', function(e) {
            $('#colorbox, #cboxOverlay').remove();
        });
    };
    // 删除图片
    var delImg = function(id) {
        layer.confirm('是否删除该图片', function() {
            $.get('/yw/jt/delImg', {id : id}, function(res){
                if(res.code == 0) {
                    layer.msg('删除成功', {icon: 1}, function(){
                        $(window.parent.document).find("iframe")[0].contentWindow.location.reload(true);
                    });
                } else {
                    layer.msg('删除失败', {icon: 2});
                }
            });
        });

    };
    return {
        onload: function () {
        },
        del: function(id) {
            delImg(id);
        }
    };
})();