package com.ta2khu75.quiz.model.base;

import com.ta2khu75.quiz.model.AccessModifier;

import lombok.Data;

@Data
public abstract class SearchRequestBase {
	protected String keyword;
	protected String authorId;
	protected String authorEmail;
	protected AccessModifier accessModifier;
	protected int page = 1;
	protected int size = 10;
}
