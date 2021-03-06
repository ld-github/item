package com.ld.web.biz;

import java.util.List;

import com.ld.web.been.Page;
import com.ld.web.been.model.Dict;

/**
 * 
 *<p>Title: DictBiz</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
public interface DictBiz {

    void delete(Dict dict) throws Exception;

    void update(Dict dict) throws Exception;

    void save(Dict dict) throws Exception;

    Dict getById(String id);

    List<Dict> get(String typeCode);

    Page<Dict> getPage(Page<Dict> page, String typeId, String value, String name);
}
