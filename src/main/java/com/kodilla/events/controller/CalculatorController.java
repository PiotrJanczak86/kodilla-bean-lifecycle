package com.kodilla.events.controller;

import com.kodilla.events.domain.CalculatorInputDto;
import com.kodilla.events.event.MathOperationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calculator/")
public class CalculatorController implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @PostMapping(path = "add")
    public ResponseEntity<Object> mathAdd(@RequestBody CalculatorInputDto input) {
        publisher.publishEvent(
                new MathOperationEvent(
                        this,
                        input.getA(),
                        input.getB(),
                        input.getA() + input.getB(),
                        "addition"));
        return ResponseEntity.ok("operation type: addition" + "\nvariable a: " + input.getA() + "\nvariable b: " + input.getB() + "\nresult: " + (input.getA() + input.getB()));
    }
    @PostMapping(path = "subtract")
    public ResponseEntity<Object> mathSubtract(@RequestBody CalculatorInputDto input) {
        publisher.publishEvent(
                new MathOperationEvent(
                        this,
                        input.getA(),
                        input.getB(),
                        input.getA() - input.getB(),
                        "subtraction"));
        return ResponseEntity.ok("operation type: subtraction" + "\nvariable a: " + input.getA() + "\nvariable b: " + input.getB() + "\nresult: " + (input.getA() - input.getB()));
    }

    @PostMapping(path = "multiply")
    public ResponseEntity<Object> mathMultiply(@RequestBody CalculatorInputDto input) {
        publisher.publishEvent(
                new MathOperationEvent(
                        this,
                        input.getA(),
                        input.getB(),
                        input.getA() * input.getB(),
                        "multiplication"));
        return ResponseEntity.ok("operation type: multiplication" + "\nvariable a: " + input.getA() + "\nvariable b: " + input.getB() + "\nresult: " + (input.getA() * input.getB()));
    }
    @PostMapping(path = "divide")
    public ResponseEntity<Object> mathDivide(@RequestBody CalculatorInputDto input) {
        publisher.publishEvent(
                new MathOperationEvent(
                        this,
                        input.getA(),
                        input.getB(),
                        input.getA() / input.getB(),
                        "division"));
        return ResponseEntity.ok("operation type: division" + "\nvariable a: " + input.getA() + "\nvariable b: " + input.getB() + "\nresult: " + (input.getA() / input.getB()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}