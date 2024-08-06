package ta2khu75.com.webquiz.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ta2khu75.com.webquiz.entity.QuizType;
public record QuizRequest(@NotBlank String question, @NotNull @JsonProperty("quiz_type") QuizType quizType, @NotNull @JsonProperty("exam_id") Long examId) {
}