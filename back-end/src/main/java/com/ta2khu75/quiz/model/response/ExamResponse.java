package com.ta2khu75.quiz.model.response;

import com.ta2khu75.quiz.model.base.ExamBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ExamResponse extends ExamBase {
	String id;
	String imagePath;
	AccountResponse author;
	ExamCategoryResponse examCategory;
}