package com.daviddela.peluapi.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     *   at least 8 characters
     *           at least one digit
     *           at least one special character (!,#,$,%,&,@)
     *           at least one uppercase letter
     * @param password
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password != null && !password.isBlank() && password.length() > 8 &&
                password.matches("[A-Z]") && password.matches("[1-9]") &&
                password.matches("[!#$%&@]");
    }
}
