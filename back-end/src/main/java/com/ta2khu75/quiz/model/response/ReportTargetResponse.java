package com.ta2khu75.quiz.model.response;

import java.time.Instant;

import com.ta2khu75.quiz.model.ReportTargetType;
import com.ta2khu75.quiz.model.TargetType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportTargetResponse {
	String targetId;
	ReportTargetType reportTargetType;
	TargetType targetType;
	Instant createdDate;
	AccountResponse author;

}
