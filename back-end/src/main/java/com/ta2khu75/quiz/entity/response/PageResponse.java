package com.ta2khu75.quiz.entity.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
	int totalElements;
	int totalPages;
	List<T> content;

}
