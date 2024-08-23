package com.ta2khu75.quiz.entity.response.details;

import java.util.List;

import com.ta2khu75.quiz.entity.response.AnswerResponse;
import com.ta2khu75.quiz.entity.response.QuizResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class QuizDetaislResponse extends QuizResponse {
	private List<AnswerResponse> answers;
}