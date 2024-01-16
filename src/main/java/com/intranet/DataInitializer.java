package com.intranet;

import com.intranet.common.MemberType;
import com.intranet.common.QuestionType;
import com.intranet.entity.Member;
import com.intranet.entity.Question;
import com.intranet.repository.MemberRepository;
import com.intranet.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class DataInitializer implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) {
        memberRepository.save(Member.builder()
                .account("admin")
                .password(encoder.encode("admin"))
                .name("관리자")
                .type(MemberType.ADMIN)
                .build());

        memberRepository.save(Member.builder()
                .account("test1")
                .password(encoder.encode("test1"))
                .name("김대리")
                .department("영업팀")
                .position("대리")
                .positionClass(2)
                .type(MemberType.USER)
                .build());

        memberRepository.save(Member.builder()
                .account("test2")
                .password(encoder.encode("test2"))
                .name("김과장")
                .department("영업팀")
                .position("과장")
                .positionClass(3)
                .type(MemberType.USER)
                .build());

        memberRepository.save(Member.builder()
                .account("test3")
                .password(encoder.encode("test3"))
                .name("김부장")
                .department("영업팀")
                .position("부장")
                .positionClass(4)
                .type(MemberType.USER)
                .build());

        questionRepository.save(Question.builder()
                .subject("1번 게시글")
                .content("Initializer Question Data")
                .createdAt(LocalDateTime.now())
                .type(QuestionType.FREE)
                .build());
    }
}
