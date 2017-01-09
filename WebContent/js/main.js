var URLS = {
    GET_INFO : contextPath + '/user/getInfo',
    WELCOME_PAGE : contextPath + '/index',
}

/**
 * @author LD
 */
var MENUS = [ {
    title : '系统管理',
    iconCls : 'icon-more',
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
    iconCls : 'icon-more',
    submenus : [ {
        title : '异常信息',
        url : contextPath + '/login'
    } ]
} ];

/**
 * Init menu to menu-panel
 */
function initMenu() {
    $('#menu-panel').accordion({
        fit : true,
    });
    for (var i = 0; i < MENUS.length; i++) {
        var title = MENUS[i].title;
        var iconCls = MENUS[i].iconCls;

        var submenus = $('<UL>').addClass('submenus-panel');
        for (var j = 0; j < MENUS[i].submenus.length; j++) {
            var menu = MENUS[i].submenus[j];
            $('<LI>').addClass('submenu-item-panel').appendTo(submenus).html(menu.title).data('menu', menu);
        }
        $('#menu-panel').accordion('add', {
            title : title,
            iconCls : iconCls,
            selected : false,
            content : submenus,
        });
    }
    $('.submenu-item-panel').click(function() {
        $('.submenu-item-panel').removeClass('selected');
        $(this).addClass('selected');

        var menu = $(this).data('menu');
        if (menu.url) {
            addTab(menu.title, menu.url);
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

});

$(function() {
    var app = new Vue({
        el : '#app',
        data : {
            message : 'hello, vue'
        }
    });

});