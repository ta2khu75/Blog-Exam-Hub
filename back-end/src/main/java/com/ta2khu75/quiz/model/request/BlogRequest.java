package com.ta2khu75.quiz.model.request;

import java.util.List;

import com.ta2khu75.quiz.model.base.BlogBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogRequest extends BlogBase {
	List<String> blogTags;
}
