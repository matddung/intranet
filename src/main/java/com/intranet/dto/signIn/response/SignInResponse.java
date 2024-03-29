package com.intranet.dto.signIn.response;

import com.intranet.common.MemberType;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignInResponse(
        @Schema(description = "회원 이름", example = "테스트")
        String name,
        @Schema(description = "회원 유형", example = "USER")
        MemberType type,
        String token,
        String refreshToken
) {
}
