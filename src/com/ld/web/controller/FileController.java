package com.ld.web.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ld.web.been.ServerResp;

@Controller
@Scope("prototype")
@RequestMapping(FileController.REQUEST_INDEX_URL)
public class FileController extends BaseController {

    private static final long serialVersionUID = 590123766546599220L;

    private static Logger logger = Logger.getLogger(FileController.class);

    public static final String REQUEST_INDEX_URL = "/file";

    @RequestMapping(value = "/upload")
    @ResponseBody
    public ServerResp upload(MultipartFile file) {
        logger.info("Upload file begin...");

        return null;
    }

}
