<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品列表</title>
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
                商品管理
            </div>
            <ol class="am-breadcrumb">
                <li><a href="/index.html" class="am-icon-home">首页</a></li>
                <li><a href="javascript:void(0);">后台管理</a></li>
                <li class="am-active">商品管理</li>
            </ol>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 商品列表
                    </div>

                </div>
                <div class="tpl-block">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6">
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button type="button" onclick="addGoods();" class="am-btn am-btn-default am-btn-success"><span class="am-icon-plus"></span> 新增</button>
                                </div>
                            </div>
                        </div>

                        <form action="/goods/goodsList">
                            <input type="hidden" name="pageNum">
                            <input type="hidden" name="pageSize">
                            <div class="am-u-sm-12 am-u-md-3">
                                <div class="am-form-group">
                                    <select id="goodsTypeParam" data-am-selected="{btnSize: 'sm'}" class="initSelect" typeId="goodsType">
                                      <option value="">点击选择</option>
                                      <option value="#value">#name</option>
                                    </select>
                                </div>
                            </div>
                            <div class="am-u-sm-12 am-u-md-3">
                                <div class="am-input-group am-input-group-sm">
                                    <input id="goodsNameParam" type="text" class="am-form-field" placeholder="商品名称..." value="${goodsName}">
                                    <span class="am-input-group-btn">
                                        <button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search" type="button" onclick="goodsList();"></button>
                                    </span>
                                </div>
                            </div>
                        </form>

                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                            <form class="am-form">
                                <table class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                        <tr>
                                            <th class="table-id">ID</th>
                                            <th class="table-title">创建时间</th>
                                            <th class="table-type">创建人</th>
                                            <th class="table-author">商品名称</th>
                                            <th class="table-date">商品描述</th>
                                            <th class="table-type">库存</th>
                                            <th class="table-type">价格</th>
                                            <th class="table-type">图片路径</th>
                                            <th class="table-type">商品类别</th>
                                            <th class="table-type">所属商铺</th>
                                            <th class="table-set">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${page.list}" var="goods">
                                            <tr>
                                                <td>${goods.id}</td>
                                                <td>${goods.createdDtView}</td>
                                                <td>${goods.createdBy}</td>
                                                <td >${goods.name}</td>
                                                <td >${goods.remark}</td>
                                                <td >${goods.num}</td>
                                                <td >${goods.price}</td>
                                                <td >${goods.image}</td>
                                                <td >${goods.goodsTypeView}</td>
                                                <td >${goods.shopsIdView}</td>
                                                <td>
                                                    <div class="am-btn-toolbar">
                                                        <div class="am-btn-group am-btn-group-xs">
                                                            <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="deleteGoods(${goods.id});"><span class="am-icon-trash-o"></span> 删除</button>
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
     * 添加商品
     */
    function addGoods() {
        window.location = '/goods/goodsAdd';
    }

    /**
     * 查询商品
     */
    function goodsList() {
        var goodsTypeParam = $("#goodsTypeParam").val();
        var goodsNameParam = $("#goodsNameParam").val();
        window.location = '/goods/goodsList?name=' + goodsNameParam + '&goodsType=' + goodsTypeParam;
    }

    /**
     * 删除商品
     */
    function deleteGoods(goodsId) {
        $.get("/goods/deleteJson", {"id" : goodsId}, function (data) {
            this.goodsList();
        });
    }
</script>

</html>