package com.ta2khu75.quiz.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.model.request.ExamResultRequest;
import com.ta2khu75.quiz.model.request.UserAnswerRequest;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamResultDetailsResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.ExamResultMapper;
import com.ta2khu75.quiz.model.QuizType;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Answer;
import com.ta2khu75.quiz.model.entity.Exam;
import com.ta2khu75.quiz.model.entity.ExamResult;
import com.ta2khu75.quiz.model.entity.Quiz;
import com.ta2khu75.quiz.model.entity.UserAnswer;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.AnswerRepository;
import com.ta2khu75.quiz.repository.ExamResultRepository;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.repository.QuizRepository;
import com.ta2khu75.quiz.repository.UserAnswerRepository;
import com.ta2khu75.quiz.service.ExamResultService;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamResultServiceImpl implements ExamResultService {
	ExamResultMapper mapper;
	ExamResultRepository repository;
	ExamRepository examRepository;
	QuizRepository quizRepository;
	AnswerRepository answerRepository;
	UserAnswerRepository userAnswerRepository;
	AccountRepository accountRepository;

	@Override
	public ExamResultResponse readByExamId(String id) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Optional<ExamResult> history = repository.findByAccountEmailAndExamIdAndEndTimeAfterAndUpdatedAtIsNull(email,
				id, Instant.now());// IsNull(email, id, LocalDateTime.now());
		if (history.isPresent()) {
			return mapper.toResponse(history.get());
		}
		return null;
	}

	@Override
	public ExamResultDetailsResponse scoreByExamId(String id, ExamResultRequest examResultRequest) {
		ExamResult examHistory = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found examHistory with id: " + id));
		if (examResultRequest.getUserAnswers().size() != 0) {
			this.score(examHistory, examResultRequest.getUserAnswers());
		}
		return mapper.toDetailsResponse(repository.save(examHistory));
	}

	private void score(ExamResult examResult, Set<UserAnswerRequest> userAnswerRequests) {
		float totalScore = 0;

		// Truy xuất tất cả câu hỏi cho bài kiểm tra
		List<Quiz> quizzes = quizRepository.findByExamId(examResult.getExam().getId());
		Set<Long> quizIds = userAnswerRequests.stream().map(UserAnswerRequest::getQuizId).collect(Collectors.toSet());

		// Truy xuất tất cả các đáp án cho các quizId
		Map<Long, List<Answer>> answerMap = answerRepository.findByQuizIdIn(quizIds).stream()
				.collect(Collectors.groupingBy(answer -> answer.getQuiz().getId()));

		// Tạo Map từ quizId đến UserAnswerRequest
		Map<Long, UserAnswerRequest> answerUserRequestMap = userAnswerRequests.stream()
				.collect(Collectors.toMap(UserAnswerRequest::getQuizId, ar -> ar));

		// Tính điểm cho từng quiz
		for (Quiz quiz : quizzes) {
			UserAnswerRequest answerUserRequest = answerUserRequestMap.get(quiz.getId());
			if (answerUserRequest != null) {
				List<Answer> quizAnswers = answerMap.get(quiz.getId());

				// Bỏ qua nếu không có đáp án cho câu hỏi
				if (quizAnswers == null)
					continue;

				saveUserAnswer(examResult, quiz, quizAnswers, answerUserRequest.getAnswerIds());

				totalScore += (quiz.getQuizType() == QuizType.SINGLE_CHOICE)
						? caculateScoreQuizSingleChoice(quizAnswers, answerUserRequest.getAnswerIds())
						: caculateScoreQuizMultiChoice(quizAnswers, answerUserRequest.getAnswerIds());
			}
		}

		int correctCount = (int) totalScore;
		examResult.setCorrectCount(correctCount);

		// Tính điểm cho một lần
		float averageScore = totalScore / quizzes.size();
		examResult.setPoint((float) Math.round(averageScore * 10)); // Đã thay đổi để đơn giản hóa
	}

