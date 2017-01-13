var URLS = {
    GET_PAGE_DICT_TYPE : contextPath + "/dictType/getPage"
}

function initTable() {
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
        minimumCountColumns : 1,
        height : 500,
        pageList : [ 10, 25, 50, 100, 'All' ],
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
            field : 'code',
            title : '字典代码',
        }, {
            field : 'name',
            title : '字典名称',
        }, {
            field : 'remark',
            title : '备注',
        } ]
    });
}

$(function() {
    initTable();
})