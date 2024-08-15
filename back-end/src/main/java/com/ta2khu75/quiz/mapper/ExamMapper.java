package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.request.ExamRequest;
import com.ta2khu75.quiz.entity.response.ExamResponse;
import com.ta2khu75.quiz.entity.response.details.ExamDetailsResponse;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    ExamResponse toResponse(Exam exam);
    Exam toEntity(ExamRequest request);
    void update(ExamRequest request, @MappingTarget Exam exam);
    ExamDetailsResponse toDetailsResponse(Exam exam);
    default Long convertExam(Quiz quiz){
        return quiz.getExam().getId();
    }
}
