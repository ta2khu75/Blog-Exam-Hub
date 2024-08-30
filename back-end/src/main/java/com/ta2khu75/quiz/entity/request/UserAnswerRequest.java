package com.ta2khu75.quiz.entity.request;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class UserAnswerRequest {
	Long quizId;
	Set<Long> answerIds;
}
