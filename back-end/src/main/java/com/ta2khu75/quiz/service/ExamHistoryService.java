package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.entity.request.UserAnswerRequest;
import com.ta2khu75.quiz.entity.response.ExamHistoryResponse;
import com.ta2khu75.quiz.entity.response.details.ExamHistoryDetailsResponse;
import com.ta2khu75.quiz.repository.ExamHistoryRepository;

public interface ExamHistoryService {
	ExamHistoryResponse readByExamId(Long id);
	ExamHistoryDetailsResponse scoreByExamId(Long id, Long examId, UserAnswerRequest[] answerUserRequest);
}
