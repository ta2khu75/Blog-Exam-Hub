package com.ta2khu75.quiz.model.entity;

import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class EntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	@CreatedDate
	@Column(updatable = false, nullable = false)
	Instant createdAt;
	@LastModifiedDate
	@Column(insertable = false)
	Instant updatedAt;
}
