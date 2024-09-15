package com.ta2khu75.quiz.model.request;

import java.util.List;

import com.ta2khu75.quiz.model.base.QuizBase;
import com.ta2khu75.quiz.model.entity.Exam;
import com.ta2khu75.quiz.model.group.Create;
import com.ta2khu75.quiz.model.group.Update;

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
	@NotNull(message = "Quiz ID must not be null",groups = Update.class)
	Long id;
	@NotNull(message = "Exam must not be null",groups = Create.class)
	Exam exam;
	@NotEmpty(message = "Answers must not be empty")
	List<AnswerRequest> answers;
}