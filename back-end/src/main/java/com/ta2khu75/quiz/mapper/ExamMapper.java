package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.request.ExamRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.ExamResponse;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    ExamResponse toResponse(Exam exam);
    Exam toEntity(ExamRequest request);
    void update(ExamRequest request, @MappingTarget Exam exam);
}
