package com.intranet.dto.report.response;

import com.intranet.entity.Report;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ReportFirstApproveResponse(
        @Schema(description = "1차 승인 성공 여부", example = "true")
        boolean result,
        @Schema(description = "1차 승인 시간", example = "2024-01-09T15:00:00")
        LocalDateTime firstApproveDate,
        @Schema(description = "1차 승인 여부", example = "true")
        boolean firstApprove
) {
    public static ReportFirstApproveResponse of(boolean result, Report report) {
        return new ReportFirstApproveResponse(result,
                report.getFirstApproveDate(),
                report.isFirstApprove()
        );
    }
}
