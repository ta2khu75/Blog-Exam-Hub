package ta2khu75.com.webquiz.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import ta2khu75.com.webquiz.entity.AnswerType;

public record AnswerResponse(Long id, @JsonProperty("answer") String answerString, boolean correct, @JsonProperty("quiz_id") Long quizId
//  @JsonProperty("answer_type") AnswerType answerType
 ) {
}