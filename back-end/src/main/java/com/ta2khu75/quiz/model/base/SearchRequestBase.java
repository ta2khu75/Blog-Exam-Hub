package com.ta2khu75.quiz.model.base;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class SearchRequestBase {
	int page = 1;
	int size = 10;
}
