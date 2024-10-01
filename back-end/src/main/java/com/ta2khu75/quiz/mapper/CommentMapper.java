package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.entity.Comment;
import com.ta2khu75.quiz.model.request.CommentRequest;
import com.ta2khu75.quiz.model.response.CommentResponse;
import com.ta2khu75.quiz.model.response.PageResponse;

import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface CommentMapper {
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "blog", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "filePath", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Comment toEntity(CommentRequest request);
	@Mapping(target = "author", ignore = true)
	@Mapping(target = "blog", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "filePath", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	void update(CommentRequest request, @MappingTarget Comment entity);
	CommentResponse toResponse(Comment entity);
	PageResponse<CommentResponse> toPageResponse(Page<Comment> commentPage);	
}
