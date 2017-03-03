<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="plugins/layui/css/layui.css">
<script type="text/javascript" src="plugins/layui/layui.js"></script>

<script type="text/javascript">

    var Message = function() {

        this.show = function(msg) {
            layui.use('layer', function() {
                layui.layer.msg(msg);
            });
        }

        this.confirm = function(msg, callback, param) {
            layui.use('layer', function() {
                layui.layer.confirm(msg, function(lay) {
                    if (callback && typeof (callback) === 'function') {
                        layui.layer.close(lay);
                        callback(param);
                    }
                })
            });
        }

        this.tipLeft = function(id, msg, time) {
            time = time || 3000;

            layui.use('layer', function() {
                layui.layer.tips(msg, $(id), {
                    tips : [ 4, '#3595CC' ],
                    time : time,
                });
            });
        }

    }
</script>