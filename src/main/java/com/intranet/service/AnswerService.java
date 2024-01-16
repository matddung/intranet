package com.intranet.service;

import com.intranet.dto.answer.request.AnswerCreateRequest;
import com.intranet.dto.answer.response.AnswerCreateResponse;
import com.intranet.dto.answer.response.AnswerDeleteResponse;
import com.intranet.entity.Answer;
import com.intranet.entity.Question;
import com.intranet.repository.AnswerRepository;
import com.intranet.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public AnswerCreateResponse createAnswer(AnswerCreateRequest request) {
        Question question = questionRepository.findById(request.questionId())
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
        Answer answer = answerRepository.save(Answer.from(request, question));
        return AnswerCreateResponse.from(answer);
    }

    @Transactional
    public AnswerDeleteResponse deleteAnswer(UUID id) {
        if(!answerRepository.existsById(id)) return new AnswerDeleteResponse(false);
        answerRepository.deleteById(id);
        return new AnswerDeleteResponse(true);
    }
}