package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.ta2khu75.quiz.model.request.QuizRequest;
import com.ta2khu75.quiz.model.response.QuizResponse;
import com.ta2khu75.quiz.model.response.details.QuizDetaislResponse;
import com.ta2khu75.quiz.model.entity.Quiz;

@Mapper(componentModel = "spring", uses = { AnswerMapper.class })
public interface QuizMapper {
	QuizResponse toResponse(Quiz quiz);

	@Mapping(target = "filePath", ignore = true)
	@Mapping(target = "answers", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userAnswers", ignore = true)
	Quiz toEntity(QuizRequest request);

	@Mapping(target = "filePath", ignore = true)
	@Mapping(target = "answers", ignore = true)
	@Mapping(target = "exam", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userAnswers", ignore = true)
	void update(QuizRequest request, @MappingTarget Quiz quiz);
	
	@Named("toQuizDetailsResponse")
	@Mapping(target = "answers", source = "answers", qualifiedByName = "toAnswerResponse")
	QuizDetaislResponse toDetailsResponse(Quiz quiz);
}
