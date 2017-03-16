package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.been.Page;
import com.ld.web.been.model.CodeRepository;
import com.ld.web.biz.CodeRepositoryBiz;
import com.ld.web.dao.CodeRepositoryDao;

/**
 * 
 *<p>Title: CodeRepositoryBizImpl</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-16
 */
@Service
@Transactional
public class CodeRepositoryBizImpl implements CodeRepositoryBiz {

    @Resource
    private CodeRepositoryDao codeRepositoryDao;

    @Override
    public Page<CodeRepository> getPage(Page<CodeRepository> page, String name) {
        return codeRepositoryDao.getPage(page, name);
    }

}
