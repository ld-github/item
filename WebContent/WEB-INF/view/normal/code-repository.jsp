<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="/WEB-INF/view/include/include.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/bootstrap-table.jsp"></jsp:include>
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
                            <div id="toolbar">
                                <div class="btn-group">
                                    <button id="btn-add" type="button" class="btn btn-default" data-toggle="modal" data-target="#code-repository-modal" data-modal-title="添加-源码库" data-handle="save">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                                    </button>
                                    <button id="btn-edit" type="button" class="btn btn-default" data-toggle="modal" data-target="#code-repository-modal" data-modal-title="修改-源码库" data-handle="update">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                    </button>
                                    <button id="btn-del" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                                    </button>
                                </div>

                                <div class="btn-group">
                                    <button id="btn-pull" type="button" class="btn btn-default" data-toggle="modal" data-target="#code-repository-branch-modal" data-modal-title="拉取-分支代码" data-handle="pull">
                                        <span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>&nbsp;pull
                                    </button>
                                </div>

                                <div class="btn-group">
                                    <button id="btn-clone-again" type="button" class="btn btn-danger" data-toggle="modal">
                                        <span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>&nbsp;重新clone
                                    </button>
                                </div>
                            </div>

                            <table id="code-repository-table"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="code-repository-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加/修改-源码库</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="code-repository-edit-form">

                        <input type="hidden" name="id">

                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">名称</span>
                                    <input type="text" class="form-control" name="name" placeholder="名称">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">远端路径</span>
                                    <input type="text" class="form-control" name="remotePath" placeholder="远端路径">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">本地路径</span>
                                    <input type="text" class="form-control" name="localPath" placeholder="本地路径">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">代码路径</span>
                                    <input type="text" class="form-control" name="codePath" placeholder="代码路径">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">用户名</span>
                                    <input type="text" class="form-control" name="username" placeholder="用户名">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">密码</span>
                                    <input type="password" class="form-control" name="password" placeholder="密码">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">备注</span>
                                    <input type="text" class="form-control" name="remark" placeholder="备注">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="code-repository-edit-btn">保存</button>
                </div>
            </div>
      </div>
    </div>
</body>
</html>