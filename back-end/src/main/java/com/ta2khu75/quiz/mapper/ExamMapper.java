package com.ta2khu75.quiz.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.request.ExamRequest;
import com.ta2khu75.quiz.entity.response.ExamResponse;
import com.ta2khu75.quiz.entity.response.details.ExamDetailsResponse;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    ExamResponse toResponse(Exam exam);
    @Mapping(target = "examHistories", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	Exam toEntity(ExamRequest request);
    @Mapping(target = "examHistories", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	void update(ExamRequest request, @MappingTarget Exam exam);
    ExamDetailsResponse toDetailsResponse(Exam exam);
}
