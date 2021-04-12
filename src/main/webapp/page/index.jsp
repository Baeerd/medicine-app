<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>服装商城</title>
    <meta name="description" content="主页">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <%@include file="css.jsp"%>
    <script type="text/javascript">
        $.cookie("menuSelect", "index");

        $(function(){
            $('.am-slider').flexslider({
                playAfterPaused: 3000,
                animation: "fade",
                animationLoop: true,
                smoothHeight: true,
                animationSpeed: 4000
            });
        });

    </script>
</head>

<body>

    <%@include file="top.jsp"%>


    <div class="tpl-page-container tpl-page-header-fixed">

        <%@include file="menu.jsp"%>

        <div class="tpl-content-wrapper">
            <div class="tpl-content-page-title">
                服装商城主页
            </div>
            <ol class="am-breadcrumb">
                <li><a href="/" class="am-icon-home">首页</a></li>
            </ol>
            <div class="tpl-content-scope">
                <div class="note note-info">
                    <p><span class="label label-danger">介绍:</span> 小铺12年衣品潮流，显于个性，忠于品质，安于保障。
                    </p>
                    <p><span class="label label-danger">介绍:</span> 提供最舒适的购物体验：会员一键注册，千万精品为您所订
                    </p>
                    <p><span class="label label-danger">介绍:</span> 提供最便利的商家服务：商品秒添加，订单一览无余
                    </p>
                    <p><span class="label label-danger">介绍:</span> 提供最人性化的特色功能：个性评论，专属店家，智能付款
                    </p>
                </div>
            </div>


            <div class="row">
				<c:forEach items="${sgvList }" var="shopsGoodsVo">
	                <div class="am-u-lg-3 am-u-md-6 am-u-sm-12">
	                    <div class="am-slider am-slider-default">
	                        <ul class="am-slides">
	                        	<c:forEach items="${shopsGoodsVo.goodsList }" var="goods">
		                            <li><img src="${goods.image }" width="300px" height="450px"></li>
	                            </c:forEach>
	                        </ul>
	                    </div>
	                </div>
				</c:forEach>
            </div>

        </div>

    </div>

</body>

</html>