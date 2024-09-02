package com.ta2khu75.quiz.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {
	Long id;
	String question;
	String filePath;
	String quizType;
	Long examId;
}
