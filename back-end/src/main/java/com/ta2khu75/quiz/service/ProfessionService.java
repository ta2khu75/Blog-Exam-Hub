package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.request.UserAnswerRequest;
import com.ta2khu75.quiz.model.entity.ExamResult;

public interface ProfessionService {
	void score(ExamResult examHistory, Long examId, UserAnswerRequest[] answerUserRequest);
}
