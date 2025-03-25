package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.ta2khu75.quiz.model.request.QuestionRequest;
import com.ta2khu75.quiz.model.response.QuestionResponse;
import com.ta2khu75.quiz.model.entity.Question;

@Mapper(componentModel = "spring", uses = { AnswerMapper.class })
public interface QuestionMapper {
	@Named("toQuestionResponse")
	@Mapping(target = "answers", ignore = true)
	@Mapping(target = "question", source = "questionString")
	QuestionResponse toResponse(Question question);

	@Mapping(target = "filePath", ignore = true)
	@Mapping(target = "answers", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userAnswers", ignore = true)
	@Mapping(target = "questionString", source = "question")
	Question toEntity(QuestionRequest request);

	@Mapping(target = "filePath", ignore = true)
	@Mapping(target = "answers", ignore = true)
	@Mapping(target = "quiz", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userAnswers", ignore = true)
	@Mapping(target = "questionString", source = "question")
	void update(QuestionRequest request, @MappingTarget Question question);
	
	@Named("toQuestionDetailResponse")
	@Mapping(target = "answers", source = "answers", qualifiedByName = "toAnswerResponse")
	@Mapping(target = "question", source = "questionString")
	QuestionResponse toDetailResponse(Question question);
	
	@Named("toQuestionAnswerDetailResponse")
	@Mapping(target = "answers", source = "answers", qualifiedByName = "toAnswerDetailResponse")
	@Mapping(target = "question", source = "questionString")
	QuestionResponse toQuestionAnswerDetailResponse(Question question);
}
