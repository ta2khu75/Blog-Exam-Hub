package com.ta2khu75.quiz.service.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import com.ta2khu75.quiz.model.request.AnswerRequest;
import com.ta2khu75.quiz.model.request.QuizRequest;
import com.ta2khu75.quiz.model.response.QuizResponse;
import com.ta2khu75.quiz.enviroment.FolderEnv;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.QuizMapper;
import com.ta2khu75.quiz.model.CreateGroup;
import com.ta2khu75.quiz.model.UpdateGroup;
import com.ta2khu75.quiz.model.entity.Answer;
import com.ta2khu75.quiz.model.entity.Quiz;
import com.ta2khu75.quiz.repository.QuizRepository;
import com.ta2khu75.quiz.repository.UserAnswerRepository;
import com.ta2khu75.quiz.service.AnswerService;
import com.ta2khu75.quiz.service.QuizService;
import com.ta2khu75.quiz.service.util.CloudinaryUtil;

import jakarta.validation.groups.Default;

@Service
@Slf4j
@Validated
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuizServiceImpl implements QuizService {
	QuizMapper mapper;
	QuizRepository repository;
	AnswerService answerService;
	UserAnswerRepository userAnswerRepository;
	CloudinaryUtil cloudinaryService;

	private Quiz findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Could not found quiz with id " + id));
	}

	private QuizResponse save(Quiz quiz) {
		return mapper.toResponse(repository.save(quiz));
	}

	private void checkCorrectAnswer(List<AnswerRequest> answers) {
		if (!answers.stream().anyMatch(answer -> answer.isCorrect())) {
			throw new IllegalArgumentException("There is at least one correct answer");
		}
	}

	private void saveFile(Quiz quiz, MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			Map map = cloudinaryService.uploadFile(file, FolderEnv.QUIZ_FOLDER);
			quiz.setFilePath((String) map.get("url"));
		}
	}

	@Override
	@Transactional
	@Validated({ CreateGroup.class, Default.class }) // khi dung validated group thi nen valid tang method
	public QuizResponse create(QuizRequest request) {
		this.checkCorrectAnswer(request.getAnswers());
		Quiz quiz = mapper.toEntity(request);
//		saveFile(quiz, file);
		Quiz savedQuiz = repository.save(quiz);
		request.getAnswers().forEach(answer -> {
			answer.setQuiz(savedQuiz);
			answerService.create(answer);
		});
		return this.save(savedQuiz);
	}

	@Override
	@Transactional
	@Validated({ Default.class, UpdateGroup.class })
	public QuizResponse update(Long id, QuizRequest request) {
		this.checkCorrectAnswer(request.getAnswers());
		Quiz quiz = findById(id);
		Map<Long, AnswerRequest> requestAnswerMap = request.getAnswers().stream()
				.filter(answer -> answer.getId() != null)
				.collect(Collectors.toMap(AnswerRequest::getId, Function.identity()));
		Iterator<Answer> answerIterator = quiz.getAnswers().iterator();
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
			answer.setQuiz(quiz);
			answerService.create(answer);
		});
		mapper.update(request, quiz);
//		saveFile(quiz, file);
		return this.save(quiz);
	}

	@Override
	public void delete(Long id) {
		userAnswerRepository.deleteByQuizId(id);
		answerService.deleteByQuizId(id);
		repository.deleteById(id);
	}

	@Override
	public Page<QuizResponse> read(Pageable pageable) {
		return repository.findAll(pageable).map((quiz) -> mapper.toResponse(quiz));
	}

	@Override
	public QuizResponse read(Long id) {
		return mapper.toResponse(findById(id));
	}

	@Override
	public List<QuizResponse> readByExamId(String id) {
		return repository.findByExamId(id).stream().map((exam) -> mapper.toResponse(exam)).toList();
	}

	@Override
	public void deleteFile(Long id) {
		Quiz quiz = findById(id);
		quiz.setFilePath(null);
		repository.save(quiz);
	}

}
