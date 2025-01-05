package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.entity.Notification;
import com.ta2khu75.quiz.model.entity.id.NotificationId;
import com.ta2khu75.quiz.model.response.NotificationResponse;
import com.ta2khu75.quiz.model.response.PageResponse;

public interface NotificationService {
	PageResponse<NotificationResponse> readPageByAccountId(String accountId, Pageable pageable);
}
