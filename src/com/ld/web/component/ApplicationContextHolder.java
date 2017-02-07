package com.ld.web.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 *<p>Title: ApplicationContextHolder</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-06
 */
@Component("ApplicationContextHolder")
public class ApplicationContextHolder implements ApplicationContextAware {

    static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationContextHolder.context = context;
    }

    public static Object getSpringBean(String name) {
        return containsBean(name) ? context.getBean(name) : null;
    }

    public static boolean containsBean(String name) {
        return context.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return context.isSingleton(name);
    }

    public static String[] getAliases(String name) {
        return context.getAliases(name);
    }

}
