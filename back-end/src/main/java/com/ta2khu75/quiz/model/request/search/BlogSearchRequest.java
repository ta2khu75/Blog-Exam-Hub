package com.ta2khu75.quiz.model.request.search;

import com.ta2khu75.quiz.model.base.SearchRequestBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlogSearchRequest extends SearchRequestBase {
	private String tagName;
}
