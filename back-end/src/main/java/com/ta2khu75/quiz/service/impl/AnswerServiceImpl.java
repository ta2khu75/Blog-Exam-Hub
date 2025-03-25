package com.ta2khu75.quiz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.ta2khu75.quiz.model.request.AnswerRequest;
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.AnswerMapper;
import com.ta2khu75.quiz.model.entity.Answer;
import com.ta2khu75.quiz.model.group.Create;
import com.ta2khu75.quiz.model.group.Update;
import com.ta2khu75.quiz.repository.AnswerRepository;
import com.ta2khu75.quiz.service.AnswerService;
import com.ta2khu75.quiz.service.base.BaseService;

import jakarta.validation.groups.Default;

@Service
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerServiceImpl extends BaseService<AnswerRepository, AnswerMapper> implements AnswerService {
	protected AnswerServiceImpl(AnswerRepository repository, AnswerMapper mapper) {
		super(repository, mapper);
	}

	@Override
	@Validated({ Default.class, Create.class })
	public AnswerResponse create(AnswerRequest request) {
		Answer answer = mapper.toEntity(request);
		return this.save(answer);
	}

	private Answer findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Couldn't find answer with id " + id));
	}

	private AnswerResponse save(Answer answer) {
		Answer saved= repository.save(answer);
		return mapper.toResponse(saved);
	}

	@Override
	@Validated({ Default.class, Update.class })
	public AnswerResponse update(Long id, AnswerRequest request) {
		Answer answer = findById(id);
		mapper.update(request, answer);
		return save(answer);
	}

	@Override
	public AnswerResponse read(Long id) {
		return mapper.toResponse(findById(id));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<AnswerResponse> readAllByQuizId(Long id) {
		return repository.findByQuestionId(id).stream().map(mapper::toResponse).toList();
	}

	@Override
	public void deleteByQuizId(Long id) {
		repository.deleteByQuestionId(id);
	}
}