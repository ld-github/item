var URLS = {
    GET_PAGE_CODE_REPOSITORY : contextPath + "/codeRepository/getPage",
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
        height : 343,
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
            field : 'remotePath',
            title : '远端地址',
        }, {
            field : 'localPath',
            title : '本地路径',
        }, {
            field : 'name',
            title : '名称',
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

$(function() {
    $('#toolbar button').attr('disabled', 'disabled');

    $('#toolbar #btn-add').removeAttr('disabled');
})