package com.ld.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ld.web.been.model.Manager;
import com.ld.web.biz.ManagerBiz;

/**
 * 
 *<p>Title: ManagerController</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-03
 */
@Controller
@Scope("prototype")
@RequestMapping("/manager")
public class ManagerController extends BaseController {

    private static final long serialVersionUID = 596021065899369405L;

    @Resource
    private ManagerBiz managerBiz;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ModelAndView login(HttpServletRequest req) {
        System.out.println(hashCode());

        Manager m = new Manager("abcd", "123");
        managerBiz.save(m);

        return null;
    }
}