package com.ta2khu75.quiz.model.response;


import com.ta2khu75.quiz.model.base.AnswerBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class AnswerResponse extends AnswerBase{
	Long id;
}