//	private void score(ExamResult examHistory, Set<UserAnswerRequest> userAnswerRequests) {
//		float score = 0;
//
//		// Truy xuất tất cả câu hỏi và đáp án một lần
//		List<Quiz> quizzes = quizRepository.findByExamId(examHistory.getExam().getId());
////		Map<Long, Quiz> quizMap = quizzes.stream().collect(Collectors.toMap(Quiz::getId, q -> q));
//
//		// Lấy danh sách quizId từ các yêu cầu trả lời
//		Set<Long> quizIds = userAnswerRequests.stream().map(UserAnswerRequest::getQuizId).collect(Collectors.toSet());
//
//		// Truy xuất tất cả các đáp án cho các quizId
//		List<Answer> answers = answerRepository.findByQuizIdIn(quizIds);
//		Map<Long, List<Answer>> answerMap = answers.stream()
//				.collect(Collectors.groupingBy(answer -> answer.getQuiz().getId()));
//
//		// Tạo Map từ quizId đến AnswerUserRequest
//		Map<Long, UserAnswerRequest> answerUserRequestMap = userAnswerRequests.stream().collect(Collectors.toMap(UserAnswerRequest::getQuizId, ar -> ar));
//
//		// Tính điểm cho từng quiz
//		for (Quiz quiz : quizzes) {
//			UserAnswerRequest answerUserRequest = answerUserRequestMap.get(quiz.getId());
//			if (answerUserRequest != null) {
//				List<Answer> quizAnswers = answerMap.get(quiz.getId());
//				if (quizAnswers == null) {
//					continue; // Nếu không có đáp án cho câu hỏi, bỏ qua
//				}
//				saveUserAnswer(examHistory, quiz, quizAnswers, answerUserRequest.getAnswerIds());
//				if (quiz.getQuizType() == QuizType.SINGLE_CHOICE) {
//					score += caculateScoreQuizSingleChoice(quizAnswers, answerUserRequest.getAnswerIds());
//				} else {
//					score += caculateScoreQuizMultiChoice(quizAnswers, answerUserRequest.getAnswerIds());
//				}
//			}
//		}
//
//		examHistory.setCorrectCount((int) score);
//		// Tính điểm cho một lần
//		float num = score / quizzes.size();
//		examHistory.setPoint((float) (Math.round(num * 100) / 10));
//	}

	private void saveUserAnswer(ExamResult examHistory, Quiz quiz, List<Answer> answers, Set<Long> answerIds) {
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setExamResult(examHistory);
		userAnswer.setQuiz(quiz);
		List<Answer> answe = answers.stream().filter(answer -> answerIds.contains(answer.getId())).toList();
		userAnswer.setAnswers(answe);
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
			return 1; // Điểm tối đa cho câu hỏi
		} else {
			return 0;
//			double fractionCorrect = (double) correctSelected / correctAnswers.size();
//			double fractionIncorrect = (double) incorrectSelected / (correctAnswers.size() + 1);
//			double maxPoints = 1;
//			double points = maxPoints * fractionCorrect - (fractionIncorrect * 0.5);
//			points = Math.max(points, 0);
//			return points;
		}
	}

	@Override
	public PageResponse<ExamResultResponse> readPage(Pageable pageable) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		return mapper.toPageResponse(repository.findByAccountEmailAndUpdatedAtIsNotNull(email, pageable));
	}

	@Override
	public ExamResultDetailsResponse read(String id) {
		return mapper.toDetailsResponse(repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found examHistory with id: " + id)));
	}

	@Override
	public ExamResultResponse createByExamId(String examId) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Exam exam = examRepository.findById(examId)
				.orElseThrow(() -> new NotFoundException("Could not found exam with id: " + examId));
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account with email: " + email));
		ExamResult examResult = ExamResult.builder().account(account).exam(exam)
				.endTime(Instant.now().plusSeconds(exam.getDuration() * 60L).plusSeconds(30)).build();
		return mapper.toResponse(repository.save(examResult));
	}
}
