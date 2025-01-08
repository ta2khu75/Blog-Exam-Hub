package com.ta2khu75.quiz.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ta2khu75.quiz.model.entity.ExamResult;

public interface ExamResultRepository extends JpaRepository<ExamResult, String> {

	Optional<ExamResult> findByAccountIdAndExamIdAndEndTimeAfterAndUpdatedAtIsNull(String id, String examId,
			Instant now);

	List<ExamResult> findByEndTimeBeforeAndUpdatedAtIsNull(Instant now);

	Page<ExamResult> findByAccountIdAndUpdatedAtIsNotNull(String accountId, Pageable pageable);
}
