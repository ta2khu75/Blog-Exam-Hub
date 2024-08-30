package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ta2khu75.quiz.entity.Answer;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.request.QuizRequest;
import com.ta2khu75.quiz.entity.response.AnswerResponse;
import com.ta2khu75.quiz.entity.response.QuizResponse;
import com.ta2khu75.quiz.entity.response.details.QuizDetaislResponse;

@Mapper(componentModel = "spring")
public interface QuizMapper {
	@Mapping(target = "examId", source = "exam.id")
	QuizResponse toResponse(Quiz quiz);

	@Mapping(target = "filePath", ignore = true)
	@Mapping(target = "answers", ignore = true)
	@Mapping(target = "exam", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userAnswers", ignore = true)
	Quiz toEntity(QuizRequest request);

	@Mapping(target = "filePath", ignore = true)
	@Mapping(target = "answers", ignore = true)
	@Mapping(target = "exam", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userAnswers", ignore = true)
	void update(QuizRequest request, @MappingTarget Quiz quiz);

	@Mapping(target = "examId", source = "exam.id")
	QuizDetaislResponse toDetailsResponse(Quiz quiz);

	default AnswerResponse toResponseDetails(Answer answer) {
		if (answer == null)
			return null;
		AnswerResponse response = new AnswerResponse();
		response.setAnswer(answer.getAnswerString());
		response.setCorrect(answer.getCorrect());
		response.setQuizId(answer.getQuiz().getId());
		return response;
	}
}
