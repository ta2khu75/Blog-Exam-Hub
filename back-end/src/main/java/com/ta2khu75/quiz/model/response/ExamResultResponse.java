package com.ta2khu75.quiz.model.response;

import java.time.Instant;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamResultResponse {
	double point;
	int correctCount;
	ExamResponse exam;
	AccountResponse account;
	Instant endTime;
	InfoResponse info;
	List<UserAnswerResponse> userAnswers;
}
