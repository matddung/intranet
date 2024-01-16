package com.intranet.dto.schedule.response;

import com.intranet.entity.Member;
import com.intranet.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleCreateResponse(
        UUID id,
        @Schema(description = "스케줄 내용", example = "거래처 미팅")
        String content,
        @Schema(description = "스케줄 시작하는 시간", example = "2024-01-09T15:00:00")
        LocalDateTime startTime,
        @Schema(description = "스케줄 끝나는 시간", example = "2024-01-09T15:00:00")
        LocalDateTime endTime,
        @Schema(description = "스케줄 주소", example = "대전광역시 서구 둔산동 111-111")
        String address,
        @Schema(description = "작성 시간", example = "2024-01-09T15:00:00")
        LocalDateTime createdAt,
        @Schema(description = "스케줄 작성자(현재 로그인한 유저)")
        Member member
) {
    public static ScheduleCreateResponse from(Schedule schedule) {
        return new ScheduleCreateResponse(
                schedule.getId(),
                schedule.getContent(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getAddress(),
                schedule.getCreatedAt(),
                schedule.getMember()
        );
    }
}
