package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ta2khu75.quiz.model.entity.ReportTarget;
import com.ta2khu75.quiz.model.request.ReportTargetRequest;
import com.ta2khu75.quiz.model.response.ReportTargetResponse;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface ReportTargetMapper {
	@Mapping(target = "authorId", ignore = true)
	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "author", ignore = true)
	ReportTarget toEntity(ReportTargetRequest request);
	@Mapping(target = "author", source = "author", qualifiedByName = "toAccountResponse")
	ReportTargetResponse toResponse(ReportTarget reportTarget);
}
