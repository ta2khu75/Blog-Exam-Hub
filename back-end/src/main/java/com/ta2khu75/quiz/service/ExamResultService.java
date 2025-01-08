package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.ExamResultRequest;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamResultDetailResponse;

public interface ExamResultService {
	ExamResultResponse readByExamId(String examId);
	ExamResultResponse createByExamId(String examId);
	ExamResultDetailResponse scoreByExamId(String id, ExamResultRequest examResultRequest);
	
	PageResponse<ExamResultResponse> readPage(Pageable pageable);

	ExamResultDetailResponse readDetails(String id);
}
