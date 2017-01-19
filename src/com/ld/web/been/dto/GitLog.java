package com.ld.web.been.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ld.web.util.DateUtil;

/**
 * 
 *<p>Title: GitLog</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-19
 */
public class GitLog implements Serializable {

    private static final long serialVersionUID = 9093166579884749321L;

    private String shortMessage;

    private String authorIdent;

    private String fullMessage;

    private String email;

    private Date commitDate;

    public String getAuthorIdent() {
        return authorIdent;
    }

    public String getFullMessage() {
        return fullMessage;
    }

    public void setAuthorIdent(String authorIdent) {
        this.authorIdent = authorIdent;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    @JsonFormat(pattern = DateUtil.DATETIME)
    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GitLog(String shortMessage, String authorIdent, String fullMessage, Date commitDate, String email) {
        this.shortMessage = shortMessage;
        this.authorIdent = authorIdent;
        this.fullMessage = fullMessage;
        this.commitDate = commitDate;
        this.email = email;
    }

}
