$(function() {
    /**
     * Ajax Start
     */
    $(document).ajaxStart(function() {
        $('#loading').show();
    });

    /**
     * Ajax Stop
     */
    $(document).ajaxStop(function() {
        $('#loading').hide();
    });

    /**
     * Ajax Error
     */
    $(document).ajaxError(function(event, response, settings) {
        $('#loading').hide();
        if (response.status == 518) {
            window.top.location.href = 'login';
        }
    });
});