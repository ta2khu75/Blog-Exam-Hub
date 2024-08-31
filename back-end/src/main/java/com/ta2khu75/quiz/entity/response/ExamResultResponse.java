package com.ta2khu75.quiz.entity.response;

import java.time.LocalDateTime;

import com.ta2khu75.quiz.entity.response.details.ExamDetailsResponse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ExamResultResponse {
	Long id;
	float point;
	int correctCount;
	ExamDetailsResponse exam;
	AccountResponse account;
	LocalDateTime endTime;
	LocalDateTime createdDate;
	LocalDateTime lastModifiedDate;
}
