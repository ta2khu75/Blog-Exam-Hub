package com.ta2khu75.quiz.entity.response;

import com.ta2khu75.quiz.entity.request.AnswerRequest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerResponse extends AnswerRequest {
	Long id;
}