package com.ta2khu75.quiz.model.entity;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamResult extends EntityBase {
	Float point;
	Integer correctCount;
	@ManyToOne
	Account account;
	@ManyToOne
	Exam exam;
	@OneToMany(mappedBy = "examResult")
	List<UserAnswer> userAnswers;
	@Column(nullable = false)
	Instant endTime;
}
