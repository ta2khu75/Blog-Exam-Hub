package com.ta2khu75.quiz.model.response;

import com.ta2khu75.quiz.model.base.ExamCategoryBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamCategoryResponse extends ExamCategoryBase{
	Long id;
}
