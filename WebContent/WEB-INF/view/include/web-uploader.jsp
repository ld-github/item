<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="plugins/uploader/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="plugins/uploader/webuploader.min.js">
<script type="text/javascript" src="plugins/uploader/uploader.js"></script>
