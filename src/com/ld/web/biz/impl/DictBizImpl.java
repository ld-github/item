package com.ld.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.been.Page;
import com.ld.web.been.model.Dict;
import com.ld.web.been.model.DictType;
import com.ld.web.biz.DictBiz;
import com.ld.web.config.BasicConfiguration;
import com.ld.web.dao.DictDao;

/**
 * 
 *<p>Title: DictBizImpl</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
@Service
@Transactional
public class DictBizImpl implements DictBiz {

    @Resource
    private DictDao dictDao;

    @Override
    public void delete(Dict dict) throws Exception {
        loadConfig(dict);

        dictDao.delete(dict);
    }

    @Override
    public Page<Dict> getPage(Page<Dict> page, String typeId, String value, String name) {
        return dictDao.getPage(page, typeId, value, name);
    }

    @Override
    public Dict getById(String id) {
        return dictDao.getUniqueResult(id);
    }

    @Override
    public void update(Dict dict) throws Exception {
        loadConfig(dict);

        dictDao.update(dict);
    }

    @Override
    public void save(Dict dict) throws Exception {
        loadConfig(dict);

        dictDao.save(dict);
    }

    @Override
    public List<Dict> get(String typeCode) {
        return dictDao.get(typeCode);
    }

    private void loadConfig(Dict dict) throws Exception {

        String code = null != dict.getType() ? dict.getType().getCode() : null;

        if (DictType.SYSTEM_CONFIG.equals(code)) {
            BasicConfiguration.getInstance().loadConfig();
        }
    }

}
