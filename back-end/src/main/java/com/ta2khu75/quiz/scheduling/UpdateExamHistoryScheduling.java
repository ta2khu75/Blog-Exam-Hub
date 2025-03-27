package com.ta2khu75.quiz.scheduling;

import java.time.Instant;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ta2khu75.quiz.model.entity.QuizResult;
import com.ta2khu75.quiz.repository.QuizResultRepository;
import com.ta2khu75.quiz.service.util.RedisUtil;
import com.ta2khu75.quiz.service.util.RedisUtil.NameModel;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateExamHistoryScheduling {
	private final QuizResultRepository repository;
	private final RedisUtil redisUtil;
	@Scheduled(fixedRate = 15000)
	public void updateEndTime() {
		List<QuizResult> list = repository.findByEndTimeBeforeAndUpdatedAtIsNull(Instant.now());
		list.stream().forEach(e->redisUtil.delete(NameModel.QUIZ_RESULT_RESPONSE, e.getAccount().getId()+e.getQuiz().getId()));
		repository.saveAll(list.stream().map(e->{e.setPoint(0f); e.setCorrectCount(0);return e;}).toList());
	}
	
}
