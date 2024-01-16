package com.intranet.dto.member.response;

import com.intranet.common.MemberType;
import com.intranet.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record MemberApproveResponse(
        @Schema(description = "회원 승인 성공 여부", example = "true")
        boolean result,
        @Schema(description = "회원 유형", example = "USER")
        MemberType type
) {
    public static MemberApproveResponse of(boolean result, Member member) {
        return new MemberApproveResponse(
                result,
                member.getType()
        );
    }
}
