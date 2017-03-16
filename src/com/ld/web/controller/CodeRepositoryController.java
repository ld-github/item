package com.ld.web.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ld.web.been.Page;
import com.ld.web.been.model.CodeRepository;
import com.ld.web.biz.CodeRepositoryBiz;

/**
 * 
 *<p>Title: CodeRepositoryController</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-16
 */
@Controller
@Scope("prototype")
@RequestMapping(CodeRepositoryController.REQUEST_INDEX_URL)
public class CodeRepositoryController extends BaseController {

    private static final long serialVersionUID = 844644663242860874L;

    public static final String REQUEST_INDEX_URL = "/codeRepository";

    @Resource
    private CodeRepositoryBiz codeRepositoryBiz;

    @RequestMapping(value = "getPage")
    @ResponseBody
    public Page<CodeRepository> getPage(Page<CodeRepository> page, String name) {
        return codeRepositoryBiz.getPage(page, name);
    }

}
