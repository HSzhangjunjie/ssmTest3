<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="../union/header-union.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#toRightBtn").click(function () {
                $("#unassignedRoles>option:selected").appendTo($("#assignedRoles"));
            })
            $("#toLeftBtn").click(function () {
                $("#assignedRoles>option:selected").appendTo($("#unassignedRoles"));
            })
            // 提交时变为全选
            $("#assignRolesForm").submit(function () {
                $("#assignedRoles>option").prop("selected", "selected");
            })
        })
    </script>
</head>

<body>
<%@ include file="../union/nav-union.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="../union/sidebar-union.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin/main">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/pages">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form id="assignRolesForm" action="${pageContext.request.contextPath}/assign/doAssign" method="post"
                          role="form"
                          class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}">
                        <div class="form-group">
                            <label for="unassignedRoles">未分配角色列表</label><br>
                            <select id="unassignedRoles" class="form-control" multiple="multiple" size="10"
                                    style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unassignedRoles}" var="role">
                                    <option value="${role.roleId}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br/>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="assignedRoles">已分配角色列表</label><br>
                            <select name="assignedRoles" id="assignedRoles" class="form-control" multiple="multiple"
                                    size="10"
                                    style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignedRoles}" var="role">
                                    <option value="${role.roleId}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn btn-sm btn-success btn-block">保存</button>
                        </div>
                    </form>
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
                    <h4>没什么可帮助的</h4>
                    <p>别看了 没什么帮助</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
