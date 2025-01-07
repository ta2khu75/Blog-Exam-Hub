package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.request.AnswerRequest;
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.service.base.CrudService;

import java.util.List;
public interface AnswerService extends CrudService<AnswerRequest, AnswerResponse, Long>{
    List<AnswerResponse> readAllByQuizId(Long id);
    void deleteByQuizId(Long id);
}