package com.intranet.controller;

import com.intranet.dto.ApiResponse;
import com.intranet.dto.Note.request.NoteSendRequest;
import com.intranet.dto.Question.Request.QuestionCreateRequest;
import com.intranet.dto.member.request.MemberUpdateRequest;
import com.intranet.dto.report.request.ReportSubmitRequest;
import com.intranet.dto.schedule.request.ScheduleCreateRequest;
import com.intranet.dto.schedule.request.ScheduleUpdateRequest;
import com.intranet.security.UserAuthorize;
import com.intranet.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "회원 승인 후 사용할 수 있는 API")
@RequiredArgsConstructor
@UserAuthorize
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final QuestionService questionService;
    private final NoteService noteService;
    private final ReportService reportService;
    private final ScheduleService scheduleService;

    // member ------------------------------------------------------------------------------------------------------
    @Operation(summary = "회원 정보 조회")
    @GetMapping
    public ApiResponse getMemberInfo(@AuthenticationPrincipal User user) {
        return ApiResponse.success(memberService.getMemberInfo(UUID.fromString(user.getUsername())));
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public ApiResponse deleteMember(@AuthenticationPrincipal User user) {
        return ApiResponse.success(memberService.deleteMember(UUID.fromString(user.getUsername())));
    }

    @Operation(summary = "회원 정보 수정")
    @PatchMapping
    public ApiResponse updateMember(@AuthenticationPrincipal User user, @RequestBody MemberUpdateRequest request) {
        return ApiResponse.success(memberService.updateMember(UUID.fromString(user.getUsername()), request));
    }

    // question ------------------------------------------------------------------------------------------------------
    @Operation(summary = "익명 글쓰기")
    @PostMapping("/questionWrite")
    public ApiResponse createQuestion(@RequestBody QuestionCreateRequest request) {
        return ApiResponse.success(questionService.createFreeQuestion(request));
    }

    // note ------------------------------------------------------------------------------------------------------
    @Operation(summary = "쪽지 보내기")
    @PostMapping("sendNote")
    public ApiResponse sendNote(@AuthenticationPrincipal User user, @RequestBody NoteSendRequest request) {
        return ApiResponse.success(noteService.sendNote(UUID.fromString(user.getUsername()), request));
    }

    @Operation(summary = "보낸 쪽지함")
    @GetMapping("/getSendNotes")
    public ApiResponse getSendNotes(@AuthenticationPrincipal User user) {
        return ApiResponse.success(noteService.getSendNotes(UUID.fromString(user.getUsername())));
    }

    @Operation(summary = "받은 쪽지함")
    @GetMapping("/getReceiveNotes")
    public ApiResponse getReceiveNotes(@AuthenticationPrincipal User user) {
        return ApiResponse.success(noteService.getReceiveNotes(UUID.fromString(user.getUsername())));
    }

    @Operation(summary = "쪽지 읽기")
    @GetMapping("/note/{id}")
    public ApiResponse readNoteDetail(@RequestParam UUID id) {
        return ApiResponse.success(noteService.readNoteDetail(id));
    }

    @Operation(summary = "쪽지 삭제")
    @DeleteMapping("/deleteNote/{id}")
    public ApiResponse deleteQuestion(@PathVariable UUID id) {
        return ApiResponse.success(noteService.deleteNote(id));
    }

    // report ------------------------------------------------------------------------------------------------------
    @Operation(summary = "보고서 등록")
    @PostMapping("/submitReport")
    public ApiResponse submitReport(@AuthenticationPrincipal User user, @RequestBody ReportSubmitRequest request) {
        return ApiResponse.success(reportService.submitReport(UUID.fromString(user.getUsername()), request));
    }

    @Operation(summary = "보고서 1차 승인")
    @PostMapping("/firstApproveReport")
    public ApiResponse firstApproveReport(@AuthenticationPrincipal User user, @RequestParam UUID id) {
        return ApiResponse.success(reportService.firstApproveReport(UUID.fromString(user.getUsername()), id));
    }

    @Operation(summary = "보고서 2차 승인")
    @PostMapping("/secondApproveReport")
    public ApiResponse secondApproveReport(@AuthenticationPrincipal User user, @RequestParam UUID id) {
        return ApiResponse.success(reportService.secondApproveReport(UUID.fromString(user.getUsername()), id));
    }

    @Operation(summary = "보고서 전체 리스트")
    @GetMapping("/allReports")
    public ApiResponse getAllReports(@AuthenticationPrincipal User user) {
        return ApiResponse.success(reportService.findAllReport(UUID.fromString(user.getUsername())));
    }

    @Operation(summary = "1차 승인 보고서 리스트")
    @GetMapping("/firstReports")
    public ApiResponse getFirstApproveReports(@AuthenticationPrincipal User user) {
        return ApiResponse.success(reportService.isApproveFirstList(UUID.fromString(user.getUsername())));
    }

    @Operation(summary = "2차 승인 보고서 리스트")
    @GetMapping("/secondReports")
    public ApiResponse getSecondApproveReports(@AuthenticationPrincipal User user) {
        return ApiResponse.success(reportService.isApproveFirstAndSecondList(UUID.fromString(user.getUsername())));
    }

    // schedule ------------------------------------------------------------------------------------------------------
    @Operation(summary = "스케줄 작성")
    @PostMapping("/createSchedule")
    public ApiResponse createSchedule(@AuthenticationPrincipal User user, @RequestBody ScheduleCreateRequest request) {
        return ApiResponse.success(scheduleService.createSchedule(UUID.fromString(user.getUsername()), request));
    }

    @Operation(summary = "스케줄 수정")
    @PatchMapping("/updateSchedule")
    public ApiResponse updateSchedule(@AuthenticationPrincipal User user, @RequestBody ScheduleUpdateRequest request) {
        return  ApiResponse.success(scheduleService.updateSchedule(UUID.fromString(user.getUsername()), request));
    }

    @Operation(summary = "스케줄 상세 보기")
    @GetMapping("/getSchedule")
    public ApiResponse getSchedule(@RequestParam UUID id) {
        return ApiResponse.success(scheduleService.getScheduleInfo(id));
    }

    @Operation(summary = "스케줄 리스트 보기")
    @GetMapping("/getSchedules")
    public ApiResponse getSchedules(@AuthenticationPrincipal User user) {
        return ApiResponse.success(scheduleService.getSchedules(UUID.fromString(user.getUsername())));
    }
}