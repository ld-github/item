var URLS = {
    GET_PAGE_DICT_TYPE : contextPath + "/dictType/getPage",
    GET_PAGE_DICT_VALUE : contextPath + "/dict/getPage"
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
        singleSelect : true,
        toolbar : '#dict-type-toolbar',
        sidePagination : 'server',
        dataField : 'records',
        queryParams : function(e) {
            page = {
                currentPage : (e.offset / e.limit) + 1,
                pageSize : e.limit,
            }
            return page;
        },
        columns : [ {
            checkbox : true,
        }, {
            field : 'code',
            title : '字典代码',
        }, {
            field : 'name',
            title : '字典名称',
        }, {
            field : 'remark',
            title : '备注',
        } ],
        onCheck : function(row) {
            if ($('#dict-value-table').bootstrapTable('getOptions').pageNumber > 1) {
                $('#dict-value-table').bootstrapTable('selectPage', 1);
                return;
            }
            $('#dict-value-table').bootstrapTable('refresh');
        },
        onUncheck : function(row) {
            $('#dict-value-table').bootstrapTable('removeAll');
        },
        onPageChange : function(number, size) {
            $('#dict-value-table').bootstrapTable('removeAll');
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
        height : 343,
        minimumCountColumns : 1,
        pageSize : 5,
        pageList : [ 5, 10, 25, 50, 100, 'All' ],
        idField : 'id',
        singleSelect : true,
        toolbar : '#dict-value-toolbar',
        sidePagination : 'server',
        dataField : 'records',
        queryParams : function(e) {
            var rows = $('#dict-type-table').bootstrapTable('getSelections');
            var typeId = rows.length > 0 ? rows[0].id : null;

            page = {
                currentPage : (e.offset / e.limit) + 1,
                pageSize : e.limit,
                typeId : typeId
            }
            return page;
        },
        columns : [ {
            checkbox : true,
        }, {
            field : 'value',
            title : '字典值',
        }, {
            field : 'name',
            title : '字典名称',
        }, {
            field : 'remark',
            title : '备注',
        } ],
        onCheck : function(row, element, field) {

        }
    });
}

$(function() {
    initDictTypeTable();

    initDictTable();
})