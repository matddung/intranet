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

    private Member findApprover(Member submitter, int increment) {
        return memberRepository.findByDepartmentAndPositionClass(submitter.getDepartment(), submitter.getPositionClass() + increment);
    }

    @Transactional
    public ReportSubmitResponse submitReport(UUID submitterId, ReportSubmitRequest request) {
        Member submitter = memberRepository.findById(submitterId).orElseThrow(() -> new UsernameNotFoundException("찾을 수 없습니다."));
        Member firstApprovePerson = null;
        Member secondApprovePerson = null;

        if (submitter.getPositionClass() == 1) { // 사원이 제출한 경우
            firstApprovePerson = findApprover(submitter, 2); // 과장 찾기
            secondApprovePerson = findApprover(submitter, 3); // 부장 or 팀장 찾기
        } else if (submitter.getPositionClass() == 2) { // 대리가 제출한 경우
            firstApprovePerson = findApprover(submitter, 1); // 과장 찾기
            secondApprovePerson = findApprover(submitter, 2); // 부장 or 팀장 찾기
        } else if (submitter.getPositionClass() == 3) { // 과장이 제출한 경우
            firstApprovePerson = findApprover(submitter, 1); // 부장 or 팀장 찾기
            secondApprovePerson = firstApprovePerson; // first와 second 모두 같은 사람이 결제
        } else if (submitter.getPositionClass() == 4) { // 팀장이 제출한 경우
            firstApprovePerson = findApprover(submitter, 1); // 대표 이사 찾기
            secondApprovePerson = firstApprovePerson; // first와 second 모두 같은 사람이 결제
        }

        // 과장이 없거나 부장, 팀장이 없을 경우 한쪽이 first와 second를 모두 결제
        if (firstApprovePerson == null && secondApprovePerson != null) {
            firstApprovePerson = secondApprovePerson;
        } else if (secondApprovePerson == null && firstApprovePerson != null) {
            secondApprovePerson = firstApprovePerson;
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