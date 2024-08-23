package com.ta2khu75.quiz.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.ExamHistory;
import com.ta2khu75.quiz.entity.request.UserAnswerRequest;
import com.ta2khu75.quiz.entity.response.ExamHistoryResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import com.ta2khu75.quiz.entity.response.details.ExamHistoryDetailsResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.ExamHistoryMapper;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.ExamHistoryRepository;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.service.ExamHistoryService;
import com.ta2khu75.quiz.service.ProfessionService;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamHistoryServiceImpl implements ExamHistoryService {
	ExamHistoryMapper mapper;
	ExamHistoryRepository repository;
	ExamRepository examRepository;
	AccountRepository accountRepository;
	ProfessionService professionService;

	@Override
	public ExamHistoryResponse readByExamId(Long id) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Optional<ExamHistory> history = repository
				.findByAccountEmailAndExamIdAndEndTimeAfterAndLastModifiedDateIsNull(email, id, LocalDateTime.now());
		if (history.isPresent()) {
			return mapper.toResponse(history.get());
		}
		Exam exam = examRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam with id: " + id));
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account with email: " + email));
		ExamHistory examHistory = ExamHistory.builder().account(account).exam(exam)
				.endTime(LocalDateTime.now().plusMinutes(exam.getTime()).plusSeconds(30)).build();
		return mapper.toResponse(repository.save(examHistory));
	}

	@Override
	public ExamHistoryDetailsResponse scoreByExamId(Long id, Long examId, UserAnswerRequest[] answerUserRequest) {
		ExamHistory examHistory = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found examHistory with id: " + id));
		examHistory.setLastModifiedDate(LocalDateTime.now());
		if (answerUserRequest.length != 0) {
			professionService.score(examHistory, examId, answerUserRequest);
		}
		return mapper.toDetailsResponse(repository.save(examHistory));
	}

	@Override
	public PageResponse<ExamHistoryResponse> readPage(Pageable pageable) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		return mapper.toPageResponse(repository.findByAccountEmailAndLastModifiedDateIsNotNull(email, pageable));
	}

	@Override
	public ExamHistoryDetailsResponse read(Long id) {
		return mapper.toDetailsResponse(repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found examHistory with id: " + id)));
	}
}
