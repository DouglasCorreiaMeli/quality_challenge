package com.meli.homeAppraisal.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HomeNameValidator implements ConstraintValidator<HomeNameConstraint, String> {
    @Override
    public void initialize(HomeNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String charToCheck = s.substring(0,1).toUpperCase();
        return s.substring(0,1).equals(charToCheck);
    }
}
