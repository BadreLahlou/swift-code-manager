package com.swift.manager.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SwiftCodeValidator implements ConstraintValidator<ValidSwiftCode, String> {
    private static final Pattern SWIFT_PATTERN = Pattern.compile("^[A-Z]{4}[A-Z]{2}[A-Z0-9]{2}([A-Z0-9]{3})?$", 0);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && SWIFT_PATTERN.matcher(value).matches();
    }
}