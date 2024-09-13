package com.ta2khu75.quiz.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"quiz", "userAnswers"})
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
	String answerString;
	@Column(nullable = false)
	Boolean correct;
	@ManyToOne
	Quiz quiz;
	@ManyToMany(mappedBy = "answers", cascade = CascadeType.ALL)
    List<UserAnswer> userAnswers = new ArrayList<>();
}
