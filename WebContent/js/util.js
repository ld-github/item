/**
 * Load form value from json
 * 
 * @param formId
 * @param json
 */
function formLoadJson(formId, json) {
    var inputs = $(formId + ' :input');
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
}

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
        layer.msg(msg);
    }

    this.confirm = function(msg, callback, param) {
        layer.confirm(msg, function(lay) {
            if (callback && typeof (callback) === 'function') {
                layer.close(lay);
                callback(param);
            }
        })
    }

    this.tipLeft = function(id, msg, time) {
        time = !time ? 5000 : time;
        layer.tips(msg, $(id), {
            tips : [ 4, '#3595CC' ],
            time : time,
        });
    }
}