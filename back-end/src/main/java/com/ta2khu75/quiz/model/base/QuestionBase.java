package com.ta2khu75.quiz.model.base;

import com.ta2khu75.quiz.model.QuestionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class QuestionBase {
	@NotBlank(message = "Question must not be blank")
	String question;
	boolean shuffleAnswer;
	@NotNull(message = "Quiz type must not be null")
	QuestionType questionType;
}
