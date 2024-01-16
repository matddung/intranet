package com.intranet.controller;

import com.intranet.dto.ApiResponse;
import com.intranet.dto.sign_up.request.SignUpRequest;
import com.intranet.dto.sign_in.request.SignInRequest;
import com.intranet.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 승인 전 사용할 수 있는 API")
@RequiredArgsConstructor
@RestController
@RequestMapping
public class UnLoginCommonController {
    private final CommonService signService;


    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ApiResponse signUp(@RequestBody SignUpRequest request) {
        return ApiResponse.success(signService.registMember(request));
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ApiResponse signIn(@RequestBody SignInRequest request) {
        return ApiResponse.success(signService.signIn(request));
    }
}
