package com.ta2khu75.quiz.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuizResponse(Long id, String question, @JsonProperty("file_path") String filePath, @JsonProperty("quiz_type") String quizType,@JsonProperty("exam_id") Long examId) {
}
