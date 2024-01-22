package com.intranet.dto.question.Request;

import io.swagger.v3.oas.annotations.media.Schema;

public record QuestionCreateRequest(
        @Schema(description = "제목", example = "안녕하세요.")
        String subject,
        @Schema(description = "내용", example = "반갑습니다.")
        String content
) {
}