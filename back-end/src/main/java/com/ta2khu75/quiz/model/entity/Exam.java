package com.ta2khu75.quiz.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.ExamLevel;
import com.ta2khu75.quiz.model.ExamStatus;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"author","blog", "quizzes","examCategory", "examResults"})
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	@Column(nullable = false)
	String title;
	@Column(nullable = false)
	Integer duration;
	@Column(nullable = false, columnDefinition = "TEXT")
	String description;
	@Column(nullable = false)
	String imagePath;
	boolean deleted;
	@CreatedDate
	@Column(nullable = false, updatable = false)
	LocalDate createdAt;
	@LastModifiedDate
	@Column(insertable = false)
	LocalDate lastModifiedAt;
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
