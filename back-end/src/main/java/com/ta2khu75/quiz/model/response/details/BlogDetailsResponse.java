package com.ta2khu75.quiz.model.response.details;

import com.ta2khu75.quiz.model.response.BlogResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlogDetailsResponse extends BlogResponse {
	private String content;
}
