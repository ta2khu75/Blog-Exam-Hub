package com.ta2khu75.quiz.model.response;

import java.time.Instant;

import com.ta2khu75.quiz.model.NotificationStatus;
import com.ta2khu75.quiz.model.TargetType;
import com.ta2khu75.quiz.model.entity.id.NotificationId;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationResponse {
//	NotificationId id;
	NotificationStatus status;
	Object target;
	TargetType targetType;
	Instant createdDate;
}
