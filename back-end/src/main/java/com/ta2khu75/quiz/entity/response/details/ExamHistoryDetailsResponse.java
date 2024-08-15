package com.ta2khu75.quiz.entity.response.details;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ta2khu75.quiz.entity.response.AccountResponse;

public record ExamHistoryDetailsResponse(Long id, double point, ExamDetailsResponse exam, AccountResponse account,
		@JsonProperty("end_time") LocalDateTime endTime, @JsonProperty("created_date") LocalDateTime createdDate, @JsonProperty("last_modified_date") LocalDateTime lastModifiedDate, @JsonProperty("user_answers") List<UserAnswerResponse> userAnswers) {
}
