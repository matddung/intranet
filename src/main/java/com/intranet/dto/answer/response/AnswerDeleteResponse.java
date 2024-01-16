package com.intranet.dto.answer.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record AnswerDeleteResponse(
        @Schema(description = "게시글 삭제 성공 여부", example = "true")
        boolean result
) {
}
