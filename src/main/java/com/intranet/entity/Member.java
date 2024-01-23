package com.intranet.entity;

import com.intranet.common.MemberType;
import com.intranet.dto.member.request.MemberUpdateRequest;
import com.intranet.dto.signUp.request.SignUpRequest;
import com.intranet.dto.member.request.MemberDepartmentRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, scale = 20, unique = true)
    private String account;
    @Column(nullable = false)
    private String password;
    private String name;
    private String birth;
    @Enumerated(EnumType.STRING)
    private MemberType type;
    @CreationTimestamp
    private LocalDate createdAt;
    private String address;
    private String email;
    private String phoneNumber;
    private String department;
    private String position;
    private int positionClass;

    public static Member from(SignUpRequest request, PasswordEncoder encoder) {
        return Member.builder()
                .account(request.account())
                .password(encoder.encode(request.password()))
                .name(request.name())
                .birth(request.birth())
                .type(MemberType.WAITING)
                .createdAt(LocalDate.now())
                .address(request.address())
                .phoneNumber(request.phoneNumber())
                .build();
    }

    @Builder
    private Member(String account, String password, String name, String birth, MemberType type, LocalDate createdAt, String address, String email, String phoneNumber, String department, String position, int positionClass) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.type = type;
        this.createdAt = createdAt;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.position = position;
        this.positionClass = positionClass;
    }

    public void update(MemberUpdateRequest newMember, PasswordEncoder encoder) {
        this.password = newMember.newPassword() == null || newMember.newPassword().isBlank()
                ? this.password : encoder.encode(newMember.newPassword());
        this.name = newMember.name();
        this.birth = newMember.birth();
        this.email = newMember.email();
        this.address = newMember.address();
        this.phoneNumber = newMember.phoneNumber();
    }

    public void setDepartment(MemberDepartmentRequest request) {
        this.department = request.department();
        this.position = request.position();
    }
}
