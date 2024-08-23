package com.ta2khu75.quiz.entity.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
	int totalElements;
	int totalPages;
	List<T> content;

}
