package com.ta2khu75.quiz.model.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ta2khu75.quiz.model.NotificationStatus;
import com.ta2khu75.quiz.model.TargetType;
import com.ta2khu75.quiz.model.entity.id.NotificationId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(NotificationId.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Notification {
	@Id
	String accountId;
	@Id
	String targetId;
	@Enumerated(EnumType.STRING)
	TargetType targetType;
	@Enumerated(EnumType.STRING)
	NotificationStatus status=NotificationStatus.UNREAD;
	@CreatedDate
	@Column(nullable = false, updatable = false)
	Instant createdDate;
	@ManyToOne
	@MapsId("accountId")
	Account account;
}
