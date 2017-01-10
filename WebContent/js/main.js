var URLS = {
    INDEX_PAGE : contextPath + '/index',
}

/**
 * @author LD
 */
var MENUS = [ {
    title : '系统管理',
    submenus : [ {
        title : '用户管理',
        url : contextPath + '/login'
    }, {
        title : '地图管理',
        url : contextPath + '/login'
    }, {
        title : '消息管理',
        url : contextPath + '/login'
    } ]
}, {
    title : '日志记录',
    submenus : [ {
        title : '异常信息',
        url : contextPath + '/login'
    } ]
}, {
    title : '帮助中心',
    url : contextPath + '/help'
} ];

/**
 * Init menu to menu-panel
 */
function initMenu() {

    var menus = $('#menus');

    $.each(MENUS, function(index, item) {
        var li = $('<LI>').addClass('layui-nav-item');
        $('<A>').attr('href', 'javascript:;').html(item.title).addClass('menu-item').data({ 'title' : item.title, 'url' : item.url }).appendTo(li);

        if (item.submenus) {
            var dl = $('<DL>').addClass('layui-nav-child');
            $.each(item.submenus, function(index, subItem) {
                $('<dd>').append($('<A>').attr('href', 'javascript:;').html(subItem.title).addClass('menu-item').data({ 'title' : subItem.title, 'url' : subItem.url })).appendTo(dl);
            });

            dl.appendTo(li);
        }
        li.appendTo(menus);
    });

    $('.menu-item').click(function() {
        var data = $(this).data();
        if (data.url) {
            addTab(data.title, data.url);
        }
    });

}

/**
 * Add page to page-panel
 * 
 * @param title
 * @param url
 * @param closable
 */
function addTab(title, url, closable) {
    closable = typeof (closable) == 'boolean' ? closable : true;
    var tab = $('#page-tabs');

    if (tab.tabs('exists', title)) {
        tab.tabs('select', title);
        return;
    }

    tab.tabs('add', {
        title : title,
        closable : closable,
        content : $('<IFRAME>').attr({
            frameborder : 0,
            src : url,
        }).css({
            height : ($('#main-panel-body').height() - 63)
        }),
    });

}

$(function() {
    initMenu();

    $('#page-tabs').tabs({
        tabHeight : 30,
        border : false,
        tabWidth : 120,
    });

    addTab("欢迎页", URLS.INDEX_PAGE, false);

    layui.use([ 'element' ], function() {
        var element = layui.element();
    });

});