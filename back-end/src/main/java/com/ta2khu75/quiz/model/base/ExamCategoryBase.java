package com.ta2khu75.quiz.model.base;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class ExamCategoryBase {
	@NotBlank(message = "Name must not be blank")
	String name;
}
