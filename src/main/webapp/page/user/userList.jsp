<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>人员列表</title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <%@include file="../css.jsp"%>
</head>

<body data-type="generalComponents">

    <%@include file="../top.jsp"%>

    <div class="tpl-page-container tpl-page-header-fixed">

        <%@include file="../menu.jsp"%>

        <div class="tpl-content-wrapper">
            <div class="tpl-content-page-title">
                人员管理
            </div>
            <ol class="am-breadcrumb">
                <li><a href="/index.html" class="am-icon-home">首页</a></li>
                <li><a href="javascript:void(0);">后台管理</a></li>
                <li class="am-active">人员管理</li>
            </ol>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 人员列表
                    </div>

                </div>
                <div class="tpl-block">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6">
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <span class="am-icon-plus"></span>
                                </div>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-3">
                            <div class="am-form-group">
                                <select id="userTypeParam" data-am-selected="{btnSize: 'sm'}" class="initSelect" typeId="userType">
                                  <option value="">点击选择</option>
                                  <option value="#value">#name</option>
                                </select>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-3">
                            <div class="am-input-group am-input-group-sm">
                                <input id="userNameParam" type="text" class="am-form-field" placeholder="用户名.." value="${userName}">
                                <span class="am-input-group-btn">
                                    <button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search" type="button" onclick="userList();"></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                            <form class="am-form">
                                <table class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                        <tr>
                                            <th class="table-id">ID</th>
                                            <th class="table-author">用户名</th>
                                            <th class="table-date">密码</th>
                                            <th class="table-type">姓名</th>
                                            <th class="table-type">email</th>
                                            <th class="table-type">电话</th>
                                            <th class="table-type">用户类别</th>
                                            <th class="table-set">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${page.list}" var="user">
                                            <tr>
                                                <td>${user.id}</td>
                                                <td >${user.username}</td>
                                                <td >${user.password}</td>
                                                <td >${user.name}</td>
                                                <td >${user.email}</td>
                                                <td >${user.phone}</td>
                                                <td >${user.userTypeView}</td>
                                                <td>
                                                    <div class="am-btn-toolbar">
                                                        <div class="am-btn-group am-btn-group-xs">
                                                            <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="auditUser(${user.id},${user.userType});"><span class="am-icon-pencil-square-o"></span> 审核通过</button>
                                                            <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="deleteuser(${user.id});"><span class="am-icon-trash-o"></span> 删除</button>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <%@include file="../page.jsp"%>

                                <hr>

                            </form>
                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
            </div>


        </div>

    </div>

</body>


<script type="text/javascript">
    /**
     * 审核通过
     */
    function auditUser(userId,userType) {
        if("1"!=userType&&"3"!=userType){
            alert("此用户非待审核用户！");
            return;
        }
        $.get("/system/auditUser", {"id" : userId,"userType" : userType}, function (data) {
            this.userList();
        });
    }

    /**
     * 查询用户
     */
    function userList() {
        var userTypeParam = $("#userTypeParam").val();
        var userNameParam = $("#userNameParam").val();
        window.location = '/system/userList?username=' + userNameParam + '&userType=' + userTypeParam;
    }

    /**
     * 删除用户
     */
    function deleteuser(userId) {
        $.get("/system/deleteJson", {"id" : userId}, function (data) {
            this.userList();
        });
    }
</script>

</html>