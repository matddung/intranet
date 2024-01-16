package com.intranet.entity;

import com.intranet.dto.schedule.request.ScheduleCreateRequest;
import com.intranet.dto.schedule.request.ScheduleUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    private Member member;
    private String content;
    private String address;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static Schedule from(ScheduleCreateRequest request, Member member) {
        return Schedule.builder()
                .startTime(request.startTime())
                .endTime(request.endTime())
                .member(member)
                .content(request.content())
                .address(request.address())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Builder
    private Schedule(LocalDateTime startTime, LocalDateTime endTime, Member member, String content, String address, LocalDateTime createdAt) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.member = member;
        this.content = content;
        this.address = address;
        this.createdAt = createdAt;
    }

    public void update(ScheduleUpdateRequest request, Member member) {
        this.member = member;
        this.content = request.content();
        this.address = request.address();
        this.startTime = request.startTime();
        this.endTime = request.endTime();
        this.modifiedAt = LocalDateTime.now();
    }
}
