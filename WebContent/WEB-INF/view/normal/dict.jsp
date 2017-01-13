<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="/WEB-INF/view/include/include.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/bootstrap-table.jsp"></jsp:include>
<script type="text/javascript" src="js/dict.js"></script>
<title>Dict Page</title>
</head>
<body>
    <div class="main-panel">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="col-xs-6">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title text-center">参数类型</h3></div>
                        <div class="panel-body">
                            <div id="dict-type-toolbar" class="btn-group">
                                <button id="btn_edit" type="button" class="btn btn-default" >
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                </button>
                                <button id="btn_delete" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                </button>
                            </div>
                            <div id="dict-type-table"></div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title text-center">参数配置</h3></div>
                        <div class="panel-body">
                            <div id="dict-value-table"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>