package com.ta2khu75.quiz.model.response;

import java.time.LocalDateTime;

import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ExamResultResponse {
	Long id;
	Double point;
	Integer correctCount;
	ExamDetailsResponse exam;
	AccountResponse account;
	LocalDateTime endTime;
	LocalDateTime createdDate;
	LocalDateTime lastModifiedDate;
}
