package com.ta2khu75.quiz.entity.response.details;

import java.util.List;

import com.ta2khu75.quiz.entity.response.QuizResponse;

public record ExamDetailsResponse(Long id, String title, Integer time, String description, String imagePath, String examType, String examLevel, List<QuizDetaislResponse> quizzes) {

}
