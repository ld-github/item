package com.ld.web.config;

import java.util.List;

import com.ld.web.been.model.Dict;
import com.ld.web.been.model.DictType;
import com.ld.web.biz.DictBiz;
import com.ld.web.component.ApplicationContextHolder;
import com.ld.web.config.dto.SysConfig;

/**
 * 
 *<p>Title: BasicConfiguration</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-13
 */
public class BasicConfiguration {

    private static final BasicConfiguration INSTANCE = new BasicConfiguration();

    private SysConfig sysConfig; // 系统配置

    private DictBiz dictBiz;

    private void loadConfig() {
        dictBiz = (DictBiz) ApplicationContextHolder.getSpringBean("dictBizImpl");

        List<Dict> dicts = dictBiz.get(DictType.CODE_UPLOAD_FILE_PATH);

        String uploadFilePath = null == dicts || dicts.isEmpty() ? null : dicts.get(0).getValue();

        sysConfig = new SysConfig(uploadFilePath);
    }

    public SysConfig getSysConfig() {
        return sysConfig;
    }

    public static BasicConfiguration getInstance() {
        return INSTANCE;
    }

    private BasicConfiguration() {
        loadConfig();
    }

}
