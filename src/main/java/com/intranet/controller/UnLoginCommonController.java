package com.intranet.controller;

import com.intranet.dto.ApiResponse;
import com.intranet.dto.password.request.FindPasswordRequest;
import com.intranet.dto.signIn.request.SignInRequest;
import com.intranet.dto.signUp.request.SignUpRequest;
import com.intranet.service.CommonService;
import com.intranet.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Tag(name = "회원 승인 전 사용할 수 있는 API")
@RequiredArgsConstructor
@RestController
@RequestMapping
public class UnLoginCommonController {
    private final CommonService commonService;
    private final EmailService emailService;

    @Operation(summary = "회원 가입")
    @PostMapping("/signUp")
    public ApiResponse signUp(@RequestBody SignUpRequest request) {
        return ApiResponse.success(commonService.registMember(request));
    }

    @Operation(summary = "로그인")
    @PostMapping("/signIn")
    public ApiResponse signIn(@RequestBody SignInRequest request) {
        return ApiResponse.success(commonService.signIn(request));
    }

    @Operation(summary = "임시 비밀번호 메일 보내기")
    @PostMapping("/findPassword")
    public ApiResponse findPassword(@RequestBody FindPasswordRequest request) throws MessagingException, UnsupportedEncodingException {
        return ApiResponse.success(emailService.sendMail(request));
    }
}
