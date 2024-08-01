package ta2khu75.com.webquiz.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record QuizRequest(@NotBlank String question, @NotBlank @JsonProperty("quiz_type") String quizType, @NotNull @JsonProperty("exam_id") Long examId) {
}