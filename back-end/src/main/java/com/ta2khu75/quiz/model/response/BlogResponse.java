package com.ta2khu75.quiz.model.response;

import java.time.LocalDate;

import com.ta2khu75.quiz.model.base.BlogBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BlogResponse extends BlogBase {
	String id;
	int viewCount;
	int commentCount;
	String imagePath;
	LocalDate createdAt;
	LocalDate lastModifiedAt;
	AccountResponse author;
}
