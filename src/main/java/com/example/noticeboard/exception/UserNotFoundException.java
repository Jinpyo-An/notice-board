package com.example.noticeboard.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("존재하지 않은 사용자입니다. (id: " + userId + ")");
    }
}
