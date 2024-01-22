package com.intranet.dto.question.Response;

import com.intranet.common.QuestionType;
import com.intranet.entity.Answer;
import com.intranet.entity.Question;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record QuestionInfoResponse(
        @Schema(description = "게시글 고유키", example = "c0a80121-7aeb-4b4b-8b0a-6b1c032f0e4a")
        UUID id,
        @Schema(description = "제목", example = "안녕하세요.")
        String subject,
        @Schema(description = "내용", example = "반갑습니다.")
        String content,
        @Schema(description = "작성 시간", example = "2024-01-09T15:00:00")
        LocalDateTime createdAt,
        @Schema(description = "게시글 타입", example = "FREE")
        QuestionType type,
        List<Answer> answerList
) {
    public static QuestionInfoResponse from(Question question) {
        return new QuestionInfoResponse(
                question.getId(),
                question.getSubject(),
                question.getContent(),
                question.getCreatedAt(),
                question.getType(),
                question.getAnswerList()
        );
    }
}
