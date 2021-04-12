<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>评论管理</title>
    <meta name="description" content="评论管理">
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
                评论管理
            </div>
            <ol class="am-breadcrumb">
                <li><a href="/index.html" class="am-icon-home">首页</a></li>
                <li><a href="javascript:void(0);">服装商城系统</a></li>
                <li class="am-active">评论管理</li>
            </ol>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 评论管理
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

                        <form action="/comment/comments">

                            <input type="hidden" name="pageNum">
                            <input type="hidden" name="pageSize">

                            <div class="am-u-sm-12 am-u-md-3">
                                <div class="am-input-group am-input-group-sm">
                                    <input type="text" name="goodsName" class="am-form-field" value="${paramsGoodsName}">
                                    <span class="am-input-group-btn">
                                        <button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search" type="submit"></button>
                                    </span>
                                </div>
                            </div>
                        </form>
                    </div>

                    评论列表：
                    <ul class="tpl-task-list tpl-task-remind">

                        <c:forEach items="${page.list}" var="comment">
                            <li>
                                <div class="cosB">
                                    ${comment.createdDtView}
                                </div>
                                <div class="cosA">
                                <span class="cosIco">
                                <i class="am-icon-bell-o"></i>
                            </span>
                                    <span> ${comment.goodsName} : ${comment.content}</span>
                                    <c:choose>
                                        <c:when test="${loginUser.username == comment.createdBy}">
                                            <span class="tpl-label-import"> ${comment.createdBy} </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="tpl-label-info"> ${comment.createdBy} </span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </li>
                        </c:forEach>

                    </ul>

                    <div class="am-u-lg-12">
                        <%@include file="../page.jsp"%>

                        <hr>
                    </div>

                </div>

            </div>


        </div>

    </div>

</body>

</html>