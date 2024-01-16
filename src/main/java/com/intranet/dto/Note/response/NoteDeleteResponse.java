package com.intranet.dto.Note.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record NoteDeleteResponse(
        @Schema(description = "쪽지 삭제 성공 여부", example = "true")
        boolean result
) {
}
