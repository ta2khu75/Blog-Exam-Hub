package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;
import com.ta2khu75.quiz.model.entity.Exam;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AccountMapper.class, InfoMapper.class, QuizMapper.class })
public interface ExamMapper {
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "blog", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "examCategory", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	Exam toEntity(ExamRequest request);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "blog", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "examCategory", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	void update(ExamRequest request, @MappingTarget Exam exam);

	@Named("toExamResponse")
	@Mapping(target = "info", source = "exam", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	ExamResponse toResponse(Exam exam);

	@Named("toExamDetailsResponse")
	@Mapping(target = "quizzes", source = "quizzes", qualifiedByName = "toQuizDetailsResponse")
	@Mapping(target = "info", source = "exam", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	ExamDetailsResponse toDetailsResponse(Exam exam);

	@Mapping(target = "content", qualifiedByName = "toExamResponse")
	PageResponse<ExamResponse> toPageResponse(Page<Exam> page);
}
