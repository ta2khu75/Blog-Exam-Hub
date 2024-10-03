package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.ExamResultRequest;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamResultDetailsResponse;

public interface ExamResultService {
	ExamResultResponse readByExamId(String examId);
	ExamResultResponse createByExamId(String examId);
	ExamResultDetailsResponse scoreByExamId(String id, ExamResultRequest examResultRequest);
	
	PageResponse<ExamResultResponse> readPage(Pageable pageable);

	ExamResultDetailsResponse read(String id);
}
