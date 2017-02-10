var URLS = {
    GET_PAGE_DICT_TYPE : contextPath + "/dictType/getPage",
    SAVE_DICT_TYPE : contextPath + "/dictType/save",
    UPDATE_DICT_TYPE : contextPath + "/dictType/update",
    DELETE_DICT_TYPE : contextPath + "/dictType/delete",
    GET_PAGE_DICT_VALUE : contextPath + "/dict/getPage",
    SAVE_DICT_VALUE : contextPath + "/dict/save",
    UPDATE_DICT_VALUE : contextPath + "/dict/update",
    DELETE_DICT_VALUE : contextPath + "/dict/delete",
}

var dictTypeParams = {};
var dictParams = {};

function delDictType() {
    var rows = $('#dict-type-table').bootstrapTable('getSelections');

    var params = {
        id : rows[0].id
    };

    $.post(URLS.DELETE_DICT_TYPE, params, function(data) {
        if (checkRespCodeSucc(data)) {
            $('#dict-type-table').bootstrapTable('removeByUniqueId', rows[0].id);

            bootstrapTableRefreshCurrentPage('#dict-type-table');
            return;
        }

        new Message().show(data.respDesc);
    });
}

function saveDictType() {
    var params = $('#dict-type-edit-form').serializeJson();

    if (params.code == '') {
        new Message().tipLeft('#dict-type-edit-btn', '字典类型代码不能为空');
        return;
    }

    $.post(URLS.SAVE_DICT_TYPE, params, function(data) {
        if (checkRespCodeSucc(data)) {
            bootstrapTableSelectFirstPage('#dict-type-table');
            $('#dict-type-modal').modal('hide')
            return;
        }

        new Message().tipLeft('#dict-type-edit-btn', data.respDesc);
    })
}

function updateDictType() {
    var params = $('#dict-type-edit-form').serializeJson();

    if (params.code == '') {
        new Message().tipLeft('#dict-type-edit-btn', '字典类型代码不能为空');
        return;
    }

    $.post(URLS.UPDATE_DICT_TYPE, params, function(data) {
        if (checkRespCodeSucc(data)) {
            bootstrapTableRefreshCurrentPage('#dict-type-table');
            $('#dict-type-modal').modal('hide')
            return;
        }

        new Message().tipLeft('#dict-type-edit-btn', data.respDesc);
    })

}

function saveDictValue() {
    var params = $('#dict-value-edit-form').serializeJson();

    if (params.value == '') {
        new Message().tipLeft('#dict-value-edit-btn', '字典值不能为空');
        return;
    }

    $.post(URLS.SAVE_DICT_VALUE, params, function(data) {
        if (checkRespCodeSucc(data)) {
            bootstrapTableSelectFirstPage('#dict-value-table');
            $('#dict-value-modal').modal('hide')
            return;
        }

        new Message().tipLeft('#dict-value-edit-btn', data.respDesc);
    })
}

function delDictValue() {
    var rows = $('#dict-value-table').bootstrapTable('getSelections');

    var params = {
        id : rows[0].id
    };

    $.post(URLS.DELETE_DICT_VALUE, params, function(data) {
        if (checkRespCodeSucc(data)) {
            $('#dict-value-table').bootstrapTable('removeByUniqueId', rows[0].id);

            bootstrapTableRefreshCurrentPage('#dict-value-table');
            return;
        }

        new Message().show(data.respDesc);
    });
}

function updateDictValue() {
    var params = $('#dict-value-edit-form').serializeJson();

    if (params.value == '') {
        new Message().tipLeft('#dict-value-edit-btn', '字典值不能为空');
        return;
    }

    var rows = $('#dict-value-table').bootstrapTable('getSelections');
    var row = rows[0];

    params.id = row.id;

    $.post(URLS.UPDATE_DICT_VALUE, params, function(data) {
        if (checkRespCodeSucc(data)) {
            bootstrapTableRefreshCurrentPage('#dict-value-table');
            $('#dict-value-modal').modal('hide')
            return;
        }

        new Message().tipLeft('#dict-value-edit-btn', data.respDesc);
    })
}

