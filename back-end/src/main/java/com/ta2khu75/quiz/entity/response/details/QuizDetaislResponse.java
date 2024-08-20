package com.ta2khu75.quiz.entity.response.details;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ta2khu75.quiz.entity.response.AnswerResponse;

public record QuizDetaislResponse(Long id, String question, @JsonProperty("file_path") String filePath, @JsonProperty("quiz_type") String quizType,@JsonProperty("exam_id") Long examId, List<AnswerResponse> answers) {

}