$(document).ready(function () {
    grant.onload();
});
var grant = (function () {
    var zTreeObj;
    var setting = {
        data: {
            key: {
                name: "mc"
            }
        },
        check: {
            enable: true
        },
        callback: {
            onCheck: function (event, treeId, treeNode) {
                var ids = [];
                $.each(zTreeObj.getCheckedNodes(), function (i, n) {
                    ids.push(n.id);
                });
                // 执行实际的更新操作
                $.ajax({
                    url: "/xt/role/grant",
                    dataType: "json",
                    type: "POST",
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    },
                    data: JSON.stringify({roleId: roleId, ids: ids}),
                    error: function(res) {
                        // todo 权限更新失败提示
                        alert("更新失败");
                    }
                });
            }
        }
    };

    var roleId = $("#id").val();
    var menuList;
    // ① 加载数据
    $.ajax({
        url: "/xt/role/getMenuTree?roleId=" + roleId,
        dataType: "json",
        type: "GET",
        async: false,
        success: function (res) {
            menuList = res.data;
        }
    });
    zTreeObj = $.fn.zTree.init($("#menuTree"), setting, removeEmptyChildren(menuList));


    /**
     * children为空时，删除该属性
     */
    function removeEmptyChildren(tree) {
        $.each(tree, function (i, n) {
            if (n.children.length == 0) {
                delete n.children;
            }
            else {
                removeEmptyChildren(n.children);
            }
        });
        return tree;
    }

    return {
        onload: function () {
        }
    };
})();
