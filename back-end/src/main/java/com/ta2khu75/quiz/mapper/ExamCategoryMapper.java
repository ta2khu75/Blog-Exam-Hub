package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.quiz.model.entity.ExamCategory;
import com.ta2khu75.quiz.model.request.ExamCategoryRequest;
import com.ta2khu75.quiz.model.response.ExamCategoryResponse;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExamCategoryMapper {

	ExamCategoryResponse toResponse(ExamCategory examCategory);
	void update(ExamCategoryRequest request, @MappingTarget ExamCategory examCategory);
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "id", ignore = true)
	ExamCategory toEntity(ExamCategoryRequest request);
}
