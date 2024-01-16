package com.intranet.dto.member.response;

import com.intranet.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record MemberDepartmentResponse (
        @Schema(description = "회원 정보 수정 성공 여부", example = "true")
        boolean result,
        @Schema(description = "부서", example = "영업팀")
        String department,
        @Schema(description = "직위", example = "대리")
        String position
) {
    public static MemberDepartmentResponse of(boolean result, Member member) {
        return new MemberDepartmentResponse(
                result,
                member.getDepartment(),
                member.getPosition()
        );
    }
}