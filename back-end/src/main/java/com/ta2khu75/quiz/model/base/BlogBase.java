package com.ta2khu75.quiz.model.base;

import java.util.List;

import com.ta2khu75.quiz.model.AccessModifier;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class BlogBase {
	@NotNull(message = "Title must not be null")
	String title;
	@NotNull(message = "Access modifier must not be null")
	AccessModifier accessModifier;
	@NotEmpty(message = "Blog tags must not be empty")
	List<String> blogTags;
	
}
