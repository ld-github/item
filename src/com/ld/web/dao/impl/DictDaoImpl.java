package com.ld.web.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ld.web.been.Page;
import com.ld.web.been.model.Dict;
import com.ld.web.dao.DictDao;
import com.ld.web.util.StringUtil;

/**
 * 
 *<p>Title: DictDaoImpl</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
@Repository
public class DictDaoImpl extends BaseDaoImpl<Dict> implements DictDao {

    @Override
    public Page<Dict> getPage(Page<Dict> page, String typeId, String value, String name) {
        String where = "WHERE o.type.id=:typeId ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("typeId", typeId);

        if (!StringUtil.isEmpty(value)) {
            where += "AND o.value LIKE :value ";
            params.put("value", "%" + value + "%");
        }

        if (!StringUtil.isEmpty(name)) {
            where += "AND o.name LIKE :name ";
            params.put("name", "%" + name + "%");
        }

        LinkedHashMap<String, String> orders = new LinkedHashMap<String, String>();
        orders.put("o.value", "asc");

        return getPage(where, params, orders, page);
    }

    @Override
    public List<Dict> get(String typeCode) {
        String where = "WHERE o.type.code=:code";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", typeCode);

        return getList(where, params, null);
    }

}
