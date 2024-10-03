package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.ta2khu75.quiz.model.entity.EntityBase;
import com.ta2khu75.quiz.model.response.InfoResponse;

@Mapper(componentModel = "spring")
public interface InfoMapper {
	@Named("toInfoResponse")
    InfoResponse toResponse(EntityBase entityBase);
}
