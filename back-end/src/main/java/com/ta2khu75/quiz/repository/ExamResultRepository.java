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

public interface ExamResultRepository extends JpaRepository<ExamResult, String>{

	Optional<ExamResult> findByAccountEmailAndExamIdAndEndTimeAfterAndUpdatedAtIsNull(String email, String examId, Instant now);

	@Query("SELECT e FROM ExamResult e WHERE e.endTime < :now AND e.updatedAt IS NULL")
	List<ExamResult> findByEndTimeBeforeNow(@Param("now") Instant now);
	Page<ExamResult> findByAccountEmailAndUpdatedAtIsNotNull(String email, Pageable pageable);
}
