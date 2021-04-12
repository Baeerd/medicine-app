<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>添加商铺</title>
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
                添加商铺
            </div>
            <ol class="am-breadcrumb">
                <li><a href="/index.html" class="am-icon-home">首页</a></li>
                <li><a href="javascript:void(0);">后台管理</a></li>
                <li class="am-active">添加商铺</li>
            </ol>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 添加商铺
                    </div>
                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" action="/shops/addShops" enctype="multipart/form-data" method="post">

                                <input type="hidden" name="userId" value="${loginUser.id}">

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">商铺名称</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="shopsName" placeholder="商铺名称">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">商铺描述</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="shopsRemark" placeholder="商铺描述">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">图片</label>
                                    <div class="am-u-sm-9">
                                        <input type="file" name="file">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="submit" class="am-btn am-btn-primary">保存</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>










        </div>

    </div>

</body>

</html>