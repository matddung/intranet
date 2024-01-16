package com.intranet.controller;

import com.intranet.dto.ApiResponse;
import com.intranet.dto.Question.Request.QuestionCreateRequest;
import com.intranet.dto.member.request.MemberDepartmentRequest;
import com.intranet.security.AdminAuthorize;
import com.intranet.service.AdminService;
import com.intranet.service.AnswerService;
import com.intranet.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "관리자용 API")
@RequiredArgsConstructor
@AdminAuthorize
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Operation(summary = "승인된 회원 목록 조회")
    @GetMapping("/members")
    public ApiResponse getApproveMembers() {
        return ApiResponse.success(adminService.getApproveMembers());
    }

    @Operation(summary = "미승인된 회원 목록 조회")
    @GetMapping("/waitingMembers")
    public ApiResponse getWaitingMembers() {
        return ApiResponse.success(adminService.getWaitingMembers());
    }

    @Operation(summary = "관리자 목록 조회")
    @GetMapping("/admins")
    public ApiResponse getAllAdmins() {
        return ApiResponse.success(adminService.getAdmins());
    }

    @Operation(summary = "회원 부서 및 직위 변경")
    @PutMapping("/setDepartment")
    public ApiResponse setDepartmentMember(@RequestParam UUID id, @RequestBody MemberDepartmentRequest request) {
        return ApiResponse.success(adminService.setDepartmentMember(id, request));
    }

    @Operation(summary = "회원 대기 상태 승인")
    @PutMapping("/approveMember")
    public ApiResponse approveMember(@RequestParam UUID id) {
        return ApiResponse.success(adminService.approveMember(id));
    }

    @Operation(summary = "공지 글쓰기")
    @PostMapping("/questionWrite")
    public ApiResponse createQuestion(@RequestBody QuestionCreateRequest request) {
        return ApiResponse.success(questionService.createNoticeQuestion(request));
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/deleteQuestion")
    public ApiResponse deleteQuestion(@RequestParam UUID id) {
        return ApiResponse.success(questionService.deleteQuestion(id));
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/deleteAnswer")
    public ApiResponse deleteAnswer(@RequestParam UUID id) {
        return ApiResponse.success(answerService.deleteAnswer(id));
    }
}