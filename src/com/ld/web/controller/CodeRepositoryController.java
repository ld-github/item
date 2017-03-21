package com.ld.web.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ld.web.been.Page;
import com.ld.web.been.ServerResp;
import com.ld.web.been.dto.RepoInfo;
import com.ld.web.been.model.CodeRepository;
import com.ld.web.biz.CodeRepositoryBiz;
import com.ld.web.enumeration.CloneStatus;
import com.ld.web.task.CloneRepositoryTask;
import com.ld.web.util.FileManager;
import com.ld.web.util.StringUtil;

/**
 * 
 *<p>Title: CodeRepositoryController</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-20
 */
@Controller
@Scope("prototype")
@RequestMapping(CodeRepositoryController.REQUEST_INDEX_URL)
public class CodeRepositoryController extends BaseController {

    private static final long serialVersionUID = 844644663242860874L;

    public static final String REQUEST_INDEX_URL = "/codeRepository";

    @Resource
    private CodeRepositoryBiz codeRepositoryBiz;

    @RequestMapping(value = "getPage")
    @ResponseBody
    public Page<CodeRepository> getPage(Page<CodeRepository> page, String name) {

        codeRepositoryBiz.updateCloneIngToError();

        return codeRepositoryBiz.getPage(page, name);
    }

    @RequestMapping(value = "saveOrUpdate")
    @ResponseBody
    public ServerResp saveOrUpdate(CodeRepository codeRepository) {

        if (null == codeRepository) {
            return new ServerResp(false, "数据错误");
        }

        if (StringUtil.isEmpty(codeRepository.getName())) {
            return new ServerResp(false, "名称不允许为空");
        }

        if (StringUtil.isEmpty(codeRepository.getRemotePath())) {
            return new ServerResp(false, "远端地址不允许为空");
        }

        if (StringUtil.isEmpty(codeRepository.getLocalPath())) {
            return new ServerResp(false, "本地地址不允许为空");
        }

        if (StringUtil.isEmpty(codeRepository.getId())) {

            codeRepository.init();
            codeRepositoryBiz.save(codeRepository);

            try {
                CloneRepositoryTask.getInstance().put(codeRepository);
            } catch (InterruptedException e) {
            }

            return new ServerResp(true, "保存成功");
        }

        CodeRepository _codeRepository = codeRepositoryBiz.get(codeRepository.getId());

        if (null == _codeRepository) {
            return new ServerResp(false, "该源码库不存在，请刷新后再试");
        }

        _codeRepository.update(codeRepository);
        codeRepositoryBiz.update(_codeRepository);

        return new ServerResp(true, "保存成功");
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public ServerResp delete(String id) {

        CodeRepository codeRepository = codeRepositoryBiz.get(id);

        if (null == codeRepository) {
            return new ServerResp(false, "该源码库不存在，请刷新后再试");
        }

        codeRepositoryBiz.delete(codeRepository);

        return new ServerResp(true, "删除成功");
    }

    @RequestMapping(value = "cloneAgain")
    @ResponseBody
    public ServerResp cloneAgain(String id) {

        CodeRepository c = codeRepositoryBiz.get(id);
        if (null == c) {
            return new ServerResp(false, "该源码库不存在，请刷新后再试");
        }

        if (CloneStatus.CLONE_ING.value() == c.getCloneStatus().value()) {
            return new ServerResp(false, "该源码库正在后台执行Clone任务中");
        }

        if (!FileManager.delDir(c.getLocalPath())) {
            return new ServerResp(false, "删除本地库失败，请稍后再试");
        }

        c.setCloneStatus(CloneStatus.NOT_INIT);
        codeRepositoryBiz.update(c);

        try {
            CloneRepositoryTask.getInstance().put(c);
        } catch (InterruptedException e) {
            return new ServerResp(false, "添加任务失败");
        }

        return new ServerResp(true, "添加任务成功，后台任务处理中");
    }

    @RequestMapping(value = "getRepoInfo")
    @ResponseBody
    public ServerResp getRepoInfo(String id) {

        CodeRepository c = codeRepositoryBiz.get(id);

        if (null == c) {
            return new ServerResp(false, "该源码库不存在，请刷新后再试");
        }

        RepoInfo repoInfo = null;

        try {
            repoInfo = codeRepositoryBiz.getRepoInfo(c);
        } catch (Exception e) {
            return new ServerResp(false, "获取分支信息异常");
        }

        return new ServerResp(true, "获取信息成功", repoInfo);
    }

}
