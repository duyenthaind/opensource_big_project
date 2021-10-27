package com.group7.fruitswebsite.common;

import com.group7.fruitswebsite.Start;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author duyenthai
 */
public class ApplicationContextProvider {

    private ApplicationContextProvider(){
    }

    public static ApplicationContext getApplicationContext(){
        return Start.context;
    }
}
