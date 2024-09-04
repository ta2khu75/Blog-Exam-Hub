package com.ta2khu75.quiz.model.base;

import com.ta2khu75.quiz.model.entity.AccessModifier;
import com.ta2khu75.quiz.model.entity.ExamLevel;
import com.ta2khu75.quiz.model.entity.ExamType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class ExamBase {
	@NotBlank(message = "Title must not be blank")
	String title;

	@NotNull(message = "Time must not be null")
	Integer duration;

	@NotBlank(message = "Description must not be blank")
	String description;

	@NotNull(message = "Exam type must not be null")
	ExamType examType;

	@NotNull(message = "Exam level must not be null")
	ExamLevel examLevel;

	@NotNull(message = "Access modifier must not be null")
	AccessModifier accessModifier;
}
