<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <a href="javascript:;" class="tpl-logo">
            <img src="${pageContext.request.contextPath}/assets/img/logo.jpeg" alt="">
        </a>
    </div>
    <div class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right">

    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">



        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="am-icon-bell-o"></span> 我的购物车 <span class="am-badge tpl-badge-success am-round">${cars.size()}</span></span>
                </a>
                <ul class="am-dropdown-content tpl-dropdown-content">
                    <li class="tpl-dropdown-content-external">
                        <h3>购物车中有 <span class="tpl-color-success">${cars.size()}</span> 种商品</h3><a href="/shoppingCar/carList">全部</a></li>

                    <c:forEach items="${cars}" var="car">
                        <li class="tpl-dropdown-list-bdbc">
                            <a href="javascript:void(0);" class="tpl-dropdown-list-fl">
                                <span class="am-icon-btn am-icon-plus tpl-dropdown-ico-btn-size tpl-badge-success"></span>
                                ${car.goods.name}
                            </a>
                            <span class="tpl-dropdown-list-fr">${car.num}件</span>
                        </li>
                    </c:forEach>

                </ul>
            </li>

            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="tpl-header-list-user-nick">
                        尊敬的<c:choose>
                                    <c:when test="${loginUser.userType=='2'}">
                                        商家:
                                    </c:when>
                                    <c:when test="${loginUser.userType=='4'}">
                                        会员:
                                    </c:when>
                                    <c:otherwise>
                                        用户:
                                    </c:otherwise>
                             </c:choose>
                        ${loginUser.username}
                    </span>
                    <span class="tpl-header-list-user-ico">
                        <img src="${pageContext.request.contextPath}/assets/img/userLogo.jpg"> 您好
                    </span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="#"><span class="am-icon-bell-o"></span> 资料</a></li>
                    <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
                    <li><a href="/system/logout"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li><a href="/system/logout" class="tpl-header-list-link"><span class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
        </ul>
    </div>
</header>