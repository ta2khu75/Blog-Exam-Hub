package com.ta2khu75.quiz.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamCategoryResponse{
	Long id;
	String name;
}
