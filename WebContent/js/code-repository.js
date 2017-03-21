var URLS = {
    GET_PAGE_CODE_REPOSITORY : contextPath + "/codeRepository/getPage",
    SAVE_OR_UPATE_CODE_REPOSITORY : contextPath + "/codeRepository/saveOrUpdate",
    DELETE_CODE_REPOSITORY : contextPath + "/codeRepository/delete",
    CLONE_AGAIN_CODE_REPOSITORY : contextPath + "/codeRepository/cloneAgain",
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
    if (params.remotePath.indexOf('http') == -1 || params.remotePath.indexOf('git') == -1) {
        new Message().tipLeft('#code-repository-edit-btn', '远端地址错误，目前支持ssh和http协议，以git和http以及https开头');
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

function cloneAgain() {

    var rows = $('#code-repository-table').bootstrapTable('getSelections');
    var row = rows[0];

    var params = {
        id : rows[0].id
    };

    $.post(URLS.CLONE_AGAIN_CODE_REPOSITORY, params, function(data) {
        if (checkRespCodeSucc(data)) {
            new Message().show(data.respDesc);
            return;
        }
        new Message().show(data.respDesc);
    });
}

var CLONE_STATUS = {
    NOT_INIT : 'NOT_INIT',
    CLONE_ING : 'CLONE_ING',
    CLONE_SUCC : 'CLONE_SUCC',
    CLONE_ERR : 'CLONE_ERR'
};

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
        height : 360,
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
            field : 'cloneStatus',
            title : '状态',
            align : 'center',
            formatter : function(value, row, index) {
                if (CLONE_STATUS.NOT_INIT == value) {
                    return "<span style='color: red;'>未初始化</span>"
                }
                if (CLONE_STATUS.CLONE_ING == value) {
                    return "<span style='color: #FF9966;'>clone中</span>"
                }
                if (CLONE_STATUS.CLONE_SUCC == value) {
                    return "<span style='color: green;'>clone成功</span>"
                }
                if (CLONE_STATUS.CLONE_ERR == value) {
                    return "<span style='color: red;'>clone失败</span>"
                }
            }
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

            if (CLONE_STATUS.CLONE_ING != row.cloneStatus) {
                $('#toolbar #btn-clone-again').removeAttr('disabled');
            } else {
                $('#toolbar #btn-clone-again').attr('disabled', 'disabled');
            }

            if (CLONE_STATUS.CLONE_SUCC == row.cloneStatus) {
                $('#toolbar #btn-pull').removeAttr('disabled');
            } else {
                $('#toolbar #btn-pull').attr('disabled', 'disabled');
            }
        },
        onUncheck : function(row) {
            $('#toolbar [id^=btn-]').attr('disabled', 'disabled');
            $('#toolbar #btn-add').removeAttr('disabled');
        },
        onPageChange : function(number, size) {
            $('#toolbar [id^=btn-]').attr('disabled', 'disabled');
            $('#toolbar #btn-add').removeAttr('disabled');
        },
        onRefresh : function() {
            $('#toolbar [id^=btn-]').attr('disabled', 'disabled');
            $('#toolbar #btn-add').removeAttr('disabled');
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

            var isHttp = row.remotePath.indexOf('http') == 0;
            $('#username-password-panel').css('display', isHttp ? 'block' : 'none');

            $("#code-repository-edit-form").autoFillForm(row);
            return;
        }
    });

    $('#username-password-panel').css('display', 'none');
    $('#code-repository-edit-btn').click(saveOrUpdate);

    $('#btn-del').click(function() {
        new Message().confirm('确定删除该条数据?', del);
    });

    $('#btn-clone-again').click(function() {

        new Message().confirm('重新Clone会导致删除本地源码，建议在不能成功拉取源码时执行该操作，确定重新Clone该源码库?', cloneAgain);
    });

    $('#code-repository-table').bootstrapTable('resetWidth');

    $('#code-repository-query-btn').click(function() {
        codeRepositoryQueryParams = $('#code-repository-query-form').serializeJson();

        $('#code-repository-table').bootstrapTable('selectPage', 1);
    });

    $('#remote-path').blur(function() {

        var value = $(this).val();
        var isHttp = value.indexOf('http') == 0;

        $('#username-password-panel').css('display', isHttp ? 'block' : 'none');
    });

    $(window).resize(function() {
        $('#code-repository-table').bootstrapTable('resetWidth');
    });

})