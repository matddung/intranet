package com.intranet.dto.note.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record NoteSendRequest(
        @Schema(description = "제목", example = "안녕하세요.")
        String subject,
        @Schema(description = "내용", example = "반갑습니다.")
        String content,
        @Schema(description = "받는 사람 account", example = "test2")
        String addresseeAccount
) {
}
