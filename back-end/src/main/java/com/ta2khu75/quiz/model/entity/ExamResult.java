package com.ta2khu75.quiz.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ExamResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Float point;
	Integer correctCount;
	@ManyToOne
	Account account;
	@ManyToOne
	Exam exam;
	@OneToMany(mappedBy = "examResult")
	List<UserAnswer> userAnswers;
	@Column(nullable = false)
	LocalDateTime endTime;
	@CreatedDate
	@Column(nullable = false, updatable = false)
	LocalDateTime createdDate;
	@LastModifiedDate
	@Column(insertable = false)
	LocalDateTime lastModifiedDate;
}
