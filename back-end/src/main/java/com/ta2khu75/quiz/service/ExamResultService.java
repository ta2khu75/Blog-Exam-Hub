package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.entity.request.UserAnswerRequest;
import com.ta2khu75.quiz.entity.response.ExamResultResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import com.ta2khu75.quiz.entity.response.details.ExamResultDetailsResponse;

public interface ExamResultService {
	ExamResultResponse readByExamId(Long id);

	ExamResultDetailsResponse scoreByExamId(Long id, Long examId, UserAnswerRequest[] answerUserRequest);

	PageResponse<ExamResultResponse> readPage(Pageable pageable);

	ExamResultDetailsResponse read(Long id);
}
