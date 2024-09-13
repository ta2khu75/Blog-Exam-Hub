package com.ta2khu75.quiz.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"account", "quizzes","examCategory", "examResults"})
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	@Column(nullable = false)
	String title;
	@Column(nullable = false)
	Integer duration;
	@Column(nullable = false)
	String description;
	@Column(nullable = false)
	String imagePath;
	boolean deleted;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	ExamLevel examLevel;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	ExamStatus examStatus;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	AccessModifier accessModifier;
	@ManyToOne
	Account account;
	@ManyToOne
	ExamCategory examCategory;
	@OneToMany(mappedBy = "exam")
	List<Quiz> quizzes;
	@OneToMany(mappedBy = "exam")
	List<ExamResult> examResults;
}
