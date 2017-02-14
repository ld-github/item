(function($) {
    $.fn.autoFillForm = function(json) {

        var inputs = $(this).find('input');

        $.each(inputs, function(i, item) {
            var input = $(item);
            var key = input.attr('name');

            if (key != undefined) {
                var keys = key.split('.');
                var value = undefined;
                for (var i = 0; i < keys.length; i++) {
                    if (undefined == value) {
                        value = json[keys[i]];
                    } else {
                        value = value[keys[i]];
                    }
                }

                if (undefined != value) {
                    input.val(value);
                }
            }
        });
    };
})(jQuery);

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

function checkRespCodeSucc(data) {
    return null != data && '00' === data.respCode;
}

function bootstrapTableRefreshCurrentPage(tableId) {

    var currentPage = $(tableId).bootstrapTable('getOptions').pageNumber;

    if (currentPage > 1 && $(tableId).bootstrapTable('getData').length == 0) {
        $(tableId).bootstrapTable('selectPage', currentPage - 1);
        return;
    }

    $(tableId).bootstrapTable('refresh');
}

function bootstrapTableSelectFirstPage(tableId) {

    var currentPage = $(tableId).bootstrapTable('getOptions').pageNumber;

    if (currentPage > 1) {
        $(tableId).bootstrapTable('selectPage', 1);
        return;
    }

    $(tableId).bootstrapTable('refresh');
}