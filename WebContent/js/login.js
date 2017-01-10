var URLS = {
    VERIFICATION_CODE : contextPath + "/kaptcha.jpg"
}

$(function() {
    $('#verification-code-img').click(function() {
        var src = URLS.VERIFICATION_CODE + "?" + Math.floor(Math.random() * 100);
        $(this).hide().attr('src', src).fadeIn();
    });

});