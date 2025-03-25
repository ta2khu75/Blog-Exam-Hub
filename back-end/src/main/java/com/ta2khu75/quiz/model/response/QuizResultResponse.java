package com.ta2khu75.quiz.model.response;

import java.time.Instant;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizResultResponse {
	double point;
	int correctCount;
	QuizResponse quiz;
	AccountResponse account;
	Instant endTime;
	InfoResponse info;
	List<UserAnswerResponse> userAnswers;
}
