package com.lewis.cms.common.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContext implements ApplicationContextAware {

    private static  ApplicationContext applicationContext;

    public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public synchronized static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
