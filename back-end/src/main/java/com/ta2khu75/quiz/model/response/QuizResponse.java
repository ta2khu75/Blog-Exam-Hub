package com.ta2khu75.quiz.model.response;


import com.ta2khu75.quiz.model.base.QuizBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizResponse extends QuizBase{
	Long id;
	String filePath;
}
