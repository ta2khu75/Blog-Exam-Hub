package com.ta2khu75.quiz.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.QuizLevel;
import com.ta2khu75.quiz.model.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, String>, JpaSpecificationExecutor<Quiz> {
	@Query("SELECT q FROM Quiz q WHERE "
			+ "(:keyword IS NULL OR q.title LIKE %:keyword% OR q.description LIKE %:keyword% OR q.author.displayName LIKE %:keyword% OR q.quizCategory.name LIKE %:keyword%) "
			+ "AND (:quizCategoryIds IS NULL OR q.quizCategory.id IN (:quizCategoryIds)) "
			+ "AND (:authorId IS NULL OR q.author.id = :authorId) "
			+ "AND (:quizLevels IS NULL OR q.quizLevel IN (:quizLevels))"
			+ "AND (:minDuration IS NULL OR q.duration >= :minDuration) "
			+ "AND (:maxDuration IS NULL OR q.duration <= :maxDuration) "
			+ "AND (:accessModifier IS NULL OR q.accessModifier = :accessModifier) ")
	Page<Quiz> searchExam(@Param("keyword") String keyword, @Param("quizCategoryIds") List<Long> quizCategoryIds,
			@Param("authorId") String authorId,
			@Param("quizLevels") List<QuizLevel> quizLevel, @Param("minDuration") Integer minDuration,
			@Param("maxDuration") Integer maxDuration, @Param("accessModifier") AccessModifier accessModifier,
			Pageable pageable);

	Page<Quiz> findByAuthorIdAndTitleContainingAndBlogIdIsNull(String authorId, String keyword, Pageable pageable);

	Set<Quiz> findByBlogId(String blogId);

	Long countByAuthorIdAndAccessModifier(String authorId, AccessModifier accessModifier);

	Long countByAuthorEmail(String authorEmail);

	Optional<Quiz> findByIdAndAuthorId(String examId, String authorId);
}
