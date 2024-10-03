package com.ta2khu75.quiz.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.ExamLevel;
import com.ta2khu75.quiz.model.ExamStatus;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"author","blog", "quizzes","examCategory", "examResults"})
public class Exam extends EntityBase {
	@Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")	
	String title;
	@Column(nullable = false)
	Integer duration;
	@Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
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
	Account author;
	@ManyToOne
	Blog blog;
	@ManyToOne
	ExamCategory examCategory;
	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Quiz> quizzes;
	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
	List<ExamResult> examResults;

	
}
