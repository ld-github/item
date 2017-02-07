/**
 * Jquery serializeArray to serializeJson
 */
(function($) {
    $.fn.serializeJson = function() {

        var serializeObj = {};
        var array = this.serializeArray();

        $(array).each(function() {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [ serializeObj[this.name], this.value ];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });

        return serializeObj;
    };
})(jQuery);

var Message = function() {

    this.show = function(msg) {
        layui.use('layer', function() {
            layui.layer.msg(msg);
        })
    }

    this.confirm = function(msg, callback, param) {
        layui.use('layer', function() {
            layui.layer.confirm(msg, function(lay) {
                if (callback && typeof (callback) === 'function') {
                    layui.layer.close(lay);
                    callback(param);
                }
            })
        })
    }

    this.tipLeft = function(id, msg, time) {
        time = time || 5000;

        layui.use('layer', function() {
            layui.layer.tips(msg, $(id), {
                tips : [ 4, '#3595CC' ],
                time : time,
            });
        })
    }

}

function checkRespCodeSucc(data) {
    return null != data && '00' === data.respCode;
}

function bootstrapTableRefreshCurrentPage(tableId) {

    var currentPage = $(tableId).bootstrapTable('getOptions').pageNumber;

    $(tableId).bootstrapTable('refresh');

    if (currentPage > 1 && $(tableId).bootstrapTable('getData').length == 0) {
        $(tableId).bootstrapTable('prevPage');
    }
}

function bootstrapTableSelectFirstPage(tableId) {

    var currentPage = $(tableId).bootstrapTable('getOptions').pageNumber;

    if (currentPage > 1) {
        $(tableId).bootstrapTable('selectPage', 1);
        return;
    }
    $(tableId).bootstrapTable('refresh');
}