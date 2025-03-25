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
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = { "account", "quiz", "userAnswers"})
@EqualsAndHashCode(callSuper = true, exclude = { "account", "quiz", "userAnswers"})
public class QuizResult extends EntityBase {
	Float point;
	Integer correctCount;
	@Column(nullable = false)
	Instant endTime;

	@ManyToOne
	Account account;
	@ManyToOne
	@JoinColumn(nullable = false)
	Quiz quiz;
	@OneToMany(mappedBy = "quizResult", cascade = CascadeType.REMOVE, orphanRemoval = true)
	List<UserAnswer> userAnswers;
}
