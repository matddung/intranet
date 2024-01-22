package com.intranet.dto.note.response;

import com.intranet.entity.Note;
import com.intranet.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record NoteSendResponse(
        @Schema(description = "쪽지 고유키", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        UUID id,
        @Schema(description = "제목", example = "안녕하세요.")
        String subject,
        @Schema(description = "내용", example = "반갑습니다.")
        String content,
        @Schema(description = "보낸 사람(로그인된 사람)")
        Member sender,
        @Schema(description = "받는 사람")
        Member addressee
) {
    public static NoteSendResponse from(Note note) {
        return new NoteSendResponse(
                note.getId(),
                note.getSubject(),
                note.getContent(),
                note.getSender(),
                note.getAddressee()
        );
    }
}