function initDictTypeTable() {
    $('#dict-type-table').bootstrapTable({
        url : URLS.GET_PAGE_DICT_TYPE,
        method : 'POST',
        contentType : "application/x-www-form-urlencoded",
        cache : false,
        striped : true,
        pagination : true,
        showRefresh : true,
        showToggle : true,
        smartDisplay : true,
        height : 343,
        minimumCountColumns : 1,
        pageSize : 5,
        clickToSelect : true,
        showExport : true,
        pageList : [ 5, 10, 25, 50, 100, 'All' ],
        idField : 'id',
        uniqueId : 'id',
        singleSelect : true,
        toolbar : '#dict-type-toolbar',
        sidePagination : 'server',
        dataField : 'records',
        queryParams : function(e) {
            var page = {
                currentPage : (e.offset / e.limit) + 1,
                pageSize : e.limit,
            }

            return $.extend(dictTypeParams, page);
        },
        columns : [ {
            checkbox : true,
        }, {
            field : 'code',
            title : '字典类型代码',
        }, {
            field : 'name',
            title : '字典类型名称',
        }, {
            field : 'remark',
            title : '字典类型备注',
        } ],
        onCheck : function(row) {
            $('#btn-edit-dict-type').removeAttr('disabled');
            $('#btn-del-dict-type').removeAttr('disabled');

            $('#btn-add-dict-value').removeAttr('disabled');

            bootstrapTableSelectFirstPage('#dict-value-table');
        },
        onUncheck : function(row) {
            $('#dict-value-table').bootstrapTable('removeAll');

            $('#dict-value-toolbar button').attr('disabled', 'disabled');

            $('#btn-edit-dict-type').attr('disabled', 'disabled');
            $('#btn-del-dict-type').attr('disabled', 'disabled');
        },
        onPageChange : function(number, size) {
            $('#dict-value-table').bootstrapTable('removeAll');

            $('#dict-value-toolbar button').attr('disabled', 'disabled');

            $('#btn-edit-dict-type').attr('disabled', 'disabled');
            $('#btn-del-dict-type').attr('disabled', 'disabled');
        },
        onRefresh : function() {
            $('#dict-value-table').bootstrapTable('removeAll');

            $('#dict-value-toolbar button').attr('disabled', 'disabled');

            $('#btn-edit-dict-type').attr('disabled', 'disabled');
            $('#btn-del-dict-type').attr('disabled', 'disabled');
        }
    });

}

function initDictTable() {
    $('#dict-value-table').bootstrapTable({
        url : URLS.GET_PAGE_DICT_VALUE,
        method : 'POST',
        contentType : "application/x-www-form-urlencoded",
        cache : false,
        striped : true,
        pagination : true,
        showRefresh : true,
        showToggle : true,
        smartDisplay : true,
        singleSelect : true,
        clickToSelect : true,
        height : 343,
        minimumCountColumns : 1,
        pageSize : 5,
        pageList : [ 5, 10, 25, 50, 100, 'All' ],
        idField : 'id',
        uniqueId : 'id',
        singleSelect : true,
        toolbar : '#dict-value-toolbar',
        sidePagination : 'server',
        dataField : 'records',
        queryParams : function(e) {
            var rows = $('#dict-type-table').bootstrapTable('getSelections');
            var typeId = rows.length > 0 ? rows[0].id : null;

            var page = {
                currentPage : (e.offset / e.limit) + 1,
                pageSize : e.limit,
                typeId : typeId
            }
            return $.extend(dictParams, page);
        },
        columns : [ {
            checkbox : true,
        }, {
            field : 'value',
            title : '字典值',
        }, {
            field : 'name',
            title : '字典值名称',
        }, {
            field : 'canUpdate',
            title : '是否可修改',
            align : 'center',
            formatter : function(value, row, index) {
                return value ? '是' : '<font style="color: red;">否</font>';
            }
        }, {
            field : 'remark',
            title : '字典值备注',
        } ],
        onCheck : function(row, element, field) {
            if (row.canUpdate) {
                $('#btn-edit-dict-value').removeAttr('disabled');
                $('#btn-del-dict-value').removeAttr('disabled');
            } else {
                $('#btn-edit-dict-value').attr('disabled', 'disabled');
                $('#btn-del-dict-value').attr('disabled', 'disabled');
            }
        },
        onUncheck : function(row) {
            $('#btn-edit-dict-value').attr('disabled', 'disabled');
            $('#btn-del-dict-value').attr('disabled', 'disabled');
        },
        onPageChange : function(number, size) {
            $('#btn-edit-dict-value').attr('disabled', 'disabled');
            $('#btn-del-dict-value').attr('disabled', 'disabled');
        },
        onRefresh : function() {
            $('#btn-edit-dict-value').attr('disabled', 'disabled');
            $('#btn-del-dict-value').attr('disabled', 'disabled');
        }
    });

}

