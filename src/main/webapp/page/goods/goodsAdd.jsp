<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>添加商品</title>
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
                添加商品
            </div>
            <ol class="am-breadcrumb">
                <li><a href="/index.html" class="am-icon-home">首页</a></li>
                <li><a href="javascript:void(0);">后台管理</a></li>
                <li class="am-active">添加商品</li>
            </ol>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 添加商品
                    </div>
                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" action="/goods/addGoods" enctype="multipart/form-data" method="post">
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">商品名称</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="name" placeholder="商品名称">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">商品描述</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="remark" placeholder="商品描述">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">库存</label>
                                    <div class="am-u-sm-9">
                                        <input type="number" pattern="[0-9]*" name="num" placeholder="库存">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">价格</label>
                                    <div class="am-u-sm-9">
                                        <input type="number" pattern="[0-9]*" name="price" placeholder="价格">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">商品类别</label>
                                    <div class="am-u-sm-9">
                                        <select name="goodsType" class="initSelect" typeId="goodsType">
                                            <option value="#value">#name</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">所属商铺</label>
                                    <div class="am-u-sm-9">
                                        <select name="shopsId" class="initSelect" tableName="shops" filterName="user_id" filterValue="${loginUser.id}" displayName="shops_name" displayValue="id">
                                            <option value="#value">#name</option>
                                        </select>
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
                                        <button type="submit" class="am-btn am-btn-primary">保存修改</button>
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