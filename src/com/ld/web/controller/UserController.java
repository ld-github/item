package com.ld.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ld.web.been.ServerResp;
import com.ld.web.biz.UserBiz;

/**
 * 
 *<p>Title: UserController</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-03
 */
@Controller
@Scope("prototype")
@RequestMapping(UserController.REQUEST_INDEX_URL)
public class UserController extends BaseController {

    private static final long serialVersionUID = 596021065899369405L;

    public static final String REQUEST_INDEX_URL = "/user";

    @Resource
    private UserBiz userBiz;

    @RequestMapping(value = "/toLogin")
    public String toLogin(HttpServletRequest req) {
        putReqAttributes(new ServerResp(false, "请输入密码"));
        return PageController.REQUEST_PAGE_URL_LOGIN;
    }

    @RequestMapping(value = "/toLogout")
    public String toLogout(HttpServletRequest req) {
        super.removeSessionUser();
        return redirect(PageController.REQUEST_PAGE_URL_LOGIN);
    }

}
