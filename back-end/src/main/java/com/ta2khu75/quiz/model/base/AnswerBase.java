package com.ta2khu75.quiz.model.base;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AnswerBase {
	@NotBlank
	String answer;
	boolean correct;
}
