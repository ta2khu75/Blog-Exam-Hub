package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.model.request.ExamCategoryRequest;
import com.ta2khu75.quiz.model.response.ExamCategoryResponse;
public interface ExamCategoryService extends CrudService<Long, ExamCategoryRequest , ExamCategoryResponse>{
	List<ExamCategoryResponse> readAll();
}
