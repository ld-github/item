package com.ld.web.config;

import java.util.List;

import org.apache.log4j.Logger;

import com.ld.web.been.model.Dict;
import com.ld.web.been.model.DictType;
import com.ld.web.biz.DictBiz;
import com.ld.web.component.ApplicationContextHolder;
import com.ld.web.config.dto.SystemConfig;
import com.ld.web.util.JsonMapper;

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

    private static Logger logger = Logger.getLogger(BasicConfiguration.class);

    private static final BasicConfiguration INSTANCE = new BasicConfiguration();

    private SystemConfig sysConfig; // 系统配置

    private DictBiz dictBiz;

    SystemConfig sysConfigBak = null;

    public void loadConfig() throws Exception {
        try {
            dictBiz = (DictBiz) ApplicationContextHolder.getSpringBean("dictBizImpl");

            List<Dict> dicts = dictBiz.get(DictType.SYSTEM_CONFIG);

            boolean isDebug = false;
            String uploadFilePath = null;

            if (null != dicts && !dicts.isEmpty()) {
                for (Dict d : dicts) {
                    if (SystemConfig.DICT_NAME_IS_DEBUG.equals(d.getName())) {
                        isDebug = Boolean.valueOf(d.getValue());
                        continue;
                    }

                    if (SystemConfig.DICT_NAME_UPLOAD_FILE_PATH.equals(d.getName())) {
                        uploadFilePath = d.getValue();
                        continue;
                    }
                }
            }

            sysConfig = new SystemConfig(isDebug, uploadFilePath);

            sysConfigBak = sysConfig;

            logger.debug(String.format("System config: %s", JsonMapper.getInstance().toJson(sysConfig)));

        } catch (Exception e) {
            logger.error(String.format("Load config error: %s", e.getMessage()), e);

            sysConfig = sysConfigBak;

            throw new Exception(e);
        }

    }

    public SystemConfig getSysConfig() {
        return sysConfig;
    }

    public static BasicConfiguration getInstance() {
        return INSTANCE;
    }

    private BasicConfiguration() {
        try {
            loadConfig();
        } catch (Exception e) {
        }
    }

}
