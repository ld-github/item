package com.ld.web.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ld.web.been.Page;
import com.ld.web.been.model.CodeRepository;
import com.ld.web.dao.CodeRepositoryDao;
import com.ld.web.enumeration.CloneStatus;
import com.ld.web.util.StringUtil;

/**
 * 
 *<p>Title: CodeRepositoryDaoImpl</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-16
 */
@Repository
public class CodeRepositoryDaoImpl extends BaseDaoImpl<CodeRepository> implements CodeRepositoryDao {

    @Override
    public Page<CodeRepository> getPage(Page<CodeRepository> page, String name) {

        String where = "WHERE 1=1 ";

        Map<String, Object> params = new HashMap<String, Object>();

        if (!StringUtil.isEmpty(name)) {
            where += "AND o.name LIKE :name ";
            params.put("name", "%" + name + "%");
        }

        LinkedHashMap<String, String> orders = new LinkedHashMap<String, String>();
        orders.put("o.createDatetime", "desc");

        return super.getPage(where, params, orders, page);
    }

    @Override
    public void updateCloneIngToError() {

        String suffixHql = "SET o.cloneStatus=:cloneStatus WHERE o.cloneStatus=:cloneIng";

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("cloneStatus", CloneStatus.CLONE_ERR);
        params.put("cloneIng", CloneStatus.CLONE_ING);

        super.update(suffixHql, params);
    }

}
