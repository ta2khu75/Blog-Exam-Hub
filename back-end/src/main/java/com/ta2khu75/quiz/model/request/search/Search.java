package com.ta2khu75.quiz.model.request.search;

import lombok.Data;

@Data
public class Search {
	protected String keyword;
	protected int page = 1;
	protected int size = 10;
}
