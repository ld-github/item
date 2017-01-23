package com.ld.web.been.dto;

import java.io.Serializable;

/**
 * 
 *<p>Title: SSHLoginInfo</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-22
 */
public class SSHLoginInfo implements Serializable {

    private static final long serialVersionUID = 5256580818619083666L;

    private String host; // 服务器地址

    private int port; // 端口

    private String username; // 用户名

    private String password; // 密码

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SSHLoginInfo(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

}
