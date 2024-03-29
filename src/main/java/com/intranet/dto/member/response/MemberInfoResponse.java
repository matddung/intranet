package com.intranet.dto.member.response;

import com.intranet.common.MemberType;
import com.intranet.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

public record MemberInfoResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b0a-6b1c032f0e4a")
        UUID id,
        @Schema(description = "회원 아이디", example = "test444")
        String account,
        @Schema(description = "회원 이름", example = "윤준혁")
        String name,
        @Schema(description = "생년월일", example = "1996-06-04")
        String birth,
        @Schema(description = "회원 타입", example = "USER")
        MemberType type,
        @Schema(description = "주소(사는 곳)", example = "대전광역시 서구 관저동 1664 301호")
        String address,
        @Schema(description = "이메일", example = "email@email.com")
        String email,
        @Schema(description = "핸드폰 번호", example = "010-0101-0101")
        String phoneNumber,
        @Schema(description = "부서", example = "영업팀")
        String department,
        @Schema(description = "직위", example = "대리")
        String position,
        @Schema(description = "직위 번호", example = "2")
        int positionClass,
        @Schema(description = "회원 생성일", example = "2023-05-11")
        LocalDate createdAt
) {
    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(
                member.getId(),
                member.getAccount(),
                member.getName(),
                member.getBirth(),
                member.getType(),
                member.getAddress(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getDepartment(),
                member.getPosition(),
                member.getPositionClass(),
                member.getCreatedAt()
        );
    }
}