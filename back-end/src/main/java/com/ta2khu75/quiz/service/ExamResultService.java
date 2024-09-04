package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.UserAnswerRequest;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamResultDetailsResponse;

public interface ExamResultService {
	ExamResultResponse readByExamId(Long examId);
	ExamResultResponse createByExamId(Long examId);
	ExamResultDetailsResponse scoreByExamId(Long id, UserAnswerRequest[] answerUserRequest);
	
	PageResponse<ExamResultResponse> readPage(Pageable pageable);

	ExamResultDetailsResponse read(Long id);
}
