package com.ta2khu75.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.Answer;

import java.util.List;
import java.util.Set;
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
    List<Answer> findByQuestionIdIn(Set<Long> quizIds);
    void deleteByQuestionId(Long questionId);
//    List<Answer> findByQuestionIdAndCorrectTrue(Long id);
}
