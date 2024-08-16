package com.ta2khu75.quiz.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.entity.ExamHistory;

public interface ExamHistoryRepository extends JpaRepository<ExamHistory, Long>, JpaSpecificationExecutor<ExamHistory> {

	Optional<ExamHistory> findByAccountEmailAndExamIdAndEndTimeAfterAndLastModifiedDateIsNull(String email, Long examId, LocalDateTime now);

	@Query("SELECT e FROM ExamHistory e WHERE e.endTime < :now AND e.lastModifiedDate IS NULL")
	List<ExamHistory> findByEndTimeBeforeNow(@Param("now") LocalDateTime now);
	Page<ExamHistory> findByAccountEmailAndLastModifiedDateIsNotNull(String email, Pageable pageable);
}
