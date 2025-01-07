package com.ta2khu75.quiz.model.response;

import java.time.Instant;

import com.ta2khu75.quiz.model.response.details.ExamDetailResponse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamResultResponse {
	Double point;
	Integer correctCount;
	ExamDetailResponse exam;
	AccountResponse account;
	Instant endTime;
	InfoResponse info;
}
