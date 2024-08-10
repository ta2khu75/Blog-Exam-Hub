package com.ta2khu75.quiz.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.entity.request.QuizRequest;
import com.ta2khu75.quiz.entity.response.QuizResponse;
import java.util.List;
public interface QuizSerivce {
    QuizResponse create(QuizRequest request);
    QuizResponse update(Long id, QuizRequest request);
    void delete(Long id);
    Page<QuizResponse> read(Pageable pageable);
    QuizResponse read(Long id);
    List<QuizResponse> readByExamId(Long id);
}
