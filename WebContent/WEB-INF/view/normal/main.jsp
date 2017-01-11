<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="/WEB-INF/view/include/include.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="plugins/jquery-ui/themes/material/easyui.css">
<link rel="stylesheet" type="text/css" href="plugins/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="plugins/jquery-ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<title>Main Page</title>
</head>
<body>
    <jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>
    <div id="main-panel-body" class="container-fluid">
        <div class="row-fluid">
            <div id="menu-pannel" class="layui-side layui-bg-black">
                <div class="layui-side-scroll">
                    <ul id="menus" class="layui-nav layui-nav-tree site-menu-nav"></ul>
                </div>
            </div>
            <div id="page-tabs"></div>
            <div class="footer">LD Copyright &copy; 2017</div>
        </div>
    </div>
</body>
</html>