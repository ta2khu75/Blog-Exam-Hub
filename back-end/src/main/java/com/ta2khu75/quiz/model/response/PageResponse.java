package com.ta2khu75.quiz.model.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
	int number;
	int totalElements;
	int totalPages;
	List<T> content;
}
