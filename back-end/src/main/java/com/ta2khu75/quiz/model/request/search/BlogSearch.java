package com.ta2khu75.quiz.model.request.search;

import java.util.List;

import com.ta2khu75.quiz.model.AccessModifier;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlogSearch extends Search {
	private List<String> blogTagNames;
	private Integer minView;
	private Integer maxView;
	private String authorId;
	private AccessModifier accessModifier;
}
