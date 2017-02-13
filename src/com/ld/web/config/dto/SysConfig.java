package com.ld.web.config.dto;

import java.io.Serializable;

/**
 * 
 *<p>Title: SysConfig</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-13
 */
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 4700091075048587183L;

    public String uploadFilePath; // 上传文件路径

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public SysConfig(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

}
