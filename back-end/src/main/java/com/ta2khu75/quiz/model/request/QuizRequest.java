package com.ta2khu75.quiz.model.request;

import java.util.List;

import com.ta2khu75.quiz.model.CreateGroup;
import com.ta2khu75.quiz.model.base.QuizBase;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizRequest extends QuizBase{
	@NotNull(message = "Exam ID must not be null",groups = CreateGroup.class)
	Long examId;
	@NotEmpty(message = "Answers must not be empty")
	List<AnswerRequest> answers;
}