package com.ta2khu75.quiz.model.request.search;

import java.util.List;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.ExamLevel;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class ExamSearch extends Search {
    private List<ExamLevel> examLevels;
    private List<Long> examCategoryIds;
    private Integer minDuration;
	private Integer maxDuration;
	private String authorId;
	private AccessModifier accessModifier;
}
