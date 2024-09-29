package com.ta2khu75.quiz.model.request.search;

import java.util.List;

import com.ta2khu75.quiz.model.ExamLevel;
import com.ta2khu75.quiz.model.base.SearchRequestBase;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class ExamSearchRequest extends SearchRequestBase {
    private List<ExamLevel> examLevels;
    private List<Long> examCategoryIds;
    private Integer minDuration;
	private Integer maxDuration;
}
