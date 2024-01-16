package com.intranet.entity;

import com.intranet.common.ReportType;
import com.intranet.dto.report.request.ReportSubmitRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private ReportType type;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    private Member submitter;
    private String subject;
    private String content;
    private LocalDateTime firstApproveDate;
    @ManyToOne
    private Member firstApprovePerson;
    private boolean firstApprove;
    private LocalDateTime secondApproveDate;
    @ManyToOne
    private Member secondApprovePerson;
    private boolean secondApprove;

    public static Report from(ReportSubmitRequest request, Member submitter, Member firstApprovePerson, Member secondApprovePerson) {
        return Report.builder()
                .type(request.type())
                .subject(request.subject())
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .submitter(submitter)
                .firstApprovePerson(firstApprovePerson)
                .firstApprove(false)
                .secondApprovePerson(secondApprovePerson)
                .secondApprove(false)
                .build();
    }

    @Builder
    public Report(ReportType type, LocalDateTime createdAt, Member submitter, String subject, String content, LocalDateTime firstApproveDate, Member firstApprovePerson, boolean firstApprove, LocalDateTime secondApproveDate, Member secondApprovePerson, boolean secondApprove) {
        this.type = type;
        this.createdAt = createdAt;
        this.submitter = submitter;
        this.subject = subject;
        this.content = content;
        this.firstApproveDate = firstApproveDate;
        this.firstApprovePerson = firstApprovePerson;
        this.firstApprove = firstApprove;
        this.secondApproveDate = secondApproveDate;
        this.secondApprovePerson = secondApprovePerson;
        this.secondApprove = secondApprove;
    }
}
