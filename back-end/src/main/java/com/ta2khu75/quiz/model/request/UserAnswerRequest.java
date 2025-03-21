package com.ta2khu75.quiz.model.request;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswerRequest {
	Long quizId;
	Set<Long> answerIds;
}
