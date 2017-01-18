package com.ld.web.been.dto;

import java.io.Serializable;

/**
 * 
 *<p>Title: Branch</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-18
 */
public class Branch implements Serializable {

    private static final long serialVersionUID = -7709173927769368990L;

    private String name; // 分支名称

    private String fullName; // 分支全名

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Branch(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }

}
