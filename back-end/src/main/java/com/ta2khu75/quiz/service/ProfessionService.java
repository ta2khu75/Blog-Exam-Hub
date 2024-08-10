package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.entity.request.AnswerUserRequest;

public interface ProfessionService {
	Double score(Long examId, AnswerUserRequest[] answerUserRequest);
}
