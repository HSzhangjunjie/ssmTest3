<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="../union/header-union.jsp" %>
    <script type="text/javascript">
        $(function () {
            // 调用后面的声明函数对页码导航条进行初始化操作
            initPagination();
        })

        function initPagination() {
            // 获取总记录数
            const totalRecord = "${requestScope.pageInfo.total}";
            // 生成一个JSON对象存储pagination要存储的属性
            const properties = {
                // 设置边缘页数
                num_edge_entries: 4,
                // 设置主体页数
                num_display_entries: 5,
                // 设置回调函数
                callback: pageSelectCallback,
                // 每页显示信息条数
                items_per_page:${requestScope.pageInfo.pageSize},
                // 当前页设置
                current_page: ${requestScope.pageInfo.pageNum-1},
                // 上一页下一页的显示信息
                prev_text: "上一页",
                next_text: "下一页"
            };
            // 生成页码导航条
            $("#pagination").pagination(totalRecord, properties);
        }

        // 回调函数即生命出来以后不由自己调用而是交给系统调用
        // 点击1、2、3等按钮实现跳转功能
        function pageSelectCallback(pageIndex, JQuery) {
            // 根据pageIndex计算得到pageNum
            const pageNum = pageIndex + 1;
            // 跳转页面
            window.location.href = "${pageContext.request.contextPath}/admin/pages?pageNum=" + pageNum + "&keyword=${param.keyword}";
            // 由于每一个页码按钮都是超链接，所以要取消超链接行为
            return false;
        }
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
                    <form action="${pageContext.request.contextPath}/admin/pages" method="post" class="form-inline"
                          role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="delete_btn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='${pageContext.request.contextPath}/admin/add'"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30"><label for="checkedAll"></label><input id="checkedAll" type="checkbox">
                                </th>
                                <th width="90">角色编号</th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.pageInfo.list}">
                                <tr>
                                    <td colspan="6" align="center">抱歉，未查询到您要找的数据！</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.pageInfo.list}">
                                <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="num">
                                    <tr>
                                        <td style="display: none">${admin.id}</td>
                                        <td><input class="check_item" type="checkbox"></td>
                                        <td>${num.count}</td>
                                        <td>${admin.loginAccount}</td>
                                        <td>${admin.userName}</td>
                                        <td>${admin.email}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/assign/roles?adminId=${admin.id}"
                                               type="button" class="btn btn-success btn-xs"><i
                                                    class=" glyphicon glyphicon-check"></i></a>
                                            <a href="${pageContext.request.contextPath}/admin/find/${admin.id}"
                                               class="btn btn-primary btn-xs"><i
                                                    class=" glyphicon glyphicon-pencil"></i></a>
                                            <a href="${pageContext.request.contextPath}/admin/remove/${admin.id}/${requestScope.pageInfo.pageNum}"
                                               class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                            <tfoot>

                            <tr>
                                <!--使用pagination的方式创建分页-->
                                <td colspan="6" align="center">
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
<script type="text/javascript">
    //完成全选、反选
    $("#checkedAll").click(function () {
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
        let loginAccounts = "";
        let ids = "";
        $.each($(".check_item:checked"), function () {
            loginAccounts += $(this).parents("tr").find("td:eq(4)").text() + " ";
            ids += $(this).parents("tr").find("td:eq(0)").text() + "-";
        })
        if (loginAccounts === "") {
            layer.msg("无效的操作");
            return false;
        }
        if (confirm("确认删除【" + loginAccounts + "】信息嘛？")) {
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/remove/" + ids,
                type: "POST",
                success: function (entity) {
                    if (entity.result === "SUCCESS") {
                        alert("success!");
                        window.location.href = '${pageContext.request.contextPath}/admin/pages';
                    }
                },
                error: function (entity) {
                    layer.msg(entity.message);
                }
            })
        } else {
            alert("确认好了再来吧！")
        }
    })
</script>
</body>
</html>
