package ta2khu75.com.webquiz.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuizResponse(Long id, String question, @JsonProperty("quiz_type") String quizType,@JsonProperty("exam_id") Long examId) {
}
