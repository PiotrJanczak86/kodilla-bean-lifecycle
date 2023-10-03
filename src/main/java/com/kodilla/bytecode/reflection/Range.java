package com.kodilla.bytecode.reflection;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangeValidator.class)
public @interface Range {
    String message() default "Value must be >5 and <50";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min() default 5;
    int max() default 50;
}