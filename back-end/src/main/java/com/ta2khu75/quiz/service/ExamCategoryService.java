package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.model.request.ExamCategoryRequest;
import com.ta2khu75.quiz.model.response.ExamCategoryResponse;
import com.ta2khu75.quiz.service.base.CrudService;

public interface ExamCategoryService extends CrudService<ExamCategoryRequest, ExamCategoryResponse, Long> {
	List<ExamCategoryResponse> readAll();
}
