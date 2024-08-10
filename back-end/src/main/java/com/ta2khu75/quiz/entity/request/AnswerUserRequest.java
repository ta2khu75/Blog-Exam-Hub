package com.ta2khu75.quiz.entity.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AnswerUserRequest(@JsonProperty("quiz_id") Long quizId,Long[] answers) { // Map<Long, Boolean> answers) {
}
