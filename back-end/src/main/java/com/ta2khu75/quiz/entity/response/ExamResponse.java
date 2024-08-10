package com.ta2khu75.quiz.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExamResponse (Long id, String title, String description, @JsonProperty("image_path") String imagePath, @JsonProperty("exam_type") String examType, @JsonProperty("exam_level") String examLevel){
}