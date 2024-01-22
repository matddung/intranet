package com.intranet.service;

import com.intranet.common.QuestionType;
import com.intranet.dto.question.Request.QuestionCreateRequest;
import com.intranet.dto.question.Response.QuestionCreateResponse;
import com.intranet.dto.question.Response.QuestionDeleteResponse;
import com.intranet.dto.question.Response.QuestionInfoResponse;
import com.intranet.entity.Question;
import com.intranet.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional
    public QuestionCreateResponse createFreeQuestion(QuestionCreateRequest request) {
        Question question = questionRepository.save(Question.from(request));
        return QuestionCreateResponse.from(question);
    }

    @Transactional
    public QuestionCreateResponse createNoticeQuestion(QuestionCreateRequest request) {
        Question question = questionRepository.save(Question.fromNotice(request));
        return QuestionCreateResponse.from(question);
    }

    @Transactional(readOnly = true)
    public List<QuestionInfoResponse> getFreeQuestions() {
        return questionRepository.findByType(QuestionType.FREE).stream()
                .map(QuestionInfoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<QuestionInfoResponse> getNoticeQuestions() {
        return questionRepository.findByType(QuestionType.NOTICE).stream()
                .map(QuestionInfoResponse::from)
                .toList();
    }

    @Transactional
    public QuestionDeleteResponse deleteQuestion(UUID id) {
        if(!questionRepository.existsById(id)) return new QuestionDeleteResponse(false);
        questionRepository.deleteById(id);
        return new QuestionDeleteResponse(true);
    }

    @Transactional(readOnly = true)
    public QuestionInfoResponse readQuestionDetail(UUID id) {
        return questionRepository.findById(id)
                .map(QuestionInfoResponse::from)
                .orElseThrow(() -> new NoSuchElementException("게시물을 찾을 수 없습니다."));
    }
}
