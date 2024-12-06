package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.response.PageResponse;

@Mapper(componentModel = "spring")
public interface PageMapper {
	default <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(page.getNumber(), page.getTotalElements(), page.getTotalPages(), page.getContent());
    }	
}
