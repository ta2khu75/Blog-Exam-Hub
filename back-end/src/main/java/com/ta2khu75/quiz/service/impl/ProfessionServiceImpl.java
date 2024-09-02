package com.ta2khu75.quiz.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.entity.Answer;
import com.ta2khu75.quiz.entity.ExamHistory;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.QuizType;
import com.ta2khu75.quiz.entity.UserAnswer;
import com.ta2khu75.quiz.entity.request.UserAnswerRequest;
import com.ta2khu75.quiz.repository.AnswerRepository;
import com.ta2khu75.quiz.repository.QuizRepository;
import com.ta2khu75.quiz.repository.UserAnswerRepository;
import com.ta2khu75.quiz.service.ProfessionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfessionServiceImpl implements ProfessionService {
	AnswerRepository answerRepository;
	QuizRepository quizRepository;
	UserAnswerRepository userAnswerRepository;

	@Override
	public void score(ExamHistory examHistory, Long examId, UserAnswerRequest[] answerUserRequests) {
		float score = 0;

		// Truy xuất tất cả câu hỏi và đáp án một lần
		List<Quiz> quizzes = quizRepository.findByExamId(examId);
		Map<Long, Quiz> quizMap = quizzes.stream().collect(Collectors.toMap(Quiz::getId, q -> q));

		// Lấy danh sách quizId từ các yêu cầu trả lời
		Set<Long> quizIds = Arrays.stream(answerUserRequests).map(UserAnswerRequest::getQuizId)
				.collect(Collectors.toSet());

		// Truy xuất tất cả các đáp án cho các quizId
		List<Answer> answers = answerRepository.findByQuizIdIn(quizIds);
		Map<Long, List<Answer>> answerMap = answers.stream()
				.collect(Collectors.groupingBy(answer -> answer.getQuiz().getId()));

		// Tạo Map từ quizId đến AnswerUserRequest
		Map<Long, UserAnswerRequest> answerUserRequestMap = Arrays.stream(answerUserRequests)
				.collect(Collectors.toMap(UserAnswerRequest::getQuizId, ar -> ar));

		// Tính điểm cho từng quiz
		for (Quiz quiz : quizzes) {
			UserAnswerRequest answerUserRequest = answerUserRequestMap.get(quiz.getId());
			if (answerUserRequest != null) {
				List<Answer> quizAnswers = answerMap.get(quiz.getId());
				if (quizAnswers == null) {
					continue; // Nếu không có đáp án cho câu hỏi, bỏ qua
				}
				saveUserAnswer(examHistory, quiz, quizAnswers, answerUserRequest.getAnswerIds());
				if (quiz.getQuizType() == QuizType.SINGLE_CHOICE) {
					score += caculateScoreQuizSingleChoice(quizAnswers, answerUserRequest.getAnswerIds());
				} else {
					score += caculateScoreQuizMultiChoice(quizAnswers, answerUserRequest.getAnswerIds());
				}
			}
		}

		examHistory.setCorrectCount((int) score);
		// Tính điểm cho một lần
		float num = score / quizzes.size();
		examHistory.setPoint((float) (Math.round(num * 100) / 10));
	}

	private void saveUserAnswer(ExamHistory examHistory, Quiz quiz, List<Answer> answers, Set<Long> answerIds) {
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setExamHistory(examHistory);
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
}
