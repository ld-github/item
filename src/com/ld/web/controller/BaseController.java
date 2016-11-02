package com.ld.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ld.web.model.Customer;

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

    private final String SESSION_CUSTOMER_KEY = "customer";

    public Object getSessionObj(String key) {
        return getSession().getAttribute(key);
    }

    public void putSessionObj(String key, Object obj) {
        getSession().setAttribute(key, obj);
    }

    public void removeSessionObj(String key) {
        getSession().removeAttribute(key);
    }

    public void putSessionCustomer(Customer customer) {
        getSession().setAttribute(SESSION_CUSTOMER_KEY, customer);
    }

    public Object getSesstionCustomer() {
        return getSessionObj(SESSION_CUSTOMER_KEY);
    }

    public void removeSessionCustomer() {
        removeSessionObj(SESSION_CUSTOMER_KEY);
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
