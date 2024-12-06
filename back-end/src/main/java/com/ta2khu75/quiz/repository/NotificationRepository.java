package com.ta2khu75.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.Notification;
import com.ta2khu75.quiz.model.entity.id.NotificationId;

public interface NotificationRepository extends JpaRepository<Notification, NotificationId> {
	Page<Notification> findByAccountId(String accountId, Pageable pageable);
}
