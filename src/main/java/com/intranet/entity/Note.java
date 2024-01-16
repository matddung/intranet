package com.intranet.entity;

import com.intranet.dto.Note.request.NoteSendRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String content;
    @ManyToOne
    private Member sender;
    @ManyToOne
    private Member addressee;
    @CreationTimestamp
    private LocalDateTime sendDate;
    private LocalDateTime readDate;
    private boolean isRead;

    public static Note from(NoteSendRequest request, Member addressee, Member sender) {
        return Note.builder()
                .subject(request.subject())
                .content(request.content())
                .sendDate(LocalDateTime.now())
                .addressee(addressee)
                .sender(sender)
                .isRead(false)
                .build();
    }

    @Builder
    private Note(String subject, String content, Member sender, Member addressee, LocalDateTime sendDate, LocalDateTime readDate, boolean isRead) {
        this.subject = subject;
        this.content = content;
        this.sender = sender;
        this.addressee = addressee;
        this.sendDate = sendDate;
        this.readDate = readDate;
        this.isRead = isRead;
    }
}
