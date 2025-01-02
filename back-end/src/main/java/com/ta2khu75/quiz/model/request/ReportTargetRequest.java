package com.ta2khu75.quiz.model.request;

import com.ta2khu75.quiz.model.ReportTargetType;
import com.ta2khu75.quiz.model.TargetType;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportTargetRequest {
	@NotNull
	String targetId;
	@NotNull
	ReportTargetType reportTargetType;
	@NotNull
	TargetType targetType;
}
