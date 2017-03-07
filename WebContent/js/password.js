var URLS = {
    FILE_UPLOAD : contextPath + '/file/upload'
};

/**
 * 初始化上传控件
 */
function initUploader() {
    var uploader = new Uploader().init('#uploader-box');

    uploader.option('server', URLS.FILE_UPLOAD);

    uploader.onUploadBeforeSend = function(object, data, headers) {
        console.log(data);
    };

    uploader.onUploadSuccess = function(file, response) {
        console.log(response);
    };

    $('#uploader-panel').modal('show');
}

$(function() {

    initUploader();

});