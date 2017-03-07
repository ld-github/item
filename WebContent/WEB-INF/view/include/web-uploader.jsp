<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="plugins/uploader/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="plugins/uploader/css/style.css">
<script type="text/javascript" src="plugins/uploader/webuploader.min.js"></script>
<script type="text/javascript" src="plugins/uploader/uploader.js"></script>

<div id="uploader-panel" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">文件上传</h4>
            </div>
            <div class="modal-body">
                <div id="uploader-box"></div>
            </div>
        </div>
    </div>
</div>