package com.ta2khu75.quiz.model.response;

import java.util.List;

import com.ta2khu75.quiz.model.base.QuizBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizResponse extends QuizBase {
	String imagePath;
	AccountResponse author;
	QuizCategoryResponse quizCategory;
	InfoResponse info;
	BlogResponse blog;
	List<QuestionResponse> questions;
}