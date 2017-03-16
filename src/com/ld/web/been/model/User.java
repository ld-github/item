package com.ld.web.been.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ld.web.util.EncryptionUtil;

/**
 * 
 * <p>Title: User</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
@Entity
@Table(name = "t_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User extends BaseModel {

    private static final long serialVersionUID = 4284572555216108008L;

    @Column(length = 32, nullable = false, unique = true)
    private String username; // 登录名

    @JsonIgnore
    @Column(nullable = false)
    private String password; // 密码

    @Column
    private String name;

    @Column(length = 20)
    private String mobile;

    @Column(length = 50)
    private String email;

    @Column
    private String remark;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDatetime; // 创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false, columnDefinition = "varchar(1) default 'N' ")
    @Type(type = "yes_no")
    private boolean available; // 是否可用

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getRemark() {
        return remark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User(String username, String password, String name, String mobile, String email, String remark) {
        this.username = username;
        this.password = EncryptionUtil.sha256(password);
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.remark = remark;

        this.init();
    }

    public void init() {
        this.createDatetime = new Date();
        this.available = true;
    }

    public User() {
    }

}
