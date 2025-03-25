package com.ta2khu75.quiz.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;

import com.ta2khu75.quiz.model.request.AnswerRequest;
import com.ta2khu75.quiz.model.request.QuestionRequest;
import com.ta2khu75.quiz.model.response.QuestionResponse;
import com.ta2khu75.quiz.exception.InvalidDataException;
import com.ta2khu75.quiz.mapper.QuestionMapper;
import com.ta2khu75.quiz.model.entity.Answer;
import com.ta2khu75.quiz.model.entity.Question;
import com.ta2khu75.quiz.model.group.Create;
import com.ta2khu75.quiz.model.group.Update;
import com.ta2khu75.quiz.repository.QuestionRepository;
import com.ta2khu75.quiz.repository.UserAnswerRepository;
import com.ta2khu75.quiz.service.AnswerService;
import com.ta2khu75.quiz.service.QuestionService;
import com.ta2khu75.quiz.service.base.BaseService;
import com.ta2khu75.quiz.util.FunctionUtil;

import jakarta.validation.groups.Default;

@Service
@Slf4j
@Validated
public class QuestionServiceImpl extends BaseService<QuestionRepository, QuestionMapper> implements QuestionService {
	private final AnswerService answerService;
	private final UserAnswerRepository userAnswerRepository;
	private Question findById(Long id) {
		return FunctionUtil.findOrThrow(id, Question.class, repository::findById);
	}

	private QuestionResponse save(Question question) {
		Question saved=repository.save(question);
		return mapper.toResponse(saved);
	}

	private void checkCorrectAnswer(List<AnswerRequest> answers) {
		if(answers.isEmpty()) {
			throw new InvalidDataException("Answers cannot be empty");
		}
		if (!answers.stream().anyMatch(answer -> answer.isCorrect())) {
			throw new InvalidDataException("There is at least one correct answer");
		}
	}

	public QuestionServiceImpl(QuestionRepository repository, QuestionMapper mapper, AnswerService answerService,
			UserAnswerRepository userAnswerRepository) {
		super(repository, mapper);
		this.answerService = answerService;
		this.userAnswerRepository = userAnswerRepository;
	}


	@Override
	@Transactional
	@Validated({ Create.class, Default.class }) // khi dung validated group thi nen valid tang method
	public QuestionResponse create(QuestionRequest request) {
		this.checkCorrectAnswer(request.getAnswers());
		Question question = mapper.toEntity(request);
//		saveFile(quiz, file);
		Question saved = repository.save(question);
		request.getAnswers().forEach(answer -> {
			answer.setQuestion(saved);
			answerService.create(answer);
		});
		return this.save(saved);
	}

	@Override
	@Transactional
	@Validated({ Default.class, Update.class })
	public QuestionResponse update(Long id, QuestionRequest request) {
		this.checkCorrectAnswer(request.getAnswers());
		Question question= findById(id);
		Map<Long, AnswerRequest> requestAnswerMap = request.getAnswers().stream()
				.filter(answer -> answer.getId() != null)
				.collect(Collectors.toMap(AnswerRequest::getId, Function.identity()));
		Iterator<Answer> answerIterator = question.getAnswers().iterator();
		while (answerIterator.hasNext()) {
			Answer existingAnswer = answerIterator.next();
			AnswerRequest answerRequest = requestAnswerMap.get(existingAnswer.getId());
			if (answerRequest != null) {
				answerService.update(existingAnswer.getId(), answerRequest);
			} else {
				answerIterator.remove();
				answerService.delete(existingAnswer.getId());
			}
		}
		request.getAnswers().stream().filter(answer -> answer.getId() == null).forEach(answer -> {
			answer.setQuestion(question);
			answerService.create(answer);
		});
		mapper.update(request, question);
//		saveFile(quiz, file);
		return this.save(question);
	}

	@Override
	public void delete(Long id) {
		userAnswerRepository.deleteByQuestionId(id);
		answerService.deleteByQuizId(id);
		repository.deleteById(id);
	}

	@Override
	public QuestionResponse read(Long id) {
		return mapper.toResponse(findById(id));
	}

//	@Override
//	public Page<QuestionResponse> read(Pageable pageable) {
//		return repository.findAll(pageable).map((quiz) -> mapper.toResponse(quiz));
//	}
//
//	@Override
//	public QuestionResponse read(Long id) {
//		return mapper.toResponse(findById(id));
//	}
//
//	@Override
//	public List<QuestionResponse> readByExamId(String id) {
//		return repository.findByExamId(id).stream().map((exam) -> mapper.toResponse(exam)).toList();
//	}
//
//	@Override
//	public void deleteFile(Long id) {
//		Question quiz = findById(id);
//		quiz.setFilePath(null);
//		repository.save(quiz);
//	}

}
