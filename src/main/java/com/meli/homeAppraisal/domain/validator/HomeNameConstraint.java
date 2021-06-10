package com.meli.homeAppraisal.domain.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HomeNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HomeNameConstraint {
    String message() default "o texto deve começar com uma letra maiúscula.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
