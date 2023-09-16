package com.kodilla.events.service;

import com.kodilla.events.event.MathOperationEvent;
import com.kodilla.lifecycle.BeanMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MathOperationListener implements ApplicationListener<MathOperationEvent> {

    private static Logger logger = LoggerFactory.getLogger(MathOperationListener.class);

    @Override
    public void onApplicationEvent(MathOperationEvent event) {
        logger.info("New math operation was made:\nType: " + event.getOperation() + ", variable A: " + event.getA() + ", variable B: " + event.getB() + ", result: " + event.getResult());
    }
}