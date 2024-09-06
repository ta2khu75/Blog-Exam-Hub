package com.ta2khu75.quiz.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.model.entity.ExamResult;

public interface ExamHistoryRepository extends JpaRepository<ExamResult, Long>{

	Optional<ExamResult> findByAccountEmailAndExamIdAndEndTimeAfterAndLastModifiedDateIsNull(String email, Long examId, LocalDateTime now);

	@Query("SELECT e FROM ExamResult e WHERE e.endTime < :now AND e.lastModifiedDate IS NULL")
	List<ExamResult> findByEndTimeBeforeNow(@Param("now") LocalDateTime now);
	Page<ExamResult> findByAccountEmailAndLastModifiedDateIsNotNull(String email, Pageable pageable);
}
