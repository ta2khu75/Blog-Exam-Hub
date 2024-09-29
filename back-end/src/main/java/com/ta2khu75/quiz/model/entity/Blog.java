package com.ta2khu75.quiz.model.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ta2khu75.quiz.model.AccessModifier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	@Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
	String title;
	@Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
	String content;
	String imagePath;
	int viewCount;
	boolean deleted;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	AccessModifier accessModifier;
	@OneToMany(mappedBy = "blog")
	List<Exam> exams;
	@CreatedDate
	@Column(updatable = false, nullable = false)
	LocalDate createdAt;
	LocalDate lastModifiedAt;
	@ManyToOne
	Account author;
	@OneToMany(mappedBy = "blog")
	List<Comment> comments;
	@ManyToMany
	List<BlogTag> blogTags;
}
