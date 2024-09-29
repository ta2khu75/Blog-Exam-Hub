package com.ta2khu75.quiz.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

import com.ta2khu75.quiz.model.QuizType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"exam","userAnswers"})
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    String question;
    String filePath;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    QuizType quizType;
    @ManyToOne
    Exam exam;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Answer> answers;
    @OneToMany(mappedBy = "quiz")
    List<UserAnswer> userAnswers;
}
