<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="plugins/pace/themes/blue/pace-theme-flat-top.css">
<script type="text/javascript" src="plugins/pace/pace.min.js"></script>