package com.ta2khu75.quiz.model.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
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
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String content;
	String filePath;
	@ManyToOne
	Account author;
	@ManyToOne
	Blog blog;
	@CreatedDate
	LocalDate createdAt;
	@LastModifiedDate
	LocalDate updatedAt;
}
