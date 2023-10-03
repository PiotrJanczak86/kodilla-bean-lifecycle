package com.kodilla.bytecode.reflection;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeValidator  implements ConstraintValidator<Range, Integer> {

    private int annotationMinValue;
    private int annotationMaxValue;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context){
        return value > annotationMinValue && value < annotationMaxValue;
    }

    public void initialize(Range constraintAnnotation) {
        annotationMinValue = constraintAnnotation.min();
        annotationMaxValue = constraintAnnotation.max();
    }
}