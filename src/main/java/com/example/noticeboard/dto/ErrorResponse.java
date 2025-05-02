package com.example.noticeboard.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;
    private final int status;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
