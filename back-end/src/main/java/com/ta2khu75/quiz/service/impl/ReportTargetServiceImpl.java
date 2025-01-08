package com.ta2khu75.quiz.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ta2khu75.quiz.mapper.ReportTargetMapper;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Blog;
import com.ta2khu75.quiz.model.entity.Exam;
import com.ta2khu75.quiz.model.entity.ReportTarget;
import com.ta2khu75.quiz.model.entity.id.ReportTargetId;
import com.ta2khu75.quiz.model.request.ReportTargetRequest;
import com.ta2khu75.quiz.model.response.ReportTargetResponse;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.BlogRepository;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.repository.ReportTargetRepository;
import com.ta2khu75.quiz.service.ReportTargetService;
import com.ta2khu75.quiz.util.FunctionUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportTargetServiceImpl implements ReportTargetService {
	ReportTargetRepository repository;
	ReportTargetMapper mapper;
	AccountRepository accountRepository;
	BlogRepository blogRepository;
	ExamRepository examRepository;

	@Override
	@Transactional
	public ReportTargetResponse create(ReportTargetRequest request) {
		String accountId = SecurityUtil.getCurrentUserLogin();
		Account account = FunctionUtil.findOrThrow(accountId, Account.class, accountRepository::findByEmail);
		switch (request.getTargetType()) {
		case BLOG: {
			FunctionUtil.findOrThrow(request.getTargetId(), Blog.class, blogRepository::findById);
			break;
		}
		case EXAM: {
			FunctionUtil.findOrThrow(request.getTargetId(), Exam.class, examRepository::findById);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + request.getTargetType());
		}

		ReportTarget reportTarget = mapper.toEntity(request);
		reportTarget.setAuthorId(account.getId());
		return mapper.toResponse(repository.save(reportTarget));
	}

	@Override
	public void delete(String id) {
		String accountId = SecurityUtil.getCurrentUserLogin();
		repository.deleteById(new ReportTargetId(accountId, id));
	}

}
