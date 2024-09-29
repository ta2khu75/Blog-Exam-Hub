package com.ta2khu75.quiz.model.request.search;

import java.util.List;

import com.ta2khu75.quiz.model.base.SearchRequestBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlogSearchRequest extends SearchRequestBase {
	private List<String> blogTagNames;
	private Integer minView;
	private Integer maxView;
}
