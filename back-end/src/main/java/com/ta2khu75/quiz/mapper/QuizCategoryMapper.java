package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.quiz.model.entity.QuizCategory;
import com.ta2khu75.quiz.model.request.QuizCategoryRequest;
import com.ta2khu75.quiz.model.response.QuizCategoryResponse;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuizCategoryMapper {

	QuizCategoryResponse toResponse(QuizCategory examCategory);

	@Mapping(target = "quizzes", ignore = true)
	@Mapping(target = "id", ignore = true)
	void update(QuizCategoryRequest request, @MappingTarget QuizCategory examCategory);

	@Mapping(target = "quizzes", ignore = true)
	@Mapping(target = "id", ignore = true)
	QuizCategory toEntity(QuizCategoryRequest request);
}
