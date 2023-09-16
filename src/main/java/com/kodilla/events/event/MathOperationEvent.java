package com.kodilla.events.event;

import org.springframework.context.ApplicationEvent;

public class MathOperationEvent extends ApplicationEvent {
    private double a;
    private double b;

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getResult() {
        return result;
    }

    public String getOperation() {
        return operation;
    }

    private double result;
    private String operation;

    public MathOperationEvent(Object source, double a, double b, double result, String operation) {
        super(source);
        this.a = a;
        this.b = b;
        this.result = result;
        this.operation = operation;
    }
}
