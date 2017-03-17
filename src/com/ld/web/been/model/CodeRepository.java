package com.ld.web.been.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ld.web.util.CustomBeanUtils;

/**
 * 
 *<p>Title: CodeRepository</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-16
 */
@Entity
@Table(name = "t_code_repository")
public class CodeRepository extends BaseModel {

    private static final long serialVersionUID = -7672786169715061452L;

    @Column(length = 100)
    private String name; // 库名称

    @Column
    private String remotePath; // 远端路径

    @Column
    private String localPath; // 本地路径

    @Column
    private String codePath; // 代码路径

    @Column
    private String remark; // 备注

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDatetime; // 创建时间

    public String getRemotePath() {
        return remotePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public String getRemark() {
        return remark;
    }

    public String getName() {
        return name;
    }

    public String getCodePath() {
        return codePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public void init() {
        this.createDatetime = new Date();
    }

    public void update(CodeRepository codeRepository) {
        CustomBeanUtils.copyProperties(codeRepository, this, null, true, "createDatetime");
    }

}
