var URLS = {
    GET_INFO : contextPath + '/user/getInfo',
}

$(function() {
    $.post(URLS.GET_INFO, function(data) {
        console.log(data.username);
    });
});