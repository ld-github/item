package com.ld.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 *<p>Title: LandingController</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-03
 */
@Controller
@Scope("prototype")
@RequestMapping(LandingController.REQUEST_INDEX_URL)
public class LandingController extends BaseController {

    private static final long serialVersionUID = -3915106687680179329L;

    public static final String REQUEST_INDEX_URL = "/landing";

    @RequestMapping
    public String index() {
        return "landing";
    }
}
