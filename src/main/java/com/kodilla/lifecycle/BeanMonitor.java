package com.kodilla.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanMonitor implements BeanPostProcessor {

    private static Logger logger = LoggerFactory.getLogger(BeanMonitor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws
            BeansException {

        System.out.println("Before initialization of bean: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After initializiation of bean: " + beanName);
        logger.info("Created bean: "+ beanName);
        return bean;
    }
}