<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit|ie-stand|ie-comp">
<meta name="description" content="主页">
<meta name="keywords" content="">
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="plugins/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<!--[if lte IE 9]>
<script type="text/javascript" src="plugins/bootstrap/js/respond.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap/js/html5shiv.js"></script>
<![endif]-->
<title>主页</title>
</head>
<body>
</body>
</html>