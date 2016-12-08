package com.ld.web.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ld.web.been.model.DictType;
import com.ld.web.dao.DictTypeDao;

/**
 * 
 *<p>Title: DictTypeDaoImpl</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
@Repository
public class DictTypeDaoImpl extends BaseDaoImpl<DictType> implements DictTypeDao {

    @Override
    public DictType get(String code) {
        String where = "WHERE o.code=:code";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);

        return super.getUniqueResult(where, params);
    }

}
