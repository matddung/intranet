package com.intranet.dto.signUp.response;

import com.intranet.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record SignUpResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        UUID id,
        @Schema(description = "회원 아이디", example = "test7898")
        String account,
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
    public static SignUpResponse from(Member member) {
        return new SignUpResponse(
                member.getId(),
                member.getAccount(),
                member.getName(),
                member.getBirth(),
                member.getAddress(),
                member.getEmail(),
                member.getPhoneNumber()
        );
    }
}
