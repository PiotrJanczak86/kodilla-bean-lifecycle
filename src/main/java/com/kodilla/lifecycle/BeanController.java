package com.kodilla.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bean")
public class BeanController {

    private final ApplicationContext applicationContext;

    public BeanController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostMapping
    public void createBean() {
        applicationContext.getBean(BeanExample.class);
    }
}