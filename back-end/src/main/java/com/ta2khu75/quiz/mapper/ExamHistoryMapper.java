package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.quiz.entity.ExamHistory;
import com.ta2khu75.quiz.entity.response.ExamHistoryResponse;
import com.ta2khu75.quiz.entity.response.details.ExamHistoryDetailsResponse;
@Mapper(componentModel = "spring")
public interface ExamHistoryMapper {
	ExamHistoryResponse toResponse(ExamHistory examHistory);
	ExamHistoryDetailsResponse toDetailsResponse(ExamHistory examHistory);
}
