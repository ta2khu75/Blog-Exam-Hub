package com.ta2khu75.quiz.model.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import com.ta2khu75.quiz.model.ReportTargetType;
import com.ta2khu75.quiz.model.TargetType;
import com.ta2khu75.quiz.model.entity.id.ReportTargetId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@IdClass(ReportTargetId.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportTarget {
	@Id
	String authorId;
	@Id
	String targetId;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	ReportTargetType reportTargetType;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	TargetType targetType;
	@CreatedDate
	@Column(nullable = false, updatable = false)
	Instant createdDate;
	@ManyToOne
	@MapsId("authorId")
	Account author;
}
