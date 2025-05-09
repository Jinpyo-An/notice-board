package com.example.noticeboard.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        int status,
        LocalDateTime timestamp
) {
    public static ErrorResponse from(String message, int status) {
        return new ErrorResponse(message, status, LocalDateTime.now());
    }
}
