package com.ta2khu75.quiz.entity.response;

import java.time.LocalDateTime;

import com.ta2khu75.quiz.entity.response.details.ExamDetailsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
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
