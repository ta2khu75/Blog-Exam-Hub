package com.ta2khu75.quiz.service.impl;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.request.QuizRequest;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;
import com.ta2khu75.quiz.enviroment.FolderEnv;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.ExamMapper;
import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.ExamStatus;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Exam;
import com.ta2khu75.quiz.model.entity.ExamCategory;
import com.ta2khu75.quiz.model.entity.ExamResult;
import com.ta2khu75.quiz.model.entity.Quiz;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.ExamCategoryRepository;
import com.ta2khu75.quiz.repository.ExamHistoryRepository;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.service.ExamService;
import com.ta2khu75.quiz.service.QuizService;
import com.ta2khu75.quiz.service.util.CloudinaryUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@Validated
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamServiceImpl implements ExamService {
	ExamRepository repository;
	AccountRepository accountRepository;
	ExamMapper mapper;
	ExamCategoryRepository examCategoryRepository;
	ExamHistoryRepository examHistoryRepository;
	QuizService quizService;
	CloudinaryUtil cloudinaryService;

	private ExamCategory findExamCategoryById(Long id) {
		return examCategoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam category with id: " + id));
	}

	private void saveFile(Exam exam, MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			Map map = cloudinaryService.uploadFile(file, FolderEnv.EXAM_FOLDER);
			exam.setImagePath((String) map.get("url"));
		}
	}

	private Exam findById(String id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Could not found exam with id: " + id));
	}

	@Override
	@Transactional
	@Validated(value = { Default.class })
	public ExamResponse create(@Valid ExamRequest examRequest, MultipartFile file) throws IOException {
		Exam exam = mapper.toEntity(examRequest);
		this.saveFile(exam, file);
		exam.setExamCategory(this.findExamCategoryById(examRequest.getExamCategoryId()));
		exam.setAuthor(accountRepository
				.findByEmail(SecurityUtil.getCurrentUserLogin()
						.orElseThrow(() -> new NotFoundException("Could not find account")))
				.orElseThrow(() -> new NotFoundException("Could not find account")));
		Exam examSaved = repository.save(exam);
		examRequest.getQuizzes().forEach(quiz -> {
			quiz.setExam(examSaved);
			quizService.create(quiz);
		});
		return mapper.toResponse(repository.save(exam));
	}

	@Override
	@Transactional
	@Validated(value = { Default.class })
	public ExamResponse update(String id, @Valid ExamRequest examRequest, MultipartFile file) throws IOException {
		Exam exam = this.findById(id);
		Map<Long, QuizRequest> requestQuizMap = examRequest.getQuizzes().stream().filter(quiz -> quiz.getId() != null)
				.collect(Collectors.toMap(QuizRequest::getId, Function.identity()));
		if (ExamStatus.NOT_COMPLETED.equals(exam.getExamStatus())) {
			Iterator<Quiz> quizIterable = exam.getQuizzes().iterator();
			while (quizIterable.hasNext()) {
				Quiz existingQuiz = quizIterable.next();
				QuizRequest quizRequest = requestQuizMap.get(existingQuiz.getId());
				if (quizRequest != null) {
					quizService.update(quizRequest.getId(), quizRequest);
				} else {
					quizIterable.remove();
					quizService.delete(existingQuiz.getId());
				}
			}
			examRequest.getQuizzes().stream().filter(quiz -> quiz.getId() == null).forEach(quiz -> {
				quiz.setExam(exam);
				quizService.create(quiz);
			});
		}
		mapper.update(examRequest, exam);
		this.saveFile(exam, file);
		if (exam.getExamCategory().getId().equals(examRequest.getExamCategoryId()))
			exam.setExamCategory(this.findExamCategoryById(examRequest.getExamCategoryId()));
		return mapper.toResponse(repository.save(exam));
	}

	@Override
//	@PostAuthorize("returnObject.author.email== authentication.name")
	public ExamResponse read(String id) {
		Exam exam = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam with id: " + id));
		return mapper.toResponse(exam);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Exam exam = this.findById(id);
		exam.setDeleted(true);
		repository.save(exam);
	}

	@Override
	public PageResponse<ExamResponse> readPage(Pageable pageable) {
		return mapper.toPageResponse(repository.findAll(pageable));
	}

	@Override
	public ExamDetailsResponse readDetail(String id) {
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
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not found email"));
		return mapper.toPageResponse(repository.findByAuthorEmail(email, pageable));
	}

	@Override
	public PageResponse<ExamResponse> readPageAccountExam(String id, Pageable pageable) {
		return mapper.toPageResponse(repository.findByAccountIdAndAccessModifier(id, AccessModifier.PUBLIC, pageable));
	}

	@Override
	public PageResponse<ExamResponse> readPageCategoryExam(Long id, Pageable pageable) {
		return mapper
				.toPageResponse(repository.findByExamCategoryIdAndAccessModifier(id, AccessModifier.PUBLIC, pageable));
	}
}
