package com.ta2khu75.quiz.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record QuizRequest(@NotBlank String question, @NotNull @JsonProperty("quiz_type") com.ta2khu75.quiz.entity.QuizType quizType, @NotNull @JsonProperty("exam_id") Long examId) {
}