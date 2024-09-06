package com.ta2khu75.quiz.model.request.update;

import java.util.List;

import com.ta2khu75.quiz.model.base.QuizBase;
import com.ta2khu75.quiz.model.response.AnswerResponse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizUpdateRequest extends QuizBase {
	List<AnswerResponse> answers;
}
