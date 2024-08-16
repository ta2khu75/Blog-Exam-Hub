package com.ta2khu75.quiz.service.impl;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.ExamHistory;
import com.ta2khu75.quiz.entity.request.ExamRequest;
import com.ta2khu75.quiz.entity.response.ExamResponse;
import com.ta2khu75.quiz.entity.response.details.ExamDetailsResponse;
import com.ta2khu75.quiz.enviroment.FolderEnvironment;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.ExamMapper;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.ExamHistoryRepository;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.service.ExamService;
import com.ta2khu75.quiz.service.util.CloudinaryUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamServiceImpl implements ExamService {
	ExamRepository repository;
	AccountRepository accountRepository;
	
	ExamMapper mapper;
	ExamHistoryRepository examHistoryRepository;
	CloudinaryUtil cloudinaryService;

	@Override
	public ExamResponse create(@Valid ExamRequest examRequest, MultipartFile file) throws IOException {
		Exam exam = mapper.toEntity(examRequest);
		Map map = cloudinaryService.uploadFile(file, FolderEnvironment.EXAM_FOLDER);
		exam.setImagePath((String) map.get("url"));
		repository.save(exam);
		return mapper.toResponse(repository.save(exam));
	}

	@Override
	public ExamResponse update(Long id, @Valid ExamRequest examRequest, MultipartFile file) throws IOException {
		Exam exam = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam with id: " + id));
		mapper.update(examRequest, exam);
		if (file != null && !file.isEmpty()) {
			Map map = cloudinaryService.uploadFile(file, FolderEnvironment.EXAM_FOLDER);
			exam.setImagePath((String) map.get("url"));
		}
		return mapper.toResponse(repository.save(exam));
	}

	@Override
	public ExamResponse read(Long id) {
		Exam exam = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam with id: " + id));
		return mapper.toResponse(exam);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Page<ExamResponse> readPage(Pageable pageable) {
		return repository.findAll(pageable).map((exam) -> mapper.toResponse(exam));
	}

	@Override
	public ExamDetailsResponse readDetail(Long id) {
		String email=SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new NotFoundException("Could not find email"));
		Account account=accountRepository.findByEmail(email).orElseThrow(()->new NotFoundException("Could not find account with email: "+email));
		Exam exam = repository.findById(id).orElseThrow(() -> new NotFoundException("Could not found exam with id: " + id));
		ExamHistory examHistory = ExamHistory.builder().account(account).exam(exam).endTime(LocalDateTime.now().plusMinutes(exam.getTime()+1)).build();
		examHistoryRepository.save(examHistory);
		return mapper.toDetailsResponse(exam);
	}
}
