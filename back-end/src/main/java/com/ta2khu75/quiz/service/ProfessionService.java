package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.entity.ExamHistory;
import com.ta2khu75.quiz.entity.request.UserAnswerRequest;

public interface ProfessionService {
	Double score(ExamHistory examHistory, Long examId, UserAnswerRequest[] answerUserRequest);
}
