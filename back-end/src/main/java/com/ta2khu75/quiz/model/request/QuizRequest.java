package com.ta2khu75.quiz.model.request;

import com.ta2khu75.quiz.model.base.QuizBase;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizRequest extends QuizBase{
	@NotNull(message = "Exam ID must not be null")
	Long examId;
}