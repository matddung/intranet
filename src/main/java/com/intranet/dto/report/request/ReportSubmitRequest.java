package com.intranet.dto.report.request;

import com.intranet.common.ReportType;
import io.swagger.v3.oas.annotations.media.Schema;

public record ReportSubmitRequest(
        @Schema(description = "보고서 유형", example = "HOLIDAY")
        ReportType type,
        @Schema(description = "제목", example = "안녕하세요.")
        String subject,
        @Schema(description = "내용", example = "반갑습니다.")
        String content
) {
}