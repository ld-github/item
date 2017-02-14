package com.ld.web.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ld.web.been.ServerResp;
import com.ld.web.been.model.Attachment;
import com.ld.web.config.BasicConfiguration;
import com.ld.web.util.FileManager;

/**
 * 
 *<p>Title: FileController</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-13
 */
@Controller
@Scope("prototype")
@RequestMapping(FileController.REQUEST_INDEX_URL)
public class FileController extends BaseController {

    private static final long serialVersionUID = 590123766546599220L;

    private static Logger logger = Logger.getLogger(FileController.class);

    public static final String REQUEST_INDEX_URL = "/file";

    @RequestMapping(value = "/upload")
    @ResponseBody
    public ServerResp upload(MultipartFile file, String name, Attachment attachment) {
        logger.info(String.format("Upload file begin, filename: %s", name));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        String year = cal.get(Calendar.YEAR) + "";
        String month = cal.get(Calendar.MONTH) + 1 + "";
        String date = cal.get(Calendar.DATE) + "";

        String suffixFilePath = File.separator + year + File.separator + month + File.separator + date;

        String uploadDir = BasicConfiguration.getInstance().getSysConfig().getUploadFilePath() + suffixFilePath;

        try {
            File dir = new File(uploadDir);
            FileManager.mkdirs(dir);

            String uuid = UUID.randomUUID().toString().replace("-", "");
            String realFileName = uuid + FileManager.getSuffixName(name);

            File serverFile = new File(uploadDir + File.separator + realFileName);
            FileCopyUtils.copy(file.getBytes(), serverFile);

            if (null == attachment) {
                attachment = new Attachment(name, realFileName, serverFile.getAbsolutePath(), dir.getAbsolutePath());
            }

            return new ServerResp(true, "上传文件出现成功", attachment);
        } catch (Exception e) {
            logger.error(String.format("Upload file error: %s", e.getMessage()), e);

            try {
                getResponse().sendError(500);
            } catch (Exception e1) {
                logger.error(String.format("Http response error: %s", e1.getMessage()), e1);
            }

            return new ServerResp(false, "上传文件出现异常");
        }
    }

}
