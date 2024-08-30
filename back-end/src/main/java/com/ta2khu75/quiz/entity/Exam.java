package com.ta2khu75.quiz.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
	String title;
	@Column(nullable = false)
	Integer time;
	@Column(nullable = false)
	String description;
	@Column(nullable = false)
	String imagePath;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	ExamType examType;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	ExamLevel examLevel;
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
	List<ExamHistory> examHistories;
}