$(function() {

    initDictTypeTable();

    initDictTable();
})

$(function() {

    $('#dict-type-toolbar button').attr('disabled', 'disabled');
    $('#dict-value-toolbar button').attr('disabled', 'disabled');

    $('#dict-type-toolbar #btn-add-dict-type').removeAttr('disabled');

    $('#dict-type-query-btn').click(function() {
        dictTypeParams = $('#dict-type-query-form').serializeJson();
        $('#dict-type-table').bootstrapTable('selectPage', 1);
    });

    $('#dict-value-query-btn').click(function() {
        dictParams = $('#dict-value-query-form').serializeJson();
        $('#dict-value-table').bootstrapTable('selectPage', 1);
    });

    $('#btn-del-dict-type').click(function() {
        new Message().confirm('确定删除该参数类型以及参数值配置?', delDictType);
    });

    $('#btn-del-dict-value').click(function() {
        new Message().confirm('确定删除该参数值配置?', delDictValue);
    });

    $('#dict-type-table').bootstrapTable('resetWidth');
    $('#dict-value-table').bootstrapTable('resetWidth');

    $(window).resize(function() {
        $('#dict-type-table').bootstrapTable('resetWidth');
        $('#dict-value-table').bootstrapTable('resetWidth');
    });

})

var HANDLE = {
    SAVE : 'save',
    UPDATE : 'update'
}

$(function() {

    $('#dict-type-modal').on('show.bs.modal', function(event) {

        var btn = $(event.relatedTarget);
        var title = btn.data('modal-title');
        var handle = btn.data('handle');

        var modal = $(this);
        modal.find('.modal-title').text(title);

        $("#dict-type-edit-form").clearForm();
        $('#dict-type-edit-btn').unbind();

        if (handle == HANDLE.UPDATE) {
            var rows = $('#dict-type-table').bootstrapTable('getSelections');
            var row = rows[0];

            $("#dict-type-edit-form").autoFillForm(row);

            $('#dict-type-edit-btn').bind("click", updateDictType);
            return;
        }

        if (handle == HANDLE.SAVE) {
            $('#dict-type-edit-btn').bind("click", saveDictType);
        }

    });

    $('#dict-value-modal').on('show.bs.modal', function(event) {
        var btn = $(event.relatedTarget);
        var title = btn.data('modal-title');
        var handle = btn.data('handle');

        var modal = $(this);
        modal.find('.modal-title').text(title);

        $("#dict-value-edit-form").clearForm();
        $('#dict-value-edit-btn').unbind();

        if (handle == HANDLE.UPDATE) {
            var rows = $('#dict-value-table').bootstrapTable('getSelections');
            var row = rows[0];

            $("#dict-value-edit-form").autoFillForm(row);
            $('#dict-value-edit-btn').bind("click", updateDictValue);
            return;
        }

        if (handle == HANDLE.SAVE) {
            var rows = $('#dict-type-table').bootstrapTable('getSelections');
            var row = rows[0];

            $("#dict-value-edit-form").autoFillForm({
                'type' : row
            });
            $('#dict-value-edit-btn').bind("click", saveDictValue);
        }

    });

})