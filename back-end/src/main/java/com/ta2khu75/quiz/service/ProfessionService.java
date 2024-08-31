package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.entity.ExamResult;
import com.ta2khu75.quiz.entity.request.UserAnswerRequest;

public interface ProfessionService {
	void score(ExamResult examHistory, Long examId, UserAnswerRequest[] answerUserRequest);
}
