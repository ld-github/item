package com.ld.web.been.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 *<p>Title: Attachment</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 文件上传附件</p>
 *
 *@author LD
 *
 *@date 2015-11-02
 */
@Entity
@Table(name = "t_attachment")
public class Attachment extends BaseModel {

    private static final long serialVersionUID = 8945470180148394720L;

    private String filename; // 文件名

    private String realname; // 真实文件名

    private String filepath; // 文件路径

    private String dir; // 文件夹路径

    private String title; // 标题

    private String alt; // 属性

    private String remark; // 备注

    private Date createDatetime = new Date(); // 上传时间

    @Column(length = 255, nullable = false)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Column(length = 255, nullable = false)
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @JsonIgnore
    @Column(length = 255, nullable = false)
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @JsonIgnore
    @Column(length = 255, nullable = false)
    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Column
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Attachment() {
    }

    public Attachment(String filename, String realname, String filepath, String dir) {
        this.filename = filename;
        this.realname = realname;
        this.filepath = filepath;
        this.dir = dir;
    }

}
