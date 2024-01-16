package com.intranet.dto.Note.response;

import com.intranet.entity.Member;
import com.intranet.entity.Note;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record NoteInfoResponse(
        @Schema(description = "쪽지 고유키", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        UUID id,
        @Schema(description = "제목", example = "안녕하세요.")
        String subject,
        @Schema(description = "내용", example = "반갑습니다.")
        String content,
        @Schema(description = "보낸 사람(로그인된 사람)")
        Member sender,
        @Schema(description = "보낸 시간", example = "2024-01-09T15:00:00")
        LocalDateTime sendDate,
        @Schema(description = "받는 사람")
        Member addressee,
        @Schema(description = "읽은 시간", example = "2024-01-09T15:00:00")
        LocalDateTime readDate,
        @Schema(description = "읽음 여부", example = "true")
        boolean isRead
) {
    public static NoteInfoResponse from(Note note) {
        return new NoteInfoResponse(
                note.getId(),
                note.getSubject(),
                note.getContent(),
                note.getSender(),
                note.getSendDate(),
                note.getAddressee(),
                note.getReadDate(),
                note.isRead()
        );
    }

    public static NoteInfoResponse read(Note note) {
        return new NoteInfoResponse(
                note.getId(),
                note.getSubject(),
                note.getContent(),
                note.getSender(),
                note.getSendDate(),
                note.getAddressee(),
                note.getReadDate(),
                note.isRead()
        );
    }
}
