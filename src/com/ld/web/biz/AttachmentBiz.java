package com.ld.web.biz;

import com.ld.web.been.model.Attachment;

/**
 * 
 *<p>Title: AttachmentBiz</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-13
 */
public interface AttachmentBiz {

    void save(Attachment attachment);

    Attachment getById(String id);

    void delete(Attachment attachment);

}
