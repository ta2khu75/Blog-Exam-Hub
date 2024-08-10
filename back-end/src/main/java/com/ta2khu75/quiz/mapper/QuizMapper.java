package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.request.QuizRequest;
import com.ta2khu75.quiz.entity.response.QuizResponse;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    @Mapping(source = "exam", target = "examId")
    QuizResponse toResponse(Quiz quiz);
    Quiz toEntity(QuizRequest request);
    void update(QuizRequest request, @MappingTarget Quiz quiz);
    default Long convertExam(Exam exam){
        return exam.getId();
    }
    
}
