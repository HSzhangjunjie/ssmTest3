function generateTree() {
    // 准备生成树形结构的JSON数据
    $.ajax({
        url: "/admin/menus",
        type: "GET",
        dataType: "json",
        success: function (resultEntity) {
            if (resultEntity.result === "SUCCESS") {
                // 创建JSON对象用于存储zTree所做设置
                const setting = {
                    view: {
                        selectedMulti: false,
                        // 实现diy图标显示功能
                        addDiyDom: function (treeId, treeNode) {
                            // treeId是附着的ul的id
                            // treeNode是当前树型节点的全部属性
                            // 根据控制图标的span标签的id找到这个span，tid的样式：tId = permissionTree_1，id的样式permissionTree_1_ico
                            const spanId = $("#" + treeNode.tId + "_ico");
                            // 删除旧的class添加新的class
                            if (treeNode.icon) {
                                spanId.removeClass().addClass(treeNode.icon);
                            }
                        },
                        // 按钮组显示的实现
                        addHoverDom: function (treeId, treeNode) {
                            const id = treeNode.id;
                            // 获取按钮组的超链接标签id
                            const anchorId = $("#" + treeNode.tId + "_a");
                            // 判断是否已经添加,防止重复添加
                            if (treeNode.editNameFlag || $("#btnGroup" + treeNode.tId).length > 0) {
                                return;
                            }
                            // 为了能精确定位span,在这里设置有规律的id
                            let s = '<span id="btnGroup' + treeNode.tId + '">';
                            // 增删改的按钮
                            const addBtn = "<a class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' data-tid='" + id + "' onclick='addBtn(this)'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg'></i></a>";
                            const deleteBtn = "<a class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' data-tid='" + id + "' onclick='deleteBtn(this)' >&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg'></i></a>";
                            const editBtn = "<a class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' data-tid='" + id + "' onclick='editBtn(this)'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
                            if (treeNode.level === 0) {
                                s += addBtn;
                            } else if (treeNode.level === 1) {
                                s += editBtn;
                                if (treeNode.children.length === 0) {
                                    s += deleteBtn;
                                }
                                s += addBtn;
                            } else if (treeNode.level === 2) {
                                s += editBtn;
                                s += deleteBtn;
                            }

                            s += "</span>";
                            anchorId.after(s);
                        },
                        // 按钮组消失的实现
                        removeHoverDom: function (treeId, treeNode) {
                            // 删除对应的span
                            $("#btnGroup" + treeNode.tId).remove();
                        }
                    },
                };
                // 获取数据
                const rootMenu = resultEntity.data;
                // 初始化树形结构
                $.fn.zTree.init($("#menuTree"), setting, rootMenu);
            } else {
                layer.msg(resultEntity.message);
            }
        },
        error: function (response) {
            layer.msg(response.status + " " + response.statusText);
        }

    })
}

function addBtn(object) {
    // 保存当前的id作为子节点的pid
    window.pid = $(object).attr('data-tid');
    // 打开模态框
    $("#menuAddModal").modal("show");
    return false;
}

function editBtn(object) {
    // 存储当前Id
    window.id = $(object).attr('data-tid');
    $(function () {
        $.ajax({
            url: "/admin/menus/" + window.id,
            type: "GET",
            dataType: "json",
            success: function (result) {
                if (result.result === "SUCCESS") {
                    const menu = result.data;
                    // 实现回显
                    $("#menuEditModal [name='name']").val(menu.name);
                    const url = menu.url;
                    if (url == null) {
                        $("#menuEditModal [name='url']").attr("style", "display: none");
                    } else {
                        $("#menuEditModal [name='url']").val(menu.url);
                    }
                    $("#menuEditModal [name='icon']").val([menu.icon]);
                    // 打开模态框
                    $("#menuEditModal").modal("show");
                }
            },
            error: function (response) {
                layer.msg(response.status + " " + response.statusText);
            }
        })
    })
    return false;
}

function deleteBtn(object) {
    // 存储当前Id
    window.id = $(object).attr('data-tid');
    // 打开模态框
    $("#menuConfirmModal").modal("show");
    return false;
}