package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.request.QuestionRequest;
import com.ta2khu75.quiz.model.response.QuestionResponse;
import com.ta2khu75.quiz.service.base.CrudService;


public interface QuestionService extends CrudService<QuestionRequest, QuestionResponse, Long> {}
