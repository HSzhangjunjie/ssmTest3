// 生成页面效果
function generatePage() {
    getPageInfoRemote();
}

// 远程访问服务器端获取pageInfo数据
function getPageInfoRemote() {
    $.ajax({
        url: "/admin/rolePages",
        type: "post",
        data: {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        dataType: "json",
        success: function (result) {
            // 判断result是否成功
            if (result.result === "ERROR") {
                layer.msg(result.message);
                fillTableBody(null);
            } else {
                // 确认成功后获取pageInfo
                const pageInfo = result.data;
                // 填充表格
                fillTableBody(pageInfo);
            }
        },
        error: function (result) {
            layer.msg("失败！响应状态码：" + status + "，失败原因：" + result.message);
            fillTableBody(null);
        }
    });
}

// 填充表格
function fillTableBody(pageInfo) {
    // 清除旧数据
    $("#rolePageBody").empty();
    // 判断pageInfo是否有效
    $("#pagination").empty();
    if (pageInfo === null || pageInfo === undefined || pageInfo.list === null || pageInfo.list.length === 0) {
        $("#rolePageBody").append("<tr><td colspan='4'>抱歉没查询到您需要的数据</td></tr>")
        return null;
    }
    // 使用pageInfo填充数据
    for (let i = 0; i < pageInfo.list.length; i++) {
        const role = pageInfo.list[i];
        const roleId = role.roleId;
        const roleName = role.roleName;
        const roleIdTd = "<td style='display: none'>" + roleId + "</td>"
        const numberTd = "<td>" + (i + 1) + "</td>";
        const checkBoxTd = "<td><input class='check_item' type='checkbox'></td>";
        const roleNameTd = "<td>" + roleName + "</td>";

        const btnTd = "<td><button type='button' class='btn btn-success btn-xs authBtn'><i class='glyphicon glyphicon-check'></i></button>" + " " + "<button type='button' class='btn btn-primary btn-xs updateBtn'><i class='glyphicon glyphicon-pencil'></i></button>" + " " + "<button type='button' class='btn btn-danger btn-xs deleteBtn'><i class=' glyphicon glyphicon-remove'></i></button></td>";
        const tr = "<tr>" + roleIdTd + checkBoxTd + numberTd + roleNameTd + btnTd + "</tr>";
        $("#rolePageBody").append(tr);
    }
    // 生成导航条
    generateNavigator(pageInfo);
}

// 生成分页页面导航条
function generateNavigator(pageInfo) {
    // 获取总记录数
    const totalRecord = pageInfo.total;
    // 声明相关属性
    const properties = {
        // 设置边缘页数
        num_edge_entries: 4,
        // 设置主体页数
        num_display_entries: 5,
        // 设置回调函数
        callback: pagenationCallBack,
        // 每页显示信息条数
        items_per_page: window.pageSize,
        // 当前页设置
        current_page: window.pageNum - 1,
        // 上一页下一页的显示信息
        prev_text: "上一页",
        next_text: "下一页"
    };
    // 生成页码导航条
    $("#pagination").pagination(totalRecord, properties);
}

// 翻页时回调函数
function pagenationCallBack(pageIndex, jQuery) {
    // 修改全局变量
    window.pageNum = pageIndex + 1;
    // 调用翻页函数
    generatePage();
    // 由于每一个页码按钮都是超链接，所以要取消超链接行为
    return false;
}

function fillAuthTree() {
    $.ajax({
        url: "/assign/auths",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            // 判断result是否成功
            if (result.result === "SUCCESS") {
                const auths = result.data;
                const setting = {
                    data: {
                        simpleData: {
                            // 查询到的list不需要组装成树形结构，不过需要配置以下内容
                            enable: true,
                            // 设置pid的属性为categoryId
                            pIdKey: "categoryId"
                        },
                        // 修改默认属性
                        key: {
                            // 使用title名称为属性名
                            name: "title"
                        }
                    },
                    check: {
                        // 显示复选框/单选框
                        enable: true,
                    }
                };
                // 生成树形结构
                $.fn.zTree.init($("#treeDemo"), setting, auths);
                // 获取zTreeObj对象
                const zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
                // 把节点全部展开
                zTreeObj.expandAll(true);
                // 获取auth组成的id数组
                $.ajax({
                    url: "/assign/authIds/" + window.roleId,
                    type: "GET",
                    dataType: "json",
                    async: false,
                    success: function (result) {
                        // 判断result是否成功
                        if (result.result === "SUCCESS") {
                            window.ids = result.data;
                        }
                    },
                    error: function (result) {
                        layer.msg("失败！响应状态码：" + status + "，失败原因：" + result.message);
                    }
                })
                // 根据ids吧树型结构中对应的节点勾选
                if (window.ids !== null) {
                    // 遍历id
                    for (let i = 0; i < window.ids.length; i++) {
                        const authId = window.ids[i];
                        // 根据id从树型结构中找到对应节点
                        const treeNode = zTreeObj.getNodeByParam("id", authId);
                        // 将treeNode设置被被勾选
                        // checked为true表示为勾选，checkTypeFlag为false表示不联动
                        zTreeObj.checkNode(treeNode, true, false);
                    }
                }
            }
        },
        error: function (result) {
            layer.msg("失败！响应状态码：" + status + "，失败原因：" + result.message);
        }
    })

}

