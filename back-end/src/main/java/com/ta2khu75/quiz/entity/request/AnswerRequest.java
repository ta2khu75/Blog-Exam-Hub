package com.ta2khu75.quiz.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class AnswerRequest {
	@NotBlank
	String answer;
	boolean correct;
	@NotNull
	Long quizId;
}
