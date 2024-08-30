package com.ta2khu75.quiz.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ta2khu75.quiz.entity.Answer;
import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.request.ExamRequest;
import com.ta2khu75.quiz.entity.response.AnswerResponse;
import com.ta2khu75.quiz.entity.response.ExamResponse;
import com.ta2khu75.quiz.entity.response.details.ExamDetailsResponse;
import com.ta2khu75.quiz.entity.response.details.QuizDetaislResponse;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExamMapper {
	@Mapping(target = "author.role", source = "account.role.name")
	ExamResponse toResponse(Exam exam);

	@Mapping(target = "account", ignore = true)
	@Mapping(target = "examCategory", ignore = true)
	@Mapping(target = "examHistories", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	Exam toEntity(ExamRequest request);

	@Mapping(target = "account", ignore = true)
	@Mapping(target = "examCategory", ignore = true)
	@Mapping(target = "examHistories", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	void update(ExamRequest request, @MappingTarget Exam exam);
	@Mapping(target = "author.role", source = "account.role.name")
	ExamDetailsResponse toDetailsResponse(Exam exam);
	default QuizDetaislResponse toResponseDetails(Quiz quiz) {
		QuizDetaislResponse quizDetaislResponse = new QuizDetaislResponse();
		quizDetaislResponse.setId(quiz.getId());
		quizDetaislResponse.setQuestion(quiz.getQuestion());
		quizDetaislResponse.setFilePath(quiz.getFilePath());
		quizDetaislResponse.setQuizType(quiz.getQuizType());
		quizDetaislResponse.setAnswers(toResponseDetailsList(quiz.getAnswers()));
		return quizDetaislResponse;
	}
	default AnswerResponse toResponseDetails(Answer answer) {
		if(answer==null) return null;
		AnswerResponse response = new AnswerResponse();
		response.setId(answer.getId());
		response.setAnswer(answer.getAnswerString());
		response.setCorrect(answer.getCorrect());
		response.setQuizId(answer.getQuiz().getId());
    	return response;
    }
	default List<AnswerResponse> toResponseDetailsList(List<Answer> list) {
		if(list==null) return null;
		return list.stream().map((answer)->toResponseDetails(answer)).toList();
	}
	
//	default AccountResponse toResponse(Account account) {
//		if(account==null) return null;
//		AccountResponse accountResponse = new AccountResponse(account.getId(), account.getEmail(),account.getRole().getName());
//		accountResponse.setBirthday(account.getBirthday());
//		accountResponse.setFirstName(account.getFirstName());
//		accountResponse.setLastName(account.getLastName());
//		accountResponse.setUsername(account.getUsername());
//		return accountResponse;
//	}
}
