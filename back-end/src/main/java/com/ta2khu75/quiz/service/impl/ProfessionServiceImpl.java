package com.ta2khu75.quiz.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.entity.Answer;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.QuizType;
import com.ta2khu75.quiz.entity.request.AnswerUserRequest;
import com.ta2khu75.quiz.repository.AnswerRepository;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.repository.QuizRepository;
import com.ta2khu75.quiz.service.ProfessionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfessionServiceImpl implements ProfessionService {
	ExamRepository examRepository;
	AnswerRepository answerRepository;
	QuizRepository quizRepository;

	@Override
	public Double score(Long examId, AnswerUserRequest[] answerUserRequests) {
        double score = 0;

        // Truy xuất tất cả câu hỏi và đáp án một lần
        List<Quiz> quizzes = quizRepository.findByExamId(examId);
        Map<Long, Quiz> quizMap = quizzes.stream().collect(Collectors.toMap(Quiz::getId, q -> q));

        // Lấy danh sách quizId từ các yêu cầu trả lời
        Set<Long> quizIds = Arrays.stream(answerUserRequests)
            .map(AnswerUserRequest::quizId)
            .collect(Collectors.toSet());

        // Truy xuất tất cả các đáp án cho các quizId
        List<Answer> answers = answerRepository.findByQuizIdIn(quizIds);
        Map<Long, List<Answer>> answerMap = answers.stream()
            .collect(Collectors.groupingBy(answer -> answer.getQuiz().getId()));

        // Tạo Map từ quizId đến AnswerUserRequest
        Map<Long, AnswerUserRequest> answerUserRequestMap = Arrays.stream(answerUserRequests)
            .collect(Collectors.toMap(AnswerUserRequest::quizId, ar -> ar));

        // Tính điểm cho từng quiz
        for (Quiz quiz : quizzes) {
            AnswerUserRequest answerUserRequest = answerUserRequestMap.get(quiz.getId());
            if (answerUserRequest != null) {
                List<Answer> quizAnswers = answerMap.get(quiz.getId());
                if (quizAnswers == null) {
                    continue; // Nếu không có đáp án cho câu hỏi, bỏ qua
                }

                if (quiz.getQuizType() == QuizType.SINGLE_CHOICE) {
                    score += caculateScoreQuizSingleChoice(quizAnswers, answerUserRequest.answers()[0]);
                } else {
                    score += caculateScoreQuizMultiChoice(quizAnswers, answerUserRequest.answers());
                }
            }
        }

        return score * 10 / quizzes.size();
    }

    private double caculateScoreQuizSingleChoice(List<Answer> answers, Long answerId) {
        return answers.stream()
            .filter(answer -> answer.getId().equals(answerId) && answer.getCorrect())
            .findFirst()
            .map(answer -> 1.0)
            .orElse(0.0);
    }

    private double caculateScoreQuizMultiChoice(List<Answer> answers, Long[] answerIds) {
        Set<Long> correctAnswers = answers.stream()
            .filter(Answer::getCorrect)
            .map(Answer::getId)
            .collect(Collectors.toSet());
        Set<Long> userAnswers = Set.of(answerIds);

        long correctSelected = userAnswers.stream()
            .filter(correctAnswers::contains)
            .count();
        long incorrectSelected = userAnswers.stream()
            .filter(answerId -> !correctAnswers.contains(answerId))
            .count();

        if (correctSelected == correctAnswers.size() && incorrectSelected == 0) {
            return 1; // Điểm tối đa cho câu hỏi
        } else {
            double fractionCorrect = (double) correctSelected / correctAnswers.size();
            double fractionIncorrect = (double) incorrectSelected / (correctAnswers.size() + 1);
            double maxPoints = 1;
            double points = maxPoints * fractionCorrect - (fractionIncorrect * 0.5);
            points = Math.max(points, 0);
            return points;
        }
    }
    
//	public Double score(Long examId, AnswerUserRequest[] answerUserRequests) {
//		double score = 0;
//		List<Quiz> quizzes = quizRepository.findByExamId(examId);
//		Map<Long, Quiz> quizMap = quizzes.stream().collect(Collectors.toMap(Quiz::getId, q -> q));
//		Map<Long, Long[]> answerUserRequestMap = Arrays.stream(answerUserRequests)
//			    .collect(Collectors.toMap(AnswerUserRequest::quizId, ar -> ar.answers()));
//		for (Map.Entry<Long, Long[]> entry : answerUserRequestMap.entrySet()) {
//			if(quizMap.containsKey(entry.getKey())) {
//				Quiz quiz = quizMap.get(entry.getKey());
//				if (quiz.getQuizType() == QuizType.SINGLE_CHOICE) {
//					score+=caculateScoreQuizSingleChoice(quiz.getId(), entry.getValue()[0]);
//				} else {
//					score+=caculateScoreQuizMultiChoice(quiz.getId(), entry.getValue());
//				}
//				
//			}
//		}
//		return score * 10 / quizzes.size();
//	}
//	private double caculateScoreQuizSingleChoice(Long quizId,Long answerId) {
//		Answer answer = answerRepository.findByQuizIdAndCorrectTrue(quizId).getFirst();
//		if (answer.getId().equals(answerId) && answer.getCorrect()) {
//			return 1;
//		}return 0;
//	}
//	private double caculateScoreQuizMultiChoice(Long quizId, Long[] answerIds) {
//		Set<Long> correctAnswers = answerRepository.findByQuizIdAndCorrectTrue(quizId).stream()
//				.map(Answer::getId).collect(Collectors.toSet());
//		Set<Long> userAnswers = Set.of(answerIds);
//		long correctSelected = userAnswers.stream().filter(correctAnswers::contains).count();
//		long incorrectSelected = userAnswers.stream()
//				.filter(answerId -> !correctAnswers.contains(answerId)).count();
//		// Nếu người dùng chọn tất cả các đáp án đúng và không có đáp án sai
//		if (correctSelected == correctAnswers.size() && incorrectSelected == 0) {
//			// Cộng điểm tối đa cho câu hỏi
//			return 1; // Giả sử điểm tối đa cho câu hỏi là 1
//		} else {
//			// Tính điểm dựa trên số đáp án đúng và sai
//			double fractionCorrect = (double) correctSelected / correctAnswers.size();
//			// Tổng số đáp án có thể sai
//			double fractionIncorrect = (double) incorrectSelected / (correctAnswers.size() + 1);
//			// Giả sử có điểm tối đa cho câu hỏi
//			double maxPoints = 1;
//			// Ví dụ trừ 0.5 điểm cho mỗi đáp án sai
//			double points = maxPoints * fractionCorrect - (fractionIncorrect * 0.5); 
//			points = Math.max(points, 0); // Điểm không thể âm
//
//			return points;
//		}
//	}
}
