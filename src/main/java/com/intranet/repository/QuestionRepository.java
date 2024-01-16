package com.intranet.repository;

import com.intranet.common.QuestionType;
import com.intranet.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findByType(QuestionType type);
}
