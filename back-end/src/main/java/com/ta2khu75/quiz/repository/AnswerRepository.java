package com.ta2khu75.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.Answer;

import java.util.List;
import java.util.Set;
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuizId(Long id);
    List<Answer> findByQuizIdIn(Set<Long> quizIds);
    List<Answer> findByQuizIdAndCorrectTrue(Long id);
    void deleteByQuizId(Long id);
}
