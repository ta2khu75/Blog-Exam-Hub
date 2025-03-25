package com.ta2khu75.quiz.model.request.search;

import java.time.Instant;
import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class QuizResultSearch extends Search {
	private Instant fromDate;
	private Instant toDate;
    private List<Long> examCategoryIds;
}
