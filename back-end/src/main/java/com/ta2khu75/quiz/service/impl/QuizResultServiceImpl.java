package com.ta2khu75.quiz.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.model.request.QuizResultRequest;
import com.ta2khu75.quiz.model.request.search.QuizResultSearch;
import com.ta2khu75.quiz.model.request.UserAnswerRequest;
import com.ta2khu75.quiz.model.response.QuizResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.QuizResultMapper;
import com.ta2khu75.quiz.model.QuestionType;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Answer;
import com.ta2khu75.quiz.model.entity.Quiz;
import com.ta2khu75.quiz.model.entity.QuizResult;
import com.ta2khu75.quiz.model.entity.Question;
import com.ta2khu75.quiz.model.entity.UserAnswer;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.AnswerRepository;
import com.ta2khu75.quiz.repository.QuizResultRepository;
import com.ta2khu75.quiz.repository.QuizRepository;
import com.ta2khu75.quiz.repository.QuestionRepository;
import com.ta2khu75.quiz.repository.UserAnswerRepository;
import com.ta2khu75.quiz.service.QuizResultService;
import com.ta2khu75.quiz.service.base.BaseService;
import com.ta2khu75.quiz.util.FunctionUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

@Service
public class QuizResultServiceImpl extends BaseService<QuizResultRepository, QuizResultMapper>
		implements QuizResultService {
	private final QuizRepository examRepository;
	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	private final UserAnswerRepository userAnswerRepository;
	private final AccountRepository accountRepository;

	public QuizResultServiceImpl(QuizResultRepository repository, QuizResultMapper mapper,
			QuizRepository examRepository, QuestionRepository questionRepository, AnswerRepository answerRepository,
			UserAnswerRepository userAnswerRepository, AccountRepository accountRepository) {
		super(repository, mapper);
		this.examRepository = examRepository;
		this.questionRepository= questionRepository;
		this.answerRepository = answerRepository;
		this.userAnswerRepository = userAnswerRepository;
		this.accountRepository = accountRepository;
	}

	private void scoreExam(QuizResult quizResult, Set<UserAnswerRequest> userAnswerRequests) {
		float totalScore = 0;

		// Truy xuất tất cả câu hỏi cho bài kiểm tra
		List<Question> questions= questionRepository.findByQuizId(quizResult.getQuiz().getId());
		Set<Long> questionIds = userAnswerRequests.stream().map(UserAnswerRequest::getQuestionId).collect(Collectors.toSet());

		// Truy xuất tất cả các đáp án cho các quizId
		Map<Long, List<Answer>> answerMap = answerRepository.findByQuestionIdIn(questionIds).stream()
				.collect(Collectors.groupingBy(answer -> answer.getQuestion().getId()));

		// Tạo Map từ quizId đến UserAnswerRequest
		Map<Long, UserAnswerRequest> answerUserRequestMap = userAnswerRequests.stream()
				.collect(Collectors.toMap(UserAnswerRequest::getQuestionId, ar -> ar));

		// Tính điểm cho từng quiz
		for (Question question: questions) {
			UserAnswerRequest answerUserRequest = answerUserRequestMap.get(question.getId());
			if (answerUserRequest != null) {
				List<Answer> questionAnswers = answerMap.get(question.getId());

				// Bỏ qua nếu không có đáp án cho câu hỏi
				if (questionAnswers == null)
					continue;

				saveUserAnswer(quizResult, question, questionAnswers, answerUserRequest.getAnswerIds());

				totalScore += (question.getQuestionType().equals(QuestionType.SINGLE_CHOICE) )
						? caculateScoreQuizSingleChoice(questionAnswers, answerUserRequest.getAnswerIds())
						: caculateScoreQuizMultiChoice(questionAnswers, answerUserRequest.getAnswerIds());
			}
		}

		int correctCount = (int) totalScore;
		quizResult.setCorrectCount(correctCount);

		// Tính điểm cho một lần
		float averageScore = totalScore / questions.size();
		quizResult.setPoint((float) Math.round(averageScore * 10)); // Đã thay đổi để đơn giản hóa
	}

	private void saveUserAnswer(QuizResult quizResult, Question question, List<Answer> answers, Set<Long> answerIds) {
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setQuizResult(quizResult);
		List<Answer> answerList = answers.stream().filter(answer -> answerIds.contains(answer.getId())).toList();
		userAnswer.setAnswers(answerList);
		userAnswer.setQuestion(question);
		userAnswerRepository.save(userAnswer);
	}

	private double caculateScoreQuizSingleChoice(List<Answer> answers, Set<Long> answerIds) {
		Long answerId = answerIds.iterator().next();
		return answers.stream().filter(answer -> answer.getId().equals(answerId) && answer.getCorrect()).findFirst()
				.map(answer -> 1.0).orElse(0.0);
	}

	private double caculateScoreQuizMultiChoice(List<Answer> answers, Set<Long> answerIds) {
		Set<Long> correctAnswers = answers.stream().filter(Answer::getCorrect).map(Answer::getId)
				.collect(Collectors.toSet());

		long correctSelected = answerIds.stream().filter(correctAnswers::contains).count();
		long incorrectSelected = answerIds.stream().filter(answerId -> !correctAnswers.contains(answerId)).count();

		if (correctSelected == correctAnswers.size() && incorrectSelected == 0) {
			return 1;
		} else {
			return 0;
		}
	}
	private QuizResult find(String id) {
	return FunctionUtil.findOrThrow(id, QuizResult.class, repository::findById);	
	}

	@Override
	public QuizResultResponse update(String id, QuizResultRequest quizResultRequest) {
		QuizResult quizResult= this.find(id);
		if (!quizResultRequest.getUserAnswers().isEmpty()) {
			this.scoreExam(quizResult, quizResultRequest.getUserAnswers());
		}
		return mapper.toDetailResponse(repository.save(quizResult));
	}

	@Override
	public QuizResultResponse readDetail(String id) {
		return mapper.toDetailResponse(this.find(id));
	}

	@Override
	public QuizResultResponse read(String quizId) {
		String accountId = SecurityUtil.getCurrentUserLogin();
		Optional<QuizResult> quizResult = repository
				.findByAccountIdAndQuizIdAndEndTimeAfterAndUpdatedAtIsNull(accountId, quizId, Instant.now());
		if (quizResult.isPresent()) {
			return mapper.toResponse(quizResult.get());
		}
		return null;
	}

	@Override
	public QuizResultResponse create(String quizId) {
		Account account = FunctionUtil.findOrThrow(SecurityUtil.getCurrentUserLogin(), Account.class,
				accountRepository::findById);
		Quiz quiz= FunctionUtil.findOrThrow(quizId, Quiz.class, examRepository::findById);
		QuizResult quizResult = QuizResult.builder().account(account).quiz(quiz)
				.endTime(Instant.now().plusSeconds(quiz.getDuration() * 60L).plusSeconds(30)).build();
		return mapper.toResponse(repository.save(quizResult));
	}

	@Override
	public PageResponse<QuizResultResponse> search(QuizResultSearch examResultSearch) {
		Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt");
		Pageable pageable = PageRequest.of(examResultSearch.getPage()-1, examResultSearch.getSize(), sort);
		String accountId = SecurityUtil.getCurrentUserLogin();
		Page<QuizResult> page=repository.search(examResultSearch.getKeyword(),
				examResultSearch.getExamCategoryIds(), accountId, examResultSearch.getFromDate(), examResultSearch.getToDate(), 
				pageable);
		return mapper.toPageResponse(page);
	}

}
