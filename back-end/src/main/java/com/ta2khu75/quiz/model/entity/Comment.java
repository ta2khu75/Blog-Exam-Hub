package com.ta2khu75.quiz.model.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false, columnDefinition = "NVARCHAR(255)")
	String content;
	String filePath;
	@ManyToOne
	Account author;
	@ManyToOne
	Blog blog;
	@Column(nullable = false, updatable = false)
	@CreatedDate
	LocalDate createdAt;
	@LastModifiedDate
	@Column(insertable = false)
	LocalDate updatedAt;
}
