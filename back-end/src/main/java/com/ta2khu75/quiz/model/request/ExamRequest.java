package com.ta2khu75.quiz.model.request;

import com.ta2khu75.quiz.model.base.ExamBase;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamRequest extends ExamBase{
	@NotNull(message = "Exam category must not be null")
	Long examCategoryId;
}