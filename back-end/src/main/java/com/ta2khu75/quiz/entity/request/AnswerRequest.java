package com.ta2khu75.quiz.entity.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class AnswerRequest {
	@NotBlank
	String answer;
	boolean correct;
	@NotNull
	Long quizId;
}
