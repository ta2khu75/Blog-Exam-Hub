package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.entity.ExamResult;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;

@Mapper(componentModel = "spring", uses = { InfoMapper.class, ExamMapper.class, AccountMapper.class, UserAnswerMapper.class })
public interface ExamResultMapper {
	@Named("toExamResultResponse")
	@Mapping(target = "info", source = "examResult", qualifiedByName = "toInfoResponse")
	@Mapping(target = "account", source = "account", qualifiedByName = "toAccountResponse")
	@Mapping(target = "exam", source = "exam", qualifiedByName = "toExamDetailsResponse")
	@Mapping(target = "userAnswers",ignore = true )
	ExamResultResponse toResponse(ExamResult examResult);

	@Mapping(target = "info", source = "examResult", qualifiedByName = "toInfoResponse")
	@Mapping(target = "account", source = "account", qualifiedByName = "toAccountResponse")
	@Mapping(target = "exam", source = "exam", qualifiedByName = "toExamQuizDetailResponse")
	@Mapping(target = "userAnswers", source = "userAnswers", qualifiedByName = "toUserAnswerResponse")
	ExamResultResponse toDetailResponse(ExamResult examResult);

	@Mapping(target = "content", qualifiedByName = "toExamResultResponse")
	PageResponse<ExamResultResponse> toPageResponse(Page<ExamResult> page);
}
