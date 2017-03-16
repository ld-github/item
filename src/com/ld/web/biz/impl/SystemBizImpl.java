package com.ld.web.biz.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.been.model.Dict;
import com.ld.web.been.model.DictType;
import com.ld.web.been.model.User;
import com.ld.web.biz.DictTypeBiz;
import com.ld.web.biz.SystemBiz;
import com.ld.web.biz.UserBiz;
import com.ld.web.config.dto.SystemConfig;

/**
 * 
 *<p>Title: SystemBizImpl</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-11
 */
@Service
@Transactional
public class SystemBizImpl implements SystemBiz {

    String userHome = System.getProperty("user.home");
    final String FILE_UPLOAD_PATH = userHome + File.separator + "publish_system" + File.separator + "upload";

    @Resource
    private UserBiz userBiz;

    @Resource
    private DictTypeBiz dictTypeBiz;

    @Override
    public void initBasicData() {

        if (userBiz.getTotal() == 0) {
            User user = new User("admin", "!QAZ2wsx", "超级管理员", null, null, "超级管理员");

            userBiz.save(user);
        }

        DictType type = dictTypeBiz.get(DictType.SYSTEM_CONFIG);

        if (null == type) {
            List<Dict> dicts = new ArrayList<Dict>();

            type = new DictType(DictType.SYSTEM_CONFIG, "系统参数配置", true, "系统参数配置", dicts);

            dicts.add(new Dict(type, FILE_UPLOAD_PATH, SystemConfig.DICT_NAME_UPLOAD_FILE_PATH, true, "文件上传路径"));
            dicts.add(new Dict(type, Boolean.toString(true), SystemConfig.DICT_NAME_IS_DEBUG, true, "是否是debug模式"));

            dictTypeBiz.save(type);
        }

    }
}
