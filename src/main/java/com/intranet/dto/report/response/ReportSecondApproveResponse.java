package com.intranet.dto.report.response;

import com.intranet.entity.Report;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ReportSecondApproveResponse(
        @Schema(description = "2차 승인 성공 여부", example = "true")
        boolean result,
        @Schema(description = "2차 승인 시간", example = "2024-01-09T15:00:00")
        LocalDateTime secondApproveDate,
        @Schema(description = "2차 승인 여부", example = "true")
        boolean secondApprove
) {
    public static ReportSecondApproveResponse of(boolean result, Report report) {
        return new ReportSecondApproveResponse(result, report.getSecondApproveDate(), report.isSecondApprove());
    }
}
