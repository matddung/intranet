package com.intranet.dto.answer.response;

import com.intranet.entity.Answer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record AnswerCreateResponse(
        @Schema(description = "내용", example = "반갑습니다.")
        String content,
        @Schema(description = "작성 시간", example = "2024-01-09T15:00:00")
        LocalDateTime createdAt
) {
    public static AnswerCreateResponse from(Answer answer) {
        return new AnswerCreateResponse(
                answer.getContent(),
                answer.getCreatedAt()
        );
    }
}