package com.ta2khu75.quiz.model.response;


import java.util.List;

import com.ta2khu75.quiz.model.base.QuestionBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResponse extends QuestionBase{
	Long id;
	String filePath;
	List<AnswerResponse> answers;
}
