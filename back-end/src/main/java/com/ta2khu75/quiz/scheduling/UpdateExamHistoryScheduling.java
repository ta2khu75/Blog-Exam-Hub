package com.ta2khu75.quiz.scheduling;

import java.time.Instant;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ta2khu75.quiz.model.entity.ExamResult;
import com.ta2khu75.quiz.repository.ExamResultRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateExamHistoryScheduling {
	private final ExamResultRepository repository;
	
	@Scheduled(fixedRate = 15000)
	public void updateEndTime() {
		List<ExamResult> list = repository.findByEndTimeBeforeNow(Instant.now());
		repository.saveAll(list.stream().map(e->{e.setPoint(0f);return e;}).toList());
	}
	
}
