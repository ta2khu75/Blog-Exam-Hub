package com.ta2khu75.quiz.entity.request;

import com.ta2khu75.quiz.entity.QuizType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class QuizRequest {
	@NotBlank(message = "Question must not be blank")
	String question;

	@NotNull(message = "Quiz type must not be null")
	QuizType quizType;

	@NotNull(message = "Exam ID must not be null")
	Long examId;
}