package com.ta2khu75.quiz.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;
import com.ta2khu75.quiz.model.entity.Blog;
import com.ta2khu75.quiz.model.entity.Exam;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { InfoMapper.class, QuizMapper.class, AccountMapper.class})
public interface ExamMapper {

	@Mapping(target = "commentCount", source = "comments")
	@Mapping(target = "info", source = "blog", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	BlogResponse toResponse(Blog blog);
	
	@Named("toExamResponse")
	@Mapping(target = "info", source = "exam", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	@Mapping(target = "blog", source = "blog")
	ExamResponse toResponse(Exam exam);
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


	@Named("toExamDetailsResponse")
	@Mapping(target = "quizzes", source = "quizzes", qualifiedByName = "toQuizDetailsResponse")
	@Mapping(target = "info", source = "exam", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	@Mapping(target = "blog", source = "blog")
	ExamDetailsResponse toDetailsResponse(Exam exam);

	@Mapping(target = "content", qualifiedByName = "toExamResponse")
	PageResponse<ExamResponse> toPageResponse(Page<Exam> page);
}
