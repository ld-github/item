package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.been.model.Attachment;
import com.ld.web.biz.AttachmentBiz;
import com.ld.web.dao.AttachmentDao;

/**
 * 
 *<p>Title: AttachmentBizImpl</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-13
 */
@Service
@Transactional
public class AttachmentBizImpl implements AttachmentBiz {

    @Resource
    private AttachmentDao attachmentDao;

    @Override
    public void save(Attachment attachment) {
        attachmentDao.save(attachment);
    }

    @Override
    public Attachment getById(String id) {
        return attachmentDao.getById(id);
    }

    @Override
    public void delete(Attachment attachment) {
        attachmentDao.delete(attachment);
    }

}
