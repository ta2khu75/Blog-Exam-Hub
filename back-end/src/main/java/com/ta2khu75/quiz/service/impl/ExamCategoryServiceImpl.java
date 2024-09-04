package com.ta2khu75.quiz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.ExamCategoryMapper;
import com.ta2khu75.quiz.model.entity.ExamCategory;
import com.ta2khu75.quiz.model.request.ExamCategoryRequest;
import com.ta2khu75.quiz.model.response.ExamCategoryResponse;
import com.ta2khu75.quiz.repository.ExamCategoryRepository;
import com.ta2khu75.quiz.service.ExamCategoryService;
import com.ta2khu75.quiz.util.StringUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamCategoryServiceImpl implements ExamCategoryService {
	ExamCategoryRepository repository;
	ExamCategoryMapper mapper;

	@Override
	public ExamCategoryResponse create(ExamCategoryRequest request) {
		ExamCategory examCategory = mapper.toEntity(request);
		examCategory.setName(StringUtil.convertCamelCaseToReadable(examCategory.getName()));
		return mapper.toResponse(repository.save(examCategory));
	}

	@Override
	public ExamCategoryResponse update(Long id, ExamCategoryRequest request) {
		ExamCategory examCategory = this.findById(id);
		mapper.update(request, examCategory);
		examCategory.setName(StringUtil.convertCamelCaseToReadable(examCategory.getName()));
		return mapper.toResponse(repository.save(examCategory));
	}

	private ExamCategory findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam category with id: " + id));
	}

	@Override
	public ExamCategoryResponse read(Long id) {
		return mapper.toResponse(this.findById(id));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<ExamCategoryResponse> readAll() {
		return repository.findAll().stream().map(mapper::toResponse).toList();
	}

}
