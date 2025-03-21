package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.ta2khu75.quiz.model.entity.UserAnswer;
import com.ta2khu75.quiz.model.response.UserAnswerResponse;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class, QuizMapper.class})
public interface UserAnswerMapper {
	@Named("toUserAnswerResponse")
	@Mapping(target = "answers", source = "answers", qualifiedByName = "toAnswerResponse")
	@Mapping(target = "quiz", source = "quiz", qualifiedByName = "toQuizResponse")
	UserAnswerResponse toResponse(UserAnswer userAnswer);
}
