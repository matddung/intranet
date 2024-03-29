package com.intranet.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberUpdateRequest(
        @Schema(description = "회원 비밀번호", example = "1234")
        String password,
        @Schema(description = "회원 새 비밀번호", example = "12345")
        String newPassword,
        @Schema(description = "회원 이름", example = "윤준혁")
        String name,
        @Schema(description = "생년월일", example = "1996-06-04")
        String birth,
        @Schema(description = "주소(사는 곳)", example = "대전광역시 서구 관저동 1664 301호")
        String address,
        @Schema(description = "이메일", example = "email@email.com")
        String email,
        @Schema(description = "핸드폰 번호", example = "010-0101-0101")
        String phoneNumber
) {
}
