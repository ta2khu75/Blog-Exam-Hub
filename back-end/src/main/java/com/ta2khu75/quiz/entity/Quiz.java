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
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String question;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    QuizType quizType;
    @ManyToOne
    Exam exam;
    @OneToMany(mappedBy = "quiz")
    List<Answer> answers;
    @OneToMany(mappedBy = "quiz")
    List<UserAnswer> userAnswers;
}
