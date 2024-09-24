package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.ExamLevel;
import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;

public interface ExamService extends BaseFileService<String, ExamRequest, ExamResponse> {
	PageResponse<ExamResponse> searchExam(@Param("keyword") String keyword, Long examCategoryId,String authorEmail, String authorId,
			ExamLevel examLevel, AccessModifier accessModifier, Pageable pageable);
	ExamDetailsResponse readDetail(String id);
}
