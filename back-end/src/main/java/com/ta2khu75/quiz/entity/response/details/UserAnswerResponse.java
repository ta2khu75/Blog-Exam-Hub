package com.ta2khu75.quiz.entity.response.details;

import java.util.List;

import com.ta2khu75.quiz.entity.response.AnswerResponse;
import com.ta2khu75.quiz.entity.response.QuizResponse;

import lombok.Data;
@Data
public class UserAnswerResponse {
	Long id;
	QuizResponse quiz;
	List<AnswerResponse> answers;
}
