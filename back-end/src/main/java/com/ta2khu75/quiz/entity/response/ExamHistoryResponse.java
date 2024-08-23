package com.ta2khu75.quiz.entity.response;

import java.time.LocalDateTime;

import com.ta2khu75.quiz.entity.response.details.ExamDetailsResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ExamHistoryResponse {
	Long id;
	float point;
	int correctCount;
	ExamDetailsResponse exam;
	AccountResponse account;
	LocalDateTime endTime;
	LocalDateTime createdDate;
	LocalDateTime lastModifiedDate;
}
