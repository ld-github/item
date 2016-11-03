package com.ld.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ld.web.been.model.Manager;

/**
 * 
 *<p>Title: BaseController</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-2
 */
@Controller
@Scope("prototype")
public class BaseController implements Serializable {

    private static final long serialVersionUID = 5249682224462255858L;

    private final String SESSION_MANAGER_KEY = "manager";

    public Object getSessionObj(String key) {
        return getSession().getAttribute(key);
    }

    public void putSessionObj(String key, Object obj) {
        getSession().setAttribute(key, obj);
    }

    public void removeSessionObj(String key) {
        getSession().removeAttribute(key);
    }

    public void putSessionManager(Manager manager) {
        getSession().setAttribute(SESSION_MANAGER_KEY, manager);
    }

    public Object getSesstionManager() {
        return getSessionObj(SESSION_MANAGER_KEY);
    }

    public void removeSessionManager() {
        removeSessionObj(SESSION_MANAGER_KEY);
    }

    /**
     * Get request
     * 
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * Get response
     * 
     * @return
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * Get session
     * 
     * @return
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

}
