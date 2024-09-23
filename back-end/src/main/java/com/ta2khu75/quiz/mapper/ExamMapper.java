package com.ta2khu75.quiz.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;
import com.ta2khu75.quiz.model.response.details.QuizDetaislResponse;
import com.ta2khu75.quiz.model.entity.Answer;
import com.ta2khu75.quiz.model.entity.Exam;
import com.ta2khu75.quiz.model.entity.Quiz;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExamMapper {
	@Named("toExamResponse")
	ExamResponse toResponse(Exam exam);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "lastModifiedAt", ignore = true)
	@Mapping(target = "blog", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "examCategory", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	Exam toEntity(ExamRequest request);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "lastModifiedAt", ignore = true)
	@Mapping(target = "blog", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "examCategory", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	void update(ExamRequest request, @MappingTarget Exam exam);
	@Mapping(target = "content", qualifiedByName = "toExamResponse")
	PageResponse<ExamResponse> toPageResponse(Page<Exam> page);

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
		if (answer == null)
			return null;
		AnswerResponse response = new AnswerResponse();
		response.setId(answer.getId());
		response.setAnswer(answer.getAnswerString());
		response.setCorrect(answer.getCorrect());
		return response;
	}

	default List<AnswerResponse> toResponseDetailsList(List<Answer> list) {
		if (list == null)
			return null;
		return list.stream().map((answer) -> toResponseDetails(answer)).toList();
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
