package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ta2khu75.quiz.model.entity.UserAnswer;
import com.ta2khu75.quiz.model.response.details.UserAnswerResponse;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class})
public interface UserAnswerMapper {
	@Mapping(target = "answers", source = "answers", qualifiedByName = "toAnswerResponse")
	UserAnswerResponse toResponse(UserAnswer userAnswer);
}
