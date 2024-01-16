package com.intranet.service;

import com.intranet.dto.report.response.ReportSecondApproveResponse;
import com.intranet.dto.report.request.ReportSubmitRequest;
import com.intranet.dto.report.response.ReportFirstApproveResponse;
import com.intranet.dto.report.response.ReportSubmitResponse;
import com.intranet.entity.Member;
import com.intranet.entity.Report;
import com.intranet.repository.MemberRepository;
import com.intranet.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReportSubmitResponse submitReport(UUID submitterId, ReportSubmitRequest request) {
        Member submitter = memberRepository.findById(submitterId).orElseThrow(() -> new UsernameNotFoundException("찾을 수 없습니다."));
        Member firstApprovePerson = memberRepository.findByDepartmentAndPositionClass(submitter.getDepartment(), submitter.getPositionClass() + 1);

        if (firstApprovePerson == null || firstApprovePerson.getPositionClass() == 2) {
            firstApprovePerson = memberRepository.findByDepartmentAndPositionClass(submitter.getDepartment(), submitter.getPositionClass() + 2);
            if (firstApprovePerson == null) {
                firstApprovePerson = memberRepository.findByDepartmentAndPositionClass(submitter.getDepartment(), submitter.getPositionClass() + 3);
                if (firstApprovePerson == null) {
                    throw new IllegalArgumentException("First Approve Person not found for this department : " + submitter.getDepartment() + " and positionClass : " + (submitter.getPositionClass() + 3));
                }
            }
        }

        Member secondApprovePerson = memberRepository.findByDepartmentAndPositionClass(firstApprovePerson.getDepartment(), firstApprovePerson.getPositionClass() + 1);

        if (secondApprovePerson == null || secondApprovePerson.getPositionClass() == 5) {
            throw new IllegalArgumentException("Second Approve Person not found for this department : " + firstApprovePerson.getDepartment() + " and positionClass : " + (firstApprovePerson.getPositionClass() + 1));
        }

        Report report = reportRepository.save(Report.from(request, submitter, firstApprovePerson, secondApprovePerson));
        return ReportSubmitResponse.from(report);
    }

    @Transactional
    public ReportFirstApproveResponse firstApproveReport(UUID firstApprovePersonId, UUID id) {
        Member firstApprovePerson = memberRepository.findById(firstApprovePersonId).orElseThrow(() -> new UsernameNotFoundException("일치하지 않습니다."));
        return reportRepository.findById(id)
                .map(report -> {
                    if (firstApprovePerson == report.getFirstApprovePerson()) {
                        report.setFirstApproveDate(LocalDateTime.now());
                        report.setFirstApprove(true);
                        return ReportFirstApproveResponse.of(true, report);
                    } else {
                        return ReportFirstApproveResponse.of(false, report);
                    }
                })
                .orElseThrow(() -> new IllegalArgumentException("보고서를 찾을 수 없습니다."));
    }

    @Transactional
    public ReportSecondApproveResponse secondApproveReport(UUID secondApprovePersonId, UUID id) {
        Member secondApprovePerson = memberRepository.findById(secondApprovePersonId).orElseThrow(() -> new UsernameNotFoundException("일치하지 않습니다."));
        return reportRepository.findById(id)
                .map(report -> {
                    if (secondApprovePerson == report.getSecondApprovePerson()) {
                        report.setSecondApproveDate(LocalDateTime.now());
                        report.setSecondApprove(true);
                        return ReportSecondApproveResponse.of(true, report);
                    } else {
                        return ReportSecondApproveResponse.of(false, report);
                    }
                })
                .orElseThrow(() -> new IllegalArgumentException("보고서를 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Report> findAllReport(UUID id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("찾을 수 없습니다."));
        return reportRepository.findByFirstApproveFalseAndSubmitterDepartment(member.getDepartment());
    }

    @Transactional(readOnly = true)
    public List<Report> isApproveFirstList(UUID id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("찾을 수 없습니다."));
        return reportRepository.findByFirstApproveTrueAndSecondApproveFalseAndSubmitterDepartment(member.getDepartment());
    }

    @Transactional(readOnly = true)
    public List<Report> isApproveFirstAndSecondList(UUID id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("찾을 수 없습니다."));
        return reportRepository.findBySecondApproveTrueAndSubmitterDepartment(member.getDepartment());
    }
}