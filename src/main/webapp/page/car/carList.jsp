<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的购物车</title>
    <meta name="description" content="我的购物车">
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
                我的购物车
            </div>
            <ol class="am-breadcrumb">
                <li><a href="/index.html" class="am-icon-home">首页</a></li>
                <li class="am-active">我的购物车</li>
            </ol>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 购物车列表
                    </div>
                </div>
                <div class="tpl-block">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6">
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button type="button" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="clearCar();">
                                        <span class="am-icon-minus">清空购物车</span>
                                    </button>
                                </div>

                                <div class="am-btn-group am-btn-group-xs">
                                    <button type="button" class="am-btn am-btn-default am-btn-xs am-text-primary am-hide-sm-only" onclick="addOrder();">
                                        <span class="am-icon-plus">提交订单</span>
                                    </button>
                                </div>

                            </div>
                        </div>

                        <form action="/goods/goodsListPanel">

                            <input type="hidden" name="pageNum">
                            <input type="hidden" name="pageSize">

                        </form>

                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                            <form class="am-form">
                                <table class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                    <tr>
                                        <th class="table-id">序号</th>
                                        <th class="table-type">商品</th>
                                        <th class="table-author">名称</th>
                                        <th class="table-date">描述</th>
                                        <th class="table-type">价格</th>
                                        <th class="table-type">商品类别</th>
                                        <th class="table-type">所属商铺</th>
                                        <th class="table-set">商品数量</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="car" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td><img src="${car.goods.image}" width="200" height="100"></td>
                                            <td>${car.goods.name}</td>
                                            <td >${car.goods.remark}</td>
                                            <td >${car.goods.price}</td>
                                            <td >${car.goods.goodsTypeView}</td>
                                            <td >${car.goods.shopsIdView}</td>
                                            <td>
                                                <div class="am-btn-toolbar">
                                                    <div class="am-btn-group am-btn-group-xs">
                                                        <button type="button" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="minusCarNum('carNum${car.goodsId}', ${car.id});"><span class="am-icon-minus"></span></button>
                                                        <button disabled class="am-btn am-btn-default am-btn-xs am-hide-sm-only" id="carNum${car.goodsId}">${car.num}</button>
                                                        <button type="button" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="plusCarNum('carNum${car.goodsId}', ${car.id});"><span class="am-icon-plus"></span></button>
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
     * 购物车数量-1
     */
    function minusCarNum(carNumId, id) {
        var num = Number($("#"+carNumId).html());
        if(num == 1) {
            if(confirm("确定删除此商品吗？")) {
                // TODO 删除商品
            }
            return;
        }
        $.post("/shoppingCar/minusCarNum", {'goodsId' : carNumId.replace('carNum',''), 'id' : id}, function (data) {
            $("#"+carNumId).html(num-1);
        });
    }

    /**
     * 购物车数量+1
     */
    function plusCarNum(carNumId, id) {
        var num = Number($("#"+carNumId).html());
        $.post("/shoppingCar/plusCarNum", {'goodsId' : carNumId.replace('carNum',''), 'id' : id}, function (data) {
            if(!data.success) {
                alert(data.data);
            } else {
                $("#"+carNumId).html(num+1);
            }
        });
    }

    /**
     * 清空购物车
     */
    function clearCar() {
        $.post("/shoppingCar/clearCar", {}, function (data) {
            if(data.success) {
                alert('购物车已清空！');
                window.location.href = '/goods/goodsListPanel';
            }
        });
    }

    /**
     * 提交订单（将该用户购物车车下的商品全部提交订单）
     */
    function addOrder(userId) {
    	$.post("/shoppingCar/addOrder", {}, function (data) {
            if(data.success) {
                alert('已提交订单！');
                /* window.location.href = '/goods/goodsListPanel'; */
            }
        });
    }
</script>

</html>