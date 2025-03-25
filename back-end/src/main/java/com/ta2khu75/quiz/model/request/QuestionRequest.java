package com.ta2khu75.quiz.model.request;

import java.util.List;

import com.ta2khu75.quiz.model.base.QuestionBase;
import com.ta2khu75.quiz.model.entity.Quiz;
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
public class QuestionRequest extends QuestionBase{
	@NotNull(message = "Question ID must not be null",groups = Update.class)
	Long id;
	@NotNull(message = "Quiz must not be null",groups = Create.class)
	Quiz quiz;
	@NotEmpty(message = "Answers must not be empty")
	List<AnswerRequest> answers;
}