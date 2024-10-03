package com.ta2khu75.quiz.model.response;

import com.ta2khu75.quiz.model.base.BlogBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogResponse extends BlogBase {
	int viewCount;
	int commentCount;
	String imagePath;
	AccountResponse author;
	InfoResponse info;
}
