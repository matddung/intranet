package com.intranet.dto.report.response;

import com.intranet.common.ReportType;
import com.intranet.entity.Member;
import com.intranet.entity.Report;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ReportSubmitResponse(
        @Schema(description = "보고서 유형", example = "HOLIDAY")
        ReportType type,
        @Schema(description = "제목", example = "안녕하세요.")
        String subject,
        @Schema(description = "내용", example = "반갑습니다.")
        String content,
        @Schema(description = "작성 시간", example = "2024-01-09T15:00:00")
        LocalDateTime createdAt,
        @Schema(description = "작성자")
        Member submitter,
        @Schema(description = "1차 승인 시간", example = "2024-01-09T15:00:00")
        LocalDateTime firstApproveDate,
        @Schema(description = "1차 승인자")
        Member firstApprovePerson,
        @Schema(description = "1차 승인 여부", example = "true")
        boolean firstApprove,
        @Schema(description = "2차 승인 시간", example = "2024-01-09T15:00:00")
        LocalDateTime secondApproveDate,
        @Schema(description = "2차 승인자")
        Member secondApprovePerson,
        @Schema(description = "2차 승인 여부", example = "true")
        boolean secondApprove
) {
        public static ReportSubmitResponse from(Report report) {
                return new ReportSubmitResponse(
                        report.getType(),
                        report.getSubject(),
                        report.getContent(),
                        report.getCreatedAt(),
                        report.getSubmitter(),
                        report.getFirstApproveDate(),
                        report.getFirstApprovePerson(),
                        report.isFirstApprove(),
                        report.getSecondApproveDate(),
                        report.getSecondApprovePerson(),
                        report.isSecondApprove()
                );
        }
}
