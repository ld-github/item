<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="/WEB-INF/view/include/include.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/login.js"></script>
<title>用户登录</title>
</head>
<body>
    <div class="login-container">
        <div class="login-pannel">
            <div class="title-panel">System User Login</div>
            <hr>
            <form class="login-form" action="user/toLogin" method="post">
                <div class="input-group-lg">
                      <input type="text" class="form-control" placeholder="Username">
                </div>
                <div class="input-group-lg">
                      <input type="password" class="form-control" placeholder="Password">
                </div>
                <div class="input-group input-group-lg">
                      <input type="text" class="form-control" placeholder="Verification Code">
                      <span class="input-group-addon"><img id="verification-code-img" src="kaptcha.jpg"></span>
                </div>
                <button type="submit" data-loading-text="Logging..." class="btn btn-primary btn-lg pull-right" id="to-login-btn">Login</button>
            </form>
        </div>
    </div>
</body>

<script type="text/javascript">
    "<c:if test="${null != respDesc}">"
        new Message().tipLeft('#to-login-btn', '${respDesc}');
    "</c:if>";
</script>

</html>