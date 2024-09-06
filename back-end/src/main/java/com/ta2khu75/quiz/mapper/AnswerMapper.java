package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ta2khu75.quiz.model.request.AnswerRequest;
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.model.entity.Answer;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(target = "answer", source = "answerString")
    AnswerResponse toResponse(Answer answer);
	@Mapping(target = "answerString", source = "answer")
	@Mapping(target = "id", ignore = true)
	Answer toEntity(AnswerRequest request);
	@Mapping(target = "answerString", source = "answer")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "quiz", ignore = true)
	void update(AnswerRequest request, @MappingTarget Answer answer);
}