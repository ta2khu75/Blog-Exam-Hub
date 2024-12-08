package com.ta2khu75.quiz.model.entity;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ta2khu75.quiz.model.AccessModifier;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = { "author", "exams", "comments", "blogTags" })
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Blog extends EntityBase {
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
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	Set<Exam> exams;
	@ManyToOne
	Account author;
	@OneToMany(mappedBy = "blog", orphanRemoval = true, cascade = CascadeType.REMOVE)
	List<Comment> comments;
	@ManyToMany
	List<BlogTag> blogTags;

	public void addExam(Exam exam) {
		exams.add(exam);
		exam.setBlog(this);
	}

	public void removeExam(Exam exam) {
		exams.remove(exam);
		exam.setBlog(null);
	}
}
