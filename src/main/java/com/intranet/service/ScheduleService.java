package com.intranet.service;

import com.intranet.common.ScheduleType;
import com.intranet.dto.schedule.request.ScheduleCreateRequest;
import com.intranet.dto.schedule.request.ScheduleUpdateRequest;
import com.intranet.dto.schedule.response.ScheduleCreateResponse;
import com.intranet.dto.schedule.response.ScheduleInfoResponse;
import com.intranet.dto.schedule.response.ScheduleUpdateResponse;
import com.intranet.entity.Member;
import com.intranet.entity.Schedule;
import com.intranet.repository.ScheduleRepository;
import com.intranet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ScheduleCreateResponse createSchedule(UUID memberId, ScheduleCreateRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UsernameNotFoundException("로그인한 회원을 찾을 수 없습니다."));
        Schedule schedule = scheduleRepository.save(Schedule.from(request, member));
        return ScheduleCreateResponse.from(schedule);
    }

    @Transactional
    public ScheduleUpdateResponse updateSchedule(UUID memberId, ScheduleUpdateRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UsernameNotFoundException("로그인한 회원을 찾을 수 없습니다."));
        return scheduleRepository.findById(request.id())
                .map(schedule -> {
                    schedule.update(request, member);
                    return ScheduleUpdateResponse.of(true, schedule);
                })
                .orElseThrow(() -> new IllegalArgumentException("입력하신 정보를 다시 확인해 주세요."));
    }

    @Transactional(readOnly = true)
    public ScheduleInfoResponse getScheduleInfo(UUID id) {
        return scheduleRepository.findById(id)
                .map(ScheduleInfoResponse::from)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 스케줄입니다."));
    }

    @Transactional(readOnly = true)
    public List<ScheduleInfoResponse> getSchedules(UUID memberId) {
        return scheduleRepository.findByMemberId(memberId).stream()
                .map(ScheduleInfoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ScheduleInfoResponse> getSomeoneElseSchedules(UUID memberId) {
        return scheduleRepository.findAllByTypeAndMemberId(ScheduleType.PUBLIC, memberId).stream()
                .map(ScheduleInfoResponse::from)
                .toList();
    }
}
