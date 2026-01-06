package org.example.soccer_manager.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.soccer_manager.validation.annotation.ValidAge;

import java.time.LocalDate;

public class AgeValidator
        implements ConstraintValidator<ValidAge, LocalDate> {

    @Override
    public boolean isValid(LocalDate dob,
                           ConstraintValidatorContext context) {

        if (dob == null) {
            buildMessage(context, "Ngày sinh không được để trống");
            return false;
        }

        LocalDate today = LocalDate.now();
        LocalDate minDob = today.minusYears(100);
        LocalDate maxDob = today.minusYears(16);

        if (dob.isAfter(maxDob)) {
            buildMessage(context, "Chưa đủ 16 tuổi");
            return false;
        }

        if (dob.isBefore(minDob)) {
            buildMessage(context, "Tuổi vượt quá 100");
            return false;
        }

        return true;
    }

    private void buildMessage(ConstraintValidatorContext context,
                              String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}