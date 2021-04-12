<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>服装商城</title>
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="../assets/css/amazeui.min.css" />
  <link rel="stylesheet" href="../assets/css/admin.css">
  <link rel="stylesheet" href="../assets/css/app.css">
</head>

<body data-type="login">

  <div class="am-g myapp-login">
	<div class="myapp-login-logo-block  tpl-login-max">
		<div class="myapp-login-logo-text">
			<div class="myapp-login-logo-text">
				服装商城 <i class="am-icon-skyatlas"></i>
				
			</div>
		</div>

		<div class="login-font">
			<i>注册 </i> or <a href="login.html"><span> 登录</span></a>
		</div>
		<div class="login-font">
			<span>${msg } </span>
		</div>
		<div class="am-u-sm-10 login-am-center">
			<form class="am-form" action="/system/regist" method="post">
				<fieldset>
					<div class="am-form-group">
						<input type="text" class="" <%--id="doc-ipt-email-1"--%> name="username" value="${user.username }" placeholder="用户名">
					</div>
					<div class="am-form-group">
						<input type="password" class="" <%--id="doc-ipt-pwd-1"--%> name="password" placeholder="密码">
					</div>
					<p><button type="submit" class="am-btn am-btn-default">注册</button></p>
				</fieldset>
			</form>
		</div>
	</div>
</div>

  <script src="../assets/js/amazeui.min.js"></script>
  <script src="../assets/js/app.js"></script>
</body>

</html>