package com.ld.web.dao;

import com.ld.web.been.Page;
import com.ld.web.been.model.CodeRepository;

/**
 * 
 *<p>Title: CodeRepositoryDao</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-16
 */
public interface CodeRepositoryDao extends BaseDao<CodeRepository> {

    Page<CodeRepository> getPage(Page<CodeRepository> page, String name);

}
