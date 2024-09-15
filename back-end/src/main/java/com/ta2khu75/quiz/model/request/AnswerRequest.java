package com.ta2khu75.quiz.model.request;

import com.ta2khu75.quiz.model.base.AnswerBase;
import com.ta2khu75.quiz.model.entity.Quiz;
import com.ta2khu75.quiz.model.group.Create;
import com.ta2khu75.quiz.model.group.Update;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerRequest extends AnswerBase{
	@NotNull(message = "Answer Id must not be null", groups = Update.class)
	Long id;
	@NotNull(message = "Quiz must not be null", groups=Create.class)
	Quiz quiz;
}
