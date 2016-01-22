<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<title>JDeploy自动化部署平台</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/materialize/0.97.0/css/materialize.min.css">
<link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/materialize/0.97.0/js/materialize.min.js"></script>
</head>
<body style="font-family: 'Roboto', 'Droid Sans Fallback', '微软雅黑';min-height: 100vh;display: flex;flex-direction: column;">

	<nav>
		<div class="nav-wrapper">
			<a href="${pageContext.request.contextPath}/" class="brand-logo center">JDeploy自动化部署平台</a>
		</div>
	</nav>

	<div class="container" style="padding-top: 20px; width: 90%;flex: 1 0 auto;">
		<div class="row">
			<form class="col s12 m12 l4 offset-l4" action="${pageContext.request.contextPath}/login" method="post">
				<div class="card">
					<div class="card-content">
						<span class="card-title grey-text">登录</span>
						<c:if test="${not empty param.error}"><div class="red-text">用户名或密码错误！</div></c:if>
						<div class="row">
							<div class="input-field col s12">
								<input id="username" type="text" name="username">
								<label for="username">用户</label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12">
								<input id="password" type="password" name="password">
								<label for="password">密码</label>
							</div>
						</div>
					</div>
					<div class="card-action">
		              <button type="submit" class="waves-effect waves-light btn">登录</button>
		            </div>
				</div>
			</form>
		</div>

	</div>
	
	<footer class="page-footer" style="padding-top: 0; margin-top: 40px;">
      <div class="footer-copyright">
        <div class="container">
        Copyright © 2016 <a class="grey-text text-lighten-4" href="http://xxgblog.com" target="_blank">http://xxgblog.com</a>. All rights reserved.
        <a class="grey-text text-lighten-4 right" href="https://github.com/wucao/JDeploy" target="_blank">GitHub</a>
        </div>
      </div>
    </footer>
</body>
</html>