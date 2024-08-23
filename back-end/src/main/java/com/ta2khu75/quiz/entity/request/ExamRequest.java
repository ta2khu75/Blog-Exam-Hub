package com.ta2khu75.quiz.entity.request;

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
	@NotBlank
	String title;
	@NotNull
	Integer time;
	@NotBlank
	String description;
	@NotNull
	ExamType examType;
	@NotNull
	ExamLevel examLevel;
}