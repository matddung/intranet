package com.intranet.service;

import com.intranet.common.MemberType;
import com.intranet.dto.member.request.MemberDepartmentRequest;
import com.intranet.dto.member.response.MemberApproveResponse;
import com.intranet.dto.member.response.MemberDepartmentResponse;
import com.intranet.dto.member.response.MemberInfoResponse;
import com.intranet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> getMembers() {
        return memberRepository.findByTypeOrType(MemberType.USER, MemberType.WAITING).stream()
                .map(MemberInfoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> getWaitingMembers() {
        return memberRepository.findAllByType(MemberType.WAITING).stream()
                .map(MemberInfoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> getAdmins() {
        return memberRepository.findAllByType(MemberType.ADMIN).stream()
                .map(MemberInfoResponse::from)
                .toList();
    }

    @Transactional
    public MemberDepartmentResponse setDepartmentMember(UUID id, MemberDepartmentRequest request) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setDepartment(request);
                    String position = request.position();

                    switch (position) {
                        case "사원":
                            member.setPositionClass(1);
                            break;
                        case "대리":
                            member.setPositionClass(2);
                            break;
                        case "과장":
                            member.setPositionClass(3);
                            break;
                        case "부장", "팀장":
                            member.setPositionClass(4);
                            break;
                        case "대표 이사":
                            member.setPositionClass(5);
                            break;
                        default:
                            member.setPositionClass(0);
                    }
                    return MemberDepartmentResponse.of(true, member);
                })
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없습니다."));
    }

    @Transactional
    public MemberApproveResponse approveMember(UUID id) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setType(MemberType.USER);
                    return MemberApproveResponse.of(true, member);
                })
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없습니다."));
    }
}