package com.ld.web.dao;

import com.ld.web.been.model.Attachment;

/**
 * 
 *<p>Title: AttachmentDao</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-13
 */
public interface AttachmentDao extends BaseDao<Attachment> {

    void save(Attachment attachment);

    Attachment getById(String id);

    void delete(Attachment attachment);

}
