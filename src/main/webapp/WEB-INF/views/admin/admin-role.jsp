<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <%@include file="../union/header-union.jsp" %>
    <style>
        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ztree/zTreeStyle.css">
    <script src="${pageContext.request.contextPath}/static/ztree/jquery.ztree.all-3.5.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/crowd/my-menu.js"></script>
    <script src="${pageContext.request.contextPath}/static/crowd/my-role.js"></script>
    <script type="text/javascript">
        $(function () {
            // 为分页初始化数据
            window.pageNum = 1;
            window.pageSize = 5;
            window.keyword = "";
            // 刷新页面
            generatePage();
            // 绑定单击事件
            $(document).on('click', "#searchBtn", function () {
                window.keyword = document.getElementById("keywordInput").value;
                generatePage();
            });
        });
    </script>
</head>
<body>
<%@ include file="../union/nav-union.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file="../union/sidebar-union.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="delete_btn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;">
                        <i
                                class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button id="add_btn" type="button" class="btn btn-primary" style="float:right;"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30"><input id="checkedAll" type="checkbox"></th>
                                <th width="100">角色编号</th>
                                <th>角色名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody"></tbody>
                            <tfoot>
                            <tr>
                                <!--使用pagination的方式创建分页-->
                                <td colspan="4" align="center">
                                    <div id="pagination" class="pagination">

                                    </div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../modal/modal-role-add.jsp" %>
<%@include file="../modal/modal-role-edit.jsp" %>
<%@include file="../modal/modal-role-auth.jsp" %>
<script type="text/javascript">
    // 多选/批量删除实现
    // 完成全选、反选
    $("#checkedAll").on("click", function () {
        //prop是dom原生的属性，attr是自定义属性
        $(".check_item").prop("checked", $(this).prop("checked"));
    })
    //单选后取消全选按钮
    $(document).on("click", ".check_item", function () {
        //判断当前选中的元素是否为五个
        $("#checkedAll").prop("checked", $(".check_item:checked").length === 5);
    })
    //点击全部删除
    $("#delete_btn").click(function () {
        let roleNames = "";
        let ids = "";
        $.each($(".check_item:checked"), function () {
            roleNames += $(this).parents("tr").find("td:eq(3)").text() + " ";
            ids += $(this).parents("tr").find("td:eq(0)").text() + "-";
        })
        if (roleNames === "") {
            layer.msg("无效的操作");
            return false;
        }
        if (confirm("确认删除【" + roleNames + "】信息嘛？")) {
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/roles/" + ids,
                type: "DELETE",
                dataType: "json",
                success: function (result) {
                    if (result.result === "SUCCESS") {
                        alert(roleNames + "信息删除成功！");
                        // 刷新分页
                        generatePage();
                    }
                },
                error: function (response) {
                    layer.msg(response.status + " " + response.statusText);
                }
            })
        } else {
            alert("确认好了再来吧！")
        }
    })

    // 添加角色信息实现
    // 点击新增打开模态框
    $("#add_btn").click(function () {
        $("#addModal").modal("show");
    })
    $("#saveBtn").click(function () {
        // 获取用户输入的角色名称
        const roleName = $("#roleNameAdd").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/roles",
            type: "POST",
            data: {
                "roleName": roleName
            },
            dataType: "json",
            success: function (result) {
                if (result.result === "SUCCESS") {
                    alert("操作成功！");
                    // 刷新分页
                    generatePage();
                } else {
                    layer.msg("操作失败！：" + result.message);
                }
            },
            error: function (response) {
                layer.msg(response.status + " " + response.statusText);
            }
        })
        // 关闭模态框
        $("#addModal").modal("hide");
        // 清空模态框
        $("#roleName").val("");
    })

    // 更新角色信息实现
    // 回显/模态框开启功能实现
    $("#rolePageBody").on("click", ".updateBtn", function () {
        $("#editModal").modal("show");
        const roleId = $(this).parents("tr").find("td:eq(0)").text();
        const roleName = $(this).parents("tr").find("td:eq(3)").text();
        $("#roleNameUpdate").val(roleName);
        $("#roleIdUpdate").val(roleId);
    })

    $("#updateBtn").click(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/roles/" + $("#roleIdUpdate").val(),
            type: "PUT",
            data: {
                roleName: $("#roleNameUpdate").val(),
            },
            dataType: "json",
            success: function (result) {
                if (result.result === "SUCCESS") {
                    alert("操作成功！");
                    // 刷新分页
                    generatePage();
                } else {
                    layer.msg("操作失败！：" + result.message);
                }
            },
            error: function (response) {
                layer.msg(response.status + " " + response.statusText);
            }
        })
        // 关闭模态框
        $("#editModal").modal("hide");
        // 清空模态框
        $("#roleNameUpdate").val("");
        $("#roleIdUpdate").val("");
    })

    // 删除角色信息的实现
    $("#rolePageBody").on("click", ".deleteBtn", function () {
        const roleId = $(this).parents("tr").find("td:eq(0)").text();
        const roleName = $(this).parents("tr").find("td:eq(3)").text();
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/roles/" + roleId,
            type: "DELETE",
            dataType: "json",
            success: function (result) {
                if (result.result === "SUCCESS") {
                    alert(roleName + "信息删除成功！");
                    // 刷新分页
                    generatePage();
                }
            },
            error: function (response) {
                layer.msg(response.status + " " + response.statusText);
            }
        })
    })

    $("#rolePageBody").on("click", ".authBtn", function () {
        // 存储roleId
        window.roleId = $(this).parents("tr").find("td:eq(0)").text();
        // 打开模态框
        $("#authAssignModal").modal("show");
        // 装在树形结构数据
        fillAuthTree();
    })
    // 给分配许可绑定单机响应函数
    $("#assignBtn").click(function () {
        // 获取zTreeObj对象
        var authArray = [];
        const zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
        // 收集已选节点信息
        const checkedNodes = zTreeObj.getCheckedNodes();
        // 遍历checkedNode
        for (let i = 0; i < checkedNodes.length; i++) {
            authArray.push(checkedNodes[i].id);
        }
        let requestBody = {
            "authArray": authArray,
            "roleId": [window.roleId]
        };
        requestBody = JSON.stringify(requestBody);
        $.ajax({
            url: "${pageContext.request.contextPath}/assign/authIds",
            type: "POST",
            data: requestBody,
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                if (result.result === "SUCCESS") {
                    alert("更新成功！");
                }
            },
            error: function (response) {
                layer.msg(response.status + " " + response.statusText);
            },
        })
        // 关闭模态框
        $("#authAssignModal").modal("hide");
    })
</script>
</body>
</html>
