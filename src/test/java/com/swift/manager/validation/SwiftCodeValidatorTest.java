package com.swift.manager.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.validation.ConstraintValidatorContext;

@SuppressWarnings("unused")
class SwiftCodeValidatorTest {
    private SwiftCodeValidator validator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new SwiftCodeValidator();
    }

    @Test
    void validSwiftCode() {
        assertTrue(validator.isValid("DEUTDEFF", context));
        assertTrue(validator.isValid("BOFAUS3NXXX", context));
    }

    @Test
    void invalidSwiftCode() {
        assertFalse(validator.isValid("DEUTDEF", context));
        assertFalse(validator.isValid("DEUTDEFFXXXX", context));
        assertFalse(validator.isValid("deutdeff", context));
        assertFalse(validator.isValid(null, context));
    }
}