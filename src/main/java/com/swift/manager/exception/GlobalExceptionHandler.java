package com.swift.manager.exception;

import com.swift.manager.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SwiftCodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSwiftCodeNotFound(SwiftCodeNotFoundException ex) {
        ErrorResponse response = new ErrorResponse("SWIFT_CODE_NOT_FOUND", ex.getMessage(), Instant.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .findFirst()
            .orElse("Validation failed");
        ErrorResponse response = new ErrorResponse("INVALID_INPUT", message, Instant.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolations(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations()
            .stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .findFirst()
            .orElse("Validation failed");
        ErrorResponse response = new ErrorResponse("INVALID_INPUT", message, Instant.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}