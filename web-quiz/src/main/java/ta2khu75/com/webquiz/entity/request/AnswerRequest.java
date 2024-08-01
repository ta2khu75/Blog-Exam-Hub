package ta2khu75.com.webquiz.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record AnswerRequest(@NotBlank String answer, @NotBlank @JsonProperty("answer_type") String answerType, @NotNull @JsonProperty("quiz_id") Long quizId) {
}
