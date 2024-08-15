package com.ta2khu75.quiz.entity.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserAnswerRequest(@JsonProperty("quiz_id") Long quizId, @JsonProperty("answer_ids") Set<Long> answerIds) { // Map<Long, Boolean> answers) {
}
