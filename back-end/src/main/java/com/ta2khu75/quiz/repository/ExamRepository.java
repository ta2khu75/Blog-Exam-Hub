package com.ta2khu75.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, String> {
	@Query("select e from Exam e where e.author.id = :id and e.accessModifier = :accessModifier")
	Page<Exam> findByAccountIdAndAccessModifier(@Param("id") String id,
			@Param("accessModifier") AccessModifier accessModifier, Pageable pageable);

	Page<Exam> findByAuthorEmail(String email, Pageable pageable);

	@Query("select e from Exam e where e.examCategory.id = :id and e.accessModifier = :accessModifier")
	Page<Exam> findByExamCategoryIdAndAccessModifier(Long id, AccessModifier accessModifier, Pageable pageable);
}
