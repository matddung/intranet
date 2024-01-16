package com.intranet.dto.answer.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record AnswerCreateRequest(
        @Schema(description = "내용", example = "반갑습니다.")
        String content,
        @Schema(description = "게시글 UUID", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        UUID questionId
) {
}