package com.ta2khu75.quiz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.QuizCategoryMapper;
import com.ta2khu75.quiz.model.entity.QuizCategory;
import com.ta2khu75.quiz.model.request.QuizCategoryRequest;
import com.ta2khu75.quiz.model.response.QuizCategoryResponse;
import com.ta2khu75.quiz.repository.QuizCategoryRepository;
import com.ta2khu75.quiz.service.QuizCategoryService;
import com.ta2khu75.quiz.service.base.BaseService;
import com.ta2khu75.quiz.util.StringUtil;

@Service
public class QuizCategoryServiceImpl extends BaseService<QuizCategoryRepository, QuizCategoryMapper>
		implements QuizCategoryService {
	protected QuizCategoryServiceImpl(QuizCategoryRepository repository, QuizCategoryMapper mapper) {
		super(repository, mapper);
	}
private QuizCategoryResponse save(QuizCategory quizCategory) {
	QuizCategory saved = repository.save(quizCategory);
	return mapper.toResponse(saved);
}
	@Override
	@Transactional
	public QuizCategoryResponse create(QuizCategoryRequest request) {
		QuizCategory quizCategory = mapper.toEntity(request);
		quizCategory.setName(StringUtil.convertCamelCaseToReadable(quizCategory.getName()));
		return this.save(quizCategory);
	}

	@Override
	@Transactional
	public QuizCategoryResponse update(Long id, QuizCategoryRequest request) {
		QuizCategory quizCategory = this.findById(id);
		mapper.update(request, quizCategory);
		quizCategory.setName(StringUtil.convertCamelCaseToReadable(quizCategory.getName()));
		return mapper.toResponse(repository.save(quizCategory));
	}

	private QuizCategory findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found exam category with id: " + id));
	}

	@Override
	public QuizCategoryResponse read(Long id) {
		return mapper.toResponse(this.findById(id));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<QuizCategoryResponse> readAll() {
		return repository.findAll().stream().map(mapper::toResponse).toList();
	}

}
