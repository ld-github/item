package com.ld.web.dao;

import com.ld.web.been.Page;
import com.ld.web.been.model.DictType;

/**
 * 
 *<p>Title: DictTypeDao</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
public interface DictTypeDao extends BaseDao<DictType> {

    /**
     * 
     * @param code
     * @return
     */
    DictType get(String code);

    /**
     * Get page
     * 
     * @param page
     * @param code
     * @param name
     * @return
     */
    Page<DictType> getPage(Page<DictType> page, String code, String name);

    /**
     * Check code is exist
     * 
     * @param code
     * @param exceptId
     * @return
     */
    boolean isExist(String code, String exceptId);

}
