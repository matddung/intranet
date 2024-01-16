package com.intranet.dto.schedule.response;

import com.intranet.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ScheduleUpdateResponse(
        @Schema(description = "스케줄 수정 성공 여부", example = "true")
        boolean result,
        @Schema(description = "수정 일시", example = "2024-01-09T15:00:00")
        LocalDateTime modifiedAt,
        @Schema(description = "스케줄 내용", example = "거래처 미팅")
        String content,
        @Schema(description = "스케줄 주소", example = "대전광역시 서구 둔산동 111-111")
        String address,
        @Schema(description = "스케줄 시작하는 시간", example = "2024-01-09T15:00:00")
        LocalDateTime startTime,
        @Schema(description = "스케줄 끝나는 시간", example = "2024-01-09T15:00:00")
        LocalDateTime endTime
) {
    public static ScheduleUpdateResponse of(boolean result, Schedule schedule) {
        return new ScheduleUpdateResponse(result, schedule.getModifiedAt(), schedule.getContent(), schedule.getAddress(), schedule.getStartTime(), schedule.getEndTime());
    }
}
