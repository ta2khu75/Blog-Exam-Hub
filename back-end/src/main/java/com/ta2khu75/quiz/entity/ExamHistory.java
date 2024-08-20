package com.ta2khu75.quiz.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ExamHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    float point;
    int correctCount;
    @ManyToOne
    Account account;
    @ManyToOne
    Exam exam;
    @OneToMany(mappedBy = "examHistory")
    List<UserAnswer> userAnswers;
    @Column(nullable = false)
    LocalDateTime endTime;
    @CreatedDate
	@Column(nullable = false, updatable = false)
	LocalDateTime createdDate;
    @LastModifiedDate
	@Column(insertable = false)
    LocalDateTime lastModifiedDate;
}
