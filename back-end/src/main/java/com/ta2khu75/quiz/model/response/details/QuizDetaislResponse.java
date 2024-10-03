package com.ta2khu75.quiz.model.response.details;

import java.util.List;

import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.model.response.QuizResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuizDetaislResponse extends QuizResponse {
	private List<AnswerResponse> answers;
}