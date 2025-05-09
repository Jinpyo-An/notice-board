package com.example.noticeboard.exception.handler;

import com.example.noticeboard.controller.PostController;
import com.example.noticeboard.dto.ErrorResponse;
import com.example.noticeboard.exception.PostNotFoundException;
import com.example.noticeboard.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = PostController.class)
public class PostExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlePostNotFound(PostNotFoundException ex) {
        ErrorResponse response = ErrorResponse.from(ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse response = ErrorResponse.from(ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
