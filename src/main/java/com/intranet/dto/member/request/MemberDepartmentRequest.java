package com.intranet.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberDepartmentRequest(
        @Schema(description = "부서", example = "영업팀")
        String department,
        @Schema(description = "직위", example = "대리")
        String position
) {
}