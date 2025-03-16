package com.ta2khu75.quiz.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.model.entity.ExamResult;

public interface ExamResultRepository extends JpaRepository<ExamResult, String> {

	Optional<ExamResult> findByAccountIdAndExamIdAndEndTimeAfterAndUpdatedAtIsNull(String id, String examId,
			Instant now);

	List<ExamResult> findByEndTimeBeforeAndUpdatedAtIsNull(Instant now);
	@Query("SELECT e FROM ExamResult e WHERE "
			+ "(:keyword IS NULL OR e.exam.title LIKE %:keyword% OR e.exam.description LIKE %:keyword% OR e.exam.author.displayName LIKE %:keyword% OR e.exam.examCategory.name LIKE %:keyword%) "
			+ "AND (:examCategoryIds IS NULL OR e.exam.examCategory.id IN (:examCategoryIds)) "
			+ "AND (:accountId IS NULL OR e.account.id= :accountId) "
			+ "AND (:fromDate IS NULL OR e.updatedAt >= :fromDate) AND (:toDate IS NULL OR e.updatedAt <= :toDate) ")
	Page<ExamResult> search(@Param("keyword") String keyword, 
			@Param("examCategoryIds") List<Long> examCategoryIds,
			@Param("accountId") String accountId,
			@Param("fromDate") Instant fromDate, @Param("toDate") Instant toDate,
			Pageable pageable);
//	Page<ExamResult> findByAccountIdAndUpdatedAtIsNotNull(String accountId, Pageable pageable);
}
