package ta2khu75.com.webquiz.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ta2khu75.com.webquiz.entity.ExamLevel;
import ta2khu75.com.webquiz.entity.ExamType;

public record ExamRequest(@NotBlank  String title, @NotBlank String description, @NotNull @JsonProperty("exam_type") ExamType examType, @JsonProperty("exam_level") @NotNull ExamLevel examLevel) {
}