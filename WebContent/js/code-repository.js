var URLS = {
    GET_PAGE_CODE_REPOSITORY : contextPath + "/codeRepository/getPage",
    SAVE_OR_UPATE_CODE_REPOSITORY : contextPath + "/codeRepository/saveOrUpdate",
    DELETE_CODE_REPOSITORY : contextPath + "/codeRepository/delete",
}

function saveOrUpdate() {
    var params = $('#code-repository-edit-form').serializeJson();

    if (params.name == '') {
        new Message().tipLeft('#code-repository-edit-btn', '名称不能为空');
        return;
    }
    if (params.remotePath == '') {
        new Message().tipLeft('#code-repository-edit-btn', '远端地址不能为空');
        return;
    }
    if (params.localPath == '') {
        new Message().tipLeft('#code-repository-edit-btn', '本地地址不能为空');
        return;
    }

    $.post(URLS.SAVE_OR_UPATE_CODE_REPOSITORY, params, function(data) {
        if (checkRespCodeSucc(data)) {
            if (params.id == '') {
                bootstrapTableSelectFirstPage('#code-repository-table');
            } else {
                bootstrapTableRefreshCurrentPage('#code-repository-table');
            }
            $('#code-repository-modal').modal('hide')
            return;
        }

        new Message().tipLeft('#code-repository-edit-btn', data.respDesc);
    })
}

function del() {

    var rows = $('#code-repository-table').bootstrapTable('getSelections');

    var params = {
        id : rows[0].id
    };

    $.post(URLS.DELETE_CODE_REPOSITORY, params, function(data) {
        if (checkRespCodeSucc(data)) {
            $('#code-repository-table').bootstrapTable('removeByUniqueId', rows[0].id);

            bootstrapTableRefreshCurrentPage('#code-repository-table');
            return;
        }

        new Message().show(data.respDesc);
    });

}

var codeRepositoryQueryParams = {};

function initCodeRepositoryTable() {
    $('#code-repository-table').bootstrapTable({
        url : URLS.GET_PAGE_CODE_REPOSITORY,
        method : 'POST',
        contentType : "application/x-www-form-urlencoded",
        cache : false,
        striped : true,
        pagination : true,
        showRefresh : true,
        showToggle : true,
        showColumns : true,
        showExport : true,
        smartDisplay : true,
        height : 355,
        minimumCountColumns : 1,
        pageSize : 5,
        clickToSelect : true,
        pageList : [ 5, 10, 25, 50, 100, 'All' ],
        idField : 'id',
        uniqueId : 'id',
        singleSelect : true,
        toolbar : '#toolbar',
        sidePagination : 'server',
        dataField : 'records',
        queryParams : function(e) {
            var page = {
                currentPage : (e.offset / e.limit) + 1,
                pageSize : e.limit,
            }

            return $.extend(codeRepositoryQueryParams, page);
        },
        columns : [ {
            checkbox : true,
        }, {
            field : 'name',
            title : '名称',
        }, {
            field : 'remotePath',
            title : '远端地址',
        }, {
            field : 'localPath',
            title : '本地路径',
        }, {
            field : 'codePath',
            title : '代码路径',
        }, {
            field : 'remark',
            title : '备注',
        }, {
            field : 'createDatetime',
            title : '创建时间',
            align : 'center',
        } ],
        onCheck : function(row) {
            $('#toolbar #btn-edit').removeAttr('disabled');
            $('#toolbar #btn-del').removeAttr('disabled');
        },
        onUncheck : function(row) {
            $('#toolbar #btn-edit').attr('disabled', 'disabled');
            $('#toolbar #btn-del').attr('disabled', 'disabled');
        },
        onPageChange : function(number, size) {
            $('#toolbar #btn-edit').attr('disabled', 'disabled');
            $('#toolbar #btn-del').attr('disabled', 'disabled');
        },
        onRefresh : function() {
            $('#toolbar #btn-edit').attr('disabled', 'disabled');
            $('#toolbar #btn-del').attr('disabled', 'disabled');
        }
    });
}

$(function() {
    initCodeRepositoryTable();
})

var HANDLE = {
    SAVE : 'save',
    UPDATE : 'update'
}

$(function() {

    $('#toolbar button').attr('disabled', 'disabled');

    $('#toolbar #btn-add').removeAttr('disabled');

    $('#code-repository-modal').on('show.bs.modal', function(event) {
        var btn = $(event.relatedTarget);
        var title = btn.data('modal-title');
        var handle = btn.data('handle');

        var modal = $(this);
        modal.find('.modal-title').text(title);

        $("#code-repository-edit-form").clearForm(true);

        if (handle == HANDLE.UPDATE) {
            var rows = $('#code-repository-table').bootstrapTable('getSelections');
            var row = rows[0];

            $("#code-repository-edit-form").autoFillForm(row);
            return;
        }
    });

    $('#code-repository-edit-btn').click(saveOrUpdate);

    $('#btn-del').click(function() {
        new Message().confirm('确定删除该条数据?', del);
    });

    $('#code-repository-table').bootstrapTable('resetWidth');

    $('#code-repository-query-btn').click(function() {
        codeRepositoryQueryParams = $('#code-repository-query-form').serializeJson();
        $('#code-repository-table').bootstrapTable('selectPage', 1);
    });

    $(window).resize(function() {
        $('#code-repository-table').bootstrapTable('resetWidth');
    });

})