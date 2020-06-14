$(document).ready(function () {
    setRole.onload();
});
var setRole = (function () {

    layui.use(['form', 'element',  'table', 'laydate'], function () {
        var element, laydate, form, table;
        element = layui.element;
        form = layui.form;

        var id = $("#id").val();

        // 加载角色
        $.ajax({
            url: '/xt/user/listRole?id=' + id,
            method: 'get',
            async : false,
            contentType: "application/json",
            success: function (res) {
                console.log(res);
                var appendHtml = '';
                $.each(res.data, function(i,n){
                    appendHtml += '<tr>';
                    appendHtml += '<td class="fp_nrbt"><input hidden name="roleid" value="'+ n.id +'"/><input type="checkbox" lay-filter="check" name="" lay-skin="primary" ' + (n.userid > 0 ? 'checked':'') + '/></td>';
                    appendHtml += '<td class="fp_nrbt">'+ n.mc +'</td>';
                    appendHtml += '<td class="fp_nrbt">'+ n.sm +'</td>';
                    appendHtml += '</tr>';
                });
                $('#tth').after(appendHtml);

                // form.render();
            },
            error: function () {
                layer.msg("加载角色失败");
            }
        });

        form.on('checkbox(check)', function(data){
            var roleid = $(data.elem).prev().val();
            var status = data.elem.checked;
            var userid = id;

            $.ajax({
                url : '/xt/user/setRole2',
                method : 'POST',
                data :{roleid : parseInt(roleid), status : status, userid : parseInt(userid)}
            });

            // console.log(data.elem); //得到checkbox原始DOM对象
            // console.log(data.elem.checked); //是否被选中，true或者false
            // console.log(data.value); //复选框value值，也可以通过data.elem.value得到
            // console.log(data.othis); //得到美化后的DOM对象
        });

        form.render('checkbox', 'ss');
    });

    return {
        onload: function () {

        }
    };
})();



