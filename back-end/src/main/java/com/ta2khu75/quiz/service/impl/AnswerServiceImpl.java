package com.ta2khu75.quiz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import com.ta2khu75.quiz.model.request.AnswerRequest;
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.AnswerMapper;
import com.ta2khu75.quiz.model.CreateGroup;
import com.ta2khu75.quiz.model.UpdateGroup;
import com.ta2khu75.quiz.model.entity.Answer;
import com.ta2khu75.quiz.repository.AnswerRepository;
import com.ta2khu75.quiz.repository.UserAnswerRepository;
import com.ta2khu75.quiz.service.AnswerService;

import jakarta.validation.groups.Default;

@Service
@Validated
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerServiceImpl implements AnswerService {
	AnswerRepository repository;
	AnswerMapper mapper;

	@Override
	@Validated({ Default.class, CreateGroup.class }) 
	public AnswerResponse create( AnswerRequest request) {
		Answer answer = mapper.toEntity(request);
		return this.save(answer);
	}

	private Answer findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Couldn't find answer with id " + id));
	}

	private AnswerResponse save(Answer answer) {
		return mapper.toResponse(repository.save(answer));
	}

	@Override
	@Validated({ Default.class, UpdateGroup.class })
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
		return repository.findByQuizId(id).stream().map(mapper::toResponse).toList();
	}

	@Override
	public void deleteByQuizId(Long id) {
		repository.deleteByQuizId(id);
		
	}
}