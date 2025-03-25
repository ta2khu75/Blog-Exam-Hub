package com.ta2khu75.quiz.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"quizzes"})
public class QuizCategory{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
	String name;
	@OneToMany(mappedBy = "quizCategory")
	List<Quiz> quizzes;
}