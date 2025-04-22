package com.swift.manager.exception;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.swift.manager.dto.response.ErrorResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;

class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleSwiftCodeNotFound() {
        SwiftCodeNotFoundException ex = new SwiftCodeNotFoundException("Not found");
        ResponseEntity<ErrorResponse> response = handler.handleSwiftCodeNotFound(ex);
        ErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("SWIFT_CODE_NOT_FOUND", body.getErrorCode());
        assertEquals("Not found", body.getMessage());
        assertNotNull(body.getTimestamp());
    }

    @Test
    void handleValidationErrors() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        var bindingResult = mock(org.springframework.validation.BindingResult.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);
        var fieldError = new org.springframework.validation.FieldError("obj", "field", "must not be null");
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));
        ResponseEntity<ErrorResponse> response = handler.handleValidationErrors(ex);
        ErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("INVALID_INPUT", body.getErrorCode());
        assertTrue(body.getMessage().contains("field: must not be null"));
        assertNotNull(body.getTimestamp());
    }

    @Test
    void handleConstraintViolations() {
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn("field");
        when(violation.getPropertyPath()).thenReturn(path);
        when(violation.getMessage()).thenReturn("must not be blank");
        Set<ConstraintViolation<?>> violations = Collections.singleton(violation);
        ConstraintViolationException ex = new ConstraintViolationException(violations);
        ResponseEntity<ErrorResponse> response = handler.handleConstraintViolations(ex);
        ErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("INVALID_INPUT", body.getErrorCode());
        assertTrue(body.getMessage().contains("field: must not be blank"));
        assertNotNull(body.getTimestamp());
    }
}
