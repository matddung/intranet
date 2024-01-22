package com.intranet.entity;

import com.intranet.common.QuestionType;
import com.intranet.dto.question.Request.QuestionCreateRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String subject;
    private String content;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Answer> answerList;

    public static Question from(QuestionCreateRequest request) {
        return Question.builder()
                .subject(request.subject())
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .type(QuestionType.FREE)
                .build();
    }

    public static Question fromNotice(QuestionCreateRequest request) {
        return  Question.builder()
                .subject(request.subject())
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .type(QuestionType.NOTICE)
                .build();
    }

    @Builder
    private Question(String subject, String content, LocalDateTime createdAt, QuestionType type) {
        this.subject = subject;
        this.content = content;
        this.createdAt = createdAt;
        this.type = type;
    }
}