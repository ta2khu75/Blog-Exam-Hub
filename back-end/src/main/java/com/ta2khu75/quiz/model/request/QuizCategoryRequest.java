package com.ta2khu75.quiz.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuizCategoryRequest{
	@NotBlank(message = "Name must not be blank")
	private String name;
}
