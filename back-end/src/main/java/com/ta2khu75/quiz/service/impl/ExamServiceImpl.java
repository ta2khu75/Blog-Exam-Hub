package com.ta2khu75.quiz.service.impl;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;
import com.ta2khu75.quiz.enviroment.FolderEnv;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.ExamMapper;
import com.ta2khu75.quiz.model.entity.AccessModifier;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Exam;
import com.ta2khu75.quiz.model.entity.ExamCategory;
import com.ta2khu75.quiz.model.entity.ExamResult;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.ExamCategoryRepository;
import com.ta2khu75.quiz.repository.ExamHistoryRepository;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.service.ExamService;
import com.ta2khu75.quiz.service.util.CloudinaryUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamServiceImpl implements ExamService {
	ExamRepository repository;
	AccountRepository accountRepository;
	ExamMapper mapper;
	ExamCategoryRepository examCategoryRepository;
	ExamHistoryRepository examHistoryRepository;
	CloudinaryUtil cloudinaryService;

	@Override
	@Transactional
	public ExamResponse create(@Valid ExamRequest examRequest, MultipartFile file) throws IOException {
		Exam exam = mapper.toEntity(examRequest);
		Map map = cloudinaryService.uploadFile(file, FolderEnv.EXAM_FOLDER);
		exam.setImagePath((String) map.get("url"));
		exam.setExamCategory(this.findExamCategoryById(examRequest.getExamCategoryId()));
		exam.setAccount(accountRepository
				.findByEmail(SecurityUtil.getCurrentUserLogin()
						.orElseThrow(() -> new NotFoundException("Could not find account")))
				.orElseThrow(() -> new NotFoundException("Could not find account")));
		repository.save(exam);
		return mapper.toResponse(repository.save(exam));
	}

	private ExamCategory findExamCategoryById(Long id) {
		return examCategoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam category with id: " + id));
	}

	@Override
	@Transactional
	public ExamResponse update(Long id, @Valid ExamRequest examRequest, MultipartFile file) throws IOException {
		Exam exam = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam with id: " + id));
		mapper.update(examRequest, exam);
		if (file != null && !file.isEmpty()) {
			Map map = cloudinaryService.uploadFile(file, FolderEnv.EXAM_FOLDER);
			exam.setImagePath((String) map.get("url"));
			if (exam.getExamCategory().getId() != examRequest.getExamCategoryId())
				exam.setExamCategory(this.findExamCategoryById(examRequest.getExamCategoryId()));
		}
		return mapper.toResponse(repository.save(exam));
	}

	@Override
//	@PostAuthorize("returnObject.author.email== authentication.name")
	public ExamResponse read(Long id) {
		Exam exam = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam with id: " + id));
		return mapper.toResponse(exam);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public PageResponse<ExamResponse> readPage(Pageable pageable) {
		return mapper.toPageResponse(repository.findAll(pageable));
	}

	@Override
	public ExamDetailsResponse readDetail(Long id) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account with email: " + email));
		Exam exam = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam with id: " + id));
		ExamResult examHistory = ExamResult.builder().account(account).exam(exam)
				.endTime(LocalDateTime.now().plusMinutes(exam.getDuration() + 1)).build();
		examHistoryRepository.save(examHistory);
		log.info(examHistory.toString());
		return mapper.toDetailsResponse(exam);
	}

	@Override
	public PageResponse<ExamResponse> readPageMyExam(Pageable pageable) {
		String email = SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new NotFoundException("Could not found email"));
		return mapper.toPageResponse(repository.findByAccountEmail(email, pageable));
	}

	@Override
	public PageResponse<ExamResponse> readPageAccountExam(String id, Pageable pageable) {
		return mapper.toPageResponse(repository.findByAccountIdAndAccessModifier(id, AccessModifier.PUBLIC, pageable));
	}

	@Override
	public PageResponse<ExamResponse> readPageCategoryExam(Long id, Pageable pageable) {
		return mapper.toPageResponse(repository.findByExamCategoryIdAndAccessModifier(id, AccessModifier.PUBLIC, pageable));
	}
}
