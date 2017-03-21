package com.ld.web.been.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ld.web.enumeration.CloneStatus;
import com.ld.web.util.CustomBeanUtils;

/**
 * 
 *<p>Title: CodeRepository</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-18
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
    private String username; // 用户名

    @Column
    private String password; // 密码

    @Column
    private String remark; // 备注

    @Column(nullable = false, columnDefinition = "INT default 0")
    @Enumerated(EnumType.ORDINAL)
    private CloneStatus cloneStatus;

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

    public CloneStatus getCloneStatus() {
        return cloneStatus;
    }

    public void setCloneStatus(CloneStatus cloneStatus) {
        this.cloneStatus = cloneStatus;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void init() {
        this.createDatetime = new Date();
        this.cloneStatus = CloneStatus.NOT_INIT;
    }

    public void update(CodeRepository codeRepository) {
        CustomBeanUtils.copyProperties(codeRepository, this, null, true, "createDatetime", "cloneStatus");
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof CodeRepository) {
            return this.getId().equals(((CodeRepository) obj).getId());
        }

        return false;
    }

}
