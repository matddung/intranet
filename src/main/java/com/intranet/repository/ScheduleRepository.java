package com.intranet.repository;

import com.intranet.common.ScheduleType;
import com.intranet.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findByMemberId(UUID memberId);
    List<Schedule> findAllByTypeAndMemberId(ScheduleType scheduleType, UUID memberId);
}
