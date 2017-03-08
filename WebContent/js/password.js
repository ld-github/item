var URLS = {
    FILE_UPLOAD : contextPath + '/file/upload'
};

/**
 * 初始化上传控件
 */

var uploader = null;

function initUploader() {

    $('#uploader-panel').modal('show');

    $('#uploader-panel').on('shown.bs.modal', function() {

        uploader = new Uploader().init(2);

        uploader.option('server', URLS.FILE_UPLOAD);

        uploader.onUploadBeforeSend = function(object, data, headers) {
            console.log(data);
        };

        uploader.onUploadSuccess = function(file, response) {
            console.log(response);
        };

    });

}

$(function() {

    initUploader();

    $('#open-uploader').click(function() {
        initUploader();
    });

});