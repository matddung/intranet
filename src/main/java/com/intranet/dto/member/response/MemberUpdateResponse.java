package com.intranet.dto.member.response;

import com.intranet.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record MemberUpdateResponse(
        @Schema(description = "회원 정보 수정 성공 여부", example = "true")
        boolean result,
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
    public static MemberUpdateResponse of(boolean result, Member member) {
        return new MemberUpdateResponse(result, member.getName(), member.getBirth(), member.getAddress(), member.getEmail(), member.getPhoneNumber());
    }
}
