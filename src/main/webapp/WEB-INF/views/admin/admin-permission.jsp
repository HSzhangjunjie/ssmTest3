<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="../union/header-union.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ztree/zTreeStyle.css">
    <script src="${pageContext.request.contextPath}/static/ztree/jquery.ztree.all-3.5.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/crowd/my-menu.js"></script>
    <script type="text/javascript">
        $(function () {
            generateTree();
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
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <ul id="menuTree" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>没有什么提示</h4>
                    <p>自己想办法</p>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../modal/modal-menu-add.jsp" %>
<%@include file="../modal/modal-menu-edit.jsp" %>
<%@include file="../modal/modal-menu-confirm.jsp" %>
<script type="text/javascript">
    $(function () {
        // 给添加模态框绑定单击响应函数
        $("#menuSaveBtn").click(function () {
            // 收集表单项的数据
            const name = $.trim($("#menuAddModal [name='name']").val());
            const url = $.trim($("#menuAddModal [name='url']").val());
            const icon = $.trim($("#menuAddModal [name='icon']:checked").val());
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/menus",
                type: "POST",
                data: {
                    pid: window.pid,
                    name: name,
                    url: url,
                    icon: icon
                },
                dataType: "json",
                success: function (result) {
                    if (result.result === "SUCCESS") {
                        alert("添加成功！");
                        // 刷新分页
                        generateTree();
                    } else {
                        layer.msg("添加失败！：" + result.message);
                    }
                },
                error: function (response) {
                    layer.msg(response.status + " " + response.statusText);
                }
            })
            // 关闭模态框
            $("#menuAddModal").modal("hide");
            // 清空表单
            $("#menuResetBtn").click();
        })
        // 给修改模态框绑定单击响应函数
        $("#menuEditBtn").click(function () {
            // 收集表单项的数据
            const name = $.trim($("#menuEditModal [name='name']").val());
            const url = $.trim($("#menuEditModal [name='url']").val());
            const icon = $.trim($("#menuEditModal [name='icon']:checked").val());
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/menus/" + window.id,
                type: "PUT",
                data: {
                    name: name,
                    url: url,
                    icon: icon
                },
                dataType: "json",
                success: function (result) {
                    if (result.result === "SUCCESS") {
                        alert("修改成功！");
                        // 刷新分页
                        generateTree();
                    } else {
                        layer.msg("修改失败！：" + result.message);
                    }
                },
                error: function (response) {
                    layer.msg(response.status + " " + response.statusText);
                }
            })
            // 关闭模态框
            $("#menuEditModal").modal("hide");
            // 清空模态框
            $("#editForm").reset();
        })
        // 给删除模态框绑定单击响应函数
        $("#confirmBtn").click(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/menus/" + window.id,
                type: "DELETE",
                dataType: "json",
                success: function (result) {
                    if (result.result === "SUCCESS") {
                        alert("删除成功！");
                        // 刷新分页
                        generateTree();
                    }
                },
                error: function (response) {
                    layer.msg(response.status + " " + response.statusText);
                }
            })
            // 关闭模态框
            $("#menuConfirmModal").modal("hide");
        })
    })
</script>
</body>
</html>
