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
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 4700091075048587183L;

    public static final String DICT_NAME_IS_DEBUG = "is_debug";
    public static final String DICT_NAME_UPLOAD_FILE_PATH = "upload_file_path";

    public boolean debug; // 是否是debug模式

    public String uploadFilePath; // 上传文件路径

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public SystemConfig(boolean debug, String uploadFilePath) {
        this.debug = debug;
        this.uploadFilePath = uploadFilePath;
    }

}
