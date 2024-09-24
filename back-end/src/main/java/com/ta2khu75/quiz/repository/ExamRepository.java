package com.ta2khu75.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.ExamLevel;
import com.ta2khu75.quiz.model.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, String> {
	@Query("SELECT e FROM Exam e WHERE "
			+ "(:keyword IS NULL OR e.title LIKE %:keyword% OR e.description LIKE %:keyword% OR e.author.displayName LIKE %:keyword% OR e.examCategory.name LIKE %:keyword%) "
			+ "AND (:examCategoryId IS NULL OR e.examCategory.id = :examCategoryId) "
			+ "AND (:authorEmail IS NULL OR e.author.email= :authorEmail) "
			+ "AND (:examLevel IS NULL OR e.examLevel = :examLevel) "
			+ "AND (:authorId IS NULL OR e.author.id= :authorId) " + "AND (:accessModifier = :accessModifier) ")
	Page<Exam> searchExam(@Param("keyword") String keyword, @Param("examCategoryId") Long examCategoryId,
			@Param("authorEmail") String authorEmail, @Param("authorId") String authorId,
			@Param("examLevel") ExamLevel examLevel, @Param("accessModifier") AccessModifier accessModifier,
			Pageable pageable);
}
