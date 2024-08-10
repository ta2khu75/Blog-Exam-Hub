package com.ta2khu75.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ta2khu75.quiz.entity.Quiz;
import java.util.List;
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByExamId(Long id);
}
