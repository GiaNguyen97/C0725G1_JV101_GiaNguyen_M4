package org.example.soccer_manager.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.soccer_manager.validation.validator.AgeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge {

    String message() default "Tuổi phải từ 16 đến 100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}