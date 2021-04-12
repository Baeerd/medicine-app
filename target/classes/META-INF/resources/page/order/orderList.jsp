<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的订单</title>
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
                我的订单
            </div>
            <ol class="am-breadcrumb">
                <li><a href="/index.html" class="am-icon-home">首页</a></li>
                <li><a href="javascript:void(0);">服装商城系统</a></li>
                <li class="am-active">我的订单</li>
            </ol>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 订单列表
                    </div>
                </div>
                <div class="tpl-block">
                    <div class="am-g">

                        <form action="/goods/goodsListPanel">

                            <input type="hidden" name="pageNum">
                            <input type="hidden" name="pageSize">

                        </form>

                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                            <form class="am-form">
                           	  <c:forEach items="${goodsOrderVoList }" var="orderVo">
                                <table class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
	                                    <tr>
	                                    	<element>
	                                    		<font color="blue" size="4">
		                                    		时间：${orderVo.creatDt }
		                                    		&nbsp;&nbsp;
		                                    		订单号：${orderVo.orderNo }
		                                    		&nbsp;&nbsp;
		                                    		商铺名称：${orderVo.shopsName }
		                                    		&nbsp;&nbsp;
		                                    		<c:choose>
	                            						<c:when  test="${orderVo.showPrice != null }">
		                                    				订单金额：<del>${orderVo.totalPrice }</del> <font color="red">${orderVo.showPrice }</font>
		                                    			</c:when>
		                                    			<c:otherwise>
		                                    				订单金额：${orderVo.totalPrice }
		                                    			</c:otherwise>
	                                    			</c:choose>
		                                    		&nbsp;&nbsp;
		                                    		订单状态：${orderVo.orderTypeView }
		                                    		
		                                    		<c:choose>
	                            						<c:when  test="${orderVo.orderTypeView=='待付款'}">
	                             						   &nbsp;&nbsp;
		                                    				<button type="button" class="am-btn am-btn-default am-btn-xs am-text-primary am-hide-sm-only" onclick="payment(${orderVo.orderNo });">
		                                       					 <span class="am-icon-plus">付款</span>
				                                 		    </button>
				                               		        &nbsp;&nbsp;
				                                 		    <button type="button" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="clearOrder(${orderVo.orderNo });">
                                       							 <span class="am-icon-minus">取消订单</span>
                                  				 		    </button>
	                          							  </c:when>
	                    						    </c:choose>
		                                    		
		                                    		
	                                    		</font>
	                                    	</element>
                                    	</tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th class="table-date">商品图</th>
                                        <th class="table-date">商品</th>
                                        <th class="table-date">简介</th>
                                        <th class="table-date">价格</th>
                                        <th class="table-date">数量</th>
                                        <c:if  test="${orderVo.orderTypeView=='已付款'}">
	                                        <th class="table-date">
                                               	评论
	                                        </th>
                                        </c:if>
                                    </tr>
                                    	<c:forEach items="${orderVo.goodsList }" var="goods">
                                    		<tr>
	                                            <td><img src="${goods.image}" width="200" height="100"></td>
	                                            <td>${goods.name}</td>
	                                            <td>${goods.remark}</td>
	                                            <td>${goods.price}</td>
	                                            <td>${goods.orderGoodsNum}</td>
                                                <td>
                                                    <c:if  test="${orderVo.orderTypeView=='已付款'}">
                                                        <input type="text" id="goodsComment${goods.id}">
                                                        <button type="button" class="am-btn am-btn-default am-btn-xs am-text-primary am-hide-sm-only" onclick="addComment(${goods.id});">
                                                            <span class="am-icon-plus">评论</span>
                                                        </button>
                                                    </c:if>
                                                </td>
                                       	    </tr>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                              </c:forEach>

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
     * 	付款
     */
    function payment(orderNo) {
    	alert(orderNo);
    	$.post("/goodsOrder/payment", {'orderNo':orderNo}, function (data) {
            if(data.success) {
                alert('已付款！');
                window.location.href = "/goodsOrder/orderList?userId=${loginUser.id}"; 
            }
        });
    }
    
    //取消订单
    function clearOrder(orderNo) {
    	alert(orderNo);
    	$.post("/goodsOrder/clearOrder", {'orderNo':orderNo}, function (data) {
            if(data.success) {
                alert('已取消订单！');
                window.location.href = "/goodsOrder/orderList?userId=${loginUser.id}";
            }
        });
    }

    function addComment(goodsId) {
        var goodsComment = $("#goodsComment"+goodsId).val();
        var jsonData = {"goodsId" : goodsId, "content" : goodsComment};
        var url = "/comment/addJson";
        $.post(url, jsonData, function (data) {
            alert("评论成功!");
            $("#goodsComment"+goodsId).val('');
        });
    }
    
</script>

</html>