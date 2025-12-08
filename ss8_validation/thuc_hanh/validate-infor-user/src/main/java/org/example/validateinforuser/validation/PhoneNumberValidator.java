package org.example.validateinforuser.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {

        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }

        String regex = "^(0|\\+84)[0-9]{9}$";

        return phone.matches(regex);
    }
}