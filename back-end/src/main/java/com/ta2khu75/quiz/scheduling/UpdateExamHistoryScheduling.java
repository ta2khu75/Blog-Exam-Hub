package com.ta2khu75.quiz.scheduling;

import java.time.Instant;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ta2khu75.quiz.model.entity.QuizResult;
import com.ta2khu75.quiz.repository.QuizResultRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateExamHistoryScheduling {
	private final QuizResultRepository repository;
	
	@Scheduled(fixedRate = 15000)
	public void updateEndTime() {
		List<QuizResult> list = repository.findByEndTimeBeforeAndUpdatedAtIsNull(Instant.now());
		repository.saveAll(list.stream().map(e->{e.setPoint(0f); e.setCorrectCount(0);return e;}).toList());
	}
	
}
