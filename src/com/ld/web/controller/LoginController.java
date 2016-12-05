package com.ld.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 *<p>Title: LoginController</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-03
 */
@Controller
@Scope("prototype")
@RequestMapping(LoginController.REQUEST_INDEX_URL)
public class LoginController extends BaseController {

    public static final String REQUEST_INDEX_URL = "/login";

    private static final long serialVersionUID = 596021065899369405L;

    @RequestMapping
    public String index() {
        return "login";
    }
}