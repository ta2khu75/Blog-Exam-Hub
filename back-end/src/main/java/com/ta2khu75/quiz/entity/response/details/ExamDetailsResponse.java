package com.ta2khu75.quiz.entity.response.details;

import java.util.List;

import com.ta2khu75.quiz.entity.response.ExamResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExamDetailsResponse extends ExamResponse {
	private List<QuizDetaislResponse> quizzes;

}
