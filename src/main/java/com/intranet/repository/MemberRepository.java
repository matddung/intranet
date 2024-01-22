package com.intranet.repository;

import com.intranet.common.MemberType;
import com.intranet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByAccount(String account);
    List<Member> findAllByType(MemberType type);
    Member findByDepartmentAndPositionClass(String department, int positionClass);
    Optional<Member> findByEmailAndAccount(String email, String account);
}
