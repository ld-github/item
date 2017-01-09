<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="/WEB-INF/view/include/include.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="plugins/jquery-ui/themes/material/easyui.css">
<link rel="stylesheet" type="text/css" href="plugins/jquery-ui/themes/icon.css">
<script type="text/javascript" src="plugins/jquery-ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<title>Index Page</title>
</head>
<body>
    <jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>
    <div id="main-panel-body" class="container-fluid">
        <div class="row-fluid">
            <div class="sidebar-nav">
                <div id="menu-panel"></div>
            </div>
            <div id="page-tabs"></div>
        </div>
    </div>
</body>
</html>