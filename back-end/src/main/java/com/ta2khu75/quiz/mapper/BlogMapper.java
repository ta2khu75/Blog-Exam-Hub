package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.entity.Blog;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.PageResponse;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { InfoMapper.class, QuizMapper.class, AccountMapper.class })
public interface BlogMapper {

	@Named("toBlogResponse")
	@Mapping(target = "commentCount", source = "comments")
	@Mapping(target = "info", source = "blog", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	@Mapping(target = "content",ignore = true)
	@Mapping(target = "quizzes",ignore = true)
	BlogResponse toResponse(Blog blog);
	
	@Named("toBlogDetailsResponse")
	@Mapping(target = "commentCount", source = "comments")
	@Mapping(target = "info", source = "blog", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	@Mapping(target = "quizzes", source = "quizzes", qualifiedByName = "toQuizResponse")
	BlogResponse toDetailsResponse(Blog blog);

	@Mapping(target = "blogTags", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "comments", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "viewCount", ignore = true)
	Blog toEntity(BlogRequest blogResponse);

	@Mapping(target = "blogTags", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "comments", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "quizzes", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "viewCount", ignore = true)
	void update(BlogRequest blogResponse, @MappingTarget Blog blog);

	@Mapping(target = "content", source = "content", qualifiedByName = "toBlogResponse")
	PageResponse<BlogResponse> toPageResponse(Page<Blog> blogs);
}
