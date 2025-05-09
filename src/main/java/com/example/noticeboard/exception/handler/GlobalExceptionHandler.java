package com.example.noticeboard.exception.handler;

import com.example.noticeboard.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice("com.example.noticeboard.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer(NullPointerException ex) {
        log.error("널 포인터 예외 발생", ex);
        ErrorResponse response = ErrorResponse.from("서버 내부 오류가 발생했습니다.", INTERNAL_SERVER_ERROR.value());

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccess(DataAccessException ex) {
        log.error("DB 오류 발생", ex);
        ErrorResponse response = ErrorResponse.from("데이터베이스 오류가 발생했습니다.", INTERNAL_SERVER_ERROR.value());

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName();
        String value = String.valueOf(ex.getValue());
        String expectedType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "Unknown";

        String message = String.format("'%s' 파라미터에 잘못된 값이 들어왔습니다. 입력값: '%s', 기대 타입: '%s'",
                name, value, expectedType);

        ErrorResponse response = ErrorResponse.from(message, BAD_REQUEST.value());

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse response = ErrorResponse.from(message, BAD_REQUEST.value());

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ErrorResponse response = ErrorResponse.from(message, BAD_REQUEST.value());

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(response);
    }
}
