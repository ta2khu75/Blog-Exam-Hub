package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.ta2khu75.quiz.entity.Answer;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.request.AnswerRequest;
import com.ta2khu75.quiz.entity.response.AnswerResponse;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(source = "quiz", target = "quizId", qualifiedByName = "convertQuizToId")
    AnswerResponse toResponse(Answer answer);
    @Mapping(target = "id", ignore = true)
	@Mapping(target = "quiz", ignore = true)
	Answer toEntity(AnswerRequest request);
    @Mapping(target = "id", ignore = true)
	@Mapping(target = "quiz", ignore = true)
	void update(AnswerRequest request, @MappingTarget Answer answer);
    @Named("convertQuizToId")
    default Long convertQuizToId(Quiz quiz) {
        return quiz != null ? quiz.getId() : null;
    } 
}