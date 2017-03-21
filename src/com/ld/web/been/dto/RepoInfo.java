package com.ld.web.been.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *<p>Title: RepoInfo</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-21
 */
public class RepoInfo implements Serializable {

    private static final long serialVersionUID = 2044695236546223655L;

    private GitBranch currentBranch; // 当前分支

    private List<GitBranch> localBranchs; // 本地分支

    private List<GitBranch> remoteBranchs; // 远端分支

    public GitBranch getCurrentBranch() {
        return currentBranch;
    }

    public List<GitBranch> getLocalBranchs() {
        return localBranchs;
    }

    public List<GitBranch> getRemoteBranchs() {
        return remoteBranchs;
    }

    public void setCurrentBranch(GitBranch currentBranch) {
        this.currentBranch = currentBranch;
    }

    public void setLocalBranchs(List<GitBranch> localBranchs) {
        this.localBranchs = localBranchs;
    }

    public void setRemoteBranchs(List<GitBranch> remoteBranchs) {
        this.remoteBranchs = remoteBranchs;
    }

    public RepoInfo(GitBranch currentBranch, List<GitBranch> localBranchs, List<GitBranch> remoteBranchs) {
        this.currentBranch = currentBranch;
        this.localBranchs = localBranchs;
        this.remoteBranchs = remoteBranchs;
    }

}
