package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.ta2khu75.quiz.model.request.AnswerRequest;
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.model.entity.Answer;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
	@Named("toAnswerResponse")
	@Mapping(target = "answer", source = "answerString")
	@Mapping(target = "correct", ignore = true)
	AnswerResponse toResponse(Answer answer);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userAnswers", ignore = true)
	@Mapping(target = "answerString", source = "answer")
	Answer toEntity(AnswerRequest request);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userAnswers", ignore = true)
	@Mapping(target = "answerString", source = "answer")
	@Mapping(target = "question", ignore = true)
	void update(AnswerRequest request, @MappingTarget Answer answer);

	@Named("toAnswerDetailResponse")
	@Mapping(target = "answer", source = "answerString")
	AnswerResponse toDetailResponse(Answer answer);
}