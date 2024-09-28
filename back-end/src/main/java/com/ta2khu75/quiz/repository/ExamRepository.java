package com.ta2khu75.quiz.repository;

import java.util.List;

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
			+ "AND (:examCategoryIds IS NULL OR e.examCategory.id IN (:examCategoryIds)) "
			+ "AND (:authorEmail IS NULL OR e.author.email= :authorEmail) "
			+ "AND (:authorId IS NULL OR e.author.id= :authorId) "
			+ "AND (:examLevel IS NULL OR e.examLevel = :examLevel)"
			+ "AND (:minDuration IS NULL OR e.duration >= :minDuration) " 
			+ "AND (:maxDuration IS NULL OR e.duration <= :maxDuration) "
			+ "AND (:accessModifier IS NULL OR :accessModifier = :accessModifier) ")
	Page<Exam> searchExam(@Param("keyword") String keyword, @Param("examCategoryIds") List<Long> examCategoryIds,
			@Param("authorEmail") String authorEmail, @Param("authorId") String authorId,
			@Param("examLevel") ExamLevel examLevel, @Param("minDuration") Integer minDuration, @Param("maxDuration") Integer maxDuration, @Param("accessModifier") AccessModifier accessModifier,
			Pageable pageable);
}
