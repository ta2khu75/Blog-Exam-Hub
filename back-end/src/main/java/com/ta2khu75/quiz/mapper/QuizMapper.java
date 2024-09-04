package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ta2khu75.quiz.model.request.QuizRequest;
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.model.response.QuizResponse;
import com.ta2khu75.quiz.model.response.details.QuizDetaislResponse;
import com.ta2khu75.quiz.model.entity.Answer;
import com.ta2khu75.quiz.model.entity.Quiz;

@Mapper(componentModel = "spring")
public interface QuizMapper {
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

	QuizDetaislResponse toDetailsResponse(Quiz quiz);

	default AnswerResponse toResponseDetails(Answer answer) {
		if (answer == null)
			return null;
		AnswerResponse response = new AnswerResponse();
		response.setAnswer(answer.getAnswerString());
		response.setCorrect(answer.getCorrect());
		return response;
	}
}
