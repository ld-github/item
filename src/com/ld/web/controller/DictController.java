package com.ld.web.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ld.web.been.Page;
import com.ld.web.been.ServerResp;
import com.ld.web.been.model.Dict;
import com.ld.web.been.model.DictType;
import com.ld.web.biz.DictBiz;
import com.ld.web.biz.DictTypeBiz;
import com.ld.web.util.StringUtil;

/**
 * 
 *<p>Title: DictController</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-16
 */
@Controller
@Scope("prototype")
@RequestMapping(DictController.REQUEST_INDEX_URL)
public class DictController extends BaseController {

    private static final long serialVersionUID = 5783046897297987491L;

    public static final String REQUEST_INDEX_URL = "/dict";

    @Resource
    private DictBiz dictBiz;

    @Resource
    private DictTypeBiz dictTypeBiz;

    @RequestMapping(value = "getPage")
    @ResponseBody
    public Page<Dict> getPage(Page<Dict> page, String typeId, String value, String name) {

        return dictBiz.getPage(page, typeId, value, name);
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public ServerResp save(Dict dict) {

        DictType dictType = dictTypeBiz.getById(dict.getType().getId());

        if (null == dictType) {
            return new ServerResp(false, "该字典类型不存在，请刷新后再试");
        }

        if (StringUtil.isEmpty(dict.getValue())) {
            return new ServerResp(false, "字典值不能为空");
        }

        dict.setType(dictType);
        dict.setCanUpdate(true);
        dictBiz.save(dict);

        return new ServerResp(true, "保存成功");
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ServerResp update(Dict dict) {

        if (StringUtil.isEmpty(dict.getValue())) {
            return new ServerResp(false, "字典值不能为空");
        }

        Dict _dict = dictBiz.getById(dict.getId());
        if (null == _dict) {
            return new ServerResp(false, "该字典值不存在，请刷新后再试");
        }

        _dict.update(dict);
        dictBiz.update(_dict);

        return new ServerResp(true, "保存成功");
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public ServerResp delete(Dict dict) {

        Dict _dict = dictBiz.getById(dict.getId());
        if (null == _dict) {
            return new ServerResp(false, "该字典值不存在，请刷新后再试");
        }

        _dict.getType().getDicts().remove(_dict);
        dictBiz.delete(_dict);

        return new ServerResp(true, "删除成功");
    }
}
