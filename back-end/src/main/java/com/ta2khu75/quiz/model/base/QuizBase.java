package com.ta2khu75.quiz.model.base;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.QuizLevel;
import com.ta2khu75.quiz.model.QuizResultMode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class QuizBase {
	@NotBlank(message = "Title must not be blank")
	String title;
	@NotNull(message = "Time must not be null")
	Integer duration;
	@NotBlank(message = "Description must not be blank")
	String description;
	@NotNull(message = "Exam level must not be null")
	QuizLevel quizLevel;
	AccessModifier accessModifier=AccessModifier.PRIVATE;
	QuizResultMode quizResultMode=QuizResultMode.ANSWER_VISIBLE;
	boolean shuffleQuestion=true;
	boolean isCompleted;
	
}
