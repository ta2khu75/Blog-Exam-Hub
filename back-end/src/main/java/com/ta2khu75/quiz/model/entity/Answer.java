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
@ToString(exclude = {"question", "userAnswers"})
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
	String answerString;
	@Column(nullable = false)
	boolean correct;
	@ManyToOne
	Question question;
	@ManyToMany(mappedBy = "answers", cascade = CascadeType.ALL)
    List<UserAnswer> userAnswers = new ArrayList<>();
}
