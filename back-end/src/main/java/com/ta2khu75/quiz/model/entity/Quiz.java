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
import com.ta2khu75.quiz.model.QuizResultMode;

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
	boolean shuffleQuestion=true;
	boolean deleted;
	boolean completed;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	QuizLevel quizLevel;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	AccessModifier accessModifier=AccessModifier.PRIVATE;
//	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	QuizResultMode quizResultMode=QuizResultMode.ANSWER_VISIBLE;
	@ManyToOne
	Account author;
	@ManyToOne
	Blog blog;
	@ManyToOne
	QuizCategory quizCategory;
	@OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE, orphanRemoval = true)
	List<Question> questions;
}
