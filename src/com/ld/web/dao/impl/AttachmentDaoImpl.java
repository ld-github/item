package com.ld.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.ld.web.been.model.Attachment;
import com.ld.web.dao.AttachmentDao;

/**
 * 
 *<p>Title: AttachmentDaoImpl</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-13
 */
@Repository
public class AttachmentDaoImpl extends BaseDaoImpl<Attachment> implements AttachmentDao {

    @Override
    public Attachment getById(String id) {
        return getUniqueResult(id);
    }

}
