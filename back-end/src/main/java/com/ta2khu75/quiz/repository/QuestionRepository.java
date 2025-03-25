package com.ta2khu75.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.Question;

import java.util.List;
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuizId(String id);
}
