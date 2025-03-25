package com.ta2khu75.quiz.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.request.QuizRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.QuizResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.entity.Blog;
import com.ta2khu75.quiz.model.entity.Quiz;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { InfoMapper.class, QuestionMapper.class, AccountMapper.class})
public interface QuizMapper {
	
	@Named("toQuizResponse")
	@Mapping(target = "info", source = "quiz", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	@Mapping(target = "blog", source = "blog")
	@Mapping(target = "questions", ignore = true)
	QuizResponse toResponse(Quiz quiz);
	
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "blog", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "quizCategory", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "questions", ignore = true)
	Quiz toEntity(QuizRequest request);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "blog", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "quizCategory", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "questions", ignore = true)
	void update(QuizRequest request, @MappingTarget Quiz quiz);

	@Mapping(target = "commentCount", source = "comments")
	@Mapping(target = "info", source = "blog", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	@Mapping(target = "content",ignore = true)
	@Mapping(target = "quizzes",ignore = true)
	BlogResponse toResponse(Blog blog);

	@Named("toQuizDetailResponse")
	@Mapping(target = "questions", source = "questions", qualifiedByName = "toQuestionDetailResponse")
	@Mapping(target = "info", source = "quiz", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	@Mapping(target = "blog", source = "blog")
	QuizResponse toDetailResponse(Quiz quiz);

	@Named("toQuizQuestionDetailResponse")
	@Mapping(target = "questions", source = "questions", qualifiedByName = "toQuestionAnswerDetailResponse")
	@Mapping(target = "info", source = "quiz", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	@Mapping(target = "blog", source = "blog")
	QuizResponse toQuizQuestionDetailResponse(Quiz quiz);

	@Mapping(target = "content", qualifiedByName = "toQuizResponse")
	PageResponse<QuizResponse> toPageResponse(Page<Quiz> page);
}
