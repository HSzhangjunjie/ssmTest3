<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%@ include file="../union/header-union.jsp" %>
</head>
<body>
<%@ include file="../union/nav-union.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="../union/sidebar-union.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin/main">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/pages">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form action="/admin/save" method="post" role="form">
                        <p>${requestScope.exception.message}</p>
                        <div class="form-group">
                            <label for="loginAccount">登录账号</label>
                            <input type="text" class="form-control" id="loginAccount" name="loginAccount"
                                   placeholder="请输入登录账号">
                        </div>
                        <div class="form-group">
                            <label for="userPassword">登录密码</label>
                            <input type="password" class="form-control" id="userPassword" name="userPassword"
                                   placeholder="请输入登录密码">
                        </div>
                        <div class="form-group">
                            <label for="userName">用户昵称</label>
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户昵称">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="请输入邮箱地址">
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增
                        </button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                        </button>
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
                    <h4>没啥可帮的，自己想办法</h4>
                    <p>麻溜的！</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
