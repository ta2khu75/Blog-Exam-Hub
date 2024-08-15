package com.ta2khu75.quiz.entity.response.details;

import java.util.List;

import com.ta2khu75.quiz.entity.response.AnswerResponse;
import com.ta2khu75.quiz.entity.response.QuizResponse;
import com.ta2khu75.quiz.repository.QuizRepository;

public record UserAnswerResponse (Long id, QuizResponse quiz, List<AnswerResponse> answers) {
}
