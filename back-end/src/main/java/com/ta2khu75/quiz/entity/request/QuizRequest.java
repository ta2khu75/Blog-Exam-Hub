package com.ta2khu75.quiz.entity.request;

import com.ta2khu75.quiz.entity.QuizType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class QuizRequest {
	@NotBlank
	String question;
	@NotNull
	QuizType quizType;
	@NotNull
	Long examId;
}