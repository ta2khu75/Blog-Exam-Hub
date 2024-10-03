package com.ta2khu75.quiz.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends EntityBase {
	@Column(nullable = false, columnDefinition = "NVARCHAR(255)")
	String content;
	String filePath;
	@ManyToOne
	Account author;
	@ManyToOne
	Blog blog;
}
