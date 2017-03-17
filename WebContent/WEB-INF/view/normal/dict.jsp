<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="/WEB-INF/view/include/include.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/bootstrap-table.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/dict.css">
<script type="text/javascript" src="js/dict.js"></script>
<title>Dict Page</title>
</head>
<body>
    <div class="main-panel">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="col-xs-6">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title text-center">字典类型配置</h3></div>
                        <div class="panel-body">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form class="form-inline" role="form" id="dict-type-query-form">
                                        <div class="form-group">
                                            <label class="sr-only" for="dict-type-code-text">字典类型代码</label>
                                            <input type="text" class="form-control" id="dict-type-code-text" placeholder="字典类型代码" name="code">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="dict-type-name-text">字典类型名称</label>
                                            <input type="text" class="form-control" id="dict-type-name-text" placeholder="字典类型名称" name="name">
                                        </div>
                                        <button type="button" class="btn btn-primary pull-right" id="dict-type-query-btn">查询</button>
                                    </form>
                                </div>
                            </div>
                            <div id="dict-type-toolbar" class="btn-group">
                                <button id="btn-add-dict-type" type="button" class="btn btn-default" data-toggle="modal" data-target="#dict-type-modal" data-modal-title="添加-字典类型" data-handle="save">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                                </button>
                                <button id="btn-edit-dict-type" type="button" class="btn btn-default" data-toggle="modal" data-target="#dict-type-modal" data-modal-title="修改-字典类型" data-handle="update">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                </button>
                                <button id="btn-del-dict-type" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                                </button>
                            </div>
                            <table id="dict-type-table"></table>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title text-center">字典值配置</h3></div>
                        <div class="panel-body">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form class="form-inline" role="form" id="dict-value-query-form">
                                        <div class="form-group">
                                            <label class="sr-only" for="dict-code-text">字典值</label>
                                            <input type="text" class="form-control" id="dict-value-text" placeholder="字典值" name="value">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="dict-name-text">字典值名称</label>
                                            <input type="text" class="form-control" id="dict-name-text" placeholder="字典值名称" name="name">
                                        </div>
                                        <button type="button" class="btn btn-primary pull-right" id="dict-value-query-btn">查询</button>
                                    </form>
                                </div>
                            </div>
                            <div id="dict-value-toolbar" class="btn-group">
                                <button id="btn-add-dict-value" type="button" class="btn btn-default" data-toggle="modal" data-target="#dict-value-modal" data-modal-title="添加-字典值" data-handle="save">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                                </button>
                                <button id="btn-edit-dict-value" type="button" class="btn btn-default"  data-toggle="modal" data-target="#dict-value-modal" data-modal-title="修改-字典值" data-handle="update">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                </button>
                                <button id="btn-del-dict-value" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                                </button>
                            </div>

                            <table id="dict-value-table"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="dict-type-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加/修改-字典类型</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="dict-type-edit-form">

                        <input type="hidden" name="id">

                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">字典类型代码</span>
                                    <input type="text" class="form-control" name="code" placeholder="字典类型代码">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">字典类型名称</span>
                                    <input type="text" class="form-control" name="name" placeholder="字典类型名称">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">字典类型备注</span>
                                    <input type="text" class="form-control" name="remark" placeholder="字典类型备注">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="dict-type-edit-btn">保存</button>
                </div>
            </div>
      </div>
    </div>

    <div class="modal fade" id="dict-value-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加/修改-字典值</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="dict-value-edit-form">

                        <input type="hidden" name="type.id">

                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">字典类型代码</span>
                                    <input type="text" class="form-control" placeholder="字典类型代码" readonly="readonly" name="type.code">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">字典类型名称</span>
                                    <input type="text" class="form-control" placeholder="字典类型名称" readonly="readonly" name="type.name">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">字典值</span>
                                    <input type="text" class="form-control" placeholder="字典值" name="value">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">字典值名称</span>
                                    <input type="text" class="form-control" placeholder="字典值名称" name="name">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group col-sm-12">
                                    <span class="input-group-addon fixed-addon-width">字典值备注</span>
                                    <input type="text" class="form-control" placeholder="字典值备注" name="remark">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="dict-value-edit-btn">保存</button>
                </div>
            </div>
      </div>
    </div>
</body>
</html>