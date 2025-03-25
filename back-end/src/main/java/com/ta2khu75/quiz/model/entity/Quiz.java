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
import com.ta2khu75.quiz.model.QuizLevel;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "author", "blog", "questions", "quizCategory"})
@EqualsAndHashCode(callSuper = true, exclude = { "author","blog", "quizCategory", "questions"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz extends EntityBase {
	@Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
	String title;
	@Column(nullable = false)
	Integer duration;
	@Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
	String description;
	@Column(nullable = false)
	String imagePath;
	boolean showResult=true;
	boolean showAnswer=true;
	boolean isShuffle=true; 
	boolean isDeleted;
	boolean isCompleted;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	QuizLevel quizLevel;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	AccessModifier accessModifier;
	@ManyToOne
	Account author;
	@ManyToOne
	Blog blog;
	@ManyToOne
	QuizCategory quizCategory;
	@OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE, orphanRemoval = true)
	List<Question> questions;
}
