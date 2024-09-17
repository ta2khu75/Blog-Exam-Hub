package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ta2khu75.quiz.model.entity.Blog;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BlogMapper {
	BlogResponse toResponse(Blog blog);

	@Mapping(target = "blogTags", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "comments", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "lastModifiedAt", ignore = true)
	@Mapping(target = "viewCount", ignore = true)
	Blog toEntity(BlogRequest blogResponse);

	@Mapping(target = "blogTags", ignore = true)
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "comments", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "lastModifiedAt", ignore = true)
	@Mapping(target = "viewCount", ignore = true)
	void update(BlogRequest blogResponse, @MappingTarget Blog blog);
}
