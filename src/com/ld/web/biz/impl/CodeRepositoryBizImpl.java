package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.been.Page;
import com.ld.web.been.dto.RepoInfo;
import com.ld.web.been.model.CodeRepository;
import com.ld.web.biz.CodeRepositoryBiz;
import com.ld.web.dao.CodeRepositoryDao;
import com.ld.web.task.CloneRepositoryTask;
import com.ld.web.util.JGitTool;

/**
 * 
 *<p>Title: CodeRepositoryBizImpl</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-16
 */
@Service
@Transactional
public class CodeRepositoryBizImpl implements CodeRepositoryBiz {

    @Resource
    private CodeRepositoryDao codeRepositoryDao;

    @Override
    public Page<CodeRepository> getPage(Page<CodeRepository> page, String name) {
        return codeRepositoryDao.getPage(page, name);
    }

    @Override
    public void save(CodeRepository codeRepository) {
        codeRepositoryDao.save(codeRepository);
    }

    @Override
    public void update(CodeRepository codeRepository) {
        codeRepositoryDao.update(codeRepository);
    }

    @Override
    public CodeRepository get(String id) {
        return codeRepositoryDao.getUniqueResult(id);
    }

    @Override
    public void delete(CodeRepository codeRepository) {
        codeRepositoryDao.delete(codeRepository);
    }

    @Override
    public void updateCloneIngToError() {

        if (CloneRepositoryTask.getInstance().getActiveCount() == 0
                && CloneRepositoryTask.getInstance().getTaskSize() == 0) {
            codeRepositoryDao.updateCloneIngToError();
        }
    }

    @Override
    public RepoInfo getRepoInfo(CodeRepository c) throws Exception {

        JGitTool j = new JGitTool(c.getLocalPath(), c.getRemotePath(), null, null);

        RepoInfo info = new RepoInfo(j.getLocalCurrentBranch(), j.getLocalBranchList(), j.getRemoteBranchList());

        j.closeRepo();

        return info;
    }

}
