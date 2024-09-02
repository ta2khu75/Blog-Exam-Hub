package com.ta2khu75.quiz.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {
	Long id;
	String answer;
	boolean correct;
	Long quizId;
}