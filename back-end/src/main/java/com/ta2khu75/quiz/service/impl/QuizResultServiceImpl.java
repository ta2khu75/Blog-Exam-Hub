package com.ta2khu75.quiz.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.QuestionResponse;
import com.ta2khu75.quiz.model.response.QuizResponse;
import com.ta2khu75.quiz.mapper.QuizResultMapper;
import com.ta2khu75.quiz.model.QuestionType;
import com.ta2khu75.quiz.model.QuizResultMode;
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
import com.ta2khu75.quiz.service.util.RedisUtil;
import com.ta2khu75.quiz.service.util.RedisUtil.NameModel;
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
	private final RedisUtil redisUtil;

	public QuizResultServiceImpl(QuizResultRepository repository, QuizResultMapper mapper,
			QuizRepository examRepository, QuestionRepository questionRepository, AnswerRepository answerRepository,
			UserAnswerRepository userAnswerRepository, AccountRepository accountRepository, RedisUtil redisUtil) {
		super(repository, mapper);
		this.examRepository = examRepository;
		this.questionRepository= questionRepository;
		this.answerRepository = answerRepository;
		this.userAnswerRepository = userAnswerRepository;
		this.accountRepository = accountRepository;
		this.redisUtil = redisUtil;
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
				boolean isCorrect;
				if(question.getQuestionType().equals(QuestionType.SINGLE_CHOICE)) {
					isCorrect = checkSingleChoice(questionAnswers, answerUserRequest.getAnswerIds());
				}else {
					isCorrect = checkMultiChoice(questionAnswers, answerUserRequest.getAnswerIds());
				}
				saveUserAnswer(quizResult, question,isCorrect, questionAnswers, answerUserRequest.getAnswerIds());
				totalScore += isCorrect ? 1 : 0;
			}
		}

		int correctCount = (int) totalScore;
		quizResult.setCorrectCount(correctCount);

		// Tính điểm cho một lần
		float averageScore = totalScore / questions.size();
		quizResult.setPoint((float) Math.round(averageScore * 10)); // Đã thay đổi để đơn giản hóa
	}

	private void saveUserAnswer(QuizResult quizResult, Question question, boolean isCorrect, List<Answer> answers, Set<Long> answerIds) {
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setQuizResult(quizResult);
		userAnswer.setQuestion(question);
		userAnswer.setCorrect(isCorrect);
		List<Answer> answerList = answers.stream().filter(answer -> answerIds.contains(answer.getId())).toList();
		userAnswer.setAnswers(answerList);
		userAnswerRepository.save(userAnswer);
	}

	private boolean checkSingleChoice (List<Answer> answers, Set<Long> answerIds) {
		Long answerId = answerIds.iterator().next();
		return answers.stream().filter(answer -> answer.getId().equals(answerId) && answer.isCorrect()).findFirst()
				.map(answer -> true).orElse(false);
	}

	private boolean checkMultiChoice(List<Answer> answers, Set<Long> answerIds) {
		Set<Long> correctAnswers = answers.stream().filter(Answer::isCorrect).map(Answer::getId)
				.collect(Collectors.toSet());

		long correctSelected = answerIds.stream().filter(correctAnswers::contains).count();
		long incorrectSelected = answerIds.stream().filter(answerId -> !correctAnswers.contains(answerId)).count();

		if (correctSelected == correctAnswers.size() && incorrectSelected == 0) {
			return true;
		} else {
			return false;
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
		redisUtil.delete(NameModel.QUIZ_RESULT_RESPONSE, quizResult.getAccount().getId()+quizResult.getQuiz().getId());
		return mapper.toDetailResponse(repository.save(quizResult));
	}

	@Override
	public QuizResultResponse readDetail(String id) {
		QuizResult quizResult = this.find(id);
		if(quizResult.getQuiz().getQuizResultMode().equals(QuizResultMode.ANSWER_VISIBLE)) {
			return mapper.toDetailResponse(quizResult);
		}else if(quizResult.getQuiz().getQuizResultMode().equals(QuizResultMode.QUESTION_RESULT_VISIBLE)) {
			return mapper.toResultResponse(quizResult);
		}
		return mapper.toResultResponse(quizResult);
	}

	@Override
	public QuizResultResponse read(String quizId) {
		String accountId = SecurityUtil.getCurrentUserLogin();
		QuizResultResponse response= redisUtil.read(NameModel.QUIZ_RESULT_RESPONSE, accountId+quizId, QuizResultResponse.class);
//		Optional<QuizResult> quizResult = repository
//				.findByAccountIdAndQuizIdAndEndTimeAfterAndUpdatedAtIsNull(accountId, quizId, Instant.now());
		if (response != null) {
			return response; 
		}
		return null;
	}

	@Override
	public QuizResultResponse create(String quizId) {
		String accountId= SecurityUtil.getCurrentUserLogin();
		Account account = FunctionUtil.findOrThrow(accountId, Account.class,
				accountRepository::findById);
		Quiz quiz= FunctionUtil.findOrThrow(quizId, Quiz.class, examRepository::findById);
		QuizResult quizResult = QuizResult.builder().account(account).quiz(quiz)
				.endTime(Instant.now().plusSeconds(quiz.getDuration() * 60L).plusSeconds(30)).build();
		QuizResultResponse response=mapper.toResponse(repository.save(quizResult));
		if(quiz.isShuffleQuestion()) {
			QuizResponse quizResponse = response.getQuiz();
			List<QuestionResponse> questions = new ArrayList<>(quizResponse.getQuestions());
			questions.stream().forEach(question->{
				if(question.isShuffleAnswer()) {
					List<AnswerResponse> answers = question.getAnswers();
					Collections.shuffle(answers);
					question.setAnswers(answers);
				}
			});
			Collections.shuffle(questions);
			quizResponse.setQuestions(questions);
		}
		redisUtil.create(NameModel.QUIZ_RESULT_RESPONSE, accountId+quizId, response);
		return response;
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
