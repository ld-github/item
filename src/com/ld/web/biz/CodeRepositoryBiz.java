package com.ld.web.biz;

import com.ld.web.been.Page;
import com.ld.web.been.model.CodeRepository;

/**
 * 
 *<p>Title: CodeRepositoryBiz</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-16
 */
public interface CodeRepositoryBiz {

    Page<CodeRepository> getPage(Page<CodeRepository> page, String name);

    void save(CodeRepository codeRepository);

    void update(CodeRepository codeRepository);

    CodeRepository get(String id);

    void delete(CodeRepository codeRepository);

}
