package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ta2khu75.quiz.entity.Answer;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.request.AnswerRequest;
import com.ta2khu75.quiz.entity.response.AnswerResponse;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(source = "quiz", target = "quizId")
    AnswerResponse toResponse(Answer answer);
    Answer toEntity(AnswerRequest request);
    void update(AnswerRequest request, @MappingTarget Answer answer);
    default Long convertQuiz(Quiz quiz){
        return quiz.getId();
    }
}