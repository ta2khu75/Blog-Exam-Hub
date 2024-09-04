package com.ta2khu75.quiz.model.request;

import java.util.Set;

import lombok.Data;
@Data
public class UserAnswerRequest {
	Long quizId;
	Set<Long> answerIds;
}
