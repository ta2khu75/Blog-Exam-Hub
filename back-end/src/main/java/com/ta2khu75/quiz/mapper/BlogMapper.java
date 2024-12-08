package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.entity.Blog;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.BlogDetailsResponse;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { InfoMapper.class, ExamMapper.class, AccountMapper.class })
public interface BlogMapper {

	@Named("toBlogResponse")
	@Mapping(target = "commentCount", source = "comments")
	@Mapping(target = "info", source = "blog", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	BlogResponse toResponse(Blog blog);
	
	@Named("toBlogDetailsResponse")
	@Mapping(target = "commentCount", source = "comments")
	@Mapping(target = "info", source = "blog", qualifiedByName = "toInfoResponse")
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	@Mapping(target = "exams", source = "exams", qualifiedByName = "toExamResponse")
	BlogDetailsResponse toDetailsResponse(Blog blog);

	@Mapping(target = "blogTags", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "comments", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "exams", ignore = true)
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
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "viewCount", ignore = true)
	void update(BlogRequest blogResponse, @MappingTarget Blog blog);

	@Mapping(target = "content", source = "content", qualifiedByName = "toBlogResponse")
	PageResponse<BlogResponse> toPageResponse(Page<Blog> blogs);
}
