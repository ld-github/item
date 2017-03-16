<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="/WEB-INF/view/include/include.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/bootstrap-table.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/layui.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/code-repository.css">
<script type="text/javascript" src="js/code-repository.js"></script>
<title>Code Repository Manager</title>
</head>
<body>
    <div class="main-panel">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form class="form-inline" role="form" id="code-repository-query-form">
                                        <div class="form-group">
                                            <label class="sr-only" for="code-repository-name-text">名称</label>
                                            <input type="text" class="form-control" id="code-repository-name-text" placeholder="名称" name="name">
                                        </div>
                                        <button type="button" class="btn btn-primary pull-right" id="code-repository-query-btn">查询</button>
                                    </form>
                                </div>
                            </div>
                            <div id="toolbar" class="btn-group">
                                <button id="btn-add" type="button" class="btn btn-default" data-toggle="modal" data-target="#code-repository-modal" data-modal-title="添加-字典类型" data-handle="save">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                                </button>
                                <button id="btn-edit" type="button" class="btn btn-default" data-toggle="modal" data-target="#code-repository-modal" data-modal-title="修改-字典类型" data-handle="update">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                </button>
                                <button id="btn-del" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                                </button>
                            </div>
                            <table id="code-repository-table"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>