package com.ta2khu75.quiz.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ta2khu75.quiz.entity.ExamLevel;
import com.ta2khu75.quiz.entity.ExamType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExamRequest(@NotBlank  String title, @NotNull Integer time, @NotBlank String description, @NotNull @JsonProperty("exam_type") ExamType examType, @JsonProperty("exam_level") @NotNull ExamLevel examLevel) {
}