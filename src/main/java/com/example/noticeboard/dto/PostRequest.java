package com.example.noticeboard.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostRequest {

    @NotNull(message = "작성자 ID는 필수입니다")
    @Min(value = 1, message = "작성자 ID는 1 이상이어야 합니다")
    private Long authorId;

    @NotBlank(message = "제목은 필수입니다")
    @Size(max = 200, message = "제목은 200자 이하로 입력하세요")
    private String title;

    @NotBlank(message = "내용은 필수입니다")
    private String content;
}
