package com.ta2khu75.quiz.model.request.search;

import java.util.List;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.QuizLevel;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class QuizSearch extends Search {
    private List<QuizLevel> examLevels;
    private List<Long> examCategoryIds;
    private Integer minDuration;
	private Integer maxDuration;
	private String authorId;
	private Boolean isCompleted;
	private Boolean isShuffle;
	private Boolean showResult;
	private Boolean showAnswer;
	private AccessModifier accessModifier;
}
