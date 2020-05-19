<%--
  Created by IntelliJ IDEA.
  User: ZhangJunjie
  Date: 2020/5/7
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我们遇到了一个错误！</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <script src="${pageContext.request.contextPath}/static/jquery/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#back").click(function () {
                //调用浏览器的后退
                window.history.back();
            })
        })
    </script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="${pageContext.request.contextPath}/index.html" style="font-size:32px;">帅杰-创意产品众筹平台</a>
            </div>
        </div>
    </div>
</nav>
<div class="container" style="text-align: center">
    <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 错误的请求：</h2>
    <!--
        以下代码相当于request.getAttribute("exception").getMessage();
    -->
    <h3>${requestScope.exception.message}</h3>
    <button id="back" class="btn btn-sm btn-success">点击返回上一步</button>
</div>
</body>
</html>