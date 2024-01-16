package com.intranet.repository;

import com.intranet.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
    List<Report> findByFirstApproveFalseAndSubmitterDepartment(String department);
    List<Report> findByFirstApproveTrueAndSecondApproveFalseAndSubmitterDepartment(String department);
    List<Report> findBySecondApproveTrueAndSubmitterDepartment(String department);
}