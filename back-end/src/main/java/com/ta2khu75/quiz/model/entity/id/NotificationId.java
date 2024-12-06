package com.ta2khu75.quiz.model.entity.id;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationId implements Serializable {
	private static final long serialVersionUID = 6760439381782529866L;
	private String accountId;
	private String targetId;
}
