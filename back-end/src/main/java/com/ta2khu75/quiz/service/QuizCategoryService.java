package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.model.request.ExamCategoryRequest;
import com.ta2khu75.quiz.model.response.QuizCategoryResponse;
import com.ta2khu75.quiz.service.base.CrudService;

public interface QuizCategoryService extends CrudService<ExamCategoryRequest, QuizCategoryResponse, Long> {
	List<QuizCategoryResponse> readAll();
}
