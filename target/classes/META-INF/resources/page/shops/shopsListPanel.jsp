<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商铺管理</title>
    <meta name="description" content="商铺管理">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <%@include file="../css.jsp" %>
</head>

<body data-type="generalComponents">

<%@include file="../top.jsp" %>

<div class="tpl-page-container tpl-page-header-fixed">

    <%@include file="../menu.jsp" %>

    <div class="tpl-content-wrapper">
        <div class="tpl-content-page-title">
            商铺管理
        </div>
        <ol class="am-breadcrumb">
            <li><a href="/index.html" class="am-icon-home">首页</a></li>
            <li><a href="javascript:void(0);">服装商城系统</a></li>
            <li class="am-active">商铺管理</li>
        </ol>
        <div class="tpl-portlet-components">
            <div class="portlet-title">
                <div class="caption font-green bold">
                    <span class="am-icon-code"></span> 商铺列表
                </div>
            </div>
            <div class="tpl-block">
                <div class="am-g">
                    <div class="am-u-sm-12 am-u-md-6">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <button type="button" class="am-btn am-btn-default am-btn-success" onclick="addShops()"><span class="am-icon-plus"></span> 新增</button>
                            </div>
                        </div>
                    </div>
                    <form action="/shops/shopsListPanel">
                        <input type="hidden" name="pageNum">
                        <input type="hidden" name="pageSize">
                        <div class="am-u-sm-12 am-u-md-3">
                            <div class="am-input-group am-input-group-sm">
                                <input id="userIdParam" type="hidden"  value="${loginUser.id}">
                                <input id="shopsNameParam" type="text" class="am-form-field" placeholder="商铺名称..."
                                       value="${shopsName}">
                                <span class="am-input-group-btn">
                                    <button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search" type="button"
                                            onclick="shopsList();"></button>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="am-g">
                    <div class="tpl-table-images">

                        <c:forEach items="${page.list}" var="shops">

                            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                                <div class="tpl-table-images-content">
                                    <div class="tpl-table-images-content-i-time">发布时间：${shops.createdDtView}</div>
                                    <div class="tpl-i-title">
                                            ${shops.shopsName}
                                    </div>
                                    <a href="javascript:;" class="tpl-table-images-content-i">
                                        <div class="tpl-table-images-content-i-info">
                                            <span class="ico">
                                                <img src="../assets/img/userLogo.jpg" alt="">${shops.createdBy}
                                             </span>

                                        </div>
                                        <span class="tpl-table-images-content-i-shadow"></span>
                                        <img src="${shops.image}" alt="" width="600;" height="300px;">
                                    </a>
                                    <div class="tpl-table-images-content-block">
                                        <div class="tpl-i-font">
                                            详细信息：${shops.shopsRemark}
                                        </div>
                                        
                                        <c:if  test="${loginUser.username=='admin'}">
	                                        <div class="am-btn-toolbar">
	                                            <div class="am-btn-group am-btn-group-xs tpl-edit-content-btn">
	                                                <button type="button" class="am-btn am-btn-default am-btn-warning"
	                                                        onclick="deleteShops(${shops.id});">
	                                                    <span class="am-icon-archive"></span> 删除
	                                                </button>
	                                            </div>
	                                        </div>
                                        </c:if>
                                        
                                    </div>
                                </div>
                            </div>

                        </c:forEach>

                        <div class="am-u-lg-12">
                            <%@include file="../page.jsp"%>
                            <hr>
                        </div>

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
     * 查询商铺
     */
    function shopsList() {
        var userIdParam = $("userIdParam").val();
        var shopsNameParam = $("#shopsNameParam").val();
        if(!userIdParam) {
            userIdParam = ${loginUser.id};
        }
        window.location = '/shops/shopsListPanel?shopsName=' + shopsNameParam + '&userId=' + userIdParam;
    }
    /**
     * 删除商铺
     */
    function deleteShops(shopsId) {
        var me = this;
        $.get("/shops/deleteJson", {"id" : shopsId}, function (data) {
            me.shopsList();
        });
    }
    /**
     * 添加商铺
     */
     function addShops() {
        window.location = '/shops/shopsAdd';
    }

</script>

</html>