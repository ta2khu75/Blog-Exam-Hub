package com.ta2khu75.quiz.model.request;

import com.ta2khu75.quiz.model.CreateGroup;
import com.ta2khu75.quiz.model.UpdateGroup;
import com.ta2khu75.quiz.model.base.AnswerBase;
import com.ta2khu75.quiz.model.entity.Quiz;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerRequest extends AnswerBase{
	@NotNull(message = "Answer Id must not be null", groups = UpdateGroup.class)
	Long id;
	@NotNull(message = "Quiz must not be null", groups=CreateGroup.class)
	Quiz quiz;
}
