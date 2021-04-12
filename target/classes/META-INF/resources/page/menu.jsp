<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="tpl-left-nav tpl-left-nav-hover">
    <div class="tpl-left-nav-title">
        服装商城菜单
    </div>
    <div class="tpl-left-nav-list">
        <ul class="tpl-left-nav-menu">
            <li class="tpl-left-nav-item">
                <a href="/index.html" class="menuListener nav-link active" id="index">
                    <i class="am-icon-home"></i>
                    <span>首页</span>
                </a>
            </li>

		<c:if  test="${loginUser.username=='admin'}">
            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-table"></i>
                    <span>后台管理</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu" style="display: block;">
                    <li>
                        <a href="/goods/goodsList" class="menuListener" id="goodsList">
                            <i class="am-icon-angle-right"></i>
                            <span>商品管理</span>
                            <%--<i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>--%>
                        </a>

                        <a href="/shops/shopsListPanel" class="menuListener" id="shopsListPanel2">
                            <i class="am-icon-angle-right"></i>
                            <span>店铺管理</span>
                        </a>


                        <a href="/system/userList" class="menuListener" id="userList">
                            <i class="am-icon-angle-right"></i>
                            <span>人员管理</span>

                        </a>

                    </li>
                </ul>
            </li>
		</c:if>
            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-wpforms"></i>
                    <span>服装商城系统</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu" style="display: block;">
                    <li>
                        <a href="/goods/goodsListPanel" class="menuListener" id="goodsListPanel">
                            <i class="am-icon-angle-right"></i>
                            <span>服装浏览</span>
                        </a>
                        
						<a href="/goodsOrder/orderList?userId=${loginUser.id}" class="menuListener" id="orderList">
                            <i class="am-icon-angle-right"></i>
                            <span>我的订单</span>
                        </a>

						<c:if  test="${loginUser.userType=='0'}">
	                        <a href="/system/registVip?id=${loginUser.id}" class="menuListener" id="registVip">
	                            <i class="am-icon-angle-right"></i>
	                            <span>会员注册</span>
	                        </a>
	                    </c:if>  
	                        
                        <c:choose>
                            <c:when  test="${loginUser.userType=='2'}">
                                <a href="/shops/shopsListPanel?userId=${loginUser.id}" class="menuListener" id="shopsListPanel">
                                    <i class="am-icon-angle-right"></i>
                                    <span>我的店铺</span>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/system/registSeller?id=${loginUser.id}" class="menuListener" id="registSeller">
                                    <i class="am-icon-angle-right"></i>
                                    <span>申请入驻</span>
                                </a>
                            </c:otherwise>
                        </c:choose>
                        
                        <a href="/comment/comments" class="menuListener">
                            <i class="am-icon-angle-right"></i>
                            <span>评论管理</span>

                        </a>
                        
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>