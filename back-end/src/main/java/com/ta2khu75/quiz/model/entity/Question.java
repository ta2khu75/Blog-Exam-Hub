package com.ta2khu75.quiz.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

import com.ta2khu75.quiz.model.QuestionType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"quiz","userAnswers"})
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    String questionString;
    String filePath;
	boolean shuffleAnswer;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    QuestionType questionType;
    @ManyToOne
    Quiz quiz;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Answer> answers;
    @OneToMany(mappedBy = "question")
    List<UserAnswer> userAnswers;
}
