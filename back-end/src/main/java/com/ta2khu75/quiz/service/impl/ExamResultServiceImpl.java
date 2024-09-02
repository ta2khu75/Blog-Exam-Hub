package com.ta2khu75.quiz.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.ExamResult;
import com.ta2khu75.quiz.entity.request.UserAnswerRequest;
import com.ta2khu75.quiz.entity.response.ExamResultResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import com.ta2khu75.quiz.entity.response.details.ExamResultDetailsResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.ExamResultMapper;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.ExamHistoryRepository;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.service.ExamResultService;
import com.ta2khu75.quiz.service.ProfessionService;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamResultServiceImpl implements ExamResultService {
	ExamResultMapper mapper;
	ExamHistoryRepository repository;
	ExamRepository examRepository;
	AccountRepository accountRepository;
	ProfessionService professionService;

	@Override
	public ExamResultResponse readByExamId(Long id) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Optional<ExamResult> history = repository
				.findByAccountEmailAndExamIdAndEndTimeAfterAndLastModifiedDateIsNull(email, id, LocalDateTime.now());
		if (history.isPresent()) {
			return mapper.toResponse(history.get());
		}
		return null;
	}

	@Override
	public ExamResultDetailsResponse scoreByExamId(Long id, Long examId, UserAnswerRequest[] answerUserRequest) {
		ExamResult examHistory = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found examHistory with id: " + id));
		examHistory.setLastModifiedDate(LocalDateTime.now());
		if (answerUserRequest.length != 0) {
			professionService.score(examHistory, examId, answerUserRequest);
		}
		return mapper.toDetailsResponse(repository.save(examHistory));
	}

	@Override
	public PageResponse<ExamResultResponse> readPage(Pageable pageable) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		return mapper.toPageResponse(repository.findByAccountEmailAndLastModifiedDateIsNotNull(email, pageable));
	}

	@Override
	public ExamResultDetailsResponse read(Long id) {
		return mapper.toDetailsResponse(repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found examHistory with id: " + id)));
	}

	@Override
	public ExamResultResponse createByExamId(Long examId) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Exam exam = examRepository.findById(examId)
				.orElseThrow(() -> new NotFoundException("Could not found exam with id: " + examId));
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account with email: " + email));
		ExamResult examResult = ExamResult.builder().account(account).exam(exam)
				.endTime(LocalDateTime.now().plusMinutes(exam.getTime()).plusSeconds(30)).build();
		return mapper.toResponse(examResult);
	}
}
