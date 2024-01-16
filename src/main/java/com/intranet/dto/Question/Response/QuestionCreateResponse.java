package com.intranet.dto.Question.Response;

import com.intranet.common.QuestionType;
import com.intranet.entity.Question;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record QuestionCreateResponse(
        @Schema(description = "제목", example = "안녕하세요.")
        String subject,
        @Schema(description = "내용", example = "반갑습니다.")
        String content,
        @Schema(description = "게시글 타입", example = "FREE")
        QuestionType type,
        @Schema(description = "작성 시간", example = "2024-01-09T15:00:00")
        LocalDateTime createdAt
) {
    public static QuestionCreateResponse from(Question question) {
        return new QuestionCreateResponse(
                question.getSubject(),
                question.getContent(),
                question.getType(),
                question.getCreatedAt()
        );
    }
}