package com.ta2khu75.quiz.entity.request;

import com.ta2khu75.quiz.entity.AccessModifier;
import com.ta2khu75.quiz.entity.ExamLevel;
import com.ta2khu75.quiz.entity.ExamType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ExamRequest {
	@NotBlank(message = "Title must not be blank")
	String title;

	@NotNull(message = "Time must not be null")
	Integer time;

	@NotBlank(message = "Description must not be blank")
	String description;

	@NotNull(message = "Exam type must not be null")
	ExamType examType;

	@NotNull(message = "Exam level must not be null")
	ExamLevel examLevel;

	@NotNull(message = "Access modifier must not be null")
	AccessModifier accessModifier;
}