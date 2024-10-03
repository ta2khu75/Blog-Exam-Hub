package com.ta2khu75.quiz.model.response;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InfoResponse {
	String id;
	Instant createdAt;
	Instant updatedAt;
}
