package com.ld.web.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ld.web.been.Page;
import com.ld.web.been.ServerResp;
import com.ld.web.been.model.DictType;
import com.ld.web.biz.DictTypeBiz;
import com.ld.web.util.StringUtil;

/**
 * 
 *<p>Title: DictTypeController</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-13
 */
@Controller
@Scope("prototype")
@RequestMapping(DictTypeController.REQUEST_INDEX_URL)
public class DictTypeController extends BaseController {

    private static final long serialVersionUID = -3666784443323247091L;

    public static final String REQUEST_INDEX_URL = "/dictType";

    @Resource
    private DictTypeBiz dictTypeBiz;

    @RequestMapping(value = "getPage")
    @ResponseBody
    private Page<DictType> getPage(Page<DictType> page, String code, String name) {

        return dictTypeBiz.getPage(page, code, name);
    }

    @RequestMapping(value = "save")
    @ResponseBody
    private ServerResp save(DictType dictType) {

        if (StringUtil.isEmpty(dictType.getCode())) {
            return new ServerResp(false, "字典类型代码不能为空");
        }

        DictType _dictType = dictTypeBiz.get(dictType.getCode());

        if (null != _dictType) {
            return new ServerResp(false, "该字典类型代码已经存在");
        }

        dictType.setCanView(true);
        dictTypeBiz.save(dictType);

        return new ServerResp(true, "保存成功");
    }

    @RequestMapping(value = "update")
    @ResponseBody
    private ServerResp update(DictType dictType) {

        if (StringUtil.isEmpty(dictType.getCode())) {
            return new ServerResp(false, "字典类型代码不能为空");
        }

        DictType _dictType = dictTypeBiz.get(dictType.getCode());

        if (null != _dictType && !_dictType.getId().equals(dictType.getId())) {
            return new ServerResp(false, "该字典类型代码已经存在");
        }

        _dictType = dictTypeBiz.getById(dictType.getId());

        if (null == _dictType) {
            return new ServerResp(false, "该字典类型不存在，请刷新后再试");
        }

        _dictType.update(dictType);
        dictTypeBiz.update(_dictType);

        return new ServerResp(true, "保存成功");
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public ServerResp delete(DictType dictType) {

        DictType _dictType = dictTypeBiz.getById(dictType.getId());
        if (null == _dictType) {
            return new ServerResp(false, "该字典类型不存在，请刷新后再试");
        }

        dictTypeBiz.delete(_dictType);

        return new ServerResp(true, "删除成功");
    }

}
