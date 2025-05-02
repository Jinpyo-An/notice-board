package com.example.noticeboard.exception;

public class PostNotFoundException extends RuntimeException {
  public PostNotFoundException(Long postId) {
    super("해당 게시물을 찾을 수 없습니다. (id: " + postId + ")");
  }
}
