<%--
  Created by IntelliJ IDEA.
  User: ZhangJunjie
  Date: 2020/5/10
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>众筹平台管理系统</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/pagination_zh/lib/pagination.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/doc.min.css">
<style>
    .tree li {
        list-style-type: none;
        cursor: pointer;
    }
</style>
<script src="${pageContext.request.contextPath}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jquery/jquery-form.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/script/docs.min.js"></script>
<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/pagination_zh/lib/jquery.pagination.js">
</script>
