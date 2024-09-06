package com.ta2khu75.quiz.scheduling;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ta2khu75.quiz.model.entity.ExamResult;
import com.ta2khu75.quiz.repository.ExamHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateExamHistoryScheduling {
	private final ExamHistoryRepository repository;
	
	@Scheduled(fixedRate = 15000)
	public void updateEndTime() {
		List<ExamResult> list = repository.findByEndTimeBeforeNow(LocalDateTime.now());
		repository.saveAll(list.stream().map(e->{e.setLastModifiedDate(LocalDateTime.now());return e;}).toList());
	}
	
}
