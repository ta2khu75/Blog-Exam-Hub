package com.ta2khu75.quiz.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.ta2khu75.quiz.model.entity.BlogTag;
import com.ta2khu75.quiz.model.entity.Comment;
import com.ta2khu75.quiz.model.entity.EntityBase;
import com.ta2khu75.quiz.model.response.InfoResponse;

@Mapper(componentModel = "spring")
public interface InfoMapper {
	
	default String blogTag(BlogTag blogTag) {
		if (blogTag == null) {
			return null;
		}
		return blogTag.getName();
	}

	default int commentCount(List<Comment> comments) {
		return comments == null ? 0 : comments.size();
	}
	
	@Named("toInfoResponse")
    InfoResponse toResponse(EntityBase entityBase);
}
