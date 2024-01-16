package com.intranet.dto.schedule.response;

import com.intranet.common.ScheduleType;
import com.intranet.entity.Schedule;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleInfoResponse(
        UUID id,
        LocalDateTime createdAt,
        String content,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LocalDateTime modifiedAt,
        String address,
        ScheduleType type
) {
    public static ScheduleInfoResponse from(Schedule schedule) {
        return new ScheduleInfoResponse(
                schedule.getId(),
                schedule.getCreatedAt(),
                schedule.getContent(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getModifiedAt(),
                schedule.getAddress(),
                schedule.getType()
        );
    }
}
