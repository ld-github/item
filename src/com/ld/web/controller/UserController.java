package com.ld.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ld.web.been.model.User;
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
    public ModelAndView login(HttpServletRequest req) {
        return null;
    }

    @RequestMapping(value = "/getInfo")
    @ResponseBody
    public User getInfo() {
        return new User("LD", "888888");
    }
}
