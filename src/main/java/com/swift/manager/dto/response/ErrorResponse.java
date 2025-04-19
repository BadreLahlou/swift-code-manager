package com.swift.manager.dto.response;

import java.time.Instant;

public class ErrorResponse {
    private final String errorCode;
    private final String message;
    private final Instant timestamp;

    public ErrorResponse(String errorCode, String message, Instant timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getErrorCode() { return errorCode; }
    public String getMessage() { return message; }
    public Instant getTimestamp() { return timestamp; }
}