package com.ld.web.been.dto;

import java.io.Serializable;

/**
 * 
 *<p>Title: GitBranch</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-18
 */
public class GitBranch implements Serializable {

    private static final long serialVersionUID = -7709173927769368990L;

    private String name; // 分支名称

    private String fullName; // 分支全名

    private String trackRemoteName; // 跟踪远端分支名称

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

    public String getTrackRemoteName() {
        return trackRemoteName;
    }

    public void setTrackRemoteName(String trackRemoteName) {
        this.trackRemoteName = trackRemoteName;
    }

    public GitBranch(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }

    public GitBranch(String name, String fullName, String trackRemoteName) {
        this.name = name;
        this.fullName = fullName;
        this.trackRemoteName = trackRemoteName;
    }

}
