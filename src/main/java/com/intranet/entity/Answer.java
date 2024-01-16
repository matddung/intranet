package com.intranet.entity;

import com.intranet.dto.answer.request.AnswerCreateRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    @JsonBackReference
    private Question question;

    public static Answer from(AnswerCreateRequest request, Question question) {
        return Answer.builder()
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .question(question)
                .build();
    }

    @Builder
    private Answer(String content, LocalDateTime createdAt, Question question) {
        this.content = content;
        this.createdAt = createdAt;
        this.question = question;
    }
}
