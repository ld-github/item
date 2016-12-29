var URLS = {
    GET_INFO : contextPath + '/user/getInfo',
}

$(function() {

    $.post(URLS.GET_INFO, function(data) {
        console.log(data.username);
    });
});

$(function() {

    var app = new Vue({
        el : '#app',
        data : {
            message : 'hello, vue'
        }
    });
});