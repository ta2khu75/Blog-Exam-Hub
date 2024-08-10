package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.entity.request.AnswerRequest;
import com.ta2khu75.quiz.entity.response.AnswerResponse;
import java.util.List;
public interface AnswerService extends CrudService<Long, AnswerRequest , AnswerResponse>{
    Page<AnswerResponse> readPage(Pageable pageable);
    List<AnswerResponse> readAllByQuizId(Long id);
}