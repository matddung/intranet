package com.intranet.service;

import com.intranet.dto.schedule.request.ScheduleCreateRequest;
import com.intranet.dto.schedule.response.ScheduleCreateResponse;
import com.intranet.entity.Member;
import com.intranet.entity.Schedule;
import com.intranet.repository.ScheduleRepository;
import com.intranet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
