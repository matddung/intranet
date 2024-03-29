package com.intranet.controller;

import com.intranet.dto.ApiResponse;
import com.intranet.dto.answer.request.AnswerCreateRequest;
import com.intranet.security.LoginAuthorize;
import com.intranet.service.AnswerService;
import com.intranet.service.QuestionService;
import com.intranet.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "ADMIN, USER 모두 사용할 수 있는 API")
@RequiredArgsConstructor
@LoginAuthorize
@RestController
@RequestMapping
public class LoginCommonController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ScheduleService scheduleService;

    @Operation(summary = "자유게시판 글 목록")
    @GetMapping("/freeBoard")
    public ApiResponse getFreeQuestions() {
        return ApiResponse.success(questionService.getFreeQuestions());
    }

    @Operation(summary = "공지게시판 글 목록")
    @GetMapping("/NoticeBoard")
    public ApiResponse getNoticeQuestions() {
        return ApiResponse.success(questionService.getNoticeQuestions());
    }

    @Operation(summary = "게시글 상세 보기")
    @GetMapping("/{id}")
    public ApiResponse readFreeQuestionDetail(@RequestParam UUID id) {
        return ApiResponse.success(questionService.readQuestionDetail(id));
    }

    @Operation(summary = "댓글 달기")
    @PostMapping("/createAnswer")
    public ApiResponse createAnswer(@RequestBody AnswerCreateRequest request) {
        return ApiResponse.success(answerService.createAnswer(request));
    }

    @Operation(summary = "다른 유저의 PUBLIC으로 작성된 스케줄 보기")
    @GetMapping("/getSomeoneElseSchedules")
    public ApiResponse getSomeoneElseSchedules(@RequestParam UUID id) {
        return  ApiResponse.success(scheduleService.getSomeoneElseSchedules(id));
    }
}
