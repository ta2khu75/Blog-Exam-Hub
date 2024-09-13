package com.ta2khu75.quiz.model.request;

import java.util.List;

import com.ta2khu75.quiz.model.UpdateGroup;
import com.ta2khu75.quiz.model.base.ExamBase;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamRequest extends ExamBase{
//	@NotNull(message="Exam id must not be null", groups = UpdateGroup.class)
//	Long id;
	@NotNull(message = "Exam category must not be null")
	Long examCategoryId;
	@NotEmpty(message = "Quizzzes must not be empty")
	List<QuizRequest> quizzes;
